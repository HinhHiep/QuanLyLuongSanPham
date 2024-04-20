package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.SanPhamDAO;
import entities.SanPham;
import enums.KieuDang;
import enums.LoaiSanPham;

import javax.swing.DefaultComboBoxModel;

public class NQLSX_QuanLyThongTinSanPham_GUI extends JPanel implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtKichThuoc;
	private JTextField txtSoLuong;
	private JTable table;
	private JComboBox comKieuDang;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnPhanCongDoan;
	private JTextField txtTenSP;
	private JTextField txtMaSP;
	private JButton btnLayMa;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	private JComboBox comLoaiSP;
	private DefaultTableModel model;
	private JButton btnTimKiem;
	private JButton btnXoaRong;
	private JButton btnXemThongTin;
	private JComboBox comTinhTrang;

	/**
	 * Create the panel.
	 */
	public NQLSX_QuanLyThongTinSanPham_GUI() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 1200, 700);
		setLayout(new BorderLayout(0, 0));
		
		createGUI();
		comLoaiSP.setSelectedIndex(0);
//		loadData();
	}

//	private void loadData() {
//		String loaisp=comLoaiSP.getSelectedItem().toString();
//		LoaiSanPham loai=null;
//		if(!loaisp.equals("")) {
//			
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
//		}
//		
//	}

	private void createGUI() {
		JPanel jpNorth = new JPanel();
		jpNorth.setBackground(Color.WHITE);
		add(jpNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÔNG TIN SẢN PHẨM");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setPreferredSize(new Dimension(200, 20));
		lblTieuDe.setForeground(new Color(0, 153, 153));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 14));
		jpNorth.add(lblTieuDe);
		
		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.WHITE);
		add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel jpLeft = new JPanel();
		FlowLayout fl_jpLeft = (FlowLayout) jpLeft.getLayout();
		fl_jpLeft.setHgap(10);
		fl_jpLeft.setVgap(10);
		jpLeft.setBackground(Color.WHITE);
		jpLeft.setPreferredSize(new Dimension(150, 10));
		jpCenter.add(jpLeft, BorderLayout.WEST);
		
		JPanel jpKhungHinh = new JPanel();
		FlowLayout fl_jpKhungHinh = (FlowLayout) jpKhungHinh.getLayout();
		fl_jpKhungHinh.setVgap(0);
		fl_jpKhungHinh.setHgap(0);
		jpKhungHinh.setBackground(Color.LIGHT_GRAY);
		jpKhungHinh.setPreferredSize(new Dimension(100, 100));
		jpLeft.add(jpKhungHinh);
		
		JLabel lblHinhAnh = new JLabel("");
		lblHinhAnh.setPreferredSize(new Dimension(100, 100));
		jpKhungHinh.add(lblHinhAnh);
		jpKhungHinh.setVisible(false);
		
		JPanel jpRight = new JPanel();
		jpRight.setBackground(Color.WHITE);
		jpCenter.add(jpRight, BorderLayout.CENTER);
		jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));
		
		JPanel jpMa = new JPanel();
		jpMa.setBackground(Color.WHITE);
		FlowLayout fl_jpMa = (FlowLayout) jpMa.getLayout();
		fl_jpMa.setHgap(40);
		fl_jpMa.setAlignment(FlowLayout.LEFT);
		jpRight.add(jpMa);
		
		JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
		lblLoaiSP.setPreferredSize(new Dimension(100, 14));
		lblLoaiSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpMa.add(lblLoaiSP);
		
		comLoaiSP = new JComboBox();
		comLoaiSP.setModel(new DefaultComboBoxModel(new String[] {"Áo sơ mi", "Đồ thể thao", "Váy liền thân", "Áo khoác", "Quần tây", "Quần jean"}));
		comLoaiSP.setBackground(Color.WHITE);
		comLoaiSP.setPreferredSize(new Dimension(830, 22));
		comLoaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
		jpMa.add(comLoaiSP);
		
		JPanel jpTen = new JPanel();
		jpTen.setBackground(Color.WHITE);
		FlowLayout fl_jpTen = (FlowLayout) jpTen.getLayout();
		fl_jpTen.setAlignment(FlowLayout.LEFT);
		fl_jpTen.setHgap(40);
		jpRight.add(jpTen);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setPreferredSize(new Dimension(100, 14));
		lblMaSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpTen.add(lblMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setBackground(Color.WHITE);
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMaSP.setColumns(64);
		jpTen.add(txtMaSP);
		
		btnLayMa = new JButton("Lấy mã");
		btnLayMa.setPreferredSize(new Dimension(80, 23));
		btnLayMa.setFont(new Font("Arial", Font.BOLD, 12));
		btnLayMa.setBackground(Color.ORANGE);
		jpTen.add(btnLayMa);
		
		JPanel jpLoai = new JPanel();
		jpLoai.setBackground(Color.WHITE);
		FlowLayout fl_jpLoai = (FlowLayout) jpLoai.getLayout();
		fl_jpLoai.setAlignment(FlowLayout.LEFT);
		fl_jpLoai.setHgap(40);
		jpRight.add(jpLoai);
		
		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setPreferredSize(new Dimension(100, 14));
		lblTenSP.setFont(new Font("Arial", Font.BOLD, 12));
		jpLoai.add(lblTenSP);
		
		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenSP.setColumns(75);
		jpLoai.add(txtTenSP);
		
		JPanel jpKieuDang = new JPanel();
		jpKieuDang.setBackground(Color.WHITE);
		FlowLayout fl_jpKieuDang = (FlowLayout) jpKieuDang.getLayout();
		fl_jpKieuDang.setAlignment(FlowLayout.LEFT);
		fl_jpKieuDang.setHgap(40);
		jpRight.add(jpKieuDang);
		
		JLabel lblKieuDang = new JLabel("Kiểu dáng:");
		lblKieuDang.setPreferredSize(new Dimension(100, 14));
		lblKieuDang.setFont(new Font("Arial", Font.BOLD, 12));
		jpKieuDang.add(lblKieuDang);
		
		comKieuDang = new JComboBox();
		comKieuDang.setBackground(Color.WHITE);
		comKieuDang.setFont(new Font("Arial", Font.PLAIN, 12));
		comKieuDang.setModel(new DefaultComboBoxModel(new String[] {"Body", "Tiêu chuẩn", "Form rộng"}));
		comKieuDang.setPreferredSize(new Dimension(830, 22));
		jpKieuDang.add(comKieuDang);
		
		JPanel jpKichThuoc = new JPanel();
		jpKichThuoc.setBackground(Color.WHITE);
		FlowLayout fl_jpKichThuoc = (FlowLayout) jpKichThuoc.getLayout();
		fl_jpKichThuoc.setAlignment(FlowLayout.LEFT);
		fl_jpKichThuoc.setHgap(40);
		jpRight.add(jpKichThuoc);
		
		JLabel lblKichThuoc = new JLabel("Kích thước");
		lblKichThuoc.setPreferredSize(new Dimension(100, 14));
		lblKichThuoc.setFont(new Font("Arial", Font.BOLD, 12));
		jpKichThuoc.add(lblKichThuoc);
		
		txtKichThuoc = new JTextField();
		txtKichThuoc.setFont(new Font("Arial", Font.PLAIN, 12));
		txtKichThuoc.setColumns(75);
		jpKichThuoc.add(txtKichThuoc);
		
		JPanel jpSoLuong = new JPanel();
		jpSoLuong.setBackground(Color.WHITE);
		FlowLayout fl_jpSoLuong = (FlowLayout) jpSoLuong.getLayout();
		fl_jpSoLuong.setAlignment(FlowLayout.LEFT);
		fl_jpSoLuong.setHgap(40);
		jpRight.add(jpSoLuong);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setPreferredSize(new Dimension(100, 14));
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 12));
		jpSoLuong.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSoLuong.setColumns(75);
		jpSoLuong.add(txtSoLuong);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(40);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.WHITE);
		jpRight.add(panel);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setPreferredSize(new Dimension(100, 14));
		lblTinhTrang.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(lblTinhTrang);
		
		comTinhTrang = new JComboBox();
		comTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đang thực hiện", "Tạm ngưng"}));
		comTinhTrang.setPreferredSize(new Dimension(830, 22));
		comTinhTrang.setFont(new Font("Arial", Font.PLAIN, 12));
		comTinhTrang.setBackground(Color.WHITE);
		panel.add(comTinhTrang);
		
		JPanel jpChucNang = new JPanel();
		jpChucNang.setBackground(Color.WHITE);
		FlowLayout fl_jpChucNang = (FlowLayout) jpChucNang.getLayout();
		fl_jpChucNang.setHgap(10);
		jpRight.add(jpChucNang);
		
		btnThem = new JButton("Thêm sản phẩm");
		btnThem.setPreferredSize(new Dimension(150, 30));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(0, 153, 153));
		btnThem.setFont(new Font("Arial", Font.BOLD, 12));
		jpChucNang.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setPreferredSize(new Dimension(100, 30));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 12));
		btnCapNhat.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnCapNhat);
		
		btnPhanCongDoan = new JButton("Phân công đoạn");
		btnPhanCongDoan.setPreferredSize(new Dimension(200, 30));
		btnPhanCongDoan.setForeground(Color.WHITE);
		btnPhanCongDoan.setFont(new Font("Arial", Font.BOLD, 12));
		btnPhanCongDoan.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnPhanCongDoan);
		
		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setPreferredSize(new Dimension(100, 30));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Arial", Font.BOLD, 12));
		btnXoaRong.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnXoaRong);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setPreferredSize(new Dimension(100, 30));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiem.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnTimKiem);
		
		btnXemThongTin = new JButton("Xem thông tin sản xuất");
		btnXemThongTin.setPreferredSize(new Dimension(170, 30));
		btnXemThongTin.setForeground(Color.WHITE);
		btnXemThongTin.setFont(new Font("Arial", Font.BOLD, 12));
		btnXemThongTin.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnXemThongTin);
		
		JPanel jpSouth = new JPanel();
		jpSouth.setBackground(Color.WHITE);
		jpSouth.setPreferredSize(new Dimension(10, 300));
		add(jpSouth, BorderLayout.SOUTH);
		jpSouth.setLayout(new BorderLayout(20, 20));
		
		
		String[] header= {"STT","Mã sản phẩm","Tên sản phẩm","Loại sản phẩm","Kiểu dáng","Kích thước","Số lượng","Tình trạng"};
		model=new DefaultTableModel(header,0);
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.setRowHeight(20);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBackground(Color.WHITE);
		JScrollPane sc=new JScrollPane(table);
		sc.setBackground(Color.WHITE);
		jpSouth.add(sc, BorderLayout.CENTER);
		
		JPanel jpTieuDe = new JPanel();
		FlowLayout fl_jpTieuDe = (FlowLayout) jpTieuDe.getLayout();
		fl_jpTieuDe.setHgap(10);
		fl_jpTieuDe.setAlignment(FlowLayout.LEFT);
		jpTieuDe.setBackground(new Color(255, 255, 255));
		jpSouth.add(jpTieuDe, BorderLayout.NORTH);
		
		JLabel lblDanhSachSPVuaThem = new JLabel("DANH SÁCH SẢN PHẨM");
		lblDanhSachSPVuaThem.setForeground(new Color(0, 153, 153));
		lblDanhSachSPVuaThem.setBackground(Color.WHITE);
		lblDanhSachSPVuaThem.setFont(new Font("Arial", Font.BOLD, 12));
		jpTieuDe.add(lblDanhSachSPVuaThem);
		
		btnCapNhat.setEnabled(false);
		btnPhanCongDoan.setEnabled(false);
		btnXemThongTin.setEnabled(false);
		txtMaSP.setEditable(true);
		
		btnLayMa.addActionListener(this);
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnPhanCongDoan.addActionListener(this);
		comLoaiSP.addActionListener(this);
		table.addMouseListener(this);
		btnXoaRong.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXemThongTin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(comLoaiSP)) {
			if(comLoaiSP.getSelectedIndex()>-1) {
				String loaisp=comLoaiSP.getSelectedItem().toString();
				LoaiSanPham loai=null;
				if(!loaisp.equals("")) {
					
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
				}
				ArrayList<SanPham> ds=sanPhamDAO.getDSSanPhamTheoLoai(loai);
				txtMaSP.setText("");
				txtTenSP.setText("");
				comKieuDang.setSelectedIndex(-1);
				txtKichThuoc.setText("");
				txtSoLuong.setText("");
				model.setRowCount(0);
				for (SanPham sanPham : ds) {
					themModel(sanPham);
				}
				btnLayMa.setEnabled(true);
			}
		}
		if(o.equals(btnLayMa)) {
			if(comLoaiSP.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm");
			}
			else {
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
					txtMaSP.setText(sanPhamDAO.layMa(loai));
				}
			}
			
			
		}
		if(o.equals(btnThem)) {
			if(kiemTra()) {
				String loaisp=comLoaiSP.getSelectedItem().toString();
				LoaiSanPham loai=null;
				if(!loaisp.equals("")) {
					
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
				}
				String ma=txtMaSP.getText().trim();
				if(kiemTraTrungMa(ma)) {
					JOptionPane.showMessageDialog(this, "Trùng mã", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}else {
					String ten=txtTenSP.getText().trim();
					String kieuDang=comKieuDang.getSelectedItem().toString();
					KieuDang kd=null;
					if(!kieuDang.equals("")) {
						if(kieuDang.equals("Body"))
							kd=KieuDang.BODY;
						if(kieuDang.equals("Tiêu chuẩn"))
							kd=KieuDang.TIEUCHUAN;
						if(kieuDang.equals("Form rộng"))
							kd=KieuDang.FORMRONG;
					}
					String kichThuoc=txtKichThuoc.getText().trim();
					String soLuong=txtSoLuong.getText().trim();
					int sl=0;
					try {
						sl=Integer.parseInt(soLuong);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					String ha="";
					String tinhTrang=comTinhTrang.getSelectedItem().toString();
					int tt=0;
					if(tinhTrang.equals("Đang thực hiện"))
						tt=0;
					else
						tt=1;
					SanPham sp=new SanPham(ma, ten, kd, kichThuoc, loai, sl, ha,tt);
					sanPhamDAO.themSanPham(sp);
					themModel(sp);
					xoaRong();
				}
				
			}
		}
		if(o.equals(btnCapNhat)) {
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng để cập nhật", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String ma=model.getValueAt(row, 1).toString();
				String txtMa=txtMaSP.getText().trim();
				if(ma.equals(txtMa)) {
					String ten=txtTenSP.getText().trim();
					String loaisp=comLoaiSP.getSelectedItem().toString();
					LoaiSanPham loai=null;
					if(!loaisp.equals("")) {
						
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
					}
					String kieuDang=comKieuDang.getSelectedItem().toString();
					KieuDang kd=null;
					if(!kieuDang.equals("")) {
						if(kieuDang.equals("Body"))
							kd=KieuDang.BODY;
						if(kieuDang.equals("Tiêu chuẩn"))
							kd=KieuDang.TIEUCHUAN;
						if(kieuDang.equals("Form rộng"))
							kd=KieuDang.FORMRONG;
					}
					String kt=txtKichThuoc.getText().trim();
					String soLuong=txtSoLuong.getText().trim();
					int sl=0;
					try {
						sl=Integer.parseInt(soLuong);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					String tinhTrang=comTinhTrang.getSelectedItem().toString();
					int tt=0;
					if(tinhTrang.equals("Đang thực hiện"))
						tt=0;
					else
						tt=1;
					if(kiemTra()) {
						sanPhamDAO.capNhatSanPham(ma, ten, loai, kd, kt, sl,tt);
						capNhatModel(ten,loai,kd,kt,sl,tt);
						xoaRong();
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Không được cập nhật mã sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		}
		if(o.equals(btnPhanCongDoan)) {
			
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để phân công", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			else {
				this.setVisible(false);
				String ma=model.getValueAt(row, 1).toString();
				NQLSX_PhanCongDoanSanPham_GUI phancong=new NQLSX_PhanCongDoanSanPham_GUI(ma);
				this.getParent().add(phancong).setVisible(true);
				setMau(this.getParent().getParent().getComponent(0).getComponentAt(0, 0));
				int size=this.getParent().getParent().getComponent(0).getComponentAt(0, 0).getWidth()+1;
				setMauMacDinh(this.getParent().getParent().getComponent(0).getComponentAt(190, 10));
			}
			
		}
		if(o.equals(btnXoaRong)) {
			xoaRong();
			model.setRowCount(0);
			btnCapNhat.setEnabled(false);
			btnPhanCongDoan.setEnabled(false);
			btnXemThongTin.setEnabled(false);
			btnLayMa.setEnabled(true);
		}
		if(o.equals(btnTimKiem)) {
			try {
				String masp=txtMaSP.getText().trim();
				String tensp=txtTenSP.getText().trim();
				String kichThuoc=txtKichThuoc.getText().trim();
				String soLuong=txtSoLuong.getText().trim();
				
				ArrayList<SanPham>ds=sanPhamDAO.getAllSanPham();
				ArrayList<SanPham>dsTimKiem=new ArrayList<SanPham>();
				model.setRowCount(0);
				for (SanPham sanPham : ds) {
					themModel(sanPham);
				}
//				String loaisp=comLoaiSP.getSelectedItem().toString();
			//	if(loaisp!=null) {
					for (int i = 0; i <ds.size(); i++) {
						boolean flag=true;
						if(comLoaiSP.getSelectedIndex()!=-1) {
							String loaisp=comLoaiSP.getSelectedItem().toString();
							if(!model.getValueAt(i, 3).toString().equals(loaisp))
								flag=false;
//							System.out.println(model.getValueAt(i, 3).equals(loaisp));
	//						System.out.println(model.getValueAt(i, 3));
		//					System.out.println(loaisp);
		//
						}
//						System.out.println(flag);
						if(!masp.equals("") && !model.getValueAt(i, 1).equals(masp))
							flag=false;
//						System.out.println(flag);

						if(!tensp.equals("") && !model.getValueAt(i, 2).equals(tensp))
							flag=false;
//						System.out.println(flag);

						if(comKieuDang.getSelectedIndex()!=-1 ) {
							String kieuDang=comKieuDang.getSelectedItem().toString();

							if(!model.getValueAt(i, 4).toString().equals(kieuDang))
								flag=false;
						}
							
						if(!kichThuoc.equals("") && !model.getValueAt(i, 5).equals(kichThuoc))
							flag=false;
						if(!soLuong.equals("") && !model.getValueAt(i, 6).equals(soLuong))
							flag=false;
						if(comTinhTrang.getSelectedIndex()!=-1 && comTinhTrang.getSelectedIndex()!=0) {
							String tinhTrang=comTinhTrang.getSelectedItem().toString();
							if(!model.getValueAt(i, 7).toString().equals(tinhTrang))
								flag=false;
						}
						if(flag==true) {
							SanPham sp=sanPhamDAO.getSanPhamTheoID(model.getValueAt(i, 1).toString());
							dsTimKiem.add(sp);
						}
					}
					model.setRowCount(0);
					for (SanPham sanPham : dsTimKiem) {
						themModel(sanPham);
					}

//				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if(o.equals(btnXemThongTin)) {
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xem thông tin");
			}
			else {
				String masp=model.getValueAt(row, 1).toString();
				NQLSX_ThongTinSanXuat gui=new NQLSX_ThongTinSanXuat(masp);
				gui.setVisible(true);
			}
		}
	}
	public void setMau(Component component) {
		((JComponent) component).setBorder(new LineBorder(new Color(196, 98, 0), 2));
		component.setBackground(Color.ORANGE);
	}
	public void setMauMacDinh(Component jp) {
		((JComponent) jp).setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jp.setBackground(new Color(0, 153, 153));
	}
	private void capNhatModel(String ten, LoaiSanPham loai, KieuDang kd, String kt, int sl,int tt) {
		int row=table.getSelectedRow();
		if(row>=0) {
			model.setValueAt(ten, row, 2);
			model.setValueAt(loai, row,3);
			model.setValueAt(kd, row, 4);
			model.setValueAt(kt, row, 5);
			model.setValueAt(sl, row, 6);
			if(tt==0) {
				model.setValueAt("Đang thực hiện", row, 7);
			}
			else {
				model.setValueAt("Tạm ngưng", row, 7);

			}
			
		}
		
		
	}

	private void xoaRong() {
		comLoaiSP.setSelectedIndex(-1);
		txtMaSP.setText("");
		txtTenSP.setText("");
		comKieuDang.setSelectedIndex(-1);
		txtKichThuoc.setText("");
		txtSoLuong.setText("");
		comTinhTrang.setSelectedIndex(-1);
	}
	private void themModel(SanPham sp) {
		Object[] o=new Object[8];
		o[0]=model.getRowCount()+1;
		o[1]=sp.getSanPhamID();
		o[2]=sp.getTenSanPham();
		o[3]=sp.getLoaiSanPham();
		o[4]=sp.getKieuDang();
		o[5]=sp.getKichThuoc();
		o[6]=sp.getSoLuong();
		int tinhTrang=sp.getTinhTrang();
		if(tinhTrang==0){
			o[7]="Đang thực hiện";
		}
		else {
			o[7]="Tạm ngưng";
		}
		model.addRow(o);
	}

	private boolean kiemTra() {
		String ma=txtMaSP.getText().trim();
		String ten=txtTenSP.getText().trim();
		String kichThuoc=txtKichThuoc.getText().trim();
		String soLuong=txtSoLuong.getText().trim();
		if(comLoaiSP.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn loại sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(ma.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {		
			Pattern patMa=Pattern.compile("SP(ASM|DTT|VLT|AK|QT|QJ)[0-9]{4}");
			Matcher matMa=patMa.matcher(ma);
			if(!matMa.matches()) {
				JOptionPane.showMessageDialog(this, "Nhập sai định dạng", "Thông báo", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(ten.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comKieuDang.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn kiểu dáng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(kichThuoc.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập kích thước", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(soLuong.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số lượng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {
				int sl=Integer.parseInt(soLuong);
				if(sl<0) {
					JOptionPane.showMessageDialog(this, "Phải nhập số lượng là số lớn hơn 0", "Thông báo", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Phải nhập số lượng là số lớn hơn 0", "Thông báo", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(comTinhTrang.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn tình trạng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(comTinhTrang.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(this, "Chọn tình trạng là Đang thực hiện hoặc tạm ngưng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean kiemTraTrungMa(String ma) {
		ArrayList<SanPham> ds=sanPhamDAO.getAllSanPham();
		for (SanPham sanPham : ds) {
			if(sanPham.getSanPhamID().equals(ma))
				return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=table.getSelectedRow();
		if(row>=0) {
			comLoaiSP.setSelectedItem(model.getValueAt(row, 3).toString());
			txtMaSP.setText(model.getValueAt(row, 1).toString());
			txtTenSP.setText(model.getValueAt(row, 2).toString());
			comKieuDang.setSelectedItem(model.getValueAt(row, 4).toString());
			txtKichThuoc.setText(model.getValueAt(row, 5).toString());
			txtSoLuong.setText(model.getValueAt(row, 6).toString());
			comTinhTrang.setSelectedItem(model.getValueAt(row, 7));
			btnLayMa.setEnabled(false);
			btnCapNhat.setEnabled(true);
			btnPhanCongDoan.setEnabled(true);
			btnXemThongTin.setEnabled(true);
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
