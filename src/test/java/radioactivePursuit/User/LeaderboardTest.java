package radioactivePursuit.User;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeaderboardTest {
    Leaderboard leaderboardInstance = Leaderboard.getInstance();

    @Test
    void testLeaderBoardAddUser(){
        User fakeUser1 = new User();
        fakeUser1.setName("Bobby");
        fakeUser1.setScore(200);

        User fakeUser2 = new User();
        fakeUser2.setName("Jenna");
        fakeUser2.setScore(300);

        User fakeUser3 = new User();
        fakeUser3.setName("Alex");
        fakeUser3.setScore(400);

        leaderboardInstance.addUserToLeaderboard(fakeUser1);
        leaderboardInstance.addUserToLeaderboard(fakeUser2);
        leaderboardInstance.addUserToLeaderboard(fakeUser3);

        assertTrue(leaderboardInstance.userList.contains(fakeUser1));
        assertTrue(leaderboardInstance.userList.contains(fakeUser2));
        assertTrue(leaderboardInstance.userList.contains(fakeUser3));
    }
    @Test
    void testCalculateLeaderBoard(){
        List<User> topThreeUsers = leaderboardInstance.calculateLeaderBoard();
        assertTrue(topThreeUsers.get(0).getScore() > topThreeUsers.get(1).getScore());
        assertTrue(topThreeUsers.get(1).getScore() > topThreeUsers.get(2).getScore());
    }
}
