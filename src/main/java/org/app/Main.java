package org.app;

import org.messagebus.MessageBus;
import org.messagebus.Storage;
import org.cli.TextEater;
import org.cli.TextFeeder;
import org.messagebus.commands.BaseCommand;
import org.messagebus.handlers.impl.CommonHandler;
import org.messagebus.handlers.impl.MatrixHandler;
import org.messagebus.handlers.impl.NumsHandler;
import org.parser.DumpCommandParser;

public class Main {
    public static void main(String[] args) {

        TextEater reader = new TextEater();
        TextFeeder writer = new TextFeeder();
        Storage dataStore = new Storage();
        NumsHandler numsOperationsHandler = new NumsHandler(dataStore, writer);
        CommonHandler commonOperationsHandler = new CommonHandler(dataStore, writer);
        MatrixHandler matrixHandler = new MatrixHandler(dataStore, writer);
        MessageBus shitBus = new MessageBus(numsOperationsHandler, commonOperationsHandler, matrixHandler);
        DumpCommandParser parser = new DumpCommandParser();

        while (true) {
            reader.eat(1);
            try{
                BaseCommand cmd = parser.parse(reader.get_last_string());
                shitBus.handle(cmd);
            } catch (Exception e){
                writer.feed("not valid command, try again");
            }

        }
    }

}
