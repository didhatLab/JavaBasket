package org.filework;

import org.filework.counting.LetterCounter;
import org.filework.counting.LetterCounterException;
import org.filework.reader.imps.ReaderForFile;
import org.filework.writer.WriterException;
import org.filework.writer.imps.WriterForFile;

import java.util.HashMap;

public class CountingService {
    private final LetterCounter letterCounter;

    public CountingService(LetterCounter letterCounter){
        this.letterCounter = letterCounter;
    }

    public void doShitWork(String inputFilePath, String outputFIlePath) throws CountingServiceException {
        try {
            this.processWork(inputFilePath, outputFIlePath);
        } catch (LetterCounterException e) {
            throw new CountingServiceException(e.errorMsg);
        } catch (WriterException e) {
            throw new CountingServiceException(e.errorMsg);
        }

    }

    private void processWork(String inputFile, String outputFile) throws LetterCounterException, WriterException {
        ReaderForFile reader = new ReaderForFile(inputFile);
        WriterForFile writer = new WriterForFile(outputFile);

        HashMap<Character, Integer> workResult = this.letterCounter.countLetters(reader);

        writer.writeResult(workResult);

    }

}
