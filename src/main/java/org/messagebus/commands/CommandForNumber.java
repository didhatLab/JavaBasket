package org.messagebus.commands;


import org.comlexnum.ComplexNumber;
import org.messagebus.commandtypes.NumCommands;

import java.util.ArrayList;

public class CommandForNumber extends BaseCommand {

    public Enum<NumCommands> commandType;
    public ArrayList<ComplexNumber> args;
    public String variableName;

    public CommandForNumber(NumCommands type, ArrayList<ComplexNumber> args, String variableName) {
        this.commandType = type;
        this.args = args;
        this.variableName = variableName;
    }
    public CommandForNumber(NumCommands type, String variableName){
        this.commandType = type;
        this.variableName = variableName;
    }

}
