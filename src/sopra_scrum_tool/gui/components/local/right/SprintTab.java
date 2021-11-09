package sopra_scrum_tool.gui.components.local.right;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.sopra.Member;

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
		
		for (Member teamMember : SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getMembers()) {
			attendancePanel.add(new JCheckBox(teamMember.getRealName()));
			attendancePanel.add(Box.createRigidArea(new Dimension(SoPraScrumTool.defaultPadding, 0)));
			attendancePanel.add(Box.createHorizontalGlue());
			memberTabs.addTab(teamMember.getRealName(), new JPanel());
		}
		
		mainPanel.add(memberTabs);

		return mainPanel;
	}
}
