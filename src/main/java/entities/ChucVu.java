package entities;

public class ChucVu {
	private String chucVuID;
	private String tenChucVu;
	public String getChucVuID() {
		return chucVuID;
	}
	public void setChucVuID(String chucVuID) {
		this.chucVuID = chucVuID;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public ChucVu(String chucVuID, String tenChucVu) {
		super();
		this.chucVuID = chucVuID;
		this.tenChucVu = tenChucVu;
	}
	public ChucVu(String chucVuID) {
		super();
		this.chucVuID = chucVuID;
	}
	public ChucVu() {
		super();
	}
	@Override
	public String toString() {
		return "ChucVu [chucVuID=" + chucVuID + ", tenChucVu=" + tenChucVu + "]";
	}
	
}
