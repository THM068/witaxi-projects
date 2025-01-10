package blnk.witaxi.request;

public class RequestUtility {

    public static String createUrl(String baseUrl, String path) {
        return String.format("%s%s", baseUrl, path);
    }
}
