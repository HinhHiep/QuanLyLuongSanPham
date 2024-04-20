package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.DBConnection;
import entities.BangPhanCongBoPhan;
import entities.BoPhan;
import entities.CongDoan;
import entities.SanPham;
import enums.LoaiSanPham;

public class CongDoanDAO {
	private Connection con;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	public CongDoanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
	public ArrayList<CongDoan> layDSCongDoanTheoMaSP(String masp) {
		ArrayList<CongDoan> ds=new ArrayList<CongDoan>();
		String sql="select*from CongDoan where SanPhamID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, masp);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				String ten=rs.getString(2);
				int mucUuTien=rs.getInt(3);
				String yckt=rs.getString(4);
				double gia=rs.getDouble(5);
				SanPham sp=sanPhamDAO.laySanPhamTheoID(rs.getString(6).toString());
				CongDoan cd=new CongDoan(ma, ten, mucUuTien, yckt, gia, sp);
				ds.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public CongDoan layCongDoanTheoID(String macd) {
		CongDoan congDoan=null;
		String sql="select *from CongDoan where CongDoanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, macd);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				String ten=rs.getString(2);
				int mucUuTien=rs.getInt(3);
				String yckt=rs.getString(4);
				double gia=rs.getDouble(5);
				SanPham sp=sanPhamDAO.laySanPhamTheoID(rs.getString(6).toString());
				congDoan=new CongDoan(ma, ten, mucUuTien, yckt, gia, sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return congDoan;
	}
	public int laySLCongDoanTheoLoaiSP(LoaiSanPham loaisp) {
		int sl=0;
		String sql="select COUNT(*) from CongDoan as cd join SanPham as sp on cd.SanPhamID=sp.SanPhamID where sp.LoaiSanPham=? group by sp.LoaiSanPham";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1,loaisp.toString());
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
	public ArrayList<CongDoan> layDSCongDoanTheoLoaiSP(LoaiSanPham loaisp) {
		ArrayList<CongDoan> ds=new ArrayList<CongDoan>();
		String sql="select * from CongDoan as cd join SanPham as sp on cd.SanPhamID=sp.SanPhamID where sp.LoaiSanPham=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, loaisp.toString());
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String macd=rs.getString(1);
				CongDoan cd=layCongDoanTheoID(macd);
				ds.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public boolean kiemTraTrungMa(String macd) {
		CongDoan cd=layCongDoanTheoID(macd);
		System.out.println(cd.getSanPham().getLoaiSanPham());
		ArrayList<CongDoan> ds=layDSCongDoanTheoLoaiSP(cd.getSanPham().getLoaiSanPham());
		for (CongDoan congDoan : ds) {
			if(congDoan.getCongDoanID().equals(macd))
					return false;
		}
		return true;
	}
	public String layMa(String maSP) {
		SanPham sp=sanPhamDAO.getSanPhamTheoID(maSP);
		LoaiSanPham l=sp.getLoaiSanPham();
		String s="CD";
//		if(l.toString().equals("Áo sơ mi"))
//			s=s+"ASM";
//		if(l.toString().equals("Áo khoác"))
//			s=s+"AK";
//		if(l.toString().equals("Đồ thể thao"))
//			s=s+"DTT";
//		if(l.toString().equals("Váy liền thân"))
//			s=s+"VLT";
//		if(l.toString().equals("Quần tây"))
//			s=s+"QT";
//		if(l.toString().equals("Quần jean"))
//			s=s+"QJ";
		String ma=maSP.substring(2);
		s=s+ma;
		ArrayList<CongDoan> ds=layDSCongDoanTheoMaSP(maSP);
		int index=ds.size()-1;
		if(index>=0) {
			CongDoan cd=ds.get(index);
			int stt=baSoCuoi(cd.getCongDoanID())+1;
			if(stt<10)
				s=s+"00"+stt;
			else if(stt<100)
				s=s+"0"+stt;
			else
				s=s+stt;
		}
		else {
			s=s+"001";
		}
		return s;
	}
	public void themCongDoan(CongDoan cd) {
		String sql="insert into CongDoan values(?,?,?,?,?,?)";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, cd.getCongDoanID());
			statement.setString(2, cd.getTenCongDoan());
			statement.setInt(3, cd.getMucUuTien());
			statement.setString(4, cd.getMucYeuCauKyThuat());
			statement.setDouble(5, cd.getDonGia());
			statement.setString(6, cd.getSanPham().getSanPhamID());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void xoaCongDoan(String cd) {
		String sql="delete from CongDoan where CongDoanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, cd);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void capNhatCongDoan(String macd,String ten,int mucUuTien,String yckt,double donGia) {
		String sql="update CongDoan set TenCongDoan=?,MucUuTien=?,MucYeuCauKyThuat=?,DonGia=? where CongDoanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, ten);
			statement.setInt(2, mucUuTien);
			statement.setString(3, yckt);
			statement.setDouble(4, donGia);
			statement.setString(5, macd);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cập nhật thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cập nhật không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	public int getSoLuongDaPhanCongBP(String congDoanID) {
		int res = 0;
		try {
			String sql = "select sum(pcbp.SoLuong) as TongSoLuong\r\n" + "from BangPhanCongBoPhan as pcbp \r\n"
					+ "inner join CongDoan as cd on cd.CongDoanID = pcbp.CongDoanID\r\n" + "where cd.CongDoanID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, congDoanID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
		}

		return res;
	}
	
	public String layMa(String maSP) {
		SanPham sp=sanPhamDAO.getSanPhamTheoID(maSP);
		LoaiSanPham l=sp.getLoaiSanPham();
		String s="CD";
		if(l.toString().equals("Áo sơ mi"))
			s=s+"ASM";
		if(l.toString().equals("Áo khoác"))
			s=s+"AK";
		if(l.toString().equals("Đồ thể thao"))
			s=s+"DTT";
		if(l.toString().equals("Váy liền thân"))
			s=s+"VLT";
		if(l.toString().equals("Quần tây"))
			s=s+"QT";
		if(l.toString().equals("Quần jean"))
			s=s+"QJ";
		ArrayList<CongDoan> ds=layDSCongDoanTheoMaSP(maSP);
		int index=ds.size()-1;
		if(index>=0) {
			CongDoan cd=ds.get(index);
			int stt=baSoCuoi(cd.getCongDoanID())+1;
			if(stt<10)
				s=s+"00"+stt;
			else if(stt<100)
				s=s+"0"+stt;
			else
				s=s+stt;
		}
		
		return s;
	}
	
	private int baSoCuoi(String id) {
		String kq=id.substring(id.length()-3);
		int so=Integer.parseInt(kq);
		return so;
	}
	
	public ArrayList<CongDoan> getAllCongDoan() {
		ArrayList<CongDoan> ds=new ArrayList<CongDoan>();
		String sql="select*from CongDoan";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String macd=rs.getString(1);
				CongDoan cd=layCongDoanTheoID(macd);
				ds.add(cd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	public ArrayList<BangPhanCongBoPhan> layDSPhanCongBoPhanTheoMaSPBP(String masp, String maBoPhan, String caLamViec,
			int day, int month, int year) {
		ArrayList<BangPhanCongBoPhan> dsPhanCong = new ArrayList<>();
		String sql = "SELECT pcbp.PhanCongID, pcbp.SoLuong, pcbp.Ngay, pcbp.CaLamViec, "
				+ "cd.CongDoanID, cd.TenCongDoan, cd.MucUuTien, cd.MucYeuCauKyThuat, cd.DonGia, "
				+ "sp.SanPhamID, sp.TenSanPham " + "FROM CongDoan AS cd "
				+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.CongDoanID = cd.CongDoanID "
				+ "INNER JOIN BoPhan AS bp ON pcbp.BoPhanID = bp.BoPhanID "
				+ "INNER JOIN SanPham AS sp ON cd.SanPhamID = sp.SanPhamID "
				+ "WHERE pcbp.BoPhanID = ? AND cd.SanPhamID = ? AND pcbp.CaLamViec LIKE ? "
				+ "AND YEAR(pcbp.Ngay) = ? AND MONTH(pcbp.Ngay) = ? AND DAY(pcbp.Ngay) = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maBoPhan);
			statement.setString(2, masp);
			statement.setString(3, "%" + caLamViec + "%");
			statement.setInt(4, year);
			statement.setInt(5, month);
			statement.setInt(6, day);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String phanCongID = rs.getString("PhanCongID");
				int soLuong = rs.getInt("SoLuong");
				LocalDate ngay = rs.getDate("Ngay").toLocalDate();
				String caLamViecResult = rs.getString("CaLamViec");

				String congDoanID = rs.getString("CongDoanID");
				String tenCongDoan = rs.getString("TenCongDoan");
				int mucUuTien = rs.getInt("MucUuTien");
				String mucYeuCauKyThuat = rs.getString("MucYeuCauKyThuat");
				double donGia = rs.getDouble("DonGia");

				String sanPhamID = rs.getString("SanPhamID");
				String tenSanPham = rs.getString("TenSanPham");

				// Tạo đối tượng BangPhanCongBoPhan từ kết quả truy vấn và thêm vào danh sách
				BangPhanCongBoPhan phanCongBoPhan = new BangPhanCongBoPhan(phanCongID, soLuong, ngay, caLamViecResult,
						new CongDoan(congDoanID, tenCongDoan, mucUuTien, mucYeuCauKyThuat, donGia, null),
						new BoPhan(maBoPhan, "")); // Thay thế tên BoPhan nếu bạn có thể lấy được
				dsPhanCong.add(phanCongBoPhan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsPhanCong;
	}
	
	public int getSoLuongDaPhanCongCuaCongDoanTheoNgayCaLamViec(String phanCongBPID) {
		int res = 0;
		try {
			String sql = "SELECT SUM(pccn.SoLuongPhanCong) AS TongSoLuongPhanCong " + "FROM BangPhanCongBoPhan AS pcbp "
					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.PhanCongBoPhanID = pcbp.PhanCongID "
					+ "WHERE pcbp.PhanCongID = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, phanCongBPID);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				res = rs.getInt("TongSoLuongPhanCong");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
		}
		return res;
	}
	
	public int laySoLuongHoanThanhCuaCongDoanTheoNgayCaLamViec(String phanCongBPID, String caLamViec, int day,
			int month, int year) {
		int res = 0;
		try {
			String sql = "SELECT SUM(pccn.SoLuongHoanThanh) AS TongSoLuongHoanThanh "
					+ "FROM BangPhanCongBoPhan AS pcbp "
					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.PhanCongBoPhanID = pcbp.PhanCongID "
					+ "WHERE pcbp.PhanCongID = ? " + "AND DAY(pcbp.Ngay) = ? " + "AND MONTH(pcbp.Ngay) = ? "
					+ "AND YEAR(pcbp.Ngay) = ? " + "AND pcbp.CaLamViec LIKE ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, phanCongBPID);
			statement.setInt(2, day);
			statement.setInt(3, month);
			statement.setInt(4, year);
			statement.setString(5, "%" + caLamViec + "%");

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				res = rs.getInt("TongSoLuongHoanThanh");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
		}
		return res;
	}
	
	public int getSoLuongDaHoanThanhCuaCongDoanCheckUuTien(String maSP, int doUuTien, LocalDate date) {
		int tongSoLuongHoanThanh = 0;
		try {
			// Câu truy vấn SQL để lấy tổng số lượng đã hoàn thành của công đoàn có độ ưu
			// tiên cụ thể
			String sql = "SELECT SUM(pccn.SoLuongHoanThanh) as TongSoLuongHoanThanh "
					+ "FROM BangPhanCongBoPhan as pcbp "
					+ "INNER JOIN BangPhanCongCongNhan as pccn ON pccn.PhanCongBoPhanID = pcbp.PhanCongID "
					+ "INNER JOIN CongDoan as cd ON cd.CongDoanID = pcbp.CongDoanID "
					+ "INNER JOIN SanPham as sp ON sp.SanPhamID = cd.SanPhamID " + "WHERE sp.SanPhamID = ? "
					+ "AND cd.MucUuTien = ? " + "AND pcbp.NGAY = ?"; // Ngày cần so sánh

			// Chuyển đổi LocalDate thành java.sql.Date
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);

			// Chuẩn bị câu truy vấn
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			statement.setInt(2, doUuTien);
			statement.setDate(3, sqlDate); // Set giá trị ngày vào câu truy vấn SQL

			// Thực hiện truy vấn
			ResultSet rs = statement.executeQuery();

			// Xử lý kết quả truy vấn
			if (rs.next()) {
				// Lấy thông tin từ ResultSet
				tongSoLuongHoanThanh = rs.getInt("TongSoLuongHoanThanh");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
		}

		return tongSoLuongHoanThanh;
	}
	
	
}
