package blnk.witaxi.client
import spock.lang.Specification

import java.net.http.HttpClient

class BlnkClientSpec extends Specification {

    def "BlnkClient has correct baseurl and http client"() {
        given:
            def client = BlnkClient.newClient(baseurl)
        expect:
            client.httpClient instanceof HttpClient
            client.baseUrl == baseurl

        where:
        baseurl  = "http://localhost:5001/"
    }
}
