package main.freecell.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Representation of stack of cards in freecell solitaire.
 */
public class CardStack implements CardHolder, Iterable<Card> {

    private final Stack<Card> stack;

    public CardStack(Stack<Card> stack) {
        this.stack = stack;
    }

    // Copy constructor
    public CardStack(CardStack cardStack) {
        this.stack = ((Stack<Card>) cardStack.stack.clone());
    }

    public int size() {
        return this.stack.size();
    }

    @Override
    public void insert(Card c) {
        stack.push(c);
    }

    @Override
    public Card peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    @Override
    public Card remove() {
        return stack.isEmpty() ? null : stack.pop();
    }

    @Override
    public boolean canInsert(Card c) {
        if (c == null) return false;

        if (stack.isEmpty()) return true;

        Card top = stack.peek();
        return top.getSuit().isOppositeColor(c.getSuit()) && top.getValue() == c.getValue() + 1;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isUsable() {
        return !isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return stack.iterator();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public String getEncodedCard() {
        return stack.peek().getSuit().getCode() + stack.peek().getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardStack cards = (CardStack) o;
        return stack.equals(cards.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stack);
    }

    public Card get(int index) {
        return stack.get(index);
    }

    public Stream<Card> getStream() {
        return stack.stream();
    }

}
