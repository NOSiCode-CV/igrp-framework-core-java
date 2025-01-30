package cv.igrp.core.domain;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}