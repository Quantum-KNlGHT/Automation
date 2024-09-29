package stepDefinitions;

import java.util.ArrayList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.UserPage;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import Autobot.Utils;

public class MainSteps {

	private List<String> results = new ArrayList<>();
	UserPage userPage = new UserPage();
	Response userResponse;
	Response todosResponse;
	List<Integer> userIdsInFanCodeCity = new ArrayList<>();

	@Given("User has the todo tasks")
	public void i_have_the_users_from_the_api() throws Exception {
		userResponse = userPage.getUsers();
		//System.out.println(userResponse.asPrettyString());
		Assert.assertTrue(userResponse.statusCode() == 200);
	}

	@When("User belongs to the city FanCode")
	public void i_check_their_todos() throws Exception {
		// Get the user data as a JSONArray
		JSONArray users = new JSONArray(userResponse.asString());

		// Iterate through each user data from the JSONArray
		for (int i = 0; i < users.length(); i++) {
			JSONObject user = users.getJSONObject(i);
			//System.out.println("Object" + user);
			if (Utils.isUserInFanCodeCity(user)) { // Pass the user object
				int userId = user.getInt("id"); // Get the user ID from the user object
				userIdsInFanCodeCity.add(userId);
			}
		}
		//System.out.println("IDs: " + userIdsInFanCodeCity);
	}

	@Then("User Completed task percentage should be greater than 50%")
	public void the_completed_task_percentage_should_be_greater_than_50_for_each_user() {
		for (int userId : userIdsInFanCodeCity) {
			// Assuming calculateCompletionPercentage is a method that takes a user ID
			todosResponse = userPage.getTodosByUserId(userId); // Fetch todos for the user
			double completionPercentage = Utils.calculateCompletionPercentage(todosResponse);

			if (completionPercentage > 50) {
				System.out.println("User ID " + userId + " has a completion percentage of " + completionPercentage
						+ "%, which is more than 50.");
			} else {
				Assert.fail(" All the users of City `FanCode` does not have more than half of their todos task completed");
			}
		}
	}
}
