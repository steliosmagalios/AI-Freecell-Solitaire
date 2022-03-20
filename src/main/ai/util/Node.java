package main.ai.util;

import main.freecell.Solitaire;
import main.freecell.move.Move;

import java.util.List;
import java.util.Objects;

/**
 * Representation of a Node that is used by the search algorithms.
 * This class only contains a {@link Solitaire} state and the list of {@link Move}
 * that were performed from the initial state to reach the current state.
 */
public class Node {

    private final Solitaire state;
    private final List<Move> moves;

    public Node(Solitaire state, List<Move> moves) {
        this.state = state;
        this.moves = moves;
    }

    public Solitaire getState() {
        return state;
    }

    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return state.equals(node.state) && moves.equals(node.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, moves);
    }

    @Override
    public String toString() {
        return "Node{" +
                "moves=" + moves +
                '}';
    }
}
