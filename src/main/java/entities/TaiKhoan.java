package entities;

public class TaiKhoan {
	private NhanVien nhanVien;
	private String matKhau;
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public TaiKhoan(NhanVien nhanVien, String matKhau) {
		super();
		this.nhanVien = nhanVien;
		this.matKhau = matKhau;
	}
	public TaiKhoan(NhanVien nhanVien) {
		super();
		this.nhanVien = nhanVien;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TaiKhoan [nhanVien=" + nhanVien + ", matKhau=" + matKhau + "]";
	}
	
}
