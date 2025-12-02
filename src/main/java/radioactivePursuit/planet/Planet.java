package radioactivePursuit.planet;


import org.slf4j.Logger;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.player.Player;


import java.util.*;
import java.util.stream.IntStream;


public class Planet {
    private List<Biome> biomes;

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

    public Boolean hasLivingPlayer() {
        return biomes.stream().anyMatch(Biome::hasLivingPlayer);
    }

    //necessary?
//    public Player getLivingPlayer() {
//        for (Biome biome : biomes) {
//            creatures.addAll(biome.getLivingCreatures());
//        }
//        return creatures;
//    }

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
        static Logger logger = org.slf4j.LoggerFactory.getLogger(Builder.class);
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


        public Builder createGridOfRooms(int rows, int columns, String[] roomNames) {
            Biome[][] biomeGrid = new Biome[rows][columns];
            List<Biome> biomes = new ArrayList<>();
            // Notice -- don't use i and j. Use row and column -- they are better
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Biome newBiome = biomeFactory.createBiome(roomNames[row * columns + column]);
                    biomeGrid[row][column] = newBiome;
                    biomes.add(newBiome);
                }
            }
            planet.biomes = biomes;


            // Now connect the rooms
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Biome currentBiome = biomeGrid[row][column];
                    Biome neighbor;
                    if (row > 0) {
                        neighbor = biomeGrid[row - 1][column];
                        currentBiome.addNeighbor(neighbor);
                    }
                    if (column > 0) {
                        neighbor = biomeGrid[row][column - 1];
                        currentBiome.addNeighbor(neighbor);
                    }
                }
            }
            return this;
        }


        public Builder createFullyConnectedBiomes(List<String> biomeNames) {
            planet.biomes = new ArrayList<>();
            for (String biomeName : biomeNames) {
                Biome currentBiome = biomeFactory.createBiome(biomeName);
                biomeMap.put(currentBiome.getName(), currentBiome);
                planet.biomes.add(currentBiome);
            }
            fullyConnectRooms();
            return this;
        }


        public Builder createBiomes(List<String> biomeNames) {
            planet.biomes = new ArrayList<>();
            for (String biomeName : biomeNames) {
                Biome currentBiome = biomeFactory.createBiome(biomeName);
                biomeMap.put(currentBiome.getName(), currentBiome);
                planet.biomes.add(currentBiome);
            }
            return this;
        }


        public Builder fullyConnectRooms() {
            for (Biome biome : planet.biomes) {
                for (Biome otherBiome : planet.biomes) {
                    biome.addNeighbor(otherBiome);
                }
            }
            return this;
        }


        public Builder add(Creature creature) {
            nextBiome().add(creature);
            return this;
        }


        public Builder addCreatures(List<Creature> creatures) {
            for (Creature creature : creatures) {
                nextBiome().add(creature);
            }
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
                    logger.warn("Biome {} has no neighbors. Connecting it to another biome.", biome.getName());
                    biome.addNeighbor(nextBiome());
                }
            }

            if (!planet.hasLivingPlayer()) {
                logger.error("No Player created. Terminating game.");
                throw new IllegalStateException("No Player created. Terminating game.");
            }
            return planet;
        }


        public Builder addToRoom(String biomeName, Creature creature) {
            getBiome(biomeName).add(creature);
            return this;
        }


        // not sure if this will affect use, without character class it works a little different.
        // Might have to change how player is added to room
        public Builder addToRoom(String biomeName, Player player) {
            getBiome(biomeName).add(player);
            return this;
        }


        public Builder addToRoom(String biomeName, Artifact artifact) {
            getBiome(biomeName).add(artifact);
            return this;
        }


        public Biome getBiome(String biomeName) {
            return biomeMap.get(biomeName);
        }


        private Builder createBiomes(int numberOfBiomes) {
            planet.biomes = new ArrayList<>();
            IntStream.range(0, numberOfBiomes).forEach(_ -> {
                Biome currentBiome = biomeFactory.createBiome();
                biomeMap.put(currentBiome.getName(), currentBiome);
                planet.biomes.add(currentBiome);
            });
            return this;
        }
    }


}
