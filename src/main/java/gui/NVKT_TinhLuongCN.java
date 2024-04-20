package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import com.toedter.calendar.JDateChooser;

import dao.BangChamCongDAO;
import dao.BangLuongCongNhanDAO;
import dao.BoPhanDAO;
import dao.ChiTietPhuCapKhenThuongKhauTruCNDAO;
import dao.ChiTietPhuCapKhenThuongKhauTruDAO;
import dao.CongDoanDAO;
import dao.CongNhanDAO;
import dao.PhanCongCongNhan_DAO;
import dao.PhuCapKhenThuongKhauTruDAO;
import entities.BangLuongCongNhan;
import entities.BangLuongNhanVien;
import entities.BoPhan;
import entities.ChiTietPhuCapKhenThuongKhauTru;
import entities.ChiTietPhuCapKhenThuongKhauTruCN;
import entities.CongNhan;
import entities.NhanVien;
import entities.PhuCapKhenThuongKhauTru;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

public class NVKT_TinhLuongCN extends JPanel {

	private JPanel contentPane;
	private JTable tableCongNhan;
	private List<CongNhan> listCongNhan = new ArrayList<CongNhan>();
	private CongNhanDAO congNhanDAO = new CongNhanDAO();
	public static int row;
	public static DefaultTableModel model;
	private JDateChooser dateChooser;
	private JButton btnCapNhat;
	private JButton btnXuat;
	private BoPhanDAO boPhanDAO = new BoPhanDAO();
	private BangChamCongDAO bangChamCongDAO = new BangChamCongDAO();
	private PhanCongCongNhan_DAO phanCongCongNhan_DAO = new PhanCongCongNhan_DAO();
	private ChiTietPhuCapKhenThuongKhauTruDAO chiTietPhuCapKhenThuongKhauTruDAO = new ChiTietPhuCapKhenThuongKhauTruDAO();
	private static PhuCapKhenThuongKhauTruDAO phuCapKhenThuongKhauTruDAO = new PhuCapKhenThuongKhauTruDAO();
	private BangLuongCongNhanDAO bangLuongCongNhanDAO = new BangLuongCongNhanDAO();
	private ChiTietPhuCapKhenThuongKhauTruCNDAO cTruCN = new ChiTietPhuCapKhenThuongKhauTruCNDAO();
	public static List<PhuCapKhenThuongKhauTru> listCTKT;
	public static String maBangLuongTable;
	public static double thueThuNhapCaNhan;
	public static double tongPhuCap;
	public static double tongKhenThuong;
	public static double tongKhauTru;
	public static double tongLuongTruocThue;;
	public static double luongThucLanh;;
	public static double luongSanPham;
	public static int SNV;
	public static int SNC;
	public static String thangLuong;
	public static BangLuongCongNhan thongTinBLCN;
	public static CongNhan thongTinCongNhan = new CongNhan(maBangLuongTable);
	public static List<PhuCapKhenThuongKhauTru> listPCKTKT;
	private JLabel textSoBP;
	private JLabel textSCN;
	
