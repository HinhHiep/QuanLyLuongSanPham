package entities;

import java.util.Date;

public class PhanCong {
	private String phanCongID;
	private String sanPhamID;
	private String tenSanPham;
	private String congDoanID;
	private String tenCongDoan;
	private String congNhanID;
	private String hoTen;
	private Date ngay;
	private int soLuongPhanCong;

	public PhanCong(String phanCongID, String sanPhamID, String tenSanPham, String congDoanID, String tenCongDoan,
			String congNhanID, String hoTen, Date ngay, int soLuongPhanCong) {
		this.phanCongID = phanCongID;
		this.sanPhamID = sanPhamID;
		this.tenSanPham = tenSanPham;
		this.congDoanID = congDoanID;
		this.tenCongDoan = tenCongDoan;
		this.congNhanID = congNhanID;
		this.hoTen = hoTen;
		this.ngay = ngay;
		this.soLuongPhanCong = soLuongPhanCong;
	}

	// Getter và Setter cho các thuộc tính

	public String getPhanCongID() {
		return phanCongID;
	}

	public void setPhanCongID(String phanCongID) {
		this.phanCongID = phanCongID;
	}

	public String getSanPhamID() {
		return sanPhamID;
	}

	public void setSanPhamID(String sanPhamID) {
		this.sanPhamID = sanPhamID;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
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

	public String getCongNhanID() {
		return congNhanID;
	}

	public void setCongNhanID(String congNhanID) {
		this.congNhanID = congNhanID;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public int getSoLuongPhanCong() {
		return soLuongPhanCong;
	}

	public void setSoLuongPhanCong(int soLuongPhanCong) {
		this.soLuongPhanCong = soLuongPhanCong;
	}

	@Override
	public String toString() {
		return "PhanCong{" + "phanCongID='" + phanCongID + '\'' + ", sanPhamID='" + sanPhamID + '\'' + ", tenSanPham='"
				+ tenSanPham + '\'' + ", congDoanID='" + congDoanID + '\'' + ", tenCongDoan='" + tenCongDoan + '\''
				+ ", congNhanID='" + congNhanID + '\'' + ", hoTen='" + hoTen + '\'' + ", ngay=" + ngay
				+ ", soLuongPhanCong=" + soLuongPhanCong + '}';
	}
}
