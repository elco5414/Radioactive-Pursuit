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

        //TODO change to numbers and number input
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
        System.out.println("Many decades ago, Earth fell silent. A chain of nuclear failures poisoned the land, twisted the wildlife, and forced humanity to flee. A handful of scientists escaped into orbit, watching their home decay from above while they struggled to survive.\n" +
                "\n" +
                "Generations passed, and now only fragmented transmissions reach the stations: life still clings to the surface, but it has changed. Animals once familiar have become dangerous, aggressive, and radioactive. If nothing is done, Earth will be lost forever.\n" +
                "\n" +
                "You, " + currentPlayer.getName() + ", are the first scientist brave — or desperate — enough to return.\n" +
                "\n" +
                "Armed with research, limited equipment, and a supply of experimental antidotes, you descend toward the ruined planet. Your mission is simple but merciless:\n" +
                "\n" +
                "• Cure what you can.\n" +
                "• Eliminate what you cannot.\n" +
                "\n" +
                "The antidotes you carry are few, and although rare supplies may still exist scattered across Earth, they will not be enough to save everything. Hard choices await. Some creatures can be restored… others must be destroyed before their mutations spread beyond control.\n" +
                "\n" +
                "Step outside your ship, " + currentPlayer.getName() + ".\n" +
                "Earth is broken, but hope has landed with you.");

    }

    public void displayMenuOptions(Map<String, Boolean> menuOptions){
        System.out.println("What would you like to do next?\n");
        for (String option : menuOptions.keySet()) {
            System.out.println("- " + option);
        }
    }

    public void displayMap(Planet currentPlanet, Player currentPlayer){
        List<Biome> biomes = currentPlanet.getBiomes();

        System.out.println("\n╔═══════════════════════════════ PLANET MAP ═══════════════════════════════╗");
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
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝\n");
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
        System.out.println("------CURRENT TURN-------\n");
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
