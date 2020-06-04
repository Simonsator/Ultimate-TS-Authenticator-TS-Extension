package de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets.configuration;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 14.06.17
 */
public class UTSConfiguration extends ConfigurationCreator {

	public UTSConfiguration(File pFile, PAFExtension pPlugin) throws IOException {
		super(pFile, pPlugin);
		readFile();
		loadDefaults();
		saveFile();
	}

	private void loadDefaults() {
		set("database.host", "localhost");
		set("database.name", "dataBaseName");
		set("database.user", "root");
		set("database.password", "password");
		set("database.useSSL", false);
	}
}
