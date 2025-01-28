package blnk.witaxi.client.exceptions;

public class BlnkClientException extends Exception {
    public BlnkClientException(String message) {
        super(message);
    }

    public BlnkClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
