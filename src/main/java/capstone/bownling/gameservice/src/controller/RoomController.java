package capstone.bownling.gameservice.src.controller;

import capstone.bownling.gameservice.config.RoomSubscriptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomSubscriptionManager manager;

    @GetMapping("/active-rooms")
    public List<String> getActiveRooms() {
        return manager.getActiveRoomIds();
    }
}