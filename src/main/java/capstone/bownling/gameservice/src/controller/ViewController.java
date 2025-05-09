package capstone.bownling.gameservice.src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/")
    public String matchRoomPage() {
        return "matchroom";
    }

    @GetMapping("/game")
    public String scoreView(@RequestParam String roomId, @RequestParam Long userId, Model model) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", userId);
        return "score-input";
    }

    @GetMapping("/watch")
    public String scoreViewOnly(@RequestParam String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "score-view";
    }
}