package main.ai.algorithms;

import main.ai.util.MoveGenerator;
import main.ai.util.Node;
import main.freecell.Solitaire;
import main.freecell.move.Move;

import java.util.*;

public abstract class BlindSearchAlgorithm  extends Algorithm {

    public BlindSearchAlgorithm(Solitaire initialState) {
        super(initialState);
    }

    /**
     * Adds a node to the search frontier.
     * @param node The {@link Node} object that will be added to the frontier.
     */
    protected abstract void insertToFrontier(Node node);

    /**
     * Removes the first node from the frontier.
     * @return The {@link Node} object from the start of the frontier.
     */
    protected abstract Node removeFromFrontier();

    /**
     * @return If the frontier is empty.
     */
    protected abstract boolean isFrontierEmpty();

    /**
     * If this method returns true, the {@link BlindSearchAlgorithm#search()} method should not
     * search a node, if it's state is in the visited map.
     * @return Whether the {@link BlindSearchAlgorithm#search()} method checks the same state again.
     */
    protected boolean removeVisitedStates() {
        return true;
    }

    @Override
    protected List<Move> search() {
        Set<Solitaire> visited = new HashSet<>();

        // Insert the first node in the frontier
        insertToFrontier(new Node(initialState, new ArrayList<>()));

        while (!isFrontierEmpty()) {
            // Remove the first node of the frontier
            // Which node if "first" is decided by the algorithms implementing this class
            Node curr = removeFromFrontier();

            // Check if duplicate states will be checked
            if (removeVisitedStates()) {
                // Check if the node was visited before
                // If it was not visited, add it to the visited map
                if (visited.contains(curr.getState()))
                    continue;
                visited.add(curr.getState());
            }

            // Check if the current state completes the game
            if (curr.getState().isCompleted())
                return curr.getMoves();

            // Generate all available moves from this state and create new nodes
            MoveGenerator.generateMoves(curr.getState()).forEach(move -> {
                Solitaire s = new Solitaire(curr.getState());
                s.move(move);

                List<Move> moves = new ArrayList<>(curr.getMoves());
                moves.add(move);

                // Add new nodes to frontier
                insertToFrontier(new Node(s, moves));
            });
        }

        return null;
    }
}
