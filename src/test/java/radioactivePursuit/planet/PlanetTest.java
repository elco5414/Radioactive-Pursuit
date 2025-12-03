package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.player.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlanetTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void TestNumRooms() {
        Player player = new Player("TestPlayer");

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(10)
                .connectCirclePlanet()
                .add(player)
                .build();

        assertEquals(10, planet.size());

    }

    @Test
    void testHasLivingCreatures() {
        Player player = new Player("TestPlayer");

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(10)
                .connectCirclePlanet()
                .add(player)
                .build();

        assertEquals(10, planet.getLivingCreatures().size());
    }
}
