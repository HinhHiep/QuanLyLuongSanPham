package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CongDoanDAO;
import dao.SanPhamDAO;
import entities.CongDoan;
import entities.SanPham;
import enums.LoaiSanPham;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;

public class NQLSX_PhanCongDoanSanPham_GUI extends JPanel implements ActionListener,MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblKQKieuDang;
	private JComboBox comLoaiSP;
	private JComboBox comMaSP;
	private JComboBox comTenSP;
	private JLabel lblKQKichThuoc;
	private JLabel lblKQSoLuong;
	private JButton btnLayMa;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	private JPanel img;
	private JPanel jpHinhAnh;
	private JTextField txtDonGia;
	private JTextField txtYeuCauKT;
	private JButton btnThemCongDoan;
	private JButton btnXoaCongDoan;
	private JButton btnCapNhat;
	private JButton btnXoaRong;
	private JComboBox comMucUuTien;
	private CongDoanDAO congDoanDAO=new CongDoanDAO();

	/**
	 * Create the panel.
	 */
	public NQLSX_PhanCongDoanSanPham_GUI() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 1200, 700);
		createGUI();
		comLoaiSP.setSelectedIndex(0);
	}
	public NQLSX_PhanCongDoanSanPham_GUI(String masp) {
		setBackground(Color.WHITE);
		setBounds(100, 100, 1200, 700);
		createGUI();
		SanPham sp=sanPhamDAO.getSanPhamTheoID(masp);
		comLoaiSP.setSelectedItem(sp.getLoaiSanPham().toString());
//		LoaiSanPham lsp=null;
//		String loai=sp.getLoaiSanPham().toString();
//		if(loai.equals("Áo sơ mi"))
//			lsp=LoaiSanPham.AOSOMI;
//		if(loai.equals("Đồ thể thao"))
//			lsp=LoaiSanPham.DOTHETHAO;
//		if(loai.equals("Váy liền thân"))
//			lsp=LoaiSanPham.VAYLIENTHAN;
//		if(loai.equals("Áo khoác"))
//			lsp=LoaiSanPham.AOKHOAC;
//		if(loai.equals("Quần jean"))
//			lsp=LoaiSanPham.QUANJEAN;
//		if(loai.equals("Quần tây"))
//			lsp=LoaiSanPham.QUANTAY;
//		ArrayList<SanPham>dssp=sanPhamDAO.getDSSanPhamTheoLoai(lsp);
		comMaSP.setSelectedItem(masp);
		
	}

	private void createGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1200, 0};
		gridBagLayout.rowHeights = new int[]{350, 350, 350, 350, 350, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel jpMain = new JPanel();
		GridBagConstraints gbc_jpMain = new GridBagConstraints();
		gbc_jpMain.insets = new Insets(0, 0, 5, 0);
		gbc_jpMain.gridheight = 2;
		gbc_jpMain.fill = GridBagConstraints.BOTH;
		gbc_jpMain.gridx = 0;
		gbc_jpMain.gridy = 0;
		add(jpMain, gbc_jpMain);
		GridBagLayout gbl_jpMain = new GridBagLayout();
		gbl_jpMain.columnWidths = new int[]{0, 0};
		gbl_jpMain.rowHeights = new int[]{0, 0};
		gbl_jpMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_jpMain.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		jpMain.setLayout(gbl_jpMain);
		
		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpCenter = new GridBagConstraints();
		gbc_jpCenter.fill = GridBagConstraints.BOTH;
		gbc_jpCenter.gridx = 0;
		gbc_jpCenter.gridy = 0;
		jpMain.add(jpCenter, gbc_jpCenter);
		GridBagLayout gbl_jpCenter = new GridBagLayout();
		gbl_jpCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_jpCenter.rowHeights = new int[]{0, 0, 0};
		gbl_jpCenter.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_jpCenter.rowWeights = new double[]{1.0, 1.0, 1.0};
		jpCenter.setLayout(gbl_jpCenter);
		
		JPanel jpLeft = new JPanel();
		jpLeft.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpLeft = new GridBagConstraints();
		gbc_jpLeft.gridheight = 2;
		gbc_jpLeft.gridwidth = 2;
		gbc_jpLeft.insets = new Insets(0, 0, 0, 5);
		gbc_jpLeft.fill = GridBagConstraints.BOTH;
		gbc_jpLeft.gridx = 0;
		gbc_jpLeft.gridy = 0;
		jpCenter.add(jpLeft, gbc_jpLeft);
		jpLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel jpThongTinSP = new JPanel();
		jpThongTinSP.setBackground(Color.WHITE);
		jpLeft.add(jpThongTinSP, BorderLayout.CENTER);
		jpThongTinSP.setLayout(new BorderLayout(0, 0));
		
		JPanel jpSanPham = new JPanel();
		jpThongTinSP.add(jpSanPham, BorderLayout.NORTH);
		jpSanPham.setLayout(new BoxLayout(jpSanPham, BoxLayout.Y_AXIS));
		
		JPanel jpLoaiSP = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpLoaiSP.getLayout();
		flowLayout.setHgap(10);
		jpLoaiSP.setBackground(Color.WHITE);
		jpSanPham.add(jpLoaiSP);
		
		JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
		lblLoaiSP.setPreferredSize(new Dimension(100, 14));
		lblLoaiSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpLoaiSP.add(lblLoaiSP);
		
		comLoaiSP = new JComboBox();
		comLoaiSP.setModel(new DefaultComboBoxModel(new String[] {"Áo sơ mi", "Đồ thể thao", "Váy liền thân", "Áo khoác", "Quần tây", "Quần jean"}));
		comLoaiSP.setPreferredSize(new Dimension(250, 22));
		comLoaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comLoaiSP.setEditable(true);
		jpLoaiSP.add(comLoaiSP);
		
		JPanel jpMaSP = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpMaSP.getLayout();
		flowLayout_1.setHgap(10);
		jpMaSP.setBackground(Color.WHITE);
		jpSanPham.add(jpMaSP);
		
		JLabel lblMSnPhm = new JLabel("Mã sản phẩm:");
		lblMSnPhm.setPreferredSize(new Dimension(100, 14));
		lblMSnPhm.setFont(new Font("Arial", Font.BOLD, 12));
		jpMaSP.add(lblMSnPhm);
		
		comMaSP = new JComboBox();
		comMaSP.setPreferredSize(new Dimension(250, 22));
		comMaSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comMaSP.setEditable(true);
		jpMaSP.add(comMaSP);
		
		JPanel jpTenSP = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jpTenSP.getLayout();
		flowLayout_2.setHgap(10);
		jpTenSP.setBackground(Color.WHITE);
		jpSanPham.add(jpTenSP);
		
		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setPreferredSize(new Dimension(100, 14));
		lblTenSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpTenSP.add(lblTenSP);
		
		comTenSP = new JComboBox();
		comTenSP.setPreferredSize(new Dimension(250, 22));
		comTenSP.setFont(new Font("Arial", Font.PLAIN, 12));
		comTenSP.setEditable(true);
		jpTenSP.add(comTenSP);
		
		JPanel jpKieuDang = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) jpKieuDang.getLayout();
		flowLayout_3.setHgap(10);
		jpKieuDang.setBackground(Color.WHITE);
		jpSanPham.add(jpKieuDang);
		
		JLabel lblKieuDang = new JLabel("Kiểu dáng:");
		lblKieuDang.setPreferredSize(new Dimension(100, 14));
		lblKieuDang.setFont(new Font("Arial", Font.BOLD, 12));
		jpKieuDang.add(lblKieuDang);
		
		lblKQKieuDang = new JLabel("...");
		lblKQKieuDang.setPreferredSize(new Dimension(250, 22));
		lblKQKieuDang.setFont(new Font("Arial", Font.PLAIN, 12));
		jpKieuDang.add(lblKQKieuDang);
		
		JPanel jpKichThuoc = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) jpKichThuoc.getLayout();
		flowLayout_5.setHgap(10);
		jpKichThuoc.setBackground(Color.WHITE);
		jpSanPham.add(jpKichThuoc);
		
		JLabel lblKichThuoc = new JLabel("Kích thước:");
		lblKichThuoc.setPreferredSize(new Dimension(100, 14));
		lblKichThuoc.setFont(new Font("Arial", Font.BOLD, 12));
		jpKichThuoc.add(lblKichThuoc);
		
		lblKQKichThuoc = new JLabel("...");
		lblKQKichThuoc.setPreferredSize(new Dimension(250, 22));
		lblKQKichThuoc.setFont(new Font("Arial", Font.PLAIN, 12));
		jpKichThuoc.add(lblKQKichThuoc);
		
		JPanel jpSoLuong = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) jpSoLuong.getLayout();
		flowLayout_6.setHgap(10);
		jpSoLuong.setBackground(Color.WHITE);
		jpSanPham.add(jpSoLuong);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setPreferredSize(new Dimension(100, 14));
		lblSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 12));
		jpSoLuong.add(lblSoLuong);
		
		lblKQSoLuong = new JLabel("...");
		lblKQSoLuong.setPreferredSize(new Dimension(250, 22));
		lblKQSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
		jpSoLuong.add(lblKQSoLuong);
		
		jpHinhAnh = new JPanel();
		jpHinhAnh.setBackground(Color.WHITE);
		jpLeft.add(jpHinhAnh, BorderLayout.WEST);
		
