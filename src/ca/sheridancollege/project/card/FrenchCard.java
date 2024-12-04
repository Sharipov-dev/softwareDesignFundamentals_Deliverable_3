package ca.sheridancollege.project.card;
public class FrenchCard extends Card {

    private Suit suit;
    private Value value;
    public FrenchCard(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    @Override
    public String toString() {
        return String.format("Suit: %s, Value: %s", suit, value);
    }
}