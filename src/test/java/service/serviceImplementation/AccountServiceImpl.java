package service.serviceImplementation;

import objectData.requestObject.RequestAccount;
import service.apiService.AccountApiService;
import service.interfaceService.AccountServiceInterface;
import service.endpoints.AccountEndpoints;
import io.restassured.response.Response;

public class AccountServiceImpl implements AccountServiceInterface {

    // Facem o instanta de ApiService ca sa putem accesa metodele generale POST, PUT, DELETE, GET etc.

    private final AccountApiService accountApiService;

    public AccountServiceImpl() {
        accountApiService = new AccountApiService();
    }

    @Override
    public Response createAccount(RequestAccount body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_CREATE);
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_TOKEN);
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        String url = AccountEndpoints.ACCOUNT_GET + userId;
        return accountApiService.get(token, url);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        String url = AccountEndpoints.ACCOUNT_DELETE + userId;
        return accountApiService.delete(token, url);
    }
}
