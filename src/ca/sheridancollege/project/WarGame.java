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
        System.out.println("Game started, choose options: ");
        OUTER:
        while (true) {
            // Prints menu options
            display_menu_options(warPlayers, user_player);

            int choice = sc.nextInt();
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
                    break OUTER;
                default:
                    System.out.println("Unknown choice; try again.");
                    break;
            }
            WarPlayer user = (WarPlayer) warPlayers.get(0);
            declareWinner();
            if (!getPlayers().contains(user)) {
                System.out.println("You lost!");
                break ;
            } else if (lostPlayers.size() == warPlayers.size() - 1) {
                System.out.println("Congratulations, You won!");
                break ;
            } else {
                System.out.println("No winners in this round");
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
    
    private void display_menu_options (ArrayList<Player> warPlayers, WarPlayer user_player) {
        System.out.println("1. Play");
        System.out.print("2. Wait (Skip the round)");
        
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
     * When the game is over, use this method to declare and display a winning player.
     */
    @Override
    public void declareWinner() {
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