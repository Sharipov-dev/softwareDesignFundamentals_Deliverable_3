package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPot {
    private List<Card> cardPot;

    public CardPot(){
        cardPot = new ArrayList<>();
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
