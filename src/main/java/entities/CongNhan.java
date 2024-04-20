package entities;

import java.time.LocalDate;

public class CongNhan extends Nguoi{
	private BoPhan boPhan;
	public BoPhan getBoPhan() {
		return boPhan;
	}
	public void setBoPhan(BoPhan boPhan) {
		this.boPhan = boPhan;
	}
	
	public CongNhan(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai, BoPhan boPhan, double heSoLamViec) {
		super(nguoiID, hoTen, ngaySinh, gioiTinh, soCMND, diaChi, email, soDienThoai, trangThai);
		this.boPhan = boPhan;
	
	}
	public CongNhan(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai) {
		super(nguoiID, hoTen, ngaySinh, gioiTinh, soCMND, diaChi, email, soDienThoai, trangThai);
	}
	
	public CongNhan(String nguoiID, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String soCMND, String diaChi,
			String email, String soDienThoai, boolean trangThai, BoPhan boPhan) {
		super(nguoiID, hoTen, ngaySinh, gioiTinh, soCMND, diaChi, email, soDienThoai, trangThai);
		this.boPhan = boPhan;
	}
	public CongNhan(String nguoiID, BoPhan boPhan) {
		super(nguoiID);
		this.boPhan = boPhan;
	
	}
	public CongNhan(String nguoiID) {
		super(nguoiID);
	}
	
	public CongNhan(Nguoi nguoi, BoPhan boPhan) {
		super(nguoi.getNguoiID(), nguoi.getHoTen(), nguoi.getNgaySinh(), nguoi.isGioiTinh(), nguoi.getSoCMND(), nguoi.getDiaChi(), nguoi.getEmail(), nguoi.getSoDienThoai(), nguoi.isTrangThai());
		this.boPhan=boPhan;
	}
	
	public CongNhan(String nguoiID, String hoTen, BoPhan bp) {
		super(nguoiID, hoTen);
		this.boPhan = bp;
	}
	
		
	
	
}
