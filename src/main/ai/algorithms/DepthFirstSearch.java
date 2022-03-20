package main.ai.algorithms;

import main.ai.util.Node;
import main.freecell.Solitaire;

import java.util.*;

/**
 * Implementation of the DepthFirstSearch algorithm
 */
public class DepthFirstSearch extends BlindSearchAlgorithm {

    // In Depth First Search, the frontier can be represented be a stack
    private final Stack<Node> frontier = new Stack<>();

    public DepthFirstSearch(Solitaire initialState) {
        super(initialState);
    }

    @Override
    protected void insertToFrontier(Node node) {
        frontier.push(node);
    }

    @Override
    protected Node removeFromFrontier() {
        return frontier.pop();
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
