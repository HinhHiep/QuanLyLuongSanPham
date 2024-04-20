package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BangPhanCongBoPhanDAO;
import dao.BangPhanCongCongNhanDAO;
import dao.CongDoanDAO;
import dao.NguyenLieuDAO;
import dao.SanPhamDAO;
import entities.BangPhanCongBoPhan;
import entities.CongDoan;
import entities.NguyenLieu;
import entities.NguyenLieu_SanPham;
import entities.SanPham;
import enums.LoaiSanPham;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class NQLSX_ThongTinSanXuat extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comLoaiSP;
	private JComboBox comMaSP;
	private JLabel lblKQKieuDang;
	private JComboBox comTenSP;
	private JLabel lblKQKichThuoc;
	private JLabel lblKQSLHoanThanhSP;
	private JLabel lblKQSLConLaiSP;
	private JLabel lblKQSoLuongCD;
	private JLabel lblKQPhanCongCD;
	private JLabel lblKQHoanThanhCD;
	private JLabel lblKQTinhTrang;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtMaPC;
	private JButton btnTimKiem;
	private JDateChooser date;
	private JTable tableNguyenLieu;
	private DefaultTableModel modelNL;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	private CongDoanDAO congDoanDAO=new CongDoanDAO();
	private JLabel lblKQSoLuongSP;
	private JComboBox comMaCD;
	private JComboBox comTenCD;
	private BangPhanCongBoPhanDAO bangPhanCongBoPhanDAO=new BangPhanCongBoPhanDAO();
	private JLabel lblKQTinhTrangCD;
	private BangPhanCongCongNhanDAO bangPhanCongCongNhanDAO=new BangPhanCongCongNhanDAO();
	private JTextField txtMaBP;
	private JComboBox comCaLamViec;
	private JTextField txtTenBP;
	private NguyenLieuDAO nguyenLieuDAO=new NguyenLieuDAO();
	private JButton btnTimKiemNL;
	private JTextField txtMauSac;
	private JTextField txtHoaVan;
	private JTextField txtChatLieu;
	private JTextField txtSoLuongNL;
	private JTextField txtDonViTinh;
	private JRadioButton radDu;
	private JRadioButton radThieu;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnTimKiemNL1;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JLabel lblTinhTrangNL;
	private ButtonGroup groupTinhTrangNL;
	private JComboBox comMaNL;
	private JComboBox comTenNL;
	private JTextField txtMaNL;
	private JTextField txtTenNL;
	private JLabel lblKQChuaPhanCong;
	private JLabel lblKQPhanCongNL;
	private JLabel lblKQHienCoNL;
	private JPanel jpThongTinNL;
	private JLabel lblKQThieu;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NQLSX_ThongTinSanXuat frame = new NQLSX_ThongTinSanXuat();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public NQLSX_ThongTinSanXuat() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1100, 700);
		setLocationRelativeTo(null);
		createGUI();
		comLoaiSP.setSelectedIndex(1);
		comMaSP.setSelectedIndex(1);
		loadData();
	}
	public NQLSX_ThongTinSanXuat(String maSP) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1100, 700);
		setLocationRelativeTo(null);
		createGUI();
		SanPham sp=sanPhamDAO.getSanPhamTheoID(maSP);
		comLoaiSP.setSelectedItem(sp.getLoaiSanPham().toString());
