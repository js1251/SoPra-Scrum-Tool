package sopra_scrum_tool.util.save_load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONObject;

public class ConfigSaveFile {
	public static String fileExtension = ".sopratoolcfg";

	JSONObject json = new JSONObject();

	/**
	 * Loads the save file from the specified path.
	 * 
	 * @throws NullPointerException  If the save path was null.
	 * @throws FileNotFoundException If the save path doesn't point to an existing
	 *                               file.
	 * @throws IOException           If there was a problem reading the file.
	 */
	public void load() throws NullPointerException, IOException {
		// check for savePath == null
		if (getSavePath() == null) {
			throw new NullPointerException("No config file path set.");
		}

		// load file
		File configFile = new File(getSavePath());

		// check if savePath file actually exists
		if (!configFile.exists()) {
			return;
		}

		// read entire file
		String content = new String(Files.readAllBytes(configFile.toPath()));

		// parse it as json
		json = new JSONObject(content);
	}

	private String getJsonString(JSONObject json, String key) {
		if (json.has(key) && json.get(key) instanceof String) {
			return json.getString(key);
		}

		return null;
	}

	/**
	 * Saves the savefile to the save folder structure
	 * 
	 * @throws NullPointerException  If the save path was null.
	 * @throws FileNotFoundException If the save path doesn't point to an existing
	 *                               file.
	 * @throws IOException           If there was a problem writing to the file.
	 */
	public void save() throws NullPointerException, IOException {
		// check for savePath == null
		if (getSavePath() == null) {
			throw new NullPointerException("No config file path set.");
		}

		// open the saveFile
		File configFile = new File(getSavePath());

		// check if savePath file actually exists
		if (!configFile.exists()) {
			// create the file
			Files.createFile(configFile.toPath());
		}

		Files.writeString(configFile.toPath(), json.toString());
	}

	/**
	 * Sets the save path.
	 * 
	 * @param savePath The save path the save will be written to.
	 */
	public void setSavePath(String savePath) {
		json.put("savePath", savePath);
	}

	/**
	 * Gets the save path.
	 * 
	 * @return The save path or null.
	 */
	public String getSavePath() {
		return getJsonString(json, "savePath");
	}

	/**
	 * Sets the look and feel
	 * 
	 * @param lookAndFeel
	 */
	public void setLookAndFeel(String lookAndFeel) {
		json.put("lookAndFeel", lookAndFeel);
	}

	/**
	 * Gets the look and feel.
	 * 
	 * @return The look and feel name.
	 */
	public String getLookAndFeel() {
		return getJsonString(json, "lookAndFeel");
	}

	/**
	 * Sets the Gitea url.
	 * 
	 * @param giteaUrl The Gitea url.
	 */
	public void setGiteaUrl(String giteaUrl) {
		json.put("giteaUrl", giteaUrl);
	}

	/**
	 * Gets the Gitea url saved in the tool config file.
	 * 
	 * @return The Gitea url or null.
	 */
	public String getGiteaUrl() {
		return getJsonString(json, "giteaUrl");
	}

	/**
	 * Sets a Gitea access token to the tool config file.
	 * 
	 * @param token The Gitea access token or null.
	 */
	public void setGiteaToken(String giteaToken) {
		json.put("giteaToken", giteaToken);
	}

	/**
	 * Gets the Gitea access token specified in settings.
	 * 
	 * @return Gitea access token or null.
	 */
	public String getGiteaToken() {
		return getJsonString(json, "giteaToken");
	}

	/**
	 * Sets the path of the last opened team save file.
	 * 
	 * @param lastOpenedTeamSave The last opened save file or null.
	 */
	public void setLastOpenedTeamSave(String lastOpenedTeamSave) {
		json.put("lastOpenedTeamSave", lastOpenedTeamSave);
	}

	/**
	 * Gets the path of the last opened team save file.
	 * 
	 * @return
	 */
	public String getLastOpenedTeamSave() {
		return getJsonString(json, "lastOpenedTeamSave");
	}
}
