package solution.model;

import solution.model.enums.SetResult;
import solution.model.enums.SetType;
import solution.utils.Constants;

public class TableTennisSet extends AbstractSet {

    private final int firstPlayerScore;
    private final int secondPlayerScore;
    private SetResult result;

    public TableTennisSet(Player firstPlayer, int firstPlayerScore, Player secondPlayer, int secondPlayerScore) {
        super(firstPlayer, secondPlayer);
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    @Override
    public SetResult getResult() {
        if (result == null) {
            this.result = calculateResult();
        }
        return result;
    }

    @Override
    public int toTennisPoints() {
        return Math.min(firstPlayerScore, secondPlayerScore);
    }

    public SetType getSetType() {
        return SetType.TABLE_TENNIS;
    }

    private SetResult calculateResult() {
        if (Constants.TENNIS_LAST_POINT == firstPlayerScore
                && Constants.TENNIS_LAST_POINT == secondPlayerScore) {
            return SetResult.DRAW;
        } else if (Constants.TENNIS_LAST_POINT == secondPlayerScore) {
            return SetResult._0_1;
        } else if (Constants.TENNIS_LAST_POINT == firstPlayerScore) {
            return SetResult._1_0;
        } else {
            throw new IllegalArgumentException("Tennis last poin should be equals 20");
        }
    }
}
