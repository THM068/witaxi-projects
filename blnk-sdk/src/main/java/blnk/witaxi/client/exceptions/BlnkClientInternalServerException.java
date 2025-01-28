package blnk.witaxi.client.exceptions;

public class BlnkClientInternalServerException extends BlnkClientException {
    public BlnkClientInternalServerException(String message) {
        super(message);
    }

    public BlnkClientInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
