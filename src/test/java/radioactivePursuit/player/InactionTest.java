package radioactivePursuit.player;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InactionTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void testInactionStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy dontAct = new InactionStrategy();
        player.setPlayStrategy(dontAct);

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(3)
                .connectCirclePlanet()
                .add(player)
                .build();

        player.doAction();

        //verify nothing has changed after action
        assertEquals(player.getHealth(), 10.0);
        assertEquals(player.getName(), "testPlayer");
        assertEquals(player.getPlayStrategy(), dontAct);
    }
}
