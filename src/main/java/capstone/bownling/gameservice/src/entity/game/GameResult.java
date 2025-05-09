package capstone.bownling.gameservice.src.entity.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GameResult {
    @Id
    @GeneratedValue
    private Long id;

    private String matchRoomId;

    private String userId;

    private int frameNumber;     // 1~10
    private int throwRound;      // 1 or 2 or (3 for 10프레임)
    private int score;
}
