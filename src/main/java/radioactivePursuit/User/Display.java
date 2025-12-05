package radioactivePursuit.User;

import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.Planet;
import radioactivePursuit.player.*;

import java.util.*;

public class Display {

    public Map<String, Boolean> instantiateMenuOptions(Player currentPlayer, Map<String, Boolean> menuOptions) {
        Map<String, Boolean> newMenuOptions = resetMenuOptions(menuOptions);

        if (currentPlayer.canMove()) {
            newMenuOptions.put("Move Biomes", false);
        }
        if (currentPlayer.canEat()) {
            newMenuOptions.put("Eat Food", false);
        }
        if (currentPlayer.canFight()) {
            newMenuOptions.put("Fight Creature", false);
        }
        if (currentPlayer.canCollectAntidote()) {
            newMenuOptions.put("Collect Antidote", false);
        }
        if (currentPlayer.canCure(currentPlayer.getCurrentLocation().getCreature())) {
            newMenuOptions.put("Cure Creature", false);
        }
        newMenuOptions.put("See Map",false);
        return newMenuOptions;
    }

    private Map<String, Boolean> resetMenuOptions(Map<String, Boolean> menuOptions) {
        menuOptions.remove("Move Biomes");
        menuOptions.remove("Eat Food");
        menuOptions.remove("Fight Creature");
        menuOptions.remove("Collect Antidote");
        menuOptions.remove("Cure Creature");

        return menuOptions;
    }