//		img = new JPanel();
//		img.setBackground(Color.BLACK);
//		img.setPreferredSize(new Dimension(100, 100));
//		jpHinhAnh.add(img);
		
		
		JPanel jpRight = new JPanel();
		jpRight.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpRight = new GridBagConstraints();
		gbc_jpRight.gridheight = 2;
		gbc_jpRight.gridwidth = 3;
		gbc_jpRight.fill = GridBagConstraints.BOTH;
		gbc_jpRight.gridx = 2;
		gbc_jpRight.gridy = 0;
		jpCenter.add(jpRight, gbc_jpRight);
		jpRight.setLayout(new BorderLayout(0, 0));
		
		JPanel jpThongTinCD = new JPanel();
		jpThongTinCD.setBackground(Color.WHITE);
		jpRight.add(jpThongTinCD, BorderLayout.NORTH);
		jpThongTinCD.setLayout(new BoxLayout(jpThongTinCD, BoxLayout.Y_AXIS));
		
		JPanel jpTieuDe = new JPanel();
		jpTieuDe.setBackground(Color.WHITE);
		jpThongTinCD.add(jpTieuDe);
		jpTieuDe.setLayout(new BorderLayout(0, 0));
		
		JPanel jpMaCD = new JPanel();
		jpMaCD.setBackground(Color.WHITE);
		jpThongTinCD.add(jpMaCD);
		
		JLabel lblMaCD = new JLabel("Mã công đoạn:");
		lblMaCD.setPreferredSize(new Dimension(100, 14));
		lblMaCD.setFont(new Font("Arial", Font.BOLD, 12));
		jpMaCD.add(lblMaCD);
		
		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMaCD.setEditable(false);
		txtMaCD.setColumns(40);
		txtMaCD.setBackground(Color.WHITE);
		jpMaCD.add(txtMaCD);
		
		btnLayMa = new JButton("Lấy mã");
		btnLayMa.setPreferredSize(new Dimension(80, 23));
		btnLayMa.setFont(new Font("Arial", Font.BOLD, 12));
		btnLayMa.setBackground(new Color(233, 184, 36));
		jpMaCD.add(btnLayMa);
		
		JPanel jpTenCD = new JPanel();
		jpTenCD.setBackground(Color.WHITE);
		jpThongTinCD.add(jpTenCD);
		
		JLabel lblTenCD = new JLabel("Tên công đoạn:");
		lblTenCD.setPreferredSize(new Dimension(100, 14));
		lblTenCD.setFont(new Font("Arial", Font.BOLD, 12));
		jpTenCD.add(lblTenCD);
		
		txtTenCD = new JTextField();
		txtTenCD.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenCD.setColumns(48);
		jpTenCD.add(txtTenCD);
		
		JPanel jpMucUuTien = new JPanel();
		jpMucUuTien.setBackground(Color.WHITE);
		jpThongTinCD.add(jpMucUuTien);
		
		JLabel lblMucUuTien = new JLabel("Mức ưu tiên:");
		lblMucUuTien.setPreferredSize(new Dimension(100, 14));
		lblMucUuTien.setFont(new Font("Arial", Font.BOLD, 12));
		jpMucUuTien.add(lblMucUuTien);
		
		comMucUuTien = new JComboBox();
		comMucUuTien.setModel(new DefaultComboBoxModel(new Integer[] {1,2,3,4,5,6,7,8,9,10}));
		comMucUuTien.setPreferredSize(new Dimension(532, 22));
		comMucUuTien.setFont(new Font("Arial", Font.PLAIN, 12));
		comMucUuTien.setEditable(true);
		jpMucUuTien.add(comMucUuTien);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		jpThongTinCD.add(panel);
		
		JLabel lblYeuCauKT = new JLabel("Yêu cầu kỹ thuật:");
		lblYeuCauKT.setPreferredSize(new Dimension(100, 14));
		lblYeuCauKT.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(lblYeuCauKT);
		
		txtYeuCauKT = new JTextField();
		txtYeuCauKT.setFont(new Font("Arial", Font.PLAIN, 12));
		txtYeuCauKT.setColumns(48);
		panel.add(txtYeuCauKT);
		
		JPanel jpDonGia = new JPanel();
		jpDonGia.setBackground(Color.WHITE);
		jpThongTinCD.add(jpDonGia);
		
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setPreferredSize(new Dimension(100, 14));
		lblDonGia.setFont(new Font("Arial", Font.BOLD, 12));
		jpDonGia.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDonGia.setColumns(48);
		jpDonGia.add(txtDonGia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		jpThongTinCD.add(panel_1);
		
		btnThemCongDoan = new JButton("Thêm công đoạn");
		btnThemCongDoan.setForeground(Color.WHITE);
		btnThemCongDoan.setFont(new Font("Arial", Font.BOLD, 12));
		btnThemCongDoan.setBackground(new Color(33, 156, 144));
		panel_1.add(btnThemCongDoan);
		
		btnXoaCongDoan = new JButton("Xóa công đoạn");
		btnXoaCongDoan.setForeground(Color.WHITE);
		btnXoaCongDoan.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoaCongDoan.setBackground(new Color(33, 156, 144));
		panel_1.add(btnXoaCongDoan);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 12));
		btnCapNhat.setBackground(new Color(33, 156, 144));
		panel_1.add(btnCapNhat);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoaRong.setBackground(new Color(33, 156, 144));
		panel_1.add(btnXoaRong);
		
		JPanel jpSouth = new JPanel();
		jpSouth.setBackground(Color.WHITE);
		GridBagConstraints gbc_jpSouth = new GridBagConstraints();
		gbc_jpSouth.gridheight = 3;
		gbc_jpSouth.fill = GridBagConstraints.BOTH;
		gbc_jpSouth.gridx = 0;
		gbc_jpSouth.gridy = 2;
		add(jpSouth, gbc_jpSouth);
		jpSouth.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_2.getLayout();
		flowLayout_9.setHgap(10);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(Color.WHITE);
		jpSouth.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblDanHSch = new JLabel("DAN H SÁCH CÔNG ĐOẠN");
		lblDanHSch.setForeground(new Color(33, 156, 144));
		lblDanHSch.setFont(new Font("Arial", Font.BOLD, 12));
		panel_2.add(lblDanHSch);
		
		String[] header= {"STT","Loại sản phẩm","Mã sản phẩm","Tên sản phẩm","Kiểu dáng","Kích thước","Mã công đoạn","Tên công đoạn","Mức ưu tiên","Yêu cầu kỹ thuật","Đơn giá","Số lượng"};
		model=new DefaultTableModel(header,0);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		JScrollPane sc=new JScrollPane(table);
		jpSouth.add(sc, BorderLayout.CENTER);
		
		txtMaCD.setEditable(true);
		
		comMaSP.addActionListener(this);
		comTenSP.addActionListener(this);
		comLoaiSP.addActionListener(this);
		btnLayMa.addActionListener(this);
		btnThemCongDoan.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoaCongDoan.addActionListener(this);
		btnCapNhat.addActionListener(this);
		table.addMouseListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(comMaSP)) {
			try {
				if(comMaSP.getSelectedIndex()!=-1) {
					int index=comMaSP.getSelectedIndex();
					if(index>=0) {
						comTenSP.setSelectedIndex(index);
						String ma=comMaSP.getSelectedItem().toString();
						SanPham sp=sanPhamDAO.getSanPhamTheoID(ma);
						comLoaiSP.setSelectedItem(sp.getLoaiSanPham().toString());
						lblKQKieuDang.setText(sp.getKieuDang().toString());
						lblKQKichThuoc.setText(sp.getKichThuoc().toString());
						lblKQSoLuong.setText(sp.getSoLuong()+"");
						String url=sp.getHinhAnh();
						System.out.println(url);
						model.setRowCount(0);
						ArrayList<CongDoan> ds=congDoanDAO.layDSCongDoanTheoMaSP(ma);
						for (CongDoan congDoan : ds) {
							themModel(congDoan);
						}
					}
				}
				
//					jpHinhAnh.removeAll();
//					img = new JPanel() {
//						@Override
//						protected void paintComponent(Graphics g) {
//							super.paintComponent(g);
//							try {
//								URL imageURL=new URL(url);
//								Image image=ImageIO.read(imageURL);
//								g.drawImage(image, 0, 0, this);
//							} catch (Exception e) {
//								// TODO: handle exception
//								e.printStackTrace();
//							}
//						}
//					};
//					img.setBackground(Color.BLACK);
//					img.setPreferredSize(new Dimension(100, 100));
//					jpHinhAnh.add(img);
					
			} catch (Exception e2) {
				//e2.printStackTrace();
			}
		}
		if(o.equals(comTenSP)) {
			try {
				int index=comTenSP.getSelectedIndex();
				if(index>=0) {
					comMaSP.setSelectedIndex(index);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(o.equals(comLoaiSP)) {
			if(comLoaiSP.getSelectedIndex()>=0) {
				String loaisp=comLoaiSP.getSelectedItem().toString();
				if(!loaisp.equals("")) {
					LoaiSanPham loai=null;
					if(loaisp.equals("Áo sơ mi"))
						loai=LoaiSanPham.AOSOMI;
					if(loaisp.equals("Áo khoác"))
						loai=LoaiSanPham.AOKHOAC;
					if(loaisp.equals("Đồ thể thao"))
						loai=LoaiSanPham.DOTHETHAO;
					if(loaisp.equals("Váy liền thân"))
						loai=LoaiSanPham.VAYLIENTHAN;
					if(loaisp.equals("Quần tây"))
						loai=LoaiSanPham.QUANTAY;
					if(loaisp.equals("Quần jean"))
						loai=LoaiSanPham.QUANJEAN;
					ArrayList<SanPham> ds=sanPhamDAO.getDSSanPhamTheoLoai(loai);
					comMaSP.removeAllItems();
					comTenSP.removeAllItems();
					for (SanPham sanPham : ds) {
						comMaSP.addItem(sanPham.getSanPhamID());
						comTenSP.addItem(sanPham.getTenSanPham());
					}
				}
			}
			
		}
		if(o.equals(btnLayMa)) {
//			String loaisp=comLoaiSP.getSelectedItem().toString();
//			LoaiSanPham loai=null;
//			if(loaisp.equals("Áo sơ mi"))
//				loai=LoaiSanPham.AOSOMI;
//			if(loaisp.equals("Áo khoác"))
//				loai=LoaiSanPham.AOKHOAC;
//			if(loaisp.equals("Đồ thể thao"))
//				loai=LoaiSanPham.DOTHETHAO;
//			if(loaisp.equals("Váy liền thân"))
//				loai=LoaiSanPham.VAYLIENTHAN;
//			if(loaisp.equals("Quần tây"))
//				loai=LoaiSanPham.QUANTAY;
//			if(loaisp.equals("Quần jean"))
//				loai=LoaiSanPham.QUANJEAN;
			String masp=comMaSP.getSelectedItem().toString();
			if(masp.equals("")) {
				JOptionPane.showMessageDialog(null, "Chưa chọn mã phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				txtMaCD.setText(congDoanDAO.layMa(masp));
			}
		}
		if(o.equals(btnThemCongDoan)) {
			if(kiemTra()) {
				String ma=txtMaCD.getText().trim();
				if(kiemTraTrungMaCD(ma)) {
					JOptionPane.showMessageDialog(null, "Trùng mã công đoạn","Thông báo",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String tenCD=txtTenCD.getText().trim();
					int mucUuTien=(int) comMucUuTien.getSelectedItem();
					String yeuCauKT=txtYeuCauKT.getText().trim();
					String donGia=txtDonGia.getText().trim();
					double gia=0;
					if(!donGia.equals("")) {
						try {
							gia=Double.parseDouble(donGia);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					String masp=comMaSP.getSelectedItem().toString();
					SanPham sp=sanPhamDAO.getSanPhamTheoID(masp);
					CongDoan cd=new CongDoan(ma, tenCD, mucUuTien, yeuCauKT, gia, sp);
					congDoanDAO.themCongDoan(cd);
					themModel(cd);
					xoaRong();
				}
				
			}
		}
		if(o.equals(btnXoaRong)) {
			xoaRong();
			model.setRowCount(0);
			btnLayMa.setEnabled(true);
		}
		if(o.equals(btnXoaCongDoan)) {
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng để xoá","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				int chon=JOptionPane.showConfirmDialog(this, "Khi xóa công đoạn thì tất cả phân công của công đoạn sẽ bị xóa.\n Bạn chắc chắn xoá công đoạn đã chọn","Thông báo",JOptionPane.YES_NO_OPTION);
				if(chon==JOptionPane.YES_OPTION) {
					

					String ma=model.getValueAt(row, 6).toString();
					System.out.println(ma);
					congDoanDAO.xoaCongDoan(ma);
					model.removeRow(row);
					xoaRong();
				}
			}
		}
		if(o.equals(btnCapNhat)) {
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng để cập nhật","Thông báo",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String ma=txtMaCD.getText().trim();
				String maModel=model.getValueAt(row, 6).toString();
				if(ma.equals(maModel)) {
					String ten=txtTenCD.getText().trim();
					int uuTien=(int) comMucUuTien.getSelectedItem();
					String yckt=txtYeuCauKT.getText().trim();
					String dg=txtDonGia.getText().trim();
					double donGia=0;
					if(kiemTra()) {
						try {
							donGia=Double.parseDouble(dg);
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
						congDoanDAO.capNhatCongDoan(ma, ten, uuTien, yckt, donGia);
						capNhatModel();
						xoaRong();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Không được cập nhật mã công đoạn","Thông báo",JOptionPane.ERROR_MESSAGE);

				}
				
			}
			
		}
//		if(o.equals(btnTimKiem)) {
//			try {
//
//				String masp=comMaSP.getSelectedItem().toString();
//				String macd=txtMaCD.getText().trim();
//				String tencd=txtTenCD.getText().trim();
//				String yeuCauKyThuat=txtYeuCauKT.getText().trim();
//				String donGia=txtDonGia.getText().trim();
//				
//				
//				ArrayList<CongDoan> ds=congDoanDAO.layDSCongDoanTheoMaSP(masp);
//System.out.println(masp);
//System.out.println(ds.size());
//				ArrayList<CongDoan> dsTimKiem=new ArrayList<CongDoan>();
//				model.setRowCount(0);
//				for (CongDoan congDoan : ds) {
//System.out.println(congDoan.getSanPham().getLoaiSanPham().toString());
//					themModel(congDoan);
//				}
//				if(masp.equals("")) {
//					model.setRowCount(0);
//					JOptionPane.showMessageDialog(null, "Chưa chọn mã sản phẩm");
//				}
//				else {
//					if(macd.equals("") && tencd.equals("") && yeuCauKyThuat.equals("") && donGia.equals("")&& comMucUuTien.getSelectedIndex()==-1) {
//						dsTimKiem=congDoanDAO.layDSCongDoanTheoMaSP(masp);
//					}
//					else {
//						for (int i = 0; i < ds.size(); i++) {
//							boolean flag=true;
//							if(!macd.equals("") && !model.getValueAt(i, 6).toString().equals(macd))
//								flag=false;
//							if(!tencd.equals("") && !model.getValueAt(i, 7).toString().equals(tencd))
//								flag=false;
//							if(comMucUuTien.getSelectedIndex()==-1 && !model.getValueAt(i, 8).toString().equals(comMucUuTien.getSelectedItem().toString()))
//								flag=false;
//							if(!yeuCauKyThuat.equals("") && !model.getValueAt(i, 9).toString().equals(yeuCauKyThuat))
//								flag=false;
//							if(!donGia.equals("") && !model.getValueAt(i, 10).toString().equals(donGia))
//								flag=false;
//							
//							if(flag==true) {
//								CongDoan cd=congDoanDAO.layCongDoanTheoID(macd);
//								dsTimKiem.add(cd);
//							}
//						}
//						model.setRowCount(0);
//						for (CongDoan congDoan : dsTimKiem) {
//							themModel(congDoan);
//						}
//					}
//				}
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//			
//		}
	}

	

	private boolean kiemTraTrungMaCD(String macd) {
		ArrayList<CongDoan>ds=congDoanDAO.getAllCongDoan();
		for (CongDoan congDoan : ds) {
			if(congDoan.getCongDoanID().equals(macd))
				return true;
		}
		return false;
	}
	private void capNhatModel() {
		int row=table.getSelectedRow();
		if(row>=0) {
			model.setValueAt(txtTenCD.getText(), row, 7);
			model.setValueAt(comMucUuTien.getSelectedItem(), row, 8);
			model.setValueAt(txtYeuCauKT.getText(), row, 9);
			model.setValueAt(txtDonGia.getText(), row, 10);
		}
		
	}

	private void xoaRong() {
		comLoaiSP.setSelectedIndex(-1);
		comMaSP.setSelectedIndex(-1);
		comTenSP.setSelectedIndex(-1);
		lblKQKichThuoc.setText("");
		lblKQKieuDang.setText("");
		lblKQSoLuong.setText("");
		txtMaCD.setText("");
		txtTenCD.setText("");
		comMucUuTien.setSelectedIndex(-1);
		txtYeuCauKT.setText("");
		txtDonGia.setText("");
		
	}

	private void themModel(CongDoan cd) {
		Object[] o=new Object[12];
		o[0]=model.getRowCount()+1;
		o[1]=cd.getSanPham().getLoaiSanPham().toString();
		o[2]=cd.getSanPham().getSanPhamID();
		o[3]=cd.getSanPham().getTenSanPham();
		o[4]=cd.getSanPham().getKieuDang();
		o[5]=cd.getSanPham().getKichThuoc();
		o[6]=cd.getCongDoanID();
		o[7]=cd.getTenCongDoan();
		o[8]=cd.getMucUuTien();
		o[9]=cd.getMucYeuCauKyThuat();
		o[10]=cd.getDonGia();
		o[11]=(int)(cd.getSanPham().getSoLuong()*1.1);
		model.addRow(o);
	}

	private boolean kiemTra() {
		String ma=txtMaCD.getText().trim();
		String tenCD=txtTenCD.getText().trim();
		int mucUuTien=0;
		String yeuCauKT=txtYeuCauKT.getText().trim();
		String donGia=txtDonGia.getText().trim();
		double gia=0;
		if(comLoaiSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMaSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(ma.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã công đoạn","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
//		}else {
//			String masp=comMaSP.getSelectedItem().toString();
//			String mamoi=masp.substring(2);
//			String pat="/^"+mamoi+"[0-9]{3}$/";
//			Pattern patMa=Pattern.compile(pat);
//			Matcher matMa=patMa.matcher(ma);
//			if(!matMa.matches()) {
//				JOptionPane.showMessageDialog(this, "Mã công đoạn chưa đúng");
//				return false;
//			}
//		}
		if(tenCD.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên công đoạn","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comMucUuTien.getSelectedIndex()<0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn mức ưu tiên","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			mucUuTien=(int) comMucUuTien.getSelectedItem();
		}
		if(yeuCauKT.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập yêu cầu kỹ thuật","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(tenCD.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			try {
				gia=Double.parseDouble(donGia);
				if(gia<0) {
					JOptionPane.showMessageDialog(this, "Phải nhập đơn giá lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Phải nhập đơn giá là số lớn hơn 0","Thông báo",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=table.getSelectedRow();
		if(row>=0) {
			comLoaiSP.setSelectedItem(model.getValueAt(	row, 1).toString());
			comMaSP.setSelectedItem(model.getValueAt(	row, 2).toString());
			comTenSP.setSelectedItem(model.getValueAt(	row, 3).toString());
			lblKQKieuDang.setText(model.getValueAt(	row, 4).toString());
			lblKQKichThuoc.setText(model.getValueAt(	row, 5).toString());
			lblKQSoLuong.setText(model.getValueAt(	row, 11).toString());
			txtMaCD.setText(model.getValueAt(row, 6).toString());
			txtTenCD.setText(model.getValueAt(row, 7).toString());
			comMucUuTien.setSelectedItem(model.getValueAt(row, 8));
			txtYeuCauKT.setText(model.getValueAt(row, 9).toString());
			txtDonGia.setText(model.getValueAt(row, 10).toString());
			btnLayMa.setEnabled(false);
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
