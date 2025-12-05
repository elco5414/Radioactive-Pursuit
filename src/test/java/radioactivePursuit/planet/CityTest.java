package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.Butterfree;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CityTest {

    ArtifactFactory artifactFactory = new ArtifactFactory();
    CreatureFactory creatureFactory = new CreatureFactory();
    BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);


    @Test
    void testHasCityCreatures() {
        Biome cityBiome = biomeFactory.createCityBiome("Denver");

        assertTrue(cityBiome.getLivingCreatures()
                .stream()
                .anyMatch(c -> c instanceof Butterfree));


    }

    @Test
    void testHas2Food() {
        Biome cityBiome = biomeFactory.createCityBiome("LA");

        assertEquals(2, cityBiome.getFoodItems().size());


    }
}
