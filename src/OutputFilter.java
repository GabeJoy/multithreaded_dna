/**
 * Filter that outputs final, edited sequence
 * @author Gabe Joy
 */
public class OutputFilter implements Runnable {

    /**
     * input buffer (from the last filter)
     */
    private final DNABuffer inputBuffer;

    /**
     * Constructor that sets the input buffer
     * @param inputBuffer buffer from the last filter
     */
    public OutputFilter(DNABuffer inputBuffer){
        this.inputBuffer = inputBuffer;
    }

    /**
     * Prints sequences from the input buffer until it encounters the stop character '#'
     */
    @Override
    public void run(){
        try {
            boolean end = false;
            while (!end) {
                String filteredSeq = inputBuffer.blockingGet();
                if (filteredSeq.equals("#")){
                    end = true;
                } else {
                    System.out.println("Filtered DNA Seq: " + "\n" + filteredSeq + "\n");
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
