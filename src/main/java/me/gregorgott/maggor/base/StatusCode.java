package me.gregorgott.maggor.base;

/**
 * This enum represents the status codes of the application.
 * It contains the status message and the HTTP status code.
 * The status message is a custom message that describes the status.
 *
 * @author Gregor Gottschewski
 */
public enum StatusCode {
    OK("OK", 200),
    TEMPORARY_NOT_AVAILABLE("Temporary not available", 503),
    PERMANENT_NOT_AVAILABLE("Permanent not available", 410);

    private final String message;
    private final int code;

    StatusCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
