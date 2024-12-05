package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This displays the running status of the game, when requested by the player between rounds.
 * 
 * @author Salan
 */
public class Status {
    public static void display_stats(int roundcount, String[] player_names, int[] scores) {
        int top_score = get_top_score(scores);
        System.out.println(top_score);
        
        System.out.println("Current Round: #" + roundcount);
        
        print_scoreboard(player_names, scores, top_score);
    }
    
    // Get top score so that the winning player(s) can be described as such
    private static int get_top_score(int[] scores) {
        int top_score = 0;
        for (int i = 0; i < scores.length; i++) {
            if (top_score <= scores[i]) {
                top_score = scores[i];
            }
        }
        return top_score;
    }
    
    private static void print_scoreboard(String[] player_names, int[] scores, int top_score) {
        for (int i = 0; i < player_names.length; i++) {
            System.out.print("Player " + player_names[i] + " has: " + scores[i]);
            if (scores[i] <= 0) {
                System.out.print(" - Eliminated");
            } else if (scores[i] >= top_score) {
                System.out.print(" - Winning");
            }
            System.out.println();
        }
    }
}
