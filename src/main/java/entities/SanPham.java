package entities;

import enums.KieuDang;
import enums.LoaiSanPham;

public class SanPham {
	private String sanPhamID;

	@Override
	public String toString() {
		return "SanPham [sanPhamID=" + sanPhamID + ", tenSanPham=" + tenSanPham + ", kieuDang=" + kieuDang
				+ ", loaiSanPham=" + loaiSanPham + ", soLuong=" + soLuong + ", getSanPhamID()=" + getSanPhamID()
				+ ", getTenSanPham()=" + getTenSanPham() + ", getKieuDang()=" + getKieuDang() + ", getLoaiSanPham()="
				+ getLoaiSanPham() + ", getSoLuong()=" + getSoLuong() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	private String tenSanPham;
	private KieuDang kieuDang;
	private String kichThuoc;
	private LoaiSanPham loaiSanPham;
	private int soLuong;
	private String hinhAnh;
	private int tinhTrang;

	public SanPham(String sanPhamID, String tenSanPham, KieuDang kieuDang, String kichThuoc, LoaiSanPham loaiSanPham,
			int soLuong, String hinhAnh, int tinhTrang) {
		super();
		this.sanPhamID = sanPhamID;
		this.tenSanPham = tenSanPham;
		this.kieuDang = kieuDang;
		this.kichThuoc = kichThuoc;
		this.loaiSanPham = loaiSanPham;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.tinhTrang = tinhTrang;
	}

	public SanPham() {
		super();
	}

	public String getSanPhamID() {
		return sanPhamID;
	}

	public void setSanPhamID(String sanPhamID) {
		this.sanPhamID = sanPhamID;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public KieuDang getKieuDang() {
		return kieuDang;
	}

	public void setKieuDang(KieuDang kieuDang) {
		this.kieuDang = kieuDang;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public SanPham(String sanPhamID, String tenSanPham, KieuDang kieuDang, String kichThuoc, LoaiSanPham loaiSanPham,
			int soLuong, String hinhAnh) {
		super();
		this.sanPhamID = sanPhamID;
		this.tenSanPham = tenSanPham;
		this.kieuDang = kieuDang;
		this.kichThuoc = kichThuoc;
		this.loaiSanPham = loaiSanPham;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.tinhTrang=0;
	}

	
	
//	public SanPham(String sanPhamID, String tenSanPham, KieuDang kieuDang, LoaiSanPham loaiSanPham, int soLuong,
//			String hinhAnh) {
//		this.sanPhamID = sanPhamID;
//		this.tenSanPham = tenSanPham;
//		this.kieuDang = kieuDang;
//	}
	
	}