//System.out.println(sp.getLoaiSanPham());
//System.out.println(comLoaiSP.getSelectedItem().toString());
//		
		ArrayList<SanPham>ds=sanPhamDAO.getDSSanPhamTheoLoai(sp.getLoaiSanPham());
		comMaSP.removeAllItems();
		comTenSP.removeAllItems();
		comMaSP.addItem("Tất cả");
		comTenSP.addItem("Tất cả");
		for (SanPham sanPham : ds) {
			comMaSP.addItem(sanPham.getSanPhamID());
			comTenSP.addItem(sanPham.getTenSanPham());
		}
		comMaSP.setSelectedItem(sp.getSanPhamID().toString());
		//comMaSP.setSelectedIndex(1);
		comTenSP.setSelectedItem(sp.getTenSanPham().toString());
		
		
		
		
		lblKQKichThuoc.setText(sp.getKichThuoc());
		lblKQKieuDang.setText(sp.getKieuDang().toString());
		lblKQSoLuongSP.setText(sp.getSoLuong()+"");
		lblKQSLHoanThanhSP.setText(sanPhamDAO.getSoLuongSPHoanThanh(maSP)+"");
		int slConLai=sp.getSoLuong()-sanPhamDAO.getSoLuongSPHoanThanh(maSP);
		if(slConLai>0) {
			lblKQSLConLaiSP.setText(slConLai+"");
		}
		else {
			lblKQSLConLaiSP.setText("0");
		}
		int tinhTrang=sp.getTinhTrang();
		if(tinhTrang==0) {
			lblKQTinhTrang.setText("Đang thực hiện");
		}
		else {
			lblKQTinhTrang.setText("Đã hoàn thành");
		}
		
		ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(maSP);
		comMaCD.removeAllItems();
		comTenCD.removeAllItems();
		model.setRowCount(0);
		for (CongDoan congDoan : dsCongDoan) {
			comMaCD.addItem(congDoan.getCongDoanID());
			comTenCD.addItem(congDoan.getTenCongDoan());
		}
		ArrayList<NguyenLieu_SanPham>dsNguyenLieu=nguyenLieuDAO.getDSNguyenLieu(maSP);
		System.out.println(dsNguyenLieu.size());
		System.out.println(modelNL.getRowCount());
		modelNL.setRowCount(0);
		themModelNguyenLieu(dsNguyenLieu);
		loadData();
		
	}
	
	private void loadData() {
		comMaNL.removeAllItems();
		comTenNL.removeAllItems();
		ArrayList<NguyenLieu>ds=nguyenLieuDAO.getAllNguyenLieu();
		for (NguyenLieu nguyenLieu : ds) {
			comMaNL.addItem(nguyenLieu.getNguyenLieuID());
			comTenNL.addItem(nguyenLieu.getTenNguyenLieu());
		}
		
	}

	private void createGUI() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel jpNorth = new JPanel();
		jpNorth.setBackground(Color.WHITE);
		contentPane.add(jpNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÔNG TIN SẢN XUẤT");
		lblTieuDe.setForeground(new Color(0, 153, 153));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 14));
		jpNorth.add(lblTieuDe);
		
		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.WHITE);
		contentPane.add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel jpThongTin = new JPanel();
		jpThongTin.setBackground(Color.WHITE);
		jpCenter.add(jpThongTin, BorderLayout.NORTH);
		GridBagLayout gbl_jpThongTin = new GridBagLayout();
		gbl_jpThongTin.columnWidths = new int[]{0, 0, 0};
		gbl_jpThongTin.rowHeights = new int[]{0, 0, 0};
		gbl_jpThongTin.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_jpThongTin.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		jpThongTin.setLayout(gbl_jpThongTin);
		
		JPanel jpLeft = new JPanel();
		jpLeft.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpLeft = new GridBagConstraints();
		gbc_jpLeft.insets = new Insets(0, 0, 5, 5);
		gbc_jpLeft.fill = GridBagConstraints.BOTH;
		gbc_jpLeft.gridx = 0;
		gbc_jpLeft.gridy = 0;
		jpThongTin.add(jpLeft, gbc_jpLeft);
		jpLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel jpThongTinSP = new JPanel();
		jpLeft.add(jpThongTinSP, BorderLayout.NORTH);
		jpThongTinSP.setLayout(new BoxLayout(jpThongTinSP, BoxLayout.Y_AXIS));
		
		JPanel jpLoaiSP = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpLoaiSP.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		jpLoaiSP.setBackground(Color.WHITE);
		jpThongTinSP.add(jpLoaiSP);
		
		JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
		lblLoaiSP.setPreferredSize(new Dimension(120, 20));
		lblLoaiSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpLoaiSP.add(lblLoaiSP);
		
		comLoaiSP = new JComboBox();
		comLoaiSP.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Áo sơ mi", "Đồ thể thao", "Váy liền thân", "Áo khoác", "Quần tây", "Quần jean"}));
		comLoaiSP.setPreferredSize(new Dimension(200, 22));
		comLoaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comLoaiSP.setBackground(Color.WHITE);
		jpLoaiSP.add(comLoaiSP);
		
		JPanel jpMaSP = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpMaSP.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpMaSP.setBackground(Color.WHITE);
		jpThongTinSP.add(jpMaSP);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setPreferredSize(new Dimension(120, 20));
		lblMaSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpMaSP.add(lblMaSP);
		
		comMaSP = new JComboBox();
		comMaSP.setPreferredSize(new Dimension(200, 22));
		comMaSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comMaSP.setBackground(Color.WHITE);
		jpMaSP.add(comMaSP);
		
		JPanel jpTenSP = new JPanel();
		FlowLayout fl_jpTenSP = (FlowLayout) jpTenSP.getLayout();
		fl_jpTenSP.setAlignment(FlowLayout.LEFT);
		jpTenSP.setBackground(Color.WHITE);
		jpThongTinSP.add(jpTenSP);
		
		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setPreferredSize(new Dimension(120, 20));
		lblTenSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpTenSP.add(lblTenSP);
		
		comTenSP = new JComboBox();
		comTenSP.setPreferredSize(new Dimension(200, 22));
		comTenSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comTenSP.setBackground(Color.WHITE);
		jpTenSP.add(comTenSP);
		
		JPanel jpKieuDang = new JPanel();
		FlowLayout fl_jpKieuDang = (FlowLayout) jpKieuDang.getLayout();
		fl_jpKieuDang.setAlignment(FlowLayout.LEFT);
		jpKieuDang.setBackground(Color.WHITE);
		jpThongTinSP.add(jpKieuDang);
		
		JLabel lblKieuDang = new JLabel("Kiểu dáng:");
		lblKieuDang.setPreferredSize(new Dimension(120, 20));
		lblKieuDang.setFont(new Font("Arial", Font.BOLD, 12));
		jpKieuDang.add(lblKieuDang);
		
		lblKQKieuDang = new JLabel("...");
		lblKQKieuDang.setFont(new Font("Arial", Font.PLAIN, 12));
		jpKieuDang.add(lblKQKieuDang);
		
		JPanel jpKichThuoc = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jpKichThuoc.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		jpKichThuoc.setBackground(Color.WHITE);
		jpThongTinSP.add(jpKichThuoc);
		
		JLabel lblKichThuoc = new JLabel("Kích thước:");
		lblKichThuoc.setPreferredSize(new Dimension(120, 20));
		lblKichThuoc.setFont(new Font("Arial", Font.BOLD, 12));
		jpKichThuoc.add(lblKichThuoc);
		
		lblKQKichThuoc = new JLabel("...");
		lblKQKichThuoc.setFont(new Font("Arial", Font.PLAIN, 12));
		jpKichThuoc.add(lblKQKichThuoc);
		
		JPanel jpRight = new JPanel();
		jpRight.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpRight = new GridBagConstraints();
		gbc_jpRight.insets = new Insets(0, 0, 5, 0);
		gbc_jpRight.fill = GridBagConstraints.BOTH;
		gbc_jpRight.gridx = 1;
		gbc_jpRight.gridy = 0;
		jpThongTin.add(jpRight, gbc_jpRight);
		jpRight.setLayout(new BorderLayout(0, 0));
		
		JPanel jpThongTinSL = new JPanel();
		jpRight.add(jpThongTinSL, BorderLayout.NORTH);
		jpThongTinSL.setLayout(new BoxLayout(jpThongTinSL, BoxLayout.Y_AXIS));
		
		JPanel jpSoLuong = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) jpSoLuong.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		jpSoLuong.setBackground(Color.WHITE);
		jpThongTinSL.add(jpSoLuong);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 12));
		jpSoLuong.add(lblSoLuong);
		
		lblKQSoLuongSP = new JLabel("...");
		lblKQSoLuongSP.setFont(new Font("Arial", Font.PLAIN, 12));
		jpSoLuong.add(lblKQSoLuongSP);
		
		JPanel jpSoLuongHoanThanh = new JPanel();
		FlowLayout fl_jpSoLuongHoanThanh = (FlowLayout) jpSoLuongHoanThanh.getLayout();
		fl_jpSoLuongHoanThanh.setAlignment(FlowLayout.LEFT);
		jpSoLuongHoanThanh.setBackground(Color.WHITE);
		jpThongTinSL.add(jpSoLuongHoanThanh);
		
		JLabel lblSLHoanThanhSP = new JLabel("Số lượng đã hoàn thành:");
		lblSLHoanThanhSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpSoLuongHoanThanh.add(lblSLHoanThanhSP);
		
		lblKQSLHoanThanhSP = new JLabel("...");
		lblKQSLHoanThanhSP.setFont(new Font("Arial", Font.PLAIN, 12));
		jpSoLuongHoanThanh.add(lblKQSLHoanThanhSP);
		
		JPanel jpSoLuongConLai = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) jpSoLuongConLai.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		jpSoLuongConLai.setBackground(Color.WHITE);
		jpThongTinSL.add(jpSoLuongConLai);
		
		JLabel lblSLConLaiSP = new JLabel("Số lượng còn lại:");
		lblSLConLaiSP.setPreferredSize(new Dimension(120, 20));
		lblSLConLaiSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpSoLuongConLai.add(lblSLConLaiSP);
		
		lblKQSLConLaiSP = new JLabel("...");
		lblKQSLConLaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
		jpSoLuongConLai.add(lblKQSLConLaiSP);
		
		JPanel jpTinhTrangSP = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) jpTinhTrangSP.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		jpTinhTrangSP.setBackground(Color.WHITE);
		jpThongTinSL.add(jpTinhTrangSP);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Arial", Font.BOLD, 12));
		jpTinhTrangSP.add(lblTinhTrang);
		
		lblKQTinhTrang = new JLabel("...");
		lblKQTinhTrang.setFont(new Font("Arial", Font.PLAIN, 12));
		jpTinhTrangSP.add(lblKQTinhTrang);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jpCenter.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel jpDSPhanCong = new JPanel();
		jpDSPhanCong.setBackground(Color.WHITE);
		tabbedPane.addTab("Danh sách phân công", null, jpDSPhanCong, null);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		jpDSPhanCong.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		jpDSPhanCong.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel jpCongDoan = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) jpCongDoan.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		jpCongDoan.setBackground(Color.WHITE);
		panel.add(jpCongDoan);
		
		JLabel lblMaCD = new JLabel("Mã công đoạn:");
		lblMaCD.setPreferredSize(new Dimension(100, 20));
		lblMaCD.setFont(new Font("Arial", Font.BOLD, 12));
		jpCongDoan.add(lblMaCD);
		
		comMaCD = new JComboBox();
		comMaCD.setPreferredSize(new Dimension(150, 22));
		comMaCD.setFont(new Font("Arial", Font.PLAIN, 12));
		comMaCD.setBackground(Color.WHITE);
		jpCongDoan.add(comMaCD);
		
		JLabel lblTenCD = new JLabel("Tên công đoạn:");
		lblTenCD.setPreferredSize(new Dimension(100, 20));
		lblTenCD.setFont(new Font("Arial", Font.BOLD, 12));
		jpCongDoan.add(lblTenCD);
		
		comTenCD = new JComboBox();
		comTenCD.setPreferredSize(new Dimension(150, 22));
		comTenCD.setFont(new Font("Arial", Font.PLAIN, 12));
		comTenCD.setBackground(Color.WHITE);
		jpCongDoan.add(comTenCD);
		
		JLabel lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setFont(new Font("Arial", Font.BOLD, 12));
		jpCongDoan.add(lblTnhTrng);
		
		lblKQTinhTrangCD = new JLabel("...");
		lblKQTinhTrangCD.setFont(new Font("Arial", Font.PLAIN, 12));
		jpCongDoan.add(lblKQTinhTrangCD);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_1.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		
		JLabel lblSoLuongCD = new JLabel("Số lượng:");
		lblSoLuongCD.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblSoLuongCD);
		
		lblKQSoLuongCD = new JLabel("...");
		lblKQSoLuongCD.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblKQSoLuongCD);
		
		JLabel lblPhnCng = new JLabel("Đã phân công:");
		lblPhnCng.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblPhnCng);
		
		lblKQPhanCongCD = new JLabel("...");
		lblKQPhanCongCD.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblKQPhanCongCD);
		
		JLabel lblHonThnh = new JLabel("Đã hoàn thành:");
		lblHonThnh.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblHonThnh);
		
		lblKQHoanThanhCD = new JLabel("...");
		lblKQHoanThanhCD.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblKQHoanThanhCD);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_2.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		
		JLabel lblTKMaPhanCong = new JLabel("Mã phân công:");
		lblTKMaPhanCong.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(lblTKMaPhanCong);
		
		txtMaPC = new JTextField();
		txtMaPC.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_2.add(txtMaPC);
		txtMaPC.setColumns(10);
		
		JLabel lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("Arial", Font.BOLD, 12));
		date=new JDateChooser();
		date.setPreferredSize(new Dimension(150, 20));
		panel_2.add(lblNgay);
		panel_2.add(date);
		
		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(lblCaLamViec);
		
		comCaLamViec = new JComboBox();
		comCaLamViec.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Ca sáng", "Ca chiều", "Ca tối"}));
		comCaLamViec.setPreferredSize(new Dimension(100, 22));
		comCaLamViec.setFont(new Font("Arial", Font.PLAIN, 12));
		comCaLamViec.setBackground(Color.WHITE);
		panel_2.add(comCaLamViec);
		
		JLabel lblMaBP = new JLabel("Mã bộ phận:");
		lblMaBP.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(lblMaBP);
		
		txtMaBP = new JTextField();
		txtMaBP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMaBP.setColumns(10);
		panel_2.add(txtMaBP);
		
		JLabel lblTenBP = new JLabel("Tên bộ phận:");
		lblTenBP.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(lblTenBP);
		
		txtTenBP = new JTextField();
		txtTenBP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenBP.setColumns(10);
		panel_2.add(txtTenBP);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(Color.ORANGE);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(btnTimKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		jpDSPhanCong.add(scrollPane, BorderLayout.CENTER);
		
		String[] header= {"STT","Mã công đoạn","Tên công đoạn","Mã phân công","Ngày","Ca làm việc","Mã bộ phận","Tên bộ phận","Số lượng phân công","Số lượng hoàn thành"};
		model=new DefaultTableModel(header,0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JPanel jpNguyenLieu = new JPanel();
		jpNguyenLieu.setBackground(Color.WHITE);
		tabbedPane.addTab("Nguyên liệu", null, jpNguyenLieu, null);
		jpNguyenLieu.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		jpNguyenLieu.add(scrollPane_1, BorderLayout.CENTER);
		
		String[] headerNL= {"STT","Mã nguyên liệu","Tên nguyên liệu","Màu sắc","Hoa văn","Chất liệu","Số lượng","Đơn vị tính"};
		modelNL=new DefaultTableModel(headerNL,0);
		tableNguyenLieu = new JTable(modelNL);
		scrollPane_1.setViewportView(tableNguyenLieu);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		jpNguyenLieu.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel jpTimKiemNL = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) jpTimKiemNL.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		jpTimKiemNL.setBackground(Color.WHITE);
		panel_3.add(jpTimKiemNL);
		
		JLabel lblMaNL = new JLabel("Mã nguyên liệu:");
		lblMaNL.setPreferredSize(new Dimension(100, 20));
		lblMaNL.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiemNL.add(lblMaNL);
		
		comMaNL = new JComboBox();
		comMaNL.setPreferredSize(new Dimension(120, 22));
		comMaNL.setFont(new Font("Arial", Font.PLAIN, 12));
		comMaNL.setBackground(Color.WHITE);
		jpTimKiemNL.add(comMaNL);
		
		txtMaNL = new JTextField();
		txtMaNL.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMaNL.setColumns(10);
		jpTimKiemNL.add(txtMaNL);
		
		JLabel lblTenNL = new JLabel("Tên nguyên liệu:");
		lblTenNL.setPreferredSize(new Dimension(100, 20));
		lblTenNL.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiemNL.add(lblTenNL);
		ButtonGroup groupTinhTrang=new ButtonGroup();
		
		comTenNL = new JComboBox();
		comTenNL.setPreferredSize(new Dimension(120, 22));
		comTenNL.setFont(new Font("Arial", Font.PLAIN, 12));
		comTenNL.setBackground(Color.WHITE);
		jpTimKiemNL.add(comTenNL);
		
		txtTenNL = new JTextField();
		txtTenNL.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenNL.setColumns(10);
		jpTimKiemNL.add(txtTenNL);
		
		JLabel lblMauSac = new JLabel("Màu sắc:");
		lblMauSac.setPreferredSize(new Dimension(70, 20));
		lblMauSac.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiemNL.add(lblMauSac);
		
		txtMauSac = new JTextField();
		txtMauSac.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMauSac.setColumns(10);
		jpTimKiemNL.add(txtMauSac);
		
		JLabel lblHoaVan = new JLabel("Hoa văn:");
		lblHoaVan.setPreferredSize(new Dimension(70, 20));
		lblHoaVan.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiemNL.add(lblHoaVan);
		
		txtHoaVan = new JTextField();
		txtHoaVan.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHoaVan.setColumns(10);
		jpTimKiemNL.add(txtHoaVan);
		
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setPreferredSize(new Dimension(70, 20));
		lblChatLieu.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiemNL.add(lblChatLieu);
		
		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Arial", Font.PLAIN, 12));
		txtChatLieu.setColumns(10);
		jpTimKiemNL.add(txtChatLieu);
		
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_4.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4);
		
		lblTinhTrangNL = new JLabel("Tình trạng:");
		lblTinhTrangNL.setPreferredSize(new Dimension(100, 20));
		lblTinhTrangNL.setFont(new Font("Arial", Font.BOLD, 12));
		panel_4.add(lblTinhTrangNL);
		
		radDu = new JRadioButton("Đủ");
		radDu.setFont(new Font("Arial", Font.PLAIN, 12));
		radDu.setBackground(Color.WHITE);
		panel_4.add(radDu);
		
		radThieu = new JRadioButton("Thiếu");
		radThieu.setPreferredSize(new Dimension(70, 23));
		radThieu.setFont(new Font("Arial", Font.PLAIN, 12));
		radThieu.setBackground(Color.WHITE);
		panel_4.add(radThieu);
		
		JLabel lblSoLuongNL = new JLabel("Số lượng:");
		lblSoLuongNL.setPreferredSize(new Dimension(100, 20));
		lblSoLuongNL.setFont(new Font("Arial", Font.BOLD, 12));
		panel_4.add(lblSoLuongNL);
		
		txtSoLuongNL = new JTextField();
		txtSoLuongNL.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSoLuongNL.setColumns(10);
		panel_4.add(txtSoLuongNL);
		
		JLabel lblDonViTinh = new JLabel("Đơn vị tính:");
		lblDonViTinh.setPreferredSize(new Dimension(70, 14));
		lblDonViTinh.setFont(new Font("Arial", Font.BOLD, 12));
		panel_4.add(lblDonViTinh);
		
		txtDonViTinh = new JTextField();
		txtDonViTinh.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDonViTinh.setColumns(10);
		panel_4.add(txtDonViTinh);
		
		btnTimKiemNL = new JButton("Tìm kiếm");
		btnTimKiemNL.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiemNL.setBackground(Color.ORANGE);
		panel_4.add(btnTimKiemNL);
		
		jpThongTinNL = new JPanel();
		FlowLayout fl_jpThongTinNL = (FlowLayout) jpThongTinNL.getLayout();
		fl_jpThongTinNL.setAlignment(FlowLayout.LEFT);
		jpThongTinNL.setBackground(Color.WHITE);
		panel_3.add(jpThongTinNL);
		
		JLabel lblHienCo = new JLabel("Hiện có:");
		lblHienCo.setFont(new Font("Arial", Font.BOLD, 12));
		jpThongTinNL.add(lblHienCo);
		
		lblKQHienCoNL = new JLabel("...");
		lblKQHienCoNL.setFont(new Font("Arial", Font.PLAIN, 12));
		jpThongTinNL.add(lblKQHienCoNL);
		
		JLabel lblPhanCongNL = new JLabel("Đã phân công cho sản phẩm:");
		lblPhanCongNL.setFont(new Font("Arial", Font.BOLD, 12));
		jpThongTinNL.add(lblPhanCongNL);
		
		lblKQPhanCongNL = new JLabel("...");
		lblKQPhanCongNL.setFont(new Font("Arial", Font.PLAIN, 12));
		jpThongTinNL.add(lblKQPhanCongNL);
		
		JLabel lblChuaPhanCong = new JLabel("Chưa phân công:");
		lblChuaPhanCong.setFont(new Font("Arial", Font.BOLD, 12));
		jpThongTinNL.add(lblChuaPhanCong);
		
		lblKQChuaPhanCong = new JLabel("...");
		lblKQChuaPhanCong.setFont(new Font("Arial", Font.PLAIN, 12));
		jpThongTinNL.add(lblKQChuaPhanCong);
		
		JLabel lblThieu = new JLabel("Thiếu:");
		lblThieu.setFont(new Font("Arial", Font.BOLD, 12));
		jpThongTinNL.add(lblThieu);
		
		lblKQThieu = new JLabel("...");
		lblKQThieu.setFont(new Font("Arial", Font.PLAIN, 12));
		jpThongTinNL.add(lblKQThieu);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_5.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(Color.WHITE);
		panel_3.add(panel_5);
		
		btnTimKiemNL1 = new JButton("Tìm kiếm");
		btnTimKiemNL1.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiemNL1.setBackground(Color.ORANGE);
		panel_5.add(btnTimKiemNL1);
		
		btnThem = new JButton("Thêm");
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 12));
		btnThem.setBackground(new Color(0, 153, 153));
		panel_5.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 12));
		btnCapNhat.setBackground(new Color(0, 153, 153));
		panel_5.add(btnCapNhat);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoa.setBackground(new Color(0, 153, 153));
		panel_5.add(btnXoa);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoaRong.setBackground(new Color(0, 153, 153));
		panel_5.add(btnXoaRong);
		
		groupTinhTrangNL=new ButtonGroup();
		groupTinhTrangNL.add(radDu);
		groupTinhTrangNL.add(radThieu);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_6.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_6.setBackground(Color.WHITE);
		panel_3.add(panel_6);
		
		JLabel lblDanhSchNguyn = new JLabel("DANH SÁCH NGUYÊN LIỆU CẦN DÙNG");
		lblDanhSchNguyn.setForeground(new Color(0, 153, 153));
		lblDanhSchNguyn.setFont(new Font("Arial", Font.BOLD, 12));
		panel_6.add(lblDanhSchNguyn);
		
		lblTinhTrangNL.setVisible(false);
		radThieu.setVisible(false);
		radDu.setVisible(false);
		btnTimKiemNL.setVisible(false);
		txtMaNL.setVisible(false);
		txtTenNL.setVisible(false);
		
		comLoaiSP.addActionListener(this);
		comMaSP.addActionListener(this);
		comTenSP.addActionListener(this);
		comMaCD.addActionListener(this);
		comTenCD.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnTimKiemNL.addActionListener(this);
		btnTimKiemNL1.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		comMaNL.addActionListener(this);
		comTenNL.addActionListener(this);
		tableNguyenLieu.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(comLoaiSP)) {
			String loaiSP=comLoaiSP.getSelectedItem().toString();
			
			ArrayList<SanPham>ds=new ArrayList<SanPham>();
			if(loaiSP.equals("Tất cả")) {
				ds=sanPhamDAO.getAllSanPham();
				comMaNL.setSelectedIndex(0);
			}
			else {
				LoaiSanPham lsp=null;
				if(loaiSP.equals("Áo sơ mi"))
					lsp=LoaiSanPham.AOSOMI;
				if(loaiSP.equals("Đồ thể thao"))
					lsp=LoaiSanPham.DOTHETHAO;
				if(loaiSP.equals("Váy liền thân"))
					lsp=LoaiSanPham.VAYLIENTHAN;
				if(loaiSP.equals("Áo khoác"))
					lsp=LoaiSanPham.AOKHOAC;
				if(loaiSP.equals("Quần tây"))
					lsp=LoaiSanPham.QUANTAY;
				if(loaiSP.equals("Quần jean"))
					lsp=LoaiSanPham.QUANJEAN;
				ds=sanPhamDAO.getDSSanPhamTheoLoai(lsp);
			}
			
			comMaSP.removeAllItems();
			comTenSP.removeAllItems();
			comMaSP.addItem("Tất cả");
			comTenSP.addItem("Tất cả");
			for (SanPham sanPham : ds) {
				comMaSP.addItem(sanPham.getSanPhamID());
				comTenSP.addItem(sanPham.getTenSanPham());
			}
		}
		if(o.equals(comMaSP)) {
			try {
				int index=comMaSP.getSelectedIndex();
				comMaNL.setSelectedIndex(0);
				if(index>=0) {
					comTenSP.setSelectedIndex(index);
					
					String ma=comMaSP.getSelectedItem().toString();
					if(!ma.equals("Tất cả")) {
						SanPham sp=sanPhamDAO.getSanPhamTheoID(ma);
						lblKQKichThuoc.setText(sp.getKichThuoc());
						lblKQKieuDang.setText(sp.getKieuDang().toString());
						lblKQSoLuongSP.setText(sp.getSoLuong()+"");
						int slHoanThanh=sanPhamDAO.getSoLuongSPHoanThanh(ma);
						lblKQSLHoanThanhSP.setText(slHoanThanh+"");
						int slConLai=sp.getSoLuong()-sanPhamDAO.getSoLuongSPHoanThanh(ma);
						if(slConLai>0) {
							lblKQSLConLaiSP.setText(slConLai+"");
						}
						else {
							lblKQSLConLaiSP.setText("0");
						}
						int tinhTrang=sp.getTinhTrang();
						if(tinhTrang==0) {
							lblKQTinhTrang.setText("Đang thực hiện");
						}
						else {
							if(slHoanThanh>=sp.getSoLuong())
								lblKQTinhTrang.setText("Đã hoàn thành");
							else
								lblKQTinhTrang.setText("Tạm ngưng");
						}
						
						ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(ma);
						comMaCD.removeAllItems();
						comTenCD.removeAllItems();
						model.setRowCount(0);
						for (CongDoan congDoan : dsCongDoan) {
							comMaCD.addItem(congDoan.getCongDoanID());
							comTenCD.addItem(congDoan.getTenCongDoan());
						}
						
	//-----------------------NguyenLieu-----------------
						ArrayList<NguyenLieu_SanPham>dsNguyenLieu=nguyenLieuDAO.getDSNguyenLieu(ma);
						System.out.println(dsNguyenLieu.size());
						System.out.println(modelNL.getRowCount());
						modelNL.setRowCount(0);
						themModelNguyenLieu(dsNguyenLieu);
						//System.out.println(modelNL.getRowCount());
					}
					else {
						lblKQKichThuoc.setText("");
						lblKQKieuDang.setText("");
						lblKQSoLuongSP.setText("");
						lblKQSLHoanThanhSP.setText("");
						lblKQSLConLaiSP.setText("");
						lblKQTinhTrang.setText("");
						comMaCD.removeAllItems();
						comTenCD.removeAllItems();
						model.setRowCount(0);
						lblKQTinhTrangCD.setText("");
						lblKQSoLuongCD.setText("");
						lblKQPhanCongCD.setText("");
						lblKQHoanThanhCD.setText("");
						txtMaPC.setText("");
						date.setDate(null);
						comCaLamViec.setSelectedIndex(0);
						txtMaBP.setText("");
						txtTenBP.setText("");
//------------------------Nguyen lieu--------------------------------------------------
						xoaRong();
						comMaNL.setSelectedIndex(0);
						String masp=comMaSP.getSelectedItem().toString();
						ArrayList<NguyenLieu_SanPham>ds=new ArrayList<NguyenLieu_SanPham>();
						if(masp.equals("Tất cả")) {
							String loaisp=comLoaiSP.getSelectedItem().toString();
							if(loaisp.equals("Tất cả")) {
								ds=nguyenLieuDAO.getALLNguyenLieuSanPham();
							}
							else {
								LoaiSanPham loai=LoaiSanPham.fromString(loaisp);
								ds=nguyenLieuDAO.getNguyenLieuSanPhamTheoLoaiSP(loai);
							}
						}
						else {
							ds=nguyenLieuDAO.getDSNguyenLieu(masp);
						}
						modelNL.setRowCount(0);
						themModelNguyenLieu(ds);
						
					}
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
//				e2.printStackTrace();
				
			}
		}
		if(o.equals(comTenSP)) {
			try {
				int index=comTenSP.getSelectedIndex();
				if(index>=0) {
					comMaSP.setSelectedIndex(index);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(comMaCD)) {
			
			try {
				int index=comMaCD.getSelectedIndex();
				if(index>=0) {
					comTenCD.setSelectedIndex(index);
					
					String macd=comMaCD.getSelectedItem().toString();
					CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
					int sl=(int) (1.1*cd.getSanPham().getSoLuong());
					lblKQSoLuongCD.setText(sl+"");
					lblKQPhanCongCD.setText(""+bangPhanCongBoPhanDAO.getSLPhanCongTheoCongDoan(macd));
					lblKQHoanThanhCD.setText(""+sanPhamDAO.getSoLuongSPHoanThanh(macd));
					
					if(sanPhamDAO.getSoLuongSPHoanThanh(macd)<sl) {
						lblKQTinhTrangCD.setText("Chưa hoàn thành");
					}
					else {
						lblKQTinhTrangCD.setText("Đã hoàn thành");
					}
					
					ArrayList<BangPhanCongBoPhan> dsBangPhanCongBP=(ArrayList<BangPhanCongBoPhan>) bangPhanCongBoPhanDAO.getPhanCongBoPhanByCongDoanID(macd);
					model.setRowCount(0);
					themModel(dsBangPhanCongBP);
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				//e2.printStackTrace();
			}
		}
		if(o.equals(comTenCD)) {
			int index=comTenCD.getSelectedIndex();
			try {
				comMaCD.setSelectedIndex(index);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnTimKiem)) {
			try {
				String maPhanCong=txtMaPC.getText().trim();
				
				Date d=date.getDate();
				
				String caLamViec=comCaLamViec.getSelectedItem().toString();
				String mabp=txtMaBP.getText().trim();
				String tenbp=txtTenBP.getText().trim();
				String macd=comMaCD.getSelectedItem().toString();
//				System.out.println(maPhanCong+"\n"+d);
				model.setRowCount(0);
				ArrayList<BangPhanCongBoPhan> dsBangPhanCongBP=(ArrayList<BangPhanCongBoPhan>) bangPhanCongBoPhanDAO.getPhanCongBoPhanByCongDoanID(macd);
				ArrayList<BangPhanCongBoPhan> dsTimKiem=new ArrayList<BangPhanCongBoPhan>();
				themModel(dsBangPhanCongBP);
				
				for (int i = 0; i < dsBangPhanCongBP.size(); i++) {
					boolean flag=true;
					if(!maPhanCong.equals("") && !maPhanCong.equals(model.getValueAt(i, 3)))
						flag=false;
					if(d!=null) {
						Instant ins=d.toInstant();
						LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
						if(!date.equals(model.getValueAt(i, 4)))
							flag=false;
					}
					if(!caLamViec.equals("Tất cả") && !caLamViec.equals(model.getValueAt(i, 5)))
						flag=false;
					if(!mabp.equals("") && !mabp.equals(model.getValueAt(i, 6)))
						flag=false;
					if(!tenbp.equals("") && !tenbp.equals(model.getValueAt(i, 7)))
						flag=false;
					
					if(flag==true) {
						BangPhanCongBoPhan bpc=bangPhanCongBoPhanDAO.layBangPhanCongTheoID(model.getValueAt(i, 3).toString());
						dsTimKiem.add(bpc);
					}
//					else {
//						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin", "Thông báo", JOptionPane.ERROR_MESSAGE);
//					}
				}
				model.setRowCount(0);
				themModel(dsTimKiem);
			} catch (Exception e2) {
				// TODO: handle exception
				//e2.printStackTrace();
			}
			
		}
//======================NguyenLieu===============
		if(o.equals(btnTimKiemNL1)) {
			if(btnTimKiemNL1.getText().equals("Tìm kiếm")) {
				btnTimKiemNL1.setText("Đóng tìm kiếm");
				lblTinhTrangNL.setVisible(true);
				radThieu.setVisible(true);
				radDu.setVisible(true);
				btnTimKiemNL.setVisible(true);
				txtMaNL.setVisible(true);
				txtTenNL.setVisible(true);
				
				btnThem.setVisible(false);
				btnXoa.setVisible(false);
				btnXoaRong.setVisible(true);
				btnCapNhat.setVisible(false);
				comMaNL.setVisible(false);
				comTenNL.setVisible(false);
				
				txtMauSac.setEditable(true);
				txtHoaVan.setEditable(true);
				txtChatLieu.setEditable(true);
				txtDonViTinh.setEditable(true);
				jpThongTinNL.setVisible(false);
				tableNguyenLieu.clearSelection();
				xoaRong();
			}
			else {
				btnTimKiemNL1.setText("Tìm kiếm");
				lblTinhTrangNL.setVisible(false);
				radThieu.setVisible(false);
				radDu.setVisible(false);
				btnTimKiemNL.setVisible(false);
				txtMaNL.setVisible(false);
				txtTenNL.setVisible(false);
				
				btnThem.setVisible(true);
				btnXoa.setVisible(true);
				btnXoaRong.setVisible(true);
				btnCapNhat.setVisible(true);
				comMaNL.setVisible(true);
				comTenNL.setVisible(true);
				
				txtMauSac.setEditable(false);
				txtHoaVan.setEditable(false);
				txtChatLieu.setEditable(false);
				txtDonViTinh.setEditable(false);
				jpThongTinNL.setVisible(true);
				xoaRong();
				comMaNL.setSelectedIndex(0);
				ArrayList<NguyenLieu_SanPham>ds=nguyenLieuDAO.getDSNguyenLieu(comMaSP.getSelectedItem().toString());
				modelNL.setRowCount(0);
				themModelNguyenLieu(ds);
			}
		}
		
		if(o.equals(btnTimKiemNL)) {
			String maNL=txtMaNL.getText().trim();
			String tenNL=txtTenNL.getText().trim();
			String mauSac=txtMauSac.getText().trim();
			String hoaVan=txtHoaVan.getText().trim();
			String chatLieu=txtChatLieu.getText().trim();
			String soLuong=txtSoLuongNL.getText().trim();
			String donViTinh=txtDonViTinh.getText().trim();
			
			String masp=comMaSP.getSelectedItem().toString();
			ArrayList<NguyenLieu_SanPham> ds=nguyenLieuDAO.getDSNguyenLieu(masp);
			
			ArrayList<NguyenLieu_SanPham>dsTimKiem=new ArrayList<NguyenLieu_SanPham>();
			modelNL.setRowCount(0);
			themModelNguyenLieu(ds);
			if(maNL.equals("")&&tenNL.equals("")&&mauSac.equals("")&&hoaVan.equals("")&&chatLieu.equals("")&&!radDu.isSelected()&&!radThieu.isSelected()&&soLuong.equals("")&&donViTinh.equals("")) {
				modelNL.setRowCount(0);
				themModelNguyenLieu(ds);
			}
			else {
				for (int i = 0; i < ds.size(); i++) {
					boolean flag=true;
					System.out.println(flag);

					if(!maNL.equals("") && !modelNL.getValueAt(i, 1).toString().equals(maNL))
						flag=false;

					if(!tenNL.equals("") && !modelNL.getValueAt(i, 2).toString().equals(tenNL))
						flag=false;
					if(radDu.isSelected() && !modelNL.getValueAt(i, 8).toString().equals("Đủ"))
						flag=false;
					if(radThieu.isSelected() && !modelNL.getValueAt(i, 8).toString().equals("Thiếu"))
						flag=false;
					if(!mauSac.equals("") && !modelNL.getValueAt(i, 3).toString().equals(mauSac))
						flag=false;
					if(!hoaVan.equals("") && !modelNL.getValueAt(i, 4).toString().equals(hoaVan))
						flag=false;
					if(!chatLieu.equals("") && !modelNL.getValueAt(i, 5).toString().equals(chatLieu))
						flag=false;
					if(!soLuong.equals("") && !modelNL.getValueAt(i, 6).toString().equals(soLuong))
						flag=false;
					if(!donViTinh.equals("") && !modelNL.getValueAt(i, 7).toString().equals(donViTinh))
						flag=false;
				
					
					if(flag==true) {
						NguyenLieu_SanPham nlsp=nguyenLieuDAO.getNLSP(masp, modelNL.getValueAt(i, 1).toString());
						dsTimKiem.add(nlsp);
					}
				}
				modelNL.setRowCount(0);
				themModelNguyenLieu(dsTimKiem);
			}
			
		}
		if(o.equals(btnXoaRong)) {
			xoaRong();
		}
		if(o.equals(comMaNL)) {
			try {
				int index=comMaNL.getSelectedIndex();
				comTenNL.setSelectedIndex(index);
				String manl=comMaNL.getSelectedItem().toString();
				NguyenLieu nl=nguyenLieuDAO.getNguyenLieuTheoID(manl);
				txtMauSac.setEditable(false);
				txtMauSac.setText(nl.getMauSac());
				txtHoaVan.setEditable(false);
				txtHoaVan.setText(nl.getHoaVan());
				txtChatLieu.setEditable(false);
				txtChatLieu.setText(nl.getChatLieu());
				txtDonViTinh.setEditable(false);
				txtDonViTinh.setText(nl.getDonViTinh());
				
				lblKQHienCoNL.setText(nl.getSoLuongTon()+"");
				int slPhanCong=nguyenLieuDAO.tinhTongSLNGuyenLieuDaPhanCong(manl);
				lblKQPhanCongNL.setText(slPhanCong+"");
				
				int slConLai=nl.getSoLuongTon()-slPhanCong;
				if(slConLai>0) {
					lblKQChuaPhanCong.setText( slConLai +"");
					lblKQThieu.setText("0");
				}
				else {
					lblKQChuaPhanCong.setText("0");
					lblKQThieu.setText((-slConLai)+"");
				}
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(comTenNL)) {
			try {
				int index=comTenNL.getSelectedIndex();
//				System.out.println(index);
//				System.out.println(comMaNL.getSelectedIndex());
				comMaNL.setSelectedIndex(index);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnThem)) {
			try {
				String manl=comMaNL.getSelectedItem().toString();
				String masp=comMaSP.getSelectedItem().toString();
				String soLuong=txtSoLuongNL.getText().trim();
				if(kiemTraNguyenLieu(manl)) {
					JOptionPane.showMessageDialog(null, "Nguyên liệu đã có","Thông báo",JOptionPane.ERROR_MESSAGE);
					for (int j = 0; j < modelNL.getRowCount(); j++) {
						if(modelNL.getValueAt(j, 1).toString().equals(manl)) {
							tableNguyenLieu.setRowSelectionInterval(j, j);
						}
					}
				}
				else {
					if(soLuong.equals("")) {
						JOptionPane.showMessageDialog(null, "Phải nhập số lượng là số nguyên lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
					}
					else {
						int sl=Integer.parseInt(soLuong);
						if(sl>0) {
							nguyenLieuDAO.addNguyenLieuSanPham(manl, masp, sl);
							NguyenLieu_SanPham nlsp=nguyenLieuDAO.getNLSP(masp, manl);
							themModelNguyenLieu(nlsp);
							xoaRong();
						}
						else {
							JOptionPane.showMessageDialog(null, "Phải nhập số lượng là số nguyên lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		if(o.equals(btnCapNhat)) {
			int row=tableNguyenLieu.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn nguyên liệu để cập nhật","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String soLuong=txtSoLuongNL.getText().trim();
				if(soLuong.equals("")) {
					JOptionPane.showMessageDialog(null, "Phải nhập số lượng là số nguyên lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						int sl=Integer.parseInt(soLuong);
						if(sl>0) {
							String masp=comMaSP.getSelectedItem().toString();
							String manl=modelNL.getValueAt(row, 1).toString();
							if(comMaNL.getSelectedItem().toString().equals(manl)) {
								nguyenLieuDAO.updateNguyenLieuSanPham(manl, masp, sl);
								capNhatModelNL(manl,sl);
								xoaRong();
								JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							}
							else {
								JOptionPane.showMessageDialog(null, "Không được cập nhật mã nguyên liệu","Thông báo",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Phải nhập số lượng là số nguyên lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		if(o.equals(btnXoa)) {
			int row=tableNguyenLieu.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn nguyên liệu để xóa","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				int chon=JOptionPane.showConfirmDialog(null, "Bạn chắc chắc xóa nguyên liệu đang chọn ?","Thông báo",JOptionPane.ERROR_MESSAGE);
				if(chon==JOptionPane.YES_OPTION) {
					
					String manl=modelNL.getValueAt(row, 1).toString();
					String masp=comMaSP.getSelectedItem().toString();
					if(comMaNL.getSelectedItem().toString().equals(manl)) {
						nguyenLieuDAO.deleteNguyenLieuSanPham(manl, masp);
						modelNL.removeRow(row);
						xoaRong();
					}
					else {
						JOptionPane.showMessageDialog(null, "Không được cập nhật mã nguyên liệu","Thông báo",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		}
	}

	private void capNhatModelNL(String manl, int sl) {
		for (int i = 0; i < modelNL.getRowCount(); i++) {
			if(modelNL.getValueAt(i, 1).toString().equals(manl)) {
				modelNL.setValueAt(sl, i, 6);
			}
		}
		
	}

	private boolean kiemTraNguyenLieu(String manl) {
		String masp=comMaSP.getSelectedItem().toString();
		ArrayList<NguyenLieu_SanPham>ds=nguyenLieuDAO.getDSNguyenLieu(masp);
		for (NguyenLieu_SanPham nlsp : ds) {
			if(nlsp.getNguyenLieu().getNguyenLieuID().equals(manl))
				return true;
		}
		return false;
	}

	private void xoaRong() {
		comMaNL.setSelectedIndex(-1);
		comTenNL.setSelectedIndex(-1);
		txtMaNL.setText("");
		txtTenNL.setText("");
		txtMauSac.setText("");
		txtHoaVan.setText("");
		txtChatLieu.setText("");
		txtSoLuongNL.setText("");
		txtDonViTinh.setText("");
		groupTinhTrangNL.clearSelection();
		
		lblKQHienCoNL.setText("...");
		lblKQPhanCongNL.setText("...");
		lblKQChuaPhanCong.setText("...");
	}

	private void themModelNguyenLieu(ArrayList<NguyenLieu_SanPham> dsNguyenLieu) {
		for (NguyenLieu_SanPham nguyenLieu : dsNguyenLieu) {
			themModelNguyenLieu(nguyenLieu);
		}
		
		
	}
	private void themModelNguyenLieu(NguyenLieu_SanPham nl) {
		Object[] o=new Object[9];
		o[0]=modelNL.getRowCount()+1;
		o[1]=nl.getNguyenLieu().getNguyenLieuID();
		o[2]=nl.getNguyenLieu().getTenNguyenLieu();
		o[3]=nl.getNguyenLieu().getMauSac();
		o[4]=nl.getNguyenLieu().getHoaVan();
		o[5]=nl.getNguyenLieu().getChatLieu();
		o[6]=nl.getSoLuong();
		o[7]=nl.getNguyenLieu().getDonViTinh();
//		int slTong=nl.getNguyenLieu().getSoLuongTon();
//		int slDaPhanCong=nguyenLieuDAO.tinhTongSLNGuyenLieuDaPhanCong(nl.getNguyenLieu().getNguyenLieuID());
////		int slConLai=slTong-slDaPhanCong;
//		if(nl.getSoLuong()+slDaPhanCong>slTong) {
//			o[8]="Thiếu"+(nl.getSoLuong()+slDaPhanCong-slTong)+nl.getNguyenLieu().getDonViTinh();
//		}
//		else {
//			o[8]="Đủ";
//		}
		modelNL.addRow(o);
	}
	private void themModel(ArrayList<BangPhanCongBoPhan> dsBangPhanCongBP) {
		for (BangPhanCongBoPhan bangPhanCongBoPhan : dsBangPhanCongBP) {
			themModel(bangPhanCongBoPhan);
		}
		
	}

	private void themModel(BangPhanCongBoPhan bpc) {
		Object[] o=new Object[10];
		o[0]=model.getRowCount()+1;
		o[1]=bpc.getCongDoan().getCongDoanID();
		o[2]=bpc.getCongDoan().getTenCongDoan();
		o[3]=bpc.getPhanCongID();
		o[4]=bpc.getNgay();
		o[5]=bpc.getCaLamViec();
		o[6]=bpc.getBoPhan().getBoPhanID();
		o[7]=bpc.getBoPhan().getTenBoPhan();
		o[8]=bpc.getSoLuong();
		o[9]=bangPhanCongCongNhanDAO.getSLHoanThanh(bpc.getPhanCongID());
		model.addRow(o);
		
	}

	private int getSLHoanThanhSanPham(String maSP) {
		int sl=0;
		ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(maSP);
		int index=dsCongDoan.size()-1;
		if(index>=0) {
			CongDoan cd=dsCongDoan.get(index);
			sl=sanPhamDAO.getSoLuongSPHoanThanh(cd.getCongDoanID());
		}
		return sl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=tableNguyenLieu.getSelectedRow();
		if(row>=0) {
			if(!btnTimKiemNL1.getText().equals("Đóng tìm kiếm")) {
				comMaNL.setSelectedItem(modelNL.getValueAt(row, 1).toString());
				txtSoLuongNL.setText(modelNL.getValueAt(row, 6).toString());
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
