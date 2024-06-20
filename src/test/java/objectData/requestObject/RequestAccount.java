package objectData.requestObject;

import lombok.Data;

import java.util.HashMap;

@Data
public class RequestAccount implements RequestPreparation {

    private String userName;
    private String password;

    public RequestAccount(HashMap<String, String> testData) {
        prepareObject(testData);
    }

    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userName":
                    setUserName(testData.get(key));
                    break;
                case "password":
                    setPassword(testData.get(key));
                    break;
            }
        }
        adjustObjectVariable();
    }

    private void adjustObjectVariable() {
        userName = userName + System.currentTimeMillis();
    }

}
