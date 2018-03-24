package solution.model;

import solution.model.enums.SetResult;
import solution.model.enums.SetType;

public abstract class AbstractSet {


    protected final Player firstPlayer;
    protected final Player secondPlayer;

    public AbstractSet(Player firstPlayer, Player secondPlayer) {

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public abstract SetResult getResult();

    public abstract int toTennisPoints();

    public abstract SetType getSetType();
}
