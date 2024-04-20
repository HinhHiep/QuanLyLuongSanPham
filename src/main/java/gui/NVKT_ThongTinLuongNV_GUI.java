package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import entities.PhuCapKhenThuongKhauTru;

public class NVKT_ThongTinLuongNV_GUI extends JPanel {

	//private JTable tableNV = NVKT_TinhLuongNV.tableNhanVien;
	private JLabel textMaNV;
	private JLabel textHoTen;
	private JLabel textGioiTinh;
	private JLabel textNgaySinh;
	private JLabel textSNC;
	private JLabel textSNV;
	private JLabel textCMND;
	private JLabel textDiaChi;
	private JLabel textEmail;
	private JLabel textSDT;
	private JLabel textPhongBan;
	private JLabel textChucVu;
	private JLabel textCapBac;
	private JLabel textSNKN;
	private JLabel textTongPhuCap;
	private JLabel textTongKhenThuong;
	private JLabel textTongKhauTru;
	private JLabel textLuongTruocThue;
	private JLabel textThuNhapCaNhan;
	private JLabel textLuongThucLanh;
	private JTable table;
	public static DefaultTableModel model;
	private JLabel textMaPhieu;
	private JLabel textThangLuong;
	private JLabel textKhauTruVang;
	private JLabel textLCB;
	

