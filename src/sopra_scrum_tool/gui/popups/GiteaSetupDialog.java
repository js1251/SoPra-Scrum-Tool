package sopra_scrum_tool.gui.popups;

import javax.swing.JDialog;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GiteaSetupDialog extends JDialog {
	private JTextField urlTextField;
	private JTextField tokenTextField;

	public GiteaSetupDialog() {
		setTitle("Gitea Setup");
		setMinimumSize(new Dimension(300, 160));
		setResizable(false);
		setLocationRelativeTo(SoPraScrumTool.frame);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		createUrlPanel();
		createTokenPanel();
		createButtonPanel();

		pack();
	}

	@Override
	public void setVisible(boolean visible) {
		SoPraScrumTool.frame.setEnabled(!visible);
		SoPraScrumTool.frame.toFront();
		toFront();
		super.setVisible(visible);
	}

	private void createUrlPanel() {
		JPanel urlPanel = new JPanel();
		urlPanel.setBorder(new TitledBorder(null, "Gitea Url", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.X_AXIS));
		getContentPane().add(urlPanel);

		urlTextField = new JTextField(SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaUrl());
		urlPanel.add(urlTextField);
	}

	private void createTokenPanel() {
		JPanel tokenPanel = new JPanel();
		tokenPanel.setBorder(
				new TitledBorder(null, "Gitea Access Token", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tokenPanel.setLayout(new BoxLayout(tokenPanel, BoxLayout.X_AXIS));
		getContentPane().add(tokenPanel);

		tokenTextField = new JPasswordField(SoPraScrumTool.saveLoad.getCurrentConfig().getGiteaToken());
		tokenPanel.add(tokenTextField);
	}

	private void createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		getContentPane().add(buttonPanel);

		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// save the github url and token
				SoPraScrumTool.saveLoad.getCurrentConfig().setGiteaUrl(urlTextField.getText());
				SoPraScrumTool.saveLoad.getCurrentConfig().setGiteaToken(tokenTextField.getText());
				try {
					SoPraScrumTool.saveLoad.save();
				} catch (Exception exception) {
					Errorhandling.error(exception);
				}

				// close window
				setVisible(false);
			}
		});
		buttonPanel.add(applyButton);

		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// close window
				setVisible(false);
			}
		});
	}
}
