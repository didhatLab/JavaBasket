package org.messagebus.commands;

import org.messagebus.commands.BaseCommand;

public class CommonCommand extends BaseCommand {
    public String type;
    public Object[] args;

    public CommonCommand(String type, Object[] args){
        this.type = type;
        this.args = args;
    }
}
