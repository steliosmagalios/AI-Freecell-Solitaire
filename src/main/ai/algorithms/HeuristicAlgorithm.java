package main.ai.algorithms;

import main.ai.util.MoveGenerator;
import main.ai.util.Node;
import main.freecell.Solitaire;
import main.freecell.move.Move;

import java.util.*;
import java.util.function.Function;

/**
 * Implementation of a generic Informed Search Algorithm using a heuristic function.
 * This class is implemented by {@link AStar} and {@link BestFirstSearch}, with the only
 * change being the heuristic used.
 */
public abstract class HeuristicAlgorithm extends Algorithm {

    private final Function<Node, Integer> calculateHeuristic;

    public HeuristicAlgorithm(Solitaire initialState, Function<Node, Integer> calculateHeuristic) {
        super(initialState);
        this.calculateHeuristic = calculateHeuristic;
    }

    /**
     * The {@link Comparator} to sort the frontier. This method executes the {@link main.ai.util.HeuristicFunction#calculateHeuristic(Node)}
     * method for the 2 nodes provided and compares the result.
     * @param node1 The first node
     * @param node2 The second node
     * @return The order of the 2 nodes, as described by the {@link Comparator}.
     */
    protected int heuristic(Node node1, Node node2) {
        return Integer.compare(
                this.calculateHeuristic.apply(node1),
                this.calculateHeuristic.apply(node2)
        );
    }

    @Override
    protected List<Move> search() {
        // A set to keep track of the unique states that the algorithm visited
        Set<Solitaire> visited = new HashSet<>();

        // The priority queue sorts its items based on a comparator provided.
        // Said comparator, will be the heuristic function for the informed search
        PriorityQueue<Node> frontier = new PriorityQueue<>(this::heuristic);
        frontier.add(new Node(initialState, new ArrayList<>()));

        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();

            if (visited.contains(curr.getState()))
                continue;
            visited.add(curr.getState());

            // Check completion
            if (curr.getState().isCompleted()) {
                return curr.getMoves();
            }

            MoveGenerator.generateMoves(curr.getState()).forEach(move -> {
                // Generate the new state
                Solitaire newState = new Solitaire(curr.getState());
                newState.move(move);

                // Create the list of moves for that state
                List<Move> newMoves = new ArrayList<>(curr.getMoves());
                newMoves.add(move);

                // Finally, add the node to the frontier
                frontier.add(new Node(newState, newMoves));
            });
        }

        return null;
    }
}
