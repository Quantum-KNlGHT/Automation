package pages;


import io.restassured.RestAssured;
import io.restassured.response.Response;
	
public class UserPage {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    
    public Response getUsers() {
        return RestAssured.get(BASE_URL+"/users");
    }

    public Response getTodosByUserId(int userId) {
        return RestAssured.get(BASE_URL+"/todos?userId=" + userId);
    }
    
}