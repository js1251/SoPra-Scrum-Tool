package sopra_scrum_tool.util.sopra;

import java.time.Duration;

public class Member {

	private String name;
	private int points;
	private Duration estimate = Duration.ZERO;
	private Duration validEstimate = Duration.ZERO;
	private Duration time = Duration.ZERO;
	private Duration validTime = Duration.ZERO;

	public Member(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addTime(int seconds) {
		time = time.plusSeconds(seconds);
	}

	public Duration getTime() {
		return this.time;
	}

	public void addValidTime(int seconds) {
		validTime = validTime.plusSeconds(seconds);
	}

	public Duration getValidTime() {
		return this.validTime;
	}

	public void addEstimate(int seconds) {
		estimate = estimate.plusSeconds(seconds);
	}

	public Duration getEstimate() {
		return this.estimate;
	}

	public void addValidEstimate(int seconds) {
		validEstimate = validEstimate.plusSeconds(seconds);
	}

	public Duration getValidEstimate() {
		return this.validEstimate;
	}
}
