package entities;

public class PhongBan {
	private String phongBanID;
	private String tenPhongBan;
	public String getPhongBanID() {
		return phongBanID;
	}
	public void setPhongBanID(String phongBanID) {
		this.phongBanID = phongBanID;
	}
	public String getTenPhongBan() {
		return tenPhongBan;
	}
	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}
	public PhongBan(String phongBanID, String tenPhongBan) {
		super();
		this.phongBanID = phongBanID;
		this.tenPhongBan = tenPhongBan;
	}
	public PhongBan(String phongBanID) {
		super();
		this.phongBanID = phongBanID;
	}
	public PhongBan() {
		super();
	}
	@Override
	public String toString() {
		return "PhongBan [phongBanID=" + phongBanID + ", tenPhongBan=" + tenPhongBan + "]";
	}
	
}
