package radioactivePursuit.User;

import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

import java.util.*;

public class Display {
    static Map<String, Boolean> menuOptions = new HashMap<>();

    public static void instantiateMenuOptions(Player currentPlayer) {
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

    public static void displayOptionMenuAndSetUserChoice() {
        Scanner sc = new Scanner(System.in);
        String userChoice;

        System.out.println("What would you like to do next?\n");
        for (String option : menuOptions.keySet()) {
            System.out.println("- " + option);
        }
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

    public static PlayStrategy getPlayerStrategy(Player player)
    {
        for (Map.Entry<String, Boolean> entry : menuOptions.entrySet()) {
            if (entry.getValue() == true) {
                String chosenOption = entry.getKey();
                if ("Move Biomes".equals(chosenOption)) {
                    return new MoverStrategy();

                } else if ("Eat Food".equals(chosenOption)) {
                    return new MuncherStrategy();

                } else if ("Fight Creature".equals(chosenOption)) {
                    return new FighterStrategy();

                } else if ("Collect Antidote".equals(chosenOption)) {
                    return new CollectingAntidoteStrategy();

                } else if ("Cure Creature".equals(chosenOption)) {
                   return new CurerStrategy();

                } else if ("See Map".equals(chosenOption)) {
                    DisplayMap();
                }
                break;
            }
        }
    }


    public static void DisplayMap(){
        //TODO, print a cute version of the map of biomes that includes where the player is right now, maybe what is in the biomes, TBD
    }

    public static void turnDisplay(Player player, Planet planet){
        //TODO: clean up this function fs
        player.displayScientist();
        player.getCurrentLocation().displayBiome();

        //TODO: find a way to display the creatures within the biome, where is that and artifacts
        List<Creature> creaturesInBiome = player.getCurrentLocation().getLivingCreatures();
        List<Artifact> artifactsInBiome = player.getCurrentLocation().getArtifacts();

        for(Creature creature: creaturesInBiome){
            creature.displayCreature();
        }
        for(Artifact artifact: artifactsInBiome){
            //TODO: clean this, change the function in Biome to just return names maybe in a list..?
            System.out.println(artifact.getName());
        }

    }

}
