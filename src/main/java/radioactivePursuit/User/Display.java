package radioactivePursuit.User;

import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

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

    public static void setPlayerStrategyBasedOnInput(Player player)
    {
        for (Map.Entry<String, Boolean> entry : menuOptions.entrySet()) {
            if (entry.getValue() == true) {
                String chosenOption = entry.getKey();
                if ("Move Biomes".equals(chosenOption)) {
                    player.setPlayStrategy(new MoverStrategy());
                    player.doAction();

                } else if ("Eat Food".equals(chosenOption)) {
                    player.setPlayStrategy(new MuncherStrategy());
                    player.doAction();

                } else if ("Fight Creature".equals(chosenOption)) {
                    player.setPlayStrategy(new FighterStrategy());
                    player.doAction();

                } else if ("Collect Antidote".equals(chosenOption)) {
                    player.setPlayStrategy(new CollectingAntidoteStrategy());
                    player.doAction();

                } else if ("Cure Creature".equals(chosenOption)) {
                    player.setPlayStrategy(new CurerStrategy());
                    player.doAction();

                } else if ("See Map".equals(chosenOption)) {
                    DisplayMap();
                }

                break;
            }
        }
    }

    public static void handleInpput(String userChoice)
    {
        System.out.println("input handled\n");
        //set whatever the user entered as true on the map, and execute that
        String cleanedChoice = userChoice.trim();

        if (menuOptions.containsKey(cleanedChoice)) {
            menuOptions.put(cleanedChoice, true);

            System.out.println("Option '" + cleanedChoice + "' selected and executed\n.");
        } else {
            System.out.println("Input '" + cleanedChoice + "' is not a valid menu option.\n");
        }
    }

    public static void DisplayMap(){
        //TO-DO, print a cute version of the map of biomes that includes where the player is right now, maybe what is in the biomes, TBD
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
