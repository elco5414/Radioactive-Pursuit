package radioactivePursuit.creatures;

public class Butterfree extends Creature{
    Butterfree(String newName, Double newHealth, int newRadioActiveLevel) {
        super(newName, newHealth, newRadioActiveLevel);
    }

    @Override
    public void displayCreature(){
        if(getRadioActiveLevel() == 0){
            System.out.println(" (\\__/)\n");
            System.out.println(" (o^-^)\n");
            System.out.println(" z(\")(\")\n");
        }else if(getRadioActiveLevel() == 1){
            System.out.println(" (\\__/)\n");
            System.out.println(" (>‘o‘<)\n");
            System.out.println(" z(\")(\")\n");
        }else if(getRadioActiveLevel() == 2){
            System.out.println(" (\\__/)\n");
            System.out.println(" (^•ᴥ•^)\n");
            System.out.println(" z(\")(\")🔥\n");
        }else{
            System.out.println("   (\\__/)");
            System.out.println("   (O>_<O)");
            System.out.println(" 🔥/︵|︵\\🔥");
            System.out.println("  z(\")(\")");
        }
    }
}
