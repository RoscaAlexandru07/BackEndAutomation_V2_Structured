package objectData.responseObject;

import objectData.responseObject.modelObject.ResponseBookObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseAccountGetSuccess {

    @JsonProperty("userId")
    private String userID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<ResponseBookObject> books;
}
