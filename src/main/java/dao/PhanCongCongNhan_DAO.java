package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import db.DBConnection;
import entities.Nguoi;
import entities.PhanCongCongNhan;

public class PhanCongCongNhan_DAO {

	private Connection con;

	public PhanCongCongNhan_DAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public void themPhanCongCongNhan(PhanCongCongNhan pccn) {
		try {
			String sql = "INSERT INTO BangPhanCongCongNhan (PhanCongID, BoPhanID, CongNhanID, PhanCongBPID, SoLuongPhanCong, SoLuongHoanThanh, Ngay, CaLamViec) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, pccn.getPhanCongID());
				statement.setString(2, pccn.getBoPhanID());
				statement.setString(3, pccn.getCongNhanID());
				statement.setString(4, pccn.getPhanCongBPID());
				statement.setInt(5, pccn.getSoLuongPhanCong());
				statement.setString(6, pccn.getSoLuongHoanThanh());
				statement.setTimestamp(7, Timestamp.valueOf(pccn.getNgay()));
				statement.setString(8, pccn.getCaLamViec());

				// Execute the INSERT statement
				int affectedRows = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSoLuongPhanCong(String maPhanCong, int soLuong) {
		try {
			String sql = "update BangPhanCongCongNhan\r\n" + "set SoLuongPhanCong = ?\r\n" + "where PhanCongID = ?";

			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setInt(1, soLuong);
				statement.setString(2, maPhanCong);

				// Execute the INSERT statement
				int affectedRows = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getSoLuongHoanThanhCuaMotCongNhan(Nguoi nguoi, LocalDate ngay) {
		 int soLuongHoanThanh = 0;
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select SUM(SoLuongHoanThanh) as st\n"
			 		+ "from BangPhanCongCongNhan as pccn\n"
			 		+ "inner join BangPhanCongBoPhan as pcbp on pcbp.PhanCongID = pccn.PhanCongBoPhanID \n"
			 		+ "INNER JOIN CongDoan ON CongDoan.CongDoanID = pcbp.CongDoanID\n"
			 		+ "and month(pcbp.Ngay) = ?\n"
			 		+ "and year(pcbp.Ngay) = ? where CongNhanID = ? group by SoLuongHoanThanh";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
					 soLuongHoanThanh = soLuongHoanThanh + rs.getInt(1); 
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return soLuongHoanThanh;
	}
	
	
	public double getLuongSanPhamCuaMotCongNhan(Nguoi nguoi, LocalDate ngay) {
		 double luongSanPham = 0;
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select SoLuongHoanThanh, DonGia\n"
			 		+ "from BangPhanCongCongNhan as pccn\n"
			 		+ "inner join BangPhanCongBoPhan as pcbp on pcbp.PhanCongID = pccn.PhanCongBoPhanID \n"
			 		+ "INNER JOIN CongDoan ON CongDoan.CongDoanID = pcbp.CongDoanID\n"
			 		+ "and month(pcbp.Ngay) = ?\n"
			 		+ "and year(pcbp.Ngay) = ? where CongNhanID = ? ";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 luongSanPham = luongSanPham + (rs.getInt(1) * rs.getDouble(2)); 
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return luongSanPham;
	}

}
