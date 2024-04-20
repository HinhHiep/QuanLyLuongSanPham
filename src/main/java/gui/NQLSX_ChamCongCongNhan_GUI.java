package gui;

import java.awt.EventQueue;

import com.toedter.calendar.JDateChooser;

import dao.BangChamCongDAO;
import dao.BoPhanDAO;
import dao.CongNhanDAO;
import dao.NguoiDAO;
import entities.BangChamCong;
import entities.BoPhan;
import entities.CongNhan;
import entities.Nguoi;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ComboBoxModel;

public class NQLSX_ChamCongCongNhan_GUI extends JPanel  implements ActionListener,TableModelListener,PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	private JDateChooser date;
	public JComboBox comCaLamviec;
	public JTable table;
	private DefaultComboBoxModel<String> comMaBPModel;
	private DefaultComboBoxModel<String> comTenBPModel;
	private DefaultComboBoxModel<String> comCaLamViecModel;
	private BoPhanDAO boPhanDAO=new BoPhanDAO();
	private DefaultTableModel model;
	private NguoiDAO nguoiDAO=new NguoiDAO();
	private ArrayList<CongNhan> dsKq=new ArrayList<CongNhan>();
	private BangChamCongDAO bangChamCongDAO=new BangChamCongDAO();
	private JTextField txtMaCN;
	private JTextField txtHoTen;
	private JDateChooser ngaySinh;
	private JButton btnTimKiemCN;
	private JButton btnHuyDiemDanh;
	private JButton btnPhanCongBoPhan;
	private JButton btnLuuThongTin;
	private JPanel jpTimKiem;
	private JTextField txtSoCMND;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JButton btnTimKiemCN_1;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private CongNhanDAO congNhanDAO=new CongNhanDAO();
	private ButtonGroup group;
	private Object nguoi;
	private JPanel jpBoPhan;
	private JLabel lblMaBP;
	private JComboBox<String> comMaBP;
	private JLabel lblTnBPhn;
	private JComboBox<String> comTenBP;
	private JLabel lblTongBoPhan;
	private JLabel lblKQTong;
	private JLabel lblCoMat;
	private JLabel lblKQCoMat;
	private JLabel lblVngMt;
	private JLabel lblKQVangMat;
	private JPanel jpDiemDanh;
	private JPanel jpVangBP;
	private JLabel lblVangBP;
	private JLabel lblKQVangBP;
	private JPanel jpTinhTrang;
	private JRadioButton radCoMat;
	private JRadioButton radVangMat;
