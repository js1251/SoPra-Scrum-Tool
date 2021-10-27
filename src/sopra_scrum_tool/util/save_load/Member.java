package sopra_scrum_tool.util.save_load;

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
}
