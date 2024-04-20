package entities;

import java.time.LocalDate;

import enums.CapBac;

public class NhanVien extends Nguoi{
	private PhongBan phongBan;
	private ChucVu chucVu;
	private CapBac capBac;
	private int soNamKinhNghiem;
	private double heSoLuong;
	private double luongCoBan;
	public PhongBan getPhongBan() {
		return phongBan;
	}
	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}
	public ChucVu getChucVu() {
		return chucVu;
	}
	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
	public CapBac getCapBac() {
		return capBac;
	}
	public void setCapBac(CapBac capBac) {
		this.capBac = capBac;
	}
	public int getSoNamKinhNghiem() {
		return soNamKinhNghiem;
	}
	public void setSoNamKinhNghiem(int soNamKinhNghiem) {
		this.soNamKinhNghiem = soNamKinhNghiem;
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
	
	public NhanVien(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai, PhongBan phongBan, ChucVu chucVu, CapBac capBac,
			int soNamKinhNghiem, double heSoLuong, double luongCoBan) {
		super(nguoiID, hoTen, ngaySinh, gioiTinh, soCMND, diaChi, email, soDienThoai, trangThai);
		this.phongBan = phongBan;
		this.chucVu = chucVu;
		this.capBac = capBac;
		this.soNamKinhNghiem = soNamKinhNghiem;
		this.heSoLuong = heSoLuong;
		this.luongCoBan = luongCoBan;
	}
	
	
	public NhanVien(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai) {
		super(nguoiID, hoTen, ngaySinh, gioiTinh, soCMND, diaChi, email, soDienThoai, trangThai);
	}
	public NhanVien(String nguoiID, PhongBan phongBan, ChucVu chucVu, CapBac capBac, int soNamKinhNghiem,
			double heSoLuong, double luongCoBan) {
		super(nguoiID);
		this.phongBan = phongBan;
		this.chucVu = chucVu;
		this.capBac = capBac;
		this.soNamKinhNghiem = soNamKinhNghiem;
		this.heSoLuong = heSoLuong;
		this.luongCoBan = luongCoBan;
	}
	
	
	
	public NhanVien(String nguoiID) {
		super(nguoiID);
	}
	@Override
	public String toString() {
		return super.toString() + "NhanVien [phongBan=" + phongBan + ", chucVu=" + chucVu + ", capBac=" + capBac + ", soNamKinhNghiem="
				+ soNamKinhNghiem + ", heSoLuong=" + heSoLuong + ", luongCoBan=" + luongCoBan + "]";
	}
	
}
