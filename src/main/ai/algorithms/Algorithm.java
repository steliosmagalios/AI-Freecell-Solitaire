package main.ai.algorithms;

import main.Main;
import main.freecell.Solitaire;
import main.freecell.move.Move;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static main.Main.TIMEOUT;

public abstract class Algorithm {

    protected Solitaire initialState;

    public Algorithm(Solitaire initialState) {
        // Copy the board
        this.initialState = new Solitaire(initialState);
    }

    /**
     * Performs a search to find a solution for the freecell solitaire
     * passed in the constructor.
     *
     * @return The steps of the solution in a list, if a solution exists. Else null.
     */
    protected abstract List<Move> search();


    /**
     * Method that executes and keep track of the time the {@link Algorithm#search()}} method
     * need to run. Forcibly stops the execution after {@link Main#TIMEOUT} minutes passed.
     *
     * @return The steps of the solution in a list, if a solution exists. Else null.
     */
    public List<Move> execute() {
        // Start the timer
        long start = System.currentTimeMillis();

        AtomicReference<List<Move>> solution = new AtomicReference<>();

        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            service.submit(() -> {
                List<Move> sol = search();
                solution.set(sol);
            }).get(TIMEOUT, TimeUnit.MINUTES);
        } catch (TimeoutException ex1) {
            System.err.printf("Timed out after %d %s.\n", TIMEOUT, TimeUnit.MINUTES);
        } catch (ExecutionException ex2) {
            System.err.println("Program ran out of memory.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        service.shutdown();

        // End the timer
        long totalTime = System.currentTimeMillis() - start;

        // Print time details
        System.out.printf("Algorithm ran for %.3f sec.\n", totalTime / 1000.0d);

        return solution.get();
    }

}
