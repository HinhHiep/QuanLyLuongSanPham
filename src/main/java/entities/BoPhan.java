package entities;

public class BoPhan {
	private String boPhanID;
	private String tenBoPhan;
	public String getBoPhanID() {
		return boPhanID;
	}
	public void setBoPhanID(String boPhanID) {
		this.boPhanID = boPhanID;
	}
	public String getTenBoPhan() {
		return tenBoPhan;
	}
	public void setTenBoPhan(String tenBoPhan) {
		this.tenBoPhan = tenBoPhan;
	}
	public BoPhan(String boPhanID, String tenBoPhan) {
		super();
		this.boPhanID = boPhanID;
		this.tenBoPhan = tenBoPhan;
	}
	public BoPhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
