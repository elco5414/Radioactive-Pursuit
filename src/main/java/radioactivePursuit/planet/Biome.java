package radioactivePursuit.planet;

import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactType;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.interactives.Food;
import radioactivePursuit.player.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


abstract public class Biome {

    static private final Random rand = new Random();
    private final String name;
    private final List<Biome> neighbors = new ArrayList<>();
    private final List<Creature> creatures = new ArrayList<>();
    private final List<Artifact> artifacts = new ArrayList<>();
    private final BiomeType type;
    Player player;
    private boolean hasPlayer;


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


    public boolean hasRadioActiveCreatures() {
        return getLivingCreatures().stream()
                .anyMatch(creature -> !creature.isCured());
    }

    public List<Creature> getLivingCreatures() {
        return creatures.stream()
                .filter(Creature::isAlive)
                .toList();
    }

    public List<Artifact> getArtifacts() {
        return artifacts.stream()
                .toList();
    }

    public void addNeighbor(Biome neighbor) {

        if (this == neighbor) return;
        if (!this.neighbors.contains(neighbor)) {
            this.neighbors.add(neighbor);
        }
        if (!neighbor.neighbors.contains(this)) {
            neighbor.addNeighbor(this);
        }
    }

    public Biome getRandomNeighbor() {
        if (neighbors.isEmpty()) {
            return null;
        }
        return neighbors.stream().toList().get(rand.nextInt(neighbors.size()));
    }

    public void add(List<Creature> creaturesToAdd) {
        creatures.addAll(creaturesToAdd);
    }

    public void add(Artifact artifact) {
        artifacts.add(artifact);
    }

    public void add(Player player) {
        this.hasPlayer = true;
        this.player = player;
    }

    public void remove(Player player) {
        this.player = null;
        hasPlayer = false;
    }

    public void enterBiome(Player player) {
        add(player);
    }

    public int numberOfNeighbors() {
        return neighbors.size();
    }

    public boolean hasNeighbors() {
        return !neighbors.isEmpty();
    }

    public boolean hasLivingPlayer() {
        return hasPlayer;
    }

    public boolean hasFood() {
        return artifacts.stream()
                .anyMatch(artifact -> artifact.isType(ArtifactType.Food));
    }

    public Food getFood() {
        Optional<Artifact> foodOpt = artifacts.stream()
                .filter(a -> a.isType(ArtifactType.Food))
                .findAny();

        foodOpt.ifPresent(artifacts::remove);

        return (Food) foodOpt.orElse(null);
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

    public List<Artifact> getAntidoteItems() {
        return artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Antidote))
                .toList();
    }

    public Creature getCreature() {
        List<Creature> creatures = getLivingCreatures();
        if (creatures.isEmpty()) {
            return null;
        }
        return creatures.get(rand.nextInt(creatures.size()));
    }

    public Optional<Artifact> getAntidote() {
        Optional<Artifact> antidote = artifacts.stream()
                .filter(artifact -> artifact.isType(ArtifactType.Antidote))
                .findAny();
        antidote.ifPresent(artifacts::remove);
        return antidote;
    }

    public void displayBiome() {
        // Should never happen
        System.out.println("Display base biome.");
    }

}