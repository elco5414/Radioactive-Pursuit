package radioactivePursuit.interactives;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class ArtifactFactory {
    private static final Random random = new Random();
    private static final double MINIMUM_VALUE = 1.0;
    private static final double MAXIMUM_VALUE = 2.0;
    private static final String[] FOOD_NAMES = new String[]{
            "cupcake", "apple", "banana", "steak", "salad", "fries", "burger", "pizza", "eggs",
            "bacon", "muffin", "donut", "chicken", "pasta", "rice", "sushi", "taco", "burrito",
            "nachos", "chips"};
    private static final Map<ArtifactType, String[]> NAMES = new HashMap<>();
    public static String[] ANTIDOTE_NAMES = new String[]{"tonic", "serum", "salve", "elixir", "remedy", "panacea", "essence", "antitoxin"};

    static {
        NAMES.put(ArtifactType.Food, FOOD_NAMES);
        NAMES.put(ArtifactType.Antidote, ANTIDOTE_NAMES);
    }

    private static double getRandomValue() {
        return random.nextDouble(MINIMUM_VALUE, MAXIMUM_VALUE);
    }

    private static String getRandomName(ArtifactType type) {
        String[] names = NAMES.get(type);
        return names[random.nextInt(names.length)];
    }

    public List<Artifact> createFoodItems(int numberOfItems) {
        return createArtifacts(ArtifactType.Food, numberOfItems);
    }

    public List<Artifact> createAntidotes(int numberOfItems) {
        return createArtifacts(ArtifactType.Antidote, numberOfItems);
    }

    private List<Artifact> createArtifacts(ArtifactType type, int numberOfItems) {
        return IntStream.range(0, numberOfItems)
                .mapToObj(_ -> create(type))
                .toList();
    }

    public Artifact create(ArtifactType type) {
        return create(type, getRandomName(type), getRandomValue(), getRandomValue());
    }


    public Artifact create(ArtifactType type, String name, double healthValue, double cureStrength) {
        return switch (type) {
            case Food -> new Food(name, healthValue);
            case Antidote -> new Antidote(name, cureStrength);
        };
    }

    public Artifact createFood(String name) {
        return create(ArtifactType.Food, name, getRandomValue(), getRandomValue());
    }

    public Artifact createFood(String name, double value) {
        return create(ArtifactType.Food, name, value, getRandomValue());
    }

}
