package radioactivePursuit.planet;

import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.interactives.ArtifactType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BiomeFactory {

    private final ArtifactFactory artifactFactory;
    private final CreatureFactory creatureFactory;

    Random random = new Random();
    private static final BiomeType[] biomeTypes = BiomeType.values();
    public static String[] CITY_NAMES = new String[]{"Oakridge🏙️", "Silverport🏙️", "Brookstone🏙️", "Denver🏙️", "Northgate🏙️"};
    public static String[] HOSPITAL_NAMES = new String[]{"Central General Hospital🏥", "Riverside Medical Center🏥", "Westview Health Clinic🏥", "Greenfield Medical Plaza🏥", "Lakeside Regional Hospital🏥", "Hillcrest Medical Center🏥"};
    public static String[] RIVER_NAMES = new String[]{"Pine River🏞️", "Maple Creek🏞️", "Silver Run🏞️", "Clearwater Stream🏞️", "Eastridge River🏞️", "Northbend Creek🏞️"};
    public static String[] TRAIN_STATION_NAMES = new String[]{"Eastline Train Station🚉", "Harbor Central🚉", "North Junction🚉", "Union Transit Hub🚉", "Ridgeview Station🚉", "Metro West Terminal🚉"};
    public static Map<BiomeType, String[]> NAMES = new HashMap<>();

    static {
        NAMES.put(BiomeType.City, CITY_NAMES);
        NAMES.put(BiomeType.Hospital, HOSPITAL_NAMES);
        NAMES.put(BiomeType.River, RIVER_NAMES);
        NAMES.put(BiomeType.TrainStation, TRAIN_STATION_NAMES);
    }

    public BiomeFactory(ArtifactFactory artifactFactory, CreatureFactory creatureFactory) {
        this.artifactFactory = artifactFactory;
        this.creatureFactory = creatureFactory;
    }

    public Biome createBiome() {
        int randomIndex = random.nextInt(biomeTypes.length);
        BiomeType randomType = biomeTypes[randomIndex];

        String name = getRandomName(randomType);

        return switch (randomType) {
            case City -> createCityBiome(name);
            case Hospital -> createHospitalBiome(name);
            case River -> createRiverBiome(name);
            case TrainStation -> createTrainStationBiome(name);
        };
    }

    public Biome createCityBiome(String name) {
        Biome biome = new CityBiome(name);

        biome.add(artifactFactory.create(ArtifactType.Food));
        biome.add(artifactFactory.create(ArtifactType.Food));

        biome.add(creatureFactory.createCityBiomeCreatures(1));
        return biome;
    }

    public Biome createHospitalBiome(String name) {
        Biome biome = new HospitalBiome(name);

        biome.add(artifactFactory.create(ArtifactType.Antidote));
        biome.add(artifactFactory.create(ArtifactType.Antidote));

        biome.add(creatureFactory.createHospitalBiomeCreatures(1));
        return biome;

    }

    public Biome createRiverBiome(String name) {
        Biome biome = new RiverBiome(name);

        biome.add(creatureFactory.createRiverBiomeCreatures(1));

        return biome;
    }

    public Biome createTrainStationBiome(String name) {
        Biome biome = new TrainStationBiome(name);

        biome.add(artifactFactory.create(ArtifactType.Antidote));

        biome.add(creatureFactory.createTrainStationBiomeCreatures(1));

        return biome;
    }

    private String getRandomName(BiomeType biomeType) {
        String[] names = NAMES.get(biomeType);
        return names[random.nextInt(names.length)];
    }
}

