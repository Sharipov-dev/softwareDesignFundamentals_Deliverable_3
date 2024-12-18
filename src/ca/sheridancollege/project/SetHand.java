package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;

import java.util.ArrayList;
import java.util.List;

public class SetHand {

    private final CardDeck cards;
    private static SetHand instance;
    private SetHand() {
        cards = CardDeck.getInstance();
    }
    public static SetHand getInstance() {
        if (instance == null){
            instance = new SetHand();
        }
        return instance;
    }

    public List<List<Card>> fetchCards(int players) {
        cards.initializeDeck();
        if (players <= 0) {
            throw new IllegalArgumentException("Number of players must be greater than 0");
        }

        int cardNumberPerPlayer = cards.getSize() / players;
        if (cardNumberPerPlayer == 0) {
            throw new IllegalArgumentException("Not enough cards to deal to each player.");
        }

        List<List<Card>> dealtCards = new ArrayList<>();

        for (int i = 0; i < players; i++) {
            System.out.println(String.format("Setting hand for " + (i + 1) + " player", i));
            List<Card> playerHand = new ArrayList<>(cards.getCards().subList(0, cardNumberPerPlayer));
            cards.getCards().subList(0, cardNumberPerPlayer).clear();
            dealtCards.add(playerHand);
        }

        return dealtCards;
    }
}