package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Adiacenti;
import it.polito.tdp.poweroutages.model.InterruzioneCorrente;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutagesDAO {
	
	public void loadAllNercs(Map<Integer,Nerc> mappa) {

		String sql = "SELECT id, value FROM nerc";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				if(!mappa.containsKey(n.getId())) {
					mappa.put(n.getId(), n);
				}
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Adiacenti> getArchi(Map<Integer,Nerc> mappa){
		String sql = "SELECT p1.nerc_id, p2.nerc_id, COUNT(DISTINCT MONTH(p2.date_event_began),YEAR(p2.date_event_began)) peso "
				+ "FROM poweroutages p1, poweroutages p2 "
				+ "WHERE  p1.nerc_id > p2.nerc_id AND "
				+ "MONTH(p1.date_event_began) = MONTH(p2.date_event_began) AND "
				+ "YEAR(p1.date_event_began) = YEAR(p2.date_event_began) "
				+ "GROUP BY p1.nerc_id, p2.nerc_id";
		List<Adiacenti> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n1 = mappa.get(res.getInt("p1.nerc_id"));
				Nerc n2 = mappa.get(res.getInt("p2.nerc_id"));				
				if(n1 != null && n2 != null) {
					result.add(new Adiacenti(n1,n2,res.getInt("peso")));
				}
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	public List<InterruzioneCorrente> getInterruzioni(Map<Integer,Nerc> mappa) {
		String sql = "SELECT * "
				+ "FROM poweroutages "
				+ "ORDER BY date_event_began";
		
		List<InterruzioneCorrente> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			
			while (res.next()) {
				Nerc n1 = mappa.get(res.getInt("nerc_id"));
				if(n1 != null) {
					InterruzioneCorrente ic = new InterruzioneCorrente(res.getDate("date_event_began").toLocalDate(),res.getDate("date_event_finished").toLocalDate(), n1);
					result.add(ic);
				}

			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
}
