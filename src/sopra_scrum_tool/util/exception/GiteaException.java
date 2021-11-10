package sopra_scrum_tool.util.exception;

@SuppressWarnings("serial")
public class GiteaException extends CustomException {

	public GiteaException() {
		super("GiteaException");
	}

	public GiteaException(String message) {
		super("GiteaException", message);
	}

	public GiteaException(Throwable cause) {
		super("GiteaException", cause);
	}

	public GiteaException(String message, Throwable cause) {
		super("GiteaException", message, cause);
	}
}