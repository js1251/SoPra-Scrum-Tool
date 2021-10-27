package sopra_scrum_tool.util.exception;

@SuppressWarnings("serial")
public class ConfigException extends CustomException {

	public ConfigException() {
		super("BspProtectionException");
	}

	public ConfigException(String message) {
		super("BspProtectionException", message);
	}

	public ConfigException(Throwable cause) {
		super("BspProtectionException", cause);
	}

	public ConfigException(String message, Throwable cause) {
		super("BspProtectionException", message, cause);
	}
}