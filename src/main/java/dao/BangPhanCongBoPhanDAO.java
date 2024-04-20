package dao;
//check_L1
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBConnection;
import entities.BangPhanCongBoPhan;
import entities.BoPhan;
import entities.CongDoan;

public class BangPhanCongBoPhanDAO {
	private Connection con;
	private CongDoanDAO congDoanDAO=new CongDoanDAO();
	private BoPhanDAO boPhanDAO=new BoPhanDAO();
	
	public BangPhanCongBoPhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
//---------------------------	
	public int laySoLuongCDDaPhanCong(String mabp,LocalDate d,String caLamViec) {
		int sl=0;
		Date date=Date.valueOf(d);
		String sql="select count(*) from BangPhanCongBoPhan where BoPhanID=? and Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
			statement.setDate(2, date);
			statement.setString(3, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}
//--------------------------------	
	public int laySLSanPhamCongDoanDaPhanCong(String macd) {
		int sl=0;
		String sql="select sum(SoLuong) from BangPhanCongBoPhan where CongDoanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, macd);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sl;
	}
//---------------------------------
	public int  laySLConLai(String macd) {
		int sl=0;
		CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
		int tong=cd.getSanPham().getSoLuong();
		int phanCong=laySLSanPhamCongDoanDaPhanCong(macd);
		sl=tong-phanCong;
		return sl;
		
	}
//------------------------------------------
	public ArrayList<BangPhanCongBoPhan> getAllDSPhanCongBoPhan(LocalDate ngay, String caLamViec) {
		ArrayList<BangPhanCongBoPhan> ds=new ArrayList<BangPhanCongBoPhan>();
		String sql="select*from BangPhanCongBoPhan where Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(ngay));
			statement.setString(2, caLamViec);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String pcid=rs.getString(1);
				BangPhanCongBoPhan bpc=layBangPhanCongTheoID(pcid);
				ds.add(bpc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
//------------------------------------------
	public ArrayList<BangPhanCongBoPhan> getDSPhanCongBoPhan(String mabp,LocalDate d,String caLamViec) {
		Date date=Date.valueOf(d);
		ArrayList<BangPhanCongBoPhan> ds=new ArrayList<BangPhanCongBoPhan>();
		String sql="select*from BangPhanCongBoPhan where BoPhanID=? and Ngay=? and CaLamViec=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
			statement.setDate(2, date);
			statement.setString(3, caLamViec);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				int sl=rs.getInt(2);
				LocalDate day=rs.getDate(3).toLocalDate();
				String caLam=rs.getString(4);
				CongDoan congDoan=congDoanDAO.layCongDoanTheoID(rs.getString(5));
				BoPhan boPhan=boPhanDAO.getBoPhanByID(rs.getString(6));
				BangPhanCongBoPhan phanCong=new BangPhanCongBoPhan(ma, sl, day, caLam, congDoan, boPhan);
				ds.add(phanCong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
//----------------------------------------------------
	public ArrayList<BangPhanCongBoPhan> getDSPhanCongBoPhan(String mabp,LocalDate d) {
		Date date=Date.valueOf(d);
		ArrayList<BangPhanCongBoPhan> ds=new ArrayList<BangPhanCongBoPhan>();
		String sql="select*from BangPhanCongBoPhan where BoPhanID=? and Ngay=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
			statement.setDate(2, date);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				BangPhanCongBoPhan phanCong=layBangPhanCongTheoID(ma);
				ds.add(phanCong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
//---------------------------
	public boolean kiemTraPhanCong(String maCD) {
		String sql="select*from BangPhanCongBoPhan where CongDoanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maCD);
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
//--------------------------------------
	public String layMaBangPhanCong(String maBP,LocalDate ngay) {
		String s="";
		int year=(ngay.getYear())%100;
		if(year<10)
			s=s+"0"+year;
		else
			s=s+year;
		int month=ngay.getMonthValue();
		if(month<10)
			s=s+"0"+month;
		else
			s=s+month;
		int day=ngay.getDayOfMonth();
		if(day<10)
			s=s+"0"+day;
		else
			s=s+day;
		s=s+maBP;
		int stt=1;
		if(kiemTraPhanCongBoPhan(maBP)) {
			ArrayList<BangPhanCongBoPhan> ds=getDSPhanCongBoPhan(maBP, ngay);
			if(ds.size()>0) {
				BangPhanCongBoPhan bpcbp=ds.get(ds.size()-1);
				String id=bpcbp.getPhanCongID();
				stt=baSoCuoi(id)+1;
			}
			
		}
		if(stt<10)
			s=s+"00"+stt;
		else if(stt<100)
			s=s+"0"+stt;
		else
			s=s+stt;
		return s;
	}
	private int baSoCuoi(String id) {
		String kq=id.substring(id.length()-3);
		int so=Integer.parseInt(kq);
		return so;
	}
//------------------------------------------------------
	public void luuThongTin(BangPhanCongBoPhan bpc) {
		String sql="insert into BangPhanCongBoPhan values (?,?,?,?,?,?)";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, bpc.getPhanCongID());
			statement.setInt(2, bpc.getSoLuong());
			statement.setDate(3, Date.valueOf(bpc.getNgay()));
			statement.setString(4,	bpc.getCaLamViec());
			statement.setString(5, bpc.getCongDoan().getCongDoanID());
			statement.setString(6, bpc.getBoPhan().getBoPhanID());
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Phân công bộ phận không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
//--------------------------------
	public void xoaThongTin(BangPhanCongBoPhan bpc) {
		String sql="delete from BangPhanCongBoPhan where PhanCongID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, bpc.getPhanCongID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//-----------------------------------
	public BangPhanCongBoPhan layBangPhanCongTheoID(String id) {
		BangPhanCongBoPhan bpc=null;
		String sql="select*from BangPhanCongBoPhan where PhanCongID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				int sl=rs.getInt(2);
				LocalDate d=rs.getDate(3).toLocalDate();
				String caLamViec=rs.getString(4);
				CongDoan cd=congDoanDAO.layCongDoanTheoID(rs.getString(5));
				BoPhan bp=boPhanDAO.getBoPhanByID(rs.getString(6));
				bpc=new BangPhanCongBoPhan(ma, sl, d, caLamViec, cd, bp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bpc;
	}
	public BangPhanCongBoPhan layBangPhanCong(String mabp,String macd,LocalDate ngay) {
		BangPhanCongBoPhan bpc=null;
		String sql="select * from BangPhanCongBoPhan where BoPhanID=? and CongDoanID=? and Ngay=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
			statement.setString(2, macd);
			statement.setDate(3, Date.valueOf(ngay));
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				bpc=layBangPhanCongTheoID(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bpc;
	}
//----------------------------------------
	public void capNhatThongTin(String mapc,int sl,String macd,String mabp) {
		String sql="update BangPhanCongBoPhan set SoLuong=?,CongDoanID=?,BoPhanID=? where PhanCongID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1, sl);
			statement.setString(2, macd);
			statement.setString(3, mabp);
			statement.setString(4, mapc);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//----------------------------------------
	public List<BangPhanCongBoPhan> getPhanCongBoPhanByCongDoanID(String congDoanID) {
		ArrayList<BangPhanCongBoPhan> listPhanCongBoPhan = new ArrayList<>();
		try {
			String sql = "SELECT * FROM BangPhanCongBoPhan WHERE CongDoanID = ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, congDoanID);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						String phanCongID=rs.getString(1);
						int soLuong=rs.getInt(2);
						LocalDate ngay=rs.getDate(3).toLocalDate();
						String caLamViec=rs.getString(4);
						String cdID=rs.getString(5);
						CongDoan cd=congDoanDAO.layCongDoanTheoID(cdID);
						String maBoPhan=rs.getString(6);
						BoPhan bp=boPhanDAO.getBoPhanByID(maBoPhan);
						BangPhanCongBoPhan bpcbp= new BangPhanCongBoPhan(phanCongID, soLuong, ngay, caLamViec, cd, bp);
						
//						BangPhanCongBoPhan phanCongBoPhan = new PhanCong_BoPhan(rs.getString("PhanCongID"),
//								rs.getInt("SoLuong"), rs.getTimestamp("Ngay").toLocalDateTime(),
//								rs.getString("CongDoanID"), rs.getString("BoPhanID"));
						listPhanCongBoPhan.add(bpcbp);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPhanCongBoPhan;
	}
//---------------------------------
	public boolean kiemTraPhanCongBoPhan(String mabp) {
		String sql="select* from BangPhanCongBoPhan where BoPhanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, mabp);
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
//----------------
	public int getSoLuongCNChamCong(String mabp, LocalDate date, String caLam) {
		int sl=0;
		String sql="select count(*) from BangChamCong where Ngay=? and CaLamViec=? and NguoiID like ? and TinhTrang=N'Có mặt'";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(date));
			statement.setString(2, caLam);
			statement.setString(3, "%"+mabp+"%");
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}
	public int  getSLPhanCongTheoCongDoan(String maCongDoan) {
		int sl=0;
		String sql="select sum(SoLuong) from BangPhanCongBoPhan where CongDoanID=?";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setString(1, maCongDoan);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}
	
	public BangPhanCongBoPhan getPhanCongBoPhanTheoID(String phanCongBPID) {
		BangPhanCongBoPhan pcbp = null;
		try {
			String sql = "SELECT * FROM BangPhanCongBoPhan WHERE phanCongID like ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, phanCongBPID);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						String phanCongID = rs.getString(1);
						int soLuong = rs.getInt(2);
						LocalDate ngay = rs.getDate(3).toLocalDate();
						String caLamViec = rs.getString(4);
						String cdID = rs.getString(5);
						CongDoan cd = congDoanDAO.layCongDoanTheoID(cdID);
						String maBoPhan = rs.getString(6);
						BoPhan bp = boPhanDAO.getBoPhanByID(maBoPhan);
						pcbp = new BangPhanCongBoPhan(phanCongID, soLuong, ngay, caLamViec, cd, bp);
					}
				}
				return pcbp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pcbp;
	}
}
