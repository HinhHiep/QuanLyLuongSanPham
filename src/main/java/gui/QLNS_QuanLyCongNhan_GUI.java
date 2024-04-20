package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.BoPhanDAO;
import dao.CongNhanDAO;
import dao.NguoiDAO;
import dao.PhongBanDAO;
import entities.BoPhan;
import entities.CongNhan;
import entities.Nguoi;
import entities.NhanVien;
import entities.PhongBan;
import enums.CapBac;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class QLNS_QuanLyCongNhan_GUI extends JPanel {

	private JPanel contentPane;
	private JTable tableCongNhan;
	static DefaultTableModel model;
	public CongNhanDAO congNhanDAO = new CongNhanDAO();
	public BoPhanDAO boPhanDAO = new BoPhanDAO();
	public NguoiDAO nguoiDAO = new NguoiDAO();
	private JDateChooser dateChooser;
	private JComboBox cBBGioiTinh;
	private JComboBox cBBBoPhan;
	private JTextField textHSLV;
	private JTextField textSDT;
	private JTextField textHoTen;
	private JTextField textDiaChi;
	private JTextField textEmail;
	private JTextField textMaCN;
	private JTextField textCMND;
	private JTextField textTimKiem;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXemThongTin;
	private JButton btnLamMoi;
	private List<CongNhan> list;

	public QLNS_QuanLyCongNhan_GUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel main = new JPanel();
		main.setBorder(
				new TitledBorder(null, "Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(main);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		main.add(panel);
		panel.setLayout(new GridLayout(0, 3, 5, 5));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Mã công nhân: ");
		lblNewLabel.setPreferredSize(new Dimension(85, 14));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1.add(lblNewLabel);

		textMaCN = new JTextField();
		textMaCN.setEditable(false);
		textMaCN.setEnabled(false);
		panel_1.add(textMaCN);
		textMaCN.setColumns(15);

		JButton btnTaoMa = new JButton("Tạo mã");
		btnTaoMa.setEnabled(false);
		btnTaoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTaoMa)) {
					if (!kiemTraMa())
						showMessage("Thông báo", "Chưa đủ dữ liệu, cần có bộ phận để tạo mã cho công nhân");
				}
			}
		});
		panel_1.add(btnTaoMa);

		JPanel panel_1_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1_1);

		JLabel lbliaCh = new JLabel("Đia chỉ: ");
		lbliaCh.setPreferredSize(new Dimension(70, 14));
		lbliaCh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1.add(lbliaCh);

		textDiaChi = new JTextField();
		textDiaChi.setColumns(15);
		panel_1_1.add(textDiaChi);

		JPanel panel_1_1_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1_1_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1_1_2);

		JLabel lblBoPhan = new JLabel("Bộ phận: ");
		lblBoPhan.setPreferredSize(new Dimension(100, 14));
		lblBoPhan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_2.add(lblBoPhan);

		String[] dsBoPhan = addDuLieuJCBBBoPhan().toArray(String[]::new);
		cBBBoPhan = new JComboBox(dsBoPhan);
		cBBBoPhan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					textMaCN.setText("");
			}
		});
		cBBBoPhan.setSelectedIndex(-1);
		panel_1_1_2.add(cBBBoPhan);

		JPanel panel_2 = new JPanel();
		main.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 5, 5));

		JPanel panel_1_3 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_1_3.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_3);

		JLabel lblHTn = new JLabel("Họ tên: ");
		lblHTn.setPreferredSize(new Dimension(85, 14));
		lblHTn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_3.add(lblHTn);

		textHoTen = new JTextField();
		textHoTen.setColumns(15);
		panel_1_3.add(textHoTen);

		JPanel panel_1_1_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_1_1_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_1_1);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setPreferredSize(new Dimension(70, 14));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_1.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(15);
		panel_1_1_1.add(textEmail);

		JPanel panel_1_1_1_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1_1_1_2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_1_1_2);

		JLabel lblHSLV = new JLabel("Hệ số làm việc:");
		lblHSLV.setPreferredSize(new Dimension(100, 14));
		lblHSLV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_1_2.add(lblHSLV);

		textHSLV = new JTextField();
		textHSLV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9')) && !(c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		textHSLV.setColumns(10);
		panel_1_1_1_2.add(textHSLV);

		JPanel panel_2_1 = new JPanel();
		main.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 3, 5, 5));

		JPanel panel_1_3_1 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_1_3_1.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_1_3_1);

		JLabel lblNgySinh = new JLabel("Ngày sinh: ");
		lblNgySinh.setPreferredSize(new Dimension(85, 14));
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_3_1.add(lblNgySinh);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		panel_1_3_1.add(dateChooser);

		JPanel panel_1_1_1_1 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_1_1_1_1.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_1_1_1_1);

		JLabel lblSCmnd = new JLabel("Số CMND: ");
		lblSCmnd.setPreferredSize(new Dimension(70, 14));
		lblSCmnd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_1_1.add(lblSCmnd);

		textCMND = new JTextField();
		textCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		textCMND.setColumns(15);
		panel_1_1_1_1.add(textCMND);

		JPanel panel_2_1_1 = new JPanel();
		main.add(panel_2_1_1);
		panel_2_1_1.setLayout(new GridLayout(0, 3, 5, 5));

		JPanel panel_1_3_1_1 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_1_3_1_1.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_1_3_1_1);

		JLabel lblGiiTnh = new JLabel("Giới tính: ");
		lblGiiTnh.setPreferredSize(new Dimension(85, 14));
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_3_1_1.add(lblGiiTnh);

		String[] dsGioiTinh = { "Nam", "Nữ" };
		cBBGioiTinh = new JComboBox(dsGioiTinh);
		cBBGioiTinh.setSelectedIndex(-1);
		panel_1_3_1_1.add(cBBGioiTinh);

		JPanel panel_1_1_1_1_1 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_1_1_1_1_1.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_1_1_1_1_1);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setPreferredSize(new Dimension(70, 14));
		lblSDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_1_1_1.add(lblSDT);

		textSDT = new JTextField();
		textSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		textSDT.setColumns(15);
		panel_1_1_1_1_1.add(textSDT);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_3.getLayout();
		flowLayout_13.setHgap(20);
		main.add(panel_3);

		btnXemThongTin = new JButton("Xem thông tin");
		btnXemThongTin.setForeground(new Color(255, 255, 255));
		btnXemThongTin.setBackground(new Color(64, 128, 128));
		panel_3.add(btnXemThongTin);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnLamMoi))
					lamMoiTextField();
			}
		});
		btnLamMoi.setBackground(new Color(64, 128, 128));
		panel_3.add(btnLamMoi);

		btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Thêm")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Bạn có muốn mở khóa các trường để thêm công nhân mới hay không?", "Thêm công nhân",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						moKhoaTextNhap();
						lamMoiTextField();
						btnThem.setText("Xác nhận");
						btnXoa.setEnabled(false);
						btnSua.setEnabled(false);
						btnTaoMa.setEnabled(true);
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM công nhân");
						khoaTextNhap();
						lamMoiTextField();
					}

				} else if (e.getActionCommand().equals("Xác nhận")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI, "Xác nhận THÊM công nhân này",
							"Xác nhận", JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (!kiemTraThem())
							showMessage("Thông báo lỗi", "Có lỗi khi thêm, mời kiểm tra lại");
						else {

							String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
							LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							Nguoi nguoi = new Nguoi(textMaCN.getText().trim(), textHoTen.getText().trim(), localDate,
									cBBGioiTinh.equals("Nam") ? true : false, textCMND.getText().trim(),
									textDiaChi.getText().trim(), textEmail.getText().trim(), textSDT.getText().trim(),
									true);

							CongNhan congNhan = new CongNhan(textMaCN.getText(),
									boPhanDAO.getBoPhanByTen(cBBBoPhan.getSelectedItem().toString()));
							boolean result = nguoiDAO.addNguoi(nguoi);

							if (result) {
								boolean resultCN = congNhanDAO.addCongNhan(congNhan);
								showMessage("Thông báo", "Thêm thành công");
								khoaTextNhap();
								lamMoiTextField();
								btnThem.setText("Thêm");
								btnXoa.setEnabled(true);
								btnSua.setEnabled(true);
								loadDataTable();
							} else
								showMessage("Thông báo", "Thêm thất bại");
						}
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM công nhân");
						btnThem.setText("Thêm");
						btnXoa.setEnabled(true);
						btnSua.setEnabled(true);
						btnTaoMa.setEnabled(false);
						khoaTextNhap();
						lamMoiTextField();
					}

				}
			}
		});
		btnThem.setBackground(new Color(64, 128, 128));
		panel_3.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnXoa)) {
					int rowSelect = tableCongNhan.getSelectedRow();
					if (rowSelect == -1)
						showMessage("Thông báo", "Mời chọn dòng cần xóa");
					else {
						int confirm = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI, "Bạn có chắc muốn xóa?",
								"Xóa công nhân", JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							String maNguoi = tableCongNhan.getValueAt(rowSelect, 1).toString();
							nguoiDAO.xoaNguoi(maNguoi);
							loadDataTable();
							showMessage("Thông báo", "Xóa thành công");
						}
						else
							showMessage("Thông báo", "Bạn đã giữ lại công nhân này");
					}

				}
			}
		});
		btnXoa.setBackground(new Color(64, 128, 128));
		panel_3.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Sửa")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Bạn có muốn mở khóa các trường để sửa thông tin công nhân hay không?", "Sửa công nhân",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						moKhoaTextNhap();
						btnTaoMa.setEnabled(false);
						btnSua.setText("Xác nhận");
						btnXoa.setEnabled(false);
						btnThem.setEnabled(false);
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng SỬA nhân viên");
						btnTaoMa.setEnabled(true);
						khoaTextNhap();
					}

				} else if (e.getActionCommand().equals("Xác nhận")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI, "Xác nhận SỬA công nhân này",
							"Xác nhận", JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (!kiemTraThem())
							showMessage("Thông báo lỗi", "Có lỗi khi sửa, mời kiểm tra lại");
						else {

							String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
							LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

							Nguoi nguoi = new Nguoi(textMaCN.getText().trim(), textHoTen.getText().trim(), localDate,
									cBBGioiTinh.equals("Nam") ? true : false, textCMND.getText().trim(),
									textDiaChi.getText().trim(), textEmail.getText().trim(), textSDT.getText().trim(),
									true);

							CongNhan congNhan = new CongNhan(textMaCN.getText(),
									boPhanDAO.getBoPhanByTen(cBBBoPhan.getSelectedItem().toString()));
							boolean result = nguoiDAO.suaNguoi(nguoi);

							if (result) {
								boolean resultCN = congNhanDAO.suaCongNhan(congNhan);
								showMessage("Thông báo", "Sửa thành công");
								khoaTextNhap();
								btnSua.setText("Sửa");
								btnXoa.setEnabled(true);
								btnThem.setEnabled(true);
								loadDataTable();
							} else
								showMessage("Thông báo", "Sửa thất bại");
						}
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng SỬA công nhân");
						btnSua.setText("Sửa");
						btnTaoMa.setEnabled(true);
						btnXoa.setEnabled(true);
						btnThem.setEnabled(true);
						khoaTextNhap();
						lamMoiTextField();
					}

				}
			}
		});
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBackground(new Color(64, 128, 128));
		panel_3.add(btnSua);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\iconTimkiem.png"));
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm theo tên");
		panel_4.add(lblNewLabel_2);

		textTimKiem = new JTextField();
		textTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(textTimKiem)) {
					while(tableCongNhan.getRowCount() != 0)
						model.setRowCount(0);
					list = null;
					list = congNhanDAO.getAllCongNhanTimKiemNangCao(textTimKiem.getText());
					int stt = 0;
					for (CongNhan cn : list) {
						model.addRow(new Object[] { stt, cn.getNguoiID(), cn.getHoTen(),
								DateTimeFormatter.ofPattern("dd-MM-yyyy").format(cn.getNgaySinh()), // format cho giống người Ziệt
																									// -.-
								cn.isGioiTinh() == true ? "Nam" : "Nữ", cn.getSoCMND(), cn.getDiaChi(), cn.getEmail(),
								cn.getSoDienThoai(), cn.getBoPhan().getTenBoPhan(),
								cn.isTrangThai() == true ? "Đang làm" : "Đã nghỉ việc" });
						stt++;
					}
				}
			}
		});
		panel_4.add(textTimKiem);
		textTimKiem.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		main.add(scrollPane);

		tableCongNhan = new JTable();
		tableCongNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableCongNhan.getSelectedRow();
				textMaCN.setText(tableCongNhan.getValueAt(rowSelect, 1).toString());
				textHoTen.setText(tableCongNhan.getValueAt(rowSelect, 2).toString());
				try {
					dateChooser.setDate(new SimpleDateFormat("dd-MM-yyyy")
							.parse(tableCongNhan.getValueAt(rowSelect, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				cBBGioiTinh.setSelectedItem(tableCongNhan.getValueAt(rowSelect, 4).toString());
				textCMND.setText(tableCongNhan.getValueAt(rowSelect, 5).toString());
				textDiaChi.setText(tableCongNhan.getValueAt(rowSelect, 6).toString());
				textEmail.setText(tableCongNhan.getValueAt(rowSelect, 7).toString());
				textSDT.setText(tableCongNhan.getValueAt(rowSelect, 8).toString());
				cBBBoPhan.setSelectedItem(tableCongNhan.getValueAt(rowSelect, 9).toString());
				textHSLV.setText(tableCongNhan.getValueAt(rowSelect, 10).toString());
			}
		});
		tableCongNhan.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "STT", "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số CMND", "Địa chỉ", "Email",
				"Số điện thoại", "B\u1ED9 ph\u1EADn", "Trạng thái" }));
		TableColumnModel tableColumnModel = tableCongNhan.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(10);
		tableColumnModel.getColumn(3).setPreferredWidth(20);
		tableColumnModel.getColumn(4).setPreferredWidth(20);
		tableColumnModel.getColumn(5).setPreferredWidth(50);
		tableColumnModel.getColumn(7).setPreferredWidth(120);
		tableColumnModel.getColumn(10).setPreferredWidth(30);
