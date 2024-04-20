package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainGUI extends JFrame {
	private static MainGUI frame =  new MainGUI();
	private JPanel contentPane;
	public static QLNS_Main_GUI qlns_Main_GUI;
	public static NQLSX_ManHinhChinh_GUI qlNqlsx_ManHinhChinh_GUI;
	public static TTBP_Main_GUI ttbp_Main_GUI;
	public static NVKT_Main_GUI nvkt_Main_GUI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setLocationRelativeTo(null);
					Toolkit tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
			        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
			        frame.setSize(screenSize.width,screenSize.height);
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1536, 864);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ImageMainGUI = new JLabel("New label");
		ImageMainGUI.setBackground(new Color(255, 255, 255));
		ImageIcon imageGUI = new ImageIcon(this.getClass().getResource("/hinh_nen_MHC.png"));
		ImageMainGUI.setIcon(imageGUI);
		ImageMainGUI.setBounds(161, 66, 1137, 531);
		contentPane.add(ImageMainGUI);
		
		JLabel lblNewLabel = new JLabel("PHẦN MỀM QUẢN LÝ LƯƠNG SẢN PHẨM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(369, -7, 824, 90);
		contentPane.add(lblNewLabel);
		
		JLabel lbChaoMung = new JLabel("Chào mừng "+ LoginGUI.hoTen);
		lbChaoMung.setHorizontalAlignment(SwingConstants.CENTER);
		lbChaoMung.setFont(new Font("Arial", Font.BOLD, 16));
		lbChaoMung.setBounds(632, 619, 300, 23);
		contentPane.add(lbChaoMung);
		
		JLabel lbTuCachSuDung = new JLabel("Bạn đang sử dụng phần mềm với tư cách là "+ LoginGUI.chucVu);
		lbTuCachSuDung.setHorizontalAlignment(SwingConstants.CENTER);
		lbTuCachSuDung.setFont(new Font("Arial", Font.BOLD, 16));
		lbTuCachSuDung.setBounds(553, 652, 482, 20);
		contentPane.add(lbTuCachSuDung);
		
		JButton btnBatDau = new JButton("Bắt đầu");
		btnBatDau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnBatDau)) {
					if(LoginGUI.chucVu.equals("Trưởng phòng nhân sự")) {
						try {
							qlns_Main_GUI = new QLNS_Main_GUI();
							qlns_Main_GUI.setVisible(true);
							dispose();
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}						
					else if(LoginGUI.chucVu.equals("Tổ trưởng bộ phận")) {
						try {
							ttbp_Main_GUI = new TTBP_Main_GUI();
							ttbp_Main_GUI.setVisible(true);
							dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(LoginGUI.chucVu.equals("Kế toán viên")) {
						nvkt_Main_GUI = new NVKT_Main_GUI();
						nvkt_Main_GUI.setVisible(true);
						dispose();
					}
					else if(LoginGUI.chucVu.equals("Trưởng phòng sản xuất")) {
						qlNqlsx_ManHinhChinh_GUI = new NQLSX_ManHinhChinh_GUI();
						qlNqlsx_ManHinhChinh_GUI.setVisible(true);
						dispose();
					}
						
				}
			}
		});
		btnBatDau.setBackground(new Color(33, 156, 144));
		btnBatDau.setOpaque(true);
		btnBatDau.setBorderPainted(false);
		btnBatDau.setForeground(new Color(255, 255, 255));
		btnBatDau.setFont(new Font("Arial", Font.BOLD, 16));
		btnBatDau.setBounds(688, 682, 160, 45);
		contentPane.add(btnBatDau);
		
		final JButton btnDangXuat = new JButton("Đăng xuất");
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
		btnDangXuat.setBackground(new Color(33, 156, 144));
		btnDangXuat.setOpaque(true);
		btnDangXuat.setBorderPainted(false);
		btnDangXuat.setFont(new Font("Arial", Font.BOLD, 16));
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setBounds(688, 747, 160, 45);
		contentPane.add(btnDangXuat);
	
	}
}
