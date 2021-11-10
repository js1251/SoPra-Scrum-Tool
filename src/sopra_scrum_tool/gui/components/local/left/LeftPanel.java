package sopra_scrum_tool.gui.components.local.left;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;
import sopra_scrum_tool.util.gitea.Api;
import sopra_scrum_tool.util.save_load.SoPraTeamSaveFile;
import sopra_scrum_tool.util.sopra.Member;
import sopra_scrum_tool.util.time.Weekday;

public class LeftPanel {
	private JPanel mainPanel;
	
	private JLabel titleLabel;
	private DefaultTableModel tableModel;
	JTextField nameSpaceField, nameField;

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

		nameSpaceField = new JTextField(); // TODO: get from savefile
		nameSpaceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, SoPraScrumTool.defaultFieldHeight));
		nameSpacePanel.add(nameSpaceField);
		
		nameSpaceField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { changeTitle(true); }
			public void removeUpdate(DocumentEvent e) { changeTitle(true); }
			public void changedUpdate(DocumentEvent e) { changeTitle(true); }
		});

		return nameSpacePanel;
	}

	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		nameField = new JTextField(); // TODO: get from savefile
		nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, SoPraScrumTool.defaultFieldHeight));
		namePanel.add(nameField);
		
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { changeTitle(false); }
			public void removeUpdate(DocumentEvent e) { changeTitle(false); }
			public void changedUpdate(DocumentEvent e) { changeTitle(false); }
		});

		return namePanel;
	}
	
	private void changeTitle(boolean left) {
		String[] currentTitle = titleLabel.getText().split("/");
		String input = left? nameSpaceField.getText() : nameField.getText();
		titleLabel.setText(left? input + "/" + currentTitle[1] : currentTitle[0] + "/" + input);
		
		if (left) {
			SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().setNamespace(input);
		} else {
			SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().setName(input);
		}
	}

	private JPanel createWhenPanel() {
		JPanel whenPanel = new JPanel();
		whenPanel.setLayout(new BoxLayout(whenPanel, BoxLayout.X_AXIS));
		whenPanel.setBorder(new TitledBorder(null, "Meeting date", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JComboBox<String> dayBox = new JComboBox<String>(Weekday.allFriendly());
		dayBox.setMaximumSize(new Dimension(30, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(dayBox);
		dayBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Weekday selectedDay = Weekday.values()[dayBox.getSelectedIndex()];
				SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getDate().setWeekday(selectedDay);
			}
		});

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
		hourBoxFrom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getDate().setStartHour(hourBoxFrom.getSelectedIndex());
			}
		});

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
		minuteBoxFrom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getDate().setStartMinute(minuteBoxFrom.getSelectedIndex());
			}
		});

		whenPanel.add(new JLabel("     -     "));

		JComboBox<String> hourBoxTo = new JComboBox<String>(hourOptions);
		hourBoxTo.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(hourBoxTo);
		hourBoxTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getDate().setEndHour(hourBoxTo.getSelectedIndex());
			}
		});

		whenPanel.add(new JLabel(" : "));

		JComboBox<String> minuteBoxTo = new JComboBox<String>(minuteOptions);
		minuteBoxTo.setMaximumSize(new Dimension(10, SoPraScrumTool.defaultFieldHeight));
		whenPanel.add(minuteBoxTo);
		minuteBoxTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave().getDate().setEndMinute(minuteBoxTo.getSelectedIndex());
			}
		});

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
		memberMapPanel.setBorder(new TitledBorder(null, "Team Members", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// a table containing the members
		JTable table = new JTable();
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setEnabled(false);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Gitea Name");
		tableModel.addColumn("Real Name");
		tableModel.addColumn("Is Tutor/ Dozent");
		table.setModel(tableModel);

		// TODO: fetch team members from gitea to fill the table
		// TODO: get the real names by user input or load from file if already set

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberMapPanel.add(scrollPane);
		
		memberMapPanel.add(Box.createRigidArea(new Dimension(0, SoPraScrumTool.defaultPadding)));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		memberMapPanel.add(buttonPanel);
		
		//buttonPanel.add(Box.createHorizontalGlue());
		
		JButton fetchMembersButton = new JButton("Fetch Members");
		buttonPanel.add(fetchMembersButton);
		
		fetchMembersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// the currently open save
					SoPraTeamSaveFile teamSave = SoPraScrumTool.saveLoad.getCurrentSoPraTeamSave();
					
					// fetch the members from the repo
					ArrayList<String> giteaNames = Api.getMembers(teamSave.getNamespace(), teamSave.getName());
					for (String giteaName : giteaNames) {
						// TODO: check if already a member (incase of a re-fetch e.g when member was removed) -> keep real name
						
						// create a new member and add it to the team
						Member newMember = new Member();
						newMember.setGiteaName(giteaName.trim());
						
						// popUp for each member that doesnt already have a real name
						String realName = JOptionPane.showInputDialog("Please provide the real name of " + giteaName + ":");
						if (realName == null || realName.isEmpty()) {
							Errorhandling.error("Member fetch error", "You must provide a real name for every member. Aborting.");
							tableModel.setNumRows(0);
							return;
						}
						
						newMember.setRealName(realName.trim());
						teamSave.addMember(newMember);

						// add a new entry in the member table
						tableModel.addRow(new String[] {giteaName, realName.trim(), "false"});
					}
					
				} catch (Exception exception) {
					Errorhandling.error(exception);
				}
				
			}
		});

		return memberMapPanel;
	}
}
