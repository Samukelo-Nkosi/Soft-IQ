/**
 * this will represent a single multiple-choice
 * quiz question
 */


package softiq;

public class Question {

    private final String questionText;
    private final String[] options;
    private final int correctIndex; // indexes into options[] of the correct answer

    public Question(String questionText, String[] options, int correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctIndex;
    }
}
