package entities;

import java.time.LocalDate;

public class BangLuongNhanVien {
	private String bangLuongID;
	private NhanVien nhanVien;
	private int thang;
	private int soNgayCong;
	private int soNgayVang;
	private double heSoLuong;
	private double luongCoBan;
	private double luongThucLanh;
	public String getBangLuongID() {
		return bangLuongID;
	}
	public void setBangLuongID(String bangLuongID) {
		this.bangLuongID = bangLuongID;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
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
	public double getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	public double getLuongCoBan() {
		return luongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}
	public double getLuongThucLanh() {
		return luongThucLanh;
	}
	public void setLuongThucLanh(double luongThucLanh) {
		this.luongThucLanh = luongThucLanh;
	}
	
	public BangLuongNhanVien(String bangLuongID, NhanVien nhanVien, int thang, int soNgayCong, int soNgayVang,
			double heSoLuong, double luongCoBan, double luongThucLanh) {
		super();
		this.bangLuongID = bangLuongID;
		this.nhanVien = nhanVien;
		this.thang = thang;
		this.soNgayCong = soNgayCong;
		this.soNgayVang = soNgayVang;
		this.heSoLuong = heSoLuong;
		this.luongCoBan = luongCoBan;
		this.luongThucLanh = luongThucLanh;
	}
	public BangLuongNhanVien(String bangLuongID) {
		super();
		this.bangLuongID = bangLuongID;
	}
	public BangLuongNhanVien() {
		
	}
	
}
