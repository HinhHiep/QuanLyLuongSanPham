package entities;

import java.time.LocalDateTime;

public class PhanCong_BoPhan {
	private String phanCongID;
	private int soLuong;
	private LocalDateTime ngay;
	private String congDoanID;
	private String boPhanID;

	public PhanCong_BoPhan(String phanCongID, int soLuong, LocalDateTime ngay, String congDoanID, String boPhanID) {
		this.phanCongID = phanCongID;
		this.soLuong = soLuong;
		this.ngay = ngay;
		this.congDoanID = congDoanID;
		this.boPhanID = boPhanID;
	}

	public String getPhanCongID() {
		return phanCongID;
	}

	public void setPhanCongID(String phanCongID) {
		this.phanCongID = phanCongID;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public LocalDateTime getNgay() {
		return ngay;
	}

	public void setNgay(LocalDateTime ngay) {
		this.ngay = ngay;
	}

	public String getCongDoanID() {
		return congDoanID;
	}

	public void setCongDoanID(String congDoanID) {
		this.congDoanID = congDoanID;
	}

	public String getBoPhanID() {
		return boPhanID;
	}

	public void setBoPhanID(String boPhanID) {
		this.boPhanID = boPhanID;
	}

	@Override
	public String toString() {
		return "PhanCong_BoPhan{" + "phanCongID='" + phanCongID + '\'' + ", soLuong=" + soLuong + ", ngay=" + ngay
				+ ", congDoanID='" + congDoanID + '\'' + ", boPhanID='" + boPhanID + '\'' + '}';
	}
}
