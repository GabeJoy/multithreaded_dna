/**
 * Interface for a Buffer class
 * @author Gabe Joy
 */

public interface Buffer {

    /**
     * Puts a sequence in the ArrayBlockingQueue
     * @param sequence sequence to put in the buffer
     * @throws InterruptedException
     */
    public void blockingPut(String sequence) throws InterruptedException;

    /**
     * Gets a sequence from the ArrayBlockingQueue
     * @return the sequence from the ArrayBlockingQueue
     * @throws InterruptedException
     */
    public String blockingGet() throws InterruptedException;

    /**
     * Checks if the ArrayBlockingQueue is empty (i.e. its size = 0)
     * @return true if empty, false otherwise
     */
    public boolean isEmpty();
}