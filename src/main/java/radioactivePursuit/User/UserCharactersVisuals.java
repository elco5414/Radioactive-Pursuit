package radioactivePursuit.User;

import radioactivePursuit.player.Player;

public class UserCharactersVisuals {
    private void displayScientistArt() {
        //fill in with the scientist ASCII character here
        System.out.println(
                "    (ᵔ‿ᵔ)✨\n" +
                "    /|️🔬|\\\n" +
                " 💉/ |  | \\\n" +
                "    /|  |\\\n" +
                "   /_|__|_\\\n" +
                "     /  \\\n"
        );
    }

    public void displayScientist(Player scientist) {
        System.out.println(scientist.getName() + ": " + scientist.getAntidoteCount() + "🧪, " + scientist.getHealth() + "❤️.\n");
        displayScientistArt();
    }
}
