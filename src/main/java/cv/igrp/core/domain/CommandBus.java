package cv.igrp.core.domain;

import java.util.ArrayList;
import java.util.List;

public interface CommandBus {

    <T> T send(Command command);

    default <T> List<T> sendAll(List<Command> commands) {
        List<T> result = new ArrayList<>();
        for (Command command : commands) {
            result.add(send(command));
        }
        return result;
    }

}