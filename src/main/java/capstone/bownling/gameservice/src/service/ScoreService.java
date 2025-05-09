package capstone.bownling.gameservice.src.service;

import capstone.bownling.gameservice.src.entity.document.GamePlayLiveEntity;
import capstone.bownling.gameservice.src.entity.game.GamePlayMessage;
import capstone.bownling.gameservice.src.repository.GamePlayLiveRepository;
import com.google.gson.Gson;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GamePlayLiveRepository mongoRepository;

    public void handleThrow(String roomId, GamePlayMessage message) {
        GamePlayLiveEntity entity = new GamePlayLiveEntity(
                roomId, message.getUserId(), message.getFrame(), message.getRound(), message.getScore());

        try{
            // Mongo 저장
            mongoRepository.save(entity);

            // STOMP 브로드캐스트
            redisTemplate.convertAndSend("game-room:" + roomId, new Gson().toJson(message));
        } catch (DuplicateKeyException ex) {
            System.out.println("⚠️ 1이미 동일한 투구 정보가 존재합니다.");
        } catch(Exception e){
            System.out.println("⚠️ 2이미 동일한 투구 정보가 존재합니다.");
        }

    }
}
