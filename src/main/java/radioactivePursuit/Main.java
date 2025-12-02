package radioactivePursuit;

import java.util.Random;
import java.util.Scanner;
import radioactivePursuit.User.User;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.Player;

public class Main {
    static private final Random rand = new Random();
    static private final int MAX_BIOMES = 5;
    static private final int MAX_ = 5;

    public static void main(String[] args) {

        //setting everything up for game play
        User currentUser = userSetUp();
        Player currentPlayer = new Player(currentUser.getName());
        Planet currentPlanet = worldSetUp(currentPlayer);

        //while loop to actually play the game
        while(gameIsNotOver(currentPlayer, currentPlanet)){

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
        BiomeFactory biomeFactory = new BiomeFactory();

        int numberOfBiomes = rand.nextInt(MAX_BIOMES);

        // set up the world
        // when instantiating player set the name to the user's name
        // will need to set the players antidote list, some random starting amount

        //change so that biome handles all generation of creatures and artifacts
        return Planet.getNewBuilder(biomeFactory)
                .createBiomes(numberOfBiomes)
                .connectCirclePlanet()
                .build();
    }

    // run the game
    private void playGame(){
        //game play for each turn
        //whenever the menu for options is displayed, set the player strategy based on that user input!

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
