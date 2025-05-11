package capstone.bownling.gameservice.src.controller;

import capstone.bownling.gameservice.src.entity.document.GamePlayLiveEntity;
import capstone.bownling.gameservice.src.entity.game.GamePlayMessage;
import capstone.bownling.gameservice.src.repository.GamePlayLiveRepository;
import capstone.bownling.gameservice.src.service.ScoreService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;


    @MessageMapping("/game/{roomId}/play")
    public void handleThrow(@DestinationVariable String roomId, GamePlayMessage message) {
        System.out.printf("수신: roomId=%s, userId=%s, frame=%d, round=%d, score=%d%n",
                roomId, message.getUserId(), message.getFrame(), message.getRound(), message.getScore());
        scoreService.handleThrow(roomId, message);
    }
}