package ca.sheridancollege.project;

public class WarGame extends Game{

    private int roundCount;

    public WarGame(String name) {
        super(name);
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    @Override
    public void play() {

    }

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    @Override
    public void declareWinner() {

    }
    public void reset(){

    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }
}
