package main.ai.algorithms;

import main.ai.util.Node;
import main.freecell.Solitaire;

import java.util.*;

/**
 * Implementation of the BreadthFirstSearch algorithm
 */
public class BreadthFirstSearch extends BlindSearchAlgorithm {

    // In Breadth First Search, the frontier can be represented be a queue
    private final Queue<Node> frontier = new ArrayDeque<>();

    public BreadthFirstSearch(Solitaire initialState) {
        super(initialState);
    }

    @Override
    protected void insertToFrontier(Node node) {
        frontier.add(node);
    }

    @Override
    protected Node removeFromFrontier() {
        return frontier.poll();
    }

    @Override
    protected boolean isFrontierEmpty() {
        return frontier.isEmpty();
    }

    @Override
    protected boolean removeVisitedStates() {
        return false;
    }
}
