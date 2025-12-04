package radioactivePursuit.planet;


//import org.slf4j.Logger;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.player.Player;


import java.util.*;
import java.util.stream.IntStream;


public class Planet {
    private static List<Biome> biomes;

    public static Builder getNewBuilder(BiomeFactory biomeFactory) {
        return new Builder(biomeFactory);
    }

    public int size() {
        return biomes.size();
    }

    public String toString() {
        return String.join("\n\n", biomes.stream().map(Object::toString).toList());
    }

    public Boolean hasLivingCreatures() {

        return biomes.stream().anyMatch(Biome::hasLivingCreatures);
    }

    public Boolean hasRadioActiveCreatures() {

        return biomes.stream().anyMatch(Biome::hasRadioActiveCreatures);
    }

    public Boolean hasLivingPlayer() {
        return biomes.stream().anyMatch(Biome::hasLivingPlayer);
    }

    public List<Creature> getLivingCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (Biome biome : biomes) {
            creatures.addAll(biome.getLivingCreatures());
        }
        return creatures;
    }

    public List<Biome> getBiomes() {
        return List.copyOf(biomes);
    }

    public Biome getBiome(String biomeName) {
        return biomes.stream()
                .filter(biome -> biome.getName().equals(biomeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No biome with name " + biomeName));
    }


    public static class Builder {
        final Planet planet = new Planet();
        private final Random rand = new Random();
        private final BiomeFactory biomeFactory;
        Map<String, Biome> biomeMap = new HashMap<>();
        private int currentBiomeIndex = 0;

        private Builder(BiomeFactory biomeFactory) {
            this.biomeFactory = biomeFactory;
        }


        private Biome nextBiome() {
            return planet.getBiomes().get(currentBiomeIndex++ % planet.getBiomes().size());
        }


        private Biome getRandomBiome() {
            return planet.biomes.get(rand.nextInt(planet.biomes.size()));
        }


        public Builder createBiomes(int numberOfBiomes) {
            planet.biomes = new ArrayList<>();
            IntStream.range(2, numberOfBiomes).forEach(_ -> {
                Biome currentBiome = biomeFactory.createBiome();
                biomeMap.put(currentBiome.getName(), currentBiome);
                planet.biomes.add(currentBiome);
            });
            return this;
        }


        public Builder connectCirclePlanet() {
            Biome lastBiome = planet.biomes.getLast();
            Biome firstBiome = planet.biomes.getFirst();

            for (int i = 0; i < planet.biomes.size() - 1; i++) {
                Biome currentBiome = planet.biomes.get(i);
                Biome neighbor = planet.biomes.get(i+1);

                currentBiome.addNeighbor(neighbor);
            }

            lastBiome.addNeighbor(firstBiome);

            return this;
        }


        public Builder add(Player player) {
            Biome biome = nextBiome();
            biome.add(player);
            player.setCurrentLocation(biome);
            return this;
        }


        public Builder addArtifacts(List<Artifact> artifacts) {
            for (Artifact artifact : artifacts) {
                nextBiome().add(artifact);
            }
            return this;
        }


        public Planet build() {
            assert planet.size() > 0;
            for (Biome biome : planet.biomes) {
                if (biome.numberOfNeighbors() == 0) {
                    //logger.warn("Biome {} has no neighbors. Connecting it to another biome.", biome.getName());
                    biome.addNeighbor(nextBiome());
                }
            }

            if (!planet.hasLivingPlayer()) {
                //logger.error("No Player created. Terminating game.");
                throw new IllegalStateException("No Player created. Terminating game.");
            }
            return planet;
        }



        // not sure if this will affect use, without character class it works a little different.
        // Might have to change how player is added to room
        public Builder addToBiome(String biomeName, Player player) {
            getBiome(biomeName).add(player);
            return this;
        }


        public Builder addToBiome(String biomeName, Artifact artifact) {
            getBiome(biomeName).add(artifact);
            return this;
        }


        public Biome getBiome(String biomeName) {
            return biomeMap.get(biomeName);
        }

    }


}
