package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.LoaiHopDong;
import db.DBConnection;

public class LoaiHopDongDAO {
private Connection con;
	
	public LoaiHopDongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	
	public List<LoaiHopDong> getAllLoaiHopDong(){
		List<LoaiHopDong> list = new ArrayList<LoaiHopDong>();
		try {
			String sql = "select * from LoaiHopDong where LoaiHopDongID LIKE '%121%'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				LoaiHopDong loaiHopDong = new LoaiHopDong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				list.add(loaiHopDong);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public LoaiHopDong getLoaiHopDongById(String id) {
		LoaiHopDong loaiHopDong = null;
		try {
			String sql = "select * from LoaiHopDong where LoaiHopDongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				loaiHopDong = new LoaiHopDong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return loaiHopDong;
	}
	
	public String getTenLoaiHopDongById(String id) {
		String tenLoaiHopDong = null;
		try {
			String sql = "select TenLoai from LoaiHopDong where LoaiHopDongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				tenLoaiHopDong = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tenLoaiHopDong;
	}
	
	public int getThoiHanLoaiHopDongById(String id) {
		int thoiHan = 0;
		try {
			String sql = "select ThoiHan from LoaiHopDong where LoaiHopDongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				thoiHan = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return thoiHan;
	}
	

}
