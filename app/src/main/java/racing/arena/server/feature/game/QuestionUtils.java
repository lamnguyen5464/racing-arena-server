package racing.arena.server.feature.game;

import java.util.List;

public class QuestionUtils {
    private static final int RANGE = 100;

    record Question(String question, String answer) {
    }

    public static Question createQuestion() {
        int firstParam = (int) (Math.random() * RANGE);
        int secondParam = (int) (Math.random() * RANGE);
        int answer = 0;
        String operator = "";
        switch ((int) (Math.random() * 5)) {
            case 0 -> {
                operator = "+";
                answer = firstParam + secondParam;
            }
            case 1 -> {
                operator = "-";
                answer = firstParam - secondParam;
            }
            case 2 -> {
                operator = "*";
                answer = firstParam * secondParam;
            }
            case 3 -> {
                operator = "/";
                answer = firstParam / secondParam;
            }
            case 4 -> {
                operator = "%";
                answer = firstParam % secondParam;
            }
        }
        return new Question(firstParam + " " + operator + " " + secondParam, Integer.toString(answer));
    }
}
