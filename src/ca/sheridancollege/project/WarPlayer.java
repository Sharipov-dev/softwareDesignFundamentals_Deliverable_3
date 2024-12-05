package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;

import java.util.ArrayList;


/**
 *
 * @author Salan
 */
public class WarPlayer extends Player {

    private ArrayList<Card> personalCards;
    private boolean is_participating;
    private int wait_counter; 

    public WarPlayer(String name) {
        super(name);
        personalCards = new ArrayList<>();
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
    public boolean is_participating() {
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

    /**
     * @return the wait_counter
     */
    public int getWait_counter() {
        return wait_counter;
    }

    /**
     * @param wait_counter the wait_counter to set
     */
    public void setWait_counter(int wait_counter) {
        this.wait_counter = wait_counter;
    }
}