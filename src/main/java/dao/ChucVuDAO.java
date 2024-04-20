package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.ChucVu;
import entities.PhongBan;

public class ChucVuDAO {

	private Connection con;
	
	public ChucVuDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public ChucVu getChucVuByID(String id) {
		ChucVu chucVu = null;
		try {
			String sql = "select * from ChucVu where chucVuID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				chucVu = new ChucVu(rs.getString(1), rs.getString(2));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return chucVu;
	}
	
	public List<ChucVu> getAllChucVu(){
		List<ChucVu> list = new ArrayList<ChucVu>();
		try {
			String sql = "select * from ChucVu";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ChucVu chucVu = new ChucVu(rs.getString(1), rs.getString(2));
				list.add(chucVu);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String getIDChucVuByTen(String ten) {
		String chucVuID = null;
		try {
			String sql = "select ChucVuID from ChucVu where TenChucVu = N'"+ ten + "'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) 
				chucVuID = rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chucVuID;
	}
	
	public ChucVu getChucVuByTen(String ten) {
		ChucVu chucVu = null;
		try {
			String sql = "select * from ChucVu where TenChucVu = N'"+ ten + "'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				chucVu = new ChucVu(rs.getString(1), rs.getString(2));
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chucVu;
	}

}
