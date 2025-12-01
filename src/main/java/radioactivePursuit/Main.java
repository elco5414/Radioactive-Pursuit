package radioactivePursuit;

import java.util.Scanner;
import radioactivePursuit.User.User;

public class Main {

    //method to instantiate user and grab their info
    private void userSetUp(){
        User currentUser = new User();

        //ask the user their name and store that in current user
        System.out.println("Welcome to RadioActive Pursuit\n");
        System.out.println("Please enter your user name in the command line!\n");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        currentUser.setName(userName);
    }

    private void worldSetUp(){
        // set up the world
        // when instantiating player set the name to the user's name
        // will need to set the players antidote list, some random starting amount
    }

    // run the game
        //whenever the menu for options is displayed, set the player strategy based on that user input!

}
