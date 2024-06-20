package objectData.responseObject;

import objectData.responseObject.modelObject.ResponseBookObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;

@Getter
public class ResponseAccountSuccess implements ResponseNotNull {

    @JsonProperty("userID")
    @JsonAlias({"userId"})
    private String userID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<ResponseBookObject> books;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(userID);
        Assert.assertNotNull(username);
        for (ResponseBookObject responseBookObject : books) {
            responseBookObject.validateNotNullFields();
        }
    }

    public void validateBookPresence(String book){
        boolean checkBookPresence = false;
        for (ResponseBookObject responseBookObject : books) {
            if (responseBookObject.getIsbn().equals(book)){
                checkBookPresence = true;
                break;
            }
        }
        Assert.assertTrue(checkBookPresence);
    }
}
