package sopra_scrum_tool.gui.components.global;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarGiteaSetupListener;
import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarLookAndFeelListener;
import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarOpenListener;
import sopra_scrum_tool.gui.components.global.top_menu_listeners.TopMenuBarSaveListener;

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

		// new team option
		JMenuItem newItem = new JMenuItem("New Team");
		fileMenu.add(newItem);
		// TODO: implement new team

		// open team option
		JMenuItem openItem = new JMenuItem("Open Team");
		openItem.addActionListener(new TopMenuBarOpenListener());
		fileMenu.add(openItem);

		// close team option
		JMenuItem closeItem = new JMenuItem("Close Team");
		fileMenu.add(closeItem);
		// TODO: implement close team

		// save team option
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.addActionListener(new TopMenuBarSaveListener());
		fileMenu.add(saveItem);

		// save team as option
		JMenuItem saveAsItem = new JMenuItem("SaveAs");
		fileMenu.add(saveAsItem);
		// TODO: implement save as

		// export team to .md file(s)
		JMenuItem exportItem = new JMenuItem("Export to .md");
		fileMenu.add(exportItem);
		// TODO: implement export to .md

		return fileMenu;
	}

	private JMenu settingMenu() {
		JMenu settingsMenu = new JMenu("Settings");

		// gitea setup option
		JMenuItem giteaItem = new JMenuItem("Gitea Setup");
		giteaItem.addActionListener(new TopMenuBarGiteaSetupListener());
		settingsMenu.add(giteaItem);

		// some spacer to indicate submenu
		settingsMenu.addSeparator();

		// look and feel submenu
		JMenu lookAndFeelSubMenu = new JMenu("Look and Feel");

		// add an option for each available look and feel
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			JMenuItem lookAndFeelItem = new JMenuItem(info.getName());
			lookAndFeelSubMenu.add(lookAndFeelItem);
			lookAndFeelItem.addActionListener(new TopMenuBarLookAndFeelListener(info));
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
