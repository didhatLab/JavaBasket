package org.filework.writer;

import java.util.HashMap;

public interface ResultWriter {

    public void writeResult(HashMap<Character, Integer> res) throws WriterException;

}
