package solution.converters;

import solution.model.Match;
import solution.model.enums.MatchResult;

public interface Converter {

    MatchResult defineResult(Match match);
}