	public NVKT_ThongTinLuongNV_GUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 255, 255));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("THÔNG TIN CHI TIẾT LƯƠNG");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(64, 128, 128));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Mã phiếu: ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_2.add(lblNewLabel_1);
		
		textMaPhieu = new JLabel("New label");
		textMaPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(textMaPhieu);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_3);

		textThangLuong = new JLabel("Tháng ");
		textThangLuong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_3.add(textThangLuong);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"TH\u00D4NG TIN C\u00D4NG NH\u00C2N", TitledBorder.LEFT, TitledBorder.TOP, null,
								new Color(64, 128, 128)),
						"TH\u00D4NG TIN NH\u00C2N VI\u00CAN", TitledBorder.LEFT, TitledBorder.TOP, null,
						new Color(64, 128, 128)));
		add(panel_4);
		panel_4.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(0, 20, 0, 0));
		panel_5.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_3 = new JLabel("Mã công nhân:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_7.add(lblNewLabel_3);

		textMaNV = new JLabel("New label");
		textMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_7.add(textMaNV);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_8);

		JLabel lblNewLabel_4 = new JLabel("Họ tên: ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_8.add(lblNewLabel_4);

		textHoTen = new JLabel("New label");
		textHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_8.add(textHoTen);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_9);

		JLabel lblNewLabel_5 = new JLabel("Giới tính: ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_9.add(lblNewLabel_5);

		textGioiTinh = new JLabel("New label");
		textGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_9.add(textGioiTinh);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_4 = (FlowLayout) panel_10.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_10);

		JLabel lblNewLabel_6 = new JLabel("Ngày sinh: ");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_10.add(lblNewLabel_6);

		textNgaySinh = new JLabel("New label");
		textNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_10.add(textNgaySinh);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EmptyBorder(0, 20, 0, 0));
		panel_6.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_7 = new JLabel("Số CMND: ");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_11.add(lblNewLabel_7);

		textCMND = new JLabel("New label");
		textCMND.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_11.add(textCMND);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_5 = (FlowLayout) panel_13.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_13);

		JLabel lblNewLabel_8 = new JLabel("Địa chỉ: ");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_13.add(lblNewLabel_8);

		textDiaChi = new JLabel("New label");
		textDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_13.add(textDiaChi);

		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_6 = (FlowLayout) panel_14.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_14);

		JLabel lblNewLabel_9 = new JLabel("Email: ");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_14.add(lblNewLabel_9);

		textEmail = new JLabel("New label");
		textEmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_14.add(textEmail);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_7 = (FlowLayout) panel_15.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_15);

		JLabel lblNewLabel_10 = new JLabel("Số điện thoại: ");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_15.add(lblNewLabel_10);

		textSDT = new JLabel("New label");
		textSDT.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_15.add(textSDT);

		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_8 = (FlowLayout) panel_16.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_12.add(panel_16);

		JLabel lblNewLabel_11 = new JLabel("Phòng ban: ");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_16.add(lblNewLabel_11);

		textPhongBan = new JLabel("New label");
		textPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_16.add(textPhongBan);

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_9 = (FlowLayout) panel_17.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_12.add(panel_17);

		JLabel lblNewLabel_12 = new JLabel("Chức vụ: ");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_17.add(lblNewLabel_12);

		textChucVu = new JLabel("New label");
		textChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_17.add(textChucVu);

		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_10 = (FlowLayout) panel_18.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_12.add(panel_18);

		JLabel lblNewLabel_13 = new JLabel("Cấp bậc: ");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_18.add(lblNewLabel_13);

		textCapBac = new JLabel("New label");
		textCapBac.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_18.add(textCapBac);

		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_11 = (FlowLayout) panel_19.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_12.add(panel_19);

		JLabel lblNewLabel_14 = new JLabel("Số năm kinh nghiệm: ");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_19.add(lblNewLabel_14);

		textSNKN = new JLabel("New label");
		textSNKN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_19.add(textSNKN);

		JPanel panel_20 = new JPanel();
		add(panel_20);
		panel_20.setLayout(new BoxLayout(panel_20, BoxLayout.Y_AXIS));

		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_20.add(panel_21);
		panel_21.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel lblNewLabel_15 = new JLabel("Số ngày công: ");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_21.add(lblNewLabel_15);

		textSNC = new JLabel("New label");
		textSNC.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_21.add(textSNC);

		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout_12 = (FlowLayout) panel_22.getLayout();
		flowLayout_12.setHgap(30);
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_20.add(panel_22);

		JLabel lblNewLabel_16 = new JLabel("Số ngày vắng: ");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_22.add(lblNewLabel_16);

		textSNV = new JLabel("New label");
		textSNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_22.add(textSNV);

		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout_13 = (FlowLayout) panel_23.getLayout();
		flowLayout_13.setHgap(30);
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		panel_20.add(panel_23);

		JLabel lblNewLabel_17 = new JLabel("Khấu trừ vắng:");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_23.add(lblNewLabel_17);
		
		textKhauTruVang = new JLabel("New label");
		textKhauTruVang.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_23.add(textKhauTruVang);
		
		JPanel panel_23_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_23_1.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_23_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_20.add(panel_23_1);
		
		JLabel lblNewLabel_17_1 = new JLabel("Lương cơ bản: ");
		lblNewLabel_17_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_23_1.add(lblNewLabel_17_1);
		
		textLCB = new JLabel(",0");
		textLCB.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_23_1.add(textLCB);

		JPanel panel_27 = new JPanel();
		add(panel_27);
		panel_27.setLayout(new BoxLayout(panel_27, BoxLayout.Y_AXIS));

		JPanel panel_24 = new JPanel();
		panel_27.add(panel_24);
		panel_24.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_25 = new JPanel();
		panel_25.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_14 = (FlowLayout) panel_25.getLayout();
		flowLayout_14.setHgap(0);
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_24.add(panel_25);

		JPanel panel_26 = new JPanel();
		FlowLayout flowLayout_15 = (FlowLayout) panel_26.getLayout();
		flowLayout_15.setAlignment(FlowLayout.RIGHT);
		panel_24.add(panel_26);

		JScrollPane scrollPane = new JScrollPane();
		panel_27.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setFont(new Font("Times New Roman", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 ", "T\u00EAn", "Lo\u1EA1i", "\u0110\u01A1n gi\u00E1"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JPanel panel_28 = new JPanel();
		add(panel_28);
		panel_28.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_30 = new JPanel();
		panel_28.add(panel_30);
		panel_30.setLayout(new BoxLayout(panel_30, BoxLayout.Y_AXIS));

		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_16 = (FlowLayout) panel_29.getLayout();
		flowLayout_16.setAlignment(FlowLayout.LEFT);
		panel_30.add(panel_29);

		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_17 = (FlowLayout) panel_31.getLayout();
		flowLayout_17.setAlignment(FlowLayout.LEFT);
		panel_30.add(panel_31);

		JLabel lblNewLabel_19 = new JLabel("Tổng phụ cấp: ");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_31.add(lblNewLabel_19);

		textTongPhuCap = new JLabel("New label");
		textTongPhuCap.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_31.add(textTongPhuCap);

		JPanel panel_32 = new JPanel();
		panel_32.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_18 = (FlowLayout) panel_32.getLayout();
		flowLayout_18.setAlignment(FlowLayout.LEFT);
		panel_30.add(panel_32);

		JLabel lblNewLabel_20 = new JLabel("Tổng khen thưởng:");
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_32.add(lblNewLabel_20);

		textTongKhenThuong = new JLabel("New label");
		textTongKhenThuong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_32.add(textTongKhenThuong);

		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_19 = (FlowLayout) panel_33.getLayout();
		flowLayout_19.setAlignment(FlowLayout.LEFT);
		panel_30.add(panel_33);

		JLabel lblNewLabel_21 = new JLabel("Tổng khấu trừ:");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_33.add(lblNewLabel_21);

		textTongKhauTru = new JLabel("New label");
		textTongKhauTru.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_33.add(textTongKhauTru);

		JPanel panel_34 = new JPanel();
		panel_28.add(panel_34);
		panel_34.setLayout(new BoxLayout(panel_34, BoxLayout.Y_AXIS));

		JPanel panel_35 = new JPanel();
		panel_35.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_20 = (FlowLayout) panel_35.getLayout();
		flowLayout_20.setAlignment(FlowLayout.LEFT);
		panel_34.add(panel_35);

		JLabel lblNewLabel_22 = new JLabel("Tổng lương trước thuế: ");
		lblNewLabel_22.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_35.add(lblNewLabel_22);

		textLuongTruocThue = new JLabel("New label");
		textLuongTruocThue.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_35.add(textLuongTruocThue);

		JPanel panel_36 = new JPanel();
		panel_36.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_21 = (FlowLayout) panel_36.getLayout();
		flowLayout_21.setAlignment(FlowLayout.LEFT);
		panel_34.add(panel_36);

		JLabel lblNewLabel_23 = new JLabel("Thuế thu nhập cá nhân");
		lblNewLabel_23.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_36.add(lblNewLabel_23);

		textThuNhapCaNhan = new JLabel("New label");
		textThuNhapCaNhan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_36.add(textThuNhapCaNhan);

		JPanel panel_37 = new JPanel();
		panel_37.setBorder(new EmptyBorder(0, 20, 0, 0));
		FlowLayout flowLayout_22 = (FlowLayout) panel_37.getLayout();
		flowLayout_22.setAlignment(FlowLayout.LEFT);
		panel_34.add(panel_37);

		JLabel lblNewLabel_24 = new JLabel("LƯƠNG THỰC LÃNH: ");
		lblNewLabel_24.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_37.add(lblNewLabel_24);

		textLuongThucLanh = new JLabel("New label");
		textLuongThucLanh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_37.add(textLuongThucLanh);

		JPanel panel_38 = new JPanel();
		FlowLayout flowLayout_23 = (FlowLayout) panel_38.getLayout();
		flowLayout_23.setAlignment(FlowLayout.RIGHT);
		add(panel_38);

		JButton btnXuat = new JButton("Xuất phiếu lương");
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnXuat)) {
					try {
						printPDF();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_38.add(btnXuat);
		addTableLabel();
	}
	
	private void addTableLabel() {
		textMaNV.setText(NVKT_TinhLuongNV.thongTinNV.getNguoiID());
		textHoTen.setText(NVKT_TinhLuongNV.thongTinNV.getHoTen());
		textMaPhieu.setText(NVKT_TinhLuongNV.maBangLuongTable);
		textThangLuong.setText("Tháng " + NVKT_TinhLuongNV.thangLuong);
		String gioiTinh;
		textGioiTinh.setText(NVKT_TinhLuongNV.thongTinNV.isGioiTinh() ? "Nam" : "Nữ");
		textNgaySinh.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(NVKT_TinhLuongNV.thongTinNV.getNgaySinh()));
		textChucVu.setText(NVKT_TinhLuongNV.thongTinNV.getChucVu().getTenChucVu());
		textCapBac.setText(NVKT_TinhLuongNV.thongTinNV.getCapBac().toString());
		textPhongBan.setText(NVKT_TinhLuongNV.thongTinNV.getPhongBan().getTenPhongBan());
		textSNKN.setText(String.valueOf(NVKT_TinhLuongNV.thongTinNV.getSoNamKinhNghiem()));
		textSNC.setText(String.valueOf(NVKT_TinhLuongNV.SNC));
		textSNV.setText(String.valueOf(NVKT_TinhLuongNV.SNV));
		textLCB.setText(convertLuongCoBan(NVKT_TinhLuongNV.thongTinNV.getLuongCoBan()));
		textKhauTruVang.setText(convertLuongCoBan(NVKT_TinhLuongNV.tienKhauTruVang));
		textLuongThucLanh.setText(convertLuongCoBan(NVKT_TinhLuongNV.luongThucLanh));
		textTongPhuCap.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongPhuCap));
		textTongKhenThuong.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongKhenThuong));
		textTongKhauTru.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongKhauTru));
		textLuongTruocThue.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongLuongTruocThue));
		textThuNhapCaNhan.setText(convertLuongCoBan(NVKT_TinhLuongNV.thueThuNhapCaNhan));
	
		textCMND.setText(NVKT_TinhLuongNV.thongTinNV.getSoCMND());
		textDiaChi.setText(NVKT_TinhLuongNV.thongTinNV.getDiaChi());
		textEmail.setText(NVKT_TinhLuongNV.thongTinNV.getEmail());
		textSDT.setText(NVKT_TinhLuongNV.thongTinNV.getSoDienThoai());
		
		addTableChiTietTable(NVKT_TinhLuongNV.listPCKTKT);
		
	}
	
	private static void printPDF() throws IOException {
		String path = "BangLuongNhanVien.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);

		PdfFont customFont = PdfFontFactory.createFont("font\\vuArial.ttf", PdfEncodings.IDENTITY_H,true);
		pdfDocument.setDefaultPageSize(PageSize.A4);	
		Document document = new Document(pdfDocument);
		float threecol = 190f;
		float threeColumnWidth[] = {threecol,threecol,threecol}; 
		float twocol = 280f;
		float twocol150 = twocol + 150f;
		float twoColumnWith[] = {twocol150, twocol};
		float fullwidth[] = {threecol * 3}; 
		Paragraph onesp = new Paragraph("\n");
		Table table = new Table(twoColumnWith);
		table.addCell(new Cell().add("Thông tin lương nhân viên").setFont(customFont).setBorder(Border.NO_BORDER).setBold());
		Table nestedTable = new Table(new float[] {twocol/2, twocol/2});
		nestedTable.addCell(getHeaderTextCell("Mã bảng lương").setFont(customFont));
		nestedTable.addCell(getHeaderTextCellValue(NVKT_TinhLuongNV.maBangLuongTable).setFont(customFont));
		nestedTable.addCell(getHeaderTextCell("Tháng lương").setFont(customFont));
		nestedTable.addCell(getHeaderTextCellValue(NVKT_TinhLuongNV.thangLuong).setFont(customFont));
		table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
		
		Border gb = new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 0.5f);
		Table divider = new Table(fullwidth);
		divider.setBorder(gb);
		divider.setMarginTop(20f);
	
			
		document.add(table);
		document.add(divider);
		document.add(onesp);
	
		
		Table twoColTable = new Table(twoColumnWith);
		twoColTable.addCell(getNhanVienCell("Thông tin cá nhân").setFont(customFont));
		twoColTable.addCell(getNhanVienCell("Thông tin lương").setFont(customFont));
		document.add(twoColTable.setMargin(12f));
		
		Table twoColTable2 = new Table(twoColumnWith);
		twoColTable2.addCell(getNhanVien10CellLeft("Mã nhân viên: ", true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Tổng phụ cấp: ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(NVKT_TinhLuongNV.thongTinNV.getNguoiID(),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.tongPhuCap),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Họ tên: ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Tổng khen thưởng",true).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(NVKT_TinhLuongNV.thongTinNV.getHoTen(), false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.tongKhenThuong),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Phòng ban:",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Tổng khấu trừ ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(NVKT_TinhLuongNV.thongTinNV.getPhongBan().getTenPhongBan(),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.tongKhauTru),false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft("Chức vụ:  ", true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Tổng lương trước thuế: ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(NVKT_TinhLuongNV.thongTinNV.getChucVu().getTenChucVu(),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.tongLuongTruocThue),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Lương cơ bản ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Thuế thu nhập cá nhân",true).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.thongTinNV.getLuongCoBan()),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.thueThuNhapCaNhan),false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft("Hệ số lương: ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Lương thực lãnh:",true).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(String.valueOf(NVKT_TinhLuongNV.thongTinNV.getHeSoLuong()),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.luongThucLanh),false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft("Số ngày công:  ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("",false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(String.valueOf(NVKT_TinhLuongNV.SNC),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("",false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft("Số ngày vắng: ",true).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("",false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(String.valueOf(NVKT_TinhLuongNV.SNV),false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("",false).setFont(customFont));
		twoColTable2.addCell(getNhanVien10CellLeft("Khấu trừ vắng:",true).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft("",false).setFont(customFont));	
		twoColTable2.addCell(getNhanVien10CellLeft(convertLuongCoBan(NVKT_TinhLuongNV.tienKhauTruVang),false).setFont(customFont));	
	
		document.add(twoColTable2);
		
		Table tableDivider2 = new Table(fullwidth);
		Border dgb = new DashedBorder(com.itextpdf.kernel.color.Color.GRAY, 0.5f);
		document.add(tableDivider2.setBorder(dgb));
		Paragraph detail = new Paragraph("Phụ cấp - Khen thưởng - Khấu trừ");
		document.add(detail.setBold().setFont(customFont));
		
		Table threeColTable1 = new Table(threeColumnWidth);
		threeColTable1.setBackgroundColor(com.itextpdf.kernel.color.Color.BLACK, 0.7f);
		
		threeColTable1.addCell(new Cell().add("Tên").setFont(customFont).setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(Border.NO_BORDER));
		threeColTable1.addCell(new Cell().add("Loại").setFont(customFont).setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
		threeColTable1.addCell(new Cell().add("Giá tiền").setFont(customFont).setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		document.add(threeColTable1);
		
		Table threeColTable2 = new Table(threeColumnWidth);
		double tien;
		for(PhuCapKhenThuongKhauTru p : NVKT_TinhLuongNV.listPCKTKT) {
			if(p.getLoai().equals("Khấu trừ")) {
				if(p.getTen().equals("Khấu trừ bảo hiểm xã hội") || p.getTen().equals("Khấu trừ bảo hiểm y tế")) {
					tien = p.getGiaTien()/100 * NVKT_TinhLuongNV.thongTinNV.getLuongCoBan();
				}
				else {
					tien = p.getGiaTien()/100 * NVKT_TinhLuongNV.tongLuongTruocThue;
				}
			}		
			else
				tien = p.getGiaTien();
			threeColTable2.addCell(new Cell().add(p.getTen()).setFont(customFont).setBorder(Border.NO_BORDER).setMarginLeft(12f));
			threeColTable2.addCell(new Cell().add(p.getLoai()).setFont(customFont).setBorder(Border.NO_BORDER).setMarginLeft(12f).setTextAlignment(TextAlignment.CENTER));
			threeColTable2.addCell(new Cell().add(convertLuongCoBan(tien)).setFont(customFont).setBorder(Border.NO_BORDER).setMarginLeft(12f).setTextAlignment(TextAlignment.RIGHT).setMarginRight(15f));
			
		}
		document.add(threeColTable2);
		document.close();
	}
	
	static Cell getHeaderTextCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
	}
	
	static Cell getHeaderTextCellValue(String textValue) {
		return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}
	
	static Cell getNhanVienCell(String textValue) {
		return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}
	
	static Cell getNhanVien10CellLeft(String textValue, boolean isBold) {
		Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
		return isBold ? myCell.setBold() : myCell;
	}
	
	private static String convertLuongCoBan(double luongCoBan) { // số dài quá kh hiển thị hết thì xài hàm này
		DecimalFormat df = new DecimalFormat("###,###,###.0");
		BigDecimal decimal = new BigDecimal(luongCoBan);
		String luong = df.format(decimal);
		return luong;
	}
	
	private void addTableChiTietTable(List<PhuCapKhenThuongKhauTru> list) {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		int stt = 1;
		double tien;
		for(PhuCapKhenThuongKhauTru p : list) {
			if(p.getLoai().equals("Khấu trừ")) {
				if(p.getTen().equals("Khấu trừ bảo hiểm xã hội") || p.getTen().equals("Khấu trừ bảo hiểm y tế")) {
					tien = p.getGiaTien()/100 * NVKT_TinhLuongNV.thongTinNV.getLuongCoBan();
				}
				else {
					tien = p.getGiaTien()/100 * NVKT_TinhLuongNV.tongLuongTruocThue;
				}
			}		
			else
				tien = p.getGiaTien();
			model.addRow(new Object[] {
					stt,
					p.getId(),
					p.getTen(),
					p.getLoai(),
					tien
			});
			stt++;
		}
	}
	
	

}
