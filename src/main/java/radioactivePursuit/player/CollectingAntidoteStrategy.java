package radioactivePursuit.player;

public class CollectingAntidoteStrategy implements PlayStrategy {
    @Override
    public void doAction(Player scientist) {
        if(scientist.canCollectAntidote()){
            scientist.collectAntidote();
        }
    }
}
