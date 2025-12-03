package radioactivePursuit.creatures;

import radioactivePursuit.planet.BiomeType;

public class Squirtle extends Creature{
    Squirtle(String newName, Double newHealth, int newRadioActiveLevel) {
        super(newName, newHealth, newRadioActiveLevel, BiomeType.River);
    }

    @Override
    public void displayCreature(){
        if(getRadioActiveLevel() == 0){
            System.out.println("   ___");
            System.out.println("  / o o\\");
            System.out.println(" (  ^  )");
            System.out.println("  \\___/ ");
            System.out.println("  ~~~Squirtle~~~\n");
        } else if(getRadioActiveLevel() == 1){
            System.out.println("   ___");
            System.out.println("  / >o<\\");
            System.out.println(" (  v  )");
            System.out.println("  \\___/ ");
            System.out.println("  ~Squirtle~\n");
        } else if(getRadioActiveLevel() == 2){
            System.out.println("   ___");
            System.out.println("  / >O<\\");
            System.out.println(" ( >ᴥ< )");
            System.out.println("  \\___/🌧️");
            System.out.println("  ~Raging Squirtle~\n");
        } else {
            System.out.println("   ___");
            System.out.println("  / >X<\\");
            System.out.println(" ( >_< )");
            System.out.println("  \\___/🌨️💧");
            System.out.println("  ~Rabid Squirtle~");
        }
    }

}

