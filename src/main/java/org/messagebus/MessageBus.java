package org.messagebus;

import org.messagebus.commands.BaseCommand;
import org.messagebus.commands.CommandForMatrix;
import org.messagebus.commands.CommandForNumber;
import org.messagebus.commands.CommonCommand;
import org.messagebus.handlers.impl.CommonHandler;
import org.messagebus.handlers.impl.MatrixHandler;
import org.messagebus.handlers.impl.NumsHandler;

public class MessageBus {
    NumsHandler numsHandler;
    CommonHandler baseOpsHandler;
    MatrixHandler matrixHandler;


    public MessageBus(NumsHandler numsHandler, CommonHandler commonHandler, MatrixHandler matrixHandler){
        this.numsHandler = numsHandler;
        this.baseOpsHandler = commonHandler;
        this.matrixHandler = matrixHandler;
    }

    public void handle(BaseCommand command){

        if (command instanceof CommandForNumber real_command){
            this.numsHandler.handle(real_command);
        }
        if (command instanceof CommonCommand real_command){
            this.baseOpsHandler.handle(real_command);
        }
        if (command instanceof CommandForMatrix real_command){
            this.matrixHandler.handle(real_command);
        }


    }

}
