package blnk.witaxi.client;

import java.net.http.HttpClient;

public class BlnkClient {
    private final String baseUrl;
    private final HttpClient httpClient;

    public static BlnkClient newClient(String baseUrl) {
        return new BlnkClient(baseUrl, HttpClient.newHttpClient());
    }

    private BlnkClient(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }



}
