package radioactivePursuit.player;

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
    private Double health;
    private List<Artifact> antidoteList = new ArrayList<>();
    private PlayStrategy playerStrat;
    private String name;
    static private final Random rand = new Random();
    static private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private Biome currentLocation;
    private Biome previousLocation;
    private Biome secondPreviousLocation;

    private static final Double DEFAULT_STARTING_HEALTH = 10.0;


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
        List<Artifact> antidotes = new ArrayList<>();
        int amount =  rand.nextInt(4)+1;
        antidotes.addAll(artifactFactory.createAntidotes(amount));
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

    public PlayStrategy getPlayStrategy() {
        return playerStrat;
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
        if (currentBiome != null) {
            currentBiome.remove(this);
        }
    }

    public void fight(Creature creature) {
        double creatureHealth = creature.getHealth();
        if (creatureHealth == health) {
            creature.setHealth(creatureHealth - 1);
            setHealth(health - 1);
        } else if (creatureHealth >= health) {
            double differenceInHealth = creatureHealth - health;
            setHealth(health - differenceInHealth);
        } else {
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
        assert getCurrentLocation().hasNeighbors();
        secondPreviousLocation = previousLocation;
        previousLocation = currentLocation;
        Biome newBiome = currentLocation.getNextNeighbor(previousLocation, secondPreviousLocation);
        newBiome.add(this);
        setCurrentLocation(newBiome);
    }

    public boolean canCure(Creature creature) {
        boolean curedCreature = creature.isCured();
        boolean livingCreature = creature.isAlive();
        return !curedCreature && livingCreature && this.getAntidoteCount() > 0;
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
        if(getAntidoteCount() != 0){
            Artifact lastAntidote = this.antidoteList.getLast();
            this.antidoteList.remove(lastAntidote);
        }
        else{
            System.out.println("No antidotes to use.");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}

