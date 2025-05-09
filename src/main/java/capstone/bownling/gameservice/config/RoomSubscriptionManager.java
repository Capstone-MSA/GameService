package capstone.bownling.gameservice.config;

import capstone.bownling.gameservice.config.Redis.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class RoomSubscriptionManager {

    private static final int MAX_ROOM_COUNT = 1000;

    private final RedisMessageListenerContainer container;
    private final RedisSubscriber subscriber;
    private final Map<String, Long> roomActivityMap = new ConcurrentHashMap<>();

    @Autowired
    public RoomSubscriptionManager(RedisMessageListenerContainer container, RedisSubscriber subscriber) {
        this.container = container;
        this.subscriber = subscriber;
    }

    public synchronized void subscribeRoom(String roomId) {
        String topic = "game-room:" + roomId;

        if (roomActivityMap.containsKey(topic)) {
            roomActivityMap.put(topic, System.currentTimeMillis());
            return;
        }

        if (roomActivityMap.size() >= MAX_ROOM_COUNT) {
            String oldest = Collections.min(roomActivityMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            container.removeMessageListener(subscriber, new PatternTopic(oldest));
            roomActivityMap.remove(oldest);
        }

        container.addMessageListener(subscriber, new PatternTopic(topic));
        roomActivityMap.put(topic, System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 60_000)
    public void cleanupInactiveRooms() {
        long now = System.currentTimeMillis();
        long timeout = 10 * 60 * 1000;

        for (Map.Entry<String, Long> entry : new HashMap<>(roomActivityMap).entrySet()) {
            if (now - entry.getValue() > timeout) {
                container.removeMessageListener(subscriber, new PatternTopic(entry.getKey()));
                roomActivityMap.remove(entry.getKey());
            }
        }
    }

    public List<String> getActiveRoomIds() {
        return roomActivityMap.keySet().stream()
                .map(key -> key.replace("game-room:", ""))
                .collect(Collectors.toList());
    }
}
