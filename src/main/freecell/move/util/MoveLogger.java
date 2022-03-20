package main.freecell.move.util;

import main.freecell.Solitaire;
import main.freecell.move.Move;

public class MoveLogger {

    public static String log(Move move, Solitaire instance) {
        String encodedSource = move.getSource().get(instance).getEncodedCard();
        // Log move
        switch (move.getTarget().getType()) {
            case FREECELL:
                return String.format("freecell %s", encodedSource);
            case FOUNDATION:
                return String.format("source %s", encodedSource);
            case STACK:
                return move.getTarget().get(instance).isEmpty()
                    ? String.format("newstack %s", encodedSource)
                    : String.format("stack %s %s", encodedSource, move.getTarget().get(instance).getEncodedCard());
            default:
                return move.toString();
        }
    }

}
