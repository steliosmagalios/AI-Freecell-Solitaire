package main.freecell.move;

import main.freecell.Solitaire;
import main.freecell.util.CardHolder;

import java.util.Objects;

/**
 * A representation of a move that can be made in freecell solitaire.
 */
public class Move {

    private final MoveComponent source;
    private final MoveComponent target;

    public Move(MoveComponent source, MoveComponent target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Performs a move in the provided instance. Moves can also be "simulated".
     * Simulating a move means checking all the conditions of whether the move is valid, but without changing the state
     * of the instance provided.
     *
     * @param instance The instance of {@link Solitaire} that the move will be executed
     * @param simulate Whether the move will be simulated.
     * @return Whether the move was successful.
     */
    public boolean performMove(Solitaire instance, boolean simulate) {
        CardHolder source = this.source.get(instance);
        CardHolder target = this.target.get(instance);

        // If the source or the target are null, the move fails
        if (source == null || target == null) return false;

        // If the source is unusable (doesn't hold a card), the move fails
        if (!source.isUsable()) return false;

        if (target.canInsert(source.peek())) {
            if (!simulate)
                target.insert(source.remove());
            return true;
        }

        return false;
    }

    public MoveComponent getSource() {
        return source;
    }

    public MoveComponent getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return source == move.source && target == move.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return "Move{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }
}
