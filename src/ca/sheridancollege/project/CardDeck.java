package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;
import ca.sheridancollege.project.card.FrenchCard;
import ca.sheridancollege.project.card.Suit;
import ca.sheridancollege.project.card.Value;

import java.util.ArrayList;

public class CardDeck extends GroupOfCards{



    private static CardDeck instance;

    private CardDeck() {
        super(52);
        initializeDeck();
    }

    public static CardDeck getInstance(){
        if(instance == null){
            instance = new CardDeck();
        }
        return instance;
    }

    private void initializeDeck() {
        System.out.println("Initializing the deck...");
        ArrayList<Card> cards = new ArrayList<>();

        //filling deck with all the cards
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new FrenchCard(suit, value));
            }
        }

        setCards(cards);
    }
    @Override
    protected void setCards(ArrayList<Card> cards) {
        super.setCards(new ArrayList<>());

        super.getCards().clear();
        super.getCards().addAll(cards);
        super.shuffle();
    }
}
