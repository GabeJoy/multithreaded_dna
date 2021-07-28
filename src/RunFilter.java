import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Driver class to demonstrate multithreaded DNA editing
 * @author Gabe Joy
 */

public class RunFilter {
    /**
     * Buffer capacity used for all buffers
     */
    private static final int buffer_capacity = 10;

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newCachedThreadPool();

        DNABuffer start = new DNABuffer(buffer_capacity);
        DNABuffer gap_buffer = new DNABuffer(buffer_capacity);
        DNABuffer reverse_buffer = new DNABuffer(buffer_capacity);
        DNABuffer frame_buffer = new DNABuffer(buffer_capacity);
        DNABuffer translate_buffer = new DNABuffer(buffer_capacity);
        DNABuffer orf_buffer = new DNABuffer(buffer_capacity);

        InputFilter input = new InputFilter(start);
        GapFinder gapFinder = new GapFinder(start, gap_buffer);
        ReverseComplementor reverseComplementor = new ReverseComplementor(gap_buffer, reverse_buffer);
        FrameBuilder frameBuilder = new FrameBuilder(reverse_buffer, frame_buffer);
        Translator translator = new Translator(frame_buffer, translate_buffer);
        ORF_Finder orf_finder = new ORF_Finder(translate_buffer, orf_buffer);
        OutputFilter output = new OutputFilter(orf_buffer);

        executor.execute(input);
        executor.execute(gapFinder);
        executor.execute(reverseComplementor);
        executor.execute(frameBuilder);
        executor.execute(translator);
        executor.execute(orf_finder);
        executor.execute(output);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
