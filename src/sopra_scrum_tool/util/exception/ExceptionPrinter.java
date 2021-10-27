package sopra_scrum_tool.util.exception;

public class ExceptionPrinter {
	public static String toString(Throwable e) {
		String message = "\n" + getName(e) + ": " + e.getMessage() + "\n";
		message += getCause(e);
		return message;
	}
	
	public static String getName(Throwable e) {
		if (e instanceof CustomException) {
			return ((CustomException) e).name;
		}
		
		// if the exception is not custom get its name by its class
		return e.getClass().getSimpleName();
	}
	
	public static String getCause(Throwable e) {
		String cause = "";
		cause += getStrackTrace(e);
		e = e.getCause();
		
		while (e != null) {
			cause += "Caused by: " + getName(e) + ": " + e.getMessage() + "\n";
			cause += getStrackTrace(e);
			e = e.getCause();
		}
		
		return cause;
	}
	
	private static String getStrackTrace(Throwable e) {
		String stacktrace = "";
		for (StackTraceElement element : e.getStackTrace()) {
			stacktrace += "    at " + element.toString() + "\n";
		}
		
		return stacktrace;
	}
	
	public static String getRootCause(Throwable e) {
		String root = "Invalid Exception";
		
		while (e != null) {
			root = getName(e) + ": " + e.getMessage();
			e = e.getCause();
		}
		
		return root;
	}
}
