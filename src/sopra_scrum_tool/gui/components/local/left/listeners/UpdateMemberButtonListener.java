package sopra_scrum_tool.gui.components.local.left.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;
import sopra_scrum_tool.util.gitea.Api;
import sopra_scrum_tool.util.sopra.Member;
import sopra_scrum_tool.util.time.DurationPrinter;

public class UpdateMemberButtonListener implements ActionListener {

	private JTextField textField;
	private DefaultTableModel tableModel;

	public UpdateMemberButtonListener(JTextField textField, DefaultTableModel tableModel) {
		this.textField = textField;
		this.tableModel = tableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// fetch current user (which is going to be the tutor)
			String tutorName = Api.getCurrentUser();
			textField.setText(tutorName);

			SoPraScrumTool.openTeam.setTutor(tutorName);
			SoPraScrumTool.openTeam.initialize();

			for (Member member : SoPraScrumTool.openTeam.getAllMembers()) {
				String name = member.getName();
				String estimate = DurationPrinter.print(member.getEstimate());
				String validEstimate = DurationPrinter.print(member.getValidEstimate());

				String time = DurationPrinter.print(member.getTime());
				String validTime = DurationPrinter.print(member.getValidTime());

				tableModel.addRow(new String[] { name, estimate, validEstimate, time, validTime });
			}
		} catch (Exception exception) {
			Errorhandling.error(exception);
		}

	}
}
