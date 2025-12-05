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
    static private final Double MIN_HEALTH = 7.0;
    static private final Double MAX_HEALTH = 12.0;

    int assignRadioActiveLevel() {
        return rand.nextInt(3) + 1;
    }

    public Creature createPikachu() {
        return new Pikachu(pikachuName, rand.nextDouble(MIN_HEALTH, MAX_HEALTH), assignRadioActiveLevel());
    }

    public Creature createSquirtle() {
        return new Squirtle(squirtleName, rand.nextDouble(MIN_HEALTH, MAX_HEALTH), assignRadioActiveLevel());
    }

    public Creature createClefairy() {
        return new Clefairy(clefairyName, rand.nextDouble(MIN_HEALTH, MAX_HEALTH), assignRadioActiveLevel());
    }

    public Creature createButterfree() {
         return new Butterfree(butterfreeName, rand.nextDouble(MIN_HEALTH, MAX_HEALTH), assignRadioActiveLevel());
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
