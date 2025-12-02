package radioactivePursuit.player;

import radioactivePursuit.interactives.Antidote;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.Food;
import radioactivePursuit.planet.Biome;

import java.util.List;
import java.util.Optional;

public class Player {
    private static Double health;
    private static List<Artifact> antidoteList;
    private static PlayStrategy playerStrat;
    private static String name;

    private static final Double DEFAULT_STARTING_HEALTH = 15.0;

    private Biome currentLocation;

    public Player(PlayStrategy playerStrategy) {
        this.health = DEFAULT_STARTING_HEALTH;
        this.playerStrat = playerStrategy;
    }

    public Player(String newName){
        this.name = newName;
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
        currentLocation = biome;
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
        return this.getCurrentLocation().hasRadioActiveCreature();
    }

    public boolean canMove() {
        return currentLocation.hasNeighbors();
    }

    public void move() {
        //TO-DO: update to the next biome
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
        antidoteList.removeLast();
    }

    private void displayScientistArt() {
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
}

