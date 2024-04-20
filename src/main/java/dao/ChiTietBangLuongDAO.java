package dao;

import java.sql.Connection;

import db.DBConnection;

public class ChiTietBangLuongDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public ChiTietBangLuongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	
}
