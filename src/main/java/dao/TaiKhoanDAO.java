package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import entities.TaiKhoan;

public class TaiKhoanDAO {
	private Connection con;
	public TaiKhoanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public TaiKhoan checkLogin(String tenTaiKhoan, String matKhau) {
		TaiKhoan taiKhoan = null;
		try {
			String sql = "Select * from TaiKhoan where NhanVienID = ? and MatKhau = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tenTaiKhoan);
			statement.setString(2, matKhau);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				taiKhoan = new TaiKhoan(null, matKhau);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return taiKhoan;
	}
	
	
}
