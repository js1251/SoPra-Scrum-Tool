package sopra_scrum_tool.gui.components.global.top_menu_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;
import sopra_scrum_tool.util.save_load.SoPraTeamSaveFile;

public class TopMenuBarOpenListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser teamSaveChooser = new JFileChooser();
		// teamSaveChooser.setCurrentDirectory(new File(lastOpened));
		teamSaveChooser.setFileFilter(new FileNameExtensionFilter(
				"SoPra Team savefile (" + SoPraTeamSaveFile.fileExtension + ")", SoPraTeamSaveFile.fileExtension));
		teamSaveChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		teamSaveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = teamSaveChooser.showOpenDialog(SoPraScrumTool.frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			String savePath = teamSaveChooser.getSelectedFile().getAbsolutePath();

			// check for valid file type
			if (!savePath.endsWith(SoPraTeamSaveFile.fileExtension)) {
				Errorhandling.error("Invalid file",
						"Please select a valid " + SoPraTeamSaveFile.fileExtension + " savefile!");
				return;
			}

			// load savePath
			SoPraScrumTool.saveLoad.loadTeamSave(savePath);
		}
	}
}
