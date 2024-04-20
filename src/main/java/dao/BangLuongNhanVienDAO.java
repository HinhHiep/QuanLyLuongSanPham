package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import entities.BangLuongNhanVien;

public class BangLuongNhanVienDAO {
	private Connection con;

	public BangLuongNhanVienDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public BangLuongNhanVien getBangLuongNhanVien(String maBangLuong) {
		BangLuongNhanVien bangLuongNhanVien = null;
		try {
			String sql = "Select * from BangLuongNhanVien where BangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maBangLuong);

			ResultSet rs = statement.executeQuery();
			while(rs.next())
				bangLuongNhanVien = new BangLuongNhanVien(rs.getString(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangLuongNhanVien;
	}

	public boolean addBangLuongNhanVien(BangLuongNhanVien bangLuongNhanVien) {
		int n = 0;
		try {
				String sql = "insert into BangLuongNhanVien VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, bangLuongNhanVien.getBangLuongID());
				statement.setString(2, bangLuongNhanVien.getNhanVien().getNguoiID());
				statement.setInt(3, bangLuongNhanVien.getThang());
				statement.setInt(4, bangLuongNhanVien.getSoNgayCong());
				statement.setInt(5, bangLuongNhanVien.getSoNgayVang());
				statement.setDouble(6, bangLuongNhanVien.getHeSoLuong());
				statement.setDouble(7, bangLuongNhanVien.getLuongCoBan());
				statement.setDouble(8, bangLuongNhanVien.getLuongThucLanh());
				n = statement.executeUpdate();
				return n > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBangLuongNhanVien(BangLuongNhanVien bangLuongNhanVien) {
		int n= 0;
		try {
			String sql = "UPDATE BangLuongNhanVien set SoNgayCong = ?, SoNgayVang = ?, LuongThucLanh = ? where bangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, bangLuongNhanVien.getSoNgayCong());
			statement.setInt(2, bangLuongNhanVien.getSoNgayVang());
			statement.setDouble(3, bangLuongNhanVien.getLuongThucLanh());
			statement.setString(4, bangLuongNhanVien.getBangLuongID());
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ResultSet thongKeLuongTheoThangNam(int thang, int nam) {
		ResultSet rs = null;
		try {
			String sql = "select NhanVienID, LuongThucLanh from BangLuongNhanVien\r\n"
					+ "JOIN BangChamCong ON BangLuongNhanVien.NhanVienID = BangChamCong.NguoiID where Thang = ?\r\n"
					+ " and YEAR(BangChamCong.Ngay) = ?\r\n"
					+ " group by NhanVienID,LuongThucLanh";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, thang);
			statement.setInt(2, nam);
			rs = statement.executeQuery();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	
}
