/**
 * Filter that finds the reverse complement of an input sequence
 * @see Filter
 * @suthor Gabe Joy
 */
public class ReverseComplementor extends Filter {

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
    public ReverseComplementor(DNABuffer inputBuffer, DNABuffer outputBuffer){
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
     * Reverses the input sequence, finds the complement of each base
     */
    @Override
    public void filter(){
        try {
            boolean end = false;
            while (!end) {
                String sequence = inputBuffer.blockingGet();
                outputBuffer.blockingPut(sequence);

                String reversed_sequence;
                String rev_comp;

                StringBuffer str_buffer = new StringBuffer();
                str_buffer.append(sequence);

                StringBuffer rev_comp_buffer = new StringBuffer();

                if (sequence.contains("#")){
                    reversed_sequence = str_buffer.reverse().toString();
                    reversed_sequence = reversed_sequence.substring(1);

                    char[] reverse_comp = new char[reversed_sequence.length()];
                    char[] reverse_seq = reversed_sequence.toCharArray();

                    //find complement of the reverse
                    for (int j = 0; j < reversed_sequence.length(); j++) {
                        reverse_comp[j] = complement(reverse_seq[j]);
                        rev_comp_buffer.append(reverse_comp[j]);
                    }

                    rev_comp = rev_comp_buffer.toString().concat("#");
                    outputBuffer.blockingPut(rev_comp);
                } else {
                    reversed_sequence = str_buffer.reverse().toString();

                    char[] reverse_comp = new char[reversed_sequence.length()];
                    char[] reverse_seq = reversed_sequence.toCharArray();

                    //find complement of the reverse
                    for (int j = 0; j < reversed_sequence.length(); j++) {
                        reverse_comp[j] = complement(reverse_seq[j]);
                        rev_comp_buffer.append(reverse_comp[j]);
                    }

                    rev_comp = rev_comp_buffer.toString();
                    outputBuffer.blockingPut(rev_comp);
                }

                if (sequence.contains("#")) {
                    end = true;
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Finds the complement of each base
     * @param base to find complement of
     * @return complement of base
     */
    private char complement(char base){
        switch (base){
            case 'A':
                return 'T';
            case 'G':
                return 'C';
            case 'T':
                return 'A';
            case 'C':
                return 'G';
        }
        return 'x';
    }
}
