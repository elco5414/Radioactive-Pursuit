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
import java.util.Scanner;

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

        game.currentUser = game.getUserName();
        game.currentPlayer = new Player(game.currentUser.getName());
        game.currentPlanet = game.worldSetUp();

        game.printIntro();
        game.playGame();
        game.printOutro();
    }

    public void playGame() {
        while (gameIsNotOver()) {
            display.turnDisplay(this.currentPlayer);
            playTurn();
        }
    }

    private User getUserName() {
        User currentUser = new User();

        System.out.println("Welcome to RadioActive Pursuit\n");
        System.out.println("Please enter your user name in the command line!\n");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        currentUser.setName(userName);

        System.out.println("Hi, " + currentUser.getName() + "\n");
        return currentUser;
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
        CollectUserChoice();
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

    public void CollectUserChoice() {
       Scanner sc = new Scanner(System.in);
        String userChoice;

        while (true) {
            System.out.print("\nEnter your choice: ");
            userChoice = sc.nextLine().trim();

            if (menuOptions.containsKey(userChoice)) {
                menuOptions.put(userChoice, true);
                System.out.println("\nYou selected: " + userChoice);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private void printIntro() {
        System.out.println("Many decades ago, Earth fell silent. A chain of nuclear failures poisoned the land, twisted the wildlife, and forced humanity to flee. A handful of scientists escaped into orbit, watching their home decay from above while they struggled to survive.\n" +
                "\n" +
                "Generations passed, and now only fragmented transmissions reach the stations: life still clings to the surface, but it has changed. Animals once familiar have become dangerous, aggressive, and radioactive. If nothing is done, Earth will be lost forever.\n" +
                "\n" +
                "You, " + this.currentPlayer.getName() + ", are the first scientist brave — or desperate — enough to return.\n" +
                "\n" +
                "Armed with research, limited equipment, and a supply of experimental antidotes, you descend toward the ruined planet. Your mission is simple but merciless:\n" +
                "\n" +
                "• Cure what you can.\n" +
                "• Eliminate what you cannot.\n" +
                "\n" +
                "The antidotes you carry are few, and although rare supplies may still exist scattered across Earth, they will not be enough to save everything. Hard choices await. Some creatures can be restored… others must be destroyed before their mutations spread beyond control.\n" +
                "\n" +
                "Step outside your ship, " + this.currentPlayer.getName() + ".\n" +
                "Earth is broken, but hope has landed with you.");

    }

    private void printOutro() {

        this.currentUser.setScore(calculateScore());
        if (playerDied) {
            System.out.println("You Died. Humanity is no longer...\n");
        }
        if (creaturesResolved) {
            System.out.println("You saved Earth from apocalyptic overturn! CONGRATULATIONS!\n");
            System.out.println("Your score will be higher if you opted to cure more than kill.\n");
        }
        System.out.println("Your score is: " + this.currentUser.getScore() + "\n");
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
