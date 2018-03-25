package solution.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequestMapping("tournament")
public class TournamentController {


    @RequestMapping(method = RequestMethod.GET, value = "/createWithDefaultParticipants")
    public String addParticipantsPage() {
        return "index";
    }
}
