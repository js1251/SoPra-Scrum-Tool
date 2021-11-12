package sopra_scrum_tool.util.sopra;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sopra_scrum_tool.util.gitea.Api;

public class Team {
	private String owner;
	private String repo;
	private String tutor;
	private HashMap<String, Member> members = new HashMap<String, Member>();

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getRepo() {
		return this.repo;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getTutor() {
		return this.tutor;
	}

	public ArrayList<Member> getAllMembers() {
		return new ArrayList<Member>(this.members.values());
	}

	public void initialize() throws Exception {
		// find all members of this team
		findMembers();

		// fetch their times
		getMemberTimes();
	}

	private void findMembers() throws Exception {
		// get all teams and compare their repoName
		String endPoint = "orgs/" + this.owner + "/teams";
		JSONArray teams = new JSONArray(Api.GET(endPoint));

		int id = -1;
		for (int i = 0; i < teams.length(); i++) {
			JSONObject team = teams.getJSONObject(i);

			// find the id of the current repo
			String name = team.getString("name");
			if (name.equals(this.repo)) {
				id = team.getInt("id");
				break;
			}
		}

		if (id == -1) {
			throw new JSONException("\"id\" is not a valid key for this JSON object");
		}

		// find all members by id
		String membersRaw = Api.GET("teams/" + id + "/members");
		JSONArray members = new JSONArray(membersRaw);

		for (int i = 0; i < members.length(); i++) {
			JSONObject member = members.getJSONObject(i);
			String memberName = member.getString("username");

			// ignore tutor
			if (memberName.equals(this.tutor)) {
				continue;
			}

			Member newMember = new Member(memberName);
			this.members.put(memberName, newMember);
		}
	}

	private void getMemberTimes() throws Exception {
		// get all teams and compare their repoName
		String endPoint = "repos/" + this.owner + "/" + this.repo + "/times";
		JSONArray times = new JSONArray(Api.GET(endPoint));

		for (int i = 0; i < times.length(); i++) {
			JSONObject time = times.getJSONObject(i);
			int issueId = time.getInt("issue_id");
			int seconds = time.getInt("time");
			String userName = time.getString("user_name");

			Member member = members.get(userName);
			member.addTime(seconds);
		}

	}
}
