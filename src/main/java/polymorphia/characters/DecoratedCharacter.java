package polymorphia.characters;

import polymorphia.observers.EventBus;
import polymorphia.observers.EventType;
import polymorphia.strategy.PlayStrategy;

public abstract class DecoratedCharacter extends Character {
    Character myself;

    protected DecoratedCharacter(Character character) {
        super(character.getName(), character.getHealth(), character.getPlayStrategy());
        this.myself = character;
    }

    @Override
    public Double getHealth(){
        return myself.getHealth();
    }

    @Override
    public void loseHealth(Double healthPoints) {
        myself.loseHealth(healthPoints);
    }

    @Override
    public Integer getRoll() {
        return myself.getRoll();
    }

    @Override
    public double getAddedDamage() {
        return myself.getAddedDamage();
    }

}
