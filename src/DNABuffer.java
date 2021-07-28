import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * Buffer class used by each filter (including shared filters)
 * @author Gabe Joy
 */
public class DNABuffer implements Buffer {

    /**
     * ArrayBlockingQueue buffer that holds strings
     */
    private final ArrayBlockingQueue<String> shared_buffer;

    /**
     * One argument constructor that sets the capacity of the buffer
     * @param capacity capacity of the buffer
     */
    public DNABuffer(int capacity){
        shared_buffer = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * Puts a sequence in the ArrayBlockingQueue
     * @param sequence sequence to put in the buffer
     * @throws InterruptedException
     */
    @Override
    public void blockingPut(String sequence) throws InterruptedException{
        shared_buffer.put(sequence);
    }

    /**
     * Gets a sequence from the ArrayBlockingQueue
     * @return the sequence from the ArrayBlockingQueue
     * @throws InterruptedException
     */
    @Override
    public String blockingGet() throws InterruptedException{
        return shared_buffer.take();
    }

    /**
     * Checks if the ArrayBlockingQueue is empty (i.e. its size = 0)
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty(){
        return shared_buffer.size() == 0;
    }
}
