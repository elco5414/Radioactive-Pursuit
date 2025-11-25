package radioactivePursuit.interactives;

import radioactivePursuit.player.PlayStrategy;

import java.util.List;

public class Creature {
    private static Double health;
    private static int radioActiveLevel;

    private static final Double DEFAULT_STARTING_HEALTH = 8.0;

    public boolean isCured(){
        return getRadioActiveLevel() < 0;
    }

    public boolean isAlive(){
        return getHealth() > 0;
    }

    public double getHealth(){
        return health;
    }

    public double setHealth(double newHealth){
        health = newHealth;
    }

    public int getRadioActiveLevel(){
        return radioActiveLevel;
    }

    public void decrementRadioActiveLevel(){
        if(radioActiveLevel != 0){
            radioActiveLevel = this.getRadioActiveLevel() - 1;
        }
    }
}
