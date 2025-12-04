package radioactivePursuit;

import java.util.Random;
import java.util.Scanner;

import radioactivePursuit.User.Display;
import radioactivePursuit.User.User;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.Player;

public class Main {
    static private final Random rand = new Random();
    static private final int MAX_BIOMES = 7;
    static private final int MAX_ = 5;
    private static final CreatureFactory creatureFactory = new CreatureFactory();
    private static final ArtifactFactory artifactFactory = new ArtifactFactory();
    private static final Display display = new Display();

    public static void main(String[] args) {

        //setting everything up for game play
        User currentUser = userSetUp();
        Player currentPlayer = new Player(currentUser.getName());
        Planet currentPlanet = worldSetUp(currentPlayer);

        openingDisplay(currentPlayer);
        //while loop to actually play the game
        while(gameIsNotOver(currentPlayer, currentPlanet)){
            Display.turnDisplay(currentPlayer, currentPlanet);
            playGame(currentPlayer.getCurrentLocation() ,currentPlayer);
        }

        Main.finalDisplay();
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

    private static Planet worldSetUp(Player currentPlayer){
        BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

        //TO-DO: change so that biome handles all generation of artifacts
        return Planet.getNewBuilder(biomeFactory)
                .createBiomes(MAX_BIOMES)
                .connectCirclePlanet()
                .add(currentPlayer)
                .build();
    }


    private static void playGame(Biome currentBiome, Player currentPlayer){

        Display.instantiateMenuOptions(currentBiome,currentPlayer); //set the menu options that are available
        Display.displayOptionMenu(); //show the player the menu and then collect what they say
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        Display.handleInpput(userChoice);
        Display.setPlayerStrategyBasedOnInput(currentPlayer);

    }

    private static void openingDisplay(Player player){
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
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
    }

    private static void finalDisplay() {

    }

    private static boolean gameIsNotOver(Player scientist, Planet currentPlanet) {
        if(!scientist.isAlive()){  //check that the player is alive
            return false;
        }else if(!currentPlanet.hasRadioActiveCreatures()){ //check that there are no living uncured creatures
            return false;
        }else{
            return true;
        }
    }
}
