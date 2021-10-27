package sopra_scrum_tool.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.exception.ExceptionPrinter;

// TODO: make resizeable and stop text wrapping

// https://web.archive.org/web/20200502073547/http://blue-walrus.com/2015/01/swing-error-dialog-with-exception-displayer/
@SuppressWarnings("serial")
public class ExceptionDialog extends JDialog {

	private int dialogWidth = 500;
	private int dialogHeight = 150;

	private JLabel iconLabel = new JLabel();

	// is error panel opened up
	private boolean open = false;

	private JLabel errorLabel = new JLabel();
	private JTextArea errorTextArea = new JTextArea("");

	private JTextArea exceptionTextArea = new JTextArea("");
	private JScrollPane exceptionTextAreaSP = new JScrollPane();

	private JButton okButton = new JButton("OK");
	private JButton viewButton = new JButton("Show Details");

	private JPanel topPanel = new JPanel(new BorderLayout());

	public ExceptionDialog(Throwable exception) {
		String name = ExceptionPrinter.getName(exception);
		String description = ExceptionPrinter.getRootCause(exception);
		String cause = ExceptionPrinter.toString(exception);

		init(name, description, cause);
	}

	private void init(String errorLabelText, String errorDescription, String exceptionStack) {
		setSize(dialogWidth, dialogHeight);
		setResizable(false);
		setLocationRelativeTo(SoPraScrumTool.frame);
		setModal(true);

		errorTextArea.setText(errorDescription);
		errorTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		errorLabel.setText(errorLabelText);

		if (exceptionStack.isEmpty()) {
			exceptionStack = "No further details to show.";
		}

		exceptionTextArea.setText(exceptionStack);
		exceptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		exceptionTextArea.setLineWrap(true);
		exceptionTextArea.setWrapStyleWord(true);
		exceptionTextArea.setEditable(false);

		exceptionTextAreaSP = new JScrollPane(exceptionTextArea);
		exceptionTextAreaSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		exceptionTextAreaSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		iconLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		iconLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));

		setupUI();
		setUpListeners();
	}

	public void setVisible(boolean visible) {
		SoPraScrumTool.frame.setEnabled(!visible);
		SoPraScrumTool.frame.toFront();
		this.toFront();
		super.setVisible(visible);
	}

	public void setupUI() {
		this.setTitle("Error");

		errorTextArea.setLineWrap(true);
		errorTextArea.setWrapStyleWord(true);
		errorTextArea.setEditable(false);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(okButton);
		buttonPanel.add(viewButton);

		errorTextArea.setBackground(iconLabel.getBackground());
		JScrollPane textAreaSP = new JScrollPane(errorTextArea);
		textAreaSP.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

		errorLabel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		exceptionTextArea.setPreferredSize(new Dimension(100, 100));
		topPanel.add(iconLabel, BorderLayout.WEST);

		JPanel p = new JPanel(new BorderLayout());
		p.add(errorLabel, BorderLayout.NORTH);
		p.add(textAreaSP);

		topPanel.add(p);

		this.add(topPanel);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setUpListeners() {
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExceptionDialog.this.setVisible(false);
			}
		});

		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (open) {
					viewButton.setText("Show Details");
					topPanel.remove(exceptionTextAreaSP);
					ExceptionDialog.this.setSize(dialogWidth, dialogHeight);
					topPanel.revalidate();
					open = false;
				} else {
					viewButton.setText("Hide Details");
					topPanel.add(exceptionTextAreaSP, BorderLayout.SOUTH);
					ExceptionDialog.this.setSize(dialogWidth, dialogHeight + 100);
					topPanel.revalidate();
					open = true;
				}
			}
		});
	}
}
