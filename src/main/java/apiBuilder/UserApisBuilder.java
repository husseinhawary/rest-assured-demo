package apiBuilder;

import org.json.simple.JSONObject;

public class UserApisBuilder {

    public JSONObject postUserPayload(String userName, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        JSONObject request = new JSONObject();
        request.put("username", userName);
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("email", email);
        request.put("password", password);
        request.put("phone", phone);
        request.put("userStatus", userStatus);
        return request;
    }

    public JSONObject updateUserPayload(String userName, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        JSONObject request = new JSONObject();
        request.put("username", userName);
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("email", email);
        request.put("password", password);
        request.put("phone", phone);
        request.put("userStatus", userStatus);
        return request;
    }
}
