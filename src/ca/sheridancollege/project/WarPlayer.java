package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;
import ca.sheridancollege.project.card.FrenchCard;

import java.util.ArrayList;


/**
 *
 * @author Salan
 */
public class WarPlayer extends Player {

    private ArrayList<FrenchCard> personalCards;
    private boolean is_participating;

    public WarPlayer(String name) {
        super(name);
        personalCards = new ArrayList<>();
    }


    /**
     * @return the personalCards
     */
    public ArrayList<FrenchCard> getPersonalCards() {
        return personalCards;
    }

    /**
     * @param personalCards the personalCards to set
     */
    public void setPersonalCards(ArrayList<FrenchCard> personalCards) {
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