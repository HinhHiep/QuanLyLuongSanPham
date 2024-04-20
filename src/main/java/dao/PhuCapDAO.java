package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import entities.KhauTru;
import entities.PhuCap;

public class PhuCapDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public PhuCapDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public PhuCap getPhuCapByID(String id) {
		PhuCap phuCap = null;
		try {
			String sql = "select * from PhuCap where PhuCapID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				phuCap = new PhuCap(rs.getString(1), rs.getString(2), rs.getDouble(3));
			}
		}catch (Exception e) {
			
		}
		return phuCap;
	}
}
