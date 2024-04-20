package entities;

import java.time.LocalDate;

public class Nguoi {
	protected String nguoiID;
	protected String hoTen;
	protected LocalDate ngaySinh;
	protected boolean gioiTinh;
	protected String soCMND;
	protected String diaChi;
	protected String email;
	protected String soDienThoai;
	protected boolean trangThai;
	public String getNguoiID() {
		return nguoiID;
	}
	public void setNguoiID(String nguoiID) {
		this.nguoiID = nguoiID;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	public Nguoi(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai) {
		super();
		this.nguoiID = nguoiID;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soCMND = soCMND;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.trangThai = trangThai;
	}
	public Nguoi(String nguoiID) {
		super();
		this.nguoiID = nguoiID;
	}
	
	public Nguoi(String nguoiID, String hoTen) {
		super();
		this.nguoiID = nguoiID;
		this.hoTen = hoTen;
	}
	
	public Nguoi() {
		super();
	}
	@Override
	public String toString() {
		return "Nguoi [nguoiID=" + nguoiID + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", soCMND=" + soCMND + ", diaChi=" + diaChi + ", email=" + email + ", soDienThoai=" + soDienThoai
				+ ", trangThai=" + trangThai + "]";
	}
	
	
}
