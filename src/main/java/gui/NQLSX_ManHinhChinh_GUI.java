package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JDesktopPane;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;

public class NQLSX_ManHinhChinh_GUI extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpSouth;
	private JPanel jpHeader;
	private JPanel jpChucNang;
	private JLabel lblImg;
	private JLabel lblTenUser;
	private JButton btnDangXuat;
	private JPanel jpChamCong;
	private JLabel lblChamCong;
	private JPanel jpPhanCong;
	private JLabel lblPhanCong;
	private JPanel jpQuanLySP;
	private JLabel lblQuanLySP;
	private JPanel jpThongKe;
	private JLabel lblThongKe;
	private JDesktopPane jpMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NQLSX_ChamCongCongNhan_GUI chamCong=new NQLSX_ChamCongCongNhan_GUI();
					NQLSX_ManHinhChinh_GUI frame = new NQLSX_ManHinhChinh_GUI(chamCong);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NQLSX_ManHinhChinh_GUI() {
		setBounds(new Rectangle(0, 0, 900, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		NQLSX_ChamCongCongNhan_GUI chamCong=new NQLSX_ChamCongCongNhan_GUI();
		createGUI(chamCong);
	}
	public NQLSX_ManHinhChinh_GUI(JPanel jp) {
		setBounds(new Rectangle(0, 0, 900, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		createGUI(jp);
		
	}
	
	private void createGUI(JPanel jp) {
		createGUI();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		jp.setBounds(0, 0,width,height-80);
		jpMain.add(jp);
	}
	private void createGUI() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jpSouth = new JPanel();
		jpSouth.setPreferredSize(new Dimension(10, 80));
		contentPane.add(jpSouth, BorderLayout.NORTH);
		jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.Y_AXIS));
		
		jpHeader = new JPanel();
		FlowLayout fl_jpHeader = (FlowLayout) jpHeader.getLayout();
		fl_jpHeader.setAlignment(FlowLayout.LEFT);
		jpHeader.setBorder(new EmptyBorder(0, 0, 2, 0));
		jpHeader.setBackground(new Color(0, 153, 153));
		jpHeader.setPreferredSize(new Dimension(10, 35));
		jpSouth.add(jpHeader);
		
		lblImg = new JLabel("");
		ImageIcon imgICon = new ImageIcon(this.getClass().getResource("/iconUser.png"));
		lblImg.setIcon(imgICon);
		jpHeader.add(lblImg);
		
		lblTenUser = new JLabel("Nguyễn Văn A");
		lblTenUser.setLabelFor(lblImg);
		lblTenUser.setForeground(Color.WHITE);
		lblTenUser.setFont(new Font("Arial", Font.PLAIN, 12));
		jpHeader.add(lblTenUser);
//Đóng giao diện đăng xuất		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnDangXuat)) {
					LoginGUI gui = new LoginGUI();
					gui.setLocationRelativeTo(null);
					gui.setVisible(true);
					dispose();
				}
			}
		});
		btnDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnDangXuat.setBackground(Color.ORANGE);
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("Arial", Font.BOLD, 12));
		jpHeader.add(btnDangXuat);
		
		jpChucNang = new JPanel();
		jpChucNang.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255)));
		jpChucNang.setPreferredSize(new Dimension(10, 45));
		jpChucNang.setBackground(new Color(0, 153, 153));
		jpSouth.add(jpChucNang);
		jpChucNang.setLayout(new BoxLayout(jpChucNang, BoxLayout.X_AXIS));
		
		jpChamCong = new JPanel();
		FlowLayout fl_jpChamCong = (FlowLayout) jpChamCong.getLayout();
		fl_jpChamCong.setVgap(10);
		jpChamCong.setBorder(new LineBorder(new Color(196, 98, 0), 2));
		jpChamCong.setBackground(Color.ORANGE);
		jpChucNang.add(jpChamCong);
		
		lblChamCong = new JLabel("CHẤM CÔNG CÔNG NHÂN");
		lblChamCong.setForeground(Color.WHITE);
		lblChamCong.setFont(new Font("Arial", Font.BOLD, 16));
		jpChamCong.add(lblChamCong);
		
		jpPhanCong = new JPanel();
		FlowLayout fl_jpPhanCong = (FlowLayout) jpPhanCong.getLayout();
		fl_jpPhanCong.setVgap(10);
		jpPhanCong.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpPhanCong.setBackground(new Color(0, 153, 153));
		jpChucNang.add(jpPhanCong);
		
		lblPhanCong = new JLabel("PHÂN CÔNG BỘ PHẬN");
		lblPhanCong.setForeground(Color.WHITE);
		lblPhanCong.setFont(new Font("Arial", Font.BOLD, 16));
		jpPhanCong.add(lblPhanCong);
		
		jpQuanLySP = new JPanel();
		FlowLayout fl_jpQuanLySP = (FlowLayout) jpQuanLySP.getLayout();
		fl_jpQuanLySP.setVgap(10);
		jpQuanLySP.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpQuanLySP.setBackground(new Color(0, 153, 153));
		jpChucNang.add(jpQuanLySP);
		
		lblQuanLySP = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblQuanLySP.setForeground(Color.WHITE);
		lblQuanLySP.setFont(new Font("Arial", Font.BOLD, 16));
		jpQuanLySP.add(lblQuanLySP);
		
		jpThongKe = new JPanel();
		FlowLayout fl_jpThongKe = (FlowLayout) jpThongKe.getLayout();
		fl_jpThongKe.setVgap(10);
		jpThongKe.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpThongKe.setBackground(new Color(0, 153, 153));
		jpChucNang.add(jpThongKe);
		
		lblThongKe = new JLabel("THỐNG KÊ SẢN PHẨM");
		lblThongKe.setForeground(Color.WHITE);
		lblThongKe.setFont(new Font("Arial", Font.BOLD, 16));
		jpThongKe.add(lblThongKe);
		
		
		jpMain = new JDesktopPane();
		contentPane.add(jpMain, BorderLayout.CENTER);//, 
		jpMain.setLayout(new BorderLayout(0, 0));
