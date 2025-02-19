package cv.igrp.framework.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * CommandBus interface for sending commands and retrieving responses.
 * Supports dynamic routing of commands to the appropriate handlers.
 */
public interface CommandBus {

    /**
     * Sends a command to the appropriate handler and returns the response.
     *
     * @param command the command to send
     * @param <C>     the type of the command
     * @param <R>     the type of the response
     * @return the response from the handler
     */
    <C extends Command, R> R send(C command);

    /**
     * Sends a list of commands to their appropriate handlers and returns their responses.
     *
     * @param commands the list of commands to send
     * @param <C>      the type of the commands
     * @param <R>      the type of the responses
     * @return a list of responses from the handlers
     */
    default <C extends Command, R> List<R> sendAll(List<C> commands) {
        List<R> result = new ArrayList<>();
        for (C command : commands) {
            result.add(this.send(command));
        }
        return result;
    }
}