//	private CongNhanDAO congNhanDAO=new CongNhanDAO();
	private ButtonGroup groupTinhTrang;
	private ArrayList<CongNhan> list;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NQLSX_ChamCongCongNhan_GUI frame = new NQLSX_ChamCongCongNhan_GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {s
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public NQLSX_ChamCongCongNhan_GUI() {
		createGUI();
		loadData();	
		
		
		
	}

	

	



	private void loadData() {
		
		ArrayList< BoPhan> ds=boPhanDAO.getAllBoPhanCoCN();
		comMaBPModel.addElement("Tất cả");
		comTenBPModel.addElement("Tất cả");
		for (BoPhan boPhan : ds) {
			
			comMaBPModel.addElement(boPhan.getBoPhanID());
			comTenBPModel.addElement(boPhan.getTenBoPhan());
		}
		Date d= date.getDate();
		Instant ins=d.toInstant();
		LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
		String caLam=comCaLamviec.getSelectedItem().toString();
		comMaBP.setSelectedIndex(1);
//		System.out.println(caLam);
//		System.out.println(date);
		kiemTraChamCong(date, caLam);
//		dsKq=bangChamCongDAO.getDSChamCongCN(date, caLam, "Có mặt");
//		System.out.println(dsKq.size());
//		if(bangChamCongDAO.kiemTraChamCongCN(date, caLam)) {
//			jpDiemDanh.setVisible(true);
//			ArrayList<CongNhan>dsCoMat=bangChamCongDAO.getDSChamCongCN(date, caLam, "Có mặt");
//			ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLam, "Vắng mặt");
//			lblKQCoMat.setText(dsCoMat.size()+" Công nhân");
//			lblKQVangMat.setText(dsVangMat.size()+" Công nhân");
//			
//		}
	}

	private void createGUI() {
		this.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 1200, 700);
		setLayout(new BorderLayout(0, 0));
	
		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.WHITE);
		this.add(jpCenter);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/timKiem.png"));
		jpCenter.setLayout(new BorderLayout(0, 0));
		
		
		JPanel jpRight = new JPanel();
		jpRight.setBackground(Color.WHITE);
		jpCenter.add(jpRight);
		jpRight.setLayout(new BorderLayout(0, 0));
		
		JPanel jpHeadDSCongNhan = new JPanel();
		jpHeadDSCongNhan.setBorder(new TitledBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(33, 156, 144)), "DANH S\u00C1CH C\u00D4NG NH\u00C2N", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(33, 156, 144)));
		jpHeadDSCongNhan.setBackground(Color.WHITE);
		jpRight.add(jpHeadDSCongNhan, BorderLayout.NORTH);
		jpHeadDSCongNhan.setLayout(new BoxLayout(jpHeadDSCongNhan, BoxLayout.Y_AXIS));
		
		comMaBPModel=new DefaultComboBoxModel<String>();
		
		comTenBPModel=new DefaultComboBoxModel<String>();
		
		jpDiemDanh = new JPanel();
		FlowLayout fl_jpDiemDanh = (FlowLayout) jpDiemDanh.getLayout();
		fl_jpDiemDanh.setAlignment(FlowLayout.LEFT);
		jpDiemDanh.setBackground(Color.WHITE);
		jpHeadDSCongNhan.add(jpDiemDanh);
		jpDiemDanh.setVisible(false);
		
		lblCoMat = new JLabel("Có mặt:");
		lblCoMat.setFont(new Font("Arial", Font.BOLD, 12));
		jpDiemDanh.add(lblCoMat);
		
		lblKQCoMat = new JLabel("...");
		lblKQCoMat.setFont(new Font("Arial", Font.PLAIN, 12));
		jpDiemDanh.add(lblKQCoMat);
		
		lblVngMt = new JLabel("Vắng mặt:");
		lblVngMt.setFont(new Font("Arial", Font.BOLD, 12));
		jpDiemDanh.add(lblVngMt);
		
		lblKQVangMat = new JLabel("...");
		lblKQVangMat.setFont(new Font("Arial", Font.PLAIN, 12));
		jpDiemDanh.add(lblKQVangMat);
		
		JPanel jpChucNang = new JPanel();
		FlowLayout fl_jpChucNang = (FlowLayout) jpChucNang.getLayout();
		fl_jpChucNang.setAlignment(FlowLayout.LEFT);
		jpChucNang.setBackground(Color.WHITE);
		jpHeadDSCongNhan.add(jpChucNang);
		
		btnTimKiemCN = new JButton("");
		btnTimKiemCN.setIcon(icon);
		btnTimKiemCN.setForeground(Color.BLACK);
		btnTimKiemCN.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiemCN.setBackground(Color.ORANGE);
		jpChucNang.add(btnTimKiemCN);
		
		btnHuyDiemDanh = new JButton("Điểm danh tất cả");
		btnHuyDiemDanh.setPreferredSize(new Dimension(200, 30));
		btnHuyDiemDanh.setForeground(Color.WHITE);
		btnHuyDiemDanh.setFont(new Font("Arial", Font.BOLD, 12));
		btnHuyDiemDanh.setBackground(new Color(33, 156, 144));
		jpChucNang.add(btnHuyDiemDanh);
		
		btnPhanCongBoPhan = new JButton("Phân công bộ phận");
		btnPhanCongBoPhan.setPreferredSize(new Dimension(200, 30));
		btnPhanCongBoPhan.setForeground(Color.WHITE);
		btnPhanCongBoPhan.setFont(new Font("Arial", Font.BOLD, 12));
		btnPhanCongBoPhan.setBackground(new Color(33, 156, 144));
		jpChucNang.add(btnPhanCongBoPhan);
		
		btnLuuThongTin = new JButton("Lưu thông tin");
		btnLuuThongTin.setPreferredSize(new Dimension(200, 30));
		btnLuuThongTin.setForeground(Color.WHITE);
		btnLuuThongTin.setFont(new Font("Arial", Font.BOLD, 12));
		btnLuuThongTin.setBackground(new Color(33, 156, 144));
		jpChucNang.add(btnLuuThongTin);
		
		jpTinhTrang = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpTinhTrang.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		jpTinhTrang.setBackground(new Color(255, 255, 153));
		jpHeadDSCongNhan.add(jpTinhTrang);
		
		radCoMat = new JRadioButton("Có mặt");
		radCoMat.setFont(new Font("Arial", Font.BOLD, 12));
		radCoMat.setBackground(new Color(255, 255, 153));
		jpTinhTrang.add(radCoMat);
		
		radVangMat = new JRadioButton("Vắng mặt");
		radVangMat.setFont(new Font("Arial", Font.BOLD, 12));
		radVangMat.setBackground(new Color(255, 255, 153));
		jpTinhTrang.add(radVangMat);
		jpTinhTrang.setVisible(false);
		groupTinhTrang=new ButtonGroup();
		groupTinhTrang.add(radCoMat);
		groupTinhTrang.add(radVangMat);
		
		jpTimKiem = new JPanel();
		FlowLayout fl_jpTimKiem = (FlowLayout) jpTimKiem.getLayout();
		fl_jpTimKiem.setHgap(10);
		fl_jpTimKiem.setAlignment(FlowLayout.LEFT);
		jpTimKiem.setBackground(new Color(255, 255, 153));
		jpHeadDSCongNhan.add(jpTimKiem);
		
		JLabel lblMaCN = new JLabel("Mã công nhân:");
		lblMaCN.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblMaCN);
		
		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMaCN.setColumns(10);
		jpTimKiem.add(txtMaCN);
		
		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHoTen.setColumns(10);
		jpTimKiem.add(txtHoTen);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 12));
		ngaySinh=new JDateChooser();
		ngaySinh.setPreferredSize(new Dimension(100, 20));
		jpTimKiem.add(lblNgaySinh);
		jpTimKiem.add(ngaySinh);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblGioiTinh);
		
		radNam = new JRadioButton("Nam");
		radNam.setBackground(new Color(255, 255, 153));
		radNam.setFont(new Font("Arial", Font.PLAIN, 12));
		jpTimKiem.add(radNam);
		
		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Arial", Font.PLAIN, 12));
		radNu.setBackground(new Color(255, 255, 153));
		jpTimKiem.add(radNu);
		
		group=new ButtonGroup();
		group.add(radNam);
		group.add(radNu);
		
		
		JLabel lblSoCmnd = new JLabel("Số CMND:");
		lblSoCmnd.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblSoCmnd);
		
		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSoCMND.setColumns(10);
		jpTimKiem.add(txtSoCMND);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSDT.setColumns(10);
		jpTimKiem.add(txtSDT);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		jpTimKiem.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		jpTimKiem.add(txtEmail);
		
		btnTimKiemCN_1 = new JButton("");
		btnTimKiemCN_1.setPreferredSize(new Dimension(30, 25));
		btnTimKiemCN_1.setIcon(icon);
		btnTimKiemCN_1.setForeground(Color.BLACK);
		btnTimKiemCN_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnTimKiemCN_1.setBackground(Color.ORANGE);
		jpTimKiem.add(btnTimKiemCN_1);
		
		jpBoPhan = new JPanel();
		FlowLayout fl_jpBoPhan = (FlowLayout) jpBoPhan.getLayout();
		fl_jpBoPhan.setHgap(10);
		fl_jpBoPhan.setAlignment(FlowLayout.LEFT);
		jpBoPhan.setBackground(Color.WHITE);
		jpHeadDSCongNhan.add(jpBoPhan);
		
		lblMaBP = new JLabel("Mã bộ phận:");
		lblMaBP.setFont(new Font("Arial", Font.BOLD, 12));
		jpBoPhan.add(lblMaBP);
		
		comMaBP = new JComboBox<String>(comMaBPModel);
