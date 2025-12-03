package radioactivePursuit.creatures;

import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeType;

abstract public class Creature {
    private static Double health;
    private static int radioActiveLevel;
    private static String name;
    private static Biome currentBiome;
    private final BiomeType preferredBiome;

    Creature(String newName, Double newHealth, int newRadioActiveLevel, BiomeType preferredBiome) {
        this.name = newName;
        this.health = newHealth;
        this.radioActiveLevel = newRadioActiveLevel;
        this.preferredBiome = preferredBiome;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public boolean isCured() {
        return getRadioActiveLevel() <= 0;
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double newHealth) {
        health = newHealth;
    }

    public int getRadioActiveLevel() {
        return radioActiveLevel;
    }

    public BiomeType getPreferredBiomeType() {
        return this.preferredBiome;
    };


    public void decrementRadioActiveLevel() {
        if (radioActiveLevel != 0) {
            radioActiveLevel = this.getRadioActiveLevel() - 1;
        }
    }

    public void displayCreature(){
        //throw an error here this shuold not hit
    }
}
