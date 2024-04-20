package entities;

import java.time.LocalDate;

public class HopDongLaoDong {
	private String hopDongID;
	private Nguoi nguoi;
	private String tenHopDong;
	private LoaiHopDong loaiHopDong;
	private LocalDate ngayKy;
	private LocalDate ngayHetHan;
	public String getHopDongID() {
		return hopDongID;
	}
	public void setHopDongID(String hopDongID) {
		this.hopDongID = hopDongID;
	}
	public Nguoi getNguoi() {
		return nguoi;
	}
	public void setNguoi(Nguoi nguoi) {
		this.nguoi = nguoi;
	}
	public String getTenHopDong() {
		return tenHopDong;
	}
	public void setTenHopDong(String tenHopDong) {
		this.tenHopDong = tenHopDong;
	}
	public LoaiHopDong getLoaiHopDong() {
		return loaiHopDong;
	}
	public void setLoaiHopDong(LoaiHopDong loaiHopDong) {
		this.loaiHopDong = loaiHopDong;
	}
	public LocalDate getNgayKy() {
		return ngayKy;
	}
	public void setNgayKy(LocalDate ngayKy) {
		this.ngayKy = ngayKy;
	}
	public LocalDate getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(LocalDate ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	public HopDongLaoDong(String hopDongID, Nguoi nguoi, String tenHopDong, LoaiHopDong loaiHopDong, LocalDate ngayKy,
			LocalDate ngayHetHan) {
		super();
		this.hopDongID = hopDongID;
		this.nguoi = nguoi;
		this.tenHopDong = tenHopDong;
		this.loaiHopDong = loaiHopDong;
		this.ngayKy = ngayKy;
		this.ngayHetHan = ngayHetHan;
	}
	public HopDongLaoDong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
