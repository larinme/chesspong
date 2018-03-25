package solution.communication;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CreateTournament {

    private List<String> participants;

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

}
