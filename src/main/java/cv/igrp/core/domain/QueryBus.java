package cv.igrp.core.domain;

import java.util.List;

public interface QueryBus {

    void handle(Query query);

    default void handleAll(List<Query> queries) {
        for (Query query : queries) {
            handle(query);
        }
    }

}