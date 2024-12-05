package ca.sheridancollege.project;

import ca.sheridancollege.project.card.FrenchCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayRound {
    private List<FrenchCard> cardsInPlay;
    private CardPot cardPot;

    public PlayRound() {
        this.cardsInPlay = new ArrayList<>();
    }

    //returns false if it is a tie
    //returns true if the winner is determined
    public boolean play(List<WarPlayer> players) {
        cardPot = new CardPot();
        cardsInPlay.clear();
        for (WarPlayer player : players) {
            if (player.is_participating() && !player.getPersonalCards().isEmpty()) {
                FrenchCard card = player.getPersonalCards().remove(0);
                cardsInPlay.add(card);
                cardPot.addToPot(List.of(card));
                System.out.println(player.getName() + " plays " + card);
            } else {
                System.out.println(player.getName() + " has no cards left!");
            }
        }

        WarPlayer winner = compareCards(players);
        cardPot.clearPot();

        if (winner != null) {
            winner.getPersonalCards().addAll(cardsInPlay);
            System.out.println(winner.getName() + " wins the round and collects " + cardsInPlay.size() + " cards.");
            cardsInPlay.clear();
            return true;
        } else {
            System.out.println("It's a tie! Play one more round to get all the cards.");
            return false;
        }
    }

    private WarPlayer compareCards(List<WarPlayer> players) {
        Map<WarPlayer, FrenchCard> playedCards = new HashMap<>();
        FrenchCard highestCard = null;
        WarPlayer winner = null;

        for (WarPlayer player : players) {
            if (player.is_participating() && !player.getPersonalCards().isEmpty()) {
                FrenchCard card = cardsInPlay.get(players.indexOf(player));
                playedCards.put(player, card);

                if (highestCard == null || card.getValue().getRank() > highestCard.getValue().getRank()) {
                    highestCard = card;
                    winner = player;
                } else if (card.getValue().getRank() == highestCard.getValue().getRank()) {
                    winner = null;
                }
            }
        }

        return winner;
    }
}