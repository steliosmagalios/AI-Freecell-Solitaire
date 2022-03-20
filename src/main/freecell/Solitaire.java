package main.freecell;

import main.freecell.move.Move;
import main.freecell.util.CardStack;
import main.freecell.util.Foundation;
import main.freecell.util.Freecell;
import main.freecell.util.Suit;

import java.util.Arrays;

/**
 * The Freecell Solitaire game
 */
public class Solitaire {

    // The number of freecells
    public static final int NUMBER_OF_FREECELLS = 4;

    private CardStack[] stacks;
    private Freecell[] freecells;
    private Foundation[] foundations;

    public Solitaire(CardStack[] stacks) {
        this.stacks = stacks;
        initFreecells();
        initFoundations();
    }

    // Copy constructor
    public Solitaire(Solitaire solitaire) {
        this.stacks = new CardStack[solitaire.stacks.length];
        for (int i = 0; i < solitaire.stacks.length; i++) {
            this.stacks[i] = new CardStack(solitaire.stacks[i]);
        }

        this.freecells = new Freecell[solitaire.freecells.length];
        for (int i = 0; i < solitaire.freecells.length; i++) {
            this.freecells[i] = new Freecell(solitaire.freecells[i]);
        }

        this.foundations = new Foundation[solitaire.foundations.length];
        for (int i = 0; i < solitaire.freecells.length; i++) {
            this.foundations[i] = new Foundation(solitaire.foundations[i]);
        }
    }


    /**
     * Performs a move on the board.
     * @param move The {@link Move} provided.
     */
    public void move(Move move) {
        move.performMove(this, false);
    }

    /**
     * Initializes the freecells of the game
     */
    private void initFreecells() {
        this.freecells = new Freecell[NUMBER_OF_FREECELLS];
        for (int i = 0; i < this.freecells.length; i++) {
            this.freecells[i] = new Freecell();
        }
    }

    /**
     * Initializes the foundations of the game
     */
    private void initFoundations() {
        this.foundations = new Foundation[Suit.values().length];
        for (int i = 0; i < Suit.values().length; i++) {
            this.foundations[i] = new Foundation(Suit.values()[i]);
        }
    }

    /**
     * Checks if the game is finished (all the cards are in the foundations)
     * @return Whether the game is completed.
     */
    public boolean isCompleted() {
        return Arrays.stream(foundations).map(Foundation::isFull).reduce((b1, b2) -> b1 && b2).orElse(false);
    }

    public CardStack[] getStacks() {
        return stacks;
    }

    public Freecell[] getFreecells() {
        return freecells;
    }

    public Foundation[] getFoundations() {
        return foundations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solitaire solitaire = (Solitaire) o;
        return Arrays.equals(stacks, solitaire.stacks) && Arrays.equals(freecells, solitaire.freecells) && Arrays.equals(foundations, solitaire.foundations);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(stacks);
        result = 31 * result + Arrays.hashCode(freecells);
        result = 31 * result + Arrays.hashCode(foundations);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("FREECELLS:").append('\n');
        builder.append(Arrays.toString(this.freecells)).append('\n').append('\n');

        builder.append("FOUNDATIONS:").append('\n');
        builder.append(Arrays.toString(this.foundations)).append('\n').append('\n');

        builder.append("STACKS:").append('\n');
        Arrays.stream(this.stacks).forEach(s -> builder.append(s).append('\n'));

        return builder.toString();
    }
}
