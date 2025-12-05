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

    //TODO: change the food names into emojis, and then we can just print the name for the display
    private static final String[] FOOD_NAMES = new String[]{
            "cupcake", "apple", "banana", "steak", "salad", "fries", "burger", "pizza", "eggs",
            "bacon", "muffin", "donut", "chicken", "pasta", "rice", "sushi", "taco", "burrito",
            "nachos", "chips"};
    private static final Map<ArtifactType, String[]> NAMES = new HashMap<>();

    //TODO: instead of naming these, can you just autopopulate all the names to be one emoji for it when you create them: 🧪, this will help display
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

    public List<Artifact> createAntidotes(int numberOfItems) {
        return createArtifacts(ArtifactType.Antidote, numberOfItems);
    }

    private List<Artifact> createArtifacts(ArtifactType type, int numberOfItems) {
        return IntStream.range(0, numberOfItems)
                .mapToObj(_ -> create(type))
                .toList();
    }

    public Artifact create(ArtifactType type) {
        return create(type, getRandomName(type), getRandomValue());
    }


    public Artifact create(ArtifactType type, String name, double healthValue) {
        return switch (type) {
            case Food -> new Food(name, healthValue);
            case Antidote -> new Antidote(name);
        };
    }

}
