package capstone.bownling.gameservice.src.repository;

import capstone.bownling.gameservice.src.entity.game.BowlingScore;
import capstone.bownling.gameservice.src.entity.game.BowlingScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BowlingScoreRepository extends JpaRepository<BowlingScore, BowlingScoreId> {
    List<BowlingScore> findByIdMatchRoomId(String matchRoomId);
}
