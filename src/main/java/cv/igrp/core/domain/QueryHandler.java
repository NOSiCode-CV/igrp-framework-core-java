package cv.igrp.core.domain;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}