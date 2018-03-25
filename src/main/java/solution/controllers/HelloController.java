package solution.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String redirectToMainPage() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tournament/createTournament")
    public String addParticipantsPage() {
        return "addParticipants";
    }
}
