package softiq;

public final class QuizAttempt {
    private final int id;
    private final String playerName;
    private final String topic;
    private final int score;
    private final int total;
    private final double percentage;
    private final String grade;
    private final String takenAt;

    public QuizAttempt(int id, String playerName, String topic, int score, int total,
                       double percentage, String grade, String takenAt) {
        this.id = id;
        this.playerName = playerName;
        this.topic = topic;
        this.score = score;
        this.total = total;
        this.percentage = percentage;
        this.grade = grade;
        this.takenAt = takenAt;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTopic() {
        return topic;
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getGrade() {
        return grade;
    }

    public String getTakenAt() {
        return takenAt;
    }
}
