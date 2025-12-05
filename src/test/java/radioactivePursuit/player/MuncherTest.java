package radioactivePursuit.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MuncherTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);


    @Test
    void testMuncherStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy muncher = new MuncherStrategy();
        player.setPlayStrategy(muncher);
        Biome biome = biomeFactory.createCityBiome("city");
        List<Artifact> foods = biome.getArtifacts();
        int foodAmount = foods.size();

        player.setCurrentLocation(biome);
        biome.add(player);

        player.doAction();
        assertTrue(foodAmount > biome.getArtifacts().size());
    }
}
