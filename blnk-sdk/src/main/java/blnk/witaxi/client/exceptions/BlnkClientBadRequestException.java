package blnk.witaxi.client.exceptions;

public class BlnkClientBadRequestException extends BlnkClientException {
    public BlnkClientBadRequestException(String message) {
        super(message);
    }

    public BlnkClientBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
