package radioactivePursuit;

import java.util.Scanner;

import radioactivePursuit.User.Display;
import radioactivePursuit.User.User;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.PlayStrategy;
import radioactivePursuit.player.Player;

public class Game {
    //TODO instance of game
    static private final int MAX_BIOMES = 7;
    private static final CreatureFactory creatureFactory = new CreatureFactory();
    private static final ArtifactFactory artifactFactory = new ArtifactFactory();
    private static final Display display = new Display();

    public static void main(String[] args) {

        // TODO make instance variables
        User currentUser = userSetUp();
        Player currentPlayer = new Player(currentUser.getName());
        Planet currentPlanet = worldSetUp(currentPlayer);

        printIntro(currentPlayer);

        playGame(currentPlayer, currentPlanet);

        finalDisplay();
    }

    public static void playGame(Player currentPlayer, Planet currentPlanet) {
        while(gameIsNotOver(currentPlayer, currentPlanet)){
            display.turnDisplay(currentPlayer, currentPlanet);
            playTurn(currentPlayer);
        }
    }

    //method to instantiate user and grab their info
    private static User userSetUp(){
        User currentUser = new User();

        //ask the user their name and store that in current user
        System.out.println("Welcome to RadioActive Pursuit\n");
        System.out.println("Please enter your user name in the command line!\n");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        currentUser.setName(userName);

        System.out.println("Hi, " + currentUser.getName() + "\n");
        return currentUser;
    }

    static Planet worldSetUp(Player currentPlayer){
        BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

        return Planet.getNewBuilder(biomeFactory)
                .createBiomes(MAX_BIOMES)
                .connectCirclePlanet()
                .add(currentPlayer)
                .build();
    }


    static void getUserChoice(Player currentPlayer){
        display.instantiateMenuOptions(currentPlayer);
        display.displayOptionMenuAndSetUserChoice();
    }

    static void playTurn(Player currentPlayer){
        PlayStrategy newStrategy = display.getPlayerStrategy(currentPlayer);
        currentPlayer.setPlayStrategy(newStrategy);
        currentPlayer.doAction();
    }

    private static void printIntro(Player player){
        //fill in the background story here...
        System.out.println("Many decades ago, Earth fell silent. A chain of nuclear failures poisoned the land, twisted the wildlife, and forced humanity to flee. A handful of scientists escaped into orbit, watching their home decay from above while they struggled to survive.\n" +
                "\n" +
                "Generations passed, and now only fragmented transmissions reach the stations: life still clings to the surface, but it has changed. Animals once familiar have become dangerous, aggressive, and radioactive. If nothing is done, Earth will be lost forever.\n" +
                "\n" +
                "You, " + player.getName()+ ", are the first scientist brave — or desperate — enough to return.\n" +
                "\n" +
                "Armed with research, limited equipment, and a supply of experimental antidotes, you descend toward the ruined planet. Your mission is simple but merciless:\n" +
                "\n" +
                "• Cure what you can.\n" +
                "• Eliminate what you cannot.\n" +
                "\n" +
                "The antidotes you carry are few, and although rare supplies may still exist scattered across Earth, they will not be enough to save everything. Hard choices await. Some creatures can be restored… others must be destroyed before their mutations spread beyond control.\n" +
                "\n" +
                "Step outside your ship, " + player.getName() + ".\n" +
                "Earth is broken, but hope has landed with you.");

        System.out.println("Would you like to continue? (yes)\n");
    }

    private static void finalDisplay() {

    }

    static boolean gameIsNotOver(Player scientist, Planet currentPlanet) {
        if(!scientist.isAlive()){  //check that the player is alive
            return false;
        }else if(!currentPlanet.hasRadioActiveCreatures()){ //check that there are no living uncured creatures
            return false;
        }else{
            return true;
        }
    }
}
