package solution.model.enums;

import java.text.MessageFormat;

public enum MatchResult {

    _0_3(0, 3),
    _05_35(0.5, 3.5),
    _1_3(1, 3),
    _15_25(1.5, 2.5),
    _2_2(2, 2),
    _25_15(2.5, 1.5),
    _3_1(3, 1),
    _3_05(3, 0.5),
    _3_0(3, 0);

    private final double firstPlayerScore;
    private final double secondPlayerScore;

    MatchResult(double firstPlayerScore, double secondPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public static MatchResult findResultByScore(double firstPlayerScore, double secondPlayerScore) {
        MatchResult[] values = values();
        for (MatchResult value : values) {
            if (value.firstPlayerScore == firstPlayerScore && value.secondPlayerScore == secondPlayerScore) {
                return value;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format(
                "Not score found for {0} - {1}",
                firstPlayerScore,
                secondPlayerScore)
        );
    }
}
