package enums;

public enum KieuDang {
	BODY("Body"), TIEUCHUAN("Tiêu chuẩn"), FORMRONG("Form rộng");

	private String value;

	private KieuDang(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public static KieuDang fromString(String text) {
		for (KieuDang kieuDang : KieuDang.values()) {
			if (kieuDang.value.equalsIgnoreCase(text)) {
				return kieuDang;
			}
		}
		return null; // Hoặc ném một ngoại lệ nếu không tìm thấy
	}
}
