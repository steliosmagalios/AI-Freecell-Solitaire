package main.freecell.util;

import main.Main;

import java.util.Objects;

/**
 * Representation of a foundation in freecell solitaire
 */
public class Foundation implements CardHolder {

    private final Suit suit;
    private int topValue;

    public Foundation(Suit suit) {
        this.suit = suit;
        this.topValue = -1;
    }

    // Copy constructor
    public Foundation(Foundation foundation) {
        this.suit = foundation.suit;
        this.topValue = foundation.topValue;
    }

    public int getCardCount() {
        return topValue + 1;
    }

    @Override
    public void insert(Card c) {
        this.topValue = c.getValue();
    }

    @Override
    public Card peek() {
        return new Card(suit, topValue);
    }

    @Override
    public Card remove() {
        return isEmpty() ? null : new Card(suit, topValue--);
    }

    @Override
    public boolean canInsert(Card c) {
        if (c == null) return false;
        return this.suit == c.getSuit() && c.getValue() == this.topValue + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.topValue == -1;
    }

    @Override
    public boolean isFull() {
        return this.topValue + 1 == Main.DECK_SIZE;
    }

    @Override
    public boolean isUsable() {
        return !isEmpty();
    }

    @Override
    public String getEncodedCard() {
        return suit.getCode() + topValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foundation that = (Foundation) o;
        return topValue == that.topValue && suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, topValue);
    }

    @Override
    public String toString() {
        return isEmpty() ? suit.toString() : String.format("%s%d", suit, topValue);
    }
}
