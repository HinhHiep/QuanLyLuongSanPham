package entities;
//checked
public class NguyenLieu_SanPham {
	private SanPham sanPham;
	private NguyenLieu nguyenLieu;
	private int soLuong;
	public NguyenLieu_SanPham() {
		super();
	}
	public NguyenLieu_SanPham(SanPham sanPham, NguyenLieu nguyenLieu, int soLuong) {
		super();
		this.sanPham = sanPham;
		this.nguyenLieu = nguyenLieu;
		this.soLuong = soLuong;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public NguyenLieu getNguyenLieu() {
		return nguyenLieu;
	}
	public void setNguyenLieu(NguyenLieu nguyenLieu) {
		this.nguyenLieu = nguyenLieu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
