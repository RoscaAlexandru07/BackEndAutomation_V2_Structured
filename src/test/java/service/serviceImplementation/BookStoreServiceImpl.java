package service.serviceImplementation;

import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import service.apiService.BookstoreApiService;
import service.interfaceService.BookStoreServiceInterface;
import service.endpoints.BookStoreEndpoints;
import io.restassured.response.Response;

public class BookStoreServiceImpl implements BookStoreServiceInterface {

    private final BookstoreApiService bookstoreApiService;

    public BookStoreServiceImpl(){
        bookstoreApiService = new BookstoreApiService();
    }

    @Override
    public Response addBooksToAccount(RequestAccountBooks body, String token) {
        return bookstoreApiService.post(body, BookStoreEndpoints.BOOKSTORE_ADD, token);
    }

    @Override
    public Response updateSpecificBook(RequestAccountBook body, String token, String actualBook) {
        String url = BookStoreEndpoints.BOOKSTORE_UPDATE+actualBook;
        return bookstoreApiService.put(body, url, token);
    }

    @Override
    public Response deleteSpecificBook(RequestAccountBook body, String token) {
        return bookstoreApiService.delete(body, token, BookStoreEndpoints.BOOKSTORE_DELETE_BOOK);
    }

    @Override
    public Response deleteBooks(String token, String userId) {
        String url = BookStoreEndpoints.BOOKSTORE_DELETE_BOOKS+userId;
        return bookstoreApiService.delete(token, url);
    }


}
