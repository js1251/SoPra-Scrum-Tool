package sopra_scrum_tool.gui.components.local.right;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.sopra.Member;

public class RightPanel {

	private JPanel mainPanel;

	public JPanel create() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new TitledBorder(null, "Sprint Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		// TODO: add a new Tab per memeber
		ArrayList<Member> members = SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getMembers();

		mainPanel.add(createSprintTabs());
		return mainPanel;
	}

	private JTabbedPane createSprintTabs() {
		JTabbedPane sprintTabs = new JTabbedPane();
		sprintTabs.addTab("Test Sprint", new SprintTab().create());
		sprintTabs.addTab("+", new JPanel());

		sprintTabs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (sprintTabs.getSelectedIndex() == sprintTabs.getTabCount() - 1) {
					// TODO: add new tab
					// TODO: how to remove tabs ?
					System.out.println("Add new sprint tab");
				}
			}
		});

		return sprintTabs;
	}
}
