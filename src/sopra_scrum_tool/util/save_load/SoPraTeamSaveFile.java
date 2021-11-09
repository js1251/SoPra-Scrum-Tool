package sopra_scrum_tool.util.save_load;

import java.util.ArrayList;

import sopra_scrum_tool.util.sopra.Member;
import sopra_scrum_tool.util.time.WeeklyDate;

public class SoPraTeamSaveFile {
	public static String fileExtension = ".soprateam";

	private String savePath;
	private String nameSpace;
	private String name;
	private WeeklyDate date = new WeeklyDate();
	private ArrayList<Member> members = new ArrayList<Member>();

	// TODO: use JSONObject instead

	/**
	 * Sets the save path.
	 * 
	 * @param savePath The save path the save will be written to.
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * Gets the save path.
	 * 
	 * @return The save path.
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * Sets the namespace of the team. Eg. "sopra-ws2021"
	 * 
	 * @param nameSpace The name of the namespace.
	 */
	public void setNamespace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	/**
	 * Gets the namespace.
	 * 
	 * @return The namespace.
	 */
	public String getNamespace() {
		return nameSpace;
	}

	/**
	 * Sets the name of the team. Eg. "sopra06"
	 * 
	 * @param name The name of the team.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	public void setDate(WeeklyDate date) {
		this.date = date;
	}

	public WeeklyDate getDate() {
		return this.date;
	}

	/**
	 * Adds a member to all memebers
	 * 
	 * @param member The new member.
	 */
	public void addMember(Member member) {
		members.add(member);
	}

	/**
	 * Gets a list of all members.
	 * 
	 * @return A list of all memebers.
	 */
	public ArrayList<Member> getMembers() {
		return members;
	}

	/**
	 * Loads the savefile from the specified path.
	 */
	public void load() {
		// TODO: implement loading
	}

	/**
	 * Saves the savefile to the save folder structure
	 */
	public void save() {
		// TODO: implement saving
	}
}
