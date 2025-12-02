package radioactivePursuit.interactives;

abstract public class Artifact {

    protected final String name;
    private final ArtifactType type;


    protected Artifact(String name, ArtifactType type) {
        this.name = name;
        this.type = type;
    }


    public boolean isType(ArtifactType type) {
        return this.type == type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
