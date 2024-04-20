package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import entities.KhauTru;
import entities.KhenThuong;

public class KhenThuongDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public KhenThuongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public KhenThuong getKhenThuongID(String id) {
		KhenThuong khenThuong = null;
		try {
			String sql = "select * from KhenThuong where KhenThuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				khenThuong = new KhenThuong(rs.getString(1), rs.getString(2), rs.getDouble(3));
			}
		}catch (Exception e) {
			
		}
		return khenThuong;
	}
	
	
	
}
