package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BangPhanCongBoPhanDAO;
import dao.BangPhanCongCongNhanDAO;
import dao.BoPhanDAO;
import dao.CongDoanDAO;
import dao.CongNhanDAO;
//import dao.PhanCongBoPhanDAO;
//import dao.PhanCongCongNhan_DAO;
//import dao.PhanCongDAO;
import dao.SanPhamDAO;
import entities.BangPhanCongBoPhan;
import entities.BangPhanCongCongNhan;
import entities.BoPhan;
import entities.CongDoan;
import entities.CongNhan;
import entities.SanPham;

public class TTBP_PhanCongCongNhan_GUI extends JPanel {
	private JTable tableCongDoan;
	private JTable tableCongNhan;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTextField txtBoPhan;
	private JTextField txtUuTien;
	private JTextField txtMaCN;
	private JTextField txtNgayPhanCong;
	private JTextField txtSoLuongCanLam;
	private JTable tableDaPC;
	private JTable tableSanPham;
	private DefaultTableModel model;
	private DefaultTableModel cdmodel;
	private DefaultTableModel cnmodel;
	private SanPhamDAO qlsp;
	private Object[][] tablemodel;
	List<String> arrPCBPID;
	private Component panel_22;
	private BangPhanCongCongNhanDAO bangPhanCongCongNhanDAO = new BangPhanCongCongNhanDAO();
	private CongNhanDAO congNhanDAO = new CongNhanDAO();
	private BangPhanCongBoPhanDAO bangPhanCongBoPhanDAO = new BangPhanCongBoPhanDAO();
	private DefaultTableModel modelSanPham;
	private CongDoanDAO qlcd;
	private DefaultTableModel modelCongDoan;
	private DefaultComboBoxModel modelBoPhan;
	private ArrayList<BangPhanCongBoPhan> listbp;
	private JComboBox danhSachBoPhan;
	private JDateChooser dateChooser;
	private DefaultTableModel modelCongNhan;
	private CongNhanDAO qlcn;
	private JComboBox caLamViec;
	private Map<String, String> boPhanMap;
	private BangPhanCongCongNhanDAO qlpccn;
	private DefaultTableModel modelDaPC;
	private BoPhanDAO qlbp;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JComboBox comboCongDoan;
	private int flagDelete;
	private BangPhanCongBoPhanDAO qlpcbp;
	private JLabel txtCanPhanCong;

	public TTBP_PhanCongCongNhan_GUI() {
		flagDelete = 0;
		qlsp = new SanPhamDAO();
		qlcd = new CongDoanDAO();
		qlbp = new BoPhanDAO();
		qlcn = new CongNhanDAO();
		qlpcbp = new BangPhanCongBoPhanDAO();
		boPhanMap = new HashMap<>();
		qlpccn = new BangPhanCongCongNhanDAO();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel pPhanCong = new JPanel();
		add(pPhanCong);
		pPhanCong.setLayout(new BoxLayout(pPhanCong, BoxLayout.Y_AXIS));

		JPanel ppct = new JPanel();
		ppct.setBorder(new EmptyBorder(5, 5, 0, 0));
		pPhanCong.add(ppct);
		ppct.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel date = new JPanel();
		date.setBorder(new EmptyBorder(0, 0, 0, 10));
		FlowLayout flowLayout = (FlowLayout) date.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		ppct.add(date);

		JLabel lblNgy = new JLabel("Ngày:");
		lblNgy.setFont(new Font("Tahoma", Font.BOLD, 11));
		date.add(lblNgy);

		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(100, 20));
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
		date.add(dateChooser);

		caLamViec = new JComboBox();
		caLamViec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
				// Lấy giá trị boPhanID từ Map
				String boPhanID = boPhanMap.get(selectedTenBoPhan);
				Date selectedDate = dateChooser.getDate();

				// Chuyển đổi từ java.util.Date sang java.time.LocalDate
				LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(txtMaSP.getText(), boPhanID, localDate);
				modelDaPC.setRowCount(0);
				modelCongNhan.setRowCount(0);
			}
		});
		caLamViec.setModel(new DefaultComboBoxModel(new String[] { "Ca sáng", "Ca chiều", "Ca tối" }));
		ppct.add(caLamViec);

		// Lấy ngày hiện tại và đặt cho JDateChooser
		LocalDate currentDate = LocalDate.now();

		// Tạo một đối tượng DefaultComboBoxModel
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

		// Lấy danh sách giá trị từ phương thức getLocSanPham
		ArrayList<String> locSanPhamList = qlsp.getLocSanPham();

		// Thêm giá trị từ danh sách vào ComboBoxModel
		for (String value : locSanPhamList) {
			comboBoxModel.addElement(value);
		}
		comboBoxModel.setSelectedItem("Chưa hoàn thành");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/automatic.png"));

		// Thêm đường kẻ vào panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 0, 8, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 30, 0, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel label = new JLabel("Phân công");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panel_1.add(label);
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		// Tạo màu xanh dương đậm
		Color darkBlue = new Color(0, 0, 128);
		separator.setForeground(darkBlue);
		panel.add(separator);
		pPhanCong.add(panel);

		JPanel pOption = new JPanel();
		pPhanCong.add(pOption);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pOption.add(panel_2);

		JLabel lblNewLabel = new JLabel("Loại sản phẩm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		hienThiDanhSachLoaiSanPham();
		panel_2.add(comboBox);

		JPanel panel_3 = new JPanel();
		pOption.add(panel_3);

		JLabel lblNewLabel_1 = new JLabel("Bộ phận:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(lblNewLabel_1);

		danhSachBoPhan = new JComboBox();
		danhSachBoPhan.setModel(modelBoPhan = new DefaultComboBoxModel(new String[] {}));
		panel_3.add(danhSachBoPhan);
		hienThiDanhSachBoPhan();
		JPanel panel_23 = new JPanel();
		danhSachBoPhan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Lấy giá trị được chọn từ combobox
					String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
					// Lấy giá trị boPhanID từ Map
					String boPhanID = boPhanMap.get(selectedTenBoPhan);
					// Xử lý và hiển thị thông tin boPhan
					hienThiTextBoPhan(selectedTenBoPhan);
					Date selectedDate = dateChooser.getDate();

					// Chuyển đổi từ java.util.Date sang java.time.LocalDate
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(txtMaSP.getText(), boPhanID, localDate);
					modelDaPC.setRowCount(0);
					modelCongNhan.setRowCount(0);
				}
			}
		});
		pOption.add(panel_23);

		JButton buttonAuto = new JButton();
		URL imageUrl = this.getClass().getResource("/automatic.png");
		buttonAuto.setPreferredSize(new Dimension(60, 40));
		if (imageUrl != null) {
			// Tạo ImageIcon từ URL
			ImageIcon icon1 = new ImageIcon(imageUrl);
			// Đặt ảnh cho JButton
			buttonAuto.setIcon(icon1);
			panel_23.add(buttonAuto);
		} else {
			System.out.println("Không thể tìm thấy ảnh");
		}

		buttonAuto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					// txtSoLuongCanLam.requestFocus(); // Đưa con trỏ chuột đến ô nhập liệu
					//
					if (tableSanPham.getRowCount() == 0) {
						JOptionPane.showMessageDialog(buttonAuto, "Vui lòng chọn sản phẩm và công đoạn cần phân công");
						return;
					}
