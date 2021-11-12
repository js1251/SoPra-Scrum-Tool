package sopra_scrum_tool.util.save_load;

import java.io.IOException;

public class SaveLoad {
	private ConfigSaveFile currentConfigFile;

	public void setCurrentConfig(ConfigSaveFile currentConfigFile) {
		this.currentConfigFile = currentConfigFile;
	}

	public ConfigSaveFile getCurrentConfig() {
		return currentConfigFile;
	}
	
	public void save() throws NullPointerException, IOException {
		currentConfigFile.save();
	}

	public void loadConfig() throws NullPointerException, IOException {
		String savePath = "./" + ConfigSaveFile.fileExtension;

		// load the savefile of the last opened team
		ConfigSaveFile configSave = new ConfigSaveFile();
		configSave.setSavePath(savePath);
		configSave.load();

		setCurrentConfig(configSave);
	}
}
