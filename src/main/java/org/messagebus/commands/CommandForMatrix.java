package org.messagebus.commands;

import org.comlexnum.ComplexNumber;
import org.messagebus.commandtypes.MatrixCommands;
import org.messagebus.commandtypes.NumCommands;

import java.util.ArrayList;

public class CommandForMatrix extends BaseCommand{

    public Enum<MatrixCommands> commandType;
    public ArrayList<String> args;
    public String variableName;

    public CommandForMatrix(MatrixCommands type, ArrayList<String> args, String variableName) {
        this.commandType = type;
        this.args = args;
        this.variableName = variableName;
    }
    public CommandForMatrix(MatrixCommands type, String variableName){
        this.commandType = type;
        this.variableName = variableName;
    }
}
