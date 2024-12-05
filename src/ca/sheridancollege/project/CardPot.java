package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPot {
    private static CardPot instance = null;
    private List<Card> cardPot;
    
    public CardPot(){
        cardPot = new ArrayList<>();
    }

    // Applied Singleton Design Pattern principle; we only ever need one pot as long as the game is running
    public static CardPot getInstance() {
        if (instance == null) {
            instance = new CardPot();
        }
        return instance;
    }
    
    public void addToPot(List<Card> cards){
        cardPot.addAll(cards);
    }

    public void clearPot(){
        cardPot = new ArrayList<>();
    }

    public List<Card> getCardPot() {
        return Collections.unmodifiableList(cardPot);
    }
}
