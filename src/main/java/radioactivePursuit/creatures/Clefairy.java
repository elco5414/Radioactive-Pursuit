package radioactivePursuit.creatures;

import radioactivePursuit.planet.BiomeType;

public class Clefairy extends Creature{
    Clefairy(String newName, Double newHealth, int newRadioActiveLevel) {
        super(newName, newHealth, newRadioActiveLevel);
    }

    @Override
    public void displayCreature(){
        System.out.println(super.getName()+ ": "+ super.getRadioActiveLevel() + "☢️ " + super.getHealth() + "❤️ \n");
        if(getRadioActiveLevel() == 0){
            System.out.println("   (o^‿^o)");
            System.out.println("  / (   ) \\");
            System.out.println("   z('')('')\n");
        }else if(getRadioActiveLevel() == 1){
            System.out.println("   (>o^o<)");
            System.out.println("  / (   ) \\");
            System.out.println("   z('')('')\n");
        }else if(getRadioActiveLevel() == 2){
            System.out.println("   (>•ᴥ•<)");
            System.out.println("  / (   ) \\🌸");
            System.out.println("   z('')('')\n");
        }else{
            System.out.println("   (>O_<>)");
            System.out.println("  🌺/ (   ) \\🌸");
            System.out.println("   z('')('')");
        }
    }

}
