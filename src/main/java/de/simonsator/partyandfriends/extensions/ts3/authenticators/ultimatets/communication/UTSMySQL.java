package de.simonsator.partyandfriends.extensions.ts3.authenticators.ultimatets.communication;

import de.simonsator.partyandfriends.communication.sql.MySQLData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;
import de.simonsator.partyandfriends.communication.sql.pool.PoolData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * @author Simonsator
 * @version 1.0.0 14.06.17
 */
public class UTSMySQL extends SQLCommunication {
	public UTSMySQL(MySQLData pMySQLData) {
		super(pMySQLData);
	}

	public UUID getUUIDByClientDbId(int pClientDbId) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select uuid from UltimateTS_linkedplayers WHERE dbId='" + pClientDbId + "' LIMIT 1");
			if (rs.next()) {
				UUID uuid = UUID.fromString(rs.getString("uuid"));
				return uuid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return null;
	}

	public Integer getClientDbIdByUuid(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("SELECT dbId FROM UltimateTS_linkedplayers WHERE uuid='" + pUUID + "' LIMIT 1");
			if (rs.next()) {
				return rs.getInt("dbId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt);
		}
		return null;
	}
}
