package radioactivePursuit.User;

import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.Player;

import java.util.*;

public class Display {
    static Map<String, Boolean> menuOptions = new HashMap<>();

    public static void instantiateMenuOptions(Biome currentBiome, Player currentPlayer) {
        if (currentPlayer.canMove()) {
            menuOptions.put("Move Biomes", false);
        }
        if (currentPlayer.canEat()) {
            menuOptions.put("Eat Food", false);
        }
        if (currentPlayer.canFight()) {
            menuOptions.put("Fight Creature", false);
        }
        if (currentPlayer.canCollectAntidote()) {
            menuOptions.put("Collect Antidote", false);
        }
        if (currentPlayer.canCure(currentPlayer.getCurrentLocation().getCreature())) {
            menuOptions.put("Cure Creature", false);
        }
        menuOptions.put("See Map",false);
    }

    public static void displayOptionMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What would you like to do next?\n");
        for (String option : menuOptions.keySet()) {
            System.out.println("- " + option);
        }
        while (true) {
            System.out.print("\nEnter your choice: ");
            String userChoice = sc.nextLine().trim();

            if (menuOptions.containsKey(userChoice)) {
                menuOptions.put(userChoice, true);
                System.out.println("\nYou selected: " + userChoice);
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

    }

    public void DisplayMap(){

    }

    public static void turnDisplay(Player player, Planet planet){
        //TO-DO: clean up this function fs
        //not currently in final printing stage, just to get info out right now
        player.displayScientist();
        player.getCurrentLocation().displayBiome();

        //TO-DO: find a way to display the creatures within the biome, where is that
        // and antidotes and foods as well
        List<Creature> creaturesInBiome = player.getCurrentLocation().getLivingCreatures();
        List<Artifact> artifactsInBiome = player.getCurrentLocation().getArtifacts();

        for(Creature creature: creaturesInBiome){
            creature.displayCreature();
        }
        for(Artifact artifact: artifactsInBiome){
            //TO-DO: clean this, change the function in Biome to just return names maybe in a list..?
            System.out.println(artifact.getName());
        }

    }

}
