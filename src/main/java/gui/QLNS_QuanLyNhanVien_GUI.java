package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;

import dao.ChucVuDAO;
import dao.NguoiDAO;
import dao.NhanVienDAO;
import dao.PhongBanDAO;
import entities.ChucVu;
import entities.Nguoi;
import entities.NhanVien;
import entities.PhongBan;
import enums.CapBac;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLNS_QuanLyNhanVien_GUI extends JPanel {

	private JPanel contentPane;
	private JTextField textMaNV;
	private JTextField textDiaChi;
	private JTextField textHoTen;
	private JTextField textEmail;
	private JTextField textSNKN;
	private JTextField textCMND;
	private JTextField textHSL = new JTextField();
	private JTextField textTimKiem;
	private JTable tableNhanVien;
	private JComboBox cBBPhongBan;
	private JComboBox cBBCapBac;
	private JComboBox cBBGioiTinh;
	private JComboBox cBBChucVu;
	private JDateChooser dateChooser;
	static DefaultTableModel model;
	public NhanVienDAO nhanVienDAO = new NhanVienDAO();
	public PhongBanDAO phongBanDAO = new PhongBanDAO();
	public ChucVuDAO chucVuDAO = new ChucVuDAO();
	public NguoiDAO nguoiDAO = new NguoiDAO();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField textSDT;
	private JTextField textLCB;
	private static int clickNutThem = 0;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnThem;
	private JButton btnSua;
	private List<NhanVien> list;

	public QLNS_QuanLyNhanVien_GUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel main = new JPanel();
		main.setBorder(
				new TitledBorder(null, "Nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(main);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		main.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Mã nhân viên: ");
		lblNewLabel.setPreferredSize(new Dimension(85, 14));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1.add(lblNewLabel);

		textMaNV = new JTextField();
		textMaNV.setEnabled(false);
		panel_1.add(textMaNV);
		textMaNV.setColumns(15);

		JButton btnTaoMa = new JButton("Tạo mã");
		btnTaoMa.setEnabled(false);
		btnTaoMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnTaoMa)) {
					if (!kiemTraMa())
						showMessage("Thông báo", "Chưa đủ dữ liệu, cần có phòng ban để tạo mã cho nhân viên");
				}
			}
		});
		panel_1.add(btnTaoMa);

		JPanel panel_1_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1_1);

		JLabel lblSCmnd = new JLabel("Số CMND: ");
		panel_1_1.add(lblSCmnd);
		lblSCmnd.setPreferredSize(new Dimension(70, 14));
		lblSCmnd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textCMND = new JTextField();
		textCMND.setEnabled(false);
		textCMND.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		panel_1_1.add(textCMND);
		textCMND.setColumns(15);

		JPanel panel_1_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1_2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1_2);

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setPreferredSize(new Dimension(130, 14));
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_2.add(lblChucVu);
		String[] dsChucVu = addDuLieuJCBBChucVu().toArray(String[]::new); // convert líst to String[]
		cBBChucVu = new JComboBox(dsChucVu);
		cBBChucVu.setEnabled(false);
		panel_1_2.add(cBBChucVu);
		cBBChucVu.setSelectedIndex(-1);
		String[] dsPhongBan = addDuLieuJCBBPhongBan().toArray(String[]::new); // convert líst to String[]

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);

		JLabel lblHSLng = new JLabel("Hệ số lương: ");
		panel_5.add(lblHSLng);
		lblHSLng.setPreferredSize(new Dimension(130, 14));
		lblHSLng.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_5.add(textHSL);

		textHSL.setEnabled(false);
		textHSL.setColumns(10);

		JPanel panel_2 = new JPanel();
		main.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_1_3 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_1_3.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_3);

		JLabel lblHTn = new JLabel("Họ tên: ");
		lblHTn.setPreferredSize(new Dimension(85, 14));
		lblHTn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_3.add(lblHTn);

		textHoTen = new JTextField();
		textHoTen.setEnabled(false);
		textHoTen.setColumns(15);
		panel_1_3.add(textHoTen);

		JPanel panel_1_1_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_1_1_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_1_1);

		JLabel lbliaCh = new JLabel("Đia chỉ: ");
		panel_1_1_1.add(lbliaCh);
		lbliaCh.setPreferredSize(new Dimension(70, 14));
		lbliaCh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textDiaChi = new JTextField();
		textDiaChi.setEnabled(false);
		panel_1_1_1.add(textDiaChi);
		textDiaChi.setColumns(15);

		JPanel panel_1_2_1 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_1_2_1.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_1_2_1);

		JLabel lblPhngBan = new JLabel("Phòng ban:");
		panel_1_2_1.add(lblPhngBan);
		lblPhngBan.setPreferredSize(new Dimension(130, 14));
		lblPhngBan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		cBBPhongBan = new JComboBox(dsPhongBan);
		cBBPhongBan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					textMaNV.setText("");
			}
		});
		cBBPhongBan.setEnabled(false);
		panel_1_2_1.add(cBBPhongBan);
		cBBPhongBan.setSelectedIndex(-1);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) panel_6.getLayout();
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_6);

		JLabel lblLCB = new JLabel("Lương cơ bản:");
		lblLCB.setPreferredSize(new Dimension(130, 14));
		lblLCB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_6.add(lblLCB);

		textLCB = new JTextField();
		textLCB.setEnabled(false);
		textLCB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		textLCB.setColumns(10);
		panel_6.add(textLCB);

		JPanel panel_2_1 = new JPanel();
		main.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 4, 0, 0));

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
		dateChooser.setEnabled(false);
		panel_1_3_1.add(dateChooser);

		JPanel panel_1_1_1_1 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_1_1_1_1.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_1_1_1_1);

		JLabel lblEmail = new JLabel("Email: ");
		panel_1_1_1_1.add(lblEmail);
		lblEmail.setPreferredSize(new Dimension(70, 14));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textEmail = new JTextField();
		textEmail.setEnabled(false);
		panel_1_1_1_1.add(textEmail);
		textEmail.setColumns(15);

		JPanel panel_1_2_1_1 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_1_2_1_1.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_1_2_1_1);

		JLabel lblSNmKinh = new JLabel("Số năm kinh nghiệm: ");
		panel_1_2_1_1.add(lblSNmKinh);
		lblSNmKinh.setPreferredSize(new Dimension(130, 14));
		lblSNmKinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textSNKN = new JTextField();
		textSNKN.setEnabled(false);
		panel_1_2_1_1.add(textSNKN);
		textSNKN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
					cBBCapBac.setSelectedIndex(-1);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}

			}
		});
		textSNKN.setColumns(10);
		String[] dsCapBac = { "THCS", "THPT", "Trung cấp nghề", "Cao đẳng", "Đại học" };

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_15 = (FlowLayout) panel_7.getLayout();
		flowLayout_15.setAlignment(FlowLayout.LEFT);
		panel_2_1.add(panel_7);

		JPanel panel_2_1_1 = new JPanel();
		main.add(panel_2_1_1);
		panel_2_1_1.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_1_3_1_1 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_1_3_1_1.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_1_3_1_1);

		JLabel lblGiiTnh = new JLabel("Giới tính: ");
		lblGiiTnh.setPreferredSize(new Dimension(85, 14));
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_3_1_1.add(lblGiiTnh);

		String[] gioiTinh = { "Nam", "Nữ" };
		cBBGioiTinh = new JComboBox(gioiTinh);
		cBBGioiTinh.setSelectedIndex(-1);
		cBBGioiTinh.setEnabled(false);
		panel_1_3_1_1.add(cBBGioiTinh);

		JPanel panel_1_1_1_1_1 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_1_1_1_1_1.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_1_1_1_1_1);

		JLabel lbSDT = new JLabel("SĐT");
		lbSDT.setPreferredSize(new Dimension(70, 14));
		lbSDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1_1_1_1_1.add(lbSDT);

		textSDT = new JTextField();
		textSDT.setEnabled(false);
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

		JPanel panel_1_2_1_1_1 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_1_2_1_1_1.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_1_2_1_1_1);

		JLabel lblCpBc = new JLabel("Cấp bậc: ");
		panel_1_2_1_1_1.add(lblCpBc);
		lblCpBc.setPreferredSize(new Dimension(130, 14));
		lblCpBc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		cBBCapBac = new JComboBox(dsCapBac);
		cBBCapBac.setEnabled(false);
		panel_1_2_1_1_1.add(cBBCapBac);
		cBBCapBac.setSelectedIndex(-1);
		cBBCapBac.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DecimalFormat df = new DecimalFormat("####0.00");
				if (e.getItem().equals("THCS") || e.getItem().equals("THPT")) {
					double heSoLuongTheoNam = 1.0
							+ (heSoCapBac(e.getItem().toString()) / 10) * Integer.parseInt(textSNKN.getText());
					if (Integer.parseInt(textSNKN.getText()) > 10)
						textHSL.setText("2.5");
					else
						textHSL.setText(df.format(heSoLuongTheoNam));
				}

				else if (e.getItem().equals("Trung cấp nghề")) {
					double heSoLuongTheoNam = 1.86
							+ (heSoCapBac(e.getItem().toString()) / 10) * Integer.parseInt(textSNKN.getText());
					if (Integer.parseInt(textSNKN.getText()) > 10)
						textHSL.setText("3.46");
					else
						textHSL.setText(df.format(heSoLuongTheoNam));
				}

				else if (e.getItem().equals("Cao đẳng")) {
					double heSoLuongTheoNam = 2.34
							+ (heSoCapBac(e.getItem().toString()) / 10) * Integer.parseInt(textSNKN.getText());
					if (Integer.parseInt(textSNKN.getText()) > 10)
						textHSL.setText("4.98");
					else
						textHSL.setText(df.format(heSoLuongTheoNam));
				}

				else if (e.getItem().equals("Đại học")) {
					double heSoLuongTheoNam = 4.40
							+ (heSoCapBac(e.getItem().toString()) / 10) * Integer.parseInt(textSNKN.getText().trim());
					if (Integer.parseInt(textSNKN.getText()) > 10)
						textHSL.setText("6.78");
					else
						textHSL.setText(df.format(heSoLuongTheoNam));
				}

			}
		});

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_16 = (FlowLayout) panel_8.getLayout();
		flowLayout_16.setAlignment(FlowLayout.LEFT);
		panel_2_1_1.add(panel_8);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_3.getLayout();
		flowLayout_13.setHgap(20);
		main.add(panel_3);

		JButton btnXemThongTin = new JButton("Xem thông tin");
		btnXemThongTin.setForeground(new Color(255, 255, 255));
		btnXemThongTin.setBackground(new Color(64, 128, 128));
		panel_3.add(btnXemThongTin);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnLamMoi))
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
							"Bạn có muốn mở khóa các trường để thêm nhân viên mới hay không?", "Thêm nhân viên",
							JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						moKhoaTextNhap();
						lamMoiTextField();
						btnThem.setText("Xác nhận");
						btnXoa.setEnabled(false);
						btnSua.setEnabled(false);
						btnTaoMa.setEnabled(true);
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM nhân viên");
						khoaTextNhap();
						lamMoiTextField();
						btnTaoMa.setEnabled(false);
					}

				} else if (e.getActionCommand().equals("Xác nhận")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI, "Xác nhận THÊM nhân viên này",
							"Xác nhận", JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (!kiemTraThem())
							showMessage("Thông báo lỗi", "Có lỗi khi thêm, mời kiểm tra lại");
						else {

							String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
							LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							Nguoi nguoi = new Nguoi(textMaNV.getText().trim(), textHoTen.getText().trim(), localDate,
									cBBGioiTinh.equals("Nam") ? true : false, textCMND.getText().trim(),
									textDiaChi.getText().trim(), textEmail.getText().trim(), textSDT.getText().trim(),
									true);

							NhanVien nhanVien = new NhanVien(textMaNV.getText().trim(),
									phongBanDAO.getPhongBanByTen(cBBPhongBan.getSelectedItem().toString().trim()),
									chucVuDAO.getChucVuByTen(cBBChucVu.getSelectedItem().toString().trim()),
									CapBac.getCapBat(cBBCapBac.getSelectedItem().toString().trim()),
									Integer.parseInt(textSNKN.getText().trim()),
									Double.parseDouble(textHSL.getText().trim().replace(",", ".")),
									Double.parseDouble(textLCB.getText().trim()));
							boolean result = nguoiDAO.addNguoi(nguoi);

							if (result) {
								boolean resultNV = nhanVienDAO.addNhanVien(nhanVien);
								showMessage("Thông báo", "Thêm thành công");
								khoaTextNhap();
								lamMoiTextField();
								btnThem.setText("Thêm");
								btnXoa.setEnabled(true);
								btnSua.setEnabled(true);
								btnTaoMa.setEnabled(false);
								loadDataTable();
							} else
								showMessage("Thông báo", "Thêm thất bại");
						}
					} else {
						showMessage("Thông báo", "Hủy bỏ chức năng THÊM nhân viên");
						btnThem.setText("Thêm");
						btnXoa.setEnabled(true);
						btnSua.setEnabled(true);
						khoaTextNhap();
						lamMoiTextField();
						btnTaoMa.setEnabled(false);
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
					int rowSelect = tableNhanVien.getSelectedRow();
					if (rowSelect == -1)
						showMessage("Thông báo", "Mời chọn dòng cần xóa");
					else {
						int confirm = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI, "Bạn có chắc muốn xóa",
								"Xóa nhân viên", JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							String maNguoi = tableNhanVien.getValueAt(rowSelect, 1).toString();
							nguoiDAO.xoaNguoi(maNguoi);
							loadDataTable();
							showMessage("Thông báo", "Xóa thành công");
						}

						else
							showMessage("Thông báo", "Bạn đã giữ lại nhân viên này");
					}

				}
			}
		});
		btnXoa.setBackground(new Color(64, 128, 128));
		panel_3.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Sửa")) {
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Bạn có muốn mở khóa các trường để sửa thông tin nhân viên hay không?", "Sửa nhân viên",
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
					int option = JOptionPane.showConfirmDialog(QLNS_Main_GUI.frame, "Xác nhận SỬA nhân viên này",
							"Xác nhận", JOptionPane.YES_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (!kiemTraThem())
							showMessage("Thông báo lỗi", "Có lỗi khi sửa, mời kiểm tra lại");
						else {

							String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
							LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							Nguoi nguoi = new Nguoi(textMaNV.getText().trim(), textHoTen.getText().trim(), localDate,
									cBBGioiTinh.equals("Nam") ? true : false, textCMND.getText().trim(),
									textDiaChi.getText().trim(), textEmail.getText().trim(), textSDT.getText().trim(),
									true);

							NhanVien nhanVien = new NhanVien(textMaNV.getText().trim(),
									phongBanDAO.getPhongBanByTen(cBBPhongBan.getSelectedItem().toString().trim()),
									chucVuDAO.getChucVuByTen(cBBChucVu.getSelectedItem().toString().trim()),
									CapBac.getCapBat(cBBCapBac.getSelectedItem().toString().trim()),
									Integer.parseInt(textSNKN.getText().trim()),
									Double.parseDouble(textHSL.getText().trim().replace(",", ".")),
									Double.parseDouble(textLCB.getText().trim()));
							boolean result = nguoiDAO.suaNguoi(nguoi);

							if (result) {
								boolean resultNV = nhanVienDAO.suaNhanVien(nhanVien);
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
						showMessage("Thông báo", "Hủy bỏ chức năng SỬA nhân viên");
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
		btnSua.setBackground(new Color(64, 128, 128));
		panel_3.add(btnSua);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\iconTimkiem.png"));
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm theo họ tên");
		panel_4.add(lblNewLabel_2);

		textTimKiem = new JTextField();
		textTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(textTimKiem)) {
					while(tableNhanVien.getRowCount() != 0)
						model.setRowCount(0);
					list = null;
					list = nhanVienDAO.getAllNhanVienTimKiemNangCao(textTimKiem.getText());
					int stt = 0;
					for(NhanVien nv: list) {
						model.addRow(new Object[] { stt, nv.getNguoiID(), nv.getHoTen(),
								DateTimeFormatter.ofPattern("dd-MM-yyyy").format(nv.getNgaySinh()), // format cho giống người Ziệt
																									// -.-
								nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.getSoCMND(), nv.getDiaChi(), nv.getEmail(),
								nv.getSoDienThoai(), nv.getChucVu().getTenChucVu(), nv.getPhongBan().getTenPhongBan(),
								nv.getSoNamKinhNghiem(), nv.getCapBac(), nv.getHeSoLuong(), convertLuongCoBan(nv.getLuongCoBan()),
								nv.isTrangThai() == true ? "Đang làm" : "Đã nghỉ việc" });
						stt++;
					}
				}
			}
		});
		panel_4.add(textTimKiem);
		textTimKiem.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		main.add(scrollPane);

		tableNhanVien = new JTable();
		tableNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableNhanVien.getSelectedRow();

				textMaNV.setText(tableNhanVien.getValueAt(rowSelect, 1).toString());
				textHoTen.setText(tableNhanVien.getValueAt(rowSelect, 2).toString());
				try {
					dateChooser.setDate(new SimpleDateFormat("dd-MM-yyyy")
							.parse(tableNhanVien.getValueAt(rowSelect, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				textSNKN.setText(tableNhanVien.getValueAt(rowSelect, 11).toString());
				cBBGioiTinh.setSelectedItem(tableNhanVien.getValueAt(rowSelect, 4).toString());
				textCMND.setText(tableNhanVien.getValueAt(rowSelect, 5).toString());
				textDiaChi.setText(tableNhanVien.getValueAt(rowSelect, 6).toString());
				textEmail.setText(tableNhanVien.getValueAt(rowSelect, 7).toString());
				textSDT.setText(tableNhanVien.getValueAt(rowSelect, 8).toString());
				cBBChucVu.setSelectedItem(tableNhanVien.getValueAt(rowSelect, 9).toString());
				cBBPhongBan.setSelectedItem(tableNhanVien.getValueAt(rowSelect, 10).toString());

				cBBCapBac.setSelectedItem(tableNhanVien.getValueAt(rowSelect, 12).toString());
				textHSL.setText(tableNhanVien.getValueAt(rowSelect, 13).toString());
				textLCB.setText(String.valueOf(Double.parseDouble(
						tableNhanVien.getValueAt(rowSelect, 14).toString().replace(".", "").replace(",", "."))));

			}
		});
		tableNhanVien.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "STT", "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số CMND", "Địa chỉ", "Email",
				"Số điện thoại", "Chức vụ", "Phòng ban", "Số năm kinh nghiệm", "Cấp bậc", "Hệ số lương", "Lương cơ bản",
				"Trạng thái" }));
		// thay chiều dài của mỗi cột nếu thấy chướng
		TableColumnModel tableColumnModel = tableNhanVien.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(10);
		tableColumnModel.getColumn(4).setPreferredWidth(20);
		tableColumnModel.getColumn(5).setPreferredWidth(50);
		tableColumnModel.getColumn(11).setPreferredWidth(30);
		tableColumnModel.getColumn(12).setPreferredWidth(30);
		tableColumnModel.getColumn(13).setPreferredWidth(30);
		scrollPane.setViewportView(tableNhanVien);
		loadDataTable();
	}

	private void loadDataTable() {
		int stt = 1;
		model = (DefaultTableModel) tableNhanVien.getModel();
		model.setRowCount(0);
		list = nhanVienDAO.getAllNhanVien();
		for (NhanVien nv : list) {
			model.addRow(new Object[] { stt, nv.getNguoiID(), nv.getHoTen(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(nv.getNgaySinh()), // format cho giống người Ziệt
																						// -.-
					nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.getSoCMND(), nv.getDiaChi(), nv.getEmail(),
					nv.getSoDienThoai(), nv.getChucVu().getTenChucVu(), nv.getPhongBan().getTenPhongBan(),
					nv.getSoNamKinhNghiem(), nv.getCapBac(), nv.getHeSoLuong(), convertLuongCoBan(nv.getLuongCoBan()),
					nv.isTrangThai() == true ? "Đang làm" : "Đã nghỉ việc" });
			stt++;
		}
	}

	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}

	private String convertLuongCoBan(double luongCoBan) { // số dài quá kh hiển thị hết thì xài hàm này
		DecimalFormat df = new DecimalFormat("###,###,###.0");
		BigDecimal decimal = new BigDecimal(luongCoBan);
		String luong = df.format(decimal);
		return luong;
	}

	private List<String> addDuLieuJCBBPhongBan() {
		List<PhongBan> list = phongBanDAO.getAllPhongBan();
		List<String> tenPhongBan = new ArrayList<String>();
		list.forEach(e -> tenPhongBan.add(e.getTenPhongBan()));
		return tenPhongBan;
	}

	private List<String> addDuLieuJCBBChucVu() {
		List<ChucVu> list = chucVuDAO.getAllChucVu();
		List<String> tenChucVu = new ArrayList<String>();
		list.forEach(e -> tenChucVu.add(e.getTenChucVu()));
		return tenChucVu;
	}

	private double heSoCapBac(String capBac) {
		return capBac == "THCS" ? (2.50 - 1.00)
				: capBac == "THPT" ? (2.50 - 1.00)
						: capBac == "Cao đẳng" ? (4.98 - 2.34)
								: capBac == "Trung cấp nghề" ? (3.46 - 1.86)
										: capBac == "Đại học" ? (6.78 - 4.40) : capBac == "" ? 0.00 : 0.00;
	}

	private boolean kiemTraMa() {
		if (cBBPhongBan.getSelectedIndex() == -1)
			return false;
		else {
			String date = LocalDate.now().toString().substring(2, 4);
			textMaNV.setText(date + "2" // date là 2 số cuối của năm vào làm, 2 là nhân viên
					+ phongBanDAO.getIDPhongBanByTen(cBBPhongBan.getSelectedItem().toString()) // lấy mã phòng ban theo
																								// tên
					+ taoSoTangDan(nhanVienDAO.getMaxNhanVienIDSubstring())); // tự động tăng
		}

		return true;
	}

	private String taoSoTangDan(String number) {
		return String.format("%04d", Integer.parseInt(number) + 1);
	}

	private void lamMoiTextField() {
		textCMND.setText("");
		textDiaChi.setText("");
		textEmail.setText("");
		textHoTen.setText("");
		textHSL.setText("");
		textMaNV.setText("");
		textSNKN.setText("");
		textTimKiem.setText("");
		textSDT.setText("");
		textLCB.setText("");
		dateChooser.setDate(null);
		cBBChucVu.setSelectedIndex(-1);
		cBBGioiTinh.setSelectedIndex(-1);
		cBBPhongBan.setSelectedIndex(-1);

	}

	private boolean kiemTraThem() {
		String regexSDT = "0[1-9]{1}[0-9]{8}"; // regex số điện thoại gồm 10 số bắt đầu bằng số 0
		String regexEmail = "[A-Za-z]{1}[A-Za-z0-9+_.-]+@(gmail.com)"; // regex email
		String regexCMND = "[0-9]{12}";
		System.out.println(textSDT.getText());
		if (textMaNV.getText().equals("") || textCMND.getText().equals("") || textDiaChi.getText().equals("")
				|| textEmail.getText().equals("") || textHoTen.getText().equals("") || textHSL.getText().equals("")
				|| textSNKN.getText().equals("") || dateChooser.getDate() == null || cBBCapBac.getSelectedIndex() == -1
				|| cBBPhongBan.getSelectedIndex() == -1 || cBBChucVu.getSelectedIndex() == -1
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

	private void moKhoaTextNhap() {
		textDiaChi.setEnabled(true);
		textHoTen.setEnabled(true);
		textEmail.setEnabled(true);
		textSNKN.setEnabled(true);
		textCMND.setEnabled(true);
		textSDT.setEnabled(true);
		textLCB.setEnabled(true);
		cBBPhongBan.setEnabled(true);
		cBBCapBac.setEnabled(true);
		cBBGioiTinh.setEnabled(true);
		cBBChucVu.setEnabled(true);
		dateChooser.setEnabled(true);
	}

	private void khoaTextNhap() {
		textDiaChi.setEnabled(false);
		textHoTen.setEnabled(false);
		textEmail.setEnabled(false);
		textSNKN.setEnabled(false);
		textCMND.setEnabled(false);
		textSDT.setEnabled(false);
		textLCB.setEnabled(false);
		cBBPhongBan.setEnabled(false);
		cBBPhongBan.setSelectedIndex(-1);
		cBBCapBac.setEnabled(false);
		cBBCapBac.setSelectedIndex(-1);
		cBBGioiTinh.setEnabled(false);
		cBBGioiTinh.setSelectedIndex(-1);
		cBBChucVu.setEnabled(false);
		cBBChucVu.setSelectedIndex(-1);
		dateChooser.setEnabled(false);
	}

}
