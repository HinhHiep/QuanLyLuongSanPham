package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import dao.BangChamCongDAO;
import dao.NguoiDAO;
import dao.NhanVienDAO;
import entities.BangChamCong;
import entities.HopDongLaoDong;
import entities.LoaiHopDong;
import entities.Nguoi;
import entities.NhanVien;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLNS_ChamCongNhanVien_GUI extends JPanel {

	private JPanel contentPane;
	private JTable tableChamCong;
	static DefaultTableModel model;
	private JButton btnDiemDanhTatCa;
	private JButton btnHuyBo;
	private JButton btnXacNhan;
	private JComboBox cBBCaLamViec;
	private JButton btnLayDanhSach;
	private JDateChooser dateChooser;
	private JLabel soLuong;
	private JLabel coMat;
	private JLabel vangMat;
	private String caLamViec = "Buổi sáng";
	private BangChamCongDAO bangChamCongDAO = new BangChamCongDAO();
	private NguoiDAO nguoiDAO = new NguoiDAO();
	private NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private boolean tinhTrangCoMat = false;
	private boolean tinhTrangVang = false;
	public static final int PRESENT_COLUMN = 6;
	public static final int ABSENT_COLUMN = 7;
	private List<BangChamCong> listBCC = new ArrayList<BangChamCong>();
	private int soLuongNhanVien = 0;
	private int soLuongCoMat = 0;
	private int soLuongVangMat = 0;
	private JButton btnSua;
	private int huyBo = 0;
	private int diemDanhTatCa = 0;
	private LocalDate ngayDate;

	public QLNS_ChamCongNhanVien_GUI() throws ParseException {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel main = new JPanel();
		main.setBorder(new TitledBorder(null, "Nh\u00E2n vi\u00EAn h\u00E0nh ch\u00EDnh", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(main);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setHgap(15);
		main.add(panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Số lượng: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel);

		soLuong = new JLabel("0");
		soLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(soLuong);

		JPanel panel_1_1 = new JPanel();
		panel.add(panel_1_1);

		JLabel lblCMt = new JLabel("Có mặt: ");
		lblCMt.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1_1.add(lblCMt);

		coMat = new JLabel("0");
		coMat.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1_1.add(coMat);

		JPanel panel_1_1_1 = new JPanel();
		panel.add(panel_1_1_1);

		JLabel lblVngCPhp = new JLabel("Vắng mặt:");
		lblVngCPhp.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1_1_1.add(lblVngCPhp);

		vangMat = new JLabel("0");
		vangMat.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1_1_1.add(vangMat);

		JPanel tacvu = new JPanel();

		main.add(tacvu);
		tacvu.setLayout(new BoxLayout(tacvu, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(
				new TitledBorder(null, "\u0110i\u1EC3m danh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setPreferredSize(new Dimension(600, 40));

		tacvu.add(panel_6);
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);

		btnDiemDanhTatCa = new JButton("ĐIỂM DANH CÓ MẶT TẤT CẢ");
		btnDiemDanhTatCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnDiemDanhTatCa)) {
					if(diemDanhTatCa == 1) {
						for(int i = 0; i < tableChamCong.getRowCount(); i++) {
							tableChamCong.setValueAt(true, i, 6);
						}
					}
					else {
						tinhTrangCoMat = true;
						tinhTrangVang = false;
						addDataTableHienTai(tinhTrangCoMat, tinhTrangVang);
						tableChamCong.setRowSelectionInterval(0, 0);
					}
					
				}

			}
		});
		btnDiemDanhTatCa.setForeground(new Color(255, 255, 255));
		btnDiemDanhTatCa.setBackground(new Color(64, 128, 128));
		btnDiemDanhTatCa.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnDiemDanhTatCa);

		btnHuyBo = new JButton("HỦY BỎ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnHuyBo)) {
					if(huyBo == 1) {
						for(int i = 0; i < tableChamCong.getRowCount(); i++) {
							tableChamCong.setValueAt(false, i, 6);
						}
					}else {
						tinhTrangCoMat = false;
						addDataTableHienTai(tinhTrangCoMat, tinhTrangVang);
					}
					
				}
			}
		});
		btnHuyBo.setEnabled(false);
		btnHuyBo.setForeground(new Color(255, 255, 255));
		btnHuyBo.setBackground(new Color(64, 128, 128));
		btnHuyBo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnHuyBo);

		btnXacNhan = new JButton("XÁC NHẬN");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnXacNhan)) {
				//	MainGUI.qlns_Main_GUI
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Xác nhận chấm công ?", "Chấm công",
							JOptionPane.YES_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						for(int i = 0; i< tableChamCong.getRowCount(); i++) {
							BangChamCong bangChamCong = new BangChamCong(tableChamCong.getValueAt(i, 0).toString(), 
									nhanVienDAO.getNhanVienByIDFullProps(tableChamCong.getValueAt(i, 3).toString()), ngayDate, 
									tableChamCong.getValueAt(i, 2).toString(), 
									chuyenDoiTinhTrang(Boolean.parseBoolean(tableChamCong.getValueAt(i, 6).toString()),  tableChamCong.getValueAt(i, 2).toString()));
							listBCC.add(bangChamCong);
						}
						themVaoBangChamCong(listBCC);
						showMessage("Thông báo", "Chấm công ca làm việc thành công");
						btnSua.setEnabled(true);
					}
					else {
						showMessage("Thông báo", "Hủy bỏ chức năng chấm công nhân viên");
						btnSua.setEnabled(false);
					}
						
				}
			}
		});
		btnXacNhan.setForeground(new Color(255, 255, 255));
		btnXacNhan.setBackground(new Color(64, 128, 128));
		btnXacNhan.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(btnXacNhan);
		
		btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSua)) {
					btnDiemDanhTatCa.setEnabled(true);
					btnHuyBo.setEnabled(true);
					btnXacNhan.setEnabled(false);
					int option = JOptionPane.showConfirmDialog(MainGUI.qlns_Main_GUI,
							"Xác nhận sửa lại?", "Chấm công",
							JOptionPane.YES_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						for(int i = 0; i < tableChamCong.getRowCount(); i++) {
							BangChamCong bangChamCong = new BangChamCong(tableChamCong.getValueAt(i, 0).toString(), 
									nguoiDAO.getNguoiByID(tableChamCong.getValueAt(i, 3).toString()), LocalDate.now(), 
									tableChamCong.getValueAt(i, 2).toString(), 
									chuyenDoiTinhTrang(Boolean.parseBoolean(tableChamCong.getValueAt(i, 6).toString()), tableChamCong.getValueAt(i, 2).toString()));
							//System.out.println(bangChamCong.toString());
							System.out.println(bangChamCongDAO.suaBangChamCong(bangChamCong));
						}
						showMessage("Thông báo", "Sửa thành công");
						addDataTableHienTaiCa(caLamViec, LocalDate.now());
					}
					else {
						showMessage("Thông báo", "Hủy bỏ thao tác sửa, bảng sẽ trở lại trạng thái ban đầu trước khi sửa");
						addDataTableHienTaiCa(caLamViec, LocalDate.now());
					}
						
						
					
				}
			}
		});
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBackground(new Color(64, 128, 128));
		btnSua.setEnabled(false);
		panel_6.add(btnSua);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Ng\u00E0y ch\u1EA5m c\u00F4ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_7.setPreferredSize(new Dimension(500, 40));
		tacvu.add(panel_7);
		FlowLayout flowLayout2 = (FlowLayout) panel_7.getLayout();
		flowLayout2.setHgap(10);
		flowLayout2.setAlignment(FlowLayout.RIGHT);

		JLabel lbCaLamViec = new JLabel("Buổi làm");
		panel_7.add(lbCaLamViec);
		
	
		cBBCaLamViec = new JComboBox();
		cBBCaLamViec.setModel(new DefaultComboBoxModel(new String[] {"Buổi sáng", "Buổi chiều"}));
		cBBCaLamViec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
