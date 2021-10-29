package sopra_scrum_tool.util.time;

import java.time.Duration;

public class WeeklyDate {
	private Weekday weekday = Weekday.MONDAY;
	private int startHour = 0;
	private int startMinute = 0;
	private int endHour = 0;
	private int endMinute = 0;

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public Weekday getWeekday() {
		return this.weekday;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartHour() {
		return this.startHour;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getStartMinute() {
		return this.startMinute;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndHour() {
		return this.endHour;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public int getEndMinute() {
		return this.endMinute;
	}
	
	public String toString() {
		return weekday.getFriendlyName() + ", "
				+ (startHour > 9 ? startHour : "0" + startHour) + ":"
				+ (startMinute > 9 ? startMinute : "0" + startMinute) + " - "
				+ (endHour > 9 ? endHour : "0" + endHour) + ":"
				+ (endMinute > 9 ? endMinute : "0" + endMinute);		
	}
	
	public Duration getLength() {
		Duration length = Duration.ZERO;
		length = length.plusHours(endHour - startHour);
		length = length.plusMinutes(endMinute - startMinute);
		
		return length;
	}
}
