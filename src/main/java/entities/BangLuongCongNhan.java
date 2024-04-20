package entities;

import java.time.LocalDate;

public class BangLuongCongNhan {
	private String bangLuongID;
	private CongNhan congNhan;
	private int thang;
	private int soNgayCong;
	private int soNgayVang;
	private double heSoLamViec;
	private double luongSanPham;
	private double luongThucLanh;
	public String getBangLuongID() {
		return bangLuongID;
	}
	public void setBangLuongID(String bangLuongID) {
		this.bangLuongID = bangLuongID;
	}
	public CongNhan getCongNhan() {
		return congNhan;
	}
	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}
	
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getSoNgayCong() {
		return soNgayCong;
	}
	public void setSoNgayCong(int soNgayCong) {
		this.soNgayCong = soNgayCong;
	}
	public int getSoNgayVang() {
		return soNgayVang;
	}
	public void setSoNgayVang(int soNgayVang) {
		this.soNgayVang = soNgayVang;
	}
	public double getHeSoLamViec() {
		return heSoLamViec;
	}
	public void setHeSoLamViec(double heSoLamViec) {
		this.heSoLamViec = heSoLamViec;
	}
	public double getLuongSanPham() {
		return luongSanPham;
	}
	public void setLuongSanPham(double luongSanPham) {
		this.luongSanPham = luongSanPham;
	}
	public double getLuongThucLanh() {
		return luongThucLanh;
	}
	public void setLuongThucLanh(double luongThucLanh) {
		this.luongThucLanh = luongThucLanh;
	}
	
	public BangLuongCongNhan(String bangLuongID, CongNhan congNhan, int thang, int soNgayCong, int soNgayVang,
			double heSoLamViec, double luongSanPham, double luongThucLanh) {
		super();
		this.bangLuongID = bangLuongID;
		this.congNhan = congNhan;
		this.thang = thang;
		this.soNgayCong = soNgayCong;
		this.soNgayVang = soNgayVang;
		this.heSoLamViec = heSoLamViec;
		this.luongSanPham = luongSanPham;
		this.luongThucLanh = luongThucLanh;
	}
	public BangLuongCongNhan(String bangLuongID) {
		super();
		this.bangLuongID = bangLuongID;
	}
	
	
}
