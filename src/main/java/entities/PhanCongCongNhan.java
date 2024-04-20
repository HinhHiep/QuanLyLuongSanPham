package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PhanCongCongNhan {
	private String phanCongID;
	private String boPhanID;
	private String congNhanID;
	private int soLuongPhanCong;
	private String soLuongHoanThanh;
	private LocalDateTime ngay;
	private String caLamViec;
	private String phanCongBPID;

	public String getPhanCongID() {
		return phanCongID;
	}

	public String getPhanCongBPID() {
		return phanCongBPID;
	}

	public void setPhanCongBPID(String phanCongBPID) {
		this.phanCongBPID = phanCongBPID;
	}

	public void setPhanCongID(String phanCongID) {
		this.phanCongID = phanCongID;
	}

	public String getBoPhanID() {
		return boPhanID;
	}

	public void setBoPhanID(String boPhanID) {
		this.boPhanID = boPhanID;
	}

	public String getCongNhanID() {
		return congNhanID;
	}

	public void setCongNhanID(String congNhanID) {
		this.congNhanID = congNhanID;
	}

	public int getSoLuongPhanCong() {
		return soLuongPhanCong;
	}

	public void setSoLuongPhanCong(int soLuongPhanCong) {
		this.soLuongPhanCong = soLuongPhanCong;
	}

	public String getSoLuongHoanThanh() {
		return soLuongHoanThanh;
	}

	public void setSoLuongHoanThanh(String soLuongHoanThanh) {
		this.soLuongHoanThanh = soLuongHoanThanh;
	}

	public LocalDateTime getNgay() {
		return ngay;
	}

	public void setNgay(LocalDateTime ngay) {
		this.ngay = ngay;
	}

	public String getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}

	public PhanCongCongNhan(String phanCongID, String boPhanID, String congNhanID, String phanCongBPID,
			int soLuongPhanCong, String soLuongHoanThanh, LocalDateTime ngay, String caLamViec) {
		super();
		this.phanCongID = phanCongID;
		this.boPhanID = boPhanID;
		this.congNhanID = congNhanID;
		this.soLuongPhanCong = soLuongPhanCong;
		this.soLuongHoanThanh = soLuongHoanThanh;
		this.ngay = ngay;
		this.caLamViec = caLamViec;
		this.phanCongBPID = phanCongBPID;
	}

	public Timestamp getNgayAsTimestamp() {
		return Timestamp.valueOf(ngay);
	}

	// Chuyển đổi từ Timestamp sang LocalDateTime
	public void setNgayFromTimestamp(Timestamp timestamp) {
		ngay = timestamp.toLocalDateTime();
	}

	public void convertPhanCongID(String autoIncrement) {
		LocalDateTime now = LocalDateTime.now();
		String nam = String.valueOf(now.getYear()).substring(2);
		String thang = String.format("%02d", now.getMonthValue());
		String ngay = String.format("%02d", now.getDayOfMonth());

		// Tạo mã BoPhanID và CongNhanID tạm thời (bạn có thể thay đổi logic tạo mã theo
		// yêu cầu thực tế)
		String maBoPhanID = "MAY001";
		String maCongNhanID = "231MAY001";

		// Tạo phần tự động tăng cho PhanCongID (tạm thời là số 1, bạn có thể thay đổi

		// Tạo chuỗi PhanCongID
		String phanCongID = nam + thang + ngay + maBoPhanID + maCongNhanID + autoIncrement;
		this.setPhanCongID(phanCongID);
	}
}