	public NVKT_TinhLuongCN() {
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
		btnLayDanhSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLayDanhSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				if(e.getSource().equals(btnLayDanhSach))
					addDataTableLuongCongNhan(localDate);
			}
		});
		btnLayDanhSach.setForeground(Color.WHITE);
		btnLayDanhSach.setBackground(new Color(64, 128, 128));
		panel.add(btnLayDanhSach);

		JPanel listCN = new JPanel();
		listCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DANH S\u00C1CH C\u00D4NG NH\u00C2N", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(64, 128, 128)));
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

		JLabel lblNewLabel_1 = new JLabel("Tổng bộ phận:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel_1);
		
		textSoBP = new JLabel("New label");
		textSoBP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textSoBP.setText(String.valueOf(getSoBoPhan()));
		panel_1.add(textSoBP);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		top.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("Số công nhân: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblNewLabel_2);
		
		textSCN = new JLabel("New label");
		textSCN.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textSCN.setText(String.valueOf(getSoCongNhan()));
		panel_2.add(textSCN);

		JPanel func = new JPanel();
		listCN.add(func);
		func.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_3.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		func.add(panel_3);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_4.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		func.add(panel_4);

		btnCapNhat = new JButton("Cập nhật phụ cấp - khen thưởng - khấu trừ");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = tableCongNhan.getSelectedRow();
				maBangLuongTable = tableCongNhan.getValueAt(row, 1).toString();
				thangLuong = tableCongNhan.getValueAt(row, 5).toString();
				thongTinCongNhan = congNhanDAO.getCongNhanByIDFullProps(tableCongNhan.getValueAt(row, 2).toString());
				thongTinBLCN = bangLuongCongNhanDAO.getBangLuongCongNhan(maBangLuongTable);
				listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachPCKTKTCongNhanhByMaLuongID(maBangLuongTable);
				SNC = Integer.parseInt(tableCongNhan.getValueAt(row, 6).toString());
				SNV = Integer.parseInt(tableCongNhan.getValueAt(row, 7).toString());
				tongPhuCap = Double.parseDouble(tableCongNhan.getValueAt(row, 10).toString());
				tongKhenThuong = Double.parseDouble(tableCongNhan.getValueAt(row, 11).toString());
				tongKhauTru = Double.parseDouble(tableCongNhan.getValueAt(row, 12).toString());
				tongLuongTruocThue = Double.parseDouble(tableCongNhan.getValueAt(row, 13).toString());
				luongThucLanh = Double.parseDouble(tableCongNhan.getValueAt(row, 14).toString());
				thueThuNhapCaNhan = thueThuNhapCaNhan(tongLuongTruocThue);
				luongSanPham = Double.parseDouble(tableCongNhan.getValueAt(row, 9).toString());
				new NVTK_PhuCap_KhenThuong_KhauTruCN().setVisible(true);
			}
		});
		btnCapNhat.setBackground(new Color(64, 128, 128));
		panel_4.add(btnCapNhat);

		btnXuat = new JButton("Xuất phiếu lương");
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = tableCongNhan.getSelectedRow();
				maBangLuongTable = tableCongNhan.getValueAt(row, 1).toString();
				thangLuong = tableCongNhan.getValueAt(row, 5).toString();
				thongTinCongNhan = congNhanDAO.getCongNhanByIDFullProps(tableCongNhan.getValueAt(row, 2).toString());
				thongTinBLCN = bangLuongCongNhanDAO.getBangLuongCongNhan(maBangLuongTable);
				listPCKTKT = phuCapKhenThuongKhauTruDAO.getDanhSachPCKTKTCongNhanhByMaLuongID(maBangLuongTable);
				SNC = Integer.parseInt(tableCongNhan.getValueAt(row, 6).toString());
				SNV = Integer.parseInt(tableCongNhan.getValueAt(row, 7).toString());
				tongPhuCap = Double.parseDouble(tableCongNhan.getValueAt(row, 10).toString());
				tongKhenThuong = Double.parseDouble(tableCongNhan.getValueAt(row, 11).toString());
				tongKhauTru = Double.parseDouble(tableCongNhan.getValueAt(row, 12).toString());
				tongLuongTruocThue = Double.parseDouble(tableCongNhan.getValueAt(row, 13).toString());
				luongThucLanh = Double.parseDouble(tableCongNhan.getValueAt(row, 14).toString());
				thueThuNhapCaNhan = thueThuNhapCaNhan(tongLuongTruocThue);
				luongSanPham = Double.parseDouble(tableCongNhan.getValueAt(row, 9).toString());
				try {
					new NVKT_ThongTinLuongCongNhan().setVisible(true);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXuat.setBackground(new Color(64, 128, 128));
		panel_4.add(btnXuat);

		JPanel table = new JPanel();
		listCN.add(table);

		JScrollPane scrollPane = new JScrollPane();
		table.add(scrollPane);

		tableCongNhan = new JTable();
		tableCongNhan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "M\u00E3 b\u1EA3ng l\u01B0\u01A1ng", "M\u00E3 c\u00F4ng nh\u00E2n", "H\u1ECD t\u00EAn", "B\u1ED9 ph\u1EADn", "Th\u00E1ng l\u01B0\u01A1ng", "S\u1ED1 ng\u00E0y c\u00F4ng", "S\u1ED1 ng\u00E0y v\u1EAFng", "S\u1ED1 l\u01B0\u1EE3ng ho\u00E0n th\u00E0nh", " L\u01B0\u01A1ng s\u1EA3n ph\u1EA9m", "Ph\u1EE5 c\u1EA5p", "Khen th\u01B0\u1EDFng", "Kh\u1EA5u tr\u1EEB", "L\u01B0\u01A1ng tr\u01B0\u1EDBc thu\u1EBF", "L\u01B0\u01A1ng th\u1EF1c l\u00E3nh"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class, Object.class, String.class, Object.class, Double.class, Integer.class, Integer.class, Double.class, Object.class, Object.class, Object.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCongNhan.getColumnModel().getColumn(0).setResizable(false);
		tableCongNhan.setPreferredScrollableViewportSize(new Dimension(1500, 280));
		scrollPane.setViewportView(tableCongNhan);

	}
	
	private void addDataTableLuongCongNhan(LocalDate date) {
		listCongNhan = congNhanDAO.getAllCongNhan();
		model = (DefaultTableModel) tableCongNhan.getModel();
		model.setRowCount(0);
		int stt = 1;
		for(CongNhan congNhan : listCongNhan) {
			String namThang = thangLuong(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText());
			String[] split = namThang.split("-");
			String ngay = split[0]+ split[1].substring(2,4);
			double luongSanPham = phanCongCongNhan_DAO.getLuongSanPhamCuaMotCongNhan(congNhan, date);
			double luongTruocThue = luongTruocThue(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay), luongSanPham, 
					bangChamCongDAO.getSoNgayVangCuaMotCongNhan(congNhan, date), 1.5);
			BangLuongCongNhan bangLuongCongNhan = new BangLuongCongNhan(
					taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay), new CongNhan(congNhan.getNguoiID()), Integer.parseInt(split[0]), 
					bangChamCongDAO.getSoNgayCongCuaMotCongNhan(congNhan, date), bangChamCongDAO.getSoNgayVangCuaMotCongNhan(congNhan, date), 
					1.5, luongSanPham, 0);
			if(bangLuongCongNhanDAO.getBangLuongCongNhan(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay)) == null)
				bangLuongCongNhanDAO.addBangLuongCongNhan(bangLuongCongNhan);
			else {
				bangLuongCongNhanDAO.updateBangLuongCongNhan(bangLuongCongNhan);
			}
	
			ChiTietPhuCapKhenThuongKhauTruCN chiTietBHXH = cTruCN
					.getChiTietKhenThuongPhuCapKhauTru("KT1220001", taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay));
			ChiTietPhuCapKhenThuongKhauTruCN chiTietBHYT = cTruCN
					.getChiTietKhenThuongPhuCapKhauTru("KT1220002", taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay));		
			ChiTietPhuCapKhenThuongKhauTruCN chiTietKhauTruThue = cTruCN
					.getChiTietKhenThuongPhuCapKhauTru(maThueXacDinhTheoLuong(luongSanPham), taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay));
	
			if(chiTietBHXH == null)
				cTruCN.addChiTietKhauTruBHXH(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay));
			if(chiTietBHYT == null)
				cTruCN.addChiTietKhauTruBHYT(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay));
			if(chiTietKhauTruThue != null) {			
				cTruCN.updateChiTietKhauTruThue(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay),chiTietKhauTruThue.getPhuCapKhenThuongKhauTru().getId(),luongSanPham);
			}	
			else {
				cTruCN.addChiTietKhauTruThue(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay),luongSanPham);
			}
		
			model.addRow(new Object[] {
					stt,
					taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay),
					congNhan.getNguoiID(),
					congNhan.getHoTen(),
					congNhan.getBoPhan().getTenBoPhan(),
					namThang,
					bangChamCongDAO.getSoNgayCongCuaMotCongNhan(congNhan, date),
					bangChamCongDAO.getSoNgayVangCuaMotCongNhan(congNhan, date),
					phanCongCongNhan_DAO.getSoLuongHoanThanhCuaMotCongNhan(congNhan, date),
					luongSanPham,
					tinhTienPhucCap(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay)),
					tinhTienKhenThuong(taoMaBangLuongNhanVien(congNhan.getNguoiID(), ngay)),
					tinhTienKhauTruTruocThue(luongSanPham,bangChamCongDAO.getSoNgayVangCuaMotCongNhan(congNhan, date)) + tinhTienKhauTruThue(luongTruocThue),
					luongTruocThue,
					luongTruocThue - thueThuNhapCaNhan(luongTruocThue)
			});
			
			stt++;
		}
		
	}
	
	private double tinhTienKhauTruTruocThue(double luongCoBan, int soNgayVang) { // chỉ bao gồm BHYT, BHXH, Khấu trừ vắng : 4.500.000
		double khauTruBHXH = luongCoBan * phuCapKhenThuongKhauTruDAO.getPhanTramBHXH()/100;
		double khauTruBHYT = luongCoBan * phuCapKhenThuongKhauTruDAO.getPhanTramBHYT()/100;
		double khauTruVang = 0;
		if(soNgayVang > 4)
			khauTruVang = soNgayVang * 50000;
		return khauTruBHXH + khauTruBHYT + khauTruVang;
	}
	
	private double tinhTienKhauTruThue(double luongTruocThue) { // lương trước thuế 18.000.000 * 0 .15 = 2.700.000
		double khauTru = thueThuNhapCaNhan(luongTruocThue);// lương thực lãnh 15.300.000
		return khauTru;
	}
	
	private double tinhTienKhenThuong(String maLuongID) {	
		double tienKhenThuong = phuCapKhenThuongKhauTruDAO.getSoTienKhenThuongCongNhanByMaLuongID(maLuongID);
		return tienKhenThuong;
	}
	
	private double tinhTienPhucCap(String maLuongID) {	
		double tienPhuCap = phuCapKhenThuongKhauTruDAO.getSoTienPhuCapCongNhanByMaLuongID(maLuongID);
		return tienPhuCap;
	}
	
	private double luongTruocThue(String maLuong,double luongCoBan, int soNgayVang, double heSoLuong) {
		double luongTruocThue = luongCoBan * heSoLuong + tinhTienPhucCap(maLuong) + tinhTienKhenThuong(maLuong) - tinhTienKhauTruTruocThue(luongCoBan, soNgayVang);
		return luongTruocThue;
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
	
	private String thangLuong(String thangLuong) {
		String[] thangNam = thangLuong.split("-");
		String date = thangNam[1] + "-" +thangNam[2];
		return date;
	}
	
	private String taoMaBangLuongNhanVien(String maNhanVien, String ngay) {
		return "BL"+ maNhanVien + ngay;
	}
	
	private int getSoBoPhan() {
		List<BoPhan> list = boPhanDAO.getAllBoPhan();
		return list.size();
	}
	
	private int getSoCongNhan() {
		List<CongNhan> list = congNhanDAO.getAllCongNhan();
		return list.size();
	}
	
	private List<BoPhan> getAllBoPhanVaoCBB(){
		List<BoPhan> list = boPhanDAO.getAllBoPhan();
		return list;
	}
	

}
