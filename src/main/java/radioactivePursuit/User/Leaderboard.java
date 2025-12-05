package radioactivePursuit.User;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private static final Leaderboard instance = new Leaderboard();

    List<User> userList = new ArrayList<>();

    private Leaderboard() {
    }

    public static Leaderboard getInstance() {
        return instance;
    }

    //could be not thread safe if BDD testing is implemented
    public void addUserToLeaderboard(User user) {
        userList.add(user);
    }


    public List<User> calculateLeaderBoard() {
        return userList.stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(3)
                .toList();
    }

    public void displayLeaderBoard() {
        System.out.println("***LEADERBOARD***\n");
        System.out.println("*** NAME --- SCORE ***\n");
        List<User> topThreeUsers = calculateLeaderBoard();
        if (topThreeUsers.size() < 3) {
            for (User user : topThreeUsers) {
                System.out.println("*** " + user.getName() + " --- " + user.getScore() + " ***");
            }
        } else {
            System.out.println("*** " + topThreeUsers.get(0).getName() + " --- " + topThreeUsers.get(0).getScore() + " ***\n");
            System.out.println("*** " + topThreeUsers.get(1).getName() + " --- " + topThreeUsers.get(1).getScore() + " ***\n");
            System.out.println("*** " + topThreeUsers.get(2).getName() + " ---  " + topThreeUsers.get(2).getScore() + "  ***\n");
        }
    }
}
