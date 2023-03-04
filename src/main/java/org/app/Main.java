package org.app;

import org.cli.TextEater;
import org.cli.TextFeeder;
import org.filework.CountingService;
import org.filework.CountingServiceException;
import org.filework.counting.LetterCounter;

public class Main {
    public static void main(String[] args) {

        TextEater reader = new TextEater();
        TextFeeder writer = new TextFeeder();
        LetterCounter counter = new LetterCounter();
        CountingService countingService = new CountingService(counter);

        while (true) {
            writer.feed("Input path to file: ");
            reader.eat(1);
            String file_path = reader.get_last_string();
            writer.feed("Input file for output: ");
            reader.eat(1);
            String output_file_path = reader.get_last_string();
            try{
                countingService.doShitWork(file_path, output_file_path);
                writer.feed("result successfully written!");
            } catch (CountingServiceException e){
                writer.feed(String.format("Error while executing: %s", e.errMsg));
            }

        }
    }

}
