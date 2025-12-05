package radioactivePursuit.planet;

public class CityBiome extends Biome {

    protected CityBiome(String name) {
        super(name, BiomeType.City);
    }

    @Override
    public void displayBiome() {
        System.out.println("~~~~~" + super.getName() + "~~~~~\n");
        System.out.println(
                "                                  _._\n" +
                        "                               .-~ | ~-.\n" +
                        "                               |   |   |\n" +
                        "                               |  _:_  |                    .-:~--.._\n" +
                        "                             .-\"~~ | ~~\"-.                .~  |      |\n" +
                        "            _.-~:.           |     |     |                |   |      |\n" +
                        "           |    | `.         |     |     |                |   |      |\n" +
                        "  _..--~:-.|    |  |         |     |     |                |   |      |\n" +
                        " |      |  ~.   |  |         |  __.:.__  |                |   |      |\n" +
                        " |      |   |   |  |       .-\"~~   |   ~~\"-.              |   |      |\n" +
                        " |      |   |  _|.--~:-.   |       |       |         .:~-.|   |      |\n" +
                        " |      A   | |      |  ~. |       |   _.-:~--._   .' |   |   |      |\n" +
                        " |      M   | |      |   | |       |  |   |     |  |  |   |   |      |\n" +
                        " |      C   | |      |   | |       |  |   |     |  |  |   |   |      |\n" +
                        " |      |   | |      |   | |       |  |   |     |  |  |   |   |      |\n" +
                        " |      9   | |      |   | |       |  |   |     |  |  |   |   |      |\n" +
                        " |      9   | |      |   | |       |  |   |     |  |  |   |   |      |\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        );

    }
}
