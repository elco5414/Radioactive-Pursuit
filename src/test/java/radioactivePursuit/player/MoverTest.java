package radioactivePursuit.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;

import java.util.List;

public class MoverTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void testMoverStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy mover = new MoverStrategy();
        player.setPlayStrategy(mover);

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(3)
                .connectCirclePlanet()
                .add(player)
                .build();

        Biome startingBiome = player.getCurrentLocation();
        player.doAction();

        assertTrue(startingBiome != player.getCurrentLocation());
    }
}
