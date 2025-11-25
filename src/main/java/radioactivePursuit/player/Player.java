package radioactivePursuit.player;

import radioactivePursuit.interactives.Antidote;
import radioactivePursuit.interactives.Creature;
import radioactivePursuit.interactives.Food;
import radioactivePursuit.planet.Biome;

import java.util.List;

public class Player {
    private static Double health;
    private static List<Antidote> antidoteList;
    private static PlayStrategy playerStrat;

    private static final Double DEFAULT_STARTING_HEALTH = 15.0;

    private Biome currentLocation;

    private Player(PlayStrategy playerStrategy){
        this.health = DEFAULT_STARTING_HEALTH;
        this.playerStrat = playerStrategy;
    }

    public Integer getAntidoteCount(){
        if(antidoteList == null){ return 0;}
        else{return antidoteList.size();}
    }

    public double getHealth(){
        return health;
    }

    public void setHealth(Double newHealth){
        health = newHealth;
    }

    public void setPlayStrategy(PlayStrategy playStrategy){
        playerStrat = playStrategy;
    }

    //current method of action -> not sure how this will look alongside user interaction
    public void doAction(){
        playerStrat.doAction(this);
    }

    private void Builder(){
        //put in builder methods
        //get builder
        //build
    }

    public void eat(Food food) {
        double healthWithFood = this.getHealth() + food.getValue();
        this.setHealth(healthWithFood);
    }

    public boolean canEat() {
        this.getCurrentLocation().hasFood();
    }

    Biome getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Biome biome){
        currentLocation = biome;
    }

    public void fight(Creature creature){
        //TO-DO: implement fight
    }

    public boolean canFight() {
        this.getCurrentLocation().hasRadioActiveCreature();
    }

    public boolean canMove() {

    }

    public void move() {
       //TO-DO: update to the next biome
    }

    public boolean canCure(Creature creature){

    }

    public void cure() {

    }

    public boolean canCollectAntidote() {
        return currentLocation.hasAntidote();
    }

    public void collectAntidote() {
        Antidote antidote = currentLocation.getAntidote();
        antidoteList.add(antidote);
    }

    public void useAntidote(){
        //TO-DO: remove one antidote from the list
    }
}

