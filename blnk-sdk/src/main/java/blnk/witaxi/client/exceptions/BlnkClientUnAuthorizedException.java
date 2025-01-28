package blnk.witaxi.client.exceptions;

public class BlnkClientUnAuthorizedException extends BlnkClientException {
    public BlnkClientUnAuthorizedException(String message) {
        super(message);
    }

    public BlnkClientUnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
