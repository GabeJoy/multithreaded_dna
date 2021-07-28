/**
 * Filter that finds open reading frames in translated sequences
 * @see Filter
 * @author Gabe Joy
 */
public class ORF_Finder extends Filter {

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
    public ORF_Finder(DNABuffer inputBuffer, DNABuffer outputBuffer){
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
     * Finds an open reading frame in a translated sequence
     */
    @Override
    public void filter(){
        try {
            boolean end = false;
            while (!end) {
                String original_sequence = inputBuffer.blockingGet();

                String sequence;

                if (original_sequence.contains("#")){
                    sequence = original_sequence.substring(0, original_sequence.length()-1);
                } else {
                    sequence = original_sequence;
                }

                int start = 0;
                int finish = 0;
                char curr_aa;

                char[] char_seq = sequence.toCharArray();

                for (int i = 0; i < sequence.length(); i++) {
                    curr_aa = char_seq[i];
                    finish = i;
                    //check if (stop codon OR if at the last amino acid) AND (the reading frame is at least 22 AA's long)
                        if (curr_aa == '*' || i == sequence.length() - 1) {
                            if (finish - start >= 22) {
                                if (i == sequence.length() - 1) {
                                    //System.out.println("ORF: " + sequence.substring(start, finish + 1));
                                    if (original_sequence.contains("#")) {
                                        outputBuffer.blockingPut(sequence.substring(start, finish + 1).concat("#"));
                                    } else {
                                        outputBuffer.blockingPut(sequence.substring(start, finish + 1));
                                    }
                                } else {
                                    if (original_sequence.contains("#")) {
                                        outputBuffer.blockingPut(sequence.substring(start, finish).concat("#"));
                                    } else {
                                        outputBuffer.blockingPut(sequence.substring(start, finish));
                                    }
                                }
                            }
                            start = i + 1; //next amino acid
                        }
                }
                if (original_sequence.contains("#")) {
                    //put a the stop character in the output buffer to be used in the output filter
                    outputBuffer.blockingPut("#");
                    end = true;
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
