package polymorphia.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polymorphia.artifacts.Artifact;
import polymorphia.artifacts.IArtifact;
import polymorphia.characters.ArmoredCharacter;
import polymorphia.characters.Character;
import polymorphia.characters.CharacterFactory;
import polymorphia.maze.Room;
import polymorphia.observers.EventBus;
import polymorphia.observers.EventType;

import java.util.Optional;

import static polymorphia.artifacts.ArtifactType.Weapon;

abstract public class PlayStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PlayStrategy.class);
    private static final CharacterFactory CHARACTER_FACTORY = new CharacterFactory();

    static void eatFood(Character myself, Room currentRoom) {
        Optional<IArtifact> food = currentRoom.getFood();
        if (food.isPresent()) {
            myself.eat(food.get());
        } else {
            logger.error("No food in room");
        }
    }

    static void putOnArmor(Character myself, Room currentRoom) {
        Optional<IArtifact> armor = currentRoom.getArmor();
        if (armor.isPresent()) {
            IArtifact foundArmor = armor.get();
            Character armoredCharacter = CHARACTER_FACTORY.createArmoredCharacter(myself, foundArmor);
            currentRoom.add(armoredCharacter);
            currentRoom.remove(myself);
            currentRoom.remove(foundArmor);
        } else {
            logger.error("No armor in room\n");
        }
    }

    static void pickUpWeapon(Character myself, Room currentRoom) {
        Optional<IArtifact> weapon = currentRoom.getArtifact(Weapon);
        if (weapon.isPresent()) {
            IArtifact foundWeapon = weapon.get();
            Character armedCharacter = CHARACTER_FACTORY.createArmedCharacter(myself, foundWeapon);
            currentRoom.add(armedCharacter);
            currentRoom.remove(myself);
            currentRoom.remove(foundWeapon);
        } else {
            logger.error("No weapon in room\n");
        }
    }

    abstract public void doAction(Character character, Room currentRoom);

    boolean shouldMove(Room room) {
        return true;
    }
}
