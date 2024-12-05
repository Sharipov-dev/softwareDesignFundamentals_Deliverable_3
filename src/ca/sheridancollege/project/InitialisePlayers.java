package ca.sheridancollege.project;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is used to name players and ensure that all names are unique prior to being assigned
 *
 * @author Salan
 */
public class InitialisePlayers {

    private static String[] default_names = {"You", "Arthur", "Sean", "Andrew"};

    /**
     * This is used to create a name for a particular player, to be returned to WarGame and added to an Array of names
     * If a given name is left blank, then it assigns a default name to that player
     * @return 
     */
    
    public static String[] register_players() {
        // Initialise players, starting with the main user and then the total # of other players
        int player_count = 1;
        String user_player = name_player(1);
        player_count += choose_player_count();
        
        // Naming other players
        String[] player_names = new String[player_count];
        player_names[0] = user_player;
        
        for (int i = 1; i < player_names.length; i++) {
            String other_player = name_player(i + 1);
            player_names[i] = other_player;
        }
        id_duplicates(player_names);
        System.out.println(Arrays.toString(player_names));
        return player_names;
    }
    
    private static String name_player(int player_count) {
        Scanner k = new Scanner(System.in);
        System.out.print("Enter a name for player #" + player_count + ": ");
        String player_name = k.nextLine();

        // Assign default name, if necessary
        if ("".equals(player_name)) {
            player_name = default_names[player_count - 1];
        }
        return player_name;
    }

    /**
     * This iterates through the array of assigned player names, appending numbers to the end of any duplicates;
     * the whole array is then returned and used to name all player objects simultaneously within WarGame's logic
     * @param existing_players
     * @return 
     */
    private static void id_duplicates(String[] existing_players) {
        // The name array is cloned to prevent checking for duplicates of edited names
        String[] original_names = existing_players.clone();

        // checked_name matches whatever name is stored at [i] index of existing_players
        for (int i = 0; i < existing_players.length; i++) {
            String checked_name = original_names[i];

            int count = 1;

            // Every name in original_names is checked to see if checked_name matches it;
            // If so, then a number is appended to it and the name at existing_players[i] changes
            for (int j = 0; j < i; j++) {
                if (checked_name.equals(existing_players[j])) {
                    checked_name = original_names[i] + count;
                    count++;
                    j = -1;
                }
            }
            existing_players[i] = checked_name;
        }
    }

    /**
     * This sets the game's player_count for use in related methods, filtering out strings invalid numbers;
     * the Players ArrayList in the actual game logic will get this total if it is needed elsewhere
     */
    private static int choose_player_count() {
        Scanner k = new Scanner(System.in);
        int player_count = 0;
        String failure_string = "Invalid input. Please enter a number between 1 and 3.";

        // Loop until a valid player count is entered
        do {
            System.out.print("How many people will you be playing with (1-3)? ");

            // Check if the input is a valid integer
            if (k.hasNextInt()) { 
                player_count = k.nextInt(); // It may get set to an invalid integer, but it'll be corrected on loop

                if (player_count < 1 || player_count > 3) {
                    System.out.println(failure_string);
                }
            } else {
                System.out.println(failure_string);
                k.next(); // Discard invalid input
            }
        } while (player_count < 1 || player_count > 3);

        return player_count;
    }
}