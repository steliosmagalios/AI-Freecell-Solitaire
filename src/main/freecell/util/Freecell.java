package main.freecell.util;

import java.util.Objects;

/**
 * Representation of a freecell in freecell solitaire
 */
public class Freecell implements CardHolder {

    private Card card;

    public Freecell() {
        this.card = null;
    }

    // Copy constructor
    public Freecell(Freecell freecell) {
        this.card = freecell.card == null ? null : new Card(freecell.card);
    }

    @Override
    public void insert(Card c) {
        this.card = c;
    }

    @Override
    public Card peek() {
        return this.card;
    }

    @Override
    public Card remove() {
        Card c = this.card;
        this.card = null;
        return c;
    }

    @Override
    public boolean canInsert(Card c) {
        return isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return this.card == null;
    }

    @Override
    public boolean isFull() {
        return !isEmpty();
    }

    @Override
    public boolean isUsable() {
        return !isEmpty();
    }

    @Override
    public String getEncodedCard() {
        return card.getSuit().getCode() + card.getValue();
    }

    @Override
    public String toString() {
        return card == null ? "  " : card.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Freecell freecell = (Freecell) o;
        return Objects.equals(card, freecell.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card);
    }
}
