package entities;

public class PhuCap {
	private String phuCapID;
	private String tenPhuCap;
	private double donGia;
	public String getPhuCapID() {
		return phuCapID;
	}
	public void setPhuCapID(String phuCapID) {
		this.phuCapID = phuCapID;
	}
	public String getTenPhuCap() {
		return tenPhuCap;
	}
	public void setTenPhuCap(String tenPhuCap) {
		this.tenPhuCap = tenPhuCap;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public PhuCap(String phuCapID, String tenPhuCap, double donGia) {
		super();
		this.phuCapID = phuCapID;
		this.tenPhuCap = tenPhuCap;
		this.donGia = donGia;
	}
	public PhuCap() {
		super();
	}
	
}

