package sopra_scrum_tool.gui.components.global.top_menu_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sopra_scrum_tool.gui.popups.GiteaSetupDialog;

public class TopMenuBarGiteaSetupListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		GiteaSetupDialog giteaSetup = new GiteaSetupDialog();
		giteaSetup.setVisible(true);
	}
}