//					if(e.getItem().toString() == "Ca tối")
//						tableChamCong.setModel(new DefaultTableModel(new Object[][] {
//
//						}, new String[] { "M\u00E3 ch\u1EA5m c\u00F4ng", "Ng\u00E0y ch\u1EA5m", "Ca l\u00E0m",
//								"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ph\u00F2ng ban", "C\u00F3 m\u1EB7t",
//								"V\u1EAFng c\u00F3 ph\u00E9p" }) {
//							Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
//									Object.class, Boolean.class, Boolean.class };
//
//							public Class getColumnClass(int columnIndex) {
//								return columnTypes[columnIndex];
//							}
//							
//							 @Override
//					            public boolean isCellEditable(int row, int column) {
//					                return column == 6; 
//					            }
//						});
//					else
//						tableChamCong.setModel(new DefaultTableModel(new Object[][] {
//
//						}, new String[] { "M\u00E3 ch\u1EA5m c\u00F4ng", "Ng\u00E0y ch\u1EA5m", "Ca l\u00E0m",
//								"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ph\u00F2ng ban", "C\u00F3 m\u1EB7t",
//								"V\u1EAFng c\u00F3 ph\u00E9p" }) {
//							Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
//									Object.class, Boolean.class, Boolean.class };
//
//							public Class getColumnClass(int columnIndex) {
//								return columnTypes[columnIndex];
//							}
//							
//					
//						});
						
					caLamViec = e.getItem().toString();
			}
		});
		panel_7.add(cBBCaLamViec);

		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		panel_7.add(lblNewLabel_1);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");

		dateChooser.setDate(java.sql.Date.valueOf(LocalDate.now()));
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				btnDiemDanhTatCa.setEnabled(false);
				btnHuyBo.setEnabled(false);
				btnXacNhan.setEnabled(false);
				btnSua.setEnabled(false);
				String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				ngayDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			}
		});
		dateChooser.setDateFormatString("dd-MM-yyyy");
		panel_7.add(dateChooser);

		btnLayDanhSach = new JButton("LẤY DANH SÁCH");
		btnLayDanhSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnLayDanhSach)) {
					String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
//					LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					if (date.equals("")) {
						showMessage("Thông báo", "Mời chọn ngày để chấm công");
					} else {
						LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
						if (localDate.isAfter(LocalDate.now())) { //ngày lớn hơn ngày hiện tại
							showMessage("Thông báo",
									"Chưa có danh sách chấm công, phải nhỏ hơn hoặc bằng ngày hiện tại");
							model.setRowCount(0);
						} else if (localDate.isBefore(LocalDate.now())) { // ngày nhỏ hơn ngày hiện tại
							btnDiemDanhTatCa.setEnabled(false);
							btnHuyBo.setEnabled(false);
							btnXacNhan.setEnabled(false);
							btnSua.setEnabled(false);
							addDataTableTimKiemID(localDate, cBBCaLamViec.getSelectedItem().toString());
							tableChamCong.setEnabled(false);
						} else {       // bằng ngày hiện tại
							tableChamCong.setEnabled(true);
							if(bangChamCongDAO.kiemTraChamCongTheoCa(cBBCaLamViec.getSelectedItem().toString(), LocalDate.now()) == true) { //ca đã chấm theo ngày hiện tại
								showMessage("Thông báo", "Ca này đã được chấm vào ngày hôm nay, chỉ mặt sửa trong ngày");
								huyBo = 1; // ca này đã được chấm trong ngày hôm nay
								diemDanhTatCa = 1;
								addDataTableHienTaiCa(cBBCaLamViec.getSelectedItem().toString(), LocalDate.now());
								tableChamCong.setEnabled(true);
								tableChamCong.setRowSelectionInterval(0, 0);
								btnDiemDanhTatCa.setEnabled(true);
								btnHuyBo.setEnabled(true);
								btnXacNhan.setEnabled(false);
								btnSua.setEnabled(true);
								
							}
							else { // ca chưa chấm theo ngày hiện tại
								huyBo = 0;
								diemDanhTatCa = 0;
								addDataTableHienTai(tinhTrangCoMat, tinhTrangVang);
								tableChamCong.setEnabled(true);
								tableChamCong.setRowSelectionInterval(0, 0);
								btnDiemDanhTatCa.setEnabled(true);
								btnHuyBo.setEnabled(true);
								btnXacNhan.setEnabled(true);
								btnSua.setEnabled(false);
							}
						}
					}
				
				}
			}
		});
		btnLayDanhSach.setForeground(new Color(255, 255, 255));
		btnLayDanhSach.setBackground(new Color(64, 128, 128));
		panel_7.add(btnLayDanhSach);

		JPanel panel_5 = new JPanel();
		main.add(panel_5);

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);

		tableChamCong = new JTable();
		tableChamCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		tableChamCong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 ch\u1EA5m c\u00F4ng", "Ng\u00E0y ch\u1EA5m", "Bu\u1ED5i l\u00E0m", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ph\u00F2ng ban", "C\u00F3 m\u1EB7t"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableChamCong.setPreferredScrollableViewportSize(new Dimension(1000, 350));
		scrollPane.setViewportView(tableChamCong);
	}

	private void addDataTableTimKiemID(LocalDate date, String ca) { // chỉ có thể xem không thể sửa
		model = (DefaultTableModel) tableChamCong.getModel();
		model.setRowCount(0);
		soLuongNhanVien = 0;
		soLuongCoMat = 0;
		soLuongVangMat = 0;
		List<BangChamCong> list = bangChamCongDAO.getAllBangChamCongByNgayVaCa(date, ca);
		if (list.size() == 0)
			showMessage("Thông báo", "Chưa có dữ liệu chấm công của ngày này");
		else {
			for (BangChamCong bangChamCong : list) {
				NhanVien nhanVien = nhanVienDAO.getNhanVienByIDFullProps(bangChamCong.getNguoi().getNguoiID());
				model.addRow(new Object[] { bangChamCong.getChamCongID(),
						DateTimeFormatter.ofPattern("dd-MM-yyyy").format(bangChamCong.getNgay()),
						ca, nhanVien.getNguoiID(), nhanVien.getHoTen(),
						nhanVien.getPhongBan().getTenPhongBan(),
						bangChamCong.getTinhTrang() == null ? false
								: bangChamCong.getTinhTrang().equals("Có mặt") ? true : false});
				soLuongTinhTrang(bangChamCong.getTinhTrang(), ca);
				soLuongNhanVien++;
			}
			soLuong.setText(String.valueOf(soLuongNhanVien));
			coMat.setText(String.valueOf(soLuongCoMat));
			vangMat.setText(String.valueOf(soLuongVangMat));
		}

	}

	private void addDataTableHienTai(boolean tinhTrangCoMat, boolean tinhTrangVang) { // có thể sửa trạng thái
		model = (DefaultTableModel) tableChamCong.getModel();
		model.setRowCount(0);
		soLuongNhanVien = 0;
		soLuongCoMat = 0;
		soLuongVangMat = 0;
		List<NhanVien> listNhanVien = nhanVienDAO.getAllNhanVien();

		for (NhanVien nhanVien : listNhanVien) {
			model.addRow(new Object[] { taoMaChamCong(nhanVien.getNguoiID()),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(ngayDate), caLamViec, nhanVien.getNguoiID(),
					nhanVien.getHoTen(), nhanVien.getPhongBan().getTenPhongBan(), tinhTrangCoMat });
			BangChamCong bangChamCong = new BangChamCong(taoMaChamCong(nhanVien.getNguoiID()), nhanVien, LocalDate.now(), 
					caLamViec, chuyenDoiTinhTrang(tinhTrangCoMat, caLamViec));
			soLuongTinhTrang(chuyenDoiTinhTrang(tinhTrangCoMat, caLamViec), caLamViec);
			soLuongNhanVien++;
		}
		soLuong.setText(String.valueOf(soLuongNhanVien));
		coMat.setText(String.valueOf(soLuongCoMat));
		vangMat.setText(String.valueOf(soLuongVangMat));
	}
	
	private void addDataTableHienTaiCa(String ca, LocalDate date) {
		model = (DefaultTableModel) tableChamCong.getModel();
		model.setRowCount(0);
		soLuongNhanVien = 0;
		soLuongCoMat = 0;
		soLuongVangMat = 0;
		List<BangChamCong> list = bangChamCongDAO.getAllBangChamCongNgayHienTai(ca,date);
		for (BangChamCong bangChamCong : list) {
			NhanVien nhanVien = nhanVienDAO.getNhanVienByIDFullProps(bangChamCong.getNguoi().getNguoiID());
			model.addRow(new Object[] { bangChamCong.getChamCongID(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(bangChamCong.getNgay()), ca, nhanVien.getNguoiID(),
					nhanVien.getHoTen(), nhanVien.getPhongBan().getTenPhongBan(),
					bangChamCong.getTinhTrang() == null ? false
							: bangChamCong.getTinhTrang().equals("Có mặt") ? true : false });
			if(bangChamCong.getTinhTrang().equals("Có mặt"))
				soLuongCoMat++;
			else if(bangChamCong.getTinhTrang().equals("Vắng mặt"))
				soLuongVangMat++;
			soLuongNhanVien++;	
		}
		soLuong.setText(String.valueOf(soLuongNhanVien));
		coMat.setText(String.valueOf(soLuongCoMat));
		vangMat.setText(String.valueOf(soLuongVangMat));

	}

	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}

	private String bonSoCuoi(String id) {
		String maNhanVien = nhanVienDAO.getNhanVienByID(id).getNguoiID();
		String builder = new StringBuilder(maNhanVien).reverse().substring(0, 4);
		String number = new StringBuilder(builder).reverse().toString();
		return number;

	}

	private String ngay() {
		String ngay = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
//		String[] dateSplit = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()).split("-");
		String[] dateSplit = ngay.split("-");
		String date = dateSplit[0] + dateSplit[1] + dateSplit[2].substring(2, 4);
		return date;
	}

	private String taoMaChamCong(String id) {
		
		return id + ngay() + caLamViecVietTat(caLamViec);
	}

	private String caLamViecVietTat(String ca) {
		return ca.equals("Buổi sáng") ? "S" : ca.equals("Buổi chiều") ? "C" : "T";
	}
	
	private String chuyenDoiTinhTrang(boolean coMat, String ca) {
		if(ca.equals("Buổi sáng") || ca.equals("Buổi chiều")) {
			if(coMat == true)
				return "Có mặt";
			else if(coMat == false)
				return "Vắng mặt";
		}
		else {
			if(coMat == true)
				return "Có mặt";
			else
				return null;
		}
		return "";
	}
	
	private void themVaoBangChamCong(List<BangChamCong> list) {
		list.forEach(e -> {
			bangChamCongDAO.addBangChamCong(e);
		});
	}
	
	private void soLuongTinhTrang(String tinhTrang, String caLamViec) {
		 if(tinhTrang.equals("Có mặt")) {
			soLuongCoMat++;
		}
		 else if(tinhTrang.equals("Vắng mặt"))
			soLuongVangMat++;
		
		
		
	}

}
