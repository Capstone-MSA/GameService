package capstone.bownling.gameservice.config.websock;

import capstone.bownling.gameservice.config.RoomSubscriptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketConnectListener implements ApplicationListener<SessionSubscribeEvent> {

    @Autowired
    private RoomSubscriptionManager manager;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        String dest = (String) event.getMessage().getHeaders().get("simpDestination");
        if (dest != null && dest.startsWith("/topic/game/")) {
            String roomId = dest.replace("/topic/game/", "");
            manager.subscribeRoom(roomId);
        }
    }
}

