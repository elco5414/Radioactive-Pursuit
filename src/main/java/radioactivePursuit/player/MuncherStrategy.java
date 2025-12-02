package radioactivePursuit.player;

import radioactivePursuit.interactives.Artifact;
import radioactivePursuit.interactives.Food;

import java.util.Optional;

public class MuncherStrategy implements PlayStrategy {
    @Override
    public void doAction(Player scientist) {
        if(scientist.canEat()){
            scientist.eat(scientist.getCurrentLocation().getFood());
        }
    }
}
