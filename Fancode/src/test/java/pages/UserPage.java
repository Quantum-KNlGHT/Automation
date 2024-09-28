package pages;


import io.restassured.RestAssured;
import io.restassured.response.Response;
	
public class UserPage {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/users";
    
    public Response getUsers() {
        return RestAssured.get(BASE_URL);
    }

    public Response getTodosByUserId(int userId) {
        return RestAssured.get("http://jsonplaceholder.typicode.com/todos?userId=" + userId);
    }
    
}