package sopra_scrum_tool.util.gitea;

import java.io.IOException;
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
import sopra_scrum_tool.util.errorhandling.Errorhandling;

public class Api {
	public Api() {
		try {
			for (String member : getMembers("sopra-ws2021", "sopra06")) {
				long s = getTime("sopra-ws2021", "sopra06", member).getSeconds();				
				System.out.println(member + " spent " + String.format("%dd %dh %02dm %02ds", s / 86400, (s / 3600) % 24, (s % 3600) / 60, (s % 60)));
			}
		} catch (Exception exception) {
			Errorhandling.error(exception);
		}
	}

	public static String GET(String url) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://" + SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaUrl() + "api/v1/" + url))
				.headers("accept", "application/json")
				.headers("Authorization", "token " + SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaToken())
				.headers("Content-type", "application/json")
				.build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body().toString();
	}

	public static ArrayList<String> getMembers(String nameSpace, String repoName)
			throws JSONException, InterruptedException, IOException {
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

	public static Duration getTime(String nameSpace, String repoName, String username) throws IOException, InterruptedException {
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
}

//https://git.sopranium.de/api/v1/repos/sopra-ws2122/sopra12
//https://git.sopranium.de/api/v1/repos/sopra-ws2122/sopra12/issues
//https://git.sopranium.de/api/v1/repos/sopra-ws2122/sopra12/issues?state=open
//https://git.sopranium.de/api/v1/repos/sopra-ws2122/sopra12/issues?state=closed
//https://git.sopranium.de/api/v1/repos/sopra-ws2122/sopra12/times?user_name=name
//https://git.sopranium.de/api/v1/orgs/sopra-ws2122/teams -> get id
//https://git.sopranium.de/api/v1/teams/ID/members <- insert id
