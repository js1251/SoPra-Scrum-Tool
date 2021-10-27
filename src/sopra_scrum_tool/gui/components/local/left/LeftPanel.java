package sopra_scrum_tool.gui.components.local.left;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import sopra_scrum_tool.SoPraScrumTool;

public class LeftPanel {
	private JPanel mainPanel;

	private JLabel titleLabel;

	public JPanel create() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new TitledBorder(null, "Team Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// TODO: left align title somehow
		titleLabel = new JLabel("New Namespace/New Team"); // TODO: get from savefile
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mainPanel.add(titleLabel);

		mainPanel.add(createNameSpacePanel());
		mainPanel.add(createNamePanel());
		mainPanel.add(createWhenPanel());
		mainPanel.add(createWherePanel());
		mainPanel.add(createMemberMapPanel());

		return mainPanel;
	}

	private JPanel createNameSpacePanel() {
		JPanel nameSpacePanel = new JPanel();
		nameSpacePanel.setLayout(new BoxLayout(nameSpacePanel, BoxLayout.X_AXIS));
		nameSpacePanel
				.setBorder(new TitledBorder(null, "NameSpace", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTextField nameSpaceField = new JTextField(); // TODO: get from savefile
		nameSpaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, SoPraScrumTool.defaultFieldHeight));
		nameSpacePanel.add(nameSpaceField);

		return nameSpacePanel;
	}

	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTextField nameField = new JTextField(); // TODO: get from savefile
		nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, SoPraScrumTool.defaultFieldHeight));
		namePanel.add(nameField);

		return namePanel;
	}

	private JPanel createWhenPanel() {
		JPanel whenPanel = new JPanel();
		whenPanel.setLayout(new BoxLayout(whenPanel, BoxLayout.X_AXIS));
		whenPanel.setBorder(new TitledBorder(null, "Meeting date", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		String dayOptions[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		JComboBox<String> dayBox = new JComboBox<String>(dayOptions);
		dayBox.setMaximumSize(new Dimension(30, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(dayBox);

		whenPanel.add(Box.createHorizontalGlue());

		String hourOptions[] = new String[24];
		for (int i = 0; i < hourOptions.length; i++) {
			hourOptions[i] = i + "";
			if (i < 10) {
				hourOptions[i] = "0" + hourOptions[i];
			}
		}
		JComboBox<String> hourBoxFrom = new JComboBox<String>(hourOptions);
		hourBoxFrom.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(hourBoxFrom);

		whenPanel.add(new JLabel(" : "));

		String minuteOptions[] = new String[60];
		for (int i = 0; i < minuteOptions.length; i++) {
			minuteOptions[i] = i + "";
			if (i < 10) {
				minuteOptions[i] = "0" + minuteOptions[i];
			}
		}
		JComboBox<String> minuteBoxFrom = new JComboBox<String>(minuteOptions);
		minuteBoxFrom.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(minuteBoxFrom);

		whenPanel.add(new JLabel("     -     "));

		JComboBox<String> hourBoxTo = new JComboBox<String>(hourOptions);
		hourBoxTo.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(hourBoxTo);

		whenPanel.add(new JLabel(" : "));

		JComboBox<String> minuteBoxTo = new JComboBox<String>(minuteOptions);
		minuteBoxTo.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(minuteBoxTo);

		return whenPanel;
	}

	private JPanel createWherePanel() {
		JPanel wherePanel = new JPanel();
		wherePanel.setLayout(new BoxLayout(wherePanel, BoxLayout.X_AXIS));
		wherePanel.setBorder(
				new TitledBorder(null, "Meeting location", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTextField nameField = new JTextField(); // TODO: get from savefile
		nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, SoPraScrumTool.defaultFieldHeight));
		wherePanel.add(nameField);

		return wherePanel;
	}

	private JPanel createMemberMapPanel() {
		JPanel memberMapPanel = new JPanel();
		memberMapPanel.setLayout(new BoxLayout(memberMapPanel, BoxLayout.Y_AXIS));
		memberMapPanel
				.setBorder(new TitledBorder(null, "Team Members", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// a table containing the members
		JTable table = new JTable();
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setEnabled(false);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Gitea Name");
		tableModel.addColumn("Real Name");
		table.setModel(tableModel);

		// TODO: fetch team members from gitea to fill the table
		// TODO: get the real names by user input or load from file if already set

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberMapPanel.add(scrollPane);

		return memberMapPanel;
	}
}
