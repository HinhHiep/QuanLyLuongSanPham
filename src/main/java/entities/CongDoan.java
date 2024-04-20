package entities;

public class CongDoan {
	private String congDoanID;
	private String tenCongDoan;
	private SanPham sanPham;
	private int mucUuTien;
	private String mucYeuCauKyThuat;
	private double donGia;
//	public CongDoan(String congDoanID, String tenCongDoan, SanPham sanPham, int mucUuTien, String mucYeuCauKyThuat,
//			double donGia) {
//		this.congDoanID = congDoanID;
//		this.tenCongDoan = tenCongDoan;
//		this.sanPham = sanPham;
//		this.mucUuTien = mucUuTien;
//		this.mucYeuCauKyThuat = mucYeuCauKyThuat;
//		this.donGia = donGia;
//	}
//
//	public CongDoan() {
//		super();
//	}

//	@Override
//	public String toString() {
//		return "CongDoan [congDoanID=" + congDoanID + ", tenCongDoan=" + tenCongDoan + ", sanPham=" + sanPham
//				+ ", mucUuTien=" + mucUuTien + ", mucYeuCauKyThuat=" + mucYeuCauKyThuat + ", donGia=" + donGia + "]";
//	}
//
//	public String getCongDoanID() {
//		return congDoanID;
//	}
//
//	public void setCongDoanID(String congDoanID) {
//		this.congDoanID = congDoanID;
//	}
//
//	public String getTenCongDoan() {
//		return tenCongDoan;
//	}
//
//	public void setTenCongDoan(String tenCongDoan) {
//		this.tenCongDoan = tenCongDoan;
//	}
//
//	public SanPham getSanPham() {
//		return sanPham;
//	}
//
//	public void setSanPham(SanPham sanPham) {
//		this.sanPham = sanPham;
//	}
//
//	public int getMucUuTien() {
//		return mucUuTien;
//	}
//
//	public void setMucUuTien(int mucUuTien) {
//		this.mucUuTien = mucUuTien;
//	}
//
//	public char getMucYeuCauKyThuat() {
//		return mucYeuCauKyThuat;
//	}
//
//	public void setMucYeuCauKyThuat(char mucYeuCauKyThuat) {
//		this.mucYeuCauKyThuat = mucYeuCauKyThuat;
//	}
//
//	public double getDonGia() {
//		return donGia;
//	}
//
//	public void setDonGia(double donGia) {
//		this.donGia = donGia;
//	}
//
//	private SanPham sanPham;
//	public CongDoan() {
//		super();
//	}
	public CongDoan(String congDoanID, String tenCongDoan, int mucUuTien, String mucYeuCauKyThuat, double donGia,
			SanPham sanPham) {
		super();
		this.congDoanID = congDoanID;
		this.tenCongDoan = tenCongDoan;
		this.mucUuTien = mucUuTien;
		this.mucYeuCauKyThuat = mucYeuCauKyThuat;
		this.donGia = donGia;
		this.sanPham = sanPham;
	}
	public String getCongDoanID() {
		return congDoanID;
	}
	public void setCongDoanID(String congDoanID) {
		this.congDoanID = congDoanID;
	}
	public String getTenCongDoan() {
		return tenCongDoan;
	}
	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}
	public int getMucUuTien() {
		return mucUuTien;
	}
	public void setMucUuTien(int mucUuTien) {
		this.mucUuTien = mucUuTien;
	}
	public String getMucYeuCauKyThuat() {
		return mucYeuCauKyThuat;
	}
	public void setMucYeuCauKyThuat(String mucYeuCauKyThuat) {
		this.mucYeuCauKyThuat = mucYeuCauKyThuat;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	

}
