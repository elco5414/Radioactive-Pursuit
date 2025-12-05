package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;
import radioactivePursuit.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class BiomeTest {

    CreatureFactory creatureFactory = new CreatureFactory();

    @Test
    void getRandomNeighbor() {
        //Arrange
        Biome city = new CityBiome("onlyCity");
        Biome hospital = new HospitalBiome("UC Health");

        //Act
        city.addNeighbor(hospital);

        //Assert
        assertEquals(city.getRandomNeighbor(), hospital);
    }

    @Test
    void testGetRandomNeighborOnRoomWithNoNeighbors() {
        Biome biome = new RiverBiome("onlyBiome");

        assertNull(biome.getRandomNeighbor());
    }


    @Test
    void testHasRadioActiveCreature() {
        Biome trainStationBiome = new TrainStationBiome("Penn Stattion");

        trainStationBiome.add(creatureFactory.createTrainStationBiomeCreatures(1));

    }

    @Test
    void testAddPlayer() {
        Biome trainStationBiome = new TrainStationBiome("Penn Stattion");
        Player player = new Player("player");

        trainStationBiome.add(player);
        assertEquals(player.getCurrentLocation(), trainStationBiome);
        assertEquals(trainStationBiome.player, player);
        assertTrue(trainStationBiome.hasPlayer);
    }
}
