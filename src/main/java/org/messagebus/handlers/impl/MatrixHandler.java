package org.messagebus.handlers.impl;

import org.messagebus.Storage;
import org.cli.TextFeeder;
import org.comlexnum.ComplexNumber;
import org.comlexnum.Matrix;
import org.messagebus.commands.CommandForMatrix;
import org.messagebus.commandtypes.MatrixCommands;
import org.messagebus.handlers.CommandHandler;

public class MatrixHandler implements CommandHandler<CommandForMatrix> {

    Storage storage;
    TextFeeder writer;

    public MatrixHandler(Storage storage, TextFeeder writer){
        this.storage = storage;
        this.writer = writer;
    }
    @Override
    public void handle(CommandForMatrix command) {
        if (command.commandType == MatrixCommands.SET){
            int n = Integer.parseInt(command.args.get(0));
            int m = Integer.parseInt(command.args.get(1));
            Matrix new_zero_matrix = new Matrix(n, m);
            this.storage.setMatrixVariable(command.variableName, new_zero_matrix);
            return;
        }
        if (command.commandType == MatrixCommands.UPDATE){
            Matrix kk = this.storage.getMatrix(command.variableName);
            if (kk == null){
                this.writer.feed("NOT FIND VARIABLE!");
                return;
            }
            int x = Integer.parseInt(command.args.get(0));
            int y = Integer.parseInt(command.args.get(1));
            ComplexNumber new_val = new ComplexNumber(Double.parseDouble(command.args.get(2)), Double.parseDouble(command.args.get(3)) );
            kk.set_value(x, y, new_val);
            this.writer.feed(String.format("number %s is set on %s %s position", new_val, x, y));
        }
        if (command.commandType == MatrixCommands.DETERMINANT){
            Matrix kk = this.storage.getMatrix(command.variableName);
            if (kk == null){
                this.writer.feed("NOT FIND VARIABLE!");
                return;
            }
            ComplexNumber det = kk.determinant();
            this.writer.feed(String.format("det of matrix %s is %s", command.variableName, det));
            return;
        }
        if (command.commandType == MatrixCommands.TRANSPORT){
            Matrix kk = this.storage.getMatrix(command.variableName);
            if (kk == null){
                this.writer.feed("NOT FIND VARIABLE");
                return;
            }
            kk.transport();
            this.writer.feed("matrix transported!");
            return;
        }
    }
}
