package radioactivePursuit.User;

import radioactivePursuit.player.Player;

public class UserCharactersVisuals {
    private void displayScientistArt() {
        //fill in with the scientist ASCII character here
    }

    public void displayScientist(Player scientist) {
        System.out.println(scientist.getName() + " has " + scientist.getAntidoteCount() + "antidotes, and " + scientist.getHealth() + " health.\n")
        displayScientistArt();
    }
}
