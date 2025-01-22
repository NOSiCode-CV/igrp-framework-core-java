package cv.igrp.core.domain;

import java.util.ArrayList;
import java.util.List;

public interface QueryBus {

    <T> T handle(Query query);

    default <T> List<T> handleAll(List<Query> queries) {
        List<T> result = new ArrayList<T>();
        for (Query query : queries) {
            result.add(handle(query));
        }
        return result;
    }

}