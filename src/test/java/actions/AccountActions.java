package actions;

import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountGetFailed;
import objectData.responseObject.ResponseAccountGetSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import restClient.ResponseStatus;
import service.serviceImplementation.AccountServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountActions {

    private final AccountServiceImpl accountServiceImpl;

    public AccountActions() {
        this.accountServiceImpl = new AccountServiceImpl();
    }

    public ResponseAccountSuccess createNewAccount(RequestAccount requestAccount) {
        Response response = accountServiceImpl.createAccount(requestAccount);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        responseAccountBody.validateNotNullFields();
        Assert.assertEquals(responseAccountBody.getUsername(), requestAccount.getUserName());
        return responseAccountBody;
    }

    public ResponseTokenSuccess generateToken(RequestAccount requestAccount) {
        Response response = accountServiceImpl.generateAccountToken(requestAccount);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        responseTokenSuccess.validateNotNullFields();
        Assert.assertEquals(responseTokenSuccess.getStatus(), "Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");
        return responseTokenSuccess;
    }

    public void getSpecificAccount(String token, String userId, RequestAccount requestAccount) {
        Response response = accountServiceImpl.getSpecificAccount(token, userId);
        if (response.getStatusCode() == ResponseStatus.SC_OK) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

            ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
            Assert.assertEquals(responseAccountGetSuccess.getUsername(), requestAccount.getUserName());
        } else {
            Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_UNAUTHORIZED);

            ResponseAccountGetFailed responseAccountGetFailed = response.body().as(ResponseAccountGetFailed.class);
            Assert.assertEquals(responseAccountGetFailed.getCode(), "1207");
            Assert.assertEquals(responseAccountGetFailed.getMessage(), "User not found!");
        }
    }

    public void deleteSpecificAccount(String token, String userId) {
        Response response = accountServiceImpl.deleteSpecificAccount(token, userId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }
}
