/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.ArrayList;


/**
 *
 * @author Salan
 */
public class WarPlayer extends Player {
    
    private ArrayList<Card> personalCards = new ArrayList<Card>();
    private boolean is_participating;

    public WarPlayer(String name) {
        super(name);
    }
    

        /**
     * @return the personalCards
     */
    public ArrayList<Card> getPersonalCards() {
        return personalCards;
    }

    /**
     * @param personalCards the personalCards to set
     */
    public void setPersonalCards(ArrayList<Card> personalCards) {
        this.personalCards = personalCards;
    }

    /**
     * @return the is_participating
     */
    public boolean isIs_participating() {
        return is_participating;
    }

    /**
     * @param is_participating the is_participating to set
     */
    public void setIs_participating(boolean is_participating) {
        this.is_participating = is_participating;
    }
    
    @Override
    public void play() {
        
    };
}


