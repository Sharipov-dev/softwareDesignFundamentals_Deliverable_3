package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarGame extends Game{

    private int roundCount = 0;
    private boolean replay;
    private final PlayRound playRound;

    public WarGame(String name) {
        super(name);
        playRound = new PlayRound();
        
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    @Override
    public void play() {
        ArrayList<Player> warPlayers = this.getPlayers();
        // If the player is replaying with the same players/settings, then the players do not get reset
        player_meet_and_greet(warPlayers);
        System.out.println(warPlayers);
        Scanner sc = new Scanner(System.in);
        System.out.println("Game started, choose options: ");
        setHands(warPlayers);
        while(true){
            System.out.println("1. Play");
            System.out.println("2. Wait (Skip the round)");
            System.out.println("3. Status");

            int choice = sc.nextInt();
            if(choice == 1){
                playRound.play(warPlayers);
            } else if(choice == 2){
                WarPlayer warPlayer = (WarPlayer) warPlayers.get(0);
                warPlayer.setIs_participating(false);
                playRound.play(warPlayers);
            }
            else if(choice == 4){
                break;
            }
        }
        System.out.println("Finishing the game...");

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
                counter++;
            }
        } else {
            System.out.println("Welcome back! Players:");
            for (Player player : warplayers) {
                // Print list of players
                System.out.println(player.getName());
            }
        }
    }
    private void setHands(ArrayList<Player> players) {
        int count = 0;
        for(List<Card> playerDeck :  SetHand.getInstance().fetchCards(players.size())){
            WarPlayer warPlayer = (WarPlayer) players.get(count);
            warPlayer.setPersonalCards((ArrayList<Card>) playerDeck);
            warPlayer.setIs_participating(true);
            count++;
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