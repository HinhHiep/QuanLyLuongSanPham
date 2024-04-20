package enums;

public enum CapBac {
	THCS("THCS"),
	THPT("THPT"),
	TRUNGCAPNGHE("Trung cấp nghề"),
	CAODANG("Cao đẳng"),
	DAIHOC("Đại học");
	private String value;
	
	private CapBac(String value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {	
		return this.value;
	}
	
	public static CapBac getCapBat(String capBac) {
		return capBac.equals("THCS") ? CapBac.THCS 
						: capBac.equals("THPT") ? CapBac.THPT 
						: capBac.equals("Cao đẳng") ? CapBac.CAODANG 
						: capBac.equals("Trung cấp nghề") ? CapBac.TRUNGCAPNGHE : CapBac.DAIHOC;	
	}
	
//	Là một số thực có giá trị từ:1-6.78
//	Có giá trị theo cấp bậc:
//	DAIHOC: 4.40-6.78
//	CAODANG: 2.34-4.98
//	TRUNGCAPNGHE: 1.86-3.46
//	THPT,THCS: 1-2.5


}
