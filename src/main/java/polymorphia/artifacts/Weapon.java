package polymorphia.artifacts;

public class Weapon extends Artifact{
    public static double DEFAULT_HEALTH_VALUE = 0.0;
    public static double DEFAULT_MOVING_COST = 0.0;
    public static double DEFAULT_STRENGTH = 0.0;

    public Weapon(String name, double damageValue) {
        super(ArtifactType.Weapon, name, DEFAULT_HEALTH_VALUE, DEFAULT_STRENGTH, DEFAULT_MOVING_COST, damageValue);
    }

    @Override
    public String toString() {
        String formattedStrength = String.format("%.2f", getDamageValue());
        return getName() + " weapon(strength:" + formattedStrength + ", damageValue:" + getDamageValue() + ")";
    }
}
