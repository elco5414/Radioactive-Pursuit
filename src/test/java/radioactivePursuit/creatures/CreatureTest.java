package radioactivePursuit.creatures;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    CreatureFactory creatureFactory = new CreatureFactory();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void PikachuCreationTest(){
        Creature pikachu = creatureFactory.createPikachu();

        assertEquals(pikachu.getHealth(), 5.0);
        assertEquals(pikachu.getName(), "Pikachu");
        assertTrue(pikachu.getRadioActiveLevel() <= 3);
        assertTrue(pikachu.getRadioActiveLevel() > 0);

        String expectedInfoLine = String.format("Pikachu: %d☢️ %.1f❤️ \n", pikachu.getRadioActiveLevel(), pikachu.getHealth());
        pikachu.displayCreature();
        String capturedOutput = outputStreamCaptor.toString();
        assertTrue(capturedOutput.startsWith(expectedInfoLine));
    }

    @Test
    void ClefairyCreationTest(){
        Creature clefairy = creatureFactory.createClefairy();
        assertEquals(clefairy.getHealth(), 4.3);
        assertEquals(clefairy.getName(), "Clefairy");
        assertTrue(clefairy.getRadioActiveLevel() <= 3);
        assertTrue(clefairy.getRadioActiveLevel() > 0);

        String expectedInfoLine = String.format("Clefairy: %d☢️ %.1f❤️ \n", clefairy.getRadioActiveLevel(), clefairy.getHealth());
        clefairy.displayCreature();
        String capturedOutput = outputStreamCaptor.toString();
        assertTrue(capturedOutput.startsWith(expectedInfoLine));
    }
    @Test
    void SquirtleCreationTest(){
        Creature squirtle = creatureFactory.createSquirtle();
        assertEquals(squirtle.getHealth(), 7.0);
        assertEquals(squirtle.getName(), "Squirtle");
        assertTrue(squirtle.getRadioActiveLevel() <= 3);
        assertTrue(squirtle.getRadioActiveLevel() > 0);

        String expectedInfoLine = String.format("Squirtle: %d☢️ %.1f❤️ \n", squirtle.getRadioActiveLevel(), squirtle.getHealth());
        squirtle.displayCreature();
        String capturedOutput = outputStreamCaptor.toString();
        assertTrue(capturedOutput.startsWith(expectedInfoLine));
    }
    @Test
    void ButterfreeCreationTest(){
        Creature butterfree = creatureFactory.createButterfree();
        assertEquals(butterfree.getHealth(), 3.0);
        assertEquals(butterfree.getName(), "Butterfree");
        assertTrue(butterfree.getRadioActiveLevel() <= 3);
        assertTrue(butterfree.getRadioActiveLevel() > 0);

        String expectedInfoLine = String.format("Butterfree: %d☢️ %.1f❤️ \n", butterfree.getRadioActiveLevel(), butterfree.getHealth());
        butterfree.displayCreature();
        String capturedOutput = outputStreamCaptor.toString();
        assertTrue(capturedOutput.startsWith(expectedInfoLine));
    }
}
