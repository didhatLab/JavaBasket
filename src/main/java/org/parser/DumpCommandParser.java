package org.parser;

import org.comlexnum.ComplexNumber;
import org.messagebus.commands.BaseCommand;
import org.messagebus.commands.CommandForMatrix;
import org.messagebus.commands.CommandForNumber;
import org.messagebus.commands.CommonCommand;
import org.messagebus.commandtypes.MatrixCommands;
import org.messagebus.commandtypes.NumCommands;

import java.util.ArrayList;

public class DumpCommandParser {

    public DumpCommandParser(){}

    public BaseCommand parse(String line){
        StringBuilder name = new StringBuilder();
        int line_index = 0;
        for (; line_index < line.length(); ++line_index){
            if (line.charAt(line_index) == '='
                    || line.charAt(line_index) == '['
                    || line.charAt(line_index) == '.'
                    || line.charAt(line_index) == '+'
                    || line.charAt(line_index) == '*'){
                break;
            }
            name.append(line.charAt(line_index));
        }
        if (line_index == line.length()){
            Object[] rr = new Object[1];
            rr[0] = name.toString();
            return new CommonCommand("out", rr);
        }

        for (; line_index < line.length(); ++line_index){
            if (line.charAt(line_index) == '['){
                StringBuilder n = new StringBuilder();
                line_index++;
                while (line.charAt(line_index) != ']'){
                    n.append(line.charAt(line_index++));
                }
                line_index++;
                StringBuilder m = new StringBuilder();
                line_index++;
                while (line.charAt(line_index) != ']'){
                    m.append(line.charAt(line_index++));
                }
                ArrayList<String> kek = new ArrayList<String>();
                kek.add(n.toString());
                kek.add(m.toString());
                return new CommandForMatrix(MatrixCommands.SET, kek, name.toString());
            }
            if (line.charAt(line_index) == '='){
                ArrayList<ComplexNumber> nums = new ArrayList<ComplexNumber>();
                line_index++;
                while (line_index < line.length()){
                    StringBuilder num = new StringBuilder();
                    if (line.charAt(line_index) == '-'){
                        num.append('-');
                        line_index++;
                    }
                    if (line.charAt(line_index) == '+'){
                        line_index++;
                    }
                    while (line_index < line.length() && line.charAt(line_index) != '+' && line.charAt(line_index)!='-'){
                        num.append(line.charAt(line_index));
                        line_index++;
                    }
                    if (num.charAt(num.length()-1) == 'i'){
                        num.deleteCharAt(num.length()-1);
                        nums.add(new ComplexNumber(0, Integer.parseInt(num.toString())));
                    } else {
                        nums.add(new ComplexNumber(Integer.parseInt(num.toString())));
                    }
                }
                ComplexNumber[] kek = new ComplexNumber[100];
                nums.toArray(kek);
                return new CommandForNumber(NumCommands.SET, nums, name.toString());
            }
            if (line.charAt(line_index) == '+'){
                line_index++;
                StringBuilder name2 = new StringBuilder();
                for (; line_index < line.length(); ++line_index){
                    name2.append(line.charAt(line_index));
                }
                Object[] args = new Object[2];
                args[0] = name.toString();
                args[1] = name2.toString();
                return new CommonCommand("sum", args);
            }
            if (line.charAt(line_index) == '*'){
                line_index++;
                StringBuilder name2 = new StringBuilder();
                for (; line_index < line.length(); ++line_index){
                    name2.append(line.charAt(line_index));
                }
                Object[] args = new Object[2];
                args[0] = name.toString();
                args[1] = name2.toString();
                return new CommonCommand("mul", args);
            }
            if (line.charAt(line_index) == '.'){
                line_index++;
                StringBuilder command = new StringBuilder();
                while (line_index < line.length() && line.charAt(line_index) != '('){
                    command.append(line.charAt(line_index));
                    line_index++;
                }
                line_index++;
                if (command.toString().equals("set")){
                    StringBuilder x = new StringBuilder();
                    StringBuilder y = new StringBuilder();
                    StringBuilder a = new StringBuilder();
                    StringBuilder i = new StringBuilder();
                    while (line.charAt(line_index) != ' '){
                        x.append(line.charAt(line_index));
                        line_index++;
                    }
                    line_index++;
                    while (line.charAt(line_index) != ' '){
                        y.append(line.charAt(line_index));
                        line_index++;
                    }
                    line_index++;
                    while (line.charAt(line_index) != ' '){
                        a.append(line.charAt(line_index));
                        line_index++;
                    }
                    line_index++;
                    while (line.charAt(line_index) != 'i'){
                        i.append(line.charAt(line_index));
                        line_index++;
                    }
                    ArrayList<String> kek = new ArrayList<String>();
                    kek.add(x.toString());
                    kek.add( y.toString());
                    kek.add(a.toString());
                    kek.add(i.toString());
                    return new CommandForMatrix(MatrixCommands.UPDATE, kek, name.toString());

                }
                if (command.toString().equals("det")){
                    return new CommandForMatrix(MatrixCommands.DETERMINANT, name.toString());
                }
                if (command.toString().equals("transport")){
                    return new CommandForMatrix(MatrixCommands.TRANSPORT, name.toString());
                }

            }
        }
        return new CommandForNumber(NumCommands.OUT, "kek");
    }


}
