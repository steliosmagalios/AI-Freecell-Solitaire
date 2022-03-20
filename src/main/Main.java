package main;

import main.ai.algorithms.*;
import main.freecell.Solitaire;
import main.freecell.move.Move;
import main.freecell.move.util.MoveLogger;
import main.freecell.util.Card;
import main.freecell.util.CardStack;
import main.freecell.util.Suit;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

public class Main {

    // Deck size
    public static final int DECK_SIZE = 13;
    public static final int TIMEOUT = 2; // in minutes

    public static final Map<String, Function<Solitaire, Algorithm>> AVAILABLE_ALGORITHMS = new HashMap<>();

    static {
        // Add all available algorithms to the map
        AVAILABLE_ALGORITHMS.put("breadth", BreadthFirstSearch::new);
        AVAILABLE_ALGORITHMS.put("depth", DepthFirstSearch::new);
        AVAILABLE_ALGORITHMS.put("best", BestFirstSearch::new);
        AVAILABLE_ALGORITHMS.put("astar", AStar::new);
    }

    // 0 -> Algorithm to run
    // 1 -> Input file (stacks)
    // 2 -> Output file (solution)
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Syntax: programName <algorithm> <input> <output>");
            return;
        }

        if (!AVAILABLE_ALGORITHMS.containsKey(args[0])) {
            System.err.printf("Unknown algorithm \"%s\"\n", args[0]);
            return;
        }

        // Create a freecell solitaire instance from the input file
        Solitaire solitaire = new Solitaire(parseFile(args[1]));

        // Fetch an instance of the requested algorithm
        Algorithm algorithm = AVAILABLE_ALGORITHMS.get(args[0]).apply(solitaire);

        // Print algorithm details
        System.out.println("Selected algorithm: " + algorithm.getClass().getSimpleName());
        System.out.println("Deck described in: " + new File(args[1]).getAbsolutePath());
        System.out.println("Configured deck size: " + DECK_SIZE);

        // Run the algorithm
        List<Move> solution = algorithm.execute();

        // If a solution was found, store the solution steps in the output file,
        // if not, do nothing
        if (solution != null) {
            // Solution was found

            // Print the steps of the solution
            System.out.printf("Found solution with %s steps.\n", solution.size());

            // Write the result to the output file
            generateOutputFile(solution, solitaire, args[2]);
        } else {
            // Solution was not found, and no errors occurred,
            // this means that the whole search tree was searched
            System.out.println("No solution was found");
            System.exit(0);
        }
    }

    static CardStack[] parseFile(String filename) {
        File f = new File(filename);

        List<CardStack> stacks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            // Read each line of the input file and parse every card
            reader.lines().forEach((line) -> {
                Stack<Card> currStack = new Stack<>();

                String[] split = line.split(" ");
                for (String value : split) {
                    Suit s = Suit.parse(value.charAt(0));
                    int v = Integer.parseInt(value.substring(1));
                    Card c = new Card(s, v);
                    currStack.push(c);
                }

                // Create a new CardStack object and add it to the stacks
                stacks.add(new CardStack(currStack));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the total stacks are less than 8, add enough stacks to make them 8
        while (stacks.size() < 8) {
            stacks.add(new CardStack(new Stack<>()));
        }

        return stacks.toArray(new CardStack[0]);
    }

    static void generateOutputFile(List<Move> solution, Solitaire initialState, String filename) {
        File outputFile = new File(filename);

        // Init a StringBuilder and push the solution size in it
        StringBuilder builder = new StringBuilder();
        builder.append(solution.size()).append('\n');

        // Run through the solution steps, execute each step after logging it
        // using the StringBuilder object.
        Solitaire state = new Solitaire(initialState);
        solution.forEach(move -> {
            // Log the current move in the output
            builder.append(MoveLogger.log(move, state)).append('\n');

            // Make move
            state.move(move);
        });

        // Write the solution to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the output file location
        System.out.println("Solution steps written in: " + outputFile.getAbsolutePath());
    }

}
