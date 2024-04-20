package entities;

public class KhauTru {
	private String khauTruID;
	private String tenKhauTru;
	private double donGia;
	public String getKhauTruID() {
		return khauTruID;
	}
	public void setKhauTruID(String khauTruID) {
		this.khauTruID = khauTruID;
	}
	public String getTenKhauTru() {
		return tenKhauTru;
	}
	public void setTenKhauTru(String tenKhauTru) {
		this.tenKhauTru = tenKhauTru;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public KhauTru() {
		
	}
	public KhauTru(String khauTruID, String tenKhauTru, double donGia) {
		super();
		this.khauTruID = khauTruID;
		this.tenKhauTru = tenKhauTru;
		this.donGia = donGia;
	}
}
