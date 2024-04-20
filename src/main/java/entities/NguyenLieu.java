package entities;

public class NguyenLieu {
	private String nguyenLieuID;
	private String tenNguyenLieu;
	private String mauSac;
	private String hoaVan;
	private String chatLieu;
	private int soLuongTon;
	private String donViTinh;
	public NguyenLieu() {
		super();
	}
	public NguyenLieu(String nguyenLieuID, String tenNguyenLieu, String mauSac, String hoaVan, String chatLieu,
			int soLuongTon, String donViTinh) {
		super();
		this.nguyenLieuID = nguyenLieuID;
		this.tenNguyenLieu = tenNguyenLieu;
		this.mauSac = mauSac;
		this.hoaVan = hoaVan;
		this.chatLieu = chatLieu;
		this.soLuongTon = soLuongTon;
		this.donViTinh = donViTinh;
	}
	public String getNguyenLieuID() {
		return nguyenLieuID;
	}
	public void setNguyenLieuID(String nguyenLieuID) {
		this.nguyenLieuID = nguyenLieuID;
	}
	public String getTenNguyenLieu() {
		return tenNguyenLieu;
	}
	public void setTenNguyenLieu(String tenNguyenLieu) {
		this.tenNguyenLieu = tenNguyenLieu;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public String getHoaVan() {
		return hoaVan;
	}
	public void setHoaVan(String hoaVan) {
		this.hoaVan = hoaVan;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	
	
}
