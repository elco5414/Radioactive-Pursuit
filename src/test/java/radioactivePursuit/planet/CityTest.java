package radioactivePursuit.planet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CityTest {

    ArtifactFactory artifactFactory = new ArtifactFactory();
    CreatureFactory creatureFactory = new CreatureFactory();
    BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

//
//    @Test
//    void hasButterfree() {
//
//        Biome city = biomeFactory.createCityBiome("Denver");
//
//        assertTrue(city.contains(creatureFactory.createButterfree()));
//
//    }

}
