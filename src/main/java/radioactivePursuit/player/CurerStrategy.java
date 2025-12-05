package radioactivePursuit.player;

public class CurerStrategy implements PlayStrategy{
    @Override
    public void doAction(Player scientist) {
        //TODO check cure for if player has antidotes also
        if(scientist.canCure(scientist.getCurrentLocation().getCreature())){
            scientist.cure(scientist.getCurrentLocation().getCreature());
        }
    }
}
