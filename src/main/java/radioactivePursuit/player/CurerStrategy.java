package radioactivePursuit.player;

public class CurerStrategy implements PlayStrategy{
    @Override
    public void doAction(Player scientist) {
        if(scientist.canCure(scientist.getCurrentLocation().getCreature())){
            scientist.cure();
        }
    }
}
