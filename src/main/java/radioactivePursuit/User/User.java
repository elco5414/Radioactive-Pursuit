package radioactivePursuit.User;

public class User {
    private static String name;
    private static int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setScore(int newScore) {
        score = newScore;
    }
}
