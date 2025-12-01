package radioactivePursuit.User;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    //this will store all the users and their scores, at the end of each game the current user will be added in
    private static List<User> userList = new ArrayList<>();

    private List<User> calculateLeaderBoard() {
        //take our list of users, and sort the top three, checks for less than three, will return a sorted list
        if (userList.size() <= 3) {
            return userList.stream()
                    .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                    .toList();
        }
        return userList.stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(3)
                .toList();
    }

    public void displayLeaderBoard() {
        //take and calculate the top3 then displays them: name - score
        System.out.println("***LEADERBOARD***\n");
        System.out.println("*** NAME --- SCORE ***\n");
        List<User> topThreeUsers = calculateLeaderBoard();
        System.out.println("*** " + topThreeUsers.get(0).getName() + " --- " + topThreeUsers.get(0).getScore() + " ***\n");
        System.out.println("*** " + topThreeUsers.get(1).getName() + " --- " + topThreeUsers.get(1).getScore() + " ***\n");
        System.out.println("*** " + topThreeUsers.get(2).getName() + " ---  " + topThreeUsers.get(2).getScore() + "  ***\n");
    }
}
