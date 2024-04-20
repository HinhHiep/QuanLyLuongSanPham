package entities;

public class ChiTietPhuCapKhenThuongKhauTru {
	private PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru;
	private BangLuongNhanVien bangLuongNhanVien;


	public ChiTietPhuCapKhenThuongKhauTru(PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru,
			BangLuongNhanVien bangLuongNhanVien) {
		super();
		this.phuCapKhenThuongKhauTru = phuCapKhenThuongKhauTru;
		this.bangLuongNhanVien = bangLuongNhanVien;
	}

	public PhuCapKhenThuongKhauTru getPhuCapKhenThuongKhauTru() {
		return phuCapKhenThuongKhauTru;
	}

	public void setPhuCapKhenThuongKhauTru(PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru) {
		this.phuCapKhenThuongKhauTru = phuCapKhenThuongKhauTru;
	}

	public BangLuongNhanVien getBangLuongNhanVien() {
		return bangLuongNhanVien;
	}

	public void setBangLuongNhanVien(BangLuongNhanVien bangLuongNhanVien) {
		this.bangLuongNhanVien = bangLuongNhanVien;
	}

	
	
	
	
}
