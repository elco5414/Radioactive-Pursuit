package polymorphia.characters;

import polymorphia.artifacts.IArtifact;

public class ArmoredCharacter extends DecoratedCharacter{

    IArtifact myArmor;
    Character myself;

    public ArmoredCharacter(Character character, IArtifact armor) {
        super(character);
        myself = character;
        myArmor = armor;
    }

    @Override
    public Double getHealthLostMovingRooms(){
        return myself.getHealthLostMovingRooms() + myArmor.getMovingCost();
    }

    @Override
    public String getName() {
        return "Armor " + myself.getName();
    }

    @Override
    void loseFightDamage(double fightDamage) {
        if (fightDamage - myArmor.getDefenseValue() >= 0.0)
        {
            loseHealth(fightDamage - myArmor.getDefenseValue());
        }
        else
        {
            logger.info("Armor deflected attack! No damage lost.");
        }
    }
}
