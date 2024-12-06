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
    private List<Player> lostPlayers;

    public WarGame(String name) {
        super(name);
        playRound = new PlayRound();
        lostPlayers = new ArrayList<>();
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
        
        // Assign starting cards to players
        setHands(warPlayers);
        
        // Grant the User player 5 Wait uses
        int total_waits = 5;
        WarPlayer user_player = (WarPlayer) warPlayers.get(0);
        user_player.setWait_counter(total_waits);
        
        // Main Menu
        Scanner sc = new Scanner(System.in);
        OUTER:
        while (true) {
            System.out.println("Game started, choose a valid option: ");
            // Prints menu options
            display_menu_options(warPlayers, user_player);
            int choice;
            // Input validation so that the game doesn't crash
            if (!sc.hasNextInt()) {
                choice = 0;
                sc.next();
            } else {
                choice = sc.nextInt();
            }
            // 1 = Play, 2 = Wait, 3 = Status, 4 = Forfeit
            switch (choice) {
                case 1:
                    playRound.play(warPlayers);
                    break;
                case 2:
                    if (returnActiveCount(warPlayers) >= 3) {
                        user_player.setIs_participating(false);
                        int waits_remaining = user_player.getWait_counter() - 1;
                        user_player.setWait_counter(waits_remaining);
                        boolean notTied = playRound.play(warPlayers);
                        if (!user_player.getPersonalCards().isEmpty() && notTied == true) {
                            user_player.setIs_participating(true);
                        }
                    }
                    break;
                case 3:
                    String[] name_list = new String[warPlayers.size()];
                    int[] score_list = new int[warPlayers.size()];
                    for (int i = 0; i < warPlayers.size(); i++) {
                        WarPlayer player_instance = (WarPlayer) warPlayers.get(i);
                        ArrayList<Card> personalCards = player_instance.getPersonalCards();
                        name_list[i] = player_instance.getName();
                        score_list[i] = personalCards.size(); 
                    }
                    Status.display_stats(roundCount, name_list, score_list);
                    break;
                case 4:
                    reset();
                    break OUTER;
                default:
                    System.out.println("Unknown choice; try again.");
                    break;
            }
            // A winner is only declared once only a single player has cards left
            discoverWinner();
            declareWinner();
            
            roundCount++;
        }
        System.out.println("Finishing the game...");

    }

    /**
     * This is a loop where, ONLY if it is a brand new game, the entire player ArrayList list is filled with new player 
     * objects; otherwise, they are replaying with the same settings as before and the process can be streamlined
     */
    private void player_meet_and_greet(ArrayList<Player> warplayers) {
        if (this.replay == false) {
            String[] player_names = InitialisePlayers.register_players();
            // Basically a small factory, iterating through the returned player_names list to assign names to players
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
    
    /**
     * Sets each player's hand at the start of a new game
     */
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
     * Displays menu options
     */
    private void display_menu_options (ArrayList<Player> warPlayers, WarPlayer user_player) {
        System.out.println("1. Play");
        System.out.print("2. Wait (Skip round)");
        
        // Suffix lines depending on various conditions
        if (returnActiveCount(warPlayers) < 3) {
            System.out.println(" - N/A (requires 3+ active players)");
        } else if (user_player.getWait_counter() == 0) {
            System.out.println(" - N/A (you are out of waits)");
        } else {
            System.out.println(": " + user_player.getWait_counter() + " uses left!");
        }
        
        System.out.println("3. Status");
        System.out.println("4. Forfeit");
        
        System.out.print("Your choice: ");
    }
    
    private int returnActiveCount(ArrayList<Player> players) {
        int livingPlayers = 0;
        for (Player player : players) {
            if (player instanceof WarPlayer) { 
                WarPlayer warPlayer = (WarPlayer) player;
                if (!warPlayer.getPersonalCards().isEmpty() || !warPlayer.is_participating()) {
                    livingPlayers++;
                }
            }
        }
    return livingPlayers;
    }
       
    /**
     * Used to determine if there is a winner.
     */
    public void discoverWinner() {
        List<Player> toRemove = new ArrayList<>();
        for (Player player: getPlayers()) {
            WarPlayer warPlayer = (WarPlayer) player;
            if (warPlayer.getPersonalCards().isEmpty()) {
                lostPlayers.add(warPlayer);
                toRemove.add(player);
            }
        }
        getPlayers().removeAll(toRemove);
    }
    
    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    @Override
    public void declareWinner() {
        ArrayList<Player> warPlayers = this.getPlayers();
        WarPlayer user = (WarPlayer) warPlayers.get(0);
            if (!getPlayers().contains(user)) {
                System.out.println("You lost!");
                reset();
            } else if (lostPlayers.size() == warPlayers.size() - 1) {
                System.out.println("Congratulations, You won!");
                reset();
            } else {
            }
    }
    
    /**
     * Asks the player if they want to play again with the same player settings
     * 
     * Called after a winner is announced, or else if the player forfeits a game
     */
    public void reset(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");
        String choice = sc.nextLine();
        if (choice.toLowerCase().equals("y")) {
            setRoundCount(0);
            setReplay(true);
        }
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