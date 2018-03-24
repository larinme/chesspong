package solution.model;

import solution.model.enums.MatchResult;
import solution.model.enums.SetResult;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private List<AbstractSet> sets;
    private double firstPlayerMatchScore = 0;
    private double secondPlayerMatchScore = 0;

    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.sets = new ArrayList<AbstractSet>();
    }

    public Match(Player firstPlayer, Player secondPlayer, List<AbstractSet> sets) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.sets = sets;
        for (AbstractSet set : sets) {
            updateTotalScores(set);
        }
    }

    public void addSet(AbstractSet set) {
        sets.add(set);
        updateTotalScores(set);
    }

    public MatchResult getMatchResult() {
        return MatchResult.findResultByScore(firstPlayerMatchScore, secondPlayerMatchScore);
    }

    private void updateTotalScores(AbstractSet set) {
        SetResult result = set.getResult();
        if (result == SetResult._1_0) {
            firstPlayerMatchScore++;
        } else if (result == SetResult.DRAW) {
            firstPlayerMatchScore += 0.5;
            secondPlayerMatchScore += 0.5;
        } else {
            secondPlayerMatchScore++;
        }
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public List<AbstractSet> getSets() {
        return sets;
    }
}
