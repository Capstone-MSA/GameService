package capstone.bownling.gameservice.config.Redis;

import capstone.bownling.gameservice.src.entity.game.GamePlayMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RedisSubscriber implements MessageListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String payload = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("🔎 Redis 수신 메시지: " + payload);

        String roomId = channel.replace("game-room:", "");
        GamePlayMessage score = new Gson().fromJson(new String(message.getBody(), StandardCharsets.UTF_8), GamePlayMessage.class);
        messagingTemplate.convertAndSend("/topic/game/" + roomId, score);
    }
}
