package sopra_scrum_tool.gui.components.local.right;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

public class SprintTab {
	private JPanel mainPanel;

	public SprintTab() {

	}

	public JPanel create() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel attendancePanel = new JPanel();
		attendancePanel.setLayout(new BoxLayout(attendancePanel, BoxLayout.X_AXIS));
		attendancePanel.setBorder(new TitledBorder(null, "Attendance", TitledBorder.TOP, TitledBorder.TOP, null, null));
		mainPanel.add(attendancePanel);
		
		JTabbedPane memberTabs = new JTabbedPane();
		mainPanel.add(memberTabs);

		return mainPanel;
	}
}
