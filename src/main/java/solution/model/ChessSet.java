package solution.model;

import solution.model.enums.SetResult;
import solution.model.enums.SetType;

import static solution.utils.Constants.CHESS_TIME_LIMIT;
import static solution.utils.Constants.TENNIS_LAST_POINT;

public class ChessSet extends AbstractSet {

    private SetResult result;
    private int winnerExpendedTime = -1;

    public ChessSet(Player firstPlayer, Player secondPlayer, SetResult setResult, int winnerExpendedTime) {
        super(firstPlayer, secondPlayer);
        this.result = setResult;
        if (setResult != SetResult.DRAW) {
            this.winnerExpendedTime = winnerExpendedTime;
        }
    }

    public SetResult getResult() {
        return result;
    }

    public int toTennisPoints() {
        if (winnerExpendedTime == -1) {
            return winnerExpendedTime;
        } else {
            int secondsPerPoint = CHESS_TIME_LIMIT / TENNIS_LAST_POINT;
            return winnerExpendedTime / secondsPerPoint;
        }
    }

    public SetType getSetType() {
        return SetType.CHESS;
    }

    public int getWinnerExpendedTime() {
        return winnerExpendedTime;
    }
}