//		tableColumnModel.getColumn(11).setPreferredWidth(30);
		scrollPane.setViewportView(tableCongNhan);
		khoaTextNhap();
		loadDataTable();
	}

	private void loadDataTable() {
		int stt = 1;
		model = (DefaultTableModel) tableCongNhan.getModel();
		model.setRowCount(0);
		list = congNhanDAO.getAllCongNhan();
		for (CongNhan cn : list) {
			model.addRow(new Object[] { stt, cn.getNguoiID(), cn.getHoTen(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(cn.getNgaySinh()), // format cho giống người Ziệt
																						// -.-
					cn.isGioiTinh() == true ? "Nam" : "Nữ", cn.getSoCMND(), cn.getDiaChi(), cn.getEmail(),
					cn.getSoDienThoai(), cn.getBoPhan().getTenBoPhan(),
					cn.isTrangThai() == true ? "Đang làm" : "Đã nghỉ việc" });
			stt++;
		}
	}

	private String getTenBoPhan(String id) {
		List<BoPhan> listBoPhan = boPhanDAO.getAllBoPhan();
		for (BoPhan boPhan : listBoPhan) {
			if (id.contains(boPhan.getBoPhanID()))
				return boPhanDAO.getTenBoPhanByID(boPhan.getBoPhanID());
		}
		return "";
	}

	private List<String> addDuLieuJCBBBoPhan() {
		List<BoPhan> list = boPhanDAO.getAllBoPhan();
		List<String> tenBoPhan = new ArrayList<String>();
		list.forEach(e -> tenBoPhan.add(e.getTenBoPhan()));
		return tenBoPhan;
	}

	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}

	private String taoSoTangDan(String number) {
		return String.format("%04d", Integer.parseInt(number) + 1);
	}

	private boolean kiemTraMa() {
		if (cBBBoPhan.getSelectedIndex() == -1)
			return false;
		else {
			String date = LocalDate.now().toString().substring(2, 4);
			textMaCN.setText(date + "2" // date là 2 số cuối của năm vào làm, 2 là nhân viên
					+ boPhanDAO.getBoPhanIDByTen(cBBBoPhan.getSelectedItem().toString()) // lấy mã phòng ban theo
																							// tên
					+ taoSoTangDan(congNhanDAO.getMaxCongNhanIDSubstring())); // tự động tăng
		}

		return true;
	}

	private boolean kiemTraThem() {
		String regexSDT = "0[1-9]{1}[0-9]{8}"; // regex số điện thoại gồm 10 số bắt đầu bằng số 0
		String regexEmail = "[A-Za-z]{1}[A-Za-z0-9+_.-]+@(gmail.com)"; // regex email
		String regexCMND = "[0-9]{12}";
		System.out.println(textSDT.getText());
		if (textMaCN.getText().equals("") || textCMND.getText().equals("") || textDiaChi.getText().equals("")
				|| textEmail.getText().equals("") || textHoTen.getText().equals("") || textHSLV.getText().equals("")
				|| dateChooser.getDate() == null || cBBBoPhan.getSelectedIndex() == -1
				|| cBBGioiTinh.getSelectedIndex() == -1) {
			showMessage("Thông báo", "Không được để trống trường nào");
			return false;
		}

		if (textCMND.getText().trim().matches(regexCMND) == false) {
			showMessage("Thông báo", "CMND không hợp lệ");
			textCMND.requestFocus();
			return false;
		}

		if (textEmail.getText().trim().matches(regexEmail) == false) {
			showMessage("Thông báo", "Email không hợp lệ");
			textEmail.requestFocus();
			return false;
		}

		if (textSDT.getText().trim().matches(regexSDT) == false) {
			showMessage("Thông báo", "Số điện thoại không hợp lệ");
			textSDT.requestFocus();
			return false;
		}

		return true;
	}

	private void lamMoiTextField() {
		textCMND.setText("");
		textDiaChi.setText("");
		textEmail.setText("");
		textHoTen.setText("");
		textHSLV.setText("");
		textMaCN.setText("");
		textTimKiem.setText("");
		textSDT.setText("");
		dateChooser.setDate(null);
		cBBGioiTinh.setSelectedIndex(-1);

	}

	private void moKhoaTextNhap() {
		textDiaChi.setEnabled(true);
		textHoTen.setEnabled(true);
		textEmail.setEnabled(true);
		textCMND.setEnabled(true);
		textSDT.setEnabled(true);
		textHSLV.setEnabled(true);
		cBBGioiTinh.setEnabled(true);
		cBBBoPhan.setEnabled(true);
		dateChooser.setEnabled(true);
	}

	private void khoaTextNhap() {
		textDiaChi.setEnabled(false);
		textHoTen.setEnabled(false);
		textEmail.setEnabled(false);
		textCMND.setEnabled(false);
		textSDT.setEnabled(false);
		textHSLV.setEnabled(false);
		cBBGioiTinh.setEnabled(false);
		cBBGioiTinh.setSelectedIndex(-1);
		cBBBoPhan.setEnabled(false);
		cBBBoPhan.setSelectedIndex(-1);
		dateChooser.setEnabled(false);
	}

}
