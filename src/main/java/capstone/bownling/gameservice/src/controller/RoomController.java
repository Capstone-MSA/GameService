package capstone.bownling.gameservice.src.controller;

import capstone.bownling.gameservice.config.RoomSubscriptionManager;
import capstone.bownling.gameservice.src.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class RoomController {

    private final RoomSubscriptionManager manager;
    private final ScoreService scoreService;

    @GetMapping("/active-rooms")
    public List<String> getActiveRooms() {
        return manager.getActiveRoomIds();
    }

    @GetMapping("/scores")
    public List<String> getScores(@RequestParam("matchRoomId") String roomId) {
        return scoreService.getScoresByRoomId(roomId);
    }
}