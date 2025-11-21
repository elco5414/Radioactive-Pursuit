package polymorphia.strategy;


import polymorphia.characters.Character;
import polymorphia.maze.Room;

import java.util.Optional;

public class FighterStrategy extends PlayStrategy {
    public void doAction(Character myself, Room currentRoom) {
        if(shouldPutOnArmor(currentRoom)){
            putOnArmor(myself, currentRoom);
            return;
        }
        if(shouldPickUpWeapon(currentRoom)){
            pickUpWeapon(myself, currentRoom);
            return;
        }
        if (shouldFight(currentRoom)) {
            fightCreature(myself, currentRoom);
            return;
        }
        if (shouldEat(currentRoom)) {
            eatFood(myself, currentRoom);
            return;
        }
        if (shouldMove(currentRoom)) {
            myself.move(currentRoom.getRandomNeighbor());
        }
    }

    boolean shouldEat(Room currentRoom) {
        return currentRoom.hasFood();
    }

    boolean shouldPutOnArmor(Room currentRoom){
        return currentRoom.hasArmor();
    }

    boolean shouldPickUpWeapon(Room currentRoom){
        return currentRoom.hasWeapon();
    }

    private static void fightCreature(Character myself, Room currentRoom) {
        Optional<Character> creature = currentRoom.getCreature();
        if (creature.isPresent()) {
            myself.fight(creature.get());
        }
    }

    boolean shouldFight(Room currentRoom) {
        return currentRoom.hasLivingCreatures();
    }

}
