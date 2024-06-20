package actions;

import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import objectData.responseObject.ResponseAccountBooksSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import restClient.ResponseStatus;
import service.serviceImplementation.BookStoreServiceImpl;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookStoreActions {

    private final BookStoreServiceImpl bookStoreServiceImpl;

    public BookStoreActions() {
        bookStoreServiceImpl = new BookStoreServiceImpl();
    }

    public void addBooksToAccount(String token, RequestAccountBooks requestAccountBooks) {
        Response response = bookStoreServiceImpl.addBooksToAccount(requestAccountBooks, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_CREATED);

        ResponseAccountBooksSuccess responseAccountBooksSuccess = response.body().as(ResponseAccountBooksSuccess.class);
        responseAccountBooksSuccess.validateNotNullFields();
        responseAccountBooksSuccess.validateBooks(requestAccountBooks.getCollectionOfIsbns());
    }

    public void updateSpecificBookFromAccount(String token, RequestAccountBook requestBody, String actualBook){
        Response response = bookStoreServiceImpl.updateSpecificBook(requestBody,token,actualBook);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_OK);

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);
        responseAccountSuccess.validateNotNullFields();
        responseAccountSuccess.validateBookPresence(requestBody.getIsbn());
    }

    public void deleteSpecificBookFromAccount(String token, RequestAccountBook requestBody){
        Response response = bookStoreServiceImpl.deleteSpecificBook(requestBody,token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }

    public void deleteBooksFromAccount(String token, String userId){
        Response response = bookStoreServiceImpl.deleteBooks(token, userId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatus.SC_NO_CONTENT);
    }
}
