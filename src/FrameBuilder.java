/**
 * Filter that removes the first and second characters from the input sequence
 * @see Filter
 * @author Gabe Joy
 */
public class FrameBuilder extends Filter {

    /**
     * input buffer (from the last filter)
     */
    private DNABuffer inputBuffer;
    /**
     * output buffer (from the next filter)
     */
    private DNABuffer outputBuffer;

    /**
     * Sets the input and output buffers, calls super-class constructor
     * @param inputBuffer buffer that contains unedited sequences
     * @param outputBuffer buffer to put transformed sequences into
     */
    public FrameBuilder(DNABuffer inputBuffer, DNABuffer outputBuffer){
        super(inputBuffer, outputBuffer);
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    /**
     * Calls filter
     */
    @Override
    public void run(){
        filter();
    }

    /**
     * Puts the original sequence, original sequence minus the first base, original sequence minus the first and second bases into the output buffer
     */
    @Override
    public void filter(){
        try {
            //System.out.println("Frame Builder");
            boolean end = false;
            while (!end) {
                String sequence = inputBuffer.blockingGet();

                outputBuffer.blockingPut(sequence);
                outputBuffer.blockingPut(sequence.substring(1));
                outputBuffer.blockingPut(sequence.substring(2));

                if (sequence.contains("#")) {
                    end = true;
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
