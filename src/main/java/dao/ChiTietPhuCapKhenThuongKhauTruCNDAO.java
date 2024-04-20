package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import entities.BangLuongCongNhan;
import entities.BangLuongNhanVien;
import entities.ChiTietPhuCapKhenThuongKhauTru;
import entities.ChiTietPhuCapKhenThuongKhauTruCN;
import entities.PhuCapKhenThuongKhauTru;

public class ChiTietPhuCapKhenThuongKhauTruCNDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	private CongNhanDAO congNhanDAO = new CongNhanDAO();
	private PhuCapKhenThuongKhauTruDAO phuCapKhenThuongKhauTruDAO = new PhuCapKhenThuongKhauTruDAO();
	public ChiTietPhuCapKhenThuongKhauTruCNDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public ChiTietPhuCapKhenThuongKhauTruCN getChiTietKhenThuongPhuCapKhauTru(String maChiTiet, String maLuong) {
		ChiTietPhuCapKhenThuongKhauTruCN chiPhuCapKhenThuongKhauTru = null;
		try {
			String sql = "select * from ChiTietPhuCapKhenThuongKhauTruCN where ID = ? and BangLuongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maChiTiet);
			statement.setString(2, maLuong);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				chiPhuCapKhenThuongKhauTru = new ChiTietPhuCapKhenThuongKhauTruCN(new PhuCapKhenThuongKhauTru(rs.getString(1)), new BangLuongCongNhan(rs.getString(2)));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chiPhuCapKhenThuongKhauTru;
	}
	
	public boolean addChiTietPhuCapKhenThuongKhauTru(ChiTietPhuCapKhenThuongKhauTruCN chiTietPhuCapKhenThuongKhauTru) {
		int n = 0;
		try {
			String sql = "insert into ChiTietPhuCapKhenThuongKhauTruCN VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(2, chiTietPhuCapKhenThuongKhauTru.getPhuCapKhenThuongKhauTru().getId());
			statement.setString(1, chiTietPhuCapKhenThuongKhauTru.getBangLuongCongNhan().getBangLuongID());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteChiTietPhuCapKhenThuongKhauTru(String bangLuongID, String ID) {
		int n = 0;
		try {
			String sql = "delete from ChiTietPhuCapKhenThuongKhauTruCN where BangLuongID = ? and ID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bangLuongID);
			statement.setString(2, ID);
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addChiTietKhauTruBHXH(String maBangLuong) {
		int n = 0;
		try {
			String maBHXH = "KT1220001"; // mã bảo hiểm y tế
			String sql = "insert into ChiTietPhuCapKhenThuongKhauTruCN VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(2, maBHXH);
			statement.setString(1, maBangLuong);
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addChiTietKhauTruBHYT(String maBangLuong) {
		int n = 0;
		try {
			String maBHYT = "KT1220002"; // mã bảo hiểm y tế
			String sql = "insert into ChiTietPhuCapKhenThuongKhauTruCN VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(2, maBHYT);
			statement.setString(1, maBangLuong);
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addChiTietKhauTruThue(String maBangLuong, double luongCoBan) {
		int n = 0;
		try {
			String sql = "insert into ChiTietPhuCapKhenThuongKhauTruCN VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			if(luongCoBan < 5000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu từ thuế đến 5 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 5000000 && luongCoBan <= 10000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 5 - 10 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 10000000 && luongCoBan <= 18000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 10 - 18 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 18000000 && luongCoBan <= 32000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 18 - 32 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 32000000 && luongCoBan <= 52000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 32 - 52 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}		
			else if(luongCoBan >= 52000000 && luongCoBan <= 80000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 52 - 80 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}			
			else {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế trên 80 triệu").getId();
				statement.setString(1, maBangLuong);
				statement.setString(2, maKhauTruThue);
				n = statement.executeUpdate();
			}
			
		
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateChiTietKhauTruThue(String maBangLuong, String maID, double luongCoBan) {
		int n = 0;
		try {
			String sql = "UPDATE ChiTietPhuCapKhenThuongKhauTruCN SET ID = ? where BangLuongID = ? and ID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			if(luongCoBan < 5000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu từ thuế đến 5 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 5000000 && luongCoBan <= 10000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 5 - 10 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 10000000 && luongCoBan <= 18000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 10 - 18 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 18000000 && luongCoBan <= 32000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 18 - 32 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}			
			else if(luongCoBan >= 32000000 && luongCoBan <= 52000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 32 - 52 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}		
			else if(luongCoBan >= 52000000 && luongCoBan <= 80000000) {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 52 - 80 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}			
			else {
				String maKhauTruThue = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế trên 80 triệu").getId();
				statement.setString(1, maKhauTruThue);
				statement.setString(2, maBangLuong);
				statement.setString(3, maID);
				n = statement.executeUpdate();
			}
			
		
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	
}
