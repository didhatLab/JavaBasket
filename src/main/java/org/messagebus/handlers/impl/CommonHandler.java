package org.messagebus.handlers.impl;

import org.messagebus.Storage;
import org.cli.TextFeeder;
import org.comlexnum.ComplexNumber;
import org.comlexnum.Matrix;
import org.messagebus.commands.CommonCommand;
import org.messagebus.handlers.CommandHandler;

public class CommonHandler implements CommandHandler<CommonCommand> {

    Storage data_store;
    TextFeeder writer;

    public CommonHandler(Storage storage, TextFeeder writer){
        this.data_store = storage;
        this.writer = writer;
    }

    @Override
    public void handle(CommonCommand command) {
        if (command.type.equals("out")){
            String arg1 = (String) command.args[0];
            ComplexNumber num = this.data_store.getNumber(arg1);
            if (num != null){
                this.writer.feed(String.format("Num variable %s is %s", arg1, num));
            }
            Matrix mat = this.data_store.getMatrix(arg1);
            if (mat != null){
                this.writer.feed(String.format("Matrix variable %s is:", arg1));
                this.writer.feed(mat.toString());
            }
        }

        if (command.type.equals("sum")){
            String arg1 = (String) command.args[0];
            String arg2 = (String) command.args[1];
            ComplexNumber num1 = this.data_store.getNumber(arg1);
            ComplexNumber num2 = this.data_store.getNumber(arg2);
            if (num1 != null && num2 != null){
                ComplexNumber res = num1.append(num2);
                this.writer.feed(String.format("Sum of %s and %s is %s", num1, num2, res.toString()));
            }
            Matrix mat1 = this.data_store.getMatrix(arg1);
            Matrix mat2 = this.data_store.getMatrix(arg2);
            if (mat1 != null && mat2 != null){
                Matrix new_matrix = mat1.plus(mat2);
                this.writer.feed(String.format("sum of matrixes %s and %s:", arg1, arg2));
                this.writer.feed(new_matrix.toString());
            }
        }
        if (command.type.equals("mul")){
            String arg1 = (String) command.args[0];
            String arg2 = (String) command.args[1];
            ComplexNumber num1 = this.data_store.getNumber(arg1);
            ComplexNumber num2 = this.data_store.getNumber(arg2);
            if (num1 != null && num2 != null){
                ComplexNumber res = num1.multiply(num2);
                this.writer.feed(String.format("Mul of %s and %s is %s", num1, num2, res.toString()));
            }

            Matrix mat1 = this.data_store.getMatrix(arg1);
            Matrix mat2 = this.data_store.getMatrix(arg2);
            if (mat1 != null && mat2 != null){
                Matrix new_matrix = mat1.mull(mat2);
                this.writer.feed(String.format("mull of matrixes %s and %s:", arg1, arg2));
                this.writer.feed(new_matrix.toString());
            }

        }
    }
}
