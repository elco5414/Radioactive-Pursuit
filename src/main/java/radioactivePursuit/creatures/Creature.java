package radioactivePursuit.creatures;

abstract public class Creature {
    private static Double health;
    private static int radioActiveLevel;
    private static String name;

    Creature(String newName, Double newHealth, int newRadioActiveLevel) {
        this.name = newName;
        this.health = newHealth;
        this.radioActiveLevel = newRadioActiveLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public boolean isCured() {
        System.out.println("is cured: " + (getRadioActiveLevel() <= 0));
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


    public void decrementRadioActiveLevel() {
        if (radioActiveLevel != 0) {
            radioActiveLevel = this.getRadioActiveLevel() - 1;
        }
    }

    public void displayCreature(){
        //throw an error here this shuold not hit
    }
}
