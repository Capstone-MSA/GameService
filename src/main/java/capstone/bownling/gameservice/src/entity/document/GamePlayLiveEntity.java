package capstone.bownling.gameservice.src.entity.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "live_game_throws")
@Getter
@Setter
public class GamePlayLiveEntity {
    @Id
    private String id;
    private String matchRoomId;
    private Long userId;
    private int frame;
    private int round;
    private int score;
    private LocalDateTime timestamp = LocalDateTime.now();

    @Indexed()  // ✅ TTL: 1시간 후 자동 삭제
    private Date expiredAt;


    public GamePlayLiveEntity(String matchRoomId, Long userId, int frame, int round, int score) {
        this.matchRoomId = matchRoomId;
        this.userId = userId;
        this.frame = frame;
        this.round = round;
        this.score = score;
        this.timestamp = LocalDateTime.now();
        this.expiredAt = Date.from(Instant.now().plusSeconds(86400)); // 현재 시각 + 1시간
    }


    public GamePlayLiveEntity() {}
    // Getters, Setters
}