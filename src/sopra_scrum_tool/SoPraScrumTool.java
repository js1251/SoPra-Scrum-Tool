package sopra_scrum_tool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import sopra_scrum_tool.gui.Gui;
import sopra_scrum_tool.util.errorhandling.Errorhandling;
import sopra_scrum_tool.util.save_load.SaveLoad;

public class SoPraScrumTool {
	public static JFrame frame = new JFrame();

	public static String name = "SoPra Scrum Tool";
	public static String version = "2021.10.27";

	public static SaveLoad saveLoad;

	public static int defaultFieldHeight = 25;

	public static void main(String[] args) {
		try {
			saveLoad = new SaveLoad();
			saveLoad.loadConfig();
			saveLoad.loadLastTeamSave();

			// load the saved look and feel. If its not yet saved default to cross platform
			String lookAndFeel = saveLoad.getCurrentConfig().getLookAndFeel();
			lookAndFeel = lookAndFeel != null ? lookAndFeel : UIManager.getCrossPlatformLookAndFeelClassName();

			UIManager.setLookAndFeel(lookAndFeel);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					new Gui();
				}
			});
		} catch (Throwable exception) {
			Errorhandling.error(exception);
		}
	}
}
