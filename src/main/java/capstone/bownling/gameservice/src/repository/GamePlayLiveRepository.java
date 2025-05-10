package capstone.bownling.gameservice.src.repository;

import capstone.bownling.gameservice.src.entity.document.GamePlayLiveEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GamePlayLiveRepository extends MongoRepository<GamePlayLiveEntity, String> {
    List<GamePlayLiveEntity> findByMatchRoomId(String matchRoomId);
    void deleteByMatchRoomId(String matchRoomId);

}
