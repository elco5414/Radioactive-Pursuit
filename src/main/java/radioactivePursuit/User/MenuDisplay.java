package radioactivePursuit.User;

import radioactivePursuit.planet.Biome;
import radioactivePursuit.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuDisplay {
    Map<String, Boolean> menuOptions = new HashMap<>();

    private void instantiateMenuOptions(Biome currentBiome, Player currentPlayer) {
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
        if (currentPlayer.canCure(currentBiome.getCreature())) {
            menuOptions.put("Cure Creature", false);
        }
    }

    public void displayOptionMenu() {
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

}