    public void printIntro(Player currentPlayer) {
        String border = "═".repeat(80);
        String thinBorder = "─".repeat(80);

        System.out.println("\n" + border);
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        E A R T H :   R E C L A M A T I O N                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(border + "\n");

        printWithDelay("  Many decades ago, Earth fell silent. A chain of nuclear failures poisoned");
        printWithDelay("  the land, twisted the wildlife, and forced humanity to flee. A handful of");
        printWithDelay("  scientists escaped into orbit, watching their home decay from above while");
        printWithDelay("  they struggled to survive.\n");

        printWithDelay("  Generations passed, and now only fragmented transmissions reach the stations:");
        printWithDelay("  life still clings to the surface, but it has changed. Animals once familiar");
        printWithDelay("  have become dangerous, aggressive, and radioactive. If nothing is done,");
        printWithDelay("  Earth will be lost forever.\n");

        System.out.println(thinBorder);
        System.out.println("\n  >> MISSION BRIEFING: SCIENTIST " + currentPlayer.getName().toUpperCase() + " <<\n");
        System.out.println(thinBorder + "\n");

        printWithDelay("  You, " + currentPlayer.getName() + ", are the first scientist brave — or desperate —");
        printWithDelay("  enough to return.\n");

        printWithDelay("  Armed with research, limited equipment, and a supply of experimental antidotes,");
        printWithDelay("  you descend toward the ruined planet. Your mission is simple but merciless:\n");

        System.out.println("  ┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("  │  ✓  Cure what you can.                                                  │");
        System.out.println("  │  ✗  Eliminate what you cannot.                                          │");
        System.out.println("  └─────────────────────────────────────────────────────────────────────────┘\n");

        printWithDelay("  The antidotes you carry are few, and although rare supplies may still exist");
        printWithDelay("  scattered across Earth, they will not be enough to save everything. Hard");
        printWithDelay("  choices await. Some creatures can be restored… others must be destroyed");
        printWithDelay("  before their mutations spread beyond control.\n");

        System.out.println(thinBorder);
        System.out.println("\n  >> Step outside your ship, " + currentPlayer.getName() + ".");
        System.out.println("  >> Earth is broken, but hope has landed with you.\n");
        System.out.println(border + "\n");
    }

    private void printWithDelay(String text) {
        System.out.println(text);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayMenuOptions(Map<String, Boolean> menuOptions){
        System.out.println("What would you like to do next?\n");
        for (String option : menuOptions.keySet()) {
            System.out.println("- " + option);
        }
    }

    public void displayMap(Planet currentPlanet, Player currentPlayer){
        List<Biome> biomes = currentPlanet.getBiomes();

        System.out.println("\n╔═════════════════════════════════════════════════════════════ PLANET MAP ═════════════════════════════════════════════════════╗");
        System.out.print("║ ");
        for(int i = 0; i < biomes.size(); i++){
            System.out.print("┌──────────────┐");
            if(i < biomes.size() - 1) System.out.print("──");
        }
        System.out.println(" ║");

        System.out.print("║ ");
        for(int i = 0; i < biomes.size(); i++){
            Biome biome = biomes.get(i);
            String name = biome.getName();
            if(name.length() > 12) name = name.substring(0, 12);
            System.out.printf("│ %-12s │", name);
            if(i < biomes.size() - 1) System.out.print("  ");
        }
        System.out.println(" ║");

        System.out.print("║ ");
        for(int i = 0; i < biomes.size(); i++){
            Biome biome = biomes.get(i);
            String artifact = "    ----    ";
            if(biome.getArtifacts() != null && !biome.getArtifacts().isEmpty()){
                String artName = biome.getArtifacts().get(0).getName();
                if(artName.length() > 12) artName = artName.substring(0, 12);
                artifact = String.format("%-12s", artName);
            }
            System.out.printf("│ %s │", artifact);
            if(i < biomes.size() - 1) System.out.print("  ");
        }
        System.out.println(" ║");
        System.out.print("║ ");
        for(int i = 0; i < biomes.size(); i++){
            Biome biome = biomes.get(i);
            if(biome == currentPlayer.getCurrentLocation()){
                System.out.print("│  👩‍🔬 YOU   │");
            } else {
                System.out.print("│              │");
            }
            if(i < biomes.size() - 1) System.out.print("  ");
        }
        System.out.println(" ║");

        System.out.print("║ ");
        for(int i = 0; i < biomes.size(); i++){
            System.out.print("└──────────────┘");
            if(i < biomes.size() - 1) System.out.print("──");
        }
        System.out.println(" ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");
    }

    public void collectUserChoice(Map<String, Boolean> menuOptions) {
        Scanner sc = new Scanner(System.in);
        String userChoice;

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

    public void turnDisplay(Player player){
            Biome currentBiome = player.getCurrentLocation();
            List<Creature> creatures = currentBiome.getLivingCreatures();
            List<Artifact> artifacts = currentBiome.getArtifacts();

            System.out.println("\n╔═══════════════════════════════════ CURRENT TURN ════════════════════════════════════════╗");
            System.out.println("║                                                                                          ║");

            System.out.print("║  👩‍🔬 " + player.getName() + ": " + player.getAntidoteCount() + "🧪, " + player.getHealth() + "❤️");
            System.out.print("  |  ");
            currentBiome.displayBiome();
            System.out.println("                                    ║");

            System.out.println("║  ════════════════════════════════════════════════════════════════════════════════════  ║");
            System.out.println("║                                                                                          ║");

            if(!creatures.isEmpty()){
                System.out.println("║  🦎 CREATURES IN BIOME:                                                                  ║");
                for(Creature creature : creatures){
                    System.out.print("║     ");
                    creature.displayCreature();
                }
            } else {
                System.out.println("║  🦎 CREATURES: None                                                                      ║");
            }
            System.out.println("║                                                                                          ║");
            if(!artifacts.isEmpty()){
                System.out.println("║  🏺 ARTIFACTS IN BIOME:                                                                  ║");
                for(Artifact artifact : artifacts){
                    System.out.println("║     • " + artifact.getName() + "                                                         ║");
                }
            } else {
                System.out.println("║  🏺 ARTIFACTS: None                                                                      ║");
            }
            System.out.println("║                                                                                          ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════╝\n");
    }

    public void showBadEnding() {
        System.out.println("You Died. Humanity is no longer...\n");
    }

    public void showGoodEnding() {
        System.out.println("You saved Earth from apocalyptic overturn! CONGRATULATIONS!\n");
        System.out.println("Your score will be higher if you opted to cure more than kill.\n");
    }

    public void showScore(User currentUser) {
        System.out.println("Your score is: " + currentUser.getScore() + "\n");
    }

    public User promptUserCreation() {
        User currentUser = new User();

        System.out.println("Welcome to RadioActive Pursuit\n");
        System.out.println("Please enter your user name in the command line!\n");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        currentUser.setName(userName);

        System.out.println("Hi, " + currentUser.getName() + "\n");
        return currentUser;
    }
}
