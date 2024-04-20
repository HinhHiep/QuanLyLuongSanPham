package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BangPhanCongCongNhan {
	private String phanCongID;
	private CongNhan congNhan;
	private BangPhanCongBoPhan bangPhanCongBoPhan;
	private LocalDate ngay;
	private int soLuongPhanCong;
	private int soLuongHoanThanh;
	public BangPhanCongCongNhan() {
		super();
	}
	public BangPhanCongCongNhan(String phanCongID, CongNhan congNhan, BangPhanCongBoPhan bangPhanCongBoPhan,
			int soLuongPhanCong, int soLuongHoanThanh) {
		super();
		this.phanCongID = phanCongID;
		this.congNhan = congNhan;
		this.bangPhanCongBoPhan = bangPhanCongBoPhan;
		this.soLuongPhanCong = soLuongPhanCong;
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	public BangPhanCongCongNhan( CongNhan congNhan, BangPhanCongBoPhan bangPhanCongBoPhan,
			int soLuongPhanCong) {
		super();
		this.phanCongID=null;
		this.congNhan = congNhan;
		this.bangPhanCongBoPhan = bangPhanCongBoPhan;
		this.soLuongPhanCong = soLuongPhanCong;
		this.soLuongHoanThanh=0;
	}
	
	public LocalDate getNgay() {
		return ngay;
	}
	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}
	public String getPhanCongID() {
		return phanCongID;
	}
	public void setPhanCongID(String phanCongID) {
		this.phanCongID = phanCongID;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	public BangPhanCongBoPhan getBangPhanCongBoPhan() {
		return bangPhanCongBoPhan;
	}
	public void setBangPhanCongBoPhan(BangPhanCongBoPhan bangPhanCongBoPhan) {
		this.bangPhanCongBoPhan = bangPhanCongBoPhan;
	}
	public int getSoLuongPhanCong() {
		return soLuongPhanCong;
	}
	public void setSoLuongPhanCong(int soLuongPhanCong) {
		this.soLuongPhanCong = soLuongPhanCong;
	}
	public int getSoLuongHoanThanh() {
		return soLuongHoanThanh;
	}
	public void setSoLuongHoanThanh(int soLuongHoanThanh) {
		this.soLuongHoanThanh = soLuongHoanThanh;
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
	
	public BangPhanCongCongNhan(String phanCongID, CongNhan congNhan, BangPhanCongBoPhan bangPhanCongBoPhan,
			LocalDate ngay, int soLuongPhanCong, int soLuongHoanThanh) {
		super();
		this.phanCongID = phanCongID;
		this.congNhan = congNhan;
		this.bangPhanCongBoPhan = bangPhanCongBoPhan;
		this.ngay = ngay;
		this.soLuongPhanCong = soLuongPhanCong;
		this.soLuongHoanThanh = soLuongHoanThanh;
	}
	
}
