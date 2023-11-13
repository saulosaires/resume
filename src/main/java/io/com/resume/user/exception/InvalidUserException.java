package io.com.resume.user.exception;

public class InvalidUserException extends Exception {

    public InvalidUserException(String message, Object obj) {
        super(String.format(message, obj));
    }
}
