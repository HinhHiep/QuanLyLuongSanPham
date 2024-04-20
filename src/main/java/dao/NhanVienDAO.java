package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.Nguoi;
import entities.NhanVien;
import entities.PhongBan;
import enums.CapBac;
	

public class NhanVienDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public PhongBanDAO phongBanDAO = new PhongBanDAO();
	public ChucVuDAO chucVuDAO = new ChucVuDAO();
	public NhanVienDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
		
	public NhanVien getNhanVienByID(String id) {
		NhanVien nhanVien = null;
		try {
			String sql = "select * from NhanVien where NhanVienID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Nguoi nguoi = nguoiDAO.getNguoiByID(id);
				nhanVien = new NhanVien(nguoi.getNguoiID(), 
						phongBanDAO.getPhongBanByID(rs.getString(2)), 
						chucVuDAO.getChucVuByID(rs.getString(3)), 
						CapBac.getCapBat(rs.getString(4)), rs.getInt(5), rs.getInt(6), rs.getDouble(7));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return nhanVien;
	}
	
	public NhanVien getNhanVienByIDFullProps(String id) {
		NhanVien nhanVien = null;
		try {
			String sql = "select * from Nguoi JOIN NhanVien on Nguoi.NguoiID = NhanVien.NhanVienID where NhanVienID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				nhanVien = new NhanVien(rs.getString(1), rs.getString(2), LocalDate.now(), rs.getBoolean(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), 
						phongBanDAO.getPhongBanByID(rs.getString(11)), chucVuDAO.getChucVuByID(rs.getString(12)), 
						CapBac.getCapBat(rs.getString(13)), rs.getInt(14), rs.getDouble(15), rs.getDouble(16));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return nhanVien;
	}
	
	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			String sql = "select * from Nguoi JOIN NhanVien on Nguoi.NguoiID = NhanVien.NhanVienID where Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				NhanVien nhanVien = new NhanVien(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
									rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), 
									phongBanDAO.getPhongBanByID(rs.getString(11)), chucVuDAO.getChucVuByID(rs.getString(12)), 
									CapBac.getCapBat(rs.getString(13)), rs.getInt(14), rs.getDouble(15), rs.getDouble(16));
				list.add(nhanVien);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public LocalDate convertDateTimeDate(String id) { // lười select từng thuộc tính nên làm vậy nha :v
		LocalDate localDate = null;
		try {
			ResultSet rs = null;
			String sql = "SELECT CONVERT(DATE, Nguoi.NgaySinh) from Nguoi JOIN NhanVien on "
					+ "Nguoi.NguoiID = NhanVien.NhanVienID where NhanVienID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
            	localDate = rs.getDate(1).toLocalDate();
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return localDate;
	    
	}
	
	public String getMaxNhanVienIDSubstring() { // lấy mã người lớn nhất => substring lấy 4 số cuối
		String maxNhanvienID = null;
		try {
			String sql = "select max(NguoiID) from Nguoi join NhanVien on Nguoi.NguoiID = NhanVien.NhanVienID";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				maxNhanvienID = rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String builder  = new StringBuilder(maxNhanvienID).reverse().substring(0, 4);
		return new StringBuilder(builder).reverse().toString();
	}
	
	public boolean addNhanVien(NhanVien nhanVien) {
		int n = 0;
		try {
			String sql = "insert into NhanVien VALUES(?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nhanVien.getNguoiID());
			statement.setString(2, nhanVien.getPhongBan().getPhongBanID());
			statement.setString(3, nhanVien.getChucVu().getChucVuID());
			statement.setString(4, nhanVien.getCapBac().toString());
			statement.setInt(5, nhanVien.getSoNamKinhNghiem());
			statement.setDouble(6, nhanVien.getHeSoLuong());
			statement.setDouble(7, nhanVien.getLuongCoBan());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean suaNhanVien(NhanVien nhanVien) {
		int n = 0;
		try {
			String sql = "update NhanVien set PhongBanID = ?, ChucVuID = ?, CapBac = ?, SoNamKinhNghiem = ?, HeSoLuong = ?, LuongCoBan = ? where NhanVienID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nhanVien.getPhongBan().getPhongBanID());
			statement.setString(2, nhanVien.getChucVu().getChucVuID());
			statement.setString(3, nhanVien.getCapBac().toString());
			statement.setInt(4, nhanVien.getSoNamKinhNghiem());
			statement.setDouble(5, nhanVien.getHeSoLuong());
			statement.setDouble(6, nhanVien.getLuongCoBan());
			statement.setString(7, nhanVien.getNguoiID());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<NhanVien> getAllNhanVienTimKiemNangCao(String maNV) {
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			String sql = "select * from Nguoi JOIN NhanVien on Nguoi.NguoiID = NhanVien.NhanVienID where HoTen like N'%"+ maNV +"%' and Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				NhanVien nhanVien = new NhanVien(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
									rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), 
									phongBanDAO.getPhongBanByID(rs.getString(11)), chucVuDAO.getChucVuByID(rs.getString(12)), 
									CapBac.getCapBat(rs.getString(13)), rs.getInt(14), rs.getDouble(15), rs.getDouble(16));
				list.add(nhanVien);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
