package entities;

import java.time.LocalDate;

public class BangChamCong {
	private String chamCongID;
	private Nguoi nguoi;
	private LocalDate ngay;
	private String caLamViec;
	private String tinhTrang;
	public String getChamCongID() {
		return chamCongID;
	}
	public void setChamCongID(String chamCongID) {
		this.chamCongID = chamCongID;
	}
	public Nguoi getNguoi() {
		return nguoi;
	}
	public void setNguoi(Nguoi nguoi) {
		this.nguoi = nguoi;
	}
	public LocalDate getNgay() {
		return ngay;
	}
	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}
	public String getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}

	
	
	
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public BangChamCong(String chamCongID, Nguoi nguoi, LocalDate ngay, String caLamViec, String tinhTrang) {
		super();
		this.chamCongID = chamCongID;
		this.nguoi = nguoi;
		this.ngay = ngay;
		this.caLamViec = caLamViec;
		this.tinhTrang = tinhTrang;
	}
	public BangChamCong() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BangChamCong [chamCongID=" + chamCongID + ", nguoi=" + nguoi + ", ngay=" + ngay + ", caLamViec="
				+ caLamViec + ", tinhTrang=" +  "]";
	}
	
	
	
	
}