//					int tongSoLuongSP = Integer
//							.parseInt(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 2).toString());
//					int tongSoLuongDaPhanCong = Integer.parseInt(tableCongDoan
//							.getValueAt(tableCongDoan.getSelectedRow(), tableCongDoan.getColumnCount() - 2).toString());
//					int res = tongSoLuongSP - tongSoLuongDaPhanCong;
					int res = Integer.parseInt(txtCanPhanCong.getText());
					int row = tableCongDoan.getSelectedRow();
					String mapcbpid = listbp.get(row).getPhanCongID();
					int dapc = qlcd.getSoLuongDaPhanCongCuaCongDoanTheoNgayCaLamViec(mapcbpid);
					res -= dapc;
					int soLuong = middleWareAllowPhanCong(txtMaSP.getText(), Integer.parseInt(txtUuTien.getText()) - 1);
					if (soLuong == 0 && Integer.parseInt(txtUuTien.getText()) != 1) {
						JOptionPane.showMessageDialog(tableCongDoan,
								"Không thể phân công vì công đoạn trước chưa được hoàn thành");
						return;
					}
					if (Integer.parseInt(txtUuTien.getText()) != 1)
						if (res >= soLuong) {
							res = soLuong;
						}

					int n = tableCongNhan.getRowCount();
					if (n == 0) {
						JOptionPane.showMessageDialog(buttonAuto, "Không còn công nhân nào trống");
						return;
					}
					int option = JOptionPane.showConfirmDialog(tableCongDoan, "Xác nhận bạn muốn tự động phân công?",
							"Xác nhận", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
						return;
					}
					int per = res / n;
					txtSoLuongCanLam.setText(per + "");
					for (int i = 0; i < n; i++) {
						// xóa ràng buộc ô input
						txtSoLuongCanLam.setEditable(true);
						txtSoLuongCanLam.setFocusable(true);
						txtSoLuongCanLam.setText(per + "");
						int index = 0;
						// Lấy giá trị từ bảng và gán cho các trường/text field khác
						try {
							String maCongNhan = tableCongNhan.getValueAt(index, 2).toString();
							txtMaCN.setText(maCongNhan);
							phanCong(txtMaCN.getText(), txtMaCD.getText());
							res -= per;
						} catch (Exception ex) {
							System.out.println("Error from the server");
						}
					}
					if (res > 0) {
						String[] anArray;
						// allocates memory for 10 integers
						anArray = new String[res];
						for (int i = 0; i < res; i++) {
							String value = tableDaPC.getValueAt(i, 1).toString();
							anArray[i] = value;
						}
						qlpccn.chiaDeuPhanCongChoCongNhan(res, anArray);
						updateHienThiSauPhanCong();
					}
					JOptionPane.showMessageDialog(buttonAuto, "Đã phân công toàn bộ hoàn thành");
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});

		JLabel lblNewLabel_16 = new JLabel("Tự động phân công");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_23.add(lblNewLabel_16);

		JPanel panel_21 = new JPanel();
		pPhanCong.add(panel_21);

		JPanel pMain = new JPanel();
		pPhanCong.add(pMain);
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.X_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setAutoscrolls(true);
		panel_4.setAlignmentX(Component.LEFT_ALIGNMENT);
		pMain.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_25 = new JPanel();
		panel_4.add(panel_25);

		JLabel lblNewLabel_2 = new JLabel("Danh sách các sản phẩm");
		panel_25.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		panel_4.add(scrollPane_1);

		tableSanPham = new JTable();
		tableSanPham.setPreferredScrollableViewportSize(new Dimension(280, 250));

		tableSanPham.setModel(modelSanPham = new DefaultTableModel(tablemodel = new Object[][] {},
				new String[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng cần làm", "Số lượng đã hoàn thành" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Tất cả ô không thể chỉnh sửa
				return false;
			}
		});

		hienThiDanhSachSanPham();

		scrollPane_1.setViewportView(tableSanPham);
		JPanel panel_5 = new JPanel();
		pMain.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = e.getItem().toString();
				switch (selected) {
				case "Chưa hoàn thành":
					hienThiDanhSachSPChuaHoanThanh();
					break;
				case "Toàn bộ":
					hienThiDanhSachSanPham();
					break;
				case "Đã hoàn thành":
					hienThiDanhSachSPDaHoanThanh();
					break;
				default:
					hienThiDanhSachSPTheoLoai();
					break;
				}
			}
		});
		hienThiDanhSachSPChuaHoanThanh();

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\muiten.png"));
		panel_5.add(lblNewLabel_3);

		JPanel panel_6 = new JPanel();
		pMain.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JPanel panel_24 = new JPanel();
		panel_6.add(panel_24);

		JLabel lblNewLabel_4 = new JLabel("Danh sách công đoạn của sản phẩm");
		panel_24.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		comboCongDoan = new JComboBox();
		comboCongDoan
				.setModel(new DefaultComboBoxModel(new String[] { "Toàn bộ", "Đã hoàn thành", "Chưa hoàn thành" }));
		panel_24.add(comboCongDoan);
		comboCongDoan.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
				// Lấy giá trị boPhanID từ Map
				String boPhanID = boPhanMap.get(selectedTenBoPhan);
				Date selectedDate = dateChooser.getDate();

				// Chuyển đổi từ java.util.Date sang java.time.LocalDate
				LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(txtMaSP.getText(), boPhanID, localDate);
				modelCongNhan.setRowCount(0);
				modelDaPC.setRowCount(0);
			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_6.add(scrollPane_2);

		tableCongDoan = new JTable();
		tableCongDoan.setPreferredScrollableViewportSize(new Dimension(650, 250));
		scrollPane_2.setViewportView(tableCongDoan);
		tableCongDoan.setModel(modelCongDoan = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã công đoạn", "Tên công đoạn", "Ưu tiên", "Đã phân công", "Đã hoàn thành" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Tất cả ô không thể chỉnh sửa
				return false;
			}
		});

		JPanel panel_8 = new JPanel();
		pMain.add(panel_8);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\muiten.png"));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_8.add(lblNewLabel_3_1);

		JPanel panel_7 = new JPanel();
		pMain.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		JPanel panel_26 = new JPanel();
		panel_7.add(panel_26);

		JLabel lblNewLabel_5 = new JLabel("Danh sách công nhân");
		panel_26.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_7.add(scrollPane_3);

		tableCongNhan = new JTable();
		tableCongNhan.setPreferredScrollableViewportSize(new Dimension(650, 250));
		tableCongNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSoLuongCanLam.setEditable(true);
				txtSoLuongCanLam.setFocusable(true);
				txtSoLuongCanLam.requestFocus(); // Đưa con trỏ chuột đến ô nhập liệu
				hienThiTextCongNhan();
			}
		});
		scrollPane_3.setViewportView(tableCongNhan);
		tableCongNhan.setModel(modelCongNhan = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã bộ phận", "Tên bộ phận", "Mã công nhân", "Họ tên" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Tất cả ô không thể chỉnh sửa
				return false;
			}
		});
		tableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(113);

		JPanel chiTiet = new JPanel();
		chiTiet.setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Chi ti\u1EBFt", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pPhanCong.add(chiTiet);
		chiTiet.setLayout(new BoxLayout(chiTiet, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		chiTiet.add(top);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_10 = new JPanel();
		top.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));

		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);

		JLabel lblNewLabel_6 = new JLabel("Mã sản phẩm");
		lblNewLabel_6.setPreferredSize(new Dimension(95, 14));
		panel_11.add(lblNewLabel_6);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		panel_11.add(txtMaSP);
		txtMaSP.setColumns(20);

		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12);

		JLabel lblNewLabel_7 = new JLabel("Tên sản phẩm");
		lblNewLabel_7.setPreferredSize(new Dimension(95, 14));
		panel_12.add(lblNewLabel_7);

		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		panel_12.add(txtTenSP);
		txtTenSP.setColumns(20);

		JPanel panel_13 = new JPanel();
		panel_10.add(panel_13);

		JLabel lblNewLabel_8 = new JLabel("Mã công đoạn");
		lblNewLabel_8.setPreferredSize(new Dimension(95, 14));
		panel_13.add(lblNewLabel_8);

		txtMaCD = new JTextField();
		txtMaCD.setEditable(false);
		panel_13.add(txtMaCD);
		txtMaCD.setColumns(20);

		JPanel panel_9 = new JPanel();
		top.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		JPanel panel_14 = new JPanel();
		panel_14.setAlignmentX(Component.RIGHT_ALIGNMENT);
		FlowLayout flowLayout_4 = (FlowLayout) panel_14.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_14);

		JLabel lblNewLabel_9 = new JLabel("Tên công đoạn");
		lblNewLabel_9.setMaximumSize(new Dimension(150, 14));
		lblNewLabel_9.setPreferredSize(new Dimension(90, 15));
		panel_14.add(lblNewLabel_9);

		txtTenCD = new JTextField();
		txtTenCD.setEditable(false);
		panel_14.add(txtTenCD);
		txtTenCD.setColumns(20);

		JPanel panel_15 = new JPanel();
		panel_15.setAlignmentX(1.0f);
		FlowLayout flowLayout_5 = (FlowLayout) panel_15.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_15);

		JLabel lblNewLabel_10 = new JLabel("Bộ phận");
		lblNewLabel_10.setMaximumSize(new Dimension(100, 14));
		lblNewLabel_10.setPreferredSize(new Dimension(90, 15));
		panel_15.add(lblNewLabel_10);

		txtBoPhan = new JTextField();
		txtBoPhan.setEditable(false);
		panel_15.add(txtBoPhan);
		txtBoPhan.setColumns(20);
		//
		String selectedBoPhan = danhSachBoPhan.getSelectedItem().toString();

		// Xử lý và hiển thị thông tin boPhan
		hienThiTextBoPhan(selectedBoPhan);

		JPanel panel_16 = new JPanel();
		panel_16.setAlignmentX(1.0f);
		FlowLayout flowLayout_6 = (FlowLayout) panel_16.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_16);

		JLabel lblNewLabel_11 = new JLabel("Ưu tiên");
		lblNewLabel_11.setMaximumSize(new Dimension(100, 14));
		lblNewLabel_11.setPreferredSize(new Dimension(90, 15));
		panel_16.add(lblNewLabel_11);

		txtUuTien = new JTextField();
		txtUuTien.setEditable(false);
		panel_16.add(txtUuTien);
		txtUuTien.setColumns(20);

		JPanel panel_17 = new JPanel();
		top.add(panel_17);
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.Y_AXIS));

		JPanel panel_18 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_18.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_17.add(panel_18);

		JLabel lblNewLabel_12 = new JLabel("Mã công nhân:");
		lblNewLabel_12.setPreferredSize(new Dimension(135, 14));
		panel_18.add(lblNewLabel_12);

		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		panel_18.add(txtMaCN);
		txtMaCN.setColumns(20);

		JPanel panel_19 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_19.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_17.add(panel_19);

		JLabel lblNewLabel_13 = new JLabel("Ngày phân công");
		lblNewLabel_13.setPreferredSize(new Dimension(135, 14));
		panel_19.add(lblNewLabel_13);

		txtNgayPhanCong = new JTextField();
		txtNgayPhanCong.setEditable(false);
		panel_19.add(txtNgayPhanCong);
		txtNgayPhanCong.setColumns(20);
		hienThiTextNgay();

		JPanel panel_20 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_20.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_17.add(panel_20);

		JLabel lblNewLabel_14 = new JLabel("Số lượng cần làm");
		lblNewLabel_14.setPreferredSize(new Dimension(135, 14));
		panel_20.add(lblNewLabel_14);

		txtSoLuongCanLam = new JTextField();
		txtSoLuongCanLam.setEditable(false);
		panel_20.add(txtSoLuongCanLam);
		txtSoLuongCanLam.setColumns(20);

		JPanel panel_22 = new JPanel();
		top.add(panel_22);

		JPanel bottom = new JPanel();
		chiTiet.add(bottom);

		JButton btnLamMoi = new JButton("Làm mới");
		bottom.add(btnLamMoi);

		JButton btnThem = new JButton("Thêm");
		bottom.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int rowsCD = tableCongDoan.getRowCount();
					int row = tableCongDoan.getSelectedRow();
					if (rowsCD >= 2) {
						int soLuongDaPCAfter = Integer.parseInt(
								tableCongDoan.getValueAt(row + 1, tableCongDoan.getColumnCount() - 2).toString());
						System.out.println("Sau ne: " + soLuongDaPCAfter);
						if (row != rowsCD - 1 || soLuongDaPCAfter == 0) {
							JOptionPane.showMessageDialog(tableCongDoan,
									"Không thể xóa vì công đoạn sau đã được phân công !!!");
							return;
						}
					}
					int index = tableDaPC.getSelectedRow();
					if (index >= 0) {
						String phanCongID = tableDaPC.getValueAt(index, 1).toString();
						int option = JOptionPane.showConfirmDialog(tableCongDoan,
								"Bạn có chắc chắn muốn hủy toàn bộ bảng phân công?", "Xác nhận",
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION)
							hoanTacPhanCong(phanCongID);
					} else {
						JOptionPane.showMessageDialog(btnXoa, "Vui lòng chọn hàng cần xóa");
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		bottom.add(btnXoa);

		JButton btnXoaAll = new JButton("Xóa toàn bộ");
		btnXoaAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// xóa toàn bộ
				flagDelete = 1;
				try {
					int rowsCD = tableCongDoan.getRowCount();
					int row = tableCongDoan.getSelectedRow();
					if (rowsCD >= 2 && row != rowsCD - 1) {
						int soLuongDaPCAfter = Integer.parseInt(
								tableCongDoan.getValueAt(row + 1, tableCongDoan.getColumnCount() - 2).toString());
						if (row != rowsCD - 1 && soLuongDaPCAfter != 0) {
							JOptionPane.showMessageDialog(tableCongDoan,
									"Không thể xóa vì công đoạn sau đã được phân công !!!");
							return;
						}
					}
					if (tableDaPC.getRowCount() <= 0) {
						JOptionPane.showMessageDialog(btnXoaAll, "Hiện chưa tồn tại bản ghi nào");
						return;
					}
					int option = JOptionPane.showConfirmDialog(tableCongDoan,
							"Bạn có chắc chắn muốn hủy toàn bộ bảng phân công?", "Xác nhận",
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						int ms1 = tableCongDoan.getRowCount();
						while (tableDaPC.getRowCount() > 0) {
							int index = 0;
							String phanCongID = tableDaPC.getValueAt(index, 1).toString();
							hoanTacPhanCong(phanCongID);
						}
						int ms2 = tableCongDoan.getRowCount();
						System.out.println(ms1 + " - " + ms2);
						if (ms1 != ms2) {
							modelCongNhan.setRowCount(0);
						}

					}

				} catch (Exception ex) {
					modelCongNhan.setRowCount(0);

					System.out.println(ex);
				}
				flagDelete = 0;
			}
		});
		bottom.add(btnXoaAll);

		JButton btnUpdate = new JButton("Cập nhật phân công");
		bottom.add(btnUpdate);

		JPanel panel_27 = new JPanel();
		chiTiet.add(panel_27);

		JPanel panel_28 = new JPanel();
		panel_27.add(panel_28);

		txtTim = new JTextField();
		txtTim.setColumns(10);
		panel_28.add(txtTim);

		JButton btnTim = new JButton("Tìm phân công theo mã công nhân");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maCN = txtTim.getText();
				if (maCN.isEmpty()) {
					JOptionPane.showMessageDialog(tableCongDoan, "Vui lòng nhập chính xác");
					return;
				}

				// Tìm hàng có giá trị cột "Mã công nhân" bằng giá trị nhập vào
				int rowCount = tableDaPC.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					String maCongNhan = tableDaPC.getValueAt(i, 6).toString();
					if (maCN.equals(maCongNhan)) {
						// Chọn hàng tìm thấy
						tableDaPC.setRowSelectionInterval(i, i);

						// Cuộn đến hàng tìm thấy
						tableDaPC.scrollRectToVisible(tableDaPC.getCellRect(i, 0, true));
						return; // Kết thúc vòng lặp khi đã tìm thấy
					}
				}

				// Nếu không tìm thấy, thông báo
				JOptionPane.showMessageDialog(tableDaPC, "Không tìm thấy công nhân có mã: " + maCN);
			}
		});
		panel_28.add(btnTim);

		JButton btnfull = new JButton("Chấm toàn bộ hoàn thành");
		btnfull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tableDaPC.getRowCount(); i++) {
					tableDaPC.setValueAt(tableDaPC.getValueAt(i, tableDaPC.getColumnCount() - 2), i,
							tableDaPC.getColumnCount() - 1);
				}
			}
		});
		panel_27.add(btnfull);

		JButton btnHuySoLuong = new JButton("Khôi phục số lượng ban đầu");
		btnHuySoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = modelDaPC.getRowCount();
				int columns = tableDaPC.getColumnCount();
				for (int i = 0; i < n; i++) {
					tableDaPC.setValueAt(0, i, columns - 1);
				}
			}
		});
		panel_27.add(btnHuySoLuong);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//
					if (tableDaPC.getRowCount() <= 0) {
						JOptionPane.showMessageDialog(btnfull, "Không tồn tại bản ghi nào");
						return;
					}
					int option = JOptionPane.showConfirmDialog(tableCongDoan,
							"Toàn bộ cập nhật sẽ được luu, bạn có chắc chắn không?", "Xác nhận",
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						int rows = modelCongDoan.getRowCount();
						int index = tableCongDoan.getSelectedRow();
						capNhatSoLuongLamDuocALL();
////						// check nè
//						int mucUuTienLastOfSanPham = qlsp.getUuTienLasted(txtMaSP.getText());
//						int mucUuTien = Integer.parseInt(tableCongDoan.getValueAt(index, 2).toString());
//						// check nè
//						if (mucUuTienLastOfSanPham == mucUuTien) {
//							boolean isHT = kiemTraHoanThanh(txtMaSP.getText());
//							if (isHT) {
//								qlsp.updateHoanThanh(txtMaSP.getText());
//								hienThiDanhSachSPChuaHoanThanh();
//								JOptionPane.showMessageDialog(tableCongDoan,
//										"Sản phẩm của bạn sắp hoàn thành, hãy cập nhật nào ><");
//								updateHienThiSauPhanCong();
//								return;
//							}
//						}
						//
						updateHienThiSauPhanCong();

						if (modelCongDoan.getRowCount() != rows) {
							modelDaPC.setRowCount(0);
							hienThiTextCongDoan();
							updateHienThiSauPhanCong();
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});

		JPanel pFooter = new JPanel();
		pFooter.setPreferredSize(new Dimension(10, 500));
		FlowLayout flowLayout_10 = (FlowLayout) pFooter.getLayout();
		flowLayout_10.setVgap(10);
		flowLayout_10.setHgap(0);

		add(pFooter);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1200, 250));
		scrollPane.setAutoscrolls(true);
		scrollPane.setEnabled(false);
		pFooter.add(scrollPane);

		tableDaPC = new JTable();

		tableDaPC.setPreferredScrollableViewportSize(new Dimension(1200, 300));
		tableDaPC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(tableDaPC);
		tableDaPC.setAlignmentX(Component.LEFT_ALIGNMENT);
		tableDaPC.setSurrendersFocusOnKeystroke(true);
		tableDaPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDaPC.setModel(modelDaPC = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã phân công", "Tên sản phẩm", "Tên công đoạn", "Ưu tiên", "Tên bộ phận",
						"Mã công nhân", "Họ tên", "Số lượng phân công", "Số lượng hoàn thành" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Chỉ cột cuối cùng có thể chỉnh sửa
				return column == getColumnCount() - 1;
			}
		});
		tableDaPC.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableDaPC.getColumnModel().getColumn(5).setPreferredWidth(64);
		tableDaPC.getColumnModel().getColumn(6).setPreferredWidth(15);

		SanPhamDAO spdao = new SanPhamDAO();
		spdao.getSanPham();

		comboCongDoan.setSelectedItem("Chưa hoàn thành");

		JLabel labelCanPhanCong = new JLabel("Số lượng cần phân công: ");
		labelCanPhanCong.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_24.add(labelCanPhanCong);

		txtCanPhanCong = new JLabel("");
		txtCanPhanCong.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_24.add(txtCanPhanCong);
		dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					// Xử lý khi ngày tháng năm được thay đổi
					// Lấy ngày hôm nay
					LocalDate today = LocalDate.now();
					if ("date".equals(evt.getPropertyName())) {
						// Lấy ngày từ JDateChooser
						Date selectedDate = dateChooser.getDate();

						// Chuyển đổi từ java.util.Date sang java.time.LocalDate
						LocalDate chosenDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

						// So sánh ngày được chọn với ngày hôm nay
						if (!chosenDate.equals(today)) {
							// Xử lý khi ngày được chọn khác ngày hôm nay
							btnXoa.setEnabled(false);
							btnThem.setEnabled(false);
							btnXoaAll.setEnabled(false);
							buttonAuto.setEnabled(false);
							btnUpdate.setEnabled(false);
							btnfull.setEnabled(false);
							btnHuySoLuong.setEnabled(false);
						}
					}
					hienThiTextNgay();
					clearState();
				}
			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearState();
			}
		});

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableCongNhan.getSelectedRow() >= 0) {
					int soLuong = middleWareAllowPhanCong(txtMaSP.getText(), Integer.parseInt(txtUuTien.getText()) - 1);
					int soLuongPhan = Integer.parseInt(txtSoLuongCanLam.getText());
					if ((soLuong > 0 && soLuongPhan <= soLuong) || Integer.parseInt(txtUuTien.getText()) == 1) {
						phanCong(txtMaCN.getText(), txtMaCD.getText());
					} else {
						JOptionPane.showMessageDialog(tableCongDoan,
								"Không thể phân công vì công đoạn trước chưa được hoàn thành");
					}
				} else {
					JOptionPane.showMessageDialog(tableCongDoan, "Vui lòng nhập số lượng phân công");
				}
			}
		});

		tableSanPham.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				// TODO Auto-generated method stub
				int selectedRow = tableSanPham.getSelectedRow();

				if (selectedRow != -1) {
					modelCongNhan.setRowCount(0);
					modelDaPC.setRowCount(0);
					// Lấy giá trị của cột "Mã sản phẩm" từ modelSanPham
					String maSanPham = String.valueOf(modelSanPham.getValueAt(selectedRow, 0));
					hienThiTextSanPham();
					// Gọi hàm hiển thị danh sách công đoạn
					String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();

					// Lấy giá trị boPhanID từ Map
					String boPhanID = boPhanMap.get(selectedTenBoPhan);
					Date selectedDate = dateChooser.getDate();

					// Chuyển đổi từ java.util.Date sang java.time.LocalDate
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(maSanPham, boPhanID, localDate);
				}

			}

		});
		tableCongDoan.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableCongDoan.getSelectedRow();
				String pcbpid = listbp.get(selectedRow).getPhanCongID();
				int soLuongCanPhanCong = qlpcbp.getPhanCongBoPhanTheoID(pcbpid).getSoLuong();
				txtCanPhanCong.setText(soLuongCanPhanCong + "");
				// Kiểm tra xem có hàng được chọn hay không
				if (selectedRow != -1) {
					hienThiTextCongDoan();
					hienThiDanhSachCongNhanChuaDuocPhanCong(txtMaCD.getText());
					String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
					// Lấy giá trị boPhanID từ Map
					String boPhanID = boPhanMap.get(selectedTenBoPhan);
					Date selectedDate = dateChooser.getDate();

					// Chuyển đổi java.util.Date thành LocalDate
					Instant instant = selectedDate.toInstant();
					LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
					hienThiDanhSachCongNhanDaPhanCong(txtMaCD.getText(), caLamViec.getSelectedItem().toString(),
							boPhanID, localDate);
				}

			}
		});
	}

	private String getNgayHienTai() {
		LocalDateTime now = LocalDateTime.now();

		// Định dạng ngày và giờ thành chuỗi
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		// In ra chuỗi đã định dạng
		return formattedDateTime;
	}

	public void clearState() {
		txtSoLuongCanLam.setEditable(false);
		txtSoLuongCanLam.setFocusable(false);
		tableSanPham.clearSelection();
		tableCongDoan.clearSelection();
		tableCongNhan.clearSelection();
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtMaCD.setText("");
		txtTenCD.setText("");
		txtBoPhan.setText("");
		txtUuTien.setText("");
		txtMaCN.setText("");
		txtNgayPhanCong.setText("");
		txtSoLuongCanLam.setText("");
		DefaultTableModel temp1 = (DefaultTableModel) tableCongDoan.getModel();
		temp1.setRowCount(0);
		DefaultTableModel temp2 = (DefaultTableModel) tableCongNhan.getModel();
		temp2.setRowCount(0);
		DefaultTableModel model = (DefaultTableModel) tableDaPC.getModel();
		// Xóa tất cả các dòng hiện tại trong table_3 (nếu có)
		model.setRowCount(0);
	}

	private void hienThiDanhSachSanPham() {
		// Lấy danh sách sản phẩm từ DAO
		List<SanPham> listSanPham = qlsp.getSanPham();

		// Xóa dữ liệu hiện tại trong table model
		modelSanPham.setRowCount(0);

		// Thêm dữ liệu từ danh sách sản phẩm vào table model
		for (SanPham sanPham : listSanPham) {
			int soLuonght = qlsp.getSoLuongHoanThanh(sanPham.getSanPhamID());
			Object[] row = { sanPham.getSanPhamID(), sanPham.getTenSanPham(), sanPham.getSoLuong(), soLuonght };
			modelSanPham.addRow(row);
		}
	}

	private void hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(String maSanPham, String maBoPhan, LocalDate date) {
		try {
			// Giả sử bạn đã có đối tượng LocalDate là localDate

			int day = date.getDayOfMonth();
			int month = date.getMonthValue();
			int year = date.getYear();

			// Gọi hàm lấy danh sách công đoạn từ DAO
			ArrayList<BangPhanCongBoPhan> dsCongDoan = qlcd.layDSPhanCongBoPhanTheoMaSPBP(maSanPham, maBoPhan,
					caLamViec.getSelectedItem().toString(), day, month, year);
			// Xóa dữ liệu hiện tại của modelCongDoan
			modelCongDoan.setRowCount(0);
			listbp = new ArrayList<BangPhanCongBoPhan>();
			// Thêm dữ liệu mới vào modelCongDoan
			for (BangPhanCongBoPhan pcbp : dsCongDoan) {
				CongDoan congDoan = pcbp.getCongDoan();
				String maCongDoan = congDoan.getCongDoanID();
				String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
				// Lấy giá trị boPhanID từ Map
				String boPhanID = boPhanMap.get(selectedTenBoPhan);
				int soLuongDaPhanCong = qlcd.getSoLuongDaPhanCongCuaCongDoanTheoNgayCaLamViec(pcbp.getPhanCongID());

				int soLuongDaHoanThanh = qlcd.laySoLuongHoanThanhCuaCongDoanTheoNgayCaLamViec(pcbp.getPhanCongID(),
						pcbp.getCaLamViec(), day, month, year);

				Object[] row = { congDoan.getCongDoanID(), congDoan.getTenCongDoan(), congDoan.getMucUuTien(),
						soLuongDaPhanCong, soLuongDaHoanThanh /*
																 * Các trường khác cần hiển thị
																 */ };
				String value = comboCongDoan.getSelectedItem().toString();
				int size = row.length;
				if (value.equals("Toàn bộ")) {
					modelCongDoan.addRow(row);
					listbp.add(pcbp);
				} else if (value.equals("Chưa hoàn thành")) {
					int index = tableSanPham.getSelectedRow();
					int columns = tableSanPham.getColumnCount();
					if (!row[size - 1].toString().equals(row[size - 2].toString())
							|| row[size - 2].toString().equals("0")) {
						modelCongDoan.addRow(row);
						listbp.add(pcbp);
					}
				} else {
					int index = tableSanPham.getSelectedRow();
					int columns = tableSanPham.getColumnCount();
					if (row[size - 1].toString().equals(row[size - 2].toString())
							&& !row[size - 1].toString().equals("0")) {
						modelCongDoan.addRow(row);
						listbp.add(pcbp);

					}
				}
			}
			modelDaPC.setRowCount(0);
//			if (tableCongDoan.getSelectedRow() >= 0) {
//				modelCongNhan.setRowCount(0);
//			}
			// Thông báo cho modelCongDoan rằng dữ liệu đã thay đổi
			modelCongDoan.fireTableDataChanged();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void hienThiTextSanPham() {
		// Lấy index hàng đã được chọn
		int selectedRow = tableSanPham.getSelectedRow();
		// Kiểm tra xem có hàng được chọn hay không
		if (selectedRow != -1) {
			String maSanPham = String.valueOf(modelSanPham.getValueAt(selectedRow, 0));
			String tenSanPham = String.valueOf(modelSanPham.getValueAt(selectedRow, 1));
			String soLuong = String.valueOf(modelSanPham.getValueAt(selectedRow, 2));
			txtMaSP.setText(maSanPham);
			txtTenSP.setText(tenSanPham);
		}

	}

	private void hienThiDanhSachBoPhan() {
		// Gọi hàm lấy danh sách bộ phận từ DAO
		ArrayList<BoPhan> dsBoPhan = (ArrayList<BoPhan>) qlbp.getAllBoPhan();

		// Tạo một Map để lưu trữ ánh xạ giữa tên và boPhanID
		// Tạo danh sách tên bộ phận để đưa vào JComboBox
		ArrayList<String> tenBoPhanList = new ArrayList<>();

		for (BoPhan boPhan : dsBoPhan) {
			String boPhanID = boPhan.getBoPhanID();
			String tenBoPhan = boPhan.getTenBoPhan();

			boPhanMap.put(tenBoPhan, boPhanID);
			tenBoPhanList.add(tenBoPhan);
		}

		// Thiết lập model cho JComboBox sử dụng danh sách tên bộ phận
		danhSachBoPhan.setModel(new DefaultComboBoxModel<>(tenBoPhanList.toArray(new String[0])));

		// Thiết lập sự kiện cho JComboBox để lấy giá trị khi thay đổi
		danhSachBoPhan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Lấy tên bộ phận được chọn
					String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
					// Lấy giá trị boPhanID từ Map
					String boPhanID = boPhanMap.get(selectedTenBoPhan);
					// Lấy ngày từ JDateChooser
					Date selectedDate = dateChooser.getDate();

					// Chuyển đổi từ java.util.Date sang java.time.LocalDate
					LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(txtMaSP.getText(), boPhanID, localDate);
					hienThiDanhSachCongNhanChuaDuocPhanCong(txtMaCD.getText());
				}
			}
		});
	}

	private void hienThiTextCongDoan() {
		int selectedRow = tableCongDoan.getSelectedRow();
		String maCongDoan = String.valueOf(modelCongDoan.getValueAt(selectedRow, 0));
		String tenCongDoan = String.valueOf(modelCongDoan.getValueAt(selectedRow, 1));
		String mucUuTien = String.valueOf(modelCongDoan.getValueAt(selectedRow, 2));

		// Các trường khác cần hiển thị

		// Hiển thị thông tin công đoạn lên các JTextField hoặc JTextArea tương ứng
		txtMaCD.setText(maCongDoan);
		txtTenCD.setText(tenCongDoan);
		txtUuTien.setText(mucUuTien);

	}

	private void hienThiTextNgay() {
		// Lấy ngày từ JDateChooser
		java.util.Date selectedDate = dateChooser.getDate();

		// Định dạng ngày tháng năm
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		// Chuyển đổi thành chuỗi và hiển thị lên JTextField hoặc JTextArea tương ứng
		String ngayPhanCong = dateFormat.format(selectedDate);
		txtNgayPhanCong.setText(ngayPhanCong);
	}

	private void hienThiTextBoPhan(String selectedBoPhan) {
		// Ở đây, bạn có thể xử lý và hiển thị thông tin boPhan
		// Ví dụ: set text cho một JTextField
		txtBoPhan.setText(selectedBoPhan);
	}

	private void hienThiDanhSachCongNhanChuaDuocPhanCong(String maCongDoan) {
		int index = tableCongDoan.getSelectedRow();
		if (index >= 0) {
			// Lấy ngày từ JDateChooser
			Date selectedDate = dateChooser.getDate();

			// Chuyển đổi java.util.Date thành LocalDate
			Instant instant = selectedDate.toInstant();
			LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			// // Gọi hàm lấy danh sách công nhân từ DAO
			String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();

			// Lấy giá trị boPhanID từ Map
			String boPhanID = boPhanMap.get(selectedTenBoPhan);
			ArrayList<CongNhan> dsCongNhan = qlcn.getCongNhanChuaDuocPhanCong(boPhanID, localDate,
					caLamViec.getSelectedItem().toString(), maCongDoan);

			// Xóa dữ liệu hiện tại của modelCongNhan
			modelCongNhan.setRowCount(0);

			// Thêm dữ liệu mới vào modelCongNhan
			for (CongNhan congNhan : dsCongNhan) {
				Object[] row = { congNhan.getBoPhan().getBoPhanID(), congNhan.getBoPhan().getTenBoPhan(),
						congNhan.getNguoiID(), congNhan.getHoTen() };
				modelCongNhan.addRow(row);
			}

			// Thông báo cho modelCongNhan rằng dữ liệu đã thay đổi
			modelCongNhan.fireTableDataChanged();
		}
	}

	private void hienThiDanhSachCongNhanDaPhanCong(String maCD, String caLamViec, String maBoPhan, LocalDate ngay) {
		try {
			// if (tableCongDoan.getSelectedRow() < 0) {
//				return;
//			}
			// Gọi hàm từ DAO để lấy danh sách công nhân đã được phân công
			ArrayList<BangPhanCongCongNhan> dsPhanCong = qlpccn.getDanhSachCongNhanDaDuocPhanCong(maCD, caLamViec,
					maBoPhan, ngay);

			// Hiển thị danh sách công nhân
			DefaultTableModel model = (DefaultTableModel) tableDaPC.getModel();
			model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

			int stt = 1; // Số thứ tự
			for (BangPhanCongCongNhan phanCong : dsPhanCong) {
				Object[] rowData = { stt++, phanCong.getPhanCongID(), txtTenSP.getText(), txtTenCD.getText(),
						txtUuTien.getText(), phanCong.getCongNhan().getBoPhan().getTenBoPhan(),
						phanCong.getCongNhan().getNguoiID(), phanCong.getCongNhan().getHoTen(),
						phanCong.getSoLuongPhanCong(), phanCong.getSoLuongHoanThanh()
						// Thêm các thông tin khác tương ứng với cột của bảng
						// Ví dụ: phanCong.getCongNhan().getHoTen(),
						// phanCong.getSoLuongPhanCong(),
						// phanCong.getSoLuongHoanThanh()
				};

				model.addRow(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void phanCong(String maCN, String maCD) {
		BangPhanCongCongNhan item = customBangPhanCong();
		Date selectedDate = dateChooser.getDate();
		// Chuyển đổi java.util.Date thành LocalDate
		Instant instant = selectedDate.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		qlpccn.themPhanCongCongNhan(item, localDate);
		updateHienThiSauPhanCong();
	}

	private void updateHienThiSauPhanCong() {
		txtSoLuongCanLam.setEditable(false);
		txtSoLuongCanLam.setFocusable(false);
		txtMaCN.setText("");
		txtSoLuongCanLam.setText("");
		Date selectedDate = dateChooser.getDate();
		// Chuyển đổi java.util.Date thành LocalDate
		Instant instant = selectedDate.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		// // Gọi hàm lấy danh sách công nhân từ DAO
		String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
		// Lấy giá trị boPhanID từ Map
		String boPhanID = boPhanMap.get(selectedTenBoPhan);
		updateCongDoanSauPhanCong();
		hienThiDanhSachCongNhanChuaDuocPhanCong(txtMaCD.getText());
		hienThiDanhSachCongNhanDaPhanCong(txtMaCD.getText(), caLamViec.getSelectedItem().toString(), boPhanID,
				localDate);
	}

	private void updateCongDoanSauPhanCong() {
		int index = tableCongDoan.getSelectedRow();
		if (index >= 0) {
			String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
			// Lấy giá trị boPhanID từ Map
			String boPhanID = boPhanMap.get(selectedTenBoPhan);
			Date selectedDate = dateChooser.getDate();

			// Chuyển đổi từ java.util.Date sang java.time.LocalDate
			LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			hienThiDanhSachCongDoanTheoMaSanPhamBoPhan(txtMaSP.getText(), boPhanID, localDate);
			// Thiết lập lại chỉ mục
			if (modelCongDoan.getRowCount() > 0) {
				// ngay đây bị lỗi nè
				int row = tableCongDoan.getSelectedRow();
				int rows = tableCongDoan.getRowCount();
//				if (row <= rows - 1) {
				tableCongDoan.setRowSelectionInterval(index, index);
//				}
			} else {
				modelCongNhan.setRowCount(0);
			}
		}

	}

	private BangPhanCongCongNhan customBangPhanCong() {
		String phanCongID = getNewMaPhanCongID();
		String congNhanID = txtMaCN.getText();
		int index = tableCongDoan.getSelectedRow();
		if (index < 0) {
			return null;
		}
		String phanCongBoPhanID = listbp.get(index).getPhanCongID();
		// chuẩn hóa ngày
		Date selectedDate = dateChooser.getDate();
		// Chuyển đổi java.util.Date thành LocalDate
		Instant instant = selectedDate.toInstant();
		LocalDate ngay = instant.atZone(ZoneId.systemDefault()).toLocalDate();

		int soLuongPhanCong = Integer
				.parseInt(!txtSoLuongCanLam.getText().isEmpty() ? txtSoLuongCanLam.getText() : "0");
		int soLuongHoanThanh = 0;
		BangPhanCongCongNhan a = new BangPhanCongCongNhan(phanCongID, qlcn.getCongNhanTheoID(congNhanID),
				new BangPhanCongBoPhan(phanCongBoPhanID), ngay, soLuongPhanCong, soLuongHoanThanh);

		return a;
	}

	private void hienThiTextCongNhan() {
		int selectedRow = tableCongNhan.getSelectedRow();
		if (selectedRow != -1) {
			// Lấy giá trị từ bảng và gán cho các trường/text field khác
			String maCongNhan = tableCongNhan.getValueAt(selectedRow, 2).toString();
			// Gán giá trị cho các trường/text field khác
			txtMaCN.setText(maCongNhan);
		}
	}

	private String getNewMaPhanCongID() {
		Date currentDate = dateChooser.getDate();
		String phanCongID = "";
		// Định dạng ngày thành chuỗi theo định dạng "yyMMdd"
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String formattedDate = sdf.format(currentDate);
		String typeMaPC = formattedDate + txtMaCN.getText();
		try {
			String lastedPC = qlpccn.getNewMaPCCN(typeMaPC);
			if (lastedPC.isEmpty()) {
				// Nếu không có mã phân công cuối cùng, thì gán số "001"
				phanCongID = typeMaPC + "001";
			} else {
				// Nếu có mã phân công cuối cùng, thì tách 3 số cuối và tăng lên 1
				int lastThreeDigits = Integer.parseInt(lastedPC.substring(lastedPC.length() - 3));
				lastThreeDigits++;
				String newSuffix = String.format("%03d", lastThreeDigits);
				phanCongID = typeMaPC + newSuffix;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// đã có mã phân công
		return phanCongID;
	}

	private void hoanTacPhanCong(String phanCongID) {
		if (!phanCongID.isEmpty()) {
			qlpccn.xoaPhanCong(phanCongID);
			updateHienThiSauPhanCong();
		} else {
			JOptionPane.showMessageDialog(tableDaPC, "Vui lòng chọn phân công muốn hủy");
		}
	}

	private void capNhatSoLuongLamDuocALL() {
		String listMaPhanCong[] = new String[tableDaPC.getRowCount()];
		int listSoLuongHoanThanh[] = new int[tableDaPC.getRowCount()];
		for (int i = 0; i < tableDaPC.getRowCount(); i++) {
			listMaPhanCong[i] = tableDaPC.getValueAt(i, 1).toString();
			listSoLuongHoanThanh[i] = Integer
					.parseInt(tableDaPC.getValueAt(i, tableDaPC.getColumnCount() - 1).toString());

		}
		qlpccn.capNhatSoLuongHoanThanh(tableDaPC.getRowCount(), listMaPhanCong, listSoLuongHoanThanh);

	}

	private void hienThiDanhSachLoaiSanPham() {
		ArrayList<String> list = qlsp.getLocSanPham();

		// Xóa tất cả các mục hiện tại trong JComboBox
		comboBox.removeAllItems();

		// Thêm các mục từ danh sách vào JComboBox
		for (String loaiSanPham : list) {
			comboBox.addItem(loaiSanPham);
		}
		comboBox.setSelectedItem("Chưa hoàn thành");

	}

	private void hienThiDanhSachSPChuaHoanThanh() {
		// Lấy danh sách sản phẩm từ DAO
		List<SanPham> listSanPham = qlsp.getSanPhamChuaHoanThanh();

		// Xóa dữ liệu hiện tại trong table model
		modelSanPham.setRowCount(0);

		// Thêm dữ liệu từ danh sách sản phẩm vào table model
		for (SanPham sanPham : listSanPham) {
			int soLuonght = qlsp.getSoLuongHoanThanh(sanPham.getSanPhamID());
			Object[] row = { sanPham.getSanPhamID(), sanPham.getTenSanPham(), sanPham.getSoLuong(), soLuonght };
			modelSanPham.addRow(row);
		}
	}

	private void hienThiDanhSachSPDaHoanThanh() {
		// Lấy danh sách sản phẩm từ DAO
		List<SanPham> listSanPham = qlsp.getSanPhamDaHoanThanh();

		// Xóa dữ liệu hiện tại trong table model
		modelSanPham.setRowCount(0);

		// Thêm dữ liệu từ danh sách sản phẩm vào table model
		for (SanPham sanPham : listSanPham) {
			int soLuonght = qlsp.getSoLuongHoanThanh(sanPham.getSanPhamID());
			Object[] row = { sanPham.getSanPhamID(), sanPham.getTenSanPham(), sanPham.getSoLuong(), soLuonght };
			modelSanPham.addRow(row);
		}
	}

	private void hienThiDanhSachSPTheoLoai() {
		// Lấy danh sách sản phẩm từ DAO
		String selected = comboBox.getSelectedItem().toString();
		List<SanPham> listSanPham = qlsp.getSanPhamTheoLoai(selected);

		// Xóa dữ liệu hiện tại trong table model
		modelSanPham.setRowCount(0);

		// Thêm dữ liệu từ danh sách sản phẩm vào table model
		for (SanPham sanPham : listSanPham) {
			int soLuonght = qlsp.getSoLuongHoanThanh(sanPham.getSanPhamID());
			Object[] row = { sanPham.getSanPhamID(), sanPham.getTenSanPham(), sanPham.getSoLuong(), soLuonght };
			modelSanPham.addRow(row);
		}
	}

	private int middleWareAllowPhanCong(String maSP, int doUuTien) {
		Date selectedDate = dateChooser.getDate();
		// Chuyển đổi từ java.util.Date sang java.time.LocalDate
		LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int soLuong = qlcd.getSoLuongDaHoanThanhCuaCongDoanCheckUuTien(maSP, doUuTien, localDate);
		return soLuong;
	}

//	private void updateHoanThanhSanPham(String maSanPham) {
//		qlsp.updateHoanThanh(maSanPham);
//	}

	private boolean kiemTraHoanThanh(String maSanPham) {
		int soLuongHt = qlsp.getSoLuongHoanThanh(maSanPham);
		int index = tableSanPham.getSelectedRow();
		if (index >= 0) {
			int soLuongYeuCau = Integer.parseInt(tableSanPham.getValueAt(index, 2).toString());
			if (soLuongHt >= soLuongYeuCau)
				return true;
		}
		return false;
	}

}
