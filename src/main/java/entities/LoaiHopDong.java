package entities;

public class LoaiHopDong {
	private String loaiHopDongID;
	private String tenLoai;
	private int thoiHan;
	private String noiDung;
	public String getLoaiHopDongID() {
		return loaiHopDongID;
	}
	public void setLoaiHopDongID(String loaiHopDongID) {
		this.loaiHopDongID = loaiHopDongID;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public int getThoiHan() {
		return thoiHan;
	}
	public void setThoiHan(int thoiHan) {
		this.thoiHan = thoiHan;
	}

	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	
	public LoaiHopDong(String loaiHopDongID, String tenLoai, int thoiHan, String noiDung) {
		super();
		this.loaiHopDongID = loaiHopDongID;
		this.tenLoai = tenLoai;
		this.thoiHan = thoiHan;
		this.noiDung = noiDung;
	}
	public LoaiHopDong() {
	
	}
	
}
