package sopra_scrum_tool.util.sopra;

import java.time.Duration;

public class Member {

	private String giteaName;
	private String realName;
	// TODO: points, estimate, actual time, etc.
	// TODO: use JSONObject instead ?
	// TODO: add toString()
	// TODO: add fromString()

	public void setGiteaName(String giteaName) {
		this.giteaName = giteaName;
	}

	public String getGiteaName() {
		return giteaName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return realName;
	}
	
	public Duration getOverallEstimate() {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public Duration getOverallTime() {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public Duration getEstimate(Sprint sprint) {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public Duration getTime(Sprint sprint) {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public Duration getEstimate(Issue issue) {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public Duration getTime(Issue issue) {
		// TODO: implement
		return Duration.ZERO;
	}
	
	public float getOverallPoints() {
		// TODO: implement
		return 0;
	}
	
	public float getPoints(Sprint sprint) {
		// TODO: implement
		return 0;
	}
}
