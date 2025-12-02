package radioactivePursuit.planet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BiomeFactory {

    Random random = new Random();
    private static final BiomeType[] biomeTypes = BiomeType.values();

    public static String[] CITY_NAMES = new String[]{"Oakridge", "Silverport", "Riverview", "Brookstone", "Clearwater", "Northgate"};
    public static String[] HOSPITAL_NAMES = new String[]{"Central General Hospital", "Riverside Medical Center", "Westview Health Clinic", "Greenfield Medical Plaza", "Lakeside Regional Hospital", "Hillcrest Medical Center"};
    public static String[] RIVER_NAMES = new String[]{"Pine River", "Maple Creek", "Silver Run", "Clearwater Stream", "Eastridge River", "Northbend Creek"};
    public static String[] TRAIN_STATION_NAMES = new String[]{"Eastline Station", "Harbor Central", "North Junction", "Union Transit Hub", "Ridgeview Station", "Metro West Terminal"};
    public static Map<BiomeType, String[]> NAMES = new HashMap<>();

    static {
        NAMES.put(BiomeType.City, CITY_NAMES);
        NAMES.put(BiomeType.Hospital, HOSPITAL_NAMES);
        NAMES.put(BiomeType.River, RIVER_NAMES);
        NAMES.put(BiomeType.TrainStation, TRAIN_STATION_NAMES);
    }

    Biome createBiome(String name) {
        int randomIndex = random.nextInt(biomeTypes.length);
        BiomeType randomType = biomeTypes[randomIndex];

        return switch (randomType) {
            case City -> new CityBiome(name);
            case Hospital -> new HospitalBiome(name);
            case River -> new RiverBiome(name);
            case TrainStation -> new TrainStationBiome((name));
        };

    }

    Biome createBiome() {
        int randomIndex = random.nextInt(biomeTypes.length);
        BiomeType randomType = biomeTypes[randomIndex];

        String name = getRandomName(randomType);

        return switch (randomType) {
            case City -> new CityBiome(name);
            case Hospital -> new HospitalBiome(name);
            case River -> new RiverBiome(name);
            case TrainStation -> new TrainStationBiome((name));
        };
    }

    public Biome createCityBiome(String name) {
        return new CityBiome(name);
    }
    public Biome createHospitalBiome(String name) {
        return new HospitalBiome(name);
    }
    public Biome createRiverBiome(String name) {
        return new RiverBiome(name);
    }
    public Biome createTrainStationBiome(String name) {
        return new TrainStationBiome(name);
    }
    private String getRandomName(BiomeType biomeType) {
        String[] names = NAMES.get(biomeType);
        return names[random.nextInt(names.length)];
    }
}

