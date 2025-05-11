package capstone.bownling.gameservice.src.entity.game;

import lombok.Data;

@Data
public class GamePlayMessage {
    private String matchRoomId;
    private String userId;
    private int frame;
    private int round;
    private int score;
}
