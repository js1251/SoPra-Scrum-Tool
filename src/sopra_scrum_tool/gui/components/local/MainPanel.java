package sopra_scrum_tool.gui.components.local;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import sopra_scrum_tool.gui.components.local.left.LeftPanel;
import sopra_scrum_tool.gui.components.local.right.RightPanel;

public class MainPanel {
	private JPanel mainPanel;
	private JSplitPane splitPane;

	public MainPanel() {

	}

	/**
	 * Creates a panel containing all main components of the SoPra Scrum Tool.
	 * 
	 * @return The main panel.
	 */
	public JPanel create() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(10, 10));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(splitPane);

		createLeft();
		createRight();

		return mainPanel;
	}

	private void createLeft() {
		splitPane.setLeftComponent(new LeftPanel().create());
	}

	private void createRight() {
		splitPane.setRightComponent(new RightPanel().create());
	}
}
