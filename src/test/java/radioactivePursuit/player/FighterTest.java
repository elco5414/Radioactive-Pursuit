package radioactivePursuit.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.Biome;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void testFighterStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy fighter = new FighterStrategy();
        player.setPlayStrategy(fighter);

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(3)
                .connectCirclePlanet()
                .add(player)
                .build();

        double creatureStartingHealth = player.getCurrentLocation().getCreature().getHealth();
        double playerStartingHealth = player.getHealth();
        player.doAction();

        assertTrue(creatureStartingHealth > player.getCurrentLocation().getCreature().getHealth() || player.getHealth() < playerStartingHealth);
    }
}
