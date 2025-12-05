package radioactivePursuit;

import org.junit.jupiter.api.Test;
import radioactivePursuit.User.User;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void testFightTurn() {
        Game game = new Game();
        game.currentPlayer = new Player("testFightTurnPlayer");
        game.currentPlanet = game.worldSetUp();

        //i do not know how to go about game play here genuinely

    }

    @Test
    void testCureTurn() {


    }

    @Test
    void testMunchTurn() {


    }

    @Test
    void testInactionTurn() {


    }

    @Test
    void testMoverTurn() {


    }

    @Test
    void testCollectingAntidoteTurn() {


    }

    @Test
    void testGameEndsThroughDeath() {


    }

    @Test
    void testGameEndsThroughCreatureCured() {


    }

    @Test
    void testGameEndsThroughCreatureDeath() {


    }

    @Test
    void testSetUserChoice(){

    }

    @Test
    void getPlayerStrategy(){

    }

    @Test
    void testPlayTurn(){

    }

    @Test
    void showPlayerOptions(){

    }

    @Test
    void playGame(){

    }

    @Test
    void testCalculateScore(){}

}