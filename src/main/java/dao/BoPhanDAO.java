package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.BoPhan;
import entities.CongNhan;
import entities.Nguoi;
public class BoPhanDAO {
private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public BoPhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public String getTenBoPhanByID(String id) {
		BoPhan boPhan = null;
		try {
			String sql = "select * from BoPhan where BoPhanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				boPhan = new BoPhan(rs.getString(1), rs.getString(2));
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return boPhan.getTenBoPhan();
	}
	
	public BoPhan getBoPhanByID(String id) {
		BoPhan boPhan = null;
		try {
			String sql = "select * from BoPhan where BoPhanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				boPhan = new BoPhan(rs.getString(1), rs.getString(2));
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return boPhan;
	}
	
	public BoPhan getBoPhanByTen(String name) {
		BoPhan boPhan = null;
		try {
			String sql = "select * from BoPhan where TenBoPhan = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				boPhan = new BoPhan(rs.getString(1), rs.getString(2));
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return boPhan;
	}
	
	public String getBoPhanIDByTen(String name) {
		BoPhan boPhan = null;
		try {
			String sql = "select * from BoPhan where TenBoPhan = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				boPhan = new BoPhan(rs.getString(1), rs.getString(2));
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return boPhan.getBoPhanID();
	}
	
	public List<BoPhan> getAllBoPhan(){
		List<BoPhan> list = new ArrayList<BoPhan>();
		try {
			String sql = "select * from BoPhan ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				BoPhan boPhan = new BoPhan(rs.getString(1), rs.getString(2));
				list.add(boPhan);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<BoPhan> getAllBoPhan1(){
		ArrayList<BoPhan> list = new ArrayList<BoPhan>();
		try {
			String sql = "select * from BoPhan ";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				BoPhan boPhan = new BoPhan(rs.getString(1), rs.getString(2));
				list.add(boPhan);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public BoPhan layBoPhanTheoID(String id) {
		String sql="select *from BoPhan where BoPhanID=?";
		BoPhan boPhan=new BoPhan();
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				boPhan=new BoPhan(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return boPhan;
	}
//Lấy ds công nhân theo bộ phận
	public ArrayList<CongNhan> layDSCongNhanTheoBoPhan(String maBP) {
		ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
		String sql="select * from CongNhan where BoPhanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maBP);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String ma=rs.getString(1);
				BoPhan bp=layBoPhanTheoID(ma);
				Nguoi nguoi = nguoiDAO.layNguoiTheoID(rs.getString(1));
				CongNhan cn=new CongNhan(nguoi.getNguoiID(), bp);
				ds.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
//Lấy danh sách tất cả bộ phận
	public ArrayList<BoPhan> layTatCaBoPhan() {
		ArrayList<BoPhan> list=new ArrayList<BoPhan>();
		try {
			String sql="select *from BoPhan";
			PreparedStatement statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				BoPhan bp=new BoPhan(rs.getString(1), rs.getString(2));
				list.add(bp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
//Lấy tổng số công nhân trong 1 bộ phận
	public int laySoLuongCNTheoBoPhan(String maBP) {
		int sl=0;
		try {
			String sql="select count(*) from CongNhan where BoPhanID=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maBP);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sl;
	}
	
	public ArrayList<BoPhan> getAllBoPhanCoCN() {
		ArrayList<BoPhan>ds=new ArrayList<BoPhan>();
		String sql="select bp.BoPhanID, count(cn.CongNhanID) from BoPhan as bp join CongNhan as cn on bp.BoPhanID=cn.BoPhanID\r\n"
				+ "group by bp.BoPhanID\r\n"
				+ "having count(cn.CongNhanID) > 0";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String bpid=rs.getString(1);
				BoPhan bp=getBoPhanByID(bpid);
				ds.add(bp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
}
