package polymorphia.artifacts;

public interface IArtifact {
    String getName();

    double getHealthValue();

    double getDefenseValue();

    double getMovingCost();

    double getDamageValue();

    boolean isType(ArtifactType type);
}