//		comMaBP.setSelectedIndex(0);
		comMaBP.setPreferredSize(new Dimension(150, 25));
		comMaBP.setFont(new Font("Arial", Font.PLAIN, 12));
		comMaBP.setEditable(true);
		comMaBP.setBackground(Color.WHITE);
		jpBoPhan.add(comMaBP);
		
		lblTnBPhn = new JLabel("Tên bộ phận:");
		lblTnBPhn.setFont(new Font("Arial", Font.BOLD, 12));
		jpBoPhan.add(lblTnBPhn);
		
		comTenBP = new JComboBox<String>(comTenBPModel);
		comTenBP.setPreferredSize(new Dimension(150, 25));
		comTenBP.setFont(new Font("Arial", Font.PLAIN, 12));
		comTenBP.setEditable(true);
		comTenBP.setBackground(Color.WHITE);
		jpBoPhan.add(comTenBP);
		
		lblTongBoPhan = new JLabel("Tổng:");
		lblTongBoPhan.setFont(new Font("Arial", Font.BOLD, 12));
		jpBoPhan.add(lblTongBoPhan);
		
		lblKQTong = new JLabel("... Công nhân");
		lblKQTong.setFont(new Font("Arial", Font.PLAIN, 12));
		jpBoPhan.add(lblKQTong);
		
		jpVangBP = new JPanel();
		jpVangBP.setBackground(Color.WHITE);
		jpVangBP.setPreferredSize(new Dimension(200, 25));
		
		jpBoPhan.add(jpVangBP);
		
		lblVangBP = new JLabel("Vắng:");
		lblVangBP.setFont(new Font("Arial", Font.BOLD, 12));
		jpVangBP.add(lblVangBP);
		
		lblKQVangBP = new JLabel("... Công nhân");
		lblKQVangBP.setFont(new Font("Arial", Font.PLAIN, 12));
		jpVangBP.add(lblKQVangBP);
		jpVangBP.setVisible(false);
		
		JScrollPane sc = new JScrollPane();
		sc.setBorder(null);
		sc.setBackground(Color.WHITE);
		sc.setAutoscrolls(true);
		jpRight.add(sc, BorderLayout.CENTER);
		
		String[] header= {"STT","Mã công nhân","Họ tên","Giới tính","Ngày sinh","Email","Số điện thoại","Có mặt"};
		model=new DefaultTableModel(header,0); 
		table = new JTable(model) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		sc.setViewportView(table);
		
		JPanel jpNorth = new JPanel();
		jpNorth.setBackground(Color.WHITE);
		FlowLayout fl_jpNorth = (FlowLayout) jpNorth.getLayout();
		fl_jpNorth.setHgap(10);
		fl_jpNorth.setAlignment(FlowLayout.LEFT);
		this.add(jpNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Ngày:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		jpNorth.add(lblNewLabel);
		
		date=new JDateChooser();

		java.util.Date d=new java.util.Date();
		date.setDate(d);
		date.setPreferredSize(new Dimension(150, 20));
		jpNorth.add(date);
		
		
		JLabel lblCaLamViec = new JLabel("Ca làm việc:");
		lblCaLamViec.setFont(new Font("Arial", Font.BOLD, 12));
		jpNorth.add(lblCaLamViec);
		
		comCaLamViecModel=new DefaultComboBoxModel<String>();
		comCaLamviec = new JComboBox<String>(comCaLamViecModel);
//		comCaLamviec.setModel(new DefaultComboBoxModel(new String[] {"Ca sáng", "Ca chiều", "Ca tối"}));
		comCaLamViecModel.addElement("Ca sáng");
		comCaLamViecModel.addElement("Ca chiều");
		comCaLamViecModel.addElement("Ca tối");
		comCaLamviec.setFont(new Font("Arial", Font.PLAIN, 12));
		comCaLamviec.setBackground(Color.WHITE);
		comCaLamviec.setPreferredSize(new Dimension(150, 20));
		jpNorth.add(comCaLamviec);
//		table.addMouseListener(this);
		comMaBP.addActionListener(this);
		comTenBP.addActionListener(this);
		btnHuyDiemDanh.addActionListener(this);
		btnLuuThongTin.addActionListener(this);
		btnTimKiemCN.addActionListener(this);
		btnTimKiemCN_1.addActionListener(this);
		btnPhanCongBoPhan.addActionListener(this);
		jpTimKiem.setVisible(false);
		model.addTableModelListener(this);
		comCaLamviec.addActionListener(this);
		date.addPropertyChangeListener(this);
}



	@Override
	public void actionPerformed(ActionEvent e) {
	//nút tìm kiếm
		Object o=e.getSource();
		if(o.equals(btnTimKiemCN)) {
			jpVangBP.setVisible(false);
			lblTongBoPhan.setVisible(false);
			lblKQTong.setVisible(false);
			if(btnTimKiemCN.getText().equals("")) {
				xoaRong();
				jpTimKiem.setVisible(true);
				jpTinhTrang.setVisible(true);
				btnTimKiemCN.setText("Đóng tìm kiếm");
			}
			else {
				jpVangBP.setVisible(true);
				lblTongBoPhan.setVisible(true);
				lblKQTong.setVisible(true);
				jpTimKiem.setVisible(false);
				jpTinhTrang.setVisible(false);
				btnTimKiemCN.setText("");
				ArrayList<CongNhan> list1=new ArrayList<CongNhan>();
				model.setRowCount(0);
				String bp=comMaBP.getSelectedItem().toString();
				model.setRowCount(0);
				comMaBP.setSelectedIndex(1);
//				list1=congNhanDAO.getDSCongNhanTheoBoPhan(bp);
//				themModel(list1);
				
			}
		}
		if(o.equals(btnTimKiemCN_1)) {
			String ma=txtMaCN.getText().trim();
			String hoTen=txtHoTen.getText().trim();
			Date d=ngaySinh.getDate();
			String sdt=txtSDT.getText().trim();
			String email=txtEmail.getText().trim();
			
			Date day= date.getDate();
			Instant ins=day.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			String caLam=comCaLamviec.getSelectedItem().toString();
			
			list=new ArrayList<CongNhan>();
			ArrayList<CongNhan> list1=new ArrayList<CongNhan>();
			model.setRowCount(0);
//			String bp=comMaBP.getSelectedItem().toString();
			list1=bangChamCongDAO.getDSChamCongCN(date, caLam);
			themModel(list1);
			
			
			int s=model.getRowCount();
			if(ma.equals("")&&hoTen.equals("")&& d==null && email.equals("")&& sdt.equals("") && !radNam.isSelected() && !radNu.isSelected() && !radCoMat.isSelected() && !radVangMat.isSelected()) {
				model.setRowCount(0);
				JOptionPane.showMessageDialog(this, "Chưa nhập thông tin tìm kiếm", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				
				boolean flag=true;
				
				for (int i = 0; i < s; i++) {
					if(radCoMat.isSelected() && (boolean) model.getValueAt(i, 7) == false)
						flag=false;
					if(radVangMat.isSelected() && (boolean) model.getValueAt(i, 7) == true)
						flag=false;
					if(!ma.equals("")&& !model.getValueAt(i, 1).equals(ma))
						flag=false;
					if(!hoTen.equals("")&& !model.getValueAt(i, 2).equals(hoTen))
						flag=false;
					if(radNam.isSelected() && !model.getValueAt(i, 3).equals("Nam"))
						flag=false;
					if(radNu.isSelected() && !model.getValueAt(i, 3).equals("Nữ"))
						flag=false;
					if(d!=null && !model.getValueAt(i, 4).equals(d))
						flag=false;
					if(!email.equals("")&& !model.getValueAt(i, 5).equals(email))
						flag=false;
					if(!sdt.equals("")&& !model.getValueAt(i, 1).equals(sdt))
						flag=false;
					
						
					
					
					if(flag==true) {
						try {
							CongNhan cn=congNhanDAO.getCongNhanTheoID(model.getValueAt(i, 1).toString());
							list.add(cn);
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
					flag=true;
				}
				comMaBP.setSelectedIndex(0);
				model.setRowCount(0);
				themModel(list);

			}
			
		}
		if(o.equals(comMaBP)) {
			try {
				//Chuyển combobox
				int index=comMaBP.getSelectedIndex();
				Date d= date.getDate();
				Instant ins=d.toInstant();
				LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
				String caLam=comCaLamviec.getSelectedItem().toString();
				comTenBP.setSelectedIndex(index);
				if(index>=0) {
					if(btnTimKiemCN.getText().equals("Đóng tìm kiếm")) {
						String mabp=comMaBP.getSelectedItem().toString();
						if(mabp.equals("Tất cả")) {
							model.setRowCount(0);
							themModel(list);
						}
						else {
							model.setRowCount(0);
							ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
							for (CongNhan congNhan : list) {
								if(congNhan.getBoPhan().getBoPhanID().equals(mabp)) {
									ds.add(congNhan);
								}
							}
							themModel(ds);
						}
					}
					else {
						
						String ma=comMaBP.getSelectedItem().toString();
						int sl=0;
						if(ma.equals("Tất cả")) {
							sl=congNhanDAO.getAllCongNhan().size();
							ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLam, "Vắng mặt");
							lblKQVangBP.setText(dsVangMat.size()+" Công nhân");

						}
						else {
							sl=congNhanDAO.getSoLuongCNTheoBoPhan(ma);
							ArrayList<CongNhan> dsVangBP=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLam, ma, "Vắng mặt");
							lblKQVangBP.setText(dsVangBP.size()+" Công nhân");
						}
						
						lblKQTong.setText(sl+" Công nhân");
						
//						System.out.println(caLam);
//						kiemTraChamCong(date, caLam);
						model.setRowCount(0);//xóa model
						ArrayList<CongNhan>ds=new ArrayList<CongNhan>();
						if(comMaBP.getSelectedItem().toString().equals("Tất cả")) {
							ds=congNhanDAO.getAllCongNhan();
							
						}
						else {
							ds=congNhanDAO.getDSCongNhanTheoBoPhan(comMaBP.getSelectedItem().toString());
						}
						
						model.setRowCount(0);
						
						themModel(ds);
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		//Chuyển combobox
		if(o.equals(comTenBP)) {
			try {
				int index=comTenBP.getSelectedIndex();
				comMaBP.setSelectedIndex(index);

				if(index>=0) {
					if(btnTimKiemCN.getText().equals("Đóng tìm kiếm")) {
						
					}
					else {
						ArrayList<CongNhan>ds=new ArrayList<CongNhan>();
						if(comTenBP.getSelectedItem().toString().equals("Tất cả")) {
							ds=congNhanDAO.getAllCongNhan();
						}
						else {
							ds=congNhanDAO.getDSCongNhanTheoBoPhan(comMaBP.getSelectedItem().toString());
						}
						
						
						model.setRowCount(0);//xóa model
						
						themModel(ds);
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		if(o.equals(btnHuyDiemDanh)) {
//			dsKq=new ArrayList<CongNhan>();
			ArrayList<CongNhan> ds =congNhanDAO.getAllCongNhan();
			System.out.println(dsKq.size());
			int sl=ds.size();
//			for (CongNhan congNhan : ds) {
//				dsKq.add(congNhan);
//			}
			model.setRowCount(0);
			themModel(ds);
//			for (CongNhan congNhan : ds) {
//				dsKq.add(congNhan);
//			}
//			System.out.println(dsKq.size());
//			System.out.println(model.getRowCount());
			if(sl>0) {
				for (int i = 0; i < sl; i++) {
					model.setValueAt(true, i, 7);
					//String ma=model.getValueAt(i, 1).toString();
					
				}
			}
			comMaBP.setSelectedIndex(1);
//			System.out.println(dsKq.size());
		}
		if(o.equals(btnLuuThongTin)) {
			Date d= date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			String caLamViec=comCaLamviec.getSelectedItem().toString();
			String mabp=comMaBP.getSelectedItem().toString();
			
			
			if(!bangChamCongDAO.kiemTraChamCongCN(date, caLamViec)) {
				btnLuuThongTin.setText("Thay đổi thông tin");
				ArrayList<CongNhan> ds=congNhanDAO.getAllCongNhan();
				for (CongNhan cn : ds) {
					String tt="Vắng mặt";
					if(kiemTraDSKQ(cn)) {
						tt="Có mặt";
					}
//					System.out.println(tt);
					Nguoi nguoi=nguoiDAO.getNguoiByID(cn.getNguoiID());
					bangChamCongDAO.addBangChamCong(nguoi, date, caLamViec, tt);
				}
				
				JOptionPane.showMessageDialog(null, "Lưu thông tin thành công");
				btnHuyDiemDanh.setEnabled(true);
				btnPhanCongBoPhan.setEnabled(true);
				btnLuuThongTin.setEnabled(true);
				jpDiemDanh.setVisible(true);
				ArrayList<CongNhan> dsCoMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Có mặt");
				ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Vắng mặt");
				lblKQCoMat.setText(dsCoMat.size()+" Công nhân");
				lblKQVangMat.setText(dsVangMat.size() + " Công nhân");
				model.setRowCount(0);
				comMaBP.setSelectedIndex(1);
				
				jpVangBP.setVisible(true);
//				ArrayList<CongNhan> dsCN=new ArrayList<CongNhan>();
//				if(mabp.equals("Tất cả")) {
//					dsCN=bangChamCongDAO.get
//				}
			}
			else {
				btnLuuThongTin.setText("Lưu thông tin");
				ArrayList<CongNhan> dsCoMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Có mặt");
				ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Vắng mặt");
				lblKQCoMat.setText(dsCoMat.size()+" Công nhân");
				lblKQVangMat.setText(dsVangMat.size() + " Công nhân");
				JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
			}
			comMaBP.setSelectedIndex(1);
		}
		if(o.equals(btnPhanCongBoPhan)) {
			String ma=comMaBP.getSelectedItem().toString();
			if(ma.equals("Tất cả")) {
				JOptionPane.showMessageDialog(null, "Chưa chọn bộ phận");
			}
			else {
				this.setVisible(false);
				String caLamViec=comCaLamviec.getSelectedItem().toString();
				NQLSX_PhanCongBoPhan_GUI phancong=new NQLSX_PhanCongBoPhan_GUI(ma,caLamViec);
				this.getParent().add(phancong).setVisible(true);
				//int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
				setMau(this.getParent().getParent().getComponent(0).getComponentAt(0, 40).getComponentAt(getWidth()/2, 40));
				setMauMacDinh(this.getParent().getParent().getComponent(0).getComponentAt(0, 40).getComponentAt(getWidth()/4, 40));
			}
			
		}
		if(o.equals(comCaLamviec)) {
			String caLam=comCaLamviec.getSelectedItem().toString();
			Date d= date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			kiemTraChamCong(date, caLam);
//			dsKq=new ArrayList<CongNhan>();
//			dsKq=bangChamCongDAO.getDSChamCongCN(date, caLam, "Có mặt");
			ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
			String bp=comMaBP.getSelectedItem().toString();
			if(bangChamCongDAO.kiemTraChamCongCN(date, caLam)) {
				ds=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLam, bp);
			}
			else {
				ds=congNhanDAO.getDSCongNhanTheoBoPhan(bp);
			}
			model.setRowCount(0);
			themModel(ds);
//			System.out.println(dsKq.size());
		}
		
		
		
	}
	private boolean kiemTraDSKQ(CongNhan cn) {
		for (CongNhan cnkq : dsKq) {
			if(cnkq.getNguoiID().equals(cn.getNguoiID())) {
				return true;
			}
		}
		return false;
	}

	public void setMau(Component component) {
		((JComponent) component).setBorder(new LineBorder(new Color(196, 98, 0), 2));
		component.setBackground(Color.ORANGE);
	}
	public void setMauMacDinh(Component jp) {
		((JComponent) jp).setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jp.setBackground(new Color(0, 153, 153));
	}
	private void xoaRong() {
		txtMaCN.setText("");
		txtHoTen.setText("");
		ngaySinh.setDate(null);
		group.clearSelection();
		txtSDT.setText("");
		txtEmail.setText("");
		groupTinhTrang.clearSelection();
	}

	private void themModel(ArrayList<CongNhan> ds) {
		int stt=1;
		for (CongNhan congNhan : ds) {
			Object[] o=new Object[10];
			o[0]=stt;
			o[1]=congNhan.getNguoiID();
			o[2]=congNhan.getHoTen();
			String gt="";
			if(congNhan.isGioiTinh()==true) {
				gt="Nam";
			}
			if(congNhan.isGioiTinh()==false) {
				gt="Nữ";
			}
			o[3]=gt;
			o[4]=congNhan.getNgaySinh();
			o[5]=congNhan.getEmail();
			o[6]=congNhan.getSoDienThoai();
			if(timNguoiTheoMa(congNhan.getNguoiID())!=-1) {
				o[7]=true;
			}
			else
				o[7]=false;
			model.addRow(o);
			stt++;
		}
	}

		
	public int timNguoiTheoMa(String id) {
		if(!dsKq.isEmpty()) {
			for (CongNhan cn : dsKq) {
				if(cn.getNguoiID().equals(id))
					return dsKq.indexOf(cn);
			}
		}
		return -1;
	}
	public boolean xoaNguoiTheoID(String id) {
		int vt=timNguoiTheoMa(id);
		if(vt!=-1) {
			dsKq.remove(vt);
			return true;
		}
		return false;
	}
	public void settable() {
		table.setColumnSelectionInterval(1, 1);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int col=e.getColumn();
		int row=e.getFirstRow();
		if(col==7) {
			String ma=model.getValueAt(row, 1).toString();
//			Nguoi nguoi=nguoiDAO.layNguoiTheoID(ma);
			CongNhan congNhan=congNhanDAO.getCongNhanTheoID(ma);
			Boolean tt= (Boolean) model.getValueAt(row, col);
			Date d=date.getDate();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			String ca =comCaLamviec.getSelectedItem().toString();
			BangChamCong bcc=bangChamCongDAO.getBangChamCong3Props(ma, ca, date);
			System.out.println(tt);
			if(bangChamCongDAO.kiemTraChamCongCN(date, ca)) {
				if(tt==true) {
					
					bangChamCongDAO.capNhatTrangThai(bcc.getChamCongID(), "Có mặt");
				}
				else {
					bangChamCongDAO.capNhatTrangThai(bcc.getChamCongID(), "Vắng mặt");
				}
				dsKq=bangChamCongDAO.getDSChamCongCN(date, ca,"Có mặt");
				
			}else {
				if(tt==true) {
					dsKq.add(congNhan);
				}
				else {
					xoaNguoiTheoID(ma);
				}
			}
			
		}
//		System.out.println(dsKq.size());
		
	}
	private void kiemTraChamCong(LocalDate date,String caLamViec) {
		dsKq=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Có mặt");
		if(date.equals(LocalDate.now())) {
			if(caLamViec!=null) {
				if(bangChamCongDAO.kiemTraChamCongCN(date, caLamViec)) {
					btnLuuThongTin.setText("Thay đổi chấm công");
					jpDiemDanh.setVisible(true);
					ArrayList<CongNhan>dsCoMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Có mặt");
					ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Vắng mặt");
					lblKQCoMat.setText(dsCoMat.size()+" Công nhân");
					lblKQVangMat.setText(dsVangMat.size()+" Công nhân");
					
					jpVangBP.setVisible(true);
					String mabp=comMaBP.getSelectedItem().toString();
//					if(mabp.equals("Tất cả")) {
//						lblKQTong.setText(dsCoMat.size()+dsVangMat.size()+ " Công nhân");
//						lblKQVangBP.setText(dsVangMat.size()+ " Công nhân");
//					}
//					System.out.println(mabp);
					ArrayList<CongNhan> dsVangBP=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLamViec, mabp, "Vắng mặt");
//					System.out.println(dsVangBP.size());
//					int sl=congNhanDAO.getSoLuongCNTheoBoPhan(comMaBP.getSelectedItem().toString());
	//				lblKQTong.setText(sl+" Công nhân");
					lblKQVangBP.setText(dsVangBP.size()+" Công nhân");
					btnHuyDiemDanh.setEnabled(true);
					btnPhanCongBoPhan.setEnabled(true);
					btnLuuThongTin.setEnabled(true);
				}
				else {
					btnLuuThongTin.setText("Lưu thông tin");
					jpDiemDanh.setVisible(false);
					jpVangBP.setVisible(false);
					
					btnHuyDiemDanh.setEnabled(true);
					if(date.isAfter(LocalDate.now() )|| date.equals(LocalDate.now()))
						btnPhanCongBoPhan.setEnabled(true);
					else
						btnPhanCongBoPhan.setEnabled(false);
					btnLuuThongTin.setEnabled(true);
				}
				comMaBP.setSelectedIndex(1);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Chưa chọn ca làm việc");
			}
		}
		else if(date.isBefore(LocalDate.now())) {
			jpDiemDanh.setVisible(true);
			ArrayList<CongNhan>dsCoMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Có mặt");
			ArrayList<CongNhan> dsVangMat=bangChamCongDAO.getDSChamCongCN(date, caLamViec, "Vắng mặt");
			lblKQCoMat.setText(dsCoMat.size()+" Công nhân");
			lblKQVangMat.setText(dsVangMat.size()+" Công nhân");
			comMaBP.setSelectedIndex(1);
			btnHuyDiemDanh.setEnabled(false);
			btnPhanCongBoPhan.setEnabled(false);
			btnLuuThongTin.setEnabled(false);
		}
		else {
			jpDiemDanh.setVisible(false);
			comMaBP.setSelectedIndex(1);
			btnHuyDiemDanh.setEnabled(false);
			btnPhanCongBoPhan.setEnabled(false);
			btnLuuThongTin.setEnabled(false);

		}
	}




	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if("date".equals(e.getPropertyName())) {
			Date d=(Date) e.getNewValue();
			Instant ins=d.toInstant();
			LocalDate date = ins.atZone(ZoneId.systemDefault()).toLocalDate();
			
			comCaLamviec.setSelectedIndex(0);
			String caLam=comCaLamviec.getSelectedItem().toString();
			kiemTraChamCong(date,caLam);
			dsKq=bangChamCongDAO.getDSChamCongCN(date, caLam, "Có mặt");
			String bp=comMaBP.getSelectedItem().toString();
			ArrayList<CongNhan> ds=new ArrayList<CongNhan>();
			if(bangChamCongDAO.kiemTraChamCongCN(date, caLam)) {
				ds=bangChamCongDAO.getDSChamCongCNTheoBoPhan(date, caLam, bp);
			}
			else {
				ds=congNhanDAO.getDSCongNhanTheoBoPhan(bp);
			}
			model.setRowCount(0);
			themModel(ds);
		}
		
	}
}
