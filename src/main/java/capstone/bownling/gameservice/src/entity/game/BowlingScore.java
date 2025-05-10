package capstone.bownling.gameservice.src.entity.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "bowling_score",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bowling_score_composite",
                        columnNames = {"matchRoomId", "userId", "frameNumber", "throwRound"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_bowling_score_match_user",
                        columnList = "matchRoomId, userId"
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BowlingScore {
    @EmbeddedId
    private BowlingScoreId id;

    private int score;
    private String scoreType;
    private String scoreStatus;

}
