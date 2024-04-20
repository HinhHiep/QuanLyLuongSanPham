package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import dao.HopDongLaoDongDAO;
import dao.LoaiHopDongDAO;
import dao.NguoiDAO;
import entities.HopDongLaoDong;
import entities.LoaiHopDong;
import entities.Nguoi;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLNS_QuanLyHopDongLaoDong_GUI extends JPanel {

	private JPanel contentPane;
	private JTextField textTenHD;
	private JTextField textTimKiem;
	private JTable tableHD;
	private JTextField textMaCN;
	private JComboBox cbbLoaiHD;
	private JButton btnLamMoi;
	private JButton btnThem;
	public LoaiHopDongDAO loaiHopDongDAO = new LoaiHopDongDAO();
	public HopDongLaoDongDAO hopDongLaoDongDAO = new HopDongLaoDongDAO();
	public NguoiDAO nguoiDAO = new NguoiDAO();
	private JTextField textMaHD;
	static DefaultTableModel model;
	private JTextField textNgayKy;
	private JTextField textNgayHH;
	private JButton btnTaoMa;
	private String maLHD;
	public static JTextField textMaCongNhan;
	private List<HopDongLaoDong> listConHan = new ArrayList<HopDongLaoDong>();
	private List<HopDongLaoDong> listHetHan = new ArrayList<HopDongLaoDong>();

	public QLNS_QuanLyHopDongLaoDong_GUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pOption = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pOption.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(pOption);

		JButton btnNewButton_4 = new JButton("NHÂN VIÊN");
		pOption.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("CÔNG NHÂN");
		btnNewButton_5.setBackground(new Color(255, 128, 64));
		pOption.add(btnNewButton_5);

		JPanel hdld = new JPanel();
		hdld.setBorder(new TitledBorder(null, "H\u1EE3p \u0111\u1ED3ng lao \u0111\u1ED9ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(hdld);
		hdld.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 25, 0, 0));
		hdld.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3);

		JLabel lblNewLabel = new JLabel("Mã HĐ: ");
		lblNewLabel.setPreferredSize(new Dimension(85, 14));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3.add(lblNewLabel);

		textMaHD = new JTextField();
		textMaHD.setEnabled(false);
		textMaHD.setEditable(false);
		panel_3.add(textMaHD);
		textMaHD.setColumns(15);

		btnTaoMa = new JButton("Tạo mã");
		btnTaoMa.setEnabled(false);
		btnTaoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnTaoMa))
					if (!kiemTraMa())
						showMessage("Thông báo", "Chưa đủ dữ liệu để tạo mã hợp đồng, cần có ngày ký, loại hợp đồng");
			}
		});
		panel_3.add(btnTaoMa);

		JPanel panel_3_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3_1);

		JLabel lblMCngNhn = new JLabel("Mã công nhân: ");
		lblMCngNhn.setPreferredSize(new Dimension(90, 14));
		lblMCngNhn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3_1.add(lblMCngNhn);

		textMaCN = new JTextField();
		textMaCN.setEditable(false);
		textMaCN.setEnabled(false);
		textMaCN.setColumns(15);
		panel_3_1.add(textMaCN);

		JPanel panel_2_1 = new JPanel();
		panel.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_3_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_3_2);

		JLabel lblNgyK = new JLabel("Ngày ký: ");
		lblNgyK.setPreferredSize(new Dimension(85, 14));
		lblNgyK.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3_2.add(lblNgyK);

		textNgayKy = new JTextField();
		textNgayKy.setEnabled(false);
		panel_3_2.add(textNgayKy);
		textNgayKy.setColumns(10);

		JPanel panel_3_1_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_3_1_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_3_1_1);

		JLabel lblLoiH = new JLabel("Loại HĐ:");
		lblLoiH.setPreferredSize(new Dimension(90, 14));
		lblLoiH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3_1_1.add(lblLoiH);
		String[] dsLoaiHopDong = addDuLieuCBBLoaiHopDong().toArray(String[]::new);
		cbbLoaiHD = new JComboBox(dsLoaiHopDong);
		cbbLoaiHD.setEnabled(false);
		cbbLoaiHD.setSelectedIndex(-1);
		cbbLoaiHD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textTenHD.setText(loaiHopDongDAO.getTenLoaiHopDongById(e.getItem().toString()));
					textNgayKy.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()));
					textNgayHH.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()
							.plusDays(loaiHopDongDAO.getThoiHanLoaiHopDongById(e.getItem().toString())))); // hàm
																											// plusDays
																											// dùng dể
																											// cộng số
																											// ngày
					textMaHD.setText("");
					maLHD = e.getItem().toString();
				}
			}
		});
		panel_3_1_1.add(cbbLoaiHD);

		JPanel panel_2_1_1 = new JPanel();
		panel.add(panel_2_1_1);
		panel_2_1_1.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3_2_1 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_3_2_1.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_3_2_1);

		JLabel lblNgyHtHn = new JLabel("Ngày hết hạn:");
		lblNgyHtHn.setPreferredSize(new Dimension(85, 14));
		lblNgyHtHn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3_2_1.add(lblNgyHtHn);

		textNgayHH = new JTextField();
		textNgayHH.setEnabled(false);
		panel_3_2_1.add(textNgayHH);
		textNgayHH.setColumns(10);

		JPanel panel_3_1_1_1 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_3_1_1_1.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_3_1_1_1);

		JLabel lblTnH = new JLabel("Tên HĐ:");
		lblTnH.setPreferredSize(new Dimension(90, 14));
		lblTnH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_3_1_1_1.add(lblTnH);

		textTenHD = new JTextField();
		textTenHD.setEnabled(false);
		textTenHD.setEditable(false);
		textTenHD.setColumns(20);
		panel_3_1_1_1.add(textTenHD);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		hdld.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 5, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_5.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_5);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo mã công nhân");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_5.add(lblNewLabel_1);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_6.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6);

		textTimKiem = new JTextField();
		panel_6.add(textTimKiem);
		textTimKiem.setColumns(15);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnTmKim)) {
					if (listTimKiemByID(textTimKiem.getText()).size() == 0)
						showMessage("Thông báo", "Không tìm thấy");
					else {
						addDataTableTimKiemID(listTimKiemByID(textTimKiem.getText()));
					}
				}

			}
		});
		panel_6.add(btnTmKim);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		JPanel panel_5_1 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_5_1.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_5_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tìm kiếm TTHĐ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_5_1.add(lblNewLabel_1_1);

		JPanel panel_6_1 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_6_1.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_6_1);
		String[] cbb = {"Còn hạn", "Hết hạn"};
		JComboBox cBBTKTTHD = new JComboBox(cbb);
		cBBTKTTHD.setSelectedIndex(0);
		cBBTKTTHD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem().toString().equals("Còn hạn"))
						addDataTableConHan();
					else if(e.getItem().toString().equals("Hết hạn"))
						addDataTableHetHan();
					
				}
			}
		});
		cBBTKTTHD.setPreferredSize(new Dimension(150, 22));
		panel_6_1.add(cBBTKTTHD);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.PAGE_AXIS));

		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_10.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_10);

		JLabel lblChuaKy = new JLabel("Nhân viên chưa ký hợp đồng");
		lblChuaKy.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_10.add(lblChuaKy);

		JButton btnDanhSch = new JButton("Danh sách");
		btnDanhSch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnDanhSch)) {
					QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI qlns_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI = new QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI();
					qlns_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI.setVisible(true);
				}
			}
		});
		btnDanhSch.setHorizontalAlignment(SwingConstants.LEFT);
		panel_10.add(btnDanhSch);

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) panel_11.getLayout();
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_11);

		textMaCongNhan = new JTextField();
		textMaCongNhan.setEnabled(false);
		textMaCongNhan.setHorizontalAlignment(SwingConstants.LEFT);
		panel_11.add(textMaCongNhan);
		textMaCongNhan.setColumns(10);

		JButton btnThemVaoText = new JButton("Thêm vào");
		btnThemVaoText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnThemVaoText)) {
					if (textMaCongNhan.getText().equals("")) {
						showMessage("Thông báo", "Chưa có mã công nhân, lấy mã công nhân từ danh sách");
						QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI.hopDongFrame.setVisible(true);
					}

					else
						textMaCN.setText(textMaCongNhan.getText());
				}
			}
		});
		btnThemVaoText.setHorizontalAlignment(SwingConstants.LEFT);
		panel_11.add(btnThemVaoText);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_8.getLayout();
		flowLayout_12.setHgap(25);
		add(panel_8);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoi();
			}
		});

		btnLamMoi.setBackground(new Color(64, 128, 128));
		panel_8.add(btnLamMoi);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Thêm")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Bạn có muốn mở khóa các trường để thêm hợp đồng mới hay không?", "Thêm hợp đồng",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						btnThem.setText("Xác nhận");
						btnTaoMa.setEnabled(true);
						cbbLoaiHD.setEnabled(true);
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM hợp đồng");
						lamMoi();
					}

				} else if (e.getActionCommand().equals("Xác nhận")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Xác nhận THÊM hợp đồng cho công nhân này", "Xác nhận", JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						
						if(hopDongLaoDongDAO.kiemTraHopDongLaoDong(nguoiDAO.getNguoiByID(textMaCN.getText()).getNguoiID(), 
								loaiHopDongDAO.getLoaiHopDongById(maLHD).getLoaiHopDongID()) == null) {
							HopDongLaoDong hopDongLaoDong = new HopDongLaoDong(textMaHD.getText(),
									nguoiDAO.getNguoiByID(textMaCN.getText()), textTenHD.getText(),
									loaiHopDongDAO.getLoaiHopDongById(maLHD),
									LocalDate.parse(textNgayKy.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
									LocalDate.parse(textNgayHH.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
							boolean result = hopDongLaoDongDAO.addHopDongLaoDong(hopDongLaoDong);
							if (result) {
								showMessage("Thông báo", "Thêm thành công");
								lamMoi();
								btnThem.setText("Thêm");
								btnTaoMa.setEnabled(false);
								cbbLoaiHD.setEnabled(false);
								
								
								model.setRowCount(0);
								addDataTableConHan();
							}
						}
						else
							showMessage("Thông báo", "Hợp đồng vẫn còn hạn");
						
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM hợp đồng");
						btnThem.setText("Thêm");
						btnTaoMa.setEnabled(false);
						cbbLoaiHD.setEnabled(false);
						addDataTableConHan();
						lamMoi();
					}
				}
			}
		});
		btnThem.setBackground(new Color(64, 128, 128));
		panel_8.add(btnThem);

		JPanel tb = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tb.getLayout();
		tb.setBorder(new TitledBorder(null, "Danh s\u00E1ch h\u1EE3p \u0111\u1ED3ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(tb);

		JScrollPane scrollPane = new JScrollPane();
		tb.add(scrollPane);

		tableHD = new JTable();
		tableHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHD.getSelectedRow();
				if(e.getClickCount() == 1) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Chọn công nhân này để thêm hợp đồng", "Lấy mã công nhân", JOptionPane.YES_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						textMaCN.setText(tableHD.getValueAt(row, 7).toString()); 
					}
				}
			}
		});
		tableHD.setPreferredScrollableViewportSize(new Dimension(1200, 280));
		tableHD.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "1", "1", "1", "1", "1", "1", "1", "1", null},
				{"2", "2", "2", "2", "2", "2", "2", "2", "2", "3"},
				{"3", "3", "3", "3", "3", "3", "3", "3", "3", "3"},
				{"4", "4", "4", "4", "4", "4", "4", "4", "4", null},
			},
			new String[] {
				"STT", " M\u00E3 H\u1EE3p \u0111\u1ED3ng", "Ng\u00E0y k\u00FD", "Ng\u00E0y h\u1EBFt h\u1EA1n", "Lo\u1EA1i H\u0110", "T\u00EAn H\u0110", "Th\u1EDDi h\u1EA1n", "M\u00E3 c\u00F4ng nh\u00E2n", "H\u1ECD t\u00EAn", "Tr\u1EA1ng th\u00E1i h\u1EE3p \u0111\u1ED3ng"
			}
		));
		tableHD.getColumnModel().getColumn(0).setPreferredWidth(34);
		tableHD.getColumnModel().getColumn(1).setPreferredWidth(101);
		tableHD.getColumnModel().getColumn(2).setPreferredWidth(66);
		tableHD.getColumnModel().getColumn(5).setPreferredWidth(110);
		tableHD.getColumnModel().getColumn(6).setPreferredWidth(56);
		tableHD.getColumnModel().getColumn(9).setPreferredWidth(111);
