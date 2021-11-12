package sopra_scrum_tool.util.time;

import java.time.Duration;

public class DurationPrinter {
	public static String print(Duration duration) {
		return duration.toString()
	            .substring(2) // remove "PT" prefix
	            .replaceAll("(\\d[HMS])(?!$)", "$1 ") // add spaces between units
	            .toLowerCase();
	}
}
