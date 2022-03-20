package main.ai.util;

import main.freecell.Solitaire;
import main.freecell.move.Move;
import main.freecell.move.MoveComponent;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {

    /**
     * This method generates all the available moves that can be made to the solitaire instance passed.
     * @param instance The {@link Solitaire} instance, who's moves will be calculated.
     * @return The list of moves.
     */
    public static List<Move> generateMoves(Solitaire instance) {
        List<Move> moves = new ArrayList<>();

        // Pair every possible location on the board, with every other location and check if a move
        // can be made
        for (MoveComponent source : MoveComponent.values()) {
            for (MoveComponent target : MoveComponent.values()) {
                // The source and target cannot be the same
                if (source == target) continue;

                // A move from a freecell to another freecell is pointless
                // and moves of this type will be discarded
                if (source.getType() == MoveComponent.ComponentType.FREECELL &&
                        target.getType() == MoveComponent.ComponentType.FREECELL)
                    continue;

                // For each pair, create a move instance and simulate the move on the instance,
                // if the moves is corrent (can be made) add it to the list of moves.
                Move candidate = new Move(source, target);
                if (candidate.performMove(instance, true))
                    moves.add(candidate);
            }
        }

        return moves;
    }

}
