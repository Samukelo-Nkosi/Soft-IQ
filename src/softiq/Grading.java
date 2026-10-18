package softiq;

public final class Grading {
    private Grading() {
    }

    public static double percentage(int score, int total) {
        if (total <= 0) {
            return 0.0;
        }
        return score * 100.0 / total;
    }

    public static String letterFor(double percentage) {
        if (percentage >= 90.0) {
            return "A";
        }
        if (percentage >= 80.0) {
            return "B";
        }
        if (percentage >= 70.0) {
            return "C";
        }
        if (percentage >= 60.0) {
            return "D";
        }
        return "F";
    }
}
