package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BangChamCongDAO;
import dao.BangLuongNhanVienDAO;
import dao.BangPhanCongBoPhanDAO;
import dao.ChiTietPhuCapKhenThuongKhauTruDAO;
import dao.KhenThuongDAO;
import dao.NguoiDAO;
import dao.NhanVienDAO;
import dao.PhongBanDAO;
import dao.PhuCapDAO;
import dao.PhuCapKhenThuongKhauTruDAO;
import entities.BangChamCong;
import entities.BangLuongNhanVien;
import entities.ChiTietPhuCapKhenThuongKhauTru;
import entities.KhauTru;
import entities.KhenThuong;
import entities.Nguoi;
import entities.NhanVien;
import entities.PhongBan;
import entities.PhuCap;
import entities.PhuCapKhenThuongKhauTru;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

public class NVKT_TinhLuongNV extends JPanel {

	private JPanel contentPane;
	public static JTable tableNhanVien = new JTable();
	private BangChamCongDAO bangChamCongDAO = new BangChamCongDAO();
	private NguoiDAO nguoiDAO = new NguoiDAO();
	private NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private PhuCapDAO phuCapDAO = new PhuCapDAO();
	private KhenThuongDAO khenThuongDAO = new KhenThuongDAO();
	private List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
	public static DefaultTableModel model;
	private JDateChooser dateChooser;
	public static int row;
	public static double thueThuNhapCaNhan;
	public static double tongPhuCap;
	public static double tongKhenThuong;
	public static double tongKhauTru;
	public static double tongLuongTruocThue;;
	public static double luongThucLanh;;
	public static int SNV;
	public static int SNC;
	public static double tienKhauTruVang;
	public static String thangLuong;
	private JComboBox cBBPhongBan;
	private PhongBanDAO phongBanDAO = new PhongBanDAO();
	private BangLuongNhanVienDAO bangLuongNhanVienDAO = new BangLuongNhanVienDAO();
	private ChiTietPhuCapKhenThuongKhauTruDAO chiTietPhuCapKhenThuongKhauTruDAO = new ChiTietPhuCapKhenThuongKhauTruDAO();
	private static PhuCapKhenThuongKhauTruDAO phuCapKhenThuongKhauTruDAO = new PhuCapKhenThuongKhauTruDAO();
	public static List<PhuCapKhenThuongKhauTru> listCTKT;
	public static String maBangLuongTable;
	public static BangLuongNhanVien thongTinBLNV;
	public static NhanVien thongTinNV = new NhanVien(maBangLuongTable);
	public static List<PhuCapKhenThuongKhauTru> listPCKTKT;
	public NVKT_TinhLuongNV() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel);

		JLabel lblNewLabel = new JLabel("Tháng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel);

		// Tạo JDateChooser và đặt nó ẩn ban đầu
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");


		dateChooser.setDate(java.sql.Date.valueOf(LocalDate.now()));
		dateChooser.setPreferredSize(new Dimension(100, 20));
		dateChooser.setVisible(true);
		panel.add(dateChooser);
		
		JButton btnLayDanhSach = new JButton("LẤY DANH SÁCH");
		btnLayDanhSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLayDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				if(e.getSource().equals(btnLayDanhSach))
					addDataTableLuongNhanVien(localDate);
			}
		});
		btnLayDanhSach.setForeground(new Color(255, 255, 255));
		btnLayDanhSach.setBackground(new Color(64, 128, 128));
		panel.add(btnLayDanhSach);

		JPanel listCN = new JPanel();
		listCN.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "DANH S\u00C1CH NH\u00C2N VI\u00CAN", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 128, 128)));
		add(listCN);
		listCN.setLayout(new BoxLayout(listCN, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) top.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		listCN.add(top);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		top.add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("Tổng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("10");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Phòng ban, ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_3);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		top.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("500");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Nhân viên");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblNewLabel_5);

		JPanel func = new JPanel();
		listCN.add(func);
		func.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_3.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		func.add(panel_3);
		String[] dsPhongBan = addDuLieuJCBBPhongBan().toArray(String[]::new);
		cBBPhongBan = new JComboBox(dsPhongBan);
		cBBPhongBan.setPreferredSize(new Dimension(120, 30));
		cBBPhongBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(cBBPhongBan);

		JButton btnNewButton_3 = new JButton("Tìm kiếm");
		btnNewButton_3.setBackground(new Color(255, 255, 0));
		btnNewButton_3.setPreferredSize(new Dimension(130, 30));
		btnNewButton_3
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\iconTimkiem.png"));
		panel_3.add(btnNewButton_3);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_4.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		func.add(panel_4);

		JButton btnCapNhat = new JButton("Cập nhật phụ cấp - khen thưởng - khấu trừ");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCapNhat)) {
					row = tableNhanVien.getSelectedRow();
					maBangLuongTable = tableNhanVien.getValueAt(row, 1).toString();
					thangLuong = tableNhanVien.getValueAt(row, 5).toString();
					thongTinNV = nhanVienDAO.getNhanVienByIDFullProps(tableNhanVien.getValueAt(row, 2).toString());
					thongTinBLNV = bangLuongNhanVienDAO.getBangLuongNhanVien(maBangLuongTable);
					listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachByMaLuongID(maBangLuongTable);
					SNC = Integer.parseInt(tableNhanVien.getValueAt(row, 8).toString());
					SNV = Integer.parseInt(tableNhanVien.getValueAt(row, 9).toString());
					tienKhauTruVang = tienKhauTruVang(SNV);
					tongPhuCap = Double.parseDouble(tableNhanVien.getValueAt(row, 10).toString());
					tongKhenThuong = Double.parseDouble(tableNhanVien.getValueAt(row, 11).toString());
					tongKhauTru = Double.parseDouble(tableNhanVien.getValueAt(row, 12).toString());
					tongLuongTruocThue = Double.parseDouble(tableNhanVien.getValueAt(row, 13).toString());
					luongThucLanh = Double.parseDouble(tableNhanVien.getValueAt(row, 14).toString());
					thueThuNhapCaNhan = thueThuNhapCaNhan(tongLuongTruocThue);
					new NVTK_PhuCap_KhenThuong_KhauTru().setVisible(true);
				}
			}
		});
		btnCapNhat.setBackground(new Color(64, 128, 128));
		panel_4.add(btnCapNhat);

		JButton btnXuatPhieu = new JButton("Xuất phiếu lương");
		btnXuatPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnXuatPhieu)) {
					row = tableNhanVien.getSelectedRow();
					maBangLuongTable = tableNhanVien.getValueAt(row, 1).toString();
					thangLuong = tableNhanVien.getValueAt(row, 5).toString();
					thongTinNV = nhanVienDAO.getNhanVienByIDFullProps(tableNhanVien.getValueAt(row, 2).toString());
					thongTinBLNV = bangLuongNhanVienDAO.getBangLuongNhanVien(maBangLuongTable);
					listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachByMaLuongID(maBangLuongTable);
					SNC = Integer.parseInt(tableNhanVien.getValueAt(row, 8).toString());
					SNV = Integer.parseInt(tableNhanVien.getValueAt(row, 9).toString());
					tienKhauTruVang = tienKhauTruVang(SNV);
					tongPhuCap = Double.parseDouble(tableNhanVien.getValueAt(row, 10).toString());
					tongKhenThuong = Double.parseDouble(tableNhanVien.getValueAt(row, 11).toString());
					tongKhauTru = Double.parseDouble(tableNhanVien.getValueAt(row, 12).toString());
					tongLuongTruocThue = Double.parseDouble(tableNhanVien.getValueAt(row, 13).toString());
					luongThucLanh = Double.parseDouble(tableNhanVien.getValueAt(row, 14).toString());
					thueThuNhapCaNhan = thueThuNhapCaNhan(tongLuongTruocThue);
									
					try {
						new NVKT_ThongTinLuongNhanVien().setVisible(true);
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnXuatPhieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnXuatPhieu.setBackground(new Color(64, 128, 128));
		panel_4.add(btnXuatPhieu);

		JPanel table = new JPanel();
		listCN.add(table);

		JScrollPane scrollPane = new JScrollPane();
		table.add(scrollPane);

		tableNhanVien.setPreferredScrollableViewportSize(new Dimension(1530, 280));
		tableNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 b\u1EA3ng l\u01B0\u01A1ng", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Ph\u00F2ng ban", "Th\u00E1ng l\u01B0\u01A1ng", "L\u01B0\u01A1ng c\u01A1 b\u1EA3n", "H\u1EC7 s\u1ED1 l\u01B0\u01A1ng", "S\u1ED1 ng\u00E0y c\u00F4ng", "S\u1ED1 ng\u00E0y v\u1EAFng", "Ph\u1EE5 c\u1EA5p", "Khen th\u01B0\u1EDFng", "Kh\u1EA5u tr\u1EEB", "L\u01B0\u01A1ng tr\u01B0\u1EDBc thu\u1EBF", "L\u01B0\u01A1ng th\u1EF1c l\u00E3nh"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Double.class, Double.class, Integer.class, Integer.class, Object.class, Object.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableNhanVien);
	}
	
	private void addDataTableLuongNhanVien(LocalDate date) {
		listNhanVien = nhanVienDAO.getAllNhanVien();
		model = (DefaultTableModel) tableNhanVien.getModel();
		model.setRowCount(0);
		int stt = 1;
		for(NhanVien nhanVien : listNhanVien) {
			String namThang = thangLuong(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText());
			String[] split = namThang.split("-");
			String ngay = split[0]+ split[1].substring(2,4);
			double luongTruocThue = luongTruocThue(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay), nhanVien.getLuongCoBan(), 
					bangChamCongDAO.getSoNgayVangCuaMotNhanVien(nhanVien, date), nhanVien.getHeSoLuong());
			
			ChiTietPhuCapKhenThuongKhauTru chiTietBHXH = chiTietPhuCapKhenThuongKhauTruDAO
				.getChiTietKhenThuongPhuCapKhauTru("KT1220001", taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay));
			ChiTietPhuCapKhenThuongKhauTru chiTietBHYT = chiTietPhuCapKhenThuongKhauTruDAO
				.getChiTietKhenThuongPhuCapKhauTru("KT1220002", taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay));		
			ChiTietPhuCapKhenThuongKhauTru chiTietKhauTruThue = chiTietPhuCapKhenThuongKhauTruDAO
				.getChiTietKhenThuongPhuCapKhauTru(maThueXacDinhTheoLuong(luongTruocThue), taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay));

			if(chiTietBHXH == null)
				chiTietPhuCapKhenThuongKhauTruDAO.addChiTietKhauTruBHXH(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay));
			if(chiTietBHYT == null)
				chiTietPhuCapKhenThuongKhauTruDAO.addChiTietKhauTruBHYT(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay));
			if(chiTietKhauTruThue == null)
				chiTietPhuCapKhenThuongKhauTruDAO.addChiTietKhauTruThue(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay), luongTruocThue);
			else {
				
			}
		
			model.addRow(new Object[] {
					stt,
					taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay),
					nhanVien.getNguoiID(),
					nhanVien.getHoTen(),
					nhanVien.getPhongBan().getTenPhongBan(),
					namThang,
					nhanVien.getLuongCoBan(),
					nhanVien.getHeSoLuong(),
					bangChamCongDAO.getSoNgayCongCuaMotNhanVien(nhanVien, date),
					bangChamCongDAO.getSoNgayVangCuaMotNhanVien(nhanVien, date),
					tinhTienPhucCap(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay)),
					tinhTienKhenThuong(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay)),
					tinhTienKhauTruTruocThue(nhanVien.getLuongCoBan(),bangChamCongDAO.getSoNgayVangCuaMotNhanVien(nhanVien, date)) + tinhTienKhauTruThue(luongTruocThue),
					luongTruocThue,
					luongTruocThue - thueThuNhapCaNhan(luongTruocThue)
			});
			BangLuongNhanVien bangLuongNhanVien = new BangLuongNhanVien(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay), 
						new NhanVien(nhanVien.getNguoiID()), Integer.parseInt(split[0]), bangChamCongDAO.getSoNgayCongCuaMotNhanVien(nhanVien, date), 
						bangChamCongDAO.getSoNgayVangCuaMotNhanVien(nhanVien, date), nhanVien.getHeSoLuong(), nhanVien.getLuongCoBan(), luongTruocThue - thueThuNhapCaNhan(luongTruocThue));
			if(bangLuongNhanVienDAO.getBangLuongNhanVien(taoMaBangLuongNhanVien(nhanVien.getNguoiID(), ngay)) == null)
				bangLuongNhanVienDAO.addBangLuongNhanVien(bangLuongNhanVien);
			else {
				bangLuongNhanVienDAO.updateBangLuongNhanVien(bangLuongNhanVien);
			}
			
			
			stt++;
		}
		
	}
	
	public double tongTienKhauTru(String bangLuongID) {
		return 1;
	}
	
	private String taoMaBangLuongNhanVien(String maNhanVien, String ngay) {
		return "BL"+ maNhanVien + ngay;
	}
	
	private String convertLuongCoBan(double luongCoBan) { // số dài quá kh hiển thị hết thì xài hàm này
		DecimalFormat df = new DecimalFormat("###,###,###.0");
		BigDecimal decimal = new BigDecimal(luongCoBan);
		String luong = df.format(decimal);
		return luong;
	}
	
	private String thangLuong(String thangLuong) {
		String[] thangNam = thangLuong.split("-");
		String date = thangNam[1] + "-" +thangNam[2];
		return date;
	}
	
	private String layMaChiTiet(String maNhanVien, String ngay) { //CTBL232PNS00100091023
		return "CTBL" + maNhanVien + ngay;
	}
	
	private double tinhTienKhauTruThue(double luongTruocThue) {
		double khauTruThue = thueThuNhapCaNhan(luongTruocThue); // thuế phải đóng dựa vào lương trước thuế sau khi đã cộng và trừ các khoản PCKTKT
		return khauTruThue;
	}
	
	private double tinhTienKhauTruTruocThue(double luongCoBan, int soNgayVang) { // chỉ bao gồm BHYT, BHXH, Khấu trừ vắng : 4.500.000
		double khauTruBHXH = luongCoBan * phuCapKhenThuongKhauTruDAO.getPhanTramBHXH()/100;
		double khauTruBHYT = luongCoBan * phuCapKhenThuongKhauTruDAO.getPhanTramBHYT()/100;
		double khauTruVang = 0;
		if(soNgayVang > 4)
			khauTruVang = soNgayVang * 200000;
		return khauTruBHXH + khauTruBHYT + khauTruVang;
	}
	
	private double khauTruThue(double luongTruocThue) { // lương trước thuế 18.000.000 * 0 .15 = 2.700.000
		double khauTru = thueThuNhapCaNhan(luongTruocThue);// lương thực lãnh 15.300.000
		return khauTru;
	}
	
	public double tienKhauTruVang(int soNgayVang) {
		double khauTruVang = 0.0;
		if(soNgayVang > 4)
			khauTruVang = soNgayVang * 200000;
		return khauTruVang;
	}
	
	private double luongTruocThue(String maLuong,double luongCoBan, int soNgayVang, double heSoLuong) {
		double luongTruocThue = luongCoBan * heSoLuong + tinhTienPhucCap(maLuong) + tinhTienKhenThuong(maLuong) - tinhTienKhauTruTruocThue(luongCoBan, soNgayVang);
		return luongTruocThue;
	}
	
	
	private double tinhTienKhenThuong(String maLuongID) {	
		double tienKhenThuong = phuCapKhenThuongKhauTruDAO.getSoTienKhenThuongByMaLuongID(maLuongID);
		return tienKhenThuong;
	}
	
	private double tinhTienPhucCap(String maLuongID) {	
		double tienPhuCap = phuCapKhenThuongKhauTruDAO.getSoTienPhuCapByMaLuongID(maLuongID);
		return tienPhuCap;
	}
	
	private double tinhLuongTruocThue(double luongCoBan, double heSoLuong,double phuCap, double khenThuong, double khauTru, int soNgayVang) {
		return luongCoBan * heSoLuong + phuCap + khenThuong - (khauTru + soNgayVang * 100000);
	}
	
	public static double thueThuNhapCaNhan(double luongCoBan) {
		double thue;
		double phanTram;
		if(luongCoBan < 5000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu từ thuế đến 5 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}			
		else if(luongCoBan >= 5000000 && luongCoBan <= 10000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 5 - 10 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}			
		else if(luongCoBan >= 10000000 && luongCoBan <= 18000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 10 - 18 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}			
		else if(luongCoBan >= 18000000 && luongCoBan <= 32000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 18 - 32 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}			
		else if(luongCoBan >= 32000000 && luongCoBan <= 52000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 32 - 52 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}		
		else if(luongCoBan >= 52000000 && luongCoBan <= 80000000) {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế 52 - 80 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}			
		else {
			phanTram = phuCapKhenThuongKhauTruDAO.getMaVaSoTienTheoTenLoai("Khấu trừ thuế trên 80 triệu").getGiaTien();
			thue = luongCoBan * phanTram/100;
		}
			
		return thue;
	}
	
	private List<String> addDuLieuJCBBPhongBan() {
		List<PhongBan> list = phongBanDAO.getAllPhongBan();
		List<String> tenPhongBan = new ArrayList<String>();
		list.forEach(e -> tenPhongBan.add(e.getTenPhongBan()));
		return tenPhongBan;
	}
	
	private String maThueXacDinhTheoLuong(double luongCoBan) {
		String maThue;
		if(luongCoBan < 5000000)
			maThue = "KT1220003";
		else if(luongCoBan >= 5000000 && luongCoBan <= 10000000) 
			maThue = "KT1220004";	
		else if(luongCoBan >= 10000000 && luongCoBan <= 18000000) 
			maThue = "KT1220005";				
		else if(luongCoBan >= 18000000 && luongCoBan <= 32000000) 
			maThue = "KT1220006";				
		else if(luongCoBan >= 32000000 && luongCoBan <= 52000000) 
			maThue = "KT1220007";			
		else if(luongCoBan >= 52000000 && luongCoBan <= 80000000) 
			maThue = "KT1220008";				
		else 
			maThue = "KT1220009";
		
		return maThue;
		
	}
	 
	
	

}
