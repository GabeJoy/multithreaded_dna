/**
 * Runnable filter class
 * @author Gabe Joy
 */
public class Filter implements Runnable {

    /**
     * input buffer (from the previous filter)
     */
    private DNABuffer inputBuffer;
    /**
     * output buffer (to the next filter)
     */
    private DNABuffer outputBuffer;

    /**
     * Constructor that sets the input and output buffers
     * @param inputBuffer
     * @param outputBuffer
     */
    public Filter(DNABuffer inputBuffer, DNABuffer outputBuffer){
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
     * Takes strings from the input buffer, makes transformations to them, puts them in the output buffer
     */
    public void filter(){}
}
