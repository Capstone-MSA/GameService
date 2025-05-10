package capstone.bownling.gameservice.src.entity.game;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BowlingScoreId implements Serializable {

    private String matchRoomId;
    private String userId;
    private int frameNumber;
    private int throwRound;

    public BowlingScoreId() {}

    public BowlingScoreId(String matchRoomId, String userId, int frameNumber, int throwRound) {
        this.matchRoomId = matchRoomId;
        this.userId = userId;
        this.frameNumber = frameNumber;
        this.throwRound = throwRound;
    }

    // equals() and hashCode() 필수!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BowlingScoreId)) return false;
        BowlingScoreId that = (BowlingScoreId) o;
        return frameNumber == that.frameNumber &&
                throwRound == that.throwRound &&
                Objects.equals(matchRoomId, that.matchRoomId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchRoomId, userId, frameNumber, throwRound);
    }

    // Getters and Setters ...
}