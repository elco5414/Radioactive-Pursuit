package radioactivePursuit.player;

import radioactivePursuit.interactives.Antidote;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.interactives.Food;
import radioactivePursuit.planet.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Player {
    private static Double health;
    private List<Artifact> antidoteList = new ArrayList<>();
    private static PlayStrategy playerStrat;
    private static String name;
    static private final Random rand = new Random();
    static private final ArtifactFactory artifactFactory = new ArtifactFactory();

    private static final Double DEFAULT_STARTING_HEALTH = 15.0;

    private Biome currentLocation;

    public Player(PlayStrategy playerStrategy) {
        this.health = DEFAULT_STARTING_HEALTH;
        this.playerStrat = playerStrategy;
        this.antidoteList = startingAntidotes();
    }

    public Player(String newName){
        this.name = newName;
        this.health = DEFAULT_STARTING_HEALTH;
        this.antidoteList = startingAntidotes();
    }

    private List<Artifact> startingAntidotes() {
        int amount =  rand.nextInt(4);
        List<Artifact> antidotes = artifactFactory.createAntidotes(amount);
        return antidotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Integer getAntidoteCount() {
        if (antidoteList == null) {
            return 0;
        } else {
            return antidoteList.size();
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(Double newHealth) {
        health = newHealth;
    }

    public void setPlayStrategy(PlayStrategy playStrategy) {
        playerStrat = playStrategy;
    }

    public void doAction() {
        playerStrat.doAction(this);
    }

    public void eat(Food food) {
        double healthWithFood = this.getHealth() + food.getHealthValue();
        this.setHealth(healthWithFood);
    }

    public boolean canEat() {
        return this.getCurrentLocation().hasFood();
    }

    public Biome getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Biome biome) {
        Biome currentBiome = getCurrentLocation();
        currentLocation = biome;
        currentLocation.enterBiome(this);
        if (currentBiome != null) {
            currentBiome.remove(this);
        }
    }

    public void fight(Creature creature) {
        double creatureHealth = creature.getHealth();
        if (creatureHealth == health) {
            System.out.println("Equal health");
            creature.setHealth(creatureHealth - 1);
            setHealth(health - 1);
        } else if (creatureHealth >= health) {
            System.out.println("creature grreater");
            double differenceInHealth = creatureHealth - health;
            setHealth(health - differenceInHealth);
        } else {
            System.out.println("you greater");
            double differenceInHealth = health - creatureHealth;
            creature.setHealth(creatureHealth - differenceInHealth);
        }
    }

    public boolean canFight() {
        return this.getCurrentLocation().hasRadioActiveCreatures();
    }

    public boolean canMove() {
        return currentLocation.hasNeighbors();
    }

    public void move() {
        //TODO: update to the next biome
    }

    public boolean canCure(Creature creature) {
        boolean curedCreature = creature.isCured();
        boolean livingCreature = creature.isAlive();
        if (curedCreature == false && livingCreature == true) {
            return true;
        }
        return false;
    }

    public void cure(Creature creature) {
        useAntidote();
        creature.decrementRadioActiveLevel();
    }

    public boolean canCollectAntidote() {
        return currentLocation.hasAntidote();
    }

    public void collectAntidote() {
        Optional<Artifact> potentialAntidote = currentLocation.getAntidote();
        Artifact antidote = potentialAntidote.get();
        antidoteList.add(antidote);
    }

    public void useAntidote() {
        Artifact lastAntidote = antidoteList.getLast();
        //TODO: fix this
        antidoteList.remove(lastAntidote);
    }

    public void displayScientistArt() {
        //fill in with the scientist ASCII character here
        System.out.println(
                "    (ᵔ‿ᵔ)✨\n" +
                "    /|️🔬|\\\n" +
                " 💉/ |  | \\\n" +
                "    /|  |\\\n" +
                "   /_|__|_\\\n" +
                "     /  \\\n"
        );
    }

    public void displayScientist() {
        System.out.println(getName() + ": " + getAntidoteCount() + "🧪, " + getHealth() + "❤️.\n");
        displayScientistArt();
    }

    public boolean isAlive() {
        return health > 0;
    }
}

