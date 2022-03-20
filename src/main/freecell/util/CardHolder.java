package main.freecell.util;

/**
 * A {@link CardHolder} is something that can store one or more cards (ie a stack, a freecell or a foundation).
 */
public interface CardHolder {

    /**
     * Inserts a card in the holder.
     * @param c The {@link Card} inserted.
     */
    void insert(Card c);

    /**
     * Displays the card at the "top" of the holder, without removing it.
     * @return The {@link Card} at the "top" of the holder.
     */
    Card peek();

    /**
     * Removes the "top" card from the holder.
     * @return The {@link Card} at the "top" of the holder.
     */
    Card remove();

    /**
     * Checks if the card provided can be inserted in the holder
     * @param c The {@link Card} to be checked.
     * @return If the card can be inserted.
     */
    boolean canInsert(Card c);

    /**
     * @return If the holder is empty.
     */
    boolean isEmpty();

    /**
     * @return If the holder is full.
     */
    boolean isFull();

    /**
     * @return If the holder can be used (to make a move).
     */
    boolean isUsable();

    /**
     * @return The encoded version of the "top" card of the holder.
     */
    String getEncodedCard();

}
