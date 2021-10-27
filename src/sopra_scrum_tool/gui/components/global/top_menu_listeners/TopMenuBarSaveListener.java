package sopra_scrum_tool.gui.components.global.top_menu_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;

public class TopMenuBarSaveListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			SoPraScrumTool.saveLoad.save();
		} catch (Exception exception) {
			Errorhandling.error(exception);
		}
	}
}
