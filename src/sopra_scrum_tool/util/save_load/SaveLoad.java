package sopra_scrum_tool.util.save_load;

import java.io.IOException;

public class SaveLoad {
	private ConfigSaveFile currentConfigFile;
	private SoPraTeamSaveFile currentTeamSaveFile;

	public void setCurrentConfig(ConfigSaveFile currentConfigFile) {
		this.currentConfigFile = currentConfigFile;
	}

	public ConfigSaveFile getCurrentConfig() {
		return currentConfigFile;
	}

	public void setCurrentSoPraTeamSave(SoPraTeamSaveFile currentTeamSaveFile) {
		this.currentTeamSaveFile = currentTeamSaveFile;
	}

	public SoPraTeamSaveFile getCurrentSoPraTeamSave() {
		return currentTeamSaveFile;
	}
	
	public void save() throws NullPointerException, IOException {
		currentConfigFile.save();
		currentTeamSaveFile.save();
	}

	public void loadConfig() throws NullPointerException, IOException {
		String savePath = "./" + ConfigSaveFile.fileExtension;

		// load the savefile of the last opened team
		ConfigSaveFile configSave = new ConfigSaveFile();
		configSave.setSavePath(savePath);
		configSave.load();

		setCurrentConfig(configSave);
	}

	/**
	 * Loads the last opened savefile from when the program was last used.
	 * 
	 * @return The last savefile from the last session or null.
	 */
	public void loadLastTeamSave() {
		// if there is no config loaded return
		if (currentConfigFile == null) {
			return;
		}

		// get the last opened save file from the config
		String lastOpenedPath = currentConfigFile.getLastOpenedTeamSave();

		// load the savefile of the last opened team
		SoPraTeamSaveFile lastOpenedSave = new SoPraTeamSaveFile();
		lastOpenedSave.setSavePath(lastOpenedPath);
		lastOpenedSave.load();

		setCurrentSoPraTeamSave(lastOpenedSave);
	}

	/**
	 * Load a specific team save file from a given save path.
	 * 
	 * @param savePath The path of the save file.
	 * @return The team savefile.
	 */
	public void loadTeamSave(String savePath) {
		// load the savefile of the last opened team
		SoPraTeamSaveFile loadedTeamSave = new SoPraTeamSaveFile();
		loadedTeamSave.setSavePath(savePath);
		loadedTeamSave.load();

		setCurrentSoPraTeamSave(loadedTeamSave);
	}
}
