package main.ai.algorithms;

import main.ai.util.HeuristicFunction;
import main.ai.util.Node;
import main.freecell.Solitaire;

/**
 * Implementation of the A* algorithm.
 */
public class AStar extends HeuristicAlgorithm {

    public AStar(Solitaire initialState) {
        super(initialState, (node) -> HeuristicFunction.calculateHeuristic(node) + node.getMoves().size());
    }
}
