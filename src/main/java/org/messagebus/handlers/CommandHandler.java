package org.messagebus.handlers;

public interface CommandHandler<K> {
    
    public void handle(K command);

}
