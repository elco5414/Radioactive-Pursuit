package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.interactives.ArtifactFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlanetTest {
    private final ArtifactFactory artifactFactory = new ArtifactFactory();
    private final CreatureFactory creatureFactory= new CreatureFactory();
    private final BiomeFactory biomeFactory = new BiomeFactory(artifactFactory, creatureFactory);

//    @Test
//    void testToString() {
//        List<Character> characters = List.of(
//                creatureFactory.createKnight("Bilbo"),
//                creatureFactory.createCreature("Ogre"));
//        Planet planet = Planet.getNewBuilder(biomeFactory)
//                .create2x2Grid()
//                .addCharacters(characters)
//                .build();
//        String mazeString = maze.toString();
//        logger.info(mazeString);
//
//        assertTrue(mazeString.contains("Bilbo"));
//        assertTrue(mazeString.contains("Ogre"));
//    }
}
