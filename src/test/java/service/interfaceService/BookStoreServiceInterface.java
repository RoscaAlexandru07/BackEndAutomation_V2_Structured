package service.interfaceService;

import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import io.restassured.response.Response;

public interface BookStoreServiceInterface {

    // aceasta interfata reprezinta actiunile pe care serviciul BookStore le poate face
    Response addBooksToAccount(RequestAccountBooks body, String token);
    Response updateSpecificBook(RequestAccountBook body, String token, String actualBook);
    Response deleteSpecificBook(RequestAccountBook body, String token);
    Response deleteBooks(String token, String userId);

}
