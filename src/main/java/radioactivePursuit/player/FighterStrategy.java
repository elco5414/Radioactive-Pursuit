package radioactivePursuit.player;

public class FighterStrategy implements PlayStrategy {
    @Override
    public void doAction(Player scientist) {
        if(scientist.canFight()){
            scientist.fight(scientist.getCurrentLocation().getCreature());
        }
    }
}
