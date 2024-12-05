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
    public void display_stats(int roundcount, String[] player_names, int[] scores) {
        System.out.println("Current Round: #" + roundcount);
        System.out.println("Players");
        for (int i = 0; i < player_names.length; i++) {
            System.out.println(player_names[i] + " has: " + scores[i]);
            if (scores[i] <= 0) {
                System.out.print(" - Eliminated");
            }
        }
    }
}
