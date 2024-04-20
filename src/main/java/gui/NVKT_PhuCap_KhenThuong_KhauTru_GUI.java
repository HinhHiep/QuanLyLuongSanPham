package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietPhuCapKhenThuongKhauTruDAO;
import dao.PhuCapKhenThuongKhauTruDAO;
import entities.BangLuongNhanVien;
import entities.ChiTietPhuCapKhenThuongKhauTru;
import entities.PhuCapKhenThuongKhauTru;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NVKT_PhuCap_KhenThuong_KhauTru_GUI extends JPanel {

	private JPanel contentPane;
	private JTextField txtMaLoai;
	private JTextField txtDonGia;
	private JTable table;
	private PhuCapKhenThuongKhauTruDAO phuCapKhenThuongKhauTruDAO = new PhuCapKhenThuongKhauTruDAO();
	private JComboBox jCBBTenLoai;
	private JComboBox jCCBChonLoai;
	public static DefaultTableModel model;
	private ChiTietPhuCapKhenThuongKhauTruDAO ctpc = new ChiTietPhuCapKhenThuongKhauTruDAO();
	private List<PhuCapKhenThuongKhauTru> listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachByMaLuongID(NVKT_TinhLuongNV.maBangLuongTable);
	private JLabel txtMaNhanVien;
	private JLabel txtHoTen;
	private JLabel txtPhongBan;
	private JLabel txtSoNgayCong;
	private JLabel txtSoNgayVang;
	private JLabel txtPhuCap;
	private JLabel txtKhenThuong;
	private JLabel txtKhauTru;
	private JLabel txtLuongCoBan;
	private JLabel txtLuongThucLanh;
	public NVKT_PhuCap_KhenThuong_KhauTru_GUI() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("PHỤ CẤP - KHEN THƯỞNG - KHẤU TRỪ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);

		JPanel main = new JPanel();
		add(main, BorderLayout.CENTER);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		main.add(top);

		JLabel lblNewLabel_1 = new JLabel("Tháng: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		top.add(lblNewLabel_1);

		JLabel txtThangLuong = new JLabel("09-2019");
		txtThangLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		top.add(txtThangLuong);

		JPanel detailCN = new JPanel();
		detailCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"THÔNG TIN NHÂN VIÊN", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(64, 128, 128)));
		main.add(detailCN);
		detailCN.setLayout(new BoxLayout(detailCN, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		detailCN.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(lblNewLabel_2);

		txtMaNhanVien = new JLabel("231NHSMAY001");
		txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(txtMaNhanVien);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("Phụ cấp:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblNewLabel_4);

		txtPhuCap = new JLabel("hongsonnguyen1003@gmail.com");
		txtPhuCap.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(txtPhuCap);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_6);

		JLabel lblNewLabel_6 = new JLabel("Họ tên:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6.add(lblNewLabel_6);

		txtHoTen = new JLabel("Nguyễn Hồng Sơn");
		txtHoTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6.add(txtHoTen);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_7);

		JLabel lblNewLabel_8 = new JLabel("Khen thưởng:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7.add(lblNewLabel_8);

		txtKhenThuong = new JLabel("8538442429");
		txtKhenThuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7.add(txtKhenThuong);

		JPanel panel_5_1 = new JPanel();
		panel_1.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_6_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_6_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_5_1.add(panel_6_1);

		JLabel lblNewLabel_6_1 = new JLabel("Phòng ban:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1.add(lblNewLabel_6_1);

		txtPhongBan = new JLabel("Nam");
		txtPhongBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1.add(txtPhongBan);

		JPanel panel_7_1 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_7_1.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_5_1.add(panel_7_1);

		JLabel lblNewLabel_8_1 = new JLabel("Khấu trừ:");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7_1.add(lblNewLabel_8_1);

		txtKhauTru = new JLabel("May 001");
		txtKhauTru.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7_1.add(txtKhauTru);

		JPanel panel_5_1_1 = new JPanel();
		panel_1.add(panel_5_1_1);
		panel_5_1_1.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_6_1_1 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_6_1_1.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_5_1_1.add(panel_6_1_1);

		JLabel lblNewLabel_6_1_1 = new JLabel("Số ngày công:");
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1_1.add(lblNewLabel_6_1_1);

		txtSoNgayCong = new JLabel("10-03-2003");
		txtSoNgayCong.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1_1.add(txtSoNgayCong);

		JPanel panel_7_1_1 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_7_1_1.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_5_1_1.add(panel_7_1_1);

		JLabel lblNewLabel_8_1_1 = new JLabel("Lương cơ bản:");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7_1_1.add(lblNewLabel_8_1_1);

		txtLuongCoBan = new JLabel("Hợp đồng nhân thọ");
		txtLuongCoBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7_1_1.add(txtLuongCoBan);

		JPanel panel_5_1_2 = new JPanel();
		panel_1.add(panel_5_1_2);
		panel_5_1_2.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_6_1_2 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_6_1_2.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_5_1_2.add(panel_6_1_2);

		JLabel lblNewLabel_6_1_2 = new JLabel("Số ngày vắng:");
		lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1_2.add(lblNewLabel_6_1_2);

		txtSoNgayVang = new JLabel("Long An");
		txtSoNgayVang.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6_1_2.add(txtSoNgayVang);
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_13.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_5_1_2.add(panel_13);
		
		JLabel lblNewLabel_8_1_1_1 = new JLabel("Lương thực lãnh:");
		lblNewLabel_8_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_13.add(lblNewLabel_8_1_1_1);
		
		txtLuongThucLanh = new JLabel("Hợp đồng nhân thọ");
		txtLuongThucLanh.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_13.add(txtLuongThucLanh);

		JPanel ctmain = new JPanel();
		ctmain.setBorder(new TitledBorder(null, "TH\u00D4NG TIN CHI TI\u1EBET", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(64, 128, 128)));
		detailCN.add(ctmain);

		JPanel panel_9 = new JPanel();
		ctmain.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_11.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_11);

		JLabel lblNewLabel_10 = new JLabel("Chọn loại:");
		lblNewLabel_10.setPreferredSize(new Dimension(90, 14));
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_11.add(lblNewLabel_10);

		jCCBChonLoai = new JComboBox();
		jCCBChonLoai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					jCBBTenLoai.setModel(new DefaultComboBoxModel(
							addDanhSachVaoComboBox(e.getItem().toString()).toArray(String[]::new)));// conver List thành String[]
					jCBBTenLoai.setSelectedIndex(-1);
				}
			}
		});
		jCCBChonLoai.setModel(new DefaultComboBoxModel(new String[] { "Phụ cấp", "Khen thưởng", "Khấu trừ" }));
		jCCBChonLoai.setSelectedIndex(-1);
		panel_11.add(jCCBChonLoai);

		JPanel panel_11_1 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_11_1.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_11_1);

		JLabel lblNewLabel_10_1 = new JLabel("Tên loại:");
		lblNewLabel_10_1.setPreferredSize(new Dimension(90, 14));
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_11_1.add(lblNewLabel_10_1);

		jCBBTenLoai = new JComboBox();
		jCBBTenLoai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					PhuCapKhenThuongKhauTru phuCapKhenThuongKhauTru = phuCapKhenThuongKhauTruDAO.
							getMaVaSoTienTheoTenLoai(e.getItem().toString());
					txtMaLoai.setText(phuCapKhenThuongKhauTru.getId());
					txtDonGia.setText(String.valueOf(phuCapKhenThuongKhauTru.getGiaTien()));
				}
			}
		});
		panel_11_1.add(jCBBTenLoai);

		JPanel panel_11_3 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_11_3.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_11_3);

		JLabel lblNewLabel_10_3 = new JLabel("Mã loại:");
		lblNewLabel_10_3.setPreferredSize(new Dimension(90, 14));
		lblNewLabel_10_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_11_3.add(lblNewLabel_10_3);

		txtMaLoai = new JTextField();
		txtMaLoai.setEditable(false);
		txtMaLoai.setEnabled(false);
		txtMaLoai.setColumns(50);
		panel_11_3.add(txtMaLoai);

		JPanel panel_11_3_1 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) panel_11_3_1.getLayout();
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_8.add(panel_11_3_1);

		JLabel lblNewLabel_10_3_1 = new JLabel("Đơn giá");
		lblNewLabel_10_3_1.setPreferredSize(new Dimension(90, 14));
		lblNewLabel_10_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_11_3_1.add(lblNewLabel_10_3_1);

		txtDonGia = new JTextField();
		txtDonGia.setEnabled(false);
		txtDonGia.setEditable(false);
		txtDonGia.setColumns(50);
		panel_11_3_1.add(txtDonGia);

		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnThem)) {
					ChiTietPhuCapKhenThuongKhauTru ct = new ChiTietPhuCapKhenThuongKhauTru
							(new PhuCapKhenThuongKhauTru(txtMaLoai.getText()), new BangLuongNhanVien(NVKT_TinhLuongNV.maBangLuongTable));
					if(ctpc.getChiTietKhenThuongPhuCapKhauTru(ct.getPhuCapKhenThuongKhauTru().getId(), ct.getBangLuongNhanVien().getBangLuongID()) == null) {
						ctpc.addChiTietPhuCapKhenThuongKhauTru(ct);
						listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachByMaLuongID(NVKT_TinhLuongNV.maBangLuongTable);
						addDataTable(listPCKTKT);
					}
					else
						showMessage("Thông báo", "Đã tồn tại");
					
					
				}
			}
		});
		btnThem.setBackground(new Color(64, 128, 128));
		panel_10.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnXoa)) {
					int rowSelect = table.getSelectedRow();
					if (rowSelect == -1)
						showMessage("Thông báo", "Mời chọn dòng cần xóa");
					else {
						System.out.println(ctpc.deleteChiTietPhuCapKhenThuongKhauTru(NVKT_TinhLuongNV.maBangLuongTable, table.getValueAt(rowSelect, 1).toString()));
						listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachByMaLuongID(NVKT_TinhLuongNV.maBangLuongTable);
						addDataTable(listPCKTKT);
					}
				}
			}
		});
		btnXoa.setBackground(new Color(64, 128, 128));
		panel_10.add(btnXoa);

		JPanel panel_12 = new JPanel();
		panel_8.add(panel_12);

		JScrollPane scrollPane = new JScrollPane();
		panel_12.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 lo\u1EA1i", "T\u00EAn lo\u1EA1i", "Lo\u1EA1i", "\u0110\u01A1n gi\u00E1"
			}
		));
		table.setPreferredScrollableViewportSize(new Dimension(750, 200));
		scrollPane.setViewportView(table);
		
		addDataTable(listPCKTKT);
	}
	
	private void addDataTable(List<PhuCapKhenThuongKhauTru> list) {
	
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);		
		int stt = 1;
		double tien = 0;
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
		txtMaNhanVien.setText(NVKT_TinhLuongNV.thongTinNV.getNguoiID());
		txtHoTen.setText(NVKT_TinhLuongNV.thongTinNV.getHoTen());
		txtPhongBan.setText(NVKT_TinhLuongNV.thongTinNV.getPhongBan().getTenPhongBan());
		txtSoNgayCong.setText(String.valueOf(NVKT_TinhLuongNV.SNC));
		txtSoNgayCong.setText(String.valueOf(NVKT_TinhLuongNV.SNV));
		txtPhuCap.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongPhuCap));
		txtKhenThuong.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongKhenThuong));
		txtKhauTru.setText(convertLuongCoBan(NVKT_TinhLuongNV.tongKhauTru));
		txtLuongCoBan.setText(convertLuongCoBan(NVKT_TinhLuongNV.thongTinNV.getLuongCoBan()));
		txtLuongThucLanh.setText(convertLuongCoBan(NVKT_TinhLuongNV.luongThucLanh));
	}

	private List<String> addDanhSachVaoComboBox(String loai) {
		List<String> ds = phuCapKhenThuongKhauTruDAO.getDanhSachTenTheoLoai(loai);
		return ds;
	}
	
	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}
	
	private static String convertLuongCoBan(double luongCoBan) { // số dài quá kh hiển thị hết thì xài hàm này
		DecimalFormat df = new DecimalFormat("###,###,###.0");
		BigDecimal decimal = new BigDecimal(luongCoBan);
		String luong = df.format(decimal);
		return luong;
	}
	
	


}
