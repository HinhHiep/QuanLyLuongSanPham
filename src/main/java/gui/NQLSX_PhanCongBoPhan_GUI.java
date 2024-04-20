package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.toedter.calendar.JDateChooser;

import dao.BangChamCongDAO;
import dao.BangPhanCongBoPhanDAO;
import dao.BoPhanDAO;
import dao.CongDoanDAO;
import dao.CongNhanDAO;
import dao.SanPhamDAO;
import entities.BangPhanCongBoPhan;
import entities.BoPhan;
import entities.CongDoan;
import entities.CongNhan;
import entities.SanPham;
import enums.LoaiSanPham;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;

public class NQLSX_PhanCongBoPhan_GUI extends JPanel implements ActionListener,MouseListener,PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	private JTable tableDSPhanCong;
	private JTextField txtSoLuong;
	private JDateChooser date;
	private JComboBox comLoaiSP;
	private JComboBox comMaSP;
	private JComboBox comTenSP;
	private JComboBox comMaCD;
	private JComboBox comTenCD;
	private JButton btnHoanThanh;
	private BoPhanDAO boPhanDAO=new BoPhanDAO();
	private BangPhanCongBoPhanDAO phanCongBoPhanDAO=new BangPhanCongBoPhanDAO();
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	private JLabel lblKQKieuDang;
	private JLabel lblKQKichThuoc;
	private JLabel lblKQMucUuTien;
	private JLabel lblKQDonGia;
	private JLabel lblKQYeuCauKT;
	private JLabel lblKQSoLuongPhanCD;
	private JLabel lblKQConLai;
	private CongDoanDAO congDoanDAO=new CongDoanDAO();
	private DefaultTableModel model;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JLabel lblKQSoLuongSP;
	private CongNhanDAO congNhanDAO=new CongNhanDAO();
	private JComboBox comCaLamViec;
	private BangChamCongDAO bangChamCongDAO=new BangChamCongDAO();
	private JScrollPane scrollPane;
	private JTable table;
	/**
	 * 
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NQLSX_PhanCongBoPhan_GUI frame = new NQLSX_PhanCongBoPhan_GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	private DefaultTableModel modelBoPhan;
	private JButton btnXemTatCaPhanCong;
	private JButton btnXemThngTin;

	/**
	 * Create the frame.
	 */
	public NQLSX_PhanCongBoPhan_GUI() {
		this.setBackground(Color.WHITE);
		setBounds(100, 100, 1200, 650);
		setBorder(null);
		this.setLayout(new BorderLayout(0, 0));
		createGUI();
		loadData();
	}
	
	public NQLSX_PhanCongBoPhan_GUI(String maBP,String caLamViec) {
		
//		String caLamViec=comCaLamViec.getSelectedItem().toString();
//		ArrayList<BoPhan> ds=bangChamCongDAO.getDSBoPhanDaChamCong(date,caLamViec);
		
		this.setBackground(Color.WHITE);
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setBounds(0, 0, width, height);
		setBorder(null);
		this.setLayout(new BorderLayout(0, 0));
		createGUI();
		loadData();
		Date d=date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		comCaLamViec.setSelectedItem(caLamViec);
		int sl=modelBoPhan.getRowCount();
		int index=-1;
		for (int i = 0; i < sl; i++) {
			if(modelBoPhan.getValueAt(i, 0).equals(maBP)) {
				index=i;
			}
		}
		if(index>=0) {
			table.setRowSelectionInterval(index, index);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Bộ phận được chọn phân công có chấm công là vắng");
		}
		ArrayList<BangPhanCongBoPhan>ds=phanCongBoPhanDAO.getDSPhanCongBoPhan(maBP, date, caLamViec);
		themModel(ds);
		if(ds.size()>0)
		btnHoanThanh.setText("Thêm phân công");
	}
	
	private boolean kiemTraMaBP(String maBP, ArrayList<BoPhan> ds) {
		for (BoPhan boPhan : ds) {
			if(boPhan.getBoPhanID().equals(maBP)) {
				return true;
			}
		}
		return false;
	}

	private void loadData() {
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		String caLamViec=comCaLamViec.getSelectedItem().toString();
		if(date.equals(LocalDate.now())) {
			
			ArrayList<BoPhan> ds=bangChamCongDAO.getDSBoPhanDaChamCong(date, caLamViec);
			for (BoPhan boPhan : ds) {
				if(bangChamCongDAO.kiemTraChamCongBoPhan(boPhan.getBoPhanID(), date, caLamViec))
					themModelBoPhan(boPhan);
			}
		}
		
		comLoaiSP.setSelectedIndex(0);
	}
	private void themModelBoPhan(BoPhan boPhan) {
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		String caLamViec=comCaLamViec.getSelectedItem().toString();
		
		Object o[] =new Object[4];
		o[0]=boPhan.getBoPhanID();
		o[1]=boPhan.getTenBoPhan();
//		o[2]=phanCongBoPhanDAO.getSoLuongCNChamCong(boPhan.getBoPhanID(), date, caLamViec);
		o[2]=phanCongBoPhanDAO.laySoLuongCDDaPhanCong(boPhan.getBoPhanID(), date,caLamViec)+" Công đoạn";
//		System.out.println(phanCongBoPhanDAO.laySoLuongCDDaPhanCong(boPhan.getBoPhanID(), date,caLamViec));
		modelBoPhan.addRow(o);
	}

	private void createGUI() {
		//Header
				JPanel jpHeader = new JPanel();
				jpHeader.setBackground(Color.WHITE);
				FlowLayout fl_jpHeader = (FlowLayout) jpHeader.getLayout();
				fl_jpHeader.setHgap(10);
				fl_jpHeader.setAlignment(FlowLayout.LEFT);
				this.add(jpHeader, BorderLayout.NORTH);
				
				JLabel lblNgay = new JLabel("Ngày:");
				lblNgay.setFont(new Font("Arial", Font.BOLD, 12));
				jpHeader.add(lblNgay);
				
				date =new JDateChooser();
//				date.getCalendarButton().addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						Date d=date.getDate();
//						Instant ins=d.toInstant();
//						LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
//						if(date.isAfter(LocalDate.now())) {
//							btnCapNhat.setEnabled(false);
//							btnHoanThanh.setEnabled(false);
//							btnXoa.setEnabled(false);
//							btnXoaRong.setEnabled(false);
//						}
//						else {
//							btnCapNhat.setEnabled(true);
//							btnHoanThanh.setEnabled(true);
//							btnXoa.setEnabled(true);
//							btnXoaRong.setEnabled(true);
//						}
//					}
//				});
				Date d=new Date();
				date.setDate(d);
				date.setPreferredSize(new Dimension(150, 20));
				jpHeader.add(date);
				
				JLabel lblCaLamViec = new JLabel("Ca làm việc:");
				lblCaLamViec.setFont(new Font("Arial", Font.BOLD, 12));
				jpHeader.add(lblCaLamViec);
				
				comCaLamViec = new JComboBox();
				comCaLamViec.setModel(new DefaultComboBoxModel(new String[] {"Ca sáng", "Ca chiều", "Ca tối"}));
				comCaLamViec.setPreferredSize(new Dimension(100, 22));
				comCaLamViec.setBackground(Color.WHITE);
				comCaLamViec.setFont(new Font("Arial", Font.PLAIN, 12));
				jpHeader.add(comCaLamViec);
		//Center
				
				JPanel jpMain = new JPanel();
				jpMain.setBackground(Color.WHITE);
				this.add(jpMain, BorderLayout.CENTER);
				GridBagLayout gbl_jpMain = new GridBagLayout();
				gbl_jpMain.columnWidths = new int[] {0, 0, 0, 0, 0, 0};
				gbl_jpMain.rowHeights = new int[] {412, 412, 412, 412, 412, 412, 0};
				gbl_jpMain.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
				gbl_jpMain.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
				jpMain.setLayout(gbl_jpMain);
				ImageIcon icon=new ImageIcon(this.getClass().getResource("/timKiem.png"));
		//JpRight
				JPanel jpRight = new JPanel();
				jpRight.setBackground(Color.WHITE);
				GridBagConstraints gbc_jpRight = new GridBagConstraints();
				gbc_jpRight.gridheight = 3;
				gbc_jpRight.gridwidth = 5;
				gbc_jpRight.fill = GridBagConstraints.BOTH;
				gbc_jpRight.gridx = 0;
				gbc_jpRight.gridy = 0;
				jpMain.add(jpRight, gbc_jpRight);
				GridBagLayout gbl_jpRight = new GridBagLayout();
				gbl_jpRight.columnWidths = new int[]{342, 0, 0};
				gbl_jpRight.rowHeights = new int[] {0, 0, 0};
				gbl_jpRight.columnWeights = new double[]{1.0, 1.0, 1.0};
				gbl_jpRight.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				jpRight.setLayout(gbl_jpRight);
				
				JPanel jpBoPhan = new JPanel();
				jpBoPhan.setBackground(Color.WHITE);
				GridBagConstraints gbc_jpBoPhan = new GridBagConstraints();
				gbc_jpBoPhan.gridheight = 2;
				gbc_jpBoPhan.insets = new Insets(0, 0, 0, 5);
				gbc_jpBoPhan.fill = GridBagConstraints.BOTH;
				gbc_jpBoPhan.gridx = 0;
				gbc_jpBoPhan.gridy = 0;
				jpRight.add(jpBoPhan, gbc_jpBoPhan);
				jpBoPhan.setLayout(new BorderLayout(0, 0));
				
				JPanel jpThongTin = new JPanel();
				jpBoPhan.add(jpThongTin, BorderLayout.NORTH);
				jpThongTin.setLayout(new BoxLayout(jpThongTin, BoxLayout.Y_AXIS));
				
				JPanel jpTieuDeBP = new JPanel();
				FlowLayout flowLayout_19 = (FlowLayout) jpTieuDeBP.getLayout();
				flowLayout_19.setHgap(10);
				flowLayout_19.setAlignment(FlowLayout.LEFT);
				jpTieuDeBP.setBackground(Color.WHITE);
				jpThongTin.add(jpTieuDeBP);
				
				ImageIcon icon1 =new ImageIcon(this.getClass().getResource("/timKiem.png"));
				
				JLabel lblBoPhan = new JLabel(" BỘ PHẬN");
				lblBoPhan.setHorizontalAlignment(SwingConstants.CENTER);
				lblBoPhan.setPreferredSize(new Dimension(200, 20));
				lblBoPhan.setForeground(new Color(33, 156, 144));
				lblBoPhan.setFont(new Font("Arial", Font.BOLD, 12));
				jpTieuDeBP.add(lblBoPhan);
				
				scrollPane = new JScrollPane();
				scrollPane.setBackground(Color.WHITE);
				jpBoPhan.add(scrollPane, BorderLayout.CENTER);
				
				String[] header= {"Mã bộ phận","Tên bộ phận","Số công đoạn được phân công"};
				modelBoPhan=new DefaultTableModel(header,0);
				table = new JTable(modelBoPhan);
				table.setFont(new Font("Arial", Font.PLAIN, 12));
				scrollPane.setViewportView(table);
				
				JPanel jpSanPham = new JPanel();
				jpSanPham.setBackground(Color.WHITE);
				GridBagConstraints gbc_jpSanPham = new GridBagConstraints();
				gbc_jpSanPham.insets = new Insets(0, 0, 0, 5);
				gbc_jpSanPham.gridheight = 2;
				gbc_jpSanPham.fill = GridBagConstraints.BOTH;
				gbc_jpSanPham.gridx = 1;
				gbc_jpSanPham.gridy = 0;
				jpRight.add(jpSanPham, gbc_jpSanPham);
				jpSanPham.setLayout(new BorderLayout(0, 0));
				
				JPanel jpThongTinSP = new JPanel();
				jpThongTinSP.setBackground(Color.WHITE);
				jpSanPham.add(jpThongTinSP, BorderLayout.NORTH);
				jpThongTinSP.setLayout(new BoxLayout(jpThongTinSP, BoxLayout.Y_AXIS));
				
				JPanel jpTieuDeSP = new JPanel();
				FlowLayout flowLayout_15 = (FlowLayout) jpTieuDeSP.getLayout();
				flowLayout_15.setHgap(10);
				flowLayout_15.setAlignment(FlowLayout.LEFT);
				jpTieuDeSP.setBackground(Color.WHITE);
				jpThongTinSP.add(jpTieuDeSP);
				
				JLabel lblSnPhm = new JLabel("SẢN PHẨM");
				lblSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
				lblSnPhm.setPreferredSize(new Dimension(200, 20));
				lblSnPhm.setForeground(new Color(33, 156, 144));
				lblSnPhm.setFont(new Font("Arial", Font.BOLD, 12));
				jpTieuDeSP.add(lblSnPhm);
				
				JPanel jpLoaiSP = new JPanel();
				FlowLayout flowLayout_3 = (FlowLayout) jpLoaiSP.getLayout();
				flowLayout_3.setHgap(10);
				flowLayout_3.setAlignment(FlowLayout.LEFT);
				jpLoaiSP.setBackground(Color.WHITE);
				jpThongTinSP.add(jpLoaiSP);
				
				JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
				lblLoaiSP.setPreferredSize(new Dimension(100, 20));
				lblLoaiSP.setFont(new Font("Arial", Font.BOLD, 12));
				jpLoaiSP.add(lblLoaiSP);
				
				comLoaiSP = new JComboBox();
				comLoaiSP.setModel(new DefaultComboBoxModel(new String[] {"Áo sơ mi", "Đồ thể thao", "Váy liền thân", "Áo khoác", "Quần tây", "Quần jean"}));
				comLoaiSP.setPreferredSize(new Dimension(200, 22));
				comLoaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
				comLoaiSP.setEditable(true);
				jpLoaiSP.add(comLoaiSP);
				
				JPanel jpMaSP = new JPanel();
				FlowLayout flowLayout_4 = (FlowLayout) jpMaSP.getLayout();
				flowLayout_4.setHgap(10);
				flowLayout_4.setAlignment(FlowLayout.LEFT);
				jpMaSP.setBackground(Color.WHITE);
				jpThongTinSP.add(jpMaSP);
				
				JLabel lblMaSP = new JLabel("Mã sản phẩm:");
				lblMaSP.setPreferredSize(new Dimension(100, 20));
				lblMaSP.setFont(new Font("Arial", Font.BOLD, 12));
				jpMaSP.add(lblMaSP);
				
				comMaSP = new JComboBox();
				comMaSP.setPreferredSize(new Dimension(200, 22));
				comMaSP.setFont(new Font("Arial", Font.PLAIN, 12));
				comMaSP.setEditable(true);
				jpMaSP.add(comMaSP);
				
				JPanel jpTenSP = new JPanel();
				FlowLayout flowLayout_12 = (FlowLayout) jpTenSP.getLayout();
				flowLayout_12.setHgap(10);
				flowLayout_12.setAlignment(FlowLayout.LEFT);
				jpTenSP.setBackground(Color.WHITE);
				jpThongTinSP.add(jpTenSP);
				
				JLabel lblTenSP = new JLabel("Tên sản phẩm:");
				lblTenSP.setPreferredSize(new Dimension(100, 20));
				lblTenSP.setFont(new Font("Arial", Font.BOLD, 12));
				jpTenSP.add(lblTenSP);
				
				comTenSP = new JComboBox();
				comTenSP.setPreferredSize(new Dimension(200, 22));
				comTenSP.setFont(new Font("Arial", Font.PLAIN, 12));
				comTenSP.setEditable(true);
				jpTenSP.add(comTenSP);
				
				JPanel jpKieuDang = new JPanel();
				FlowLayout flowLayout_16 = (FlowLayout) jpKieuDang.getLayout();
				flowLayout_16.setHgap(10);
				flowLayout_16.setAlignment(FlowLayout.LEFT);
				jpKieuDang.setBackground(Color.WHITE);
				jpThongTinSP.add(jpKieuDang);
				
				JLabel lblKieuDang = new JLabel("Kiểu dáng:");
				lblKieuDang.setPreferredSize(new Dimension(100, 20));
				lblKieuDang.setFont(new Font("Arial", Font.BOLD, 12));
				jpKieuDang.add(lblKieuDang);
				
				lblKQKieuDang = new JLabel("...");
				lblKQKieuDang.setFont(new Font("Arial", Font.PLAIN, 12));
				jpKieuDang.add(lblKQKieuDang);
				
				JPanel jpKichThuoc = new JPanel();
				FlowLayout flowLayout_18 = (FlowLayout) jpKichThuoc.getLayout();
				flowLayout_18.setAlignment(FlowLayout.LEFT);
				flowLayout_18.setHgap(10);
				jpKichThuoc.setBackground(Color.WHITE);
				jpThongTinSP.add(jpKichThuoc);
				
				JLabel lblKchThc = new JLabel("Kích thước:");
				lblKchThc.setPreferredSize(new Dimension(100, 20));
				lblKchThc.setFont(new Font("Arial", Font.BOLD, 12));
				jpKichThuoc.add(lblKchThc);
				
				lblKQKichThuoc = new JLabel("...");
				lblKQKichThuoc.setFont(new Font("Arial", Font.PLAIN, 12));
				jpKichThuoc.add(lblKQKichThuoc);
				
				JPanel jpSoLuong = new JPanel();
				FlowLayout fl_jpSoLuong = (FlowLayout) jpSoLuong.getLayout();
				fl_jpSoLuong.setAlignment(FlowLayout.LEFT);
				fl_jpSoLuong.setHgap(10);
				jpSoLuong.setBackground(Color.WHITE);
				jpThongTinSP.add(jpSoLuong);
				
				JLabel lblSoLuongSP = new JLabel("Số lượng:");
				lblSoLuongSP.setPreferredSize(new Dimension(100, 20));
				lblSoLuongSP.setFont(new Font("Arial", Font.BOLD, 12));
				jpSoLuong.add(lblSoLuongSP);
				
				lblKQSoLuongSP = new JLabel("...");
				lblKQSoLuongSP.setFont(new Font("Arial", Font.PLAIN, 12));
				jpSoLuong.add(lblKQSoLuongSP);
				
				JPanel jpXemThongTin = new JPanel();
				jpXemThongTin.setBackground(Color.WHITE);
				jpThongTinSP.add(jpXemThongTin);
				
				btnXemThngTin = new JButton("Xem thông tin phân công");
				btnXemThngTin.setForeground(Color.WHITE);
				btnXemThngTin.setFont(new Font("Arial", Font.BOLD, 12));
				btnXemThngTin.setBackground(new Color(33, 156, 144));
				jpXemThongTin.add(btnXemThngTin);
				
				JPanel jpCongDoan = new JPanel();
				jpCongDoan.setBackground(Color.WHITE);
				GridBagConstraints gbc_jpCongDoan = new GridBagConstraints();
				gbc_jpCongDoan.gridheight = 2;
				gbc_jpCongDoan.fill = GridBagConstraints.BOTH;
				gbc_jpCongDoan.gridx = 2;
				gbc_jpCongDoan.gridy = 0;
				jpRight.add(jpCongDoan, gbc_jpCongDoan);
				jpCongDoan.setLayout(new BorderLayout(0, 0));
				
				JPanel jpThongTinCD = new JPanel();
				jpThongTinCD.setBackground(Color.WHITE);
				jpCongDoan.add(jpThongTinCD, BorderLayout.NORTH);
				jpThongTinCD.setLayout(new BoxLayout(jpThongTinCD, BoxLayout.Y_AXIS));
				
				JPanel jpTieuDeCD = new JPanel();
				jpTieuDeCD.setBackground(Color.WHITE);
				jpThongTinCD.add(jpTieuDeCD);
				
				JLabel lblCongDoan = new JLabel("CÔNG ĐOẠN");
				lblCongDoan.setPreferredSize(new Dimension(100, 20));
				lblCongDoan.setForeground(new Color(33, 156, 144));
				lblCongDoan.setFont(new Font("Arial", Font.BOLD, 12));
				jpTieuDeCD.add(lblCongDoan);
				
				JPanel jpMaCD = new JPanel();
				FlowLayout flowLayout_5 = (FlowLayout) jpMaCD.getLayout();
				flowLayout_5.setHgap(10);
				flowLayout_5.setAlignment(FlowLayout.LEFT);
				jpMaCD.setBackground(Color.WHITE);
				jpThongTinCD.add(jpMaCD);
				
				JLabel lblMaCD = new JLabel("Mã công đoạn:");
				lblMaCD.setPreferredSize(new Dimension(100, 20));
				lblMaCD.setFont(new Font("Arial", Font.BOLD, 12));
				jpMaCD.add(lblMaCD);
				
				comMaCD = new JComboBox();
				comMaCD.setPreferredSize(new Dimension(200, 22));
				comMaCD.setFont(new Font("Arial", Font.PLAIN, 12));
				comMaCD.setEditable(true);
				jpMaCD.add(comMaCD);
				
				JPanel jpTenCD = new JPanel();
				FlowLayout fl_jpTenCD = (FlowLayout) jpTenCD.getLayout();
				fl_jpTenCD.setHgap(10);
				fl_jpTenCD.setAlignment(FlowLayout.LEFT);
				jpTenCD.setBackground(Color.WHITE);
				jpThongTinCD.add(jpTenCD);
				
				JLabel lblTenCD = new JLabel("Tên công đoạn:");
				lblTenCD.setPreferredSize(new Dimension(100, 20));
				lblTenCD.setFont(new Font("Arial", Font.BOLD, 12));
				jpTenCD.add(lblTenCD);
				
				comTenCD = new JComboBox();
				comTenCD.setPreferredSize(new Dimension(200, 22));
				comTenCD.setFont(new Font("Arial", Font.PLAIN, 12));
				comTenCD.setEditable(true);
				jpTenCD.add(comTenCD);
				
				JPanel jpMucUuTien = new JPanel();
				FlowLayout flowLayout_7 = (FlowLayout) jpMucUuTien.getLayout();
				flowLayout_7.setHgap(10);
				flowLayout_7.setAlignment(FlowLayout.LEFT);
				jpMucUuTien.setBackground(Color.WHITE);
				jpThongTinCD.add(jpMucUuTien);
				
				JLabel lblMucUuTien = new JLabel("Thứ tự thực hiện:");
				lblMucUuTien.setFont(new Font("Arial", Font.BOLD, 12));
				jpMucUuTien.add(lblMucUuTien);
				
				lblKQMucUuTien = new JLabel("...");
				lblKQMucUuTien.setFont(new Font("Arial", Font.PLAIN, 12));
				jpMucUuTien.add(lblKQMucUuTien);
				
				JPanel jpDonGia = new JPanel();
				FlowLayout flowLayout_6 = (FlowLayout) jpDonGia.getLayout();
				flowLayout_6.setHgap(10);
				flowLayout_6.setAlignment(FlowLayout.LEFT);
				jpDonGia.setBackground(Color.WHITE);
				jpThongTinCD.add(jpDonGia);
				
				JLabel lblDonGia = new JLabel("Đơn giá:");
				lblDonGia.setFont(new Font("Arial", Font.BOLD, 12));
				jpDonGia.add(lblDonGia);
				
				lblKQDonGia = new JLabel("...");
				lblKQDonGia.setFont(new Font("Arial", Font.PLAIN, 12));
				jpDonGia.add(lblKQDonGia);
				
				JPanel jpDaHoanThanh = new JPanel();
				FlowLayout fl_jpDaHoanThanh = (FlowLayout) jpDaHoanThanh.getLayout();
				fl_jpDaHoanThanh.setHgap(10);
				fl_jpDaHoanThanh.setAlignment(FlowLayout.LEFT);
				jpDaHoanThanh.setBackground(Color.WHITE);
				jpThongTinCD.add(jpDaHoanThanh);
				
				JLabel lblYeuCauKT = new JLabel("Mức yêu cầu kỹ thuật");
				lblYeuCauKT.setFont(new Font("Arial", Font.BOLD, 12));
				jpDaHoanThanh.add(lblYeuCauKT);
				
				lblKQYeuCauKT = new JLabel("...");
				lblKQYeuCauKT.setMaximumSize(new Dimension(200, 14));
				lblKQYeuCauKT.setPreferredSize(new Dimension(200, 14));
				lblKQYeuCauKT.setFont(new Font("Arial", Font.PLAIN, 12));
				jpDaHoanThanh.add(lblKQYeuCauKT);
				
				JPanel jpDaPhanCong = new JPanel();
				FlowLayout flowLayout_9 = (FlowLayout) jpDaPhanCong.getLayout();
				flowLayout_9.setHgap(10);
				flowLayout_9.setAlignment(FlowLayout.LEFT);
				jpDaPhanCong.setBackground(Color.WHITE);
				jpThongTinCD.add(jpDaPhanCong);
				
				JLabel lblSoLuong = new JLabel("Số lượng:");
				lblSoLuong.setFont(new Font("Arial", Font.BOLD, 12));
				jpDaPhanCong.add(lblSoLuong);
				
				lblKQSoLuongPhanCD = new JLabel("...");
				lblKQSoLuongPhanCD.setFont(new Font("Arial", Font.PLAIN, 12));
				jpDaPhanCong.add(lblKQSoLuongPhanCD);
				
				JPanel jpConLai = new JPanel();
				FlowLayout flowLayout_10 = (FlowLayout) jpConLai.getLayout();
				flowLayout_10.setHgap(10);
				flowLayout_10.setAlignment(FlowLayout.LEFT);
				jpConLai.setBackground(Color.WHITE);
				jpThongTinCD.add(jpConLai);
				
				JLabel lblConLai = new JLabel("Còn lại:");
				lblConLai.setFont(new Font("Arial", Font.BOLD, 12));
				jpConLai.add(lblConLai);
				
				lblKQConLai = new JLabel("...");
				lblKQConLai.setFont(new Font("Arial", Font.PLAIN, 12));
				jpConLai.add(lblKQConLai);
				
				JPanel jpPhanCong = new JPanel();
				FlowLayout flowLayout_11 = (FlowLayout) jpPhanCong.getLayout();
				flowLayout_11.setHgap(10);
				flowLayout_11.setAlignment(FlowLayout.LEFT);
				jpPhanCong.setBackground(Color.WHITE);
				jpThongTinCD.add(jpPhanCong);
				
				JLabel lblNhpSLng = new JLabel("Nhập số lượng phân công:");
				lblNhpSLng.setFont(new Font("Arial", Font.BOLD, 12));
				jpPhanCong.add(lblNhpSLng);
				
				txtSoLuong = new JTextField();
				txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
				jpPhanCong.add(txtSoLuong);
				txtSoLuong.setColumns(10);
				
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				jpThongTinCD.add(panel);
				
				btnHoanThanh = new JButton("Hoàn thành");
				btnHoanThanh.setBackground(new Color(33, 156, 144));
				btnHoanThanh.setForeground(Color.WHITE);
				btnHoanThanh.setFont(new Font("Arial", Font.BOLD, 12));
				panel.add(btnHoanThanh);
				
				btnXoaRong = new JButton("Xóa rỗng");
				btnXoaRong.setForeground(Color.WHITE);
				btnXoaRong.setFont(new Font("Arial", Font.BOLD, 12));
				btnXoaRong.setBackground(new Color(33, 156, 144));
				panel.add(btnXoaRong);
				
				JPanel jpSouth = new JPanel();
				jpSouth.setBackground(Color.WHITE);
				GridBagConstraints gbc_jpSouth = new GridBagConstraints();
				gbc_jpSouth.gridwidth = 5;
				gbc_jpSouth.gridheight = 3;
				gbc_jpSouth.fill = GridBagConstraints.BOTH;
				gbc_jpSouth.gridx = 0;
				gbc_jpSouth.gridy = 3;
				jpMain.add(jpSouth, gbc_jpSouth);
				jpSouth.setLayout(new BorderLayout(0, 0));
				
				JPanel jpTieuDe = new JPanel();
				jpTieuDe.setBackground(Color.WHITE);
				jpSouth.add(jpTieuDe, BorderLayout.NORTH);
				jpTieuDe.setLayout(new BoxLayout(jpTieuDe, BoxLayout.X_AXIS));
				
				JLabel lblDanhSachPhanCong = new JLabel("DANH SÁCH PHÂN CÔNG");
				lblDanhSachPhanCong.setFont(new Font("Arial", Font.BOLD, 12));
				lblDanhSachPhanCong.setForeground(new Color(33, 156, 144));
				lblDanhSachPhanCong.setBackground(Color.WHITE);
				jpTieuDe.add(lblDanhSachPhanCong);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
				flowLayout_2.setAlignment(FlowLayout.RIGHT);
				jpTieuDe.add(panel_1);
				
				btnXoa = new JButton("Xóa phân công");
				btnXoa.setForeground(Color.WHITE);
				btnXoa.setFont(new Font("Arial", Font.BOLD, 12));
				btnXoa.setBackground(new Color(33, 156, 144));
				panel_1.add(btnXoa);
				
				btnCapNhat = new JButton("Cập nhật phân công");
				btnCapNhat.setForeground(Color.WHITE);
				btnCapNhat.setFont(new Font("Arial", Font.BOLD, 12));
				btnCapNhat.setBackground(new Color(33, 156, 144));
				panel_1.add(btnCapNhat);
				
				btnXemTatCaPhanCong = new JButton("Xem tất cả phân công");
				btnXemTatCaPhanCong.setForeground(Color.WHITE);
				btnXemTatCaPhanCong.setFont(new Font("Arial", Font.BOLD, 12));
				btnXemTatCaPhanCong.setBackground(new Color(33, 156, 144));
				panel_1.add(btnXemTatCaPhanCong);
				
				JScrollPane scTableDSPhancong = new JScrollPane();
				scTableDSPhancong.setBorder(null);
				scTableDSPhancong.setBackground(Color.WHITE);
				jpSouth.add(scTableDSPhancong, BorderLayout.CENTER);
				
				String[] headerPhanCong= {"STT","Mã phân công","Mã bộ phận","Tên bộ phận","Số lượng công nhân","Mã sản phẩm","Tên sản phẩm","Loại sản phẩm","Mã công đoạn","Tên công đoạn","Thứ tự thực hiện","Đơn giá","Số lượng phân công","Ngày"};
				model=new DefaultTableModel(headerPhanCong,0);
				tableDSPhanCong = new JTable(model);
				tableDSPhanCong.getColumnModel().getColumn(0).setPreferredWidth(15);
				tableDSPhanCong.getColumnModel().getColumn(3).setPreferredWidth(100);
				tableDSPhanCong.getColumnModel().getColumn(11).setPreferredWidth(100);
				tableDSPhanCong.setFont(new Font("Arial", Font.PLAIN, 12));
				scTableDSPhancong.setViewportView(tableDSPhanCong);
				comMaSP.addActionListener(this);
				comTenSP.addActionListener(this);
				comLoaiSP.addActionListener(this);
				comMaCD.addActionListener(this);
				comTenCD.addActionListener(this);
				btnHoanThanh.addActionListener(this);
				btnXoaRong.addActionListener(this);
				btnXoa.addActionListener(this);
				btnCapNhat.addActionListener(this);
				tableDSPhanCong.addMouseListener(this);
				table.addMouseListener(this);
				btnXemTatCaPhanCong.addActionListener(this);
				btnXemThngTin.addActionListener(this);
				date.addPropertyChangeListener(this);
				comCaLamViec.addActionListener(this);
				
				btnHoanThanh.setText("Thêm phân công");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		try {
			if(o.equals(comLoaiSP)) {
				String loaiSP=comLoaiSP.getSelectedItem().toString();
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
				ArrayList<SanPham>ds=sanPhamDAO.getDSSanPhamDaPhanCongDoan(loaiSP);
				if(ds.size()==0) {
					comMaSP.removeAllItems();
					comTenSP.removeAllItems();
					lblKQKieuDang.setText("");
					lblKQKichThuoc.setText("");
					lblKQSoLuongSP.setText("");
					xoaRongCongDoan();
				}
				
				for (SanPham sanPham : ds) {
					comMaSP.addItem(sanPham.getSanPhamID());
					comTenSP.addItem(sanPham.getTenSanPham());
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
//			xoaRongCongDoan();
		
		if(o.equals(comMaSP)) {
			try {
				int index=comMaSP.getSelectedIndex();
				if(index>=0) {
					comTenSP.setSelectedIndex(index);
					String ma=comMaSP.getSelectedItem().toString();
					SanPham sp=sanPhamDAO.getSanPhamTheoID(ma);
					lblKQKichThuoc.setText(sp.getKichThuoc());
					lblKQKieuDang.setText(sp.getKieuDang().toString());
					lblKQSoLuongSP.setText(sp.getSoLuong()+"");
					ArrayList<CongDoan> list=congDoanDAO.layDSCongDoanTheoMaSP(ma);
					
					xoaRongCongDoan();
					for (CongDoan congDoan : list) {
						comMaCD.addItem(congDoan.getCongDoanID().toString());
						comTenCD.addItem(congDoan.getTenCongDoan().toString());
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
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
					String ma=comMaCD.getSelectedItem().toString();
					CongDoan cd=congDoanDAO.layCongDoanTheoID(ma);
					lblKQMucUuTien.setText(cd.getMucUuTien()+"");
					lblKQDonGia.setText(cd.getDonGia()+"");
					lblKQYeuCauKT.setText(cd.getMucYeuCauKyThuat());
					int sl=(int) (1.1*cd.getSanPham().getSoLuong());
					lblKQSoLuongPhanCD.setText(sl+"");
					lblKQConLai.setText(phanCongBoPhanDAO.laySLConLai(ma)+"");
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(comTenCD)) {
			try {
				int index=comTenCD.getSelectedIndex();
				if(index>=0) {
					comMaCD.setSelectedIndex(index);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnHoanThanh)) {
			if(kiemTra()) {
				try {
					int[] row=table.getSelectedRows();
					Date d= date.getDate();
					Instant ins=d.toInstant();
					LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
					String caLamViec=comCaLamViec.getSelectedItem().toString();
					String macd=comMaCD.getSelectedItem().toString();
					CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
					int slTong=Integer.parseInt(txtSoLuong.getText().trim());
					
					if(row.length >0) {
//						System.out.println(row.length);
						int slPhanCong=0;
//						String mapcDau="";
//						String mabpDau="";
						int tongsobp=row.length;
						int sl=(int)(slTong/tongsobp);
						for (int i = 0; i < row.length; i++) {
							String mabp=modelBoPhan.getValueAt(row[i], 0).toString();
							String maPhancong=phanCongBoPhanDAO.layMaBangPhanCong(mabp, date);
							
							int slbp=sl;
							BoPhan bp=boPhanDAO.getBoPhanByID(mabp);
//							System.out.println(sl);
							
//							if(i==0) {
//								mapcDau=maPhancong;
//								mabpDau=mabp;
//							}
							BangPhanCongBoPhan bpc=new BangPhanCongBoPhan();
							if(i==row.length-1) {
								sl=slTong-slPhanCong;
								 bpc=new BangPhanCongBoPhan(maPhancong, sl, date, caLamViec, cd, bp);
//								System.out.println(bpc.getPhanCongID());
								themModel(bpc);
								
							
								capNhatSoLuongCongDoan(mabp, date);
								//capNhatSoLuongModel(mabp, slbp)
							}
							else {
								bpc=new BangPhanCongBoPhan(maPhancong, sl, date, caLamViec, cd, bp);
//								System.out.println(bpc.getPhanCongID());
								themModel(bpc);
								
								capNhatSoLuongCongDoan(mabp, date);
								slPhanCong=slPhanCong+sl;
							}
							phanCongBoPhanDAO.luuThongTin(bpc);
						}
						JOptionPane.showMessageDialog(null, "Phân công bộ phận thành công");
//						System.out.println(slPhanCong);
//						System.out.println(slTong);
//						if(slPhanCong <slTong) {
//							phanCongBoPhanDAO.capNhatThongTin(mapcDau, slTong-slPhanCong, macd, mabpDau);
//							capNhatSoLuongModel(mabpDau,slTong-slPhanCong);
//						}
						xoaRong();
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
					
			}
			}
		if(o.equals(btnXoaRong)) {
			xoaRong();
			model.setRowCount(0);
		}
		if(o.equals(btnXoa)) {
			int row=tableDSPhanCong.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng để xóa","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				int chon=JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa phân công đã chọn", "Thông báo", JOptionPane.YES_NO_OPTION);
				if(chon==JOptionPane.YES_OPTION) {
					String mabp=model.getValueAt(row, 2).toString();
					String macd=model.getValueAt(row, 8).toString();
					Date d= date.getDate();
					Instant ins=d.toInstant();
					LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
					BangPhanCongBoPhan bpc=phanCongBoPhanDAO.layBangPhanCong(mabp, macd, date);
					phanCongBoPhanDAO.xoaThongTin(bpc);
					model.removeRow(row);
					JOptionPane.showMessageDialog(null, "Xóa phân công thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
					capNhatSoLuongCongDoanXoa(mabp, date);
					xoaRong();
				}
			}
		}
		if(o.equals(btnCapNhat)) {
			int row=tableDSPhanCong.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng để cập nhật","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				Date d= date.getDate();
				Instant ins=d.toInstant();
				LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
				String mabp=model.getValueAt(row, 2).toString();
				String macd=model.getValueAt(row, 8).toString();
				BangPhanCongBoPhan bpc=phanCongBoPhanDAO.layBangPhanCong(mabp, macd, date);
				if(kiemTraKhongSoLuong()) {
					int sl=0;
					try {
						sl=Integer.parseInt(txtSoLuong.getText().toString());
						int slTong=phanCongBoPhanDAO.laySLConLai(macd)+bpc.getSoLuong();
						if(sl>slTong) {
							JOptionPane.showMessageDialog(null, "Số lượng phân công không hợp lệ","Thông báo",JOptionPane.ERROR_MESSAGE);
						}
						else {
							String mcd=comMaCD.getSelectedItem().toString();
//							String mbp=comMaBP.getSelectedItem().toString();
							
							phanCongBoPhanDAO.capNhatThongTin(bpc.getPhanCongID(), sl, macd, mabp);
							capNhatModel(sl, mabp, macd);
							JOptionPane.showMessageDialog(null, "Cập nhật thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
							xoaRong();
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				
			}
		}
		
		if(o.equals(btnXemTatCaPhanCong)) {
			model.setRowCount(0);
			Date d= date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			String caLamViec=comCaLamViec.getSelectedItem().toString();
			ArrayList<BangPhanCongBoPhan> ds=new ArrayList<BangPhanCongBoPhan>();
			ds=phanCongBoPhanDAO.getAllDSPhanCongBoPhan(date, caLamViec);
			themModel(ds);
		}
		if(o.equals(btnXemThngTin)) {
			String masp=comMaSP.getSelectedItem().toString();
//			SanPham sp=sanPhamDAO.getSanPhamTheoID(masp);
			NQLSX_ThongTinSanXuat thongTinGUI=new NQLSX_ThongTinSanXuat(masp);
			thongTinGUI.setVisible(true);
		}
		if(o.equals(comCaLamViec)) {
			Date d=date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			
//			comCaLamViec.setSelectedIndex(0);
			String caLam=comCaLamViec.getSelectedItem().toString();
//			System.out.println(caLam);
			ArrayList<BoPhan> dsbp=boPhanDAO.getAllBoPhanCoCN();
			modelBoPhan.setRowCount(0);
			model.setRowCount(0);
			if(bangChamCongDAO.kiemTraChamCongTheoCa(caLam, date)) {
				ArrayList<BoPhan> ds=bangChamCongDAO.getDSBoPhanDaChamCong(date, caLam);
				for (BoPhan boPhan : ds) {
					themModelBoPhan(boPhan);
				}
			}
			else {
				ArrayList<BoPhan> ds=boPhanDAO.getAllBoPhanCoCN();
				for (BoPhan boPhan : ds) {
					themModelBoPhan(boPhan);
				}
			}
//			if(bangChamCongDAO.kiemTraChamCongTheoCa(caLam, date)) {
//				btnHoanThanh.setText("Thêm phân công");
//				for (BoPhan boPhan : dsbp) {
//					if(bangChamCongDAO.kiemTraChamCongBoPhan(boPhan.getBoPhanID(), date, caLam))
//						themModelBoPhan(boPhan);
//				}
//			}
//			else {
//				
//				ArrayList<BoPhan>ds=boPhanDAO.getAllBoPhanCoCN();
//				for (BoPhan boPhan : ds) {
//					
//						themModelBoPhan(boPhan);
//				}
//			}
			
			if(date.equals(LocalDate.now())) {
				btnHoanThanh.setEnabled(true);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
			}
			else {
				btnHoanThanh.setEnabled(false);
				btnCapNhat.setEnabled(false);
				btnXoa.setEnabled(false);
			}
		}
	}


	private void capNhatSoLuongCongDoan(String mabp,LocalDate ngay) {
		int sl=modelBoPhan.getRowCount();
		ArrayList<BangPhanCongBoPhan>ds=phanCongBoPhanDAO.getDSPhanCongBoPhan(mabp, ngay);
		int soLuong=ds.size()+1;
		for (int i = 0; i < sl; i++) {
			if(modelBoPhan.getValueAt(i, 0).equals(mabp)) {
				modelBoPhan.setValueAt(soLuong+" Công đoạn", i, 2);
			}
		}
		
	}
	private void capNhatSoLuongCongDoanXoa(String mabp,LocalDate ngay) {
		int sl=modelBoPhan.getRowCount();
		ArrayList<BangPhanCongBoPhan>ds=phanCongBoPhanDAO.getDSPhanCongBoPhan(mabp, ngay);
		int soLuong=ds.size()-1;
		if(soLuong<0)
			soLuong=0;
		for (int i = 0; i < sl; i++) {
			if(modelBoPhan.getValueAt(i, 0).equals(mabp)) {
				modelBoPhan.setValueAt(soLuong+" Công đoạn", i, 2);
			}
		}
		
	}

	private void capNhatSoLuongModel(String mabp, int sl) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if(model.getValueAt(i, 1).toString().equals(mabp)) {
				model.setValueAt(sl, i, 12);
			}
		}
	}

//	private int chiaSoLuongBoPhan(String mabp) {
//		
//	}
//	private int chiaSoLuongBoPhan(String mabp) {
//		int sl=0;
//		int tongcn=0;
//		try {
//			int soLuong=Integer.parseInt(txtSoLuong.getText().trim());
//			Date d= date.getDate();
//			Instant ins=d.toInstant();
//			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
//			String caLam=comCaLamViec.getSelectedItem().toString();
//			int[] row=table.getSelectedRows();
//			if(row.length==1) {
//				sl=soLuong;
//			}
//			if(row.length>1) {
//				for (int i = 0; i < row.length; i++) {
//					String ma=modelBoPhan.getValueAt(i, 0).toString();
//					ArrayList<CongNhan> ds=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLam, ma, "Có mặt");
//					tongcn=tongcn+ds.size();
//				}
//				double slTrungBinh=(double) soLuong/tongcn;
//				System.out.println(slTrungBinh);
//				ArrayList<CongNhan> dsCNBoPhan=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLam, mabp, "Có mặt");
//				int slcn=dsCNBoPhan.size();
//				sl=(int) (slTrungBinh*slcn);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		return sl;
//	}

	private void xoaRongCongDoan() {
		comMaCD.removeAllItems();
		comTenCD.removeAllItems();
		lblKQMucUuTien.setText("");
		lblKQDonGia.setText("");
		lblKQYeuCauKT.setText("");
		lblKQSoLuongPhanCD.setText("");
		lblKQConLai.setText("");
		txtSoLuong.setText("");
	}

	private void capNhatModel(int sl,String mabp,String macd) {
		int row=tableDSPhanCong.getSelectedRow();
		if(row<0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn dòng để cập nhật","Thông báo",JOptionPane.ERROR_MESSAGE);
		}else {
			CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
			BoPhan bp=boPhanDAO.getBoPhanByID(mabp);
			model.setValueAt(mabp, row,2);
			model.setValueAt(bp.getTenBoPhan(), row, 3);
			model.setValueAt(congNhanDAO.getSoLuongCNTheoBoPhan(mabp), row, 4);
			model.setValueAt(cd.getSanPham().getSanPhamID(), row,5);
			model.setValueAt(cd.getSanPham().getTenSanPham(), row,6);
			model.setValueAt(cd.getSanPham().getLoaiSanPham(), row,7);
			model.setValueAt(cd.getCongDoanID(), row,8);
			model.setValueAt(cd.getTenCongDoan(), row,9);
			model.setValueAt(cd.getMucUuTien(), row,10);
			model.setValueAt(cd.getDonGia(), row,11);
			model.setValueAt(sl, row, 12);
		}
		
	}

	private void xoaRong() {
		txtSoLuong.setText("");
//		comMaBP.setSelectedIndex(-1);
//		comTenBP.setSelectedIndex(-1);
//		lblKQSoLuong.setText("");
//		lblKQConLai.setText("");
//		lblKQDonGia.setText("");
//		lblKQKichThuoc.setText("");
//		lblKQKieuDang.setText("");
//		lblKQMucUuTien.setText("");
//		lblKQSoLuongPhanCD.setText("");
//		lblKQSoLuongCD.setText("");
//		lblKQSoLuongSP.setText("");
//		lblKQYeuCauKT.setText("");
		comLoaiSP.setSelectedIndex(0);
		comMaSP.setSelectedIndex(0);
//		comTenSP.setSelectedIndex(-1);
		comMaCD.setSelectedIndex(0);
//		comTenCD.setSelectedIndex(-1);
		
		table.clearSelection();
		
		
	}

	private boolean kiemTra() {
//		if(comMaBP.getSelectedIndex()==-1) {
//			JOptionPane.showMessageDialog(this, "Chưa chọn bộ phận","Thông báo",JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
		int[] row=table.getSelectedRows();
		if(row.length==0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn bộ phận");
			return false;
		}
		if(comLoaiSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMaSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMaCD.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String slNhap=txtSoLuong.getText().trim();
		if(slNhap.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số lượng phân công","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {
				int sl=Integer.parseInt(slNhap);
				String macd=comMaCD.getSelectedItem().toString();
				int slConLai=phanCongBoPhanDAO.laySLConLai(macd);
				if(sl<=0 || sl>slConLai) {
					JOptionPane.showMessageDialog(this, "Số lượng phân công chưa phù hợp","Thông báo",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Phải nhập số lượng phân công là số","Thông báo",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
//		String masp=comMaSP.getSelectedItem().toString();
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		String caLam=comCaLamViec.getSelectedItem().toString();
		ArrayList<BangPhanCongBoPhan> dsbangPhanCong=phanCongBoPhanDAO.getAllDSPhanCongBoPhan(date, caLam);
		String macd=comMaCD.getSelectedItem().toString();
		CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
		
		if(dsbangPhanCong.size()>0) {
			boolean flag=false;
			for (BangPhanCongBoPhan pcbp : dsbangPhanCong) {
				if(pcbp.getCongDoan().getMucUuTien()-cd.getMucUuTien()==-1){
					flag=true;
				}
			}
			if(flag==false) {
				JOptionPane.showMessageDialog(this, "Công đoạn ưu tiên làm trước chưa được phân công","Thông báo",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		
		
//		ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(masp);
//		ArrayList<CongDoan> dsCongDoan=phanCongBoPhanDAO.
//		String macd=comMaCD.getSelectedItem().toString();
//		CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
//		int mucUuTien=cd.getMucUuTien();
////		if(model.getR)
////		for (int i = 0; i < model.getRowCount(); i++) {
////			boolean flag=true;
////			if(model.getValueAt(i, 10).toString())
////		}
//		if(dsCongDoan.size()>0) {
//			boolean flag=false;
//			for (CongDoan congDoan : dsCongDoan) {
//				
//				if(congDoan.getMucUuTien()<mucUuTien) {
//					flag=true;
////					if(!phanCongBoPhanDAO.kiemTraPhanCong(congDoan.getCongDoanID())) {
////						JOptionPane.showMessageDialog(null, "Công đoạn ưu tiên làm trước chưa được phân công", "Thông báo", JOptionPane.ERROR_MESSAGE);
////						return false;
////					}
//						
//				}
//			}
//			if(flag==false) {
//				JOptionPane.showMessageDialog(this, "Công đoạn ưu tiên làm trước chưa được","Thông báo",JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//		}
//		
		
		return true;
	}
	public boolean kiemTraKhongSoLuong() {
//		if(comMaBP.getSelectedIndex()==-1) {
//			JOptionPane.showMessageDialog(this, "Chưa chọn bộ phận","Thông báo",JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
		if(comLoaiSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMaSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMaCD.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String masp=comMaSP.getSelectedItem().toString();
		ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(masp);
		String macd=comMaCD.getSelectedItem().toString();
		CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
		int mucUuTien=cd.getMucUuTien();
		for (CongDoan congDoan : dsCongDoan) {
			if(congDoan.getMucUuTien()<mucUuTien) {
				if(!phanCongBoPhanDAO.kiemTraPhanCong(congDoan.getCongDoanID()))
					return false;
			}
		}
		
		return true;
	}
	private void themModel(ArrayList<BangPhanCongBoPhan> ds) {
		for (BangPhanCongBoPhan bpc : ds) {
			themModel(bpc);
			
		}
				
	}
	private void themModel(BangPhanCongBoPhan bpc) {
		
		Object[] o=new Object[14];
		o[0]=model.getRowCount()+1;
		o[1]=bpc.getPhanCongID();
		o[2]=bpc.getBoPhan().getBoPhanID();
		o[3]=bpc.getBoPhan().getTenBoPhan();
		o[4]=congNhanDAO.getSoLuongCNTheoBoPhan(bpc.getBoPhan().getBoPhanID());
		o[5]=bpc.getCongDoan().getSanPham().getSanPhamID();
		o[6]=bpc.getCongDoan().getSanPham().getTenSanPham();
		o[7]=bpc.getCongDoan().getSanPham().getLoaiSanPham();
		o[8]=bpc.getCongDoan().getCongDoanID();
		o[9]=bpc.getCongDoan().getTenCongDoan();
		o[10]=bpc.getCongDoan().getMucUuTien();
		o[11]=bpc.getCongDoan().getDonGia();
		o[12]=bpc.getSoLuong();
		o[13]=bpc.getNgay().toString();
		model.addRow(o);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o=e.getSource();
		if(o.equals(tableDSPhanCong)) {
			int row=tableDSPhanCong.getSelectedRow();
			if(row>=0) {
				String mabp=model.getValueAt(row, 2).toString();
//				comMaBP.setSelectedItem(mabp);
				String loaisp=model.getValueAt(row, 7).toString();
				comLoaiSP.setSelectedItem(loaisp);
				String masp=model.getValueAt(row, 5).toString();
				comMaSP.setSelectedItem(masp);
				String macd=model.getValueAt(row, 8).toString();
				comMaCD.setSelectedItem(macd);
				try {
					int sl=Integer.parseInt(model.getValueAt(row, 12).toString());
					txtSoLuong.setText(sl+"");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
		if(o.equals(table)) {
			model.setRowCount(0);
			int[] row=table.getSelectedRows();
			for (int i = 0; i < row.length; i++) {
				String mabp=modelBoPhan.getValueAt(row[i], 0).toString();
				Date d= date.getDate();
				Instant ins=d.toInstant();
				LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
				String caLamViec=comCaLamViec.getSelectedItem().toString();
				ArrayList<BangPhanCongBoPhan> ds=phanCongBoPhanDAO.getDSPhanCongBoPhan(mabp, date, caLamViec);
				themModel(ds);
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

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if("date".equals(e.getPropertyName())) {
			Date d=(Date) e.getNewValue();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			
			comCaLamViec.setSelectedIndex(0);
			String caLam=comCaLamViec.getSelectedItem().toString();
			
			if(date.isAfter(LocalDate.now())){
				ArrayList<BoPhan> dsbp=boPhanDAO.getAllBoPhanCoCN();
				modelBoPhan.setRowCount(0);
				model.setRowCount(0);
				for (BoPhan boPhan : dsbp) {
					themModelBoPhan(boPhan);
				}
			}
			else {
				ArrayList<BoPhan> dsbp=boPhanDAO.getAllBoPhanCoCN();
				modelBoPhan.setRowCount(0);
				model.setRowCount(0);
				if(date.equals(LocalDate.now())) {
					if(bangChamCongDAO.kiemTraChamCongTheoCa(caLam, date)) {
						for (BoPhan boPhan : dsbp) {
							if(bangChamCongDAO.kiemTraChamCongBoPhan(boPhan.getBoPhanID(), date, caLam))
								themModelBoPhan(boPhan);
						}
					}
					else {
						for (BoPhan boPhan : dsbp) {
							themModelBoPhan(boPhan);
						}
					}
					
				}
				else {
					for (BoPhan boPhan : dsbp) {
						themModelBoPhan(boPhan);
					}
				}
				
			}
			
			
			if(date.isAfter(LocalDate.now())) {
				btnHoanThanh.setEnabled(true);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
			}
			else {
				btnHoanThanh.setEnabled(false);
				btnCapNhat.setEnabled(false);
				btnXoa.setEnabled(false);
			}
			if(date.equals(LocalDate.now())) {
				btnHoanThanh.setEnabled(true);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
			}
		}
		
	}
}
