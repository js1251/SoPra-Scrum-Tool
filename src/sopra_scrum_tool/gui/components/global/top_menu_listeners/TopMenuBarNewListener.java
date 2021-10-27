package sopra_scrum_tool.gui.components.global.top_menu_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.save_load.SoPraTeamSaveFile;

public class TopMenuBarNewListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		SoPraTeamSaveFile newTeamSave = new SoPraTeamSaveFile();
		SoPraScrumTool.saveLoad.setCurrentSoPraTeamSave(newTeamSave);
	}
}
