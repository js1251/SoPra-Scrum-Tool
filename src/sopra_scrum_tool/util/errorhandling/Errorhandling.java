package sopra_scrum_tool.util.errorhandling;

import javax.swing.JOptionPane;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.gui.popups.ExceptionDialog;

public class Errorhandling {
	
	public static void info(String header, String message) {
		JOptionPane.showMessageDialog(SoPraScrumTool.frame, message, header, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void warning(String header, String message) {
		JOptionPane.showMessageDialog(SoPraScrumTool.frame, message, header, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void error(String header, String message) {		
		JOptionPane.showMessageDialog(SoPraScrumTool.frame, message, header, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void error(Throwable e) {
		new ExceptionDialog(e).setVisible(true);
	}
}
