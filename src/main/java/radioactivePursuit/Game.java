package radioactivePursuit;

import radioactivePursuit.User.Display;
import radioactivePursuit.User.Leaderboard;
import radioactivePursuit.User.User;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final int MAX_BIOMES = 7;
    private final CreatureFactory creatureFactory = new CreatureFactory();
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final Display display = new Display();
    Boolean playerDied = false;
    Boolean creaturesResolved = false;

    User currentUser;
    Player currentPlayer;
    Planet currentPlanet;
    Leaderboard leaderboard = Leaderboard.getInstance();
    Map<String, Boolean> menuOptions = new HashMap<>();

    public static void main(String[] args) {

        Game game = new Game();

        game.currentUser = game.display.promptUserCreation();
        game.currentPlayer = new Player(game.currentUser.getName());
        game.currentPlanet = game.worldSetUp();

        game.display.printIntro(game.currentPlayer);
        game.playGame();
        game.determineUserEnding();
    }

    public void playGame() {
        while (gameIsNotOver()) {
            display.turnDisplay(this.currentPlayer);
            playTurn();
        }
    }

    Planet worldSetUp() {
        BiomeFactory biomeFactory = new BiomeFactory(this.artifactFactory, this.creatureFactory);

        return Planet.getNewBuilder(biomeFactory)
                .createBiomes(MAX_BIOMES)
                .connectCirclePlanet()
                .add(this.currentPlayer)
                .build();
    }

    void showPlayerOptions() {
        this.menuOptions = display.instantiateMenuOptions(this.currentPlayer, this.menuOptions);
        this.display.displayMenuOptions(this.menuOptions);
    }

    void playTurn() {
        showPlayerOptions();
        display.collectUserChoice(this.menuOptions);
        PlayStrategy newStrategy = getPlayerStrategy();
        this.currentPlayer.setPlayStrategy(newStrategy);
        this.currentPlayer.doAction();
    }

    public PlayStrategy getPlayerStrategy() {
        for (Map.Entry<String, Boolean> entry : menuOptions.entrySet()) {
            if (entry.getValue()) {
                return switch (entry.getKey()) {
                    case "Move Biomes" -> new MoverStrategy();
                    case "Eat Food" -> new MuncherStrategy();
                    case "Fight Creature" -> new FighterStrategy();
                    case "Collect Antidote" -> new CollectingAntidoteStrategy();
                    case "Cure Creature" -> new CurerStrategy();
                    case "See Map" -> {
                        display.displayMap(this.currentPlanet, this.currentPlayer);
                        yield new InactionStrategy();
                    }
                    default -> new InactionStrategy();
                };
            }
        }
        return new InactionStrategy();
    }


    private void determineUserEnding() {
        this.currentUser.setScore(calculateScore());
        if (playerDied) {
            display.showBadEnding();
        }
        if (creaturesResolved) {
            display.showGoodEnding();
        }
        display.showScore(this.currentUser);
        leaderboard.addUserToLeaderboard(currentUser);
        leaderboard.displayLeaderBoard();
    }

    int calculateScore() {
        int score = 0;
        if (playerDied) {
            return score;
        }
        if (creaturesResolved) {
            int curedCreatureCount = 0;
            int killedCreatureCount = 0;

            List<Creature> creatures = this.currentPlanet.getCreatures();

            for (Creature creature : creatures) {
                if (!creature.isAlive()) {
                    killedCreatureCount++;
                } else if (creature.isCured()) {
                    curedCreatureCount = curedCreatureCount + 3;
                }
            }
            score = killedCreatureCount + curedCreatureCount;
        }
        return score;
    }

    boolean gameIsNotOver() {
        if (!this.currentPlayer.isAlive()) {
            playerDied = true;
            return false;
        } else if (!this.currentPlanet.hasRadioActiveCreatures()) {
            creaturesResolved = true;
            return false;
        } else {
            return true;
        }
    }
}
