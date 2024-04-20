package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBConnection;
import entities.BangChamCong;
import entities.BoPhan;
import entities.CongNhan;
import entities.Nguoi;

public class BangChamCongDAO {
	private Connection con;
	public NguoiDAO nguoiDAO = new NguoiDAO();
	private CongNhanDAO congNhanDAO=new CongNhanDAO();
	private BoPhanDAO boPhanDAO=new BoPhanDAO();
	public BangChamCongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public boolean addBangChamCong(BangChamCong bangChamCong) {
		int n = 0;
		try {
			LocalDate ngay = bangChamCong.getNgay();
			java.sql.Date date = java.sql.Date.valueOf(ngay);
			String sql = "Insert into BangChamCong VALUES(?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bangChamCong.getChamCongID());
			statement.setString(2, bangChamCong.getNguoi().getNguoiID());
			statement.setDate(3, date);
			statement.setString(4, bangChamCong.getCaLamViec());
			statement.setString(5, bangChamCong.getTinhTrang());

			n = statement.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<BangChamCong> getAllBangChamCongByNgayVaCa(LocalDate ngay, String ca) {
		List<BangChamCong> list = new ArrayList<BangChamCong>();
		try {
			String sql = "select * from BangChamCong left join CongNhan on CongNhan.CongNhanID = BangChamCong.NguoiID where CaLamViec = ? and Ngay = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ca);
			statement.setDate(2, Date.valueOf(ngay));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if(rs.getString(5) == null) {
					BangChamCong bangChamCong = new BangChamCong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)),
							rs.getDate(3).toLocalDate(), rs.getString(4), null);
					list.add(bangChamCong);
				}else {
					BangChamCong bangChamCong = new BangChamCong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)),
							rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5));
					list.add(bangChamCong);
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean kiemTraChamCongTheoCa(String ca, LocalDate datelocalDate) {
		ResultSet rs = null;
		try {
			java.sql.Date date = java.sql.Date.valueOf(datelocalDate);
			String sql = "select CaLamViec from BangChamCong left join Nguoi "
					+ "on Nguoi.NguoiID = BangChamCong.NguoiID where CaLamViec = ? and Ngay = ? and Nguoi.TrangThai = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ca);
			statement.setDate(2, date);
			
			rs = statement.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<BangChamCong> getAllBangChamCongNgayHienTai(String ca, LocalDate localDate) { //bao gồm các nhân viên còn đang đi làm
		List<BangChamCong> list = new ArrayList<BangChamCong>();
		try {
			java.sql.Date date = java.sql.Date.valueOf(localDate.now());
			String sql = "select * from BangChamCong left join Nguoi "
					+ "on Nguoi.NguoiID = BangChamCong.NguoiID "
					+ "where CaLamViec = ? and Ngay = ? and Nguoi.TrangThai = 1" ;
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ca);
			statement.setDate(2, date);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				BangChamCong bangChamCong = new BangChamCong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)),
						rs.getDate(3).toLocalDate(), rs.getString(4), rs.getString(5));
				list.add(bangChamCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	
	public boolean suaBangChamCong(BangChamCong bangChamCong) {
		int n = 0;
		try {

			java.sql.Date date = java.sql.Date.valueOf(bangChamCong.getNgay());
			String sql = "update BangChamCong set TinhTrang = ?  where ChamCongID = ? and CaLamViec = ? and Ngay = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bangChamCong.getTinhTrang().trim());
			statement.setString(2, bangChamCong.getChamCongID().trim());
			statement.setString(3, bangChamCong.getCaLamViec().trim());
			statement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			
			n = statement.executeUpdate();
			return n > 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public BangChamCong getBangChamCong3Props(String nguoiID, String caLamViec, LocalDate ngay) {
		BangChamCong bangChamCong = null;
		try {
			java.sql.Date date = java.sql.Date.valueOf(ngay);
			String sql = "select * from BangChamCong where NguoiID = ? and CaLamViec = ?  and Ngay = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nguoiID);
			statement.setString(2, caLamViec);
			statement.setDate(3, date);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				bangChamCong = new BangChamCong(rs.getString(1), nguoiDAO.getNguoiByID(rs.getString(2)), ngay, rs.getString(4), rs.getString(5));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bangChamCong;
	}
	
	public boolean kiemTraChamCongTheoNgay(LocalDate d, String caLamViec) {
		Date date=Date.valueOf(d);
		String sql="select * from BangChamCong where Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Nguoi>layDanhSachNguoi(LocalDate d, String caLamViec) {
		Date date=Date.valueOf(d);
		ArrayList<Nguoi> ds=new ArrayList<Nguoi>();
		String sql="select*from BangChamCong where Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Nguoi nguoi=nguoiDAO.layNguoiTheoID(rs.getString(2));
				ds.add(nguoi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public void luuThongTin(Nguoi nguoi, LocalDate d,String caLamViec,String tt) {
		Date date=Date.valueOf(d);
		String sql="Insert into BangChamCong values(?,?,?,?,?)";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, layMaChamCong(nguoi, d, caLamViec));
			statement.setString(2,nguoi.getNguoiID());
			statement.setDate(3, date);
			statement.setString(4, caLamViec);
			statement.setString(5, tt);
			statement.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lưu thông tin không thành công");
			e.printStackTrace();
		}
		
	}
	
	public int getSoNgayCongCuaMotNhanVien(Nguoi nguoi, LocalDate ngay) { // mới viết
		 int soNgayCong = 0;
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select Count(Nguoi.NguoiID) - 1 as SoNgayCong, Nguoi.NguoiID, MONTH(Ngay)as thang,YEAR(Ngay) as nam from BangChamCong join Nguoi on Nguoi.NguoiID = BangChamCong.NguoiID \r\n"
			 		+ " where ((CaLamViec = N'Buổi sáng' AND TinhTrang = N'Có mặt') OR (CaLamViec = N'Buổi chiều' AND TinhTrang = N'Có mặt') )  \r\n"
			 		+ " and MONTH(Ngay) = ? and YEAR(Ngay) = ? and Nguoi.NguoiID = ?  group by Nguoi.NguoiID,Ngay, MONTH(Ngay), YEAR(Ngay)";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 if(rs.getInt(1) == 1)
					 soNgayCong++;
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return soNgayCong;
	}
	
	public int getSoNgayVangCuaMotNhanVien(Nguoi nguoi, LocalDate ngay) { // mới viết
		 int soNgayCong = 0;
		 int soNgayTrongThang = ngay.getMonth().maxLength();
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select Count(Nguoi.NguoiID) - 1 as SoNgayCong, Nguoi.NguoiID, MONTH(Ngay)as thang,YEAR(Ngay) as nam from BangChamCong join Nguoi on Nguoi.NguoiID = BangChamCong.NguoiID \r\n"
			 		+ " where ((CaLamViec = N'Buổi sáng' AND TinhTrang = N'Có mặt') OR (CaLamViec = N'Buổi chiều' AND TinhTrang = N'Có mặt') )  \r\n"
			 		+ " and MONTH(Ngay) = ? and YEAR(Ngay) = ? and Nguoi.NguoiID = ?  group by Nguoi.NguoiID,Ngay, MONTH(Ngay), YEAR(Ngay)";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 if(rs.getInt(1) == 1)
					 soNgayCong++;
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return soNgayTrongThang  - soNgayCong;
	}
	
	public int getSoNgayCongCuaMotCongNhan(Nguoi nguoi, LocalDate ngay) { // mới viết
		 int soNgayCong = 0;
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select Count(Nguoi.NguoiID) - 1 as SoNgayCong, Nguoi.NguoiID, MONTH(Ngay)as thang,YEAR(Ngay) as nam from BangChamCong join Nguoi on Nguoi.NguoiID = BangChamCong.NguoiID \r\n"
			 		+ " where ((CaLamViec = N'Ca sáng' AND TinhTrang = N'Có mặt') OR (CaLamViec = N'Ca chiều' AND TinhTrang = N'Có mặt') )  \r\n"
			 		+ " and MONTH(Ngay) = ? and YEAR(Ngay) = ? and Nguoi.NguoiID = ?  group by Nguoi.NguoiID,Ngay, MONTH(Ngay), YEAR(Ngay)";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 if(rs.getInt(1) == 1)
					 soNgayCong++;
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return soNgayCong;
	}
	
	public int getSoNgayVangCuaMotCongNhan(Nguoi nguoi, LocalDate ngay) { // mới viết
		 int soNgayCong = 0;
		 int soNgayTrongThang = ngay.getMonth().maxLength();
		 try {
			 int thang = ngay.getMonthValue();
			 int nam = ngay.getYear();
			 String sql = "select Count(Nguoi.NguoiID) - 1 as SoNgayCong, Nguoi.NguoiID, MONTH(Ngay)as thang,YEAR(Ngay) as nam from BangChamCong join Nguoi on Nguoi.NguoiID = BangChamCong.NguoiID \r\n"
			 		+ " where ((CaLamViec = N'Ca sáng' AND TinhTrang = N'Có mặt') OR (CaLamViec = N'Ca chiều' AND TinhTrang = N'Có mặt') )  \r\n"
			 		+ " and MONTH(Ngay) = ? and YEAR(Ngay) = ? and Nguoi.NguoiID = ?  group by Nguoi.NguoiID,Ngay, MONTH(Ngay), YEAR(Ngay)";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, thang);
			 statement.setInt(2, nam);
			 statement.setString(3, nguoi.getNguoiID());
			 
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()) {
				 if(rs.getInt(1) == 1)
					 soNgayCong++;
			 }
				 	
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return soNgayTrongThang  - soNgayCong;
	}
	

	private String layMaChamCong(Nguoi nguoi, LocalDate d, String caLamViec) {
		String ca="";
		if(caLamViec.equals("Ca sáng") || caLamViec.equals("Buổi sáng")) {
			ca="S";
		}
		if(caLamViec.equals("Ca chiều") || caLamViec.equals("Buổi chiều")) {
			ca="C";
		}
		if(caLamViec.equals("Ca tối")) {
			ca="T";
		}
		return nguoi.getNguoiID() + ngay() + ca;
	}
	public BangChamCong layBangChamCongTheoID(String id) {
		BangChamCong bcc=new BangChamCong();
		String sql="select *from BangChamCong where ChamCongID=?";
		try {
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs=stm.executeQuery();
			while (rs.next()) {
				String macc=rs.getString(1);
				String manguoi=rs.getString(2);
				Nguoi nguoi=nguoiDAO.layNguoiTheoID(id);
				Date date=rs.getDate(3);
				String ca=rs.getString(4);
				String tt=rs.getString(5);
				bcc=new BangChamCong(macc, nguoi, date.toLocalDate(), ca, tt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bcc;
	}
	public ArrayList<BangChamCong> layBangChamCongTheoTrangThai(LocalDate date, String ca, String tt) {
		ArrayList<BangChamCong> ds=new ArrayList<BangChamCong>();
		Date d=Date.valueOf(date);
		String sql="select * from BangChamCong where Ngay=? and CaLamViec=? and TinhTrang=?";
		try {
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setDate(1, d);
			stm.setString(2, ca);
			stm.setString(3, tt);
			ResultSet rs=stm.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				BangChamCong bcc=layBangChamCongTheoID(ma);
				ds.add(bcc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public ArrayList<Nguoi> layDSChamCongCN(LocalDate date, String ca, String tt) {
		ArrayList<Nguoi> ds=new ArrayList<Nguoi>();
		Date d=Date.valueOf(date);
		String sql="select * from BangChamCong where Ngay=? and CaLamViec=? and TinhTrang=?";
		try {
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setDate(1, d);
			stm.setString(2, ca);
			stm.setString(3, tt);
			ResultSet rs=stm.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(2);
				Nguoi nguoi=nguoiDAO.layNguoiTheoID(ma);
				ds.add(nguoi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public void capNhatTrangThai(String mabcc,String tt) {
		String sql="update BangChamCong set TinhTrang=? where ChamCongID=?";
		try {
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setString(1, tt);
			stm.setString(2, mabcc);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String bonSoCuoi(String id) {
		String maCongNhan = congNhanDAO.layCongNhanTheoID(id).getNguoiID(); 
		String builder = new StringBuilder(maCongNhan).reverse().substring(0, 4);
		String number = new StringBuilder(builder).reverse().toString();
		return number;

	}

	private String ngay() {
		String[] dateSplit = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()).split("-");
		String date = dateSplit[0] + dateSplit[1] + dateSplit[2].substring(2, 4);
		return date;
	}
	
	public ArrayList<CongNhan> getDSChamCongCN(LocalDate ngay, String caLamViec) {
		ArrayList<CongNhan> ds =new ArrayList<CongNhan>();
		String sql="select * from CongNhan as cn join BangChamCong as bcc on cn.CongNhanID=bcc.NguoiID where Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(ngay));
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String congNhanID=rs.getString(1);
				CongNhan cn=congNhanDAO.getCongNhanTheoID(congNhanID);
				ds.add(cn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	public ArrayList<CongNhan> getDSChamCongCN(LocalDate ngay,String caLamViec, String tinhTrang) {
		ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
		String sql="select * from CongNhan as cn join BangChamCong as bcc on cn.CongNhanID=bcc.NguoiID where Ngay=? and CaLamViec=? and TinhTrang=?";
		try {
			Date date=Date.valueOf(ngay);
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setString(2, caLamViec);
			statement.setString(3,tinhTrang);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String congNhanID=rs.getString(1);
				CongNhan cn=congNhanDAO.getCongNhanTheoID(congNhanID);
				ds.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public ArrayList<CongNhan> getDSChamCongCNTheoBoPhan(LocalDate date, String caLamViec, String mabp, String tinhTrang) {
		ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
		String sql="select * from BangChamCong where Ngay=? and CaLamviec=? and NguoiID like ? and TinhTrang=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(date));
			statement.setString(2, caLamViec);
			statement.setString(3, "%1"+mabp+"%");
			statement.setString(4, tinhTrang);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String nguoiID=rs.getString(2);
				CongNhan cn=congNhanDAO.getCongNhanTheoID(nguoiID);
				ds.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean kiemTraChamCongCN(LocalDate ngay, String caLamViec) {
		String sql="select * from CongNhan as cn join BangChamCong as bcc on cn.CongNhanID=bcc.NguoiID where Ngay=? and CaLamViec=?";
		try {
			Date date=Date.valueOf(ngay);
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private String getMaChamCong(Nguoi nguoi, LocalDate d, String caLamViec) {
		String ca="";
		if(caLamViec.equals("Ca sáng")) {
			ca="S";
		}
		if(caLamViec.equals("Ca chiều")) {
			ca="C";
		}
		if(caLamViec.equals("Ca tối")) {
			ca="T";
		}
		return nguoi.getNguoiID()+ngay()+ca;
	}
	
	public void addBangChamCong(Nguoi nguoi, LocalDate d,String caLamViec,String tt) {
		Date date=Date.valueOf(d);
		String sql="Insert into BangChamCong values(?,?,?,?,?)";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, getMaChamCong(nguoi, d, caLamViec));
			statement.setString(2,nguoi.getNguoiID());
			statement.setDate(3, date);
			statement.setString(4, caLamViec);
			statement.setString(5, tt);
			statement.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lưu thông tin không thành công");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<CongNhan> getDSChamCongCNTheoBoPhan(LocalDate date, String caLamViec, String mabp) {
		ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
		String sql="select * from BangChamCong where Ngay=? and CaLamviec=? and NguoiID like ?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(date));
			statement.setString(2, caLamViec);
			statement.setString(3, "%1"+mabp+"%");
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String nguoiID=rs.getString(2);
				CongNhan cn=congNhanDAO.getCongNhanTheoID(nguoiID);
				ds.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public ArrayList<BoPhan> getDSBoPhanDaChamCong(LocalDate date, String caLamViec) {
		ArrayList<BoPhan> ds=new ArrayList<BoPhan>();
		String sql="select cn.BoPhanID, count(*) from BangChamCong as bcc join CongNhan as cn on bcc.NguoiID=cn.CongNhanID\r\n"
				+ "where bcc.TinhTrang=N'Có mặt' and Ngay=? and CaLamViec=? \r\n"
				+ "group by BoPhanID";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(date));
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String bpid=rs.getString(1);
				BoPhan bp=boPhanDAO.getBoPhanByID(bpid);
				ds.add(bp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public boolean kiemTraChamCongBoPhan(String mabp,LocalDate ngay, String caLamViec) {
		String sql="select BophanID,count(*) from BangChamCong as cc join CongNhan as cn on cc.NguoiID=cn.CongNhanID where BoPhanID=? and Ngay=? and CaLamViec=? group by BoPhanID";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
			statement.setDate(2, Date.valueOf(ngay));
			statement.setString(3, caLamViec);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
