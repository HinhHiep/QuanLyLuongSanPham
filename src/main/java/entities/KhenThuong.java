package entities;

public class KhenThuong {
	private String khenThuongID;
	private String tenKhenThuong;
	private double donGia;
	public String getKhenThuongID() {
		return khenThuongID;
	}
	public void setKhenThuongID(String khenThuongID) {
		this.khenThuongID = khenThuongID;
	}
	public String getTenKhenThuong() {
		return tenKhenThuong;
	}
	public void setTenKhenThuong(String tenKhenThuong) {
		this.tenKhenThuong = tenKhenThuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public KhenThuong(String khenThuongID, String tenKhenThuong, double donGia) {
		super();
		this.khenThuongID = khenThuongID;
		this.tenKhenThuong = tenKhenThuong;
		this.donGia = donGia;
	}
	public KhenThuong() {
		super();
	}
	
}
