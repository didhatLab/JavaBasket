package org.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class TextEater {

    ArrayDeque<String> textBuffer;
    BufferedReader reader;


    public TextEater(){
        this.textBuffer = new ArrayDeque<String>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public void eat(int number){

        for (int i = 0; i < number; ++i){
            String line;
            try {
                line = this.reader.readLine();
            } catch (IOException e) {
                line = "";
            }
            textBuffer.add(line);
        }
    }

    public String get_last_string(){
        return this.textBuffer.pop();
    }


}
