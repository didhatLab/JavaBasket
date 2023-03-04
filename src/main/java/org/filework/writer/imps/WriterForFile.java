package org.filework.writer.imps;

import org.filework.writer.ResultWriter;
import org.filework.writer.WriterException;

import java.io.*;
import java.util.HashMap;

public class WriterForFile implements ResultWriter {

    String outputFilePath;
    BufferedWriter buffer;

    public WriterForFile(String outputFilePath){
        this.outputFilePath = outputFilePath;
        this.buffer = null;
    }

    @Override
    public void writeResult(HashMap<Character, Integer> res) throws WriterException {
        try {
            this.writeIntoFile(res);
        } catch (FileNotFoundException e){
            throw new WriterException("Not found outout file and can not create!");
        } catch (IOException e) {
            throw new WriterException("Error while writing to output file, sorry!");
        }
    }



    private void writeIntoFile(HashMap<Character, Integer> res) throws IOException {
        this.openFile();
        for (var entry: res.entrySet()) {
            this.buffer.write(entry.getKey() + "-" + entry.getValue() + "\n");
            this.buffer.flush();
        }
        this.buffer.close();
    }

    private void openFile() throws IOException {
        File outputFile = new File(this.outputFilePath);
        if (!outputFile.exists()){
            this.createOutPutFile(outputFile);
        }
        FileWriter writer = new FileWriter(outputFile.getPath());
        this.buffer = new BufferedWriter(writer);
    }

    private void createOutPutFile(File file) throws IOException {
        boolean res = file.createNewFile();
        if (!res){
            throw new FileNotFoundException();
        }
    }

}
