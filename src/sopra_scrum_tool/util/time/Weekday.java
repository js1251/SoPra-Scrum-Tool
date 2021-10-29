package sopra_scrum_tool.util.time;

public enum Weekday {
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday"),
	SUNDAY("Sunday");

	private String friendlyName;
	Weekday(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getFriendlyName() {
		return this.friendlyName;
	}
	
	public static Weekday[] all() {
		return new Weekday[] {
				Weekday.MONDAY,
				Weekday.TUESDAY,
				Weekday.WEDNESDAY,
				Weekday.THURSDAY,
				Weekday.FRIDAY,
				Weekday.SATURDAY,
				Weekday.SUNDAY,
				};
	}
		
	public static String[] allFriendly() {
		return new String[] {
				Weekday.MONDAY.getFriendlyName(),
				Weekday.TUESDAY.getFriendlyName(),
				Weekday.WEDNESDAY.getFriendlyName(),
				Weekday.THURSDAY.getFriendlyName(),
				Weekday.FRIDAY.getFriendlyName(),
				Weekday.SATURDAY.getFriendlyName(),
				Weekday.SUNDAY.getFriendlyName(),
				};
	}
}
