package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.creatures.Pikachu;
import radioactivePursuit.interactives.ArtifactFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainStationTest {
    ArtifactFactory artifactFactory = new ArtifactFactory();
    CreatureFactory creatureFactory = new CreatureFactory();
    BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);


    @Test
    void testHasTrainCreatures() {
        Biome train = biomeFactory.createTrainStationBiome("Penn Station");

        assertTrue(train.getLivingCreatures()
                .stream()
                .anyMatch(c -> c instanceof Pikachu));


    }

    @Test
    void testHasAntidote() {
        Biome train = biomeFactory.createTrainStationBiome("Penn Station");

        assertEquals(1, train.getAntidoteItems().size());


    }
}
