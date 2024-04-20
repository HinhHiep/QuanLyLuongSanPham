package dao;
//Check_L1
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entities.NguyenLieu;
import entities.NguyenLieu_SanPham;
import entities.SanPham;
import enums.LoaiSanPham;

public class NguyenLieuDAO {
	private Connection con;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();

	public NguyenLieuDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public String getTenNguyenLieu(String sanPhamID) {
		String tenNguyenLieu = "";
		try {
			String sql = "select nl.TenNguyenLieu\r\n" + "from NguyenLieu as nl\r\n"
					+ "inner join NguyenLieuSanPham as nlsp on nlsp.NguyenLieuID = nl.NguyenLieuID\r\n"
					+ "inner join SanPham as sp on sp.SanPhamID = nlsp.SanPhamID\r\n" + "where sp.SanPhamID like ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, sanPhamID); // Di chuyển dòng này lên đầu
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tenNguyenLieu = rs.getString("TenNguyenLieu");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
		}
		return tenNguyenLieu;
	}
//-----------------------------------------------
	public NguyenLieu getNguyenLieuTheoID(String nguyenLieuID) {
		NguyenLieu nl=new NguyenLieu();
		String sql="select*from NguyenLieu where NguyenLieuID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, nguyenLieuID);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String maNL=rs.getString(1);
				String tenNL=rs.getString(2);
				String mauSac=rs.getString(3);
				String hoaVan=rs.getString(4);
				String chatLieu=rs.getString(5);
				int slTon=rs.getInt(6);
				String donViTinh=rs.getString(7);
				nl=new NguyenLieu(maNL, tenNL, mauSac, hoaVan, chatLieu, slTon, donViTinh);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nl;
	}

	public ArrayList<NguyenLieu_SanPham> getDSNguyenLieu(String maSP) {
		ArrayList<NguyenLieu_SanPham>ds=new ArrayList<NguyenLieu_SanPham>();
		String sql="select*from NguyenLieuSanPham where SanPhamID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String masp=rs.getString(1);
				SanPham sp=sanPhamDAO.getSanPhamTheoID(masp);
				String maNL=rs.getString(2);
				NguyenLieu nl=getNguyenLieuTheoID(maNL);
				int sl=rs.getInt(3);
				NguyenLieu_SanPham nlsp=new NguyenLieu_SanPham(sp, nl, sl);
				ds.add(nlsp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public NguyenLieu_SanPham getNLSP(String maSP,String maNL) {
		NguyenLieu_SanPham nlsp=new NguyenLieu_SanPham();
		String sql="select*from NguyenLieuSanPham where SanPhamID=? and NguyenLieuID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maSP);
			statement.setString(2, maNL);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				SanPham sp=sanPhamDAO.getSanPhamTheoID(maSP);
				NguyenLieu nl=getNguyenLieuTheoID(maNL);
				int sl=rs.getInt(3);
				nlsp=new NguyenLieu_SanPham(sp, nl, sl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nlsp;
	}
	public ArrayList<NguyenLieu> getAllNguyenLieu() {
		ArrayList<NguyenLieu>ds=new ArrayList<NguyenLieu>();
		String sql="select*from NguyenLieu";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String ma=rs.getString(1);
				NguyenLieu nl=getNguyenLieuTheoID(ma);
				ds.add(nl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public void addNguyenLieuSanPham(String maNguyenLieu,String maSanPham,int soLuong) {
		String sql="insert into NguyenLieuSanPham values (?,?,?)";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maSanPham);
			statement.setString(2, maNguyenLieu);
			statement.setInt(3, soLuong);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateNguyenLieuSanPham(String maNguyenLieu,String maSanPham,int soLuong) {
		String sql="update NguyenLieuSanPham set SoLuong=? where NguyenLieuID=? and SanPhamID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1, soLuong);
			statement.setString(2, maNguyenLieu);
			statement.setString(3, maSanPham);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteNguyenLieuSanPham(String maNguyenLieu,String maSanPham) {
		String sql="delete from NguyenLieuSanPham where NguyenLieuID=? and SanPhamID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maNguyenLieu);
			statement.setString(2, maSanPham);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int tinhTongSLNGuyenLieuDaPhanCong(String maNguyenLieu) {
		int sl=0;
		String sql="select sum(SoLuong) from NguyenLieuSanPham where NguyenLieuID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, maNguyenLieu);
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
	public ArrayList<NguyenLieu_SanPham> getALLNguyenLieuSanPham() {
		ArrayList<NguyenLieu_SanPham>ds=new ArrayList<NguyenLieu_SanPham>();
		String sql="select*from NguyenLieuSanPham";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				String masp=rs.getString(1);
				String manl=rs.getString(2);
				NguyenLieu_SanPham nlsp=getNLSP(masp, manl);
				ds.add(nlsp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<NguyenLieu_SanPham> getNguyenLieuSanPhamTheoLoaiSP(LoaiSanPham loaisp) {
		ArrayList<NguyenLieu_SanPham>ds=new ArrayList<NguyenLieu_SanPham>();
		String sql="select*from NguyenLieuSanPham as nlsp join SanPham as sp on nlsp.SanPhamID=sp.SanPhamID where LoaiSanPham=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, loaisp.toString());
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				String masp=rs.getString(1);
				String manl=rs.getString(2);
				NguyenLieu_SanPham nlsp=getNLSP(masp, manl);
				ds.add(nlsp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
}
