package radioactivePursuit.player;

public class MuncherStrategy implements PlayStrategy {
    @Override
    public void doAction(Player scientist) {
        if(scientist.canEat()){
            scientist.eat(scientist.getCurrentLocation().getFood());
        }
    }
}
