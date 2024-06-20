package objectData.requestObject;

import java.util.HashMap;

public interface RequestPreparation {

    // Aceasta interfata are ca scop sa serializeze un specific request body
    void prepareObject(HashMap<String, String> testData);
}
