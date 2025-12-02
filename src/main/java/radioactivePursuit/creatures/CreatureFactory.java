package radioactivePursuit.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatureFactory {
    static private final Random rand = new Random();
    static private final String pikachuName = "Pikachu";
    static private final String clefairyName = "Clefairy";
    static private final String squirtleName = "Squirtle";
    static private final String butterfreeName = "Butterfree";

    int assignRadioActiveLevel() {
        return rand.nextInt(3) + 1;
    }

    private Creature createPikachu() {
        return new Pikachu(pikachuName, 5.0, assignRadioActiveLevel());
    }

    private Creature createSquirtle() {
        return new Squirtle(squirtleName, 7.0, assignRadioActiveLevel());
    }

    private Creature createClefairy() {
        return new Clefairy(clefairyName, 4.3, assignRadioActiveLevel());
    }

    private Creature createButterfree() {
        return new Butterfree(butterfreeName, 3.0, assignRadioActiveLevel());
    }

    public List<Creature> createRiverBiomeCreatures(int amountToGenerate) {
        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < amountToGenerate; i++) {
            creatures.add(createSquirtle());
        }
        return creatures;
    }

    public List<Creature> createCityBiomeCreatures(int amountToGenerate) {
        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < amountToGenerate; i++) {
            creatures.add(createButterfree());
        }
        return creatures;
    }

    public List<Creature> createHospitalBiomeCreatures(int amountToGenerate) {
        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < amountToGenerate; i++) {
            creatures.add(createClefairy());
        }
        return creatures;
    }

    public List<Creature> createTrainStationBiomeCreatures(int amountToGenerate) {
        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < amountToGenerate; i++) {
            creatures.add(createPikachu());
        }
        return creatures;
    }
}
