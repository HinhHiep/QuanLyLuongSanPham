package enums;

public enum LoaiSanPham {
	AOSOMI("Áo sơ mi"), DOTHETHAO("Đồ thể thao"), VAYLIENTHAN("Váy liền thân"), AOKHOAC("Áo khoác"),
	QUANTAY("Quần tây"), QUANJEAN("Quần jean");

	private String value;

	private LoaiSanPham(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public static LoaiSanPham fromString(String text) {
		for (LoaiSanPham lsp : LoaiSanPham.values()) {
			if (lsp.value.equalsIgnoreCase(text)) {
				return lsp;
			}
		}
		return null; // Hoặc ném một ngoại lệ nếu không tìm thấy
	}
}