//		NQLSX_ChamCongCongNhan_GUI chamcong=new NQLSX_ChamCongCongNhan_GUI();
//		chamcong.setVisible(true);
//		jpMain.add(chamcong,BorderLayout.CENTER);
		
		
		jpChamCong.addMouseListener(this);
		jpPhanCong.addMouseListener(this);
		jpQuanLySP.addMouseListener(this);
		jpThongKe.addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o=e.getSource();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if(o.equals(jpChamCong)) {
			setMau(jpChamCong);
			jpMain.removeAll();
			NQLSX_ChamCongCongNhan_GUI chamCong=new NQLSX_ChamCongCongNhan_GUI();
			chamCong.setBounds(0, 0,width,height-80);
			jpMain.add(chamCong).setVisible(true);
		}
		else if(o.equals(jpPhanCong)) {
			setMau(jpPhanCong);
			jpMain.removeAll();
			NQLSX_PhanCongBoPhan_GUI phanCong=new NQLSX_PhanCongBoPhan_GUI();
			phanCong.setBounds(0, 0,width,height-80);
			jpMain.add(phanCong).setVisible(true);;
		}
		else if(o.equals(jpQuanLySP)) {
			setMau(jpQuanLySP);
			jpMain.removeAll();
			NQLSX_QuanLySanPham_GUI qlSanPham=new NQLSX_QuanLySanPham_GUI();
			qlSanPham.setBounds(0, 0,width,height-80);
			jpMain.add(qlSanPham).setVisible(true);
		}
		else if(o.equals(jpThongKe)) {
			setMau(jpThongKe);
			jpMain.removeAll();
			NQLSX_ThongKe_GUI tk=new NQLSX_ThongKe_GUI();
			tk.setBounds(0, 0,width,height-80);
			jpMain.add(tk).setVisible(true);
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
	//Set màu mặc định cho 1 jpanel
	public void setMauMacDinh(JPanel jp) {
		jp.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jp.setBackground(new Color(0, 153, 153));
	}
	//Set màu mặc định cho tất cả jp chức năng
	public void setMauMacDinh() {
		setMauMacDinh(jpChamCong);
		setMauMacDinh(jpPhanCong);
		setMauMacDinh(jpQuanLySP);
		setMauMacDinh(jpThongKe);
	}
	public void setMau(JPanel jp) {
		setMauMacDinh();
		jp.setBorder(new LineBorder(new Color(196, 98, 0), 2));
		jp.setBackground(Color.ORANGE);
	}
}
