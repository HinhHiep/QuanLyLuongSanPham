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
import entities.SanPham;
import enums.KieuDang;
import enums.LoaiSanPham;

public class SanPhamDAO {
	private Connection con;
	private ArrayList<SanPham> listSP;
	private BoPhanDAO boPhanDAO=new BoPhanDAO();

	public SanPhamDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public ArrayList<SanPham> layDSSanPhamTheoLoai(LoaiSanPham s) {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		String sql = "select* from SanPham where LoaiSanPham=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, s.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String ten = rs.getString(2);
				String kieuDang = rs.getString(3);
				if (!kieuDang.equals("")) {
					KieuDang kd = null;
					if (kieuDang.equals("Body"))
						kd = KieuDang.BODY;
					if (kieuDang.equals("Tiêu chuẩn"))
						kd = KieuDang.TIEUCHUAN;
					if (kieuDang.equals("Form rộng"))
						kd = KieuDang.FORMRONG;
					String kichThuoc = rs.getString(4);
					String loaiSP = rs.getString(5);
					LoaiSanPham lsp = null;
					if (loaiSP.equals("Áo sơ mi"))
						lsp = LoaiSanPham.AOSOMI;
					if (loaiSP.equals("Đồ thể thao"))
						lsp = LoaiSanPham.DOTHETHAO;
					if (loaiSP.equals("Váy liền thân"))
						lsp = LoaiSanPham.VAYLIENTHAN;
					if (loaiSP.equals("Áo khoác"))
						lsp = LoaiSanPham.AOKHOAC;
					if (loaiSP.equals("Quần tây"))
						lsp = LoaiSanPham.QUANTAY;
					if (loaiSP.equals("Quần jean"))
						lsp = LoaiSanPham.QUANJEAN;
					int sl = rs.getInt(6);
					String ha = rs.getString(7);
					SanPham sp = new SanPham(id, ten, kd, kichThuoc, s, sl, ha);
					ds.add(sp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public SanPham laySanPhamTheoID(String masp) {
		SanPham sp = null;
		String sql = "select*from SanPham where SanPhamID=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, masp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String kieuDang = rs.getString(3);
				KieuDang kd = null;
				if (kieuDang.equals("Body"))
					kd = KieuDang.BODY;
				if (kieuDang.equals("Tiêu chuẩn"))
					kd = KieuDang.TIEUCHUAN;
				if (kieuDang.equals("Form rộng"))
					kd = KieuDang.FORMRONG;
				String kt = rs.getString(4);
				String loaiSP = rs.getString(5);
				LoaiSanPham lsp = null;
				if (loaiSP.equals("Áo sơ mi"))
					lsp = LoaiSanPham.AOSOMI;
				if (loaiSP.equals("Đồ thể thao"))
					lsp = LoaiSanPham.DOTHETHAO;
				if (loaiSP.equals("Váy liền thân"))
					lsp = LoaiSanPham.VAYLIENTHAN;
				if (loaiSP.equals("Áo khoác"))
					lsp = LoaiSanPham.AOKHOAC;
				if (loaiSP.equals("Quần tây"))
					lsp = LoaiSanPham.QUANTAY;
				if (loaiSP.equals("Quần jean"))
					lsp = LoaiSanPham.QUANJEAN;
				int sl = rs.getInt(6);
				String ha = rs.getString(7);
				sp = new SanPham(ma, ten, kd, kt, lsp, sl, ha);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}

	public int laySLSanPhamTheoLoaiSP(LoaiSanPham loai) {
		int sl = 0;
		String sql = "select count(*) from SanPham where LoaiSanPham=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, loai.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				sl = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}

	public boolean kiemTraTrungMa(String masp, LoaiSanPham loai) {
		ArrayList<SanPham> ds = layDSSanPhamTheoLoai(loai);
		for (SanPham sanPham : ds) {
			if (sanPham.getSanPhamID().equals(masp)) {
				return false;
			}
		}
		return true;
	}

	public String layMa(LoaiSanPham loai) {
		String s = "SP";
		if (loai.toString().equals("Áo sơ mi"))
			s = s + "ASM";
		if (loai.toString().equals("Áo khoác"))
			s = s + "AK";
		if (loai.toString().equals("Đồ thể thao"))
			s = s + "DTT";
		if (loai.toString().equals("Váy liền thân"))
			s = s + "VLT";
		if (loai.toString().equals("Quần tây"))
			s = s + "QT";
		if (loai.toString().equals("Quần jean"))
			s = s + "QJ";
		int sl = laySLSanPhamTheoLoaiSP(loai);
		String stt = "";
		boolean flag = false;
		do {
			if (sl < 10)
				stt = stt + "000" + sl;
			else if (sl < 100)
				stt = stt + "00" + sl;
			else if (sl < 1000)
				stt = stt + "0" + sl;
			else
				stt = stt + sl;
			s = s + stt;
//			if(kiemTraTrungMa(s,loai)) {
//				sl=sl+1;
//				stt="";
//			}else {
//				flag=false;
//			}
		} while (flag);
		return s;
	}

	public void themSanPham(SanPham sp) {
		String sql = "Insert into SanPham values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, sp.getSanPhamID());
			statement.setString(2, sp.getTenSanPham());
			statement.setString(3, sp.getKieuDang().toString());
			statement.setString(4, sp.getKichThuoc());
			statement.setString(5, sp.getLoaiSanPham().toString());
			statement.setInt(6, sp.getSoLuong());
			statement.setString(7, sp.getHinhAnh());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void capNhatSanPham(String ma, String ten, LoaiSanPham loai, KieuDang kd, String kichThuoc, int sl) {
		String sql = "update SanPham set TenSanPham=?,KieuDang=?,KichThuoc=?,LoaiSanPham=?,SoLuong=? where SanPhamID=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			statement.setString(2, kd.toString());
			statement.setString(3, kichThuoc);
			statement.setString(4, loai.toString());
			statement.setInt(5, sl);
			statement.setString(6, ma);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void xoaSanPham(String masp) {
		String sql = "delete from SanPham where SanPhamID=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, masp);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<SanPham> getSanPham() {
		try {
			listSP = new ArrayList<SanPham>();
			String sql = "select SanPhamID, TenSanPham, KieuDang, SoLuong\r\n" + "from SanPham";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Create a new SanPham object and set its attributes
				SanPham sanPham = new SanPham();

				sanPham.setSanPhamID(rs.getString("SanPhamID"));
				sanPham.setTenSanPham(rs.getString("TenSanPham"));

				// Set KieuDang enum
				String kieuDangString = rs.getString("KieuDang");
				KieuDang kieuDang = KieuDang.fromString(kieuDangString);

				if (kieuDang != null) {
					sanPham.setKieuDang(kieuDang);
				}
				sanPham.setSoLuong(rs.getInt("SoLuong"));

				// Add the SanPham object to the list
				listSP.add(sanPham);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return (ArrayList<SanPham>) listSP;
	}

	public ArrayList<SanPham> getSanPhamWithAtribute() {
		try {
			listSP = new ArrayList<SanPham>();
			String sql = "SELECT sp.LoaiSanPham, sp.SanPhamID, sp.TenSanPham, sp.KieuDang, sp.SoLuong "
					+ "FROM SanPham AS sp";
			try (PreparedStatement statement = con.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

				while (rs.next()) {
					SanPham sanPham = new SanPham();

					// Set LoaiSanPham enum
					String loaiSanPhamString = rs.getString("LoaiSanPham");
					LoaiSanPham lsp = (loaiSanPhamString != null) ? LoaiSanPham.fromString(loaiSanPhamString) : null;
					sanPham.setLoaiSanPham(lsp);

					sanPham.setSanPhamID(rs.getString("SanPhamID"));
					sanPham.setTenSanPham(rs.getString("TenSanPham"));

					// Set KieuDang enum
					String kieuDangString = rs.getString("KieuDang");
					KieuDang kieuDang = (kieuDangString != null) ? KieuDang.fromString(kieuDangString) : null;
					sanPham.setKieuDang(kieuDang);

					sanPham.setSoLuong(rs.getInt("SoLuong"));
					listSP.add(sanPham);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return new ArrayList<>(listSP);
	}

	public String getUrlImage(String sanPhamID) {
		String url = "";
		try {
			String sql = "select sp.HinhAnh\r\n" + "from SanPham as sp\r\n" + "where sp.SanPhamID = ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, sanPhamID);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						url = rs.getString(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return url;
	}

	public ArrayList<String> getLocSanPham() {
		List<String> list = new ArrayList<String>();
		list.add("Toàn bộ");
		list.add("Đã hoàn thành");
		list.add("Chưa hoàn thành");
		try {
			String sql = "select distinct sp.LoaiSanPham from sanpham as sp";
			try (PreparedStatement statement = con.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					// Thêm giá trị từ cột LoaiSanPham vào danh sách, nếu giá trị không trùng lặp
					String loaiSanPham = rs.getString("LoaiSanPham");
					if (!list.contains(loaiSanPham)) {
						list.add(loaiSanPham);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return (ArrayList<String>) list;
	}

	public ArrayList<SanPham> getSPTheoCombo() {
		try {
			listSP = new ArrayList<SanPham>();
			String sql = "select SanPhamID, TenSanPham, SoLuong\r\n" + "from SanPham";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Create a new SanPham object and set its attributes
				SanPham sanPham = new SanPham();

				sanPham.setSanPhamID(rs.getString("SanPhamID"));
				sanPham.setTenSanPham(rs.getString("TenSanPham"));
				sanPham.setSoLuong(rs.getInt("SoLuong"));

				// Add the SanPham object to the list
				listSP.add(sanPham);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return (ArrayList<SanPham>) listSP;
	}

	public ArrayList<SanPham> getSanPhamChuaHoanThanh() {
		ArrayList<SanPham> listSP = new ArrayList<>();

		try {
			String sql = "SELECT SanPhamID, TenSanPham, SoLuong FROM SanPham WHERE tinhTrang = 0";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setSanPhamID(rs.getString("SanPhamID"));
				sanPham.setTenSanPham(rs.getString("TenSanPham"));
				sanPham.setSoLuong(rs.getInt("SoLuong"));

				listSP.add(sanPham);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}

		return listSP;
	}

	public ArrayList<SanPham> getSanPhamDaHoanThanh() {
		ArrayList<SanPham> listSP = new ArrayList<>();

		try {
			String sql = "SELECT SanPhamID, TenSanPham, SoLuong " + "FROM SanPham " + "WHERE tinhTrang = 1";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setSanPhamID(rs.getString("SanPhamID"));
				sanPham.setTenSanPham(rs.getString("TenSanPham"));
				sanPham.setSoLuong(rs.getInt("SoLuong"));

				listSP.add(sanPham);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listSP;
	}

	public ArrayList<SanPham> getSanPhamTheoLoai(String loaiSanPham) {
		try {
			listSP = new ArrayList<>();
			String sql = "SELECT SanPhamID, TenSanPham, SoLuong " + "FROM SanPham " + "WHERE LoaiSanPham = ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, loaiSanPham);
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					SanPham sanPham = new SanPham();
					sanPham.setSanPhamID(rs.getString("SanPhamID"));
					sanPham.setTenSanPham(rs.getString("TenSanPham"));
					sanPham.setSoLuong(rs.getInt("SoLuong"));
					listSP.add(sanPham);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return new ArrayList<>(listSP);
	}
	
	public ArrayList<SanPham> getDSSanPhamDaPhanCongDoan(String loaiSP) {
		ArrayList<SanPham>ds=new ArrayList<SanPham>();
		String sql="select sp.SanPhamID, count(*) from SanPham as sp join CongDoan as cd on sp.SanPhamID=cd.SanPhamID where tinhTrang=0 and sp.LoaiSanPham=?\r\n"
				+ "group by sp.SanPhamID";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, loaiSP);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String maSp=rs.getString(1);
				SanPham sp=getSanPhamTheoID(maSp);
				ds.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public SanPham getSanPhamTheoID(String masp) {
		SanPham sp = null;
		String sql = "select*from SanPham where SanPhamID=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, masp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String kieuDang = rs.getString(3);
				KieuDang kd = KieuDang.fromString(kieuDang);
//				if (kieuDang.equals("Body"))
//					kd = KieuDang.BODY;
//				if (kieuDang.equals("Tiêu chuẩn"))
//					kd = KieuDang.TIEUCHUAN;
//				if (kieuDang.equals("Form rộng"))
//					kd = KieuDang.FORMRONG;
				String kt = rs.getString(4);
				String loaiSP = rs.getString(5);
				LoaiSanPham lsp = LoaiSanPham.fromString(loaiSP);
//				if (loaiSP.equals("Áo sơ mi"))
//					lsp = LoaiSanPham.AOSOMI;
//				if (loaiSP.equals("Đồ thể thao"))
//					lsp = LoaiSanPham.DOTHETHAO;
//				if (loaiSP.equals("Váy liền thân"))
//					lsp = LoaiSanPham.VAYLIENTHAN;
//				if (loaiSP.equals("Áo khoác"))
//					lsp = LoaiSanPham.AOKHOAC;
//				if (loaiSP.equals("Quần tây"))
//					lsp = LoaiSanPham.QUANTAY;
//				if (loaiSP.equals("Quần jean"))
//					lsp = LoaiSanPham.QUANJEAN;
				int sl = rs.getInt(6);
				String ha = rs.getString(7);
				int tinhTrang=rs.getInt(8);
				sp = new SanPham(ma, ten, kd, kt, lsp, sl, ha,tinhTrang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}
	
	public ArrayList<SanPham> getDSSanPhamTheoLoai(LoaiSanPham s) {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		String sql = "select* from SanPham where LoaiSanPham=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, s.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String ten = rs.getString(2);
				String kieuDang = rs.getString(3);
				if (!kieuDang.equals("")) {
					KieuDang kd = null;
					if (kieuDang.equals("Body"))
						kd = KieuDang.BODY;
					if (kieuDang.equals("Tiêu chuẩn"))
						kd = KieuDang.TIEUCHUAN;
					if (kieuDang.equals("Form rộng"))
						kd = KieuDang.FORMRONG;
					String kichThuoc = rs.getString(4);
					String loaiSP = rs.getString(5);
					LoaiSanPham lsp = null;
					if (loaiSP.equals("Áo sơ mi"))
						lsp = LoaiSanPham.AOSOMI;
					if (loaiSP.equals("Đồ thể thao"))
						lsp = LoaiSanPham.DOTHETHAO;
					if (loaiSP.equals("Váy liền thân"))
						lsp = LoaiSanPham.VAYLIENTHAN;
					if (loaiSP.equals("Áo khoác"))
						lsp = LoaiSanPham.AOKHOAC;
					if (loaiSP.equals("Quần tây"))
						lsp = LoaiSanPham.QUANTAY;
					if (loaiSP.equals("Quần jean"))
						lsp = LoaiSanPham.QUANJEAN;
					int sl = rs.getInt(6);
					String ha = rs.getString(7);
					int tinhTrang=rs.getInt(8);
					SanPham sp = new SanPham(id, ten, kd, kichThuoc, lsp, sl, ha, tinhTrang);
					ds.add(sp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public void capNhatSanPham(String ma, String ten, LoaiSanPham loai, KieuDang kd, String kichThuoc, int sl, int tinhTrang) {
		String sql = "update SanPham set TenSanPham=?,KieuDang=?,KichThuoc=?,LoaiSanPham=?,SoLuong=? ,tinhTrang=? where SanPhamID=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			statement.setString(2, kd.toString());
			statement.setString(3, kichThuoc);
			statement.setString(4, loai.toString());
			statement.setInt(5, sl);
			statement.setInt(6, tinhTrang);
			statement.setString(7, ma);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham>ds=new ArrayList<SanPham>();
		String sql="select* from SanPham";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String sanPhamID=rs.getString(1);
				SanPham sp=getSanPhamTheoID(sanPhamID);
				ds.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public int getSoLuongSPHoanThanh(String macd) {
		int sl=0;
		
			
			String sql="select sum(pccn.SoLuongHoanThanh) from BangPhanCongCongNhan as pccn join BangPhanCongBoPhan as pcbp on pccn.PhanCongBoPhanID=pcbp.PhanCongID\r\n"
					+ "where pcbp.CongDoanID=?";
			try {
				PreparedStatement statement =con.prepareStatement(sql);
				statement.setString(1, macd);
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
	
	public int getSoLuongHoanThanh(String maSanPham) {
		int soLuongHoanThanh = 0;
		int mucUuTien = getUuTienLasted(maSanPham);
		try {
			String sql = "SELECT TOP 1 SUM(pccn.SoLuongHoanThanh) AS SoLuong " + "FROM SanPham AS sp "
					+ "INNER JOIN CongDoan AS cd ON sp.SanPhamID = cd.SanPhamID "
					+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.CongDoanID = cd.CongDoanID "
					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.PhanCongBoPhanID = pcbp.PhanCongID "
					+ "WHERE sp.SanPhamID = ? AND cd.MucUuTien = ? " + "GROUP BY sp.SanPhamID, cd.MucUuTien "
					+ "ORDER BY cd.MucUuTien DESC";

			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, maSanPham);
				statement.setInt(2, mucUuTien);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					soLuongHoanThanh += rs.getInt("SoLuong");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return soLuongHoanThanh;
	}
	
	public int getUuTienLasted(String maSanPham) {
		int muuUuTien = -1;
		try {
			String sql = "select top 1 MucUuTien\r\n" + "from sanpham as sp\r\n"
					+ "inner join CongDoan as cd on cd.SanPhamID = sp.SanPhamID\r\n" + "where sp.SanPhamID like ?\r\n"
					+ "order by MucUuTien desc";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, maSanPham);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					muuUuTien = rs.getInt("MucUuTien");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return muuUuTien;
	}
	
	public void capNhatTrangThai(String maSP,int trangThai) {
		String sql="update SanPham set tinhTrang=? where SanPhamID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1, trangThai);
			statement.setString(2, maSP);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<SanPham> getDSSanPhamThucHienTheoNgay(LocalDate ngay) {
		ArrayList<SanPham> ds=new ArrayList<SanPham>();
		String sql="SELECT sp.SanPhamID ,count(cd.CongDoanID) FROM  BangPhanCongBoPhan as pcbp INNER JOIN CongDoan as cd ON pcbp.CongDoanID = cd.CongDoanID INNER JOIN SanPham as sp ON cd.SanPhamID = sp.SanPhamID\r\n"
				+ "where pcbp.Ngay=?\r\n"
				+ "group by sp.SanPhamID";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(ngay));
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String masp=rs.getString(1);
				SanPham sp=getSanPhamTheoID(masp);
				ds.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public ArrayList<SanPham> getDSSanPhamHoanThanh() {
		ArrayList<SanPham>ds=new ArrayList<SanPham>();
		String sql="select * from SanPham where tinhTrang = 1";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String masp=rs.getString(1);
				SanPham sp=getSanPhamTheoID(masp);
				ds.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public int getSoLuongSPHoanThanh(String macd, LocalDate ngay) {
		int sl=0;
		
			
			String sql="select sum(pccn.SoLuongHoanThanh) from BangPhanCongCongNhan as pccn join BangPhanCongBoPhan as pcbp on pccn.PhanCongBoPhanID=pcbp.PhanCongID\r\n"
					+ "where pcbp.CongDoanID=? and pcbp.Ngay=?";
			try {
				PreparedStatement statement =con.prepareStatement(sql);
				statement.setString(1, macd);
				statement.setDate(2, Date.valueOf(ngay));
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
	
	public int  getSLHoanThanhSP(String macd, LocalDate ngay) {
		int sl=0;
		String sql="select sum(pccn.SoLuongHoanThanh) from BangPhanCongCongNhan as pccn join BangPhanCongBoPhan as pcbp on pccn.PhanCongBoPhanID=pcbp.PhanCongID\r\n"
				+ "where pcbp.CongDoanID=? and pcbp.Ngay<=?";
		try {
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setString(1, macd);
			statement.setDate(2, Date.valueOf(ngay));
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
	
	


}
