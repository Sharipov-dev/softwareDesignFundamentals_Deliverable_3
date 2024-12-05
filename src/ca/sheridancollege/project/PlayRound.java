package ca.sheridancollege.project;

import ca.sheridancollege.project.card.Card;
import ca.sheridancollege.project.card.FrenchCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayRound {

    private List<Card> cardsInPlay;
    private CardPot cardPot;

    public PlayRound() {
        this.cardsInPlay = new ArrayList<>();
    }

    //returns false if it is a tie
    //returns true if the winner is determined
    public boolean play(List<Player> players) {
        cardPot = new CardPot(); // Cards that can be won after x number of rounds
        cardsInPlay.clear(); // Cards that are played this round; all cards get added to the pot
        
        ArrayList<WarPlayer> participatingPlayers = new ArrayList<>(); // Only participating players play
        
        for (Player player : players) {
            if (player instanceof WarPlayer) {
                WarPlayer warPlayer = (WarPlayer) player;

                // If player is participating
                if (warPlayer.is_participating() && !warPlayer.getPersonalCards().isEmpty()) {
                    participatingPlayers.add(warPlayer);
                    Card card = warPlayer.getPersonalCards().remove(0);
                    cardsInPlay.add(card);
                    System.out.println(warPlayer.getName() + " plays " + card);
                } else if(!warPlayer.is_participating()) {
                    System.out.println(warPlayer.getName() + " is sitting this round out!");
                }else{
                    System.out.println(warPlayer.getName() + " has no cards left!");
                }
            }
        }

        // Add all played cards to pot
        cardPot.addToPot(cardsInPlay);
        
        // Compare cards to determine who the winner is, if there is one
        WarPlayer winner = compareCards(participatingPlayers);

        // Final comparison
        if (winner != null) {
            winner.getPersonalCards().addAll(cardPot.getCardPot());
            System.out.println(winner.getName() + " wins the round and collects " + cardsInPlay.size() + " cards.");
            cardPot.clearPot();
            return true;
        } else {
            System.out.println("It's a tie! Play one more round to get all the cards.");
            
            for (WarPlayer player : participatingPlayers) {
                player.setIs_participating(false);
                System.out.println(player.getName() + " has lost the tie and will sit the next hand out.");
            }
            return false;
        }
    }

    private WarPlayer compareCards(ArrayList<WarPlayer> players) {
        FrenchCard highestCard = null;
        WarPlayer winner = null;


        for (WarPlayer player : players) {
            if (player.is_participating() && !player.getPersonalCards().isEmpty()) {
                FrenchCard card = (FrenchCard) cardsInPlay.get(players.indexOf(player));


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