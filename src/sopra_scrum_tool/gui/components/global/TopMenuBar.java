package sopra_scrum_tool.gui.components.global;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarOpenListener;
import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarSaveListener;
import sopra_scrum_tool.util.errorhandling.Errorhandling;

public class TopMenuBar {
	private JMenuBar menuBar;

	public TopMenuBar() {

	}

	public JMenuBar create() {
		menuBar = new JMenuBar();
		menuBar.add(fileMenu());
		menuBar.add(settingMenu());
		menuBar.add(aboutMenu());

		return menuBar;
	}

	private JMenu fileMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem newItem = new JMenuItem("New Team");
		fileMenu.add(newItem);
		// TODO: implement new team

		JMenuItem openItem = new JMenuItem("Open Team");
		openItem.addActionListener(new TopMenuBarOpenListener());
		fileMenu.add(openItem);
		
		JMenuItem closeItem = new JMenuItem("Close Team");
		fileMenu.add(closeItem);
		// TODO: implement close team

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.addActionListener(new TopMenuBarSaveListener());
		fileMenu.add(saveItem);
		
		JMenuItem saveAsItem = new JMenuItem("SaveAs");
		fileMenu.add(saveAsItem);
		// TODO: implement save as

		return fileMenu;
	}

	private JMenu settingMenu() {
		JMenu settingsMenu = new JMenu("Settings");

		JMenuItem giteaItem = new JMenuItem("Gitea Setup");
		settingsMenu.add(giteaItem);
		// TODO: implement gitea setup

		settingsMenu.addSeparator();
		JMenu lookAndFeelSubMenu = new JMenu("Look and Feel");

		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			JMenuItem lookAndFeelItem = new JMenuItem(info.getName());
			lookAndFeelSubMenu.add(lookAndFeelItem);

			lookAndFeelItem.addActionListener(new ActionListener() {
				// TODO: extract into its own class
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						UIManager.setLookAndFeel(info.getClassName());
						SwingUtilities.updateComponentTreeUI(SoPraScrumTool.frame);
						SoPraScrumTool.frame.pack();

						SoPraScrumTool.saveLoad.getCurrentConfig().setLookAndFeel(info.getClassName());
					} catch (Exception exception) {
						Errorhandling.error(exception);
					}
				}
			});
		}

		settingsMenu.add(lookAndFeelSubMenu);

		return settingsMenu;
	}

	private JMenu aboutMenu() {
		JMenu aboutMenu = new JMenu("About");

		JMenuItem infoItem = new JMenuItem("Info");
		aboutMenu.add(infoItem);

		return aboutMenu;
	}
}
