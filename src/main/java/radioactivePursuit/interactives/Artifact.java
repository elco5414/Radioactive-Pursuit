package radioactivePursuit.interactives;

abstract public class Artifact {

    public boolean isType(ArtifactType type) {
        return this.type == type;
    }
}
