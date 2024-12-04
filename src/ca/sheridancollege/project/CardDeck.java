package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;
import ca.sheridancollege.project.card.FrenchCard;
import ca.sheridancollege.project.card.Suit;
import ca.sheridancollege.project.card.Value;

import java.util.ArrayList;

public class CardDeck extends GroupOfCards{

    private static CardDeck instance;

    private CardDeck() {
        super(56);
        initializeDeck();
    }

    public static CardDeck getInstance(){
        if(instance == null){
            instance = new CardDeck();
        }
        return instance;
    }

    private void initializeDeck() {
        ArrayList<Card> cards = new ArrayList<>();

        //filling deck with all the cards
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new FrenchCard(suit, value));
            }
        }

        setCards(cards);
    }
    private void setCards(ArrayList<Card> cards) {
        super.getCards().clear();
        super.getCards().addAll(cards);
    }
}
