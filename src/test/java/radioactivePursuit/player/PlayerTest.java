package radioactivePursuit.player;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.Creature;
import radioactivePursuit.creatures.CreatureFactory;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void testInitialization(){
        PlayStrategy playStrategy = new InactionStrategy();
        Player player = new Player(playStrategy);
        Player playerWithName = new Player("TestPlayer");

        assertEquals(player.getHealth(), 15.0);
        assertTrue(player.getAntidoteCount() > 0);

        assertEquals(playerWithName.getName(), "TestPlayer");
        assertEquals(playerWithName.getHealth(), 15.0);
        assertTrue(playerWithName.getAntidoteCount() > 0);

        player.setName("new_player_name");
        player.setHealth(5.0);
        assertEquals(player.getHealth(), 5.0);
        assertEquals(player.getName(), "new_player_name");
    }

    @Test
    void testUseAntidote(){
        Player playerWithName = new Player("TestPlayer");
        int intialAntidoteCount = playerWithName.getAntidoteCount();

        assertTrue(playerWithName.getAntidoteCount() > 0);

        playerWithName.useAntidote();
        assertEquals((intialAntidoteCount - 1), playerWithName.getAntidoteCount());
    }

    @Test
    void testSetPlayerStrategy(){
        PlayStrategy playStrategy = new InactionStrategy();
        PlayStrategy moverPlayStrategy = new MoverStrategy();
        Player player = new Player(playStrategy);

        assertEquals(player.getPlayStrategy(), playStrategy);

        player.setPlayStrategy(moverPlayStrategy);
        assertEquals(player.getPlayStrategy(), moverPlayStrategy);
    }

    @Test
    void testFight(){
        CreatureFactory creatureFactory = new CreatureFactory();

        Player player = new Player("PlayerToFight");
        Creature creature = creatureFactory.createPikachu();

        player.fight(creature);

        assertFalse(creature.isAlive());
    }

    @Test
    void isAlive(){
        Player player = new Player("PlayerToFight");
        player.setHealth(0.0);

        assertFalse(player.isAlive());
    }
}
