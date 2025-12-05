package radioactivePursuit.interactives;

public class Food extends Artifact {

    protected final double healthValue;

    protected Food(String name, double healthValue) {
        super(name, ArtifactType.Food);
        this.healthValue = Math.round(healthValue * 100.0) / 100.0;
    }


    public double getHealthValue() {
        return healthValue;
    }

}
