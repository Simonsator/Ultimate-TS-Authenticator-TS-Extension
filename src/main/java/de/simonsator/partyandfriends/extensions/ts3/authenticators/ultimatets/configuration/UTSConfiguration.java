package de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets.configuration;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 14.06.17
 */
public class UTSConfiguration extends ConfigurationCreator {

	public UTSConfiguration(File pFile) throws IOException {
		super(pFile);
		readFile();
		loadDefaults();
		saveFile();
	}

	private void loadDefaults() {
		set("database.host", "localhost");
		set("database.name", "dataBaseName");
		set("database.user", "root");
		set("database.password", "password");
		set("database.useSSL", true);
	}

	public void reloadConfiguration() throws IOException {
// deprecated
	}
}
