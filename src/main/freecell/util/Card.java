package main.freecell.util;

import java.util.Objects;

/**
 * A representation of a card from a deck of cards.
 */
public class Card {

    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Card(Card card) {
        this.suit = card.suit;
        this.value = card.value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s%d", this.suit, this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }
}
