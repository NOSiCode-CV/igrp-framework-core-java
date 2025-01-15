package cv.igrp.core.domain;

import java.util.List;

public interface CommandBus {

    void send(Command command);

    default void sendAll(List<Command> commands) {
        for (Command command : commands) {
            send(command);
        }
    }

}