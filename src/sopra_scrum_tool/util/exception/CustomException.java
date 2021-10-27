package sopra_scrum_tool.util.exception;

@SuppressWarnings("serial")
public abstract class CustomException extends Exception {
	public String name;

	protected CustomException(String name) {
		super();
		this.name = name;
	};

	protected CustomException(String name, String message) {
		super(message);
		this.name = name;
	}

	protected CustomException(String name, Throwable cause) {
		super(cause);
		this.name = name;
	}

	protected CustomException(String name, String message, Throwable cause) {
		super(message, cause);
		this.name = name;
	}
}