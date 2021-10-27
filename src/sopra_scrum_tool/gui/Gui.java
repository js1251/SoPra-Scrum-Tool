package sopra_scrum_tool.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.gui.components.global.TopMenuBar;
import sopra_scrum_tool.gui.components.local.MainPanel;

public class Gui {

	public Gui() {
		// setup basics
		initialize();

		// menubar
		createMenuBar();

		// components
		createMainPanel();

		// finally show GUI
		SoPraScrumTool.frame.pack();
		SoPraScrumTool.frame.setVisible(true);
	}

	private void initialize() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension minSize = new Dimension(800, 700);
		int xStart = (screenDimension.width - minSize.width) / 2;
		int yStart = (screenDimension.height - minSize.height) / 2;

		// re-initialize a new JFrame so JSwing Designer can see it
		SoPraScrumTool.frame = new JFrame();

		SoPraScrumTool.frame.setBounds(xStart, yStart, minSize.width, minSize.height);
		SoPraScrumTool.frame.setMinimumSize(new Dimension(minSize.width, minSize.height));
		SoPraScrumTool.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SoPraScrumTool.frame.setResizable(true);
		SoPraScrumTool.frame.getContentPane()
				.setLayout(new BoxLayout(SoPraScrumTool.frame.getContentPane(), BoxLayout.Y_AXIS));
		SoPraScrumTool.frame.setTitle(SoPraScrumTool.name + " v" + SoPraScrumTool.version);
	}

	private void createMenuBar() {
		TopMenuBar menuBar = new TopMenuBar();
		SoPraScrumTool.frame.setJMenuBar(menuBar.create());
	}

	private void createMainPanel() {
		MainPanel mainPanel = new MainPanel();
		SoPraScrumTool.frame.add(mainPanel.create());
	}
}
