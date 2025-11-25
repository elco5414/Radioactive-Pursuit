package radioactivePursuit.player;

import radioactivePursuit.interactives.Creature;

public class CurerStrategy implements PlayStrategy{
    @Override
    public void doAction(Player scientist) {
        if(scientist.canCure(scientist.getCurrentLocation().getCreature())){
            scientist.cure(scientist.getCurrentLocation().getCreature());
        }
    }
}
