package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import db.DBConnection;
import entities.BangLuongCongNhan;
import entities.BangLuongNhanVien;

public class BangLuongCongNhanDAO {
	private Connection con;

	public BangLuongCongNhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public BangLuongCongNhan getBangLuongCongNhan(String maBangLuong) {
		BangLuongCongNhan bangLuongCongNhan = null;
		try {
			String sql = "Select * from BangLuongCongNhan where BangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maBangLuong);

			ResultSet rs = statement.executeQuery();
			while(rs.next())
				bangLuongCongNhan = new BangLuongCongNhan(rs.getString(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangLuongCongNhan;
	}

	public boolean addBangLuongCongNhan(BangLuongCongNhan bangLuongCongNhan) {
		int n = 0;
		try {
				
				String sql = "insert into BangLuongCongNhan VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, bangLuongCongNhan.getBangLuongID());
				statement.setString(2, bangLuongCongNhan.getCongNhan().getNguoiID());
				statement.setInt(3, bangLuongCongNhan.getThang());
				statement.setInt(4, bangLuongCongNhan.getSoNgayCong());
				statement.setInt(5, bangLuongCongNhan.getSoNgayVang());
				statement.setDouble(6, bangLuongCongNhan.getHeSoLamViec());
				statement.setDouble(7, bangLuongCongNhan.getLuongSanPham());
				statement.setDouble(8, bangLuongCongNhan.getLuongThucLanh());
				n = statement.executeUpdate();
				return n > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBangLuongCongNhan(BangLuongCongNhan bangLuongCongNhan) {
		int n= 0;
		try {
			String sql = "UPDATE BangLuongCongNhan set SoNgayCong = ?, SoNgayVang = ?, LuongSanPham = ?, LuongThucLanh = ? where bangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, bangLuongCongNhan.getSoNgayCong());
			statement.setInt(2, bangLuongCongNhan.getSoNgayVang());
			statement.setDouble(3, bangLuongCongNhan.getLuongThucLanh());
			statement.setDouble(4, bangLuongCongNhan.getLuongSanPham());
			statement.setString(5, bangLuongCongNhan.getBangLuongID());
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
			String sql = "select CongNhanID, LuongThucLanh from BangLuongCongNhan\r\n"
					+ "JOIN BangChamCong ON BangLuongCongNhan.CongNhanID = BangChamCong.NguoiID where Thang = ?\r\n"
					+ " and YEAR(BangChamCong.Ngay) = ?\r\n"
					+ " group by CongNhanID,LuongThucLanh";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, thang);
			statement.setInt(2, nam);
			rs = statement.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
