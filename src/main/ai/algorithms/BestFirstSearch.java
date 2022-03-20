package main.ai.algorithms;

import main.ai.util.HeuristicFunction;
import main.freecell.Solitaire;

/**
 * Implementation of the BestFirstSearch algorithm
 */
public class BestFirstSearch extends HeuristicAlgorithm {

    public BestFirstSearch(Solitaire initialState) {
        super(initialState, HeuristicFunction::calculateHeuristic);
    }

}
