/**
 * Filter that adds an input sequence to the first outputBuffer
 * @author Gabe Joy
 */
public class InputFilter implements Runnable {

    /**
     * Buffer to put original sequence into
     */
    private final DNABuffer outputBuffer;

    /**
     * Constructor that sets the output buffer
     * @param outputBuffer buffer to put the original sequence into
     */
    public InputFilter(DNABuffer outputBuffer){
        this.outputBuffer = outputBuffer;
    }

    /**
     * Concatenates the stop character '#' to the original sequence, adds it to the output buffer
     */
    @Override
    public void run(){
        try {
            boolean end = false;
            while (!end) {
                String inputSeq = "TAAATTAATGCGGCTGCACTGCTCTAAGGACAATTACGGAGTGGN GCGGCCTGGCGGGAGCACTACCCCATCGACGCGTACTCGAATACTGTATATTGCTCTCACATGAACAAATTAGTAGAGTGCCGCTTCAN GCCCCCCTGTCGTC*  GGCGACGTCTGTAAAATGGCGTTGATGTGGATCGACTCTATAGAGGCATCTACTGATGCGTAGGGAGATCCGGAATGTATTGGCCTATGTCACTGAAACGGCGACGTCTGTAAAATGGCGTTGATGTGGATCGACTCTATAGAGGCATCTACTGATGCGTAGGGAGATCCGGAATGTATTGGCCTATGTCACTGAAAC  TGTCCAAAC ACCCCATGTCGTTACTGAACGTAT CGACGCATACCTCCTTCGTTGAGAACTCACAATTATACAACTGN GGGACATAATCCCTACGCCCATCTTCTACACCCGTCTCTGTGGGTCCAGTTCAAGTGCTGGGAGAGCATCCTCCACAAGGTCTAGTGGTATGGTGGTATN  AGTAAGCTCGTACTGTGATACATGCGACAGGGGTAAGACCATCAGTAGTAGGGATAGTGCCAAAGCTCACTCACCACTGCCTATAAGGGGTGCTTACCTCTAGAATAAGTGTCAGCCAGTATAACCCCATGAGGAACCGAAAAGGCGAACCGGGCCAGACAATCCGGAGGCACCGGGCTCAAAGCCGCGACACGACGGCTCACAGCCGGTAAGAGTAACCCCGGAGTGAACACCTATGGGGCTGGATAAAACTGCCCTGGTGACCGCCATCAACAACCCGAATACGTGGCATTTCAGGAGGCGGCCGGAGGGGGGATGTTTTCTACTATTCGAGGCCGTTCGTTATAACTTGTTGCGTTCCTAGCCGCTATATTTGTCTCTTTGCCGANCTAATGAGAACAACCACACCATAGCGATTTGACGCAGCGCCTCGGAATACCGTATCAGCAGGCGCCTCGTAAGGCCATTGCGAATACCAGGTATCGTGTAAGTAGCGTAGG CCCGTACGCGAG ATAAAC TGCTAGGGA ACCGCGTCTCTACGACCGGTGCTCNGATTTAATTTCGCCGACGTGATGACATTC";
                inputSeq = inputSeq.concat("#");
                System.out.println("Input Sequence:\n" + inputSeq + "\n");
                outputBuffer.blockingPut(inputSeq);
                if (inputSeq.contains("#")){
                    end = true;
                }
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
