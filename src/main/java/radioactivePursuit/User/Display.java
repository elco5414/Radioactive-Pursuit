package radioactivePursuit.User;

import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

import java.util.*;

public class Display {


    public Map<String, Boolean> instantiateMenuOptions(Player currentPlayer, Map<String, Boolean> menuOptions) {
        //TODO change to numbers and number input
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
        return menuOptions;
    }

    public void displayMenuOptions(Map<String, Boolean> menuOptions){
        System.out.println("What would you like to do next?\n");
        for (String option : menuOptions.keySet()) {
            System.out.println("- " + option);
        }
    }

    public static void displayMap(Planet currentPlanet){
        //TODO, print a cute version of the map of biomes that includes where the player is right now, maybe what is in the biomes, TBD
    }


    public static void turnDisplay(Player player){
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
