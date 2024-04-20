package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import db.DBConnection;
import entities.NhanVien;
import entities.PhongBan;

public class PhongBanDAO {
	private Connection con;
	
	public PhongBanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public PhongBan getPhongBanByID(String id) {
		PhongBan phongBan = null;
		try {
			String sql = "select * from PhongBan where phongBanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				phongBan = new PhongBan(rs.getString(1), rs.getString(2));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return phongBan;
	}
	
	public String getIDPhongBanByTen(String ten) {
		String phongBanID = null;
		try {
			String sql = "select PhongBanID from PhongBan where TenPhongBan = N'"+ ten + "'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				phongBanID = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return phongBanID;
	}
	
	public PhongBan getPhongBanByTen(String ten) {
		PhongBan phongBan = null;
		try {
			String sql = "select * from PhongBan where TenPhongBan = N'"+ ten + "'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				phongBan = new PhongBan(rs.getString(1), rs.getString(2));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return phongBan;
	}
	
	public List<PhongBan> getAllPhongBan(){
		List<PhongBan> list = new ArrayList<PhongBan>();
		try {
			String sql = "select * from PhongBan";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				PhongBan phongBan = new PhongBan(rs.getString(1), rs.getString(2));
				list.add(phongBan);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
