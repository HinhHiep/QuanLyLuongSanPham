package entities;
//checked
import java.time.LocalDate;

public class BangPhanCongBoPhan {
	private String phanCongID;
	private int soLuong;
	private LocalDate ngay;
	private String CaLamViec;
	private CongDoan congDoan;
	private BoPhan boPhan;
	public BangPhanCongBoPhan() {
		super();
	}
	public BangPhanCongBoPhan(String phanCongID, int soLuong, LocalDate ngay, String caLamViec, CongDoan congDoan,
			BoPhan boPhan) {
		super();
		this.phanCongID = phanCongID;
		this.soLuong = soLuong;
		this.ngay = ngay;
		CaLamViec = caLamViec;
		this.congDoan = congDoan;
		this.boPhan = boPhan;
	}
	public String getPhanCongID() {
		return phanCongID;
	}
	public void setPhanCongID(String phanCongID) {
		this.phanCongID = phanCongID;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public LocalDate getNgay() {
		return ngay;
	}
	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}
	public String getCaLamViec() {
		return CaLamViec;
	}
	public void setCaLamViec(String caLamViec) {
		CaLamViec = caLamViec;
	}
	public CongDoan getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}
	public BoPhan getBoPhan() {
		return boPhan;
	}
	public void setBoPhan(BoPhan boPhan) {
		this.boPhan = boPhan;
	}
	public BangPhanCongBoPhan(String phanCongID, int soLuong, LocalDate ngay, CongDoan congDoan, BoPhan boPhan) {
		super();
		this.phanCongID = phanCongID;
		this.soLuong = soLuong;
		this.ngay = ngay;
		this.congDoan = congDoan;
		this.boPhan = boPhan;
	}
	
	public BangPhanCongBoPhan(String phanCongID) {
		this.phanCongID = phanCongID;
	}
	
	
	
	
}
