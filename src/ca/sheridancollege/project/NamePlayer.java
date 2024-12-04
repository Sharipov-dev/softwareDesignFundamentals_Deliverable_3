/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Scanner;

/**
 * This class is used to name players and ensure that all names are unique prior to being assigned
 * 
 * @author Salan
 */
public class NamePlayer {
    
    private String[] default_cpus = {"You", "Arthur", "Sean", "Andrew"};
    
    /** 
     * This is used to create a name for a particular player, to be returned to WarGame and added to an Array of names
     * If a given name is left blank, then it assigns a default name to that player
     */
    public String name_player(int player_count) {
        Scanner k = new Scanner(System.in);
        System.out.print("Enter a name for player #" + player_count);
        String player_name = k.nextLine();
        
        // Assign default name, if necessary
        if ("".equals(player_name)) {
            player_name = default_cpus[player_count - 1];
        }
        
        // Wrapup
        k.close();
        return player_name;
    }
        
    /**  
     * This iterates through the array of assigned player names, appending numbers to the end of any duplicates; 
     * the whole array is then returned and used to name all player objects simultaneously within WarGame's logic
     */
    public String[] id_duplicates(String[] existing_players) {
        // The name array is cloned to prevent checking for duplicates of edited names
        String[] original_names = existing_players.clone();
        
        // checked_name matches whatever name is stored at [i] index of existing_players
        for (int i = 0; i < existing_players.length; i++) {
            String checked_name = existing_players[i];
        
            int count = 1;
            
            // Every name in original_names is checked to see if checked_name matches it;
            // If so, then a number is appended to it and the name at existing_players[i] changes
            for (int j = 0; j < i; j++) {
                if (checked_name.equals(original_names[j])) {
                    checked_name = existing_players[i] + count;
                    count++;
                    j = -1;
                }
            }
            existing_players[i] = checked_name;
        }
        
        return existing_players;
    }   
    
}
