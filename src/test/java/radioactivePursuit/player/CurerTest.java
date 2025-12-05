package radioactivePursuit.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.Antidote;
import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.ArtifactFactory;
import radioactivePursuit.planet.BiomeFactory;
import radioactivePursuit.planet.Planet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static radioactivePursuit.interactives.ArtifactType.Antidote;

public class CurerTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

    @Test
    void testCurerStrat(){
        Player player = new Player("testPlayer");
        PlayStrategy curer = new CurerStrategy();
        player.setPlayStrategy(curer);

        Planet planet = Planet.getNewBuilder(biomeFactory)
                .createBiomes(3)
                .connectCirclePlanet()
                .add(player)
                .build();

        double startingRadioActiveLevel = player.getCurrentLocation().getCreature().getRadioActiveLevel();
        player.doAction();

        assertTrue(startingRadioActiveLevel > player.getCurrentLocation().getCreature().getRadioActiveLevel());
    }
}
