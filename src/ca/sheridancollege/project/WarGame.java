package ca.sheridancollege.project;

import java.util.Arrays;
import java.util.ArrayList;

public class WarGame extends Game{

    private int roundCount = 0;
    private boolean replay;

    public WarGame(String name) {
        super(name);
        
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    @Override
    public void play() {
        ArrayList<Player> warplayers = this.getPlayers();
        // If the player is replaying with the same players/settings, then the players do not get reset
        player_meet_and_greet(warplayers);
        
    }

    /**
     * We broke this off into its own method for readability; this is a loop where, ONLY if it is a brand new game, the
     * entire player ArrayList list is filled with new player objects
     * 
     * Otherwise, they are replaying with the same settings as before and the process can be streamlined
     * 
     */
    private void player_meet_and_greet(ArrayList<Player> warplayers) {
        if (this.replay == false) {
            String[] player_names = InitialisePlayers.register_players();
            for (String name : player_names) {
                WarPlayer player = new WarPlayer(name);
                warplayers.add(player);
            }
            System.out.println( "Now starting " + this.getName() + " with " + warplayers.size() + " players!");
            int counter = 1;
            for (Player player : warplayers) {
                // Print list of players
                System.out.println("Player #" + counter + ": " + player.getName());
            }
        } else {
            System.out.println("Welcome back! Players:");
            for (Player player : warplayers) {
                // Print list of players
                System.out.println(player.getName());
            }
        }
    }
    
    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    @Override
    public void declareWinner() {

    }
    public void reset(){
        setRoundCount(0);
    }
    
    /**
     * Getters & Setters below
     * 
     */
    
    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public boolean getReplay() {
        return replay;
    }

    public void setReplay(boolean replay) {
        this.replay = replay;
    }
}