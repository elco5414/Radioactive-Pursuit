
package radioactivePursuit.planet;


import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactType;
import radioactivePursuit.interactives.Creature;
import radioactivePursuit.player.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


abstract public class Biome {

    private final String name;
    static private final Random rand = new Random();

    private final List<Biome> neighbors = new ArrayList<>();
    private final List<Creature> creatures = new ArrayList<>();
    private final List<Artifact> artifacts = new ArrayList<>();
    private final BiomeType type;
    private boolean hasPlayer;
    Player player;


    protected Biome(String name, BiomeType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean hasLivingCreatures() {
        return !getLivingCreatures().isEmpty();
    }


    public boolean hasRadioActiveCreature() {
        return getLivingCreatures().stream()
                .anyMatch(Creature::isCured);
    }

    public List<Creature> getLivingCreatures() {
        return creatures.stream()
                .filter(Creature::isAlive)
                .toList();
    }

    //change to get Player if in room as singleton
    public List<String> getContents() {
        List<String> characterStrings = new ArrayList<>(getLivingCreatures().stream()
                .map(Object::toString)
                .toList());

        for (Artifact artifact : artifacts) {
            characterStrings.add(artifact.toString());
        }

        if (hasPlayer) {
            characterStrings.add(player.toString());
        }

        return characterStrings;
    }

    public void addNeighbor(Biome neighbor) {
        // Make sure we are never a neighbor of ourselves
        if (this != neighbor) {
            this.neighbors.add(neighbor);
            neighbor.addNeighbor(this);
        }
    }

    public Biome getRandomNeighbor() {
        if (neighbors.isEmpty()) {
            return null;
        }
        return neighbors.stream().toList().get(rand.nextInt(neighbors.size()));
    }


    // might have to change enter room functionality
    public void add(Creature creature) {
        creatures.add(creature);
        creature.enterRoom(this);
    }


    public void add(Artifact artifact) {
    }

    public void add(Player player) {
        this.hasPlayer = true;
        this.player = player;
    }

    public void remove(Creature creature) {
        creatures.remove(creature);
    }

    public void remove(Player player) {
        this.player = null;
        hasPlayer = false;
    }

    public void enter(Player player) {
        add(player);
    }


    public int numberOfNeighbors() {
        return neighbors.size();
    }

    public boolean hasNeighbors() {
        return !neighbors.isEmpty();
    }

    public boolean contains(Creature creature) {
        return creatures.contains(creature);
    }

    public boolean contains(Artifact artifact) {
        return artifacts.contains(artifact);
    }

    public boolean hasLivingPlayer() {
        return hasPlayer;
    }

    public boolean hasFood() {
        return artifacts.stream()
                .anyMatch(artifact -> artifact.isType(ArtifactType.Food));
    }

    public Optional<Artifact> getFood() {
        Optional<Artifact> food = artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Food))
                .findAny();
        food.ifPresent(artifacts::remove);
        return food;
    }

    public List<Artifact> getFoodItems() {
        return artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Food))
                .toList();
    }

    public boolean hasAntidote() {
        return artifacts.stream()
                .anyMatch(artifact -> artifact.isType(ArtifactType.Antidote));
    }

    public Optional<Artifact> getAnitdote() {
        Optional<Artifact> antidote = artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Antidote))
                .findAny();
        antidote.ifPresent(artifacts::remove);
        return antidote;
    }

    public List<Artifact> getAntidoteItems() {
        return artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Antidote))
                .toList();
    }

    public Optional<Creature> getCreature() {
        List<Creature> creatures = getLivingCreatures();
        if (creatures.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(creatures.get(rand.nextInt(creatures.size())));
    }

    public Optional<Artifact> getArtifact(ArtifactType artifactType) {
        return artifacts.stream()
                .filter(artifact -> artifact.isType(artifactType))
                .findAny();
    }


    public String toString() {
        String representation = "\t" + name + ":\n\t\t";
        representation += String.join("\n\t\t", getContents());
        representation += "\n";
        return representation;
    }
}