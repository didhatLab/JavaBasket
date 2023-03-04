package org.filework.counting;

import org.filework.reader.Reader;
import org.filework.reader.ReaderException;

import java.util.HashMap;

public class LetterCounter {

    public LetterCounter(){}

    public HashMap<Character, Integer> countLetters(Reader reader) throws LetterCounterException {
        try {
            return this.processCounting(reader);
        } catch (ReaderException e) {
            throw new LetterCounterException(e.errorMessage);
        }

    }

    private HashMap<Character, Integer> processCounting(Reader reader) throws ReaderException {
        HashMap<Character, Integer> workResult = new HashMap<>();
        String line = reader.getLine();

        while (line != null) {
            countLine(line, workResult);
            line = reader.getLine();
        }
        return workResult;
    }

    private void countLine(String line, HashMap<Character, Integer> MapForResult){
        for (int i = 0; i < line.length(); ++i){
            char symbol = line.charAt(i);
            if (MapForResult.containsKey(symbol)){
                MapForResult.put(symbol, MapForResult.get(symbol) + 1);
            } else {
                MapForResult.put(symbol, 1);
            }
        }
    }


}
