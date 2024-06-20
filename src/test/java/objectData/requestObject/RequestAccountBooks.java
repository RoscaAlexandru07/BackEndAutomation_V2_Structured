package objectData.requestObject;

import objectData.requestObject.modelObject.RequestBookObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class RequestAccountBooks implements RequestPreparation {

    private String userId;
    private List<RequestBookObject> collectionOfIsbns;

    public RequestAccountBooks(HashMap<String, String> testData) {
        prepareObject(testData);
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userId":
                    setUserId(testData.get(key));
                    break;
                case "collectionOfIsbns":
                    prepareBooks(testData.get(key));
                    break;
            }
        }
    }

    // trebuie sa parsam valoarea pentru carti intr-o lista de obiecte (BookObject)
    private void prepareBooks(String value) {
        collectionOfIsbns = new ArrayList<>();
        String[] books = value.split(",");

        for (String book : books) {
            collectionOfIsbns.add(new RequestBookObject(book));
        }
    }

}
