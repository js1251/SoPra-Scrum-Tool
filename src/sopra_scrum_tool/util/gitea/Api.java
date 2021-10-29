package sopra_scrum_tool.util.gitea;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sopra_scrum_tool.SoPraScrumTool;

public class Api {
	public static String GET(String url) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		
		String fullUrl = "https://" + SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaUrl() + "/api/v1/" + url;
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(fullUrl))
				.headers("accept", "application/json")
				.headers("Authorization", "token " + SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaToken())
				.headers("Content-type", "application/json")
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body().toString();
		} catch (Exception exception) {
			throw new Exception("Unable to reach " + fullUrl + ". Invalid token?");
		}
		
	}

	public static ArrayList<String> getMembers(String nameSpace, String repoName) throws Exception {
		String teamsRaw = GET("orgs/" + nameSpace + "/teams");
		JSONArray teams = new JSONArray(teamsRaw);

		int id = -1;
		for (int i = 0; i < teams.length(); i++) {
			JSONObject team = teams.getJSONObject(i);

			String name = team.getString("name");
			if (name.equals(repoName)) {
				id = team.getInt("id");
				break;
			}
		}

		if (id == -1) {
			throw new JSONException("\"id\" is not a valid key for this JSON object");
		}

		String membersRaw = GET("teams/" + id + "/members");
		JSONArray members = new JSONArray(membersRaw);

		ArrayList<String> memberStrings = new ArrayList<String>();
		for (int i = 0; i < members.length(); i++) {
			JSONObject member = members.getJSONObject(i);
			memberStrings.add(member.getString("login"));
		}

		return memberStrings;
	}

	public static Duration getTime(String nameSpace, String repoName, String username) throws Exception {
		String timesRaw = GET("repos/" + nameSpace + "/" + repoName + "/times");
		JSONArray times = new JSONArray(timesRaw);

		long seconds = 0;
		for (int i = 0; i < times.length(); i++) {
			JSONObject member = times.getJSONObject(i);

			if (member.getString("user_name").equals(username)) {
				seconds += member.getInt("time");
			}
		}
		Duration timeSpent = Duration.ofSeconds(seconds);
		return timeSpent;
	}
	
	/*
	public static String getTimeString(String nameSpace, String repoName, String username) throws Exception {
		long s = getTime(nameSpace, repoName, username).getSeconds();
		int days = (int) (s / 86400f);
		int hours = (int) (s / 3600f) % 24;
		int minutes = (int) (s / 3600f) % 60;
		int seconds = (int) (s) % 60;
		return String.format("%dd %dh %02dm %02ds", days, hours, minutes, seconds);
	}
	*/
}
