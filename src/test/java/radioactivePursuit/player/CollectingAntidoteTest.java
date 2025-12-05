package radioactivePursuit.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectingAntidoteTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void testCollectionStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy collection = new CollectingAntidoteStrategy();
        player.setPlayStrategy(collection);

        Biome biome = biomeFactory.createHospitalBiome("hospital");
        List<Artifact> antidotes = biome.getArtifacts();
        int amount = antidotes.size();

        player.setCurrentLocation(biome);
        biome.add(player);

        player.doAction();

        assertTrue(amount > biome.getArtifacts().size());
    }
}
