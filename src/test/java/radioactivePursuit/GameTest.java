package radioactivePursuit;

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

    private final InputStream originalSystemIn = System.in;

    @Test
    void testFightTurn() {

    }

    @Test
    void testCureTurn() {
        Game game = new Game();

        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", true);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertTrue(strategy instanceof CurerStrategy);

    }

    @Test
    void testMunchTurn() {
        Game game = new Game();

        game.menuOptions.put("Move Biomes", false);
        game.menuOptions.put("Eat Food", true);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",false);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertTrue(strategy instanceof MuncherStrategy);

    }

    @Test
    void testInactionTurn() {
        Game game = new Game();

        game.menuOptions.put("Move Biomes",false);
        game.menuOptions.put("Eat Food", false);
        game.menuOptions.put("Fight Creature", false);
        game.menuOptions.put("Collect Antidote", false);
        game.menuOptions.put("Cure Creature", false);
        game.menuOptions.put("See Map",true);

        PlayStrategy strategy = game.getPlayerStrategy();

        assertTrue(strategy instanceof InactionStrategy);
    }

    @Test
    void testMoverTurn() {
        Game game = new Game();
        Player player = new Player("TestMovePlayer");
        game.currentPlayer = player;
        game.currentPlanet = game.worldSetUp();
        Biome playersFirstRoom = player.getCurrentLocation();

        game.showPlayerOptions();
        game.setUserChoice("Move Biomes");

        game.playTurn();
        assertTrue(player.getCurrentLocation() != playersFirstRoom);
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

        assertTrue(strategy instanceof CollectingAntidoteStrategy);

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

    @Test
    void testGameEndsThroughCreatureCured() {
        Game game = new Game();
        ArtifactFactory artifactFactory = new ArtifactFactory();
        CreatureFactory creatureFactory = new CreatureFactory();
        BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

        Player player = new Player("TestGameOver");
        player.setHealth(0.0);
        game.currentPlayer = player;
        game.currentPlanet =
                Planet.getNewBuilder(biomeFactory)
                .createBiomes(3)
                .connectCirclePlanet()
                .add(game.currentPlayer)
                .build();

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

    @Test
    void testGameEndsThroughCreatureDeath() {
        Game game = new Game();
        ArtifactFactory artifactFactory = new ArtifactFactory();
        CreatureFactory creatureFactory = new CreatureFactory();
        BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

        Player player = new Player("TestGameOver");
        player.setHealth(0.0);
        game.currentPlayer = player;
        game.currentPlanet =
                Planet.getNewBuilder(biomeFactory)
                        .createBiomes(3)
                        .connectCirclePlanet()
                        .add(player)
                        .build();

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

    @Test
    void testPlayTurn(){

    }

    @Test
    void showPlayerOptions(){

    }

    @Test
    void playGame(){

    }

}