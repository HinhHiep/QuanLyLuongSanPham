package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.PhuCapKhenThuongKhauTru;

public class PhuCapKhenThuongKhauTruDAO {
	private Connection con;
	public PhuCapKhenThuongKhauTruDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public List<String> getDanhSachTenTheoLoai(String loai){
		List<String> danhSach = new ArrayList<String>();
		try {
			String sql = "select * from PhuCapKhenThuongKhauTru where Loai = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, loai);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				danhSach.add(rs.getString(2));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return danhSach;
	}
	
	public PhuCapKhenThuongKhauTru getMaVaSoTienTheoTenLoai(String ten){
		PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru = null;
		try {
			String sql = "select * from PhuCapKhenThuongKhauTru where Ten = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				phuCapKhenThuongKhauTru = new PhuCapKhenThuongKhauTru
						(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));			
			}			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return phuCapKhenThuongKhauTru;
	}
	
	public List<PhuCapKhenThuongKhauTru> getDanhSachByMaLuongID(String maLuongID){
		List<PhuCapKhenThuongKhauTru> list = new ArrayList<PhuCapKhenThuongKhauTru>();
		try {
			String sql = "select * from ChiTietPhuCapKhenThuongKhauTru JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTru.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru = new PhuCapKhenThuongKhauTru
						(rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
				list.add(phuCapKhenThuongKhauTru);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<PhuCapKhenThuongKhauTru> getDanhSachPCKTKTCongNhanhByMaLuongID(String maLuongID){
		List<PhuCapKhenThuongKhauTru> list = new ArrayList<PhuCapKhenThuongKhauTru>();
		try {
			String sql = "select * from ChiTietPhuCapKhenThuongKhauTruCN JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTruCN.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru = new PhuCapKhenThuongKhauTru
						(rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
				list.add(phuCapKhenThuongKhauTru);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public double getPhanTramBHYT() {
		double phanTram = 0.0;
		try {
			String sql = "select GiaTien from PhuCapKhenThuongKhauTru where Ten = N'Khấu trừ bảo hiểm y tế'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				phanTram = rs.getDouble(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return phanTram;
	}
	
	public double getPhanTramBHXH() {
		double phanTram = 0.0;
		try {
			String sql = "select GiaTien from PhuCapKhenThuongKhauTru where Ten = N'Khấu trừ bảo hiểm xã hội'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				phanTram = rs.getDouble(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return phanTram;
	}
	
	public double getSoTienKhenThuongByMaLuongID(String maLuongID){
		double tien = 0;
		try {
			String sql = "select GiaTien from ChiTietPhuCapKhenThuongKhauTru JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTru.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ? and Loai = N'Khen thưởng'";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				tien = tien + rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tien;
	}
	
	public double getSoTienKhenThuongCongNhanByMaLuongID(String maLuongID){
		double tien = 0;
		try {
			String sql = "select GiaTien from ChiTietPhuCapKhenThuongKhauTruCN JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTruCN.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ? and Loai = N'Khen thưởng'";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				tien = tien + rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tien;
	}
	
	public double getSoTienPhuCapByMaLuongID(String maLuongID){
		double tien = 0;
		try {
			String sql = "select GiaTien from ChiTietPhuCapKhenThuongKhauTru JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTru.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ? and Loai = N'Phụ cấp'";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				tien = tien + rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tien;
	}
	
	public double getSoTienPhuCapCongNhanByMaLuongID(String maLuongID){
		double tien = 0;
		try {
			String sql = "select GiaTien from ChiTietPhuCapKhenThuongKhauTruCN JOIN PhuCapKhenThuongKhauTru \r\n"
					+ "ON ChiTietPhuCapKhenThuongKhauTruCN.ID = PhuCapKhenThuongKhauTru.ID\r\n"
					+ "where BangLuongID = ? and Loai = N'Phụ cấp'";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maLuongID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				tien = tien + rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tien;
	}
	
	
}
