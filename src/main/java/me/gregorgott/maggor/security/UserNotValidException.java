package me.gregorgott.maggor.security;

public class UserNotValidException extends Exception {
    public UserNotValidException(String message) {
        super(message);
    }
}
