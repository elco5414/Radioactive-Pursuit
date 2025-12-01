package radioactivePursuit.planet;

import org.junit.jupiter.api.Test;
import radioactivePursuit.interactives.CreatureFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BiomeTest {

    CreatureFactory creatureFactory;

    @Test
    void getRandomNeighbor() {
        //Arrange
        CityBiome city = new CityBiome("onlyCity");
        HospitalBiome hospital = new HospitalBiome("UC Health");

        //Act
        city.addNeighbor(hospital);

        //Assert
        assertEquals(city.getRandomNeighbor(), hospital);
    }

    @Test
    void testGetRandomNeighborOnRoomWithNoNeighbors() {
        RiverBiome biome = new RiverBiome("onlyBiome");

        assertNull(biome.getRandomNeighbor());
    }

    @Test
    void testToString() {
        CityBiome cityBiome = new CityBiome("onlyBiome");
        cityBiome.add(creatureFactory.createCreature("Killer Bunny"));
        cityBiome.add(creatureFactory.createCreature("Oozy Lizard"));

        System.out.println(cityBiome);

        assertTrue(cityBiome.toString().contains("onlyBiome"));
        assertTrue(cityBiome.toString().contains("Killer Bunny"));
        assertTrue(cityBiome.toString().contains("Oozy Lizard"));
    }
}
