package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.CreatureFactory;

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
}
