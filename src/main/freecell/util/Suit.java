package main.freecell.util;

/**
 * Representation of the card Suits
 */
public enum Suit {

    DIAMONDS('♦', 'D', Color.RED),
    HEARTS('♥', 'H', Color.RED),
    SPADES('♠', 'S', Color.BLACK),
    CLUBS('♣', 'C', Color.BLACK);


    enum Color {
        RED, BLACK;
    }

    private final char prefix;
    private final char code;
    private final Color color;

    Suit(char prefix, char code, Color color) {
        this.prefix = prefix;
        this.code = code;
        this.color = color;
    }

    public static Suit parse(char suitCode) {
        for (Suit value : values()) {
            if (value.code == suitCode)
                return value;
        }
        return null;
    }

    public String getCode() {
        return String.valueOf(this.code);
    }

    public boolean isOppositeColor(Suit o) {
        return this.color != o.color;
    }

    public char getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return String.valueOf(prefix);
    }
}
