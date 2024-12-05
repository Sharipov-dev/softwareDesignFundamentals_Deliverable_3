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
        cardPot = CardPot.getInstance(); // Cards that can be won after x number of rounds
        cardsInPlay.clear(); // Cards that are played this round; all cards get added to the pot
        
        ArrayList<WarPlayer> participatingPlayers = new ArrayList<>(); // Only participating players play
        
        makeParticipantList(players, participatingPlayers);

        // Add all played cards to pot
        cardPot.addToPot(cardsInPlay);
        
        // Compare cards to determine who the winner is, if there is one
        ArrayList<WarPlayer> tiedPlayers = new ArrayList<>();
        WarPlayer winner = findWinner(participatingPlayers, tiedPlayers);

        boolean roundWon = checkWinner(winner, participatingPlayers, tiedPlayers);
        
        return roundWon;
    }

    // This method tries to find a winner by comparing cards played; renamed for clarity of purpose
    private WarPlayer findWinner(ArrayList<WarPlayer> players, ArrayList<WarPlayer> tiedPlayers) {
        FrenchCard highestCard = null;
        WarPlayer winner = null;

        for (WarPlayer player : players) {
            if (player.is_participating() && !player.getPersonalCards().isEmpty()) {
                FrenchCard card = (FrenchCard) cardsInPlay.get(players.indexOf(player));

                // If currently checked card is the highest
                if (highestCard == null || card.getValue().getRank() > highestCard.getValue().getRank()) {
                    highestCard = card;
                    tiedPlayers.clear();
                    tiedPlayers.add(player); // Add to list, just in case the next card checked is tied
                    winner = player;
                } 
                // If currently checked card is equal to the highest, they are tied
                else if (card.getValue().getRank() == highestCard.getValue().getRank()) {
                    tiedPlayers.add(player);
                    winner = null;
                }
            }
        }

        return winner;
    }
    
    private ArrayList<WarPlayer> makeParticipantList(List<Player> players, ArrayList<WarPlayer> participatingPlayers) {
            for (Player player : players) {
            if (player instanceof WarPlayer) {
                WarPlayer warPlayer = (WarPlayer) player;

                // If player is participating
                if (warPlayer.is_participating() && !warPlayer.getPersonalCards().isEmpty()) {
                    participatingPlayers.add(warPlayer);
                    Card card = warPlayer.getPersonalCards().remove(0);
                    cardsInPlay.add(card);
                    System.out.println(warPlayer.getName() + " plays " + card);
                } 
                // If player is not participating
                else if(!warPlayer.is_participating()) {
                    System.out.println(warPlayer.getName() + " is sitting this round out!");
                }
                // If player has been eliminated
                else{
                    System.out.println(warPlayer.getName() + " has no cards left!");
                }
            }
        }
        return participatingPlayers;
    }
    
    private boolean checkWinner(WarPlayer winner, ArrayList<WarPlayer> participatingPlayers, 
            ArrayList<WarPlayer> tiedPlayers) {
        if (winner != null) {
            // One person claims the pot
            winner.getPersonalCards().addAll(cardPot.getCardPot());
            System.out.println(winner.getName() + " wins the round and collects " + cardPot.getCardPot().size() + " cards.");
            cardPot.clearPot();
            for (WarPlayer player : participatingPlayers) {
                player.setIs_participating(true);
            }
            return true;
        } else {
            // Nobody won; pot remains and all non-tied parties are flagged as not participating
            System.out.println("It's a tie! Play one more round to get all the cards.");
            for (WarPlayer player : participatingPlayers) {
                if (!tiedPlayers.contains(player)) {
                    player.setIs_participating(false);
                    System.out.println(player.getName() + " has lost the tie and will sit the next hand out.");
                }
            }
            return false;
        }
    }
}