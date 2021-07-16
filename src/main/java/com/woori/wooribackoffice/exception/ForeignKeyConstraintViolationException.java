package com.woori.wooribackoffice.exception;

public class ForeignKeyConstraintViolationException extends RuntimeException {
    public ForeignKeyConstraintViolationException(String msg) {
        super(msg);
    }
}
