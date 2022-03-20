package main.ai.util;

import main.Main;
import main.freecell.Solitaire;
import main.freecell.util.Card;
import main.freecell.util.Foundation;

import java.util.Arrays;

public class HeuristicFunction {

    /**
     * The heuristic function used by this program rank solutions for the freecell solitaire.
     *
     * The heuristic ranks Nodes based on the amount of cards in the foundations (more cards == better score),
     * and the amount of "deadlocks" in the stacks (fewer deadlocks == better score).
     *
     * A "deadlock" exists when a card of higher value is above a card of lower value in the stack (e.g. a ♥K is above a ♠2).
     * For the deadlock to be resolved, the card with the higher value (and all the cards above that) need to be removed from
     * the stack.
     *
     * The final heuristic is H(n) = ({@link Main#DECK_SIZE} * 4) -  f(n) + d(n).
     * Where f(n) = the amount of cards in the foundations, and d(n) = the
     * amount of deadlocks in the stacks.
     *
     * @param node The {@link Node} to be ranked
     * @return The score of the node.
     */
    public static int calculateHeuristic(Node node) {
        Solitaire state = node.getState();

        // Cards in foundations
        int foundationCards = Arrays.stream(state.getFoundations()).map(Foundation::getCardCount).reduce(Integer::sum).orElse(0);

        // Number of deadlocks in stacks
        // A "deadlock" exists when a card of lower value is covered be a card of higher value
        // inside the stack. To break the deadlock, all the cards above the lower value one
        // have to be removed. The number of deadlocks is the number of cards.
        int totalDeadlocks = Arrays.stream(state.getStacks()).map(stack -> {
            int localDeadlocks = 0;
            for (int i = 1; i < stack.size(); i++) {
                Card c1 = stack.get(i - 1);
                Card c2 = stack.get(i);

                if (c1.getValue() < c2.getValue())
                    localDeadlocks += stack.size() - i;
            }
            return localDeadlocks;
        }).reduce(Integer::sum).orElse(0);

        return (Main.DECK_SIZE) * 4 - foundationCards + totalDeadlocks;
    }

}
