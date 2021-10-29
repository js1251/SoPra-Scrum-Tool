package sopra_scrum_tool.util.sopra;

import java.time.Duration;
import java.util.ArrayList;

public class Team {
	private String nameSpace;
	private String repoName;
	private ArrayList<Member> members;

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getNameSpace() {
		return this.nameSpace;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getRepoName() {
		return this.repoName;
	}

	public void addMember(Member member) {
		this.members.add(member);
	}

	public void removeMember(Member member) {
		this.members.remove(member);
	}

	public ArrayList<Member> getAllMembers() {
		return this.members;
	}

	public Duration getOverallEstimate() {
		Duration overallEstimate = Duration.ZERO;

		for (Member member : members) {
			overallEstimate = overallEstimate.plus(member.getOverallEstimate());
		}

		return overallEstimate;
	}

	public Duration getOverallTime() {
		Duration overallTime = Duration.ZERO;

		for (Member member : members) {
			overallTime = overallTime.plus(member.getOverallTime());
		}

		return overallTime;
	}
}
