package sopra_scrum_tool.gui.components.local.right;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

// TODO: how to remove sprint tabs ?
public class RightPanel {

	private JPanel mainPanel;

	public JPanel create() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new TitledBorder(null, "Sprint Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		mainPanel.add(createSprintTabs());
		return mainPanel;
	}

	private JTabbedPane createSprintTabs() {
		JTabbedPane sprintTabs = new JTabbedPane();
		
		// TODO: load sprints from savefile
		
		// a button to create new sprints
		sprintTabs.addTab("+", new JPanel());

		sprintTabs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (sprintTabs.getSelectedIndex() == sprintTabs.getTabCount() - 1) {
					int sprintCount = sprintTabs.getTabCount();
					String sprintName = "Sprint";
						sprintName += sprintCount > 9 ? sprintCount : "0" + sprintCount;
						
					sprintTabs.addTab(sprintName, new SprintTab().create());
				}
			}
		});

		return sprintTabs;
	}
}
