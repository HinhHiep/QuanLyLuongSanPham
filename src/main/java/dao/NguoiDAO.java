package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import db.DBConnection;
import entities.Nguoi;

public class NguoiDAO {
	private Connection con;

	
	public NguoiDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public Nguoi getNguoiByID(String id) {
		Nguoi nguoi = null;
		try {
			String sql = "select * from Nguoi where nguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				nguoi = new Nguoi(rs.getString(1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nguoi;
	}
	
	public String getHoTenNguoiByID(String id) {
		String hoTen = "";
		try {
			String sql = "select HoTen from Nguoi where nguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				hoTen = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return hoTen;
	}
	
	public ResultSet getTenNguoiByID(String id) {
		ResultSet rs = null;
		try {
			String sql = "select HoTen from Nguoi where NguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            rs = statement.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean addNguoi(Nguoi nguoi) {
		int n = 0;
		try {
			LocalDate ngaySinh = nguoi.getNgaySinh();
			java.sql.Date date = java.sql.Date.valueOf(ngaySinh);
			String sql = "Insert into Nguoi VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nguoi.getNguoiID());
			statement.setString(2, nguoi.getHoTen());
			statement.setDate(3, date);
			statement.setBoolean(4, nguoi.isGioiTinh());
			statement.setString(5,nguoi.getSoCMND());
			statement.setString(6, nguoi.getDiaChi());
			statement.setString(7, nguoi.getEmail());
			statement.setString(8, nguoi.getSoDienThoai());
			statement.setBoolean(9, true);
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean xoaNguoi(String id) {
		int n = 0;
		try {
			String sql = "update Nguoi set TrangThai = 0 where NguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean suaNguoi(Nguoi nguoi) {
		int n = 0;
		try {
			LocalDate ngaySinh = nguoi.getNgaySinh();
			java.sql.Date date = java.sql.Date.valueOf(ngaySinh);
			String sql = "update Nguoi set HoTen = ?, NgaySinh = ?, GioiTinh = ?, SoCMND = ?, DiaChi = ?, Email = ?, SoDienThoai = ? where NguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nguoi.getHoTen());
			statement.setDate(2, date);
			statement.setBoolean(3, nguoi.isGioiTinh());
			statement.setString(4, nguoi.getSoCMND());
			statement.setString(5, nguoi.getDiaChi());
			statement.setString(6, nguoi.getEmail());
			statement.setString(7, nguoi.getSoDienThoai());
			statement.setString(8, nguoi.getNguoiID());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public Nguoi layNguoiTheoID(String id) {
		Nguoi nguoi=new Nguoi();
		try {
			String sql="select*from Nguoi where NguoiID=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				nguoi=new Nguoi(rs.getString(1), rs.getString(2),rs.getDate(3).toLocalDate(),rs.getBoolean(4) ,rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getBoolean(9));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nguoi;
	}
	//Lấy danh sách công nhân
	public ArrayList<Nguoi> layDSCongNhan() {
		ArrayList<Nguoi> ds=new ArrayList<Nguoi>();
		String sql="select *from CongNhan join Nguoi on CongNhanID=NguoiID";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String nguoiID=rs.getString(1);
				Nguoi nguoi=layNguoiTheoID(nguoiID);
				ds.add(nguoi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	
}