//		tableColumnModel.getColumn(0).setPreferredWidth(10);
//		tableColumnModel.getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(tableHD);
		addDataTable();
		model.setRowCount(0);
		addDataTableConHan();
	}

	private List<String> addDuLieuCBBLoaiHopDong() {
		List<LoaiHopDong> list = loaiHopDongDAO.getAllLoaiHopDong();
		List<String> tenLoaiHopDong = new ArrayList<String>();
		list.forEach(e -> tenLoaiHopDong.add(e.getLoaiHopDongID()));
		return tenLoaiHopDong;
	}

	private void addDataTable() {
		model = (DefaultTableModel) tableHD.getModel();
		model.setRowCount(0);
		int stt = 1;
		List<HopDongLaoDong> list = hopDongLaoDongDAO.getAllHopDong();
		for (HopDongLaoDong hopDongLaoDong : list) {
			LoaiHopDong loaiHopDong = loaiHopDongDAO
					.getLoaiHopDongById(hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID());
			Nguoi congNhan = nguoiDAO.getNguoiByID(hopDongLaoDong.getNguoi().getNguoiID());
			model.addRow(new Object[] { stt, hopDongLaoDong.getHopDongID(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayKy()),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayHetHan()),
					hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID(), hopDongLaoDong.getLoaiHopDong().getTenLoai(),
					loaiHopDong.getThoiHan(), congNhan.getNguoiID(),
					nguoiDAO.getHoTenNguoiByID(hopDongLaoDong.getNguoi().getNguoiID()),
					compare2Date(hopDongLaoDong.getNgayHetHan()) == true ? "Hết hạn" : "Còn hạn" });
			stt++;
			
			if (compare2Date(hopDongLaoDong.getNgayHetHan()))
				listHetHan.add(hopDongLaoDong);
			else
				listConHan.add(hopDongLaoDong);
			
			model.setRowCount(0);
		}
	}

	private List<HopDongLaoDong> listTimKiemByID(String id) {
		List<HopDongLaoDong> list = hopDongLaoDongDAO.getHopDongLaoDongByNguoiID(id);
		return list;
	}

	private void addDataTableTimKiemID(List<HopDongLaoDong> list) {
		model = (DefaultTableModel) tableHD.getModel();
		model.setRowCount(0);
		int stt = 1;

		for (HopDongLaoDong hopDongLaoDong : list) {
			LoaiHopDong loaiHopDong = loaiHopDongDAO
					.getLoaiHopDongById(hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID());
			Nguoi congNhan = nguoiDAO.getNguoiByID(hopDongLaoDong.getNguoi().getNguoiID());
			model.addRow(new Object[] { stt, hopDongLaoDong.getHopDongID(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayKy()),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayHetHan()),
					hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID(), hopDongLaoDong.getLoaiHopDong().getTenLoai(),
					loaiHopDong.getThoiHan(), congNhan.getNguoiID(),
					nguoiDAO.getHoTenNguoiByID(hopDongLaoDong.getNguoi().getNguoiID()),
					compare2Date(hopDongLaoDong.getNgayHetHan()) == true ? "Hết hạn" : "Còn hạn" });
			stt++;
			
		}

	}

	private void addDataTableConHan() {
		model = (DefaultTableModel) tableHD.getModel();
		model.setRowCount(0);
		int stt = 1;

		for (HopDongLaoDong hopDongLaoDong : listConHan) {
			LoaiHopDong loaiHopDong = loaiHopDongDAO
					.getLoaiHopDongById(hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID());
			Nguoi congNhan = nguoiDAO.getNguoiByID(hopDongLaoDong.getNguoi().getNguoiID());
			model.addRow(new Object[] { stt, hopDongLaoDong.getHopDongID(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayKy()),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayHetHan()),
					hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID(), hopDongLaoDong.getLoaiHopDong().getTenLoai(),
					loaiHopDong.getThoiHan(), congNhan.getNguoiID(),
					nguoiDAO.getHoTenNguoiByID(hopDongLaoDong.getNguoi().getNguoiID()), "Còn hạn" });
			stt++;
		}
	}

	private void addDataTableHetHan() {
		model = (DefaultTableModel) tableHD.getModel();
		model.setRowCount(0);
		int stt = 1;

		for (HopDongLaoDong hopDongLaoDong : listHetHan) {
			LoaiHopDong loaiHopDong = loaiHopDongDAO
					.getLoaiHopDongById(hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID());
			Nguoi congNhan = nguoiDAO.getNguoiByID(hopDongLaoDong.getNguoi().getNguoiID());
			model.addRow(new Object[] { stt, hopDongLaoDong.getHopDongID(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayKy()),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(hopDongLaoDong.getNgayHetHan()),
					hopDongLaoDong.getLoaiHopDong().getLoaiHopDongID(), hopDongLaoDong.getLoaiHopDong().getTenLoai(),
					loaiHopDong.getThoiHan(), congNhan.getNguoiID(),
					nguoiDAO.getHoTenNguoiByID(hopDongLaoDong.getNguoi().getNguoiID()), "Hết hạn" });
			stt++;
		
		}
	}

	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}

	private boolean compare2Date(LocalDate ngayHetHan) {
		return (LocalDate.now().isAfter(ngayHetHan)) ? true : false;
	}

	private String taoSoTangDan(String number) {
		return String.format("%04d", Integer.parseInt(number) + 1);
	}

	private String soHienTai(String maLDH) {
		String ma = hopDongLaoDongDAO.getMaxHopDongIDBaoHiemSubstring(maLDH);
		if (ma == null)
			return "0000";
		else {
			String builder = new StringBuilder(ma).reverse().substring(0, 4);
			String number = new StringBuilder(builder).reverse().toString();
			return number;
		}

	}

	private boolean kiemTraMa() {
		if (cbbLoaiHD.getSelectedIndex() == -1)
			return false;
		else {
			String[] dateSplit = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()).split("-");
			String date = dateSplit[0] + dateSplit[1] + dateSplit[2].substring(2, 4);
			textMaHD.setText("HD" + cbbLoaiHD.getSelectedItem().toString() + date + taoSoTangDan(soHienTai(maLHD)));
		}
		return true;
	}

	private void lamMoi() {
		textTimKiem.setText("");
		textMaCN.setText("");
		textNgayHH.setText("");
		textNgayKy.setText("");
		textTenHD.setText("");
		textMaHD.setText("");
		textMaCongNhan.setText("");
		cbbLoaiHD.setSelectedIndex(-1);
	
	}

}
