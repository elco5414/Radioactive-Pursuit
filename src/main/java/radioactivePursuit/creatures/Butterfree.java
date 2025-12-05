package radioactivePursuit.creatures;

import radioactivePursuit.planet.BiomeType;

public class Butterfree extends Creature{
    Butterfree(String newName, Double newHealth, int newRadioActiveLevel) {
        super(newName, newHealth, newRadioActiveLevel);
    }

    @Override
    public void displayCreature(){
        System.out.println(super.getName()+ ": "+ super.getRadioActiveLevel() + "☢️ " + super.getHealth() + "❤️ \n");
        if(getRadioActiveLevel() == 0){
            System.out.println("    .==-.                   .-==.");
            System.out.println("   \\()8`-._  `.   .'  _.-'8()/");
            System.out.println("   (88\"   ::.  \\./  .::   \"88)");
            System.out.println("    \\_.'`-::::.(#).::::-'`._/");
            System.out.println("      `._... .q(_)p. ..._.'");
            System.out.println("        \"\"-..-'|=|'`-..-\"\"\n");
        }else if(getRadioActiveLevel() == 1){
            System.out.println("    .==-.                   .-==.");
            System.out.println("   \\()8`-._  `.   .'  _.-'8()/🔥");
            System.out.println("   (88\"   ::.  \\./  .::   \"88)");
            System.out.println("    \\_.'`-::::.(#).::::-'`._/");
            System.out.println("      `._... .q(_)p. ..._.'");
            System.out.println("        \"\"-..-'|=|'`-..-\"\"\n");
        }else if(getRadioActiveLevel() == 2){
            System.out.println("    .==-.                   .-==.");
            System.out.println("   \\()8`-._  `.   .'  _.-'8()/🔥🔥");
            System.out.println("   (88\"  >:o::.  \\./  .::  o<\"88)");
            System.out.println("    \\_.'`-::::.(#).::::-'`._/");
            System.out.println("      `._... .q(_)p. ..._.'");
            System.out.println("        \"\"-..-'|=|'`-..-\"\"\n");
        }else{
            System.out.println("    .==-.       🔥🔥🔥🔥       .-==.");
            System.out.println("   \\()8`-._  >:O<  _.-'8()/");
            System.out.println("   (88\"  >:O<::.  \\./  .::  O<\"88)");
            System.out.println("    \\_.'`-::::.(#).::::-'`._/");
            System.out.println("      `._... .q(_)p. ..._.'");
            System.out.println("        \"\"-..-'|=|'`-..-\"\"");
        }
    }

}
