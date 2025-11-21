package polymorphia.characters;

import polymorphia.artifacts.IArtifact;

public class ArmedCharacter extends DecoratedCharacter{
    IArtifact myWeapon;
    Character myself;

    public ArmedCharacter(Character character, IArtifact weapon) {
        super(character);
        myself = character;
        myWeapon = weapon;
    }

    @Override
    public String getName() {
        return "Weapon " + myself.getName();
    }

    @Override
    public double getAddedDamage() {
        return myself.getAddedDamage() + myWeapon.getDamageValue();
    }
}
