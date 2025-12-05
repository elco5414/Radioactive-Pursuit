package radioactivePursuit.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {
    CreatureFactory creatureFactory = new CreatureFactory();

    @Test
    void PikachuCreationTest(){
        Creature pikachu = creatureFactory.createPikachu();

        assertTrue(pikachu.getHealth() <= 12 && pikachu.getHealth() >= 7);
        assertEquals(pikachu.getName(), "Pikachu");
        assertTrue(pikachu.getRadioActiveLevel() <= 3);
        assertTrue(pikachu.getRadioActiveLevel() > 0);
    }

    @Test
    void ClefairyCreationTest(){
        Creature clefairy = creatureFactory.createClefairy();

        assertTrue(clefairy.getHealth() <= 12 && clefairy.getHealth() >= 7);
        assertEquals(clefairy.getName(), "Clefairy");
        assertTrue(clefairy.getRadioActiveLevel() <= 3);
        assertTrue(clefairy.getRadioActiveLevel() > 0);

    }
    @Test
    void SquirtleCreationTest(){
        Creature squirtle = creatureFactory.createSquirtle();
        assertTrue(squirtle.getHealth() <= 12 && squirtle.getHealth() >= 7);
        assertEquals(squirtle.getName(), "Squirtle");
        assertTrue(squirtle.getRadioActiveLevel() <= 3);
        assertTrue(squirtle.getRadioActiveLevel() > 0);
    }
    @Test
    void ButterfreeCreationTest(){
        Creature butterfree = creatureFactory.createButterfree();
        assertTrue(butterfree.getHealth() <= 12 && butterfree.getHealth() >= 7);
        assertEquals(butterfree.getName(), "Butterfree");
        assertTrue(butterfree.getRadioActiveLevel() <= 3);
        assertTrue(butterfree.getRadioActiveLevel() > 0);

    }
}
