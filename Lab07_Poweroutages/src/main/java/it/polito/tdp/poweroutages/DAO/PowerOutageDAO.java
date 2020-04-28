package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import it.polito.tdp.poweroutages.model.*;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutagesByNerc (Nerc nerc) {

		String sql = "SELECT id, customers_affected, YEAR(poweroutages.date_event_began) AS yearI,"
				+ " date_event_began, date_event_finished\r\n" + 
				"FROM poweroutages\r\n" + 
				"WHERE poweroutages.nerc_id = ?\r\n" + 
				"ORDER BY date_event_began ASC;";
		
		List<PowerOutage> PowerOutages = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages.add(new PowerOutage(res.getInt("id"), nerc, 
						res.getInt("customers_affected"), Year. of(res.getInt("yearI")),
						res.getTimestamp("date_event_began"), res.getTimestamp("date_event_finished")));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return PowerOutages;
	}

}
