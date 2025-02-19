package cv.igrp.framework.core.domain;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}