package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.Clefairy;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HospitalTest {
    ArtifactFactory artifactFactory = new ArtifactFactory();
    CreatureFactory creatureFactory = new CreatureFactory();
    BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);


    @Test
    void testHasHosptialCreatures() {
        Biome hospital = biomeFactory.createHospitalBiome("UC Health");

        assertTrue(hospital.getLivingCreatures()
                .stream()
                .anyMatch(c -> c instanceof Clefairy));


    }

    @Test
    void testHas2Antidote() {
        Biome hospital = biomeFactory.createHospitalBiome("UC Health");

        assertEquals(2, hospital.getAntidoteItems().size());


    }
}
