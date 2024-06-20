package service.interfaceService;

import objectData.requestObject.RequestAccount;
import io.restassured.response.Response;

public interface AccountServiceInterface {

    // Aceasta interfata reprezinta actiunile pe care vrem sa le facem cu un modul (Account)

    Response createAccount(RequestAccount body);
    Response generateAccountToken(RequestAccount body);
    Response getSpecificAccount(String token, String userId);
    Response deleteSpecificAccount(String token, String userId);

}
