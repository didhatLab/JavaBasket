package org.filework.reader.imps;


import org.filework.reader.Reader;
import org.filework.reader.ReaderException;

import java.io.*;

public class ReaderForFile implements Reader {

    private BufferedReader bufferedReader;
    private final String filePath;
    private boolean is_file_open;

    public ReaderForFile(String fileName){
        this.filePath = fileName;
        this.is_file_open = false;
        this.bufferedReader = null;
    }

    @Override
    public String getLine() throws ReaderException {

        try {
            return this.readLine();
        } catch (FileNotFoundException e) {
            throw new ReaderException("not found file");
        } catch (IOException e) {
            throw new ReaderException("error while read data");
        }

    }

    private String readLine() throws IOException {
        if (!this.is_file_open){
            this.openFile();
        }
        return this.bufferedReader.readLine();

    }

    private void openFile() throws FileNotFoundException {
        File inputFile = new File(this.filePath);
        if (!inputFile.exists()){
            throw new FileNotFoundException();
        }

        FileReader reader = new FileReader(inputFile.getPath());
        this.bufferedReader = new BufferedReader(reader);
        this.is_file_open = true;
    }




}
