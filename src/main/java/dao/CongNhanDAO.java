package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.BoPhan;
import entities.CongNhan;
import entities.Nguoi;
import entities.NhanVien;
import enums.CapBac;

public class CongNhanDAO {
private Connection con;
private BoPhanDAO boPhanDAO = new BoPhanDAO();
private NguoiDAO nguoiDAO = new NguoiDAO();
private ArrayList<CongNhan> list;
	public CongNhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	
	public CongNhan getCongNhanTheoID(String id) {
		CongNhan cn=null;
		try {
			String sql="select *from CongNhan where CongNhanID=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Nguoi nguoi=nguoiDAO.getNguoiByID(id);
				BoPhan bp=boPhanDAO.getBoPhanByID(rs.getString(2));
				cn=new CongNhan(nguoi, bp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cn;
	}
	
	public CongNhan layCongNhanTheoID(String id) {
		CongNhan cn=null;
		try {
			String sql="select *from CongNhan where CongNhanID=?";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Nguoi nguoi=nguoiDAO.layNguoiTheoID(id);
				BoPhan bp=boPhanDAO.layBoPhanTheoID(rs.getString(2));
				cn = new CongNhan(nguoi.getNguoiID(), bp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cn;
	}
	public ArrayList<CongNhan> getCongNhanChuaDuocPhanCong(String boPhanID) {
		list = new ArrayList<CongNhan>();
		try {
			String sql = "select cn.CongNhanID, n.HoTen, cn.BoPhanID, cn.HeSoLamViec\r\n" + "from CongNhan as cn\r\n"
					+ "left join BangPhanCongCongNhan as pccn on pccn.CongNhanID = cn.CongNhanID\r\n"
					+ "inner join Nguoi as n on n.NguoiID = cn.CongNhanID\r\n"
					+ "where pccn.CongNhanID is null and cn.BoPhanID like ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, boPhanID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String congNhanID = rs.getString("CongNhanID");
				String boPhanIDFromDB = rs.getString("BoPhanID");
				String hoTenDB = rs.getString("HoTen");
				double heSoLamViec = rs.getDouble("HeSoLamViec");

				// Tạo đối tượng CongNhan và thiết lập thông tin
//				CongNhan congNhan = new CongNhan(null, hoTenDB, null, false, null, null, null, null, false, congNhanID,
//						boPhanIDFromDB, heSoLamViec);
				CongNhan congNhan=new CongNhan(congNhanID, hoTenDB, null, false, sql, congNhanID, boPhanIDFromDB, hoTenDB, false, null, heSoLamViec);
						

				// Thêm vào danh sách
				list.add(congNhan);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return (ArrayList<CongNhan>) list;
	}
	
	public List<CongNhan> getAllCongNhan() {
		List<CongNhan> list = new ArrayList<CongNhan>();
		try {
			String sql = "select * from Nguoi JOIN CongNhan on Nguoi.NguoiID = CongNhan.CongNhanID where Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CongNhan congNhan = new CongNhan(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), boPhanDAO.getBoPhanByID(rs.getString(11)));
			 
				list.add(congNhan);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public CongNhan getCongNhanByIDFullProps(String id) {
		CongNhan congNhan = null;
		try {
			String sql = "select * from Nguoi JOIN CongNhan on Nguoi.NguoiID = CongNhan.CongNhanID where CongNhanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				congNhan = new CongNhan(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), boPhanDAO.getBoPhanByID(rs.getString(11)));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return congNhan;
	}
	
	public LocalDate convertDateTimeDate(String id) { // lười select từng thuộc tính nên làm vậy nha :v
		LocalDate localDate = null;
		try {
			ResultSet rs = null;
			String sql = "SELECT CONVERT(DATE, Nguoi.NgaySinh) from Nguoi JOIN CongNhan on "
					+ "Nguoi.NguoiID = CongNhan.CongNhanID where CongNhan.CongNhanID = ?";
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
	
	public String getMaxCongNhanIDSubstring() { // lấy mã người lớn nhất => substring lấy 4 số cuối
		String maxCongNhanID = null;
		try {
			String sql = "select max(NguoiID) from Nguoi join CongNhan on Nguoi.NguoiID = CongNhan.CongNhanID";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				maxCongNhanID = rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String builder  = new StringBuilder(maxCongNhanID).reverse().substring(0, 4);
		return new StringBuilder(builder).reverse().toString();
	}
	
	public boolean addCongNhan(CongNhan congNhan) {
		int n = 0;
		try {
			String sql = "insert into CongNhan VALUES(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, congNhan.getNguoiID());
			statement.setString(2, congNhan.getBoPhan().getBoPhanID());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean suaCongNhan(CongNhan congNhan) {
		int n = 0;
		try {
			String sql = "update CongNhan set BoPhanID = ? where CongNhanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, congNhan.getBoPhan().getBoPhanID());
			statement.setString(2, congNhan.getNguoiID());
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<CongNhan> getAllCongNhanChuaKyHopDong() {
		List<CongNhan> list = new ArrayList<CongNhan>();
		try {
			String sql = "select *\r\n"
					+ "from Nguoi JOIN CongNhan ON Nguoi.NguoiID = CongNhan.CongNhanID LEFT JOIN HopDongLaoDong \r\n"
					+ "ON HopDongLaoDong.NguoiID = CongNhan.CongNhanID where HopDongLaoDong.HopDongID IS NULL";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CongNhan congNhan = new CongNhan(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), boPhanDAO.getBoPhanByID(rs.getString(11)));
				list.add(congNhan);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<CongNhan> getAllCongNhanTimKiemNangCao(String tenCongNhan) {
		List<CongNhan> list = new ArrayList<CongNhan>();
		try {
			String sql = "select * from Nguoi JOIN CongNhan on Nguoi.NguoiID = CongNhan.CongNhanID where HoTen like N'%"+ tenCongNhan +"%' and Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CongNhan congNhan = new CongNhan(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), boPhanDAO.getBoPhanByID(rs.getString(11)));
				list.add(congNhan);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<CongNhan> getCongNhanChuaDuocPhanCong(String boPhanID, String caLamViec, LocalDate date) {
		list = new ArrayList<CongNhan>();
		try {
			String sql = "select cn.CongNhanID, n.HoTen, cn.BoPhanID, cn.HeSoLamViec\r\n" + "from CongNhan as cn\r\n"
					+ "left join BangPhanCongCongNhan as pccn on pccn.CongNhanID = cn.CongNhanID\r\n"
					+ "inner join Nguoi as n on n.NguoiID = cn.CongNhanID\r\n"
					+ "inner join BangChamCong as bcc on bcc.NguoiID = n.NguoiID\r\n"
					+ "where pccn.CongNhanID is null and cn.BoPhanID like ? and bcc.tinhTrang like N'Có mặt' and bcc.CaLamViec like ? "
					+ "and bcc.Ngay = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, boPhanID);
			statement.setString(2, caLamViec);
			statement.setDate(3, Date.valueOf(date));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String congNhanID = rs.getString("CongNhanID");
				String boPhanIDFromDB = rs.getString("BoPhanID");
				String hoTenDB = rs.getString("HoTen");
				double heSoLamViec = rs.getDouble("HeSoLamViec");

				// Tạo đối tượng CongNhan và thiết lập thông tin
//				CongNhan congNhan = new CongNhan(null, hoTenDB, null, false, null, null, null, null, false, congNhanID,
//						boPhanIDFromDB, heSoLamViec);
				CongNhan congNhan = new CongNhan(congNhanID, hoTenDB, null, false, sql, congNhanID, boPhanIDFromDB,
						hoTenDB, false, null, heSoLamViec);

				// Thêm vào danh sách
				list.add(congNhan);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return (ArrayList<CongNhan>) list;
	}
	
	public ArrayList<CongNhan> getDSCongNhanTheoBoPhan(String maBP) {
		ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
		String sql="select * from CongNhan as cn join Nguoi as n on cn.CongNhanID=n.NguoiID where BoPhanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maBP);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				BoPhan bp=boPhanDAO.getBoPhanByID(rs.getString(2));
				Nguoi nguoi = nguoiDAO.getNguoiByID(rs.getString(1));
				CongNhan cn=new CongNhan(nguoi, bp);
				ds.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public int getSoLuongCNTheoBoPhan(String maBP) {
		int sl=0;
		try {
			String sql="select count(*) from CongNhan as cn join Nguoi as n on cn.CongNhanID=n.NguoiID where BoPhanID=? and TrangThai=1";
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
	
	public ArrayList<CongNhan> getAllCongNhan1() {
		ArrayList<CongNhan> list = new ArrayList<CongNhan>();
		try {
			String sql = "select * from Nguoi JOIN CongNhan on Nguoi.NguoiID = CongNhan.CongNhanID where Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
//				CongNhan congNhan = new CongNhan(rs.getString(1), rs.getString(2), convertDateTimeDate(rs.getString(1)), rs.getBoolean(4), rs.getString(5), 
//						rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), boPhanDAO.getBoPhanByID(rs.getString(11)), rs.getDouble(12));
				CongNhan cn=getCongNhanTheoID(rs.getString(1));
				list.add(cn);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<CongNhan> getCongNhanChuaDuocPhanCong(String boPhanID, LocalDate ngay, String caLamViec,
			String congDoanID) {
		ArrayList<CongNhan> list = new ArrayList<CongNhan>();
		try {
			String sql = "SELECT cn.CongNhanID, n.HoTen, bp.boPhanID, bp.tenBoPhan " + "FROM CongNhan AS cn "
					+ "INNER JOIN Nguoi AS n ON n.NguoiID = cn.CongNhanID "
					+ "INNER JOIN BoPhan AS bp ON bp.BoPhanID = cn.BoPhanID "
					+ "INNER JOIN BangChamCong AS BCC ON BCC.NguoiID = N.NguoiID " + "WHERE bp.BoPhanID = ? "
					+ "AND bcc.calamViec LIKE ? " + "AND bcc.Ngay = ? " + "AND NOT EXISTS ( " + "    SELECT 1 "
					+ "    FROM BangPhanCongCongNhan AS pccn "
					+ "    INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.PhanCongID = pccn.PhanCongBoPhanID "
					+ "    WHERE pccn.CongNhanID = cn.CongNhanID " + "        AND pcbp.CaLamViec LIKE ? "
					+ "        AND pcbp.Ngay = ? " + "        AND pcbp.CongDoanID = ?" + ")";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, boPhanID);
			statement.setString(2, "%" + caLamViec + "%");
			statement.setDate(3, java.sql.Date.valueOf(ngay));
			statement.setString(4, "%" + caLamViec + "%");
			statement.setDate(5, java.sql.Date.valueOf(ngay));
			statement.setString(6, congDoanID);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String congNhanID = rs.getString("CongNhanID");
				String hoTen = rs.getString("HoTen");
				String maBoPhan = rs.getString("boPhanID");
				String tenBoPhan = rs.getString("tenBoPhan");
				BoPhan bp = new BoPhan(maBoPhan, tenBoPhan);
				CongNhan congNhan = new CongNhan(congNhanID, hoTen, bp);
				list.add(congNhan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
