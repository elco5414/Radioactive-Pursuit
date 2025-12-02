package radioactivePursuit.interactives;

public class Antidote extends Artifact {

    protected final double cureStrength;

    public Antidote(String name, double cureStrength) {
        super(name, ArtifactType.Antidote);
        this.cureStrength = cureStrength;
    }

    public double getCureStrength() {
        return cureStrength;
    }

    @Override
    public String toString() {
        String formattedHealth = String.format("%.2f", cureStrength);
        return name + "(" + formattedHealth + ")" + "\n" +
                "      _____\n" +
                "     `.___,'\n" +
                "      (___)\n" +
                "      <   >\n" +
                "       ) (\n" +
                "      /`-.\\\n" +
                "     /     \\\n" +
                "    / _    _\\\n" +
                "   :,' `-.' `:\n" +
                "   |         |\n" +
                "   :         ;\n" +
                "    \\       /\n" +
                "     `.___.'";
    }
}
