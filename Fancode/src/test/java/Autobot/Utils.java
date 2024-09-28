package Autobot;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Utils {
	
    public static boolean isUserInFanCodeCity(JSONObject user) {
        double lat = user.getJSONObject("address").getJSONObject("geo").getDouble("lat");
        double lng = user.getJSONObject("address").getJSONObject("geo").getDouble("lng");
        return (lat > -40 && lat < 5) && (lng > 5 && lng < 100);
    }

    public static double calculateCompletionPercentage(Response todosResponse) {
        List<Boolean> completedTasks = todosResponse.jsonPath().getList("completed");
        long completedCount = completedTasks.stream().filter(Boolean::booleanValue).count();
        return (double) completedCount / completedTasks.size() * 100;
    }


}
