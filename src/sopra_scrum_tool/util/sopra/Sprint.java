package sopra_scrum_tool.util.sopra;

import java.util.ArrayList;
import java.util.Date;

import sopra_scrum_tool.util.time.WeeklyDate;

public class Sprint {
	private int number;
	private String name;
	private Date start;
	private Date end;

	public static WeeklyDate weeklyDate = new WeeklyDate();

	private ArrayList<Issue> issues;

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	// TODO: replace by api request ?
	public void setName(String name) {
		this.name = name;
	}

	// TODO: replace by api request ?
	public String getName() {
		return this.name;
	}

	// TODO: replace by api request ?
	public void setStart(Date start) {
		this.start = start;
	}

	// TODO: replace by api request ?
	public Date getStart(Date start) {
		return this.start;
	}

	// TODO: replace by api request ?
	public void setEnd(Date end) {
		this.end = end;
	}

	// TODO: replace by api request ?
	public Date getEnd() {
		return this.end;
	}

	// TODO: replace by api request ?
	public void addIssue(Issue issue) {
		this.issues.add(issue);
	}

	public void removeIssue(Issue issue) {
		this.issues.remove(issue);
	}

	// TODO: replace by api request ?
	public ArrayList<Issue> getAllIssues() {
		return this.issues;
	}
}
