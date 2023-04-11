package com.cookiestore.cookiesubdomain.utils.exceptions;

public class DuplicateCookieIdentifierException extends RuntimeException{
    public DuplicateCookieIdentifierException() {}

    public DuplicateCookieIdentifierException(String message) { super(message); }

    public DuplicateCookieIdentifierException(Throwable cause) { super(cause); }

    public DuplicateCookieIdentifierException(String message, Throwable cause) { super(message, cause); }
}
