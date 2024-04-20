package gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.toedter.calendar.JDateChooser;

import dao.CongDoanDAO;
import dao.SanPhamDAO;
import db.ExcelExporter;
import entities.CongDoan;
import entities.CongNhan;
import entities.SanPham;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;


public class NQLSX_ThongKe_GUI extends JPanel implements ActionListener,PropertyChangeListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JLabel lblNgay;
	private JDateChooser date;
	private JPanel jpTimKiem;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panel_1;
	private JLabel lblLoiSnPhm;
	private JComboBox comLoaiSP;
	private JLabel lblMaSP;
	private JTextField txtMaSP;
	private JLabel lblTenSP;
	private JTextField txtTenSP;
	private JLabel lblKieuDang;
	private JComboBox comKieuDang;
	private JLabel lblKichThuoc;
	private JTextField txtKichThuoc;
	private JLabel lblTinhTrang;
	private JComboBox comTinhTrang;
	private JButton btnTimKiem;
	private JPanel jpSouth;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_2;
	private JPanel panel_5;
	private JButton btnXuatFile;
	private JPanel panel_6;
	private JLabel lblSLngSn;
	private JLabel lblKQSoLuongSPTB;
	private SanPhamDAO sanPhamDAO=new SanPhamDAO();
	private CongDoanDAO congDoanDAO=new CongDoanDAO();
	private JPanel panel_7;
	private JCheckBox btnCheckNgayHienTai;
	private JPopupMenu popupMenu;
	private JMenuItem xemThongTinSX;
	private JMenuItem capNhatTrangThai;
	private JPopupMenu popupTrangThai;
	private JMenuItem hoanThanh;
	private JMenuItem tamNgung;
	private JMenuItem thucHien;
	private JMenuItem dangThucHien;
	private JMenuItem daHoanThanh;
	private JPopupMenu popup;
	private JMenuItem menuItem1;
	private JMenu menuTrangThai;
	private JMenuItem itemTrangThai1;
	private JMenuItem itemTrangThai2;
	private JMenuItem itemTrangThai3;
	private JMenuItem itemThongTinSX;

	/**
	 * Create the panel.
	 */
	public NQLSX_ThongKe_GUI() {
		new JPanel();
		createGUI();
		
		loadData();
		btnCheckNgayHienTai.setSelected(true);
	}

	private void loadData() {
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		ArrayList<SanPham> ds=sanPhamDAO.getDSSanPhamThucHienTheoNgay(date);
		for (SanPham sanPham : ds) {
			themModel(sanPham);
		}
		lblKQSoLuongSPTB.setText(soLuongSPTrungBinh(date)+" Sản phẩm");
	}

	private int soLuongSPTrungBinh(LocalDate d) {
		int slTong=0;
		ArrayList<SanPham>dssp=sanPhamDAO.getDSSanPhamThucHienTheoNgay(d);
		int slSanPham=dssp.size();
		if(slSanPham>0) {
			for (SanPham sanPham : dssp) {
				ArrayList<CongDoan>dscd=congDoanDAO.layDSCongDoanTheoMaSP(sanPham.getSanPhamID());
				if(dscd.size()>0) {
					int tt=dscd.get(0).getMucUuTien();
					int index=0;
					for (int i=0;i<dscd.size();i++) {
						if(dscd.get(i).getMucUuTien()>tt) {
							tt=dscd.get(i).getMucUuTien();
							index=i;
						}
							
					}
					CongDoan cd=dscd.get(index);
					int slHoanThanh=sanPhamDAO.getSoLuongSPHoanThanh(cd.getCongDoanID(), d);
					slTong=slTong+slHoanThanh;
				}
				
				
			}
			return slTong/slSanPham;
		}
		else
			return 0;
		
		
		
	}

	private void themModel(SanPham sanPham) {
		Object[] o=new Object[10];
		o[0]=model.getRowCount()+1;
		o[1]=sanPham.getLoaiSanPham().toString();
		o[2]=sanPham.getSanPhamID();
		o[3]=sanPham.getTenSanPham();
		o[4]=sanPham.getKieuDang().toString();
		o[5]=sanPham.getKichThuoc();

		
		
		String masp=sanPham.getSanPhamID();
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		
		
		ArrayList<CongDoan> dsCongDoan=congDoanDAO.layDSCongDoanTheoMaSP(masp);
		if(dsCongDoan.size()>0) {
			int tt=dsCongDoan.get(0).getMucUuTien();
			int index=0;
			for (int i=0;i<dsCongDoan.size();i++) {
				if(dsCongDoan.get(i).getMucUuTien()>tt) {
					tt=dsCongDoan.get(i).getMucUuTien();
					index=i;
				}
					
			}
			CongDoan congDoan=dsCongDoan.get(index);
			//Hoàn thành-----------------------------
						int sl=sanPhamDAO.getSLHoanThanhSP(congDoan.getCongDoanID(), date);
						System.out.println(congDoan.getCongDoanID()+"\n"+ date);

						o[6]=sl+"/"+sanPham.getSoLuong();
						
			//Số lượng sp mới--------------------------------------			
						
						int slngay=sanPhamDAO.getSoLuongSPHoanThanh(congDoan.getCongDoanID(), date);
						o[7]=slngay;
						
			//Tình trạng------------------------------------------------
						if(sanPham.getTinhTrang()==0) {
							o[8]="Đang thực hiện";
						}
						else {
							int sltong=sanPham.getSoLuong();
							int slHoanThanh=sl;
							if(slHoanThanh>=sltong) {
								o[8]="Đã hoàn thành";
							}
							else {
								o[8]="Tạm ngưng";
							}
						}
			//Ngày hoàn thành dự kiến----------------------------------------------------------
						int slTrungBinh=soLuongSPTrungBinh(date);
						if(slTrungBinh>0) {
							int slConLai=sanPham.getSoLuong()-sl;
							if(slConLai<=0) {
								o[9]=date;
							}else {
								int ngay=slConLai/slTrungBinh;
								if(ngay==0) {
									o[9]=date.plusDays(1);
								}
								else {
									o[9]=date.plusDays(ngay);
								}
							}
						}
						else {
							o[9]="Không xác định";
						}
		}
		else {
			o[6]="0/"+sanPham.getSoLuong();
			o[7]=0;
			if(sanPham.getTinhTrang()==0)
				o[8]="Đang thực hiện";
			else
				o[8]="Tạm ngưng";
			o[9]="Không xác định";
		}
		model.addRow(o);
	}


	private void createGUI() {
		setBounds(100, 100,1200, 700);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.WHITE);
		add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new BorderLayout(0, 0));
		
		jpTimKiem = new JPanel();
		jpTimKiem.setBorder(new TitledBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 153, 153)), "T\u00CCM KI\u1EBEM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 153)));
		jpTimKiem.setBackground(Color.WHITE);
		jpCenter.add(jpTimKiem, BorderLayout.NORTH);
		jpTimKiem.setLayout(new BoxLayout(jpTimKiem, BoxLayout.Y_AXIS));
		
		panel_7 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_7.setBackground(Color.WHITE);
		jpTimKiem.add(panel_7);
		
		btnCheckNgayHienTai = new JCheckBox("Theo ngày hiện tại");
		btnCheckNgayHienTai.setBackground(Color.WHITE);
		btnCheckNgayHienTai.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_7.add(btnCheckNgayHienTai);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(Color.WHITE);
		jpTimKiem.add(panel_1);
		
		lblLoiSnPhm = new JLabel("Loại sản phẩm:");
		lblLoiSnPhm.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblLoiSnPhm);
		
		comLoaiSP = new JComboBox();
		comLoaiSP.setBackground(Color.WHITE);
		comLoaiSP.setPreferredSize(new Dimension(100, 22));
		comLoaiSP.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Áo sơ mi", "Đồ thể thao", "Váy liền thân", "Áo khoác", "Quần tây", "Quần jean"}));
		comLoaiSP.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(comLoaiSP);
		
		lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(txtMaSP);
		txtMaSP.setColumns(10);
		
		lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblTenSP);
		
		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTenSP.setColumns(10);
		panel_1.add(txtTenSP);
		
		lblKieuDang = new JLabel("Kiểu dáng:");
		lblKieuDang.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblKieuDang);
		
		comKieuDang = new JComboBox();
		comKieuDang.setBackground(Color.WHITE);
		comKieuDang.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Body", "Tiêu chuẩn", "Form rộng"}));
		comKieuDang.setPreferredSize(new Dimension(100, 22));
		comKieuDang.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(comKieuDang);
		
		lblKichThuoc = new JLabel("Kích thước:");
		lblKichThuoc.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblKichThuoc);
		
		txtKichThuoc = new JTextField();
		txtKichThuoc.setFont(new Font("Arial", Font.PLAIN, 12));
		txtKichThuoc.setColumns(10);
		panel_1.add(txtKichThuoc);
		
		lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setFont(new Font("Arial", Font.BOLD, 12));
		panel_1.add(lblTinhTrang);
		
		comTinhTrang = new JComboBox();
		comTinhTrang.setBackground(Color.WHITE);
		comTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đang thực hiện", "Đã hoàn thành", "Tạm ngưng"}));
		comTinhTrang.setPreferredSize(new Dimension(100, 22));
		comTinhTrang.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(comTinhTrang);
		
		btnTimKiem = new JButton("");
		btnTimKiem.setBackground(Color.ORANGE);
		btnTimKiem.setPreferredSize(new Dimension(30, 30));
		ImageIcon imgICon = new ImageIcon(this.getClass().getResource("/timKiem.png"));
		btnTimKiem.setIcon(imgICon);
		panel_1.add(btnTimKiem);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		jpTimKiem.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5);
		
		btnXuatFile = new JButton("Xuất file");
		btnXuatFile.setForeground(Color.WHITE);
		btnXuatFile.setFont(new Font("Arial", Font.BOLD, 12));
		btnXuatFile.setBackground(new Color(0, 153, 153));
		panel_5.add(btnXuatFile);
		
		panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_6);
		
		lblSLngSn = new JLabel("Số lượng sản phẩm trung bình:");
		lblSLngSn.setFont(new Font("Arial", Font.BOLD, 12));
		panel_6.add(lblSLngSn);
		
		lblKQSoLuongSPTB = new JLabel("...");
		lblKQSoLuongSPTB.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_6.add(lblKQSoLuongSPTB);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		jpCenter.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		String[] header= {"STT","Loại sản phẩm","Mã sản phẩm","Tên sản phẩm","Kiểu dáng","Kích thước","Đã hoàn thành","Số lượng sản phẩm mới","Tình trạng","Ngày hoàn thành dự kiến"};
		model=new DefaultTableModel(header,0);
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		JPanel jpNorth = new JPanel();
		FlowLayout fl_jpNorth = (FlowLayout) jpNorth.getLayout();
		fl_jpNorth.setAlignment(FlowLayout.LEFT);
		jpNorth.setBackground(Color.WHITE);
		add(jpNorth, BorderLayout.NORTH);
		
		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("Arial", Font.BOLD, 12));
		date =new JDateChooser();
		Date d=new Date();
		date.setDate(d);
		date.setPreferredSize(new Dimension(120, 20));
		jpNorth.add(lblNgay);
		jpNorth.add(date);
		
		jpSouth = new JPanel();
		jpSouth.setBackground(Color.WHITE);
		add(jpSouth, BorderLayout.SOUTH);
		jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(Color.WHITE);
		jpSouth.add(panel_3);
		
		
		btnTimKiem.addActionListener(this);
		date.addPropertyChangeListener(this);
		table.addMouseListener(this);
		btnXuatFile.addActionListener(this);
		
