/**
 * Filter that transcribes an input DNA sequence to RNA, translates the result to amino acids.
 * @see Filter
 * @author Gabe Joy
 */
public class Translator extends Filter {

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
    public Translator(DNABuffer inputBuffer, DNABuffer outputBuffer){
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
                String original_sequence = inputBuffer.blockingGet();

                String sequence;

                //remove the stop character if the input sequence contains it
                if (original_sequence.contains("#")){
                    sequence = original_sequence.substring(0, original_sequence.length()-1);
                } else {
                    sequence = original_sequence;
                }

                int ind = 0;
                String bases;
                StringBuffer translation = new StringBuffer();

                while (sequence.length() - ind >= 3) {
                    bases = sequence.substring(ind, ind + 3);
                    ind += 3;
                    Codon codon = Codon.getCodon(bases);
                    translation.append(codon.getOneLetterCode());
                }

                //add the stop character if the original sequence contained it
                if (original_sequence.contains("#")) {
                    outputBuffer.blockingPut(translation.toString().concat("#"));
                    end = true;
                } else {
                    outputBuffer.blockingPut(translation.toString());
                }
            }
        } catch (InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
