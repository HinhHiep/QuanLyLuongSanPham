package entities;

public class PhuCapKhenThuongKhauTru {
	private String id;
	private String ten;
	private String loai;
	private double giaTien;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public PhuCapKhenThuongKhauTru() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhuCapKhenThuongKhauTru(String id, String ten, String loai, double giaTien) {
		super();
		this.id = id;
		this.ten = ten;
		this.loai = loai;
		this.giaTien = giaTien;
	}
	public PhuCapKhenThuongKhauTru(String id) {
		super();
		this.id = id;
	}
	
	
}