//-----------------------test popup---------------------
		popup=new JPopupMenu();
		itemThongTinSX=new JMenuItem("Xem thông tin sản xuất");
		
		menuTrangThai=new JMenu("Cập nhật trạng thái");
		itemTrangThai1=new JMenuItem("Đang thực hiện");
		itemTrangThai2=new JMenuItem("Đã hoàn thành");
		itemTrangThai3=new JMenuItem("Tạm ngưng");
		menuTrangThai.add(itemTrangThai1);
		menuTrangThai.add(itemTrangThai2);
		menuTrangThai.add(itemTrangThai3);
		
		popup.add(itemThongTinSX);
		popup.add(menuTrangThai);
		
		//table.setComponentPopupMenu(popup);
		//this.add(popup);
		
		itemThongTinSX.addActionListener(this);
		itemTrangThai1.addActionListener(this);
		itemTrangThai2.addActionListener(this);
		itemTrangThai3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnTimKiem)) {
			try {
				Date d= date.getDate();
				Instant ins=d.toInstant();
				LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
				
				String loaisp=comLoaiSP.getSelectedItem().toString();
				String masp=txtMaSP.getText().trim();
				String tensp=txtTenSP.getText().trim();
				String kieuDang=comKieuDang.getSelectedItem().toString();
				String kichThuoc=txtKichThuoc.getText().trim();
				String tinhTrang=comTinhTrang.getSelectedItem().toString();
				
				ArrayList<SanPham>dsThucHien=sanPhamDAO.getDSSanPhamThucHienTheoNgay(date);
				ArrayList<SanPham>ds=sanPhamDAO.getAllSanPham();
				ArrayList<SanPham> dsTimKiem=new ArrayList<SanPham>();
				ArrayList<SanPham>dsHoanThanh=sanPhamDAO.getDSSanPhamHoanThanh();
				
				if(loaisp.equals("Tất cả")&& masp.equals("") && tensp.equals("") && kieuDang.equals("Tất cả")&& kichThuoc.equals("") && tinhTrang.equals("Tất cả")) {
					if(btnCheckNgayHienTai.isSelected()==true) {
						dsTimKiem=dsThucHien;
						for (SanPham sanPham : dsHoanThanh) {
							dsTimKiem.add(sanPham);
						}
					}
					else {
						dsTimKiem=ds;
//						dsTimKiem=sanPhamDAO.getAllDSSanPhamDangThucHien();
//						for (SanPham sanPham : dsHoanThanh) {
//							dsTimKiem.add(sanPham);
//						}
					}
				}else {
					if(btnCheckNgayHienTai.isSelected()==true) {
						if(tinhTrang.equals("Đang thực hiện")) {
							model.setRowCount(0);
							for (SanPham sanPham : dsThucHien) {
								themModel(sanPham);
							}
							for (int i = 0; i < model.getRowCount(); i++) {
								boolean flag=true;
								if(!loaisp.equals("Tất cả") && !model.getValueAt(i, 1).toString().equals(loaisp))
									flag=false;
								if(!masp.equals("") &&!model.getValueAt(i, 2).toString().equals(masp) )
									flag=false;
								if(!tensp.equals("") &&!model.getValueAt(i, 3).toString().equals(tensp) )
									flag=false;
								if(!kieuDang.equals("Tất cả") &&!model.getValueAt(i, 4).toString().equals(kieuDang) )
									flag=false;
								if(!kichThuoc.equals("") && !model.getValueAt(i, 5).toString().equals(kichThuoc) )
									flag=false;
								
								if(flag==true) {
									SanPham sp=sanPhamDAO.getSanPhamTheoID(model.getValueAt(i, 2).toString());
									dsTimKiem.add(sp);
								}
							}
						}
						else if(tinhTrang.equals("Tất cả")) {
							model.setRowCount(0);
							for (SanPham sanPham : dsThucHien) {
								themModel(sanPham);
							}
							for (SanPham sanPham : dsHoanThanh) {
								themModel(sanPham);
							}
							
							
							
							
							
							for (int i = 0; i < model.getRowCount(); i++) {
								boolean flag=true;
								if(!loaisp.equals("Tất cả") && !model.getValueAt(i, 1).toString().equals(loaisp))
									flag=false;
								if(!masp.equals("") &&!model.getValueAt(i, 2).toString().equals(masp) )
									flag=false;
								if(!tensp.equals("") &&!model.getValueAt(i, 3).toString().equals(tensp) )
									flag=false;
								if(!kieuDang.equals("Tất cả") &&!model.getValueAt(i, 4).toString().equals(kieuDang) )
									flag=false;
								if(!kichThuoc.equals("") && !model.getValueAt(i, 5).toString().equals(kichThuoc) )
									flag=false;
								
								if(flag==true) {
									SanPham sp=sanPhamDAO.getSanPhamTheoID(model.getValueAt(i, 2).toString());
									dsTimKiem.add(sp);
								}
							}
							
							
							
							
						}
						//-----------------
						else {
							model.setRowCount(0);
							for (SanPham sanPham : dsHoanThanh) {
								themModel(sanPham);
							}
							for (int i = 0; i < model.getRowCount(); i++) {
								boolean flag=true;
								if(!loaisp.equals("Tất cả") && !model.getValueAt(i, 1).toString().equals(loaisp))
									flag=false;
								if(!masp.equals("") &&!model.getValueAt(i, 2).toString().equals(masp) )
									flag=false;
								if(!tensp.equals("") &&!model.getValueAt(i, 3).toString().equals(tensp) )
									flag=false;
								if(!kieuDang.equals("Tất cả") &&!model.getValueAt(i, 4).toString().equals(kieuDang) )
									flag=false;
								if(!kichThuoc.equals("") && !model.getValueAt(i, 5).toString().equals(kichThuoc) )
									flag=false;
								if(!tinhTrang.equals("") && !model.getValueAt(i, 8).toString().equals(tinhTrang) )
									flag=false;
								if(flag==true) {
									SanPham sp=sanPhamDAO.getSanPhamTheoID(model.getValueAt(i, 2).toString());
									dsTimKiem.add(sp);
								}
							}
						}
						
					}
					///=========================
					else {
						model.setRowCount(0);
						for (SanPham sanPham : ds) {
							themModel(sanPham);
						}
						for (int i = 0; i < model.getRowCount(); i++) {
							boolean flag=true;
							if(!loaisp.equals("Tất cả") && !model.getValueAt(i, 1).toString().equals(loaisp))
								flag=false;
							if(!masp.equals("") &&!model.getValueAt(i, 2).toString().equals(masp) )
								flag=false;
							if(!tensp.equals("") &&!model.getValueAt(i, 3).toString().equals(tensp) )
								flag=false;
							if(!kieuDang.equals("Tất cả") &&!model.getValueAt(i, 4).toString().equals(kieuDang) )
								flag=false;
							if(!kichThuoc.equals("") && !model.getValueAt(i, 5).toString().equals(kichThuoc) )
								flag=false;
							if(!tinhTrang.equals("Tất cả") && !model.getValueAt(i, 8).toString().equals(tinhTrang) )
								flag=false;
//	System.out.println(flag);
							if(flag==true) {
								SanPham sp=sanPhamDAO.getSanPhamTheoID(model.getValueAt(i, 2).toString());
								dsTimKiem.add(sp);
							}
						}
					}
					
					
				}
//	System.out.println(dsTimKiem.size());
				model.setRowCount(0);
				for (SanPham sanPham : dsTimKiem) {
					themModel(sanPham);
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
//		if(o.equals(btnXemSanXuat)) {
//			int row=table.getSelectedRow();
//			if(row>=0) {
//				String masp=model.getValueAt(row, 2).toString();
//				NQLSX_ThongTinSanXuat gui =new NQLSX_ThongTinSanXuat(masp);
//				gui.setVisible(true);
//			}
//		}
//--------------------------test popup----------------------------------------
		if(o.equals(itemThongTinSX)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				String masp=model.getValueAt(row, 2).toString();
				NQLSX_ThongTinSanXuat gui =new NQLSX_ThongTinSanXuat(masp);
				gui.setVisible(true);
			}
		}
		
		if(o.equals(itemTrangThai1)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				
				String masp=model.getValueAt(row, 2).toString();
				sanPhamDAO.capNhatTrangThai(masp, 0);
				capNhatModel(masp,"Đang thực hiện");
			}
			
		}
		if(o.equals(itemTrangThai2)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				String masp=model.getValueAt(row, 2).toString();
				sanPhamDAO.capNhatTrangThai(masp, 1);
				
				capNhatModel(masp,"Đã hoàn thành");
			}
		}
		if(o.equals(itemTrangThai3)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				String masp=model.getValueAt(row, 2).toString();
				sanPhamDAO.capNhatTrangThai(masp, 1);
				
				capNhatModel(masp,"Tạm ngưng");
			}
		}
		if(o.equals(btnXuatFile)) {
			String fileName=layDuongDan();
			ArrayList<SanPham>ds=sanPhamDAO.getAllSanPham();
			model.setRowCount(0);
			for (SanPham sanPham : ds) {
				themModel(sanPham);
			}
			try {
				ExcelExporter.exportTable(table, fileName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Date d=date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			model.setRowCount(0);
			ArrayList<SanPham>dsmoi=sanPhamDAO.getDSSanPhamThucHienTheoNgay(date);
			for (SanPham sanPham : dsmoi) {
				themModel(sanPham);
			}
			btnCheckNgayHienTai.setSelected(true);
			JOptionPane.showMessageDialog(this, "Xuất file thành công");
		}
	}

	private String layDuongDan() {
		String url="";
        final JFileChooser file = new JFileChooser();
        int returnVal = file.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File files = file.getSelectedFile();
            url=files.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Chọn nơi lưu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        url=url+".xls";
		return url;
	}

	private void capNhatModel(String masp,String tt) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if(model.getValueAt(i, 2).toString().equals(masp))
				model.setValueAt(tt, i, 8);
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if("date".equals(e.getPropertyName())) {
			Date d=(Date) e.getNewValue();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			
			ArrayList<SanPham> ds=sanPhamDAO.getDSSanPhamThucHienTheoNgay(date);
			model.setRowCount(0);
			for (SanPham sanPham : ds) {
				themModel(sanPham);
			}
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o=e.getSource();
		Date d=date.getDate();
		Instant ins=d.toInstant();
		LocalDate ngay = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		
		if(ngay.equals(LocalDate.now())) {
			if(e.getButton()==MouseEvent.BUTTON3)
				popup.show(e.getComponent(), e.getX(), e.getY());
		}
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
////		Object o=e.getSource();
//		if(e.isPopupTrigger()) {
//			//if(o.equals(capNhatTrangThai))
//			popupTrangThai.show(capNhatTrangThai, capNhatTrangThai.getX(),capNhatTrangThai.getY());
//			
//		}
			}

	@Override
	public void mouseExited(MouseEvent e) {
//		Object o=e.getSource();
//		if(o.equals(capNhatTrangThai)) {
//			
//			popupTrangThai.setVisible(false);
//		}
		
	}

}
