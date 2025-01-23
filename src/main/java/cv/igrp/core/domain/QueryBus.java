package cv.igrp.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * QueryBus interface for sending queries and retrieving responses.
 * Supports dynamic routing of queries to the appropriate handlers.
 */
public interface QueryBus {

    /**
     * Sends a query to the appropriate handler and returns the response.
     *
     * @param query the query to send
     * @param <C>     the type of the query
     * @param <R>     the type of the response
     * @return the response from the handler
     */
    <C extends Query, R> R send(C query);

    /**
     * Sends a list of queries to their appropriate handlers and returns their responses.
     *
     * @param queries the list of queries to send
     * @param <C>      the type of the queries
     * @param <R>      the type of the responses
     * @return a list of responses from the handlers
     */
    default <C extends Query, R> List<R> sendAll(List<C> queries) {
        List<R> result = new ArrayList<>();
        for (C query : queries) {
            result.add(this.send(query));
        }
        return result;
    }
}
