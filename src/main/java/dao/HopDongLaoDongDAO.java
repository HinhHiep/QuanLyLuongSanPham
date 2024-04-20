package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.HopDongLaoDong;
public class HopDongLaoDongDAO {
	private Connection con;
	private NguoiDAO nguoiDAO = new NguoiDAO();
	private LoaiHopDongDAO loaiHopDongDAO = new LoaiHopDongDAO();
	
	public HopDongLaoDongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public List<HopDongLaoDong> getAllHopDong(){
		List<HopDongLaoDong> list = new ArrayList<HopDongLaoDong>();
		try {
			String sql = "select HopDongID, NguoiID, TenHopDong, LoaiHopDongID,CONVERT(DATE, NgayKy), CONVERT(DATE, NgayHetHan) "
					+ "from HopDongLaoDong JOIN CongNhan ON HopDongLaoDong.NguoiID = CongNhan.CongNhanID";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				HopDongLaoDong hopDongLaoDong = new HopDongLaoDong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)), 
						rs.getString(3), loaiHopDongDAO.getLoaiHopDongById(rs.getString(4)), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate());
				list.add(hopDongLaoDong);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addHopDongLaoDong(HopDongLaoDong hopDongLaoDong) {
		int n = 0;
		try {
			String sql = "insert into HopDongLaoDong VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, hopDongLaoDong.getHopDongID());
			statement.setString(2, hopDongLaoDong.getNguoi().getNguoiID());
			statement.setString(3, hopDongLaoDong.getTenHopDong());
			statement.setString(4, hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID());
			statement.setDate(5, java.sql.Date.valueOf(hopDongLaoDong.getNgayKy()));
			statement.setDate(6, java.sql.Date.valueOf(hopDongLaoDong.getNgayHetHan()));
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getMaxHopDongIDBaoHiemSubstring(String baoHiemID) { // lấy mã hợp đồng lớn nhất theo mã bảo hiểm => substring lấy 4 số cuối
		String maxHopDongID = "";
		try {
			String sql = "select MAX(HopDongID) from HopDongLaoDong where HopDongID LIKE " + "'%" + baoHiemID + "%'";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				maxHopDongID = rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return maxHopDongID;
	}
	
	
	public List<HopDongLaoDong> getHopDongLaoDongByNguoiID(String id){
		List<HopDongLaoDong> list = null;
		try {
			String sql = "select * from HopDongLaoDong JOIN CongNhan ON HopDongLaoDong.NguoiID = CongNhan.CongNhanID where NguoiID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			list = new ArrayList<HopDongLaoDong>();
			while(rs.next()) {
				HopDongLaoDong hopDongLaoDong = new HopDongLaoDong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)), 
						rs.getString(3), loaiHopDongDAO.getLoaiHopDongById(rs.getString(4)), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate());
				list.add(hopDongLaoDong);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
			
	}
	
	public HopDongLaoDong kiemTraHopDongLaoDong(String maCongNhan, String loaiHopDong) {
		HopDongLaoDong hopDongLaoDong = null;
		try {
			String sql = "select * from HopDongLaoDong where NguoiID = ? and LoaiHopDongID = ? and CONVERT(DATE, NgayHetHan) > GETDATE()";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maCongNhan);
			statement.setString(2, loaiHopDong);
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				hopDongLaoDong = new HopDongLaoDong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)), 
						rs.getString(3), loaiHopDongDAO.getLoaiHopDongById(rs.getString(4)), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate());
			}
			else 
				return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return hopDongLaoDong;
	}

	
}
