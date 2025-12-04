package radioactivePursuit.interactives;

public class Food extends Artifact {

    // TO-DO fix healthValue of food to be int o rounded off
    protected final double healthValue;

    protected Food(String name, double healthValue) {
        super(name, ArtifactType.Food);
        this.healthValue = healthValue;

    }

    public double getHealthValue() {
        return healthValue;
    }

    @Override
    public String toString() {
        String formattedHealth = String.format("%.2f", healthValue);
        return name + "(" + formattedHealth + ")";
    }
}
