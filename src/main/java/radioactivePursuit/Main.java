package radioactivePursuit;

import java.util.Scanner;
import radioactivePursuit.User.User;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.Player;

public class Main {

    public static void main(String[] args) {

        //setting everything up for game play
        User currentUser = userSetUp();
        Planet currentPlanet = worldSetUp();
        Player currentPlayer = new Player(currentUser.getName());

        //add player to planet

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

    private static Planet worldSetUp(){
        // set up the world
        // when instantiating player set the name to the user's name
        // will need to set the players antidote list, some random starting amount
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
