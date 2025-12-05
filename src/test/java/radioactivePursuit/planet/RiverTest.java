package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.creatures.Squirtle;
import radioactivePursuit.interactives.ArtifactFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RiverTest {
    ArtifactFactory artifactFactory = new ArtifactFactory();
    CreatureFactory creatureFactory = new CreatureFactory();
    BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);


    @Test
    void testHasRiverCreatures() {
        Biome river = biomeFactory.createRiverBiome("Boulder Creek");

        assertTrue(river.getLivingCreatures()
                .stream()
                .anyMatch(c -> c instanceof Squirtle));


    }
}
