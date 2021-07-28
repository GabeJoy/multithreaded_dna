/**
 * Filter that removes gaps from sequences
 * @see Filter
 * @author Gabe Joy
 */
public class GapFinder extends Filter {

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
    public GapFinder(DNABuffer inputBuffer, DNABuffer outputBuffer){
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
     * Splits a sequences whenever it encounters 'N', deletes ' ' spaces, adds the resulting sequences to the output buffer
     */
    @Override
    public void filter(){
        try {
            //System.out.println("Gap Finder");
            boolean end = false;
            while (!end) {
                String sequence = inputBuffer.blockingGet();
                char[] char_seq = sequence.toCharArray();
                StringBuffer filtered_string = new StringBuffer();
                char curr_base;

                for (int i = 0; i < char_seq.length; i++) {
                    curr_base = char_seq[i];
                    if (curr_base == 'N') {
                        if (filtered_string.toString().length() > 0) {
                            outputBuffer.blockingPut(filtered_string.toString());
                            filtered_string.delete(0, filtered_string.length());
                        }
                    } else if (curr_base == 'A' || curr_base == 'G' || curr_base == 'T' || curr_base == 'C' || curr_base == '#') {
                        filtered_string.append(curr_base);
                    }
                }
                outputBuffer.blockingPut(filtered_string.toString());

                if (filtered_string.toString().contains("#")) {
                    end = true;
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
