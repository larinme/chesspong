package solution.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import solution.communication.CreateTournament;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @RequestMapping(value = "/createWithDefaultParticipants")
    public String addParticipantsPage(@RequestBody CreateTournament controller) {
        return "index";
    }
}
