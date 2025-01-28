package blnk.witaxi.client.exceptions;

public class BlnkClientNotFoundException extends BlnkClientException {
    public BlnkClientNotFoundException(String message) {
        super(message);
    }

    public BlnkClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
