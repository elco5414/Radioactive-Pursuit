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
            "🧁", "🍎", "🍌", "🥩", "🥗", "🍟", "🍔", "🍕", "🍳",
            "🥓", "🥐", "🍩", "🍗", "🍝", "🍚", "🍣", "🌮", "🌯",
            "🌭", "🥭", "🍓", "🍒"};
    private static final Map<ArtifactType, String[]> NAMES = new HashMap<>();

    static {
        NAMES.put(ArtifactType.Food, FOOD_NAMES);
    }

    private static double getRandomValue() {
        return random.nextDouble(MINIMUM_VALUE, MAXIMUM_VALUE);
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
        return create(type, "🧪", getRandomValue());
    }


    public Artifact create(ArtifactType type, String name, double healthValue) {
        return switch (type) {
            case Food -> new Food(name, healthValue);
            case Antidote -> new Antidote(name);
        };
    }

}
