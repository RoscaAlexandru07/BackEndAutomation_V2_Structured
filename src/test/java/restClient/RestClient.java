package restClient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import xmlFile.GeneralXml;
import xmlFile.xmlNode.Configuration;

public class RestClient {

    // Layer 1 = clasa unde sunt definite configurari la nivel de client
    // Am de facut 2 actiuni:
    // 1. Metoda care configureaza clientul
    // 2. Metoda care returneaza un raspuns pe baza configurarii de la client

    private RequestSpecification prepareClient(RequestSpecification requestSpecification) {
        Configuration configuration = GeneralXml.createConfig(Configuration.class);

        requestSpecification.baseUri(configuration.backEndConfig.baseURL);
        requestSpecification.contentType(configuration.backEndConfig.contentType);

        return requestSpecification;
    }

    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint) {
        switch (requestType) {
            case RequestType.REQUEST_POST:
                return prepareClient(requestSpecification).post(endPoint);
            case RequestType.REQUEST_PUT:
                return prepareClient(requestSpecification).put(endPoint);
            case RequestType.REQUEST_DELETE:
                return prepareClient(requestSpecification).delete(endPoint);
            case RequestType.REQUEST_GET:
                return prepareClient(requestSpecification).get(endPoint);
        }
        return null;
    }


}
