package sopra_scrum_tool.gui.components.global.top_menu_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sopra_scrum_tool.SoPraScrumTool;
import sopra_scrum_tool.util.errorhandling.Errorhandling;

public class TopMenuBarLookAndFeelListener implements ActionListener {
	private LookAndFeelInfo info;

	public TopMenuBarLookAndFeelListener(LookAndFeelInfo info) {
		this.info = info;
	}

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
}
