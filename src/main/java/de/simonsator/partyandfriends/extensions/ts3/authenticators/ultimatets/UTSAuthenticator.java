package de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.extensions.ts3.TSExtension;
import de.simonsator.partyandfriends.extensions.ts3.api.authentication.Authenticator;
import de.simonsator.partyandfriends.extensions.ts3.api.user.TS3User;
import de.simonsator.partyandfriends.extensions.ts3.api.user.TS3UserManager;
import de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets.communication.UTSMySQL;
import de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets.configuration.UTSConfiguration;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 14.06.17
 */
public class UTSAuthenticator extends PAFExtension implements Authenticator {
	private UTSMySQL connection;

	public void onEnable() {
		try {
			Configuration config = new UTSConfiguration(new File(getConfigFolder(), "config.yml")).getCreatedConfiguration();
			MySQLData mySQLData = new MySQLData(config.getString("database.host"), config.getString("database.user"), config.getString("database.password"), 3306, config.getString("database.name"), null, config.getBoolean("database.useSSL"));
			connection = new UTSMySQL(mySQLData);
			TS3UserManager.setAuthenticator(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		registerAsExtension();
	}

	public PAFPlayer getPAFPlayer(TS3User pUser) {
		UUID uuid = connection.getUUIDByClientDbId(pUser.getClientInfo().getDatabaseId());
		if (uuid == null)
			return null;
		return PAFPlayerManager.getInstance().getPlayer(uuid);
	}

	public String getTSUniqueId(PAFPlayer pPlayer) {
		Integer cDbId = connection.getClientDbIdByUuid(pPlayer.getUniqueId());
		if (cDbId == null)
			return null;
		return TSExtension.getInstance().getApi().getDatabaseClientInfo(cDbId).getUniqueIdentifier();
	}
}
