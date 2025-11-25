package radioactivePursuit.player;

public class MoverStrategy implements PlayStrategy{

    @Override
    public void doAction(Player scientist) {
        if(scientist.canMove()){
            scientist.move();
        }
    }
}
