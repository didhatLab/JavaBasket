package org.messagebus.handlers.impl;

import org.messagebus.Storage;
import org.cli.TextFeeder;
import org.messagebus.commandtypes.NumCommands;
import org.messagebus.commands.CommandForNumber;
import org.messagebus.handlers.CommandHandler;
import org.comlexnum.ComplexNumber;


;

public class NumsHandler implements CommandHandler<CommandForNumber> {

    Storage data_store;
    TextFeeder textFeeder;

    public NumsHandler(Storage data_store, TextFeeder feeder){
        this.data_store = data_store;
        this.textFeeder = feeder;
    }

    @Override
    public void handle(CommandForNumber command) {
        this.helpHandler(command);
    }

    private void helpHandler(CommandForNumber command) {
        if (command.commandType == NumCommands.ADDING){
            ComplexNumber res = this.sum(command);
            this.textFeeder.feed(String.format("result of adding is %s", res.toString()));
        }
        else if (command.commandType == NumCommands.MULTIPLY){
            ComplexNumber res = this.mull(command);
            this.textFeeder.feed(String.format("result of multiply is %s", res.toString()));
        }
        else if (command.commandType == NumCommands.SET){
            ComplexNumber new_num = new ComplexNumber(0, 0);

            for (int i = 0; i < command.args.size(); ++i){
                new_num = new_num.append(command.args.get(i));
            }

            this.data_store.setNumVariable(command.variableName, new_num);
            this.textFeeder.feed(String.format("variable %s is set!", command.variableName));
        }
    }

    private ComplexNumber sum(CommandForNumber command){
        ComplexNumber first = command.args.get(0);
        ComplexNumber second = command.args.get(1);

        return first.append(second);

    }

    private ComplexNumber mull(CommandForNumber command){
        ComplexNumber first = command.args.get(0);
        ComplexNumber second = command.args.get(1);

        return first.multiply(second);

    }

}
