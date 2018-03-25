package solution.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tournament")
public class TournamentController {


    @RequestMapping(method = RequestMethod.GET, value = "createWithDefaultParticipants")
    public String addParticipantsPage(ModelAndView modelAndView) {
        return "index";
    }
}
