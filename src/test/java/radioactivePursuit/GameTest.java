package radioactivePursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import radioactivePursuit.User.User;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.currentUser = new User();
        game.currentPlayer = new Player(game.currentUser.getName());
        game.currentPlanet = game.worldSetUp();

    }


    @Test
    void testCureTurn() {
        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", true);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertInstanceOf(CurerStrategy.class, strategy);

    }

    @Test
    void testMunchTurn() {
        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", true);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertInstanceOf(MuncherStrategy.class, strategy);

    }

    @Test
    void testInactionTurn() {
        game.menuOptions.put("Move Biomes",false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",true);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertInstanceOf(InactionStrategy.class, strategy);
    }

    @Test
    void testMoverTurn() {
        game.menuOptions.put("Move Biomes",true);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertInstanceOf(MoverStrategy.class, strategy);
    }

    @Test
    void testCollectingAntidoteTurn() {
        Game game = new Game();

        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", true);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertInstanceOf(CollectingAntidoteStrategy.class, strategy);

    }

    @Test
    void testGameEndsThroughDeath() {
        Game game = new Game();
        Player player = new Player("testGameOver");
        player.setHealth(0.0);
        game.currentPlayer = player;
        game.currentPlanet = game.worldSetUp();

        game.playGame();

        assertFalse(game.gameIsNotOver());
        assertTrue(game.playerDied);

        int score = game.calculateScore();
        assertEquals(score, 0);
    }

    @Disabled
    @Test
    void testGameEndsThroughCreatureCured() {

        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", true);
        game.menuOptions.put("See Map",false);

        while(game.gameIsNotOver()){
            game.playGame();
        }

        assertFalse(game.gameIsNotOver());
        assertTrue(game.creaturesResolved);

        int score = game.calculateScore();
        assertEquals(score, 3);
    }

    @Disabled
    @Test
    void testGameEndsThroughCreatureDeath() {

        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", true);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",false);

        while(game.gameIsNotOver()){
            game.playGame();
        }

        assertFalse(game.gameIsNotOver());
        assertTrue(game.creaturesResolved);

        int score = game.calculateScore();
        assertEquals(score, 1);

    }

}