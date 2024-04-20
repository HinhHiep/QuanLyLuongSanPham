package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NVKT_Main_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel jpNorth;
	private JPanel jpHeader;
	private JLabel lblImg;
	private JLabel lblTenUser;
	private JButton btnDangXuat;
	private JPanel jpChucNang;
	private JPanel jpTinhLuongCongNhan;
	private JLabel lblChamCong;
	private JPanel jpTinhLuongNhanVien;
	private JLabel lblPhanCong;
	private JPanel jpThongKeLuong;
	private JLabel lblQuanLySP;
	private JPanel pCenter;
	private NVKT_TinhLuongCN tlcn;
	private NVKT_TinhLuongNV tlnv;
	private NVKT_ThongTinLuongNV_GUI ttlnv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NVKT_Main_GUI frame = new NVKT_Main_GUI();
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
	public NVKT_Main_GUI() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Tổ trưởng bộ phận");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(882, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		jpNorth = new JPanel();
		jpNorth.setPreferredSize(new Dimension(10, 80));
		contentPane.add(jpNorth, BorderLayout.NORTH);
		jpNorth.setLayout(new BoxLayout(jpNorth, BoxLayout.Y_AXIS));

		jpHeader = new JPanel();
		FlowLayout fl_jpHeader = (FlowLayout) jpHeader.getLayout();
		fl_jpHeader.setAlignment(FlowLayout.LEFT);
		jpHeader.setBorder(new EmptyBorder(0, 0, 2, 0));
		jpHeader.setBackground(new Color(0, 153, 153));
		jpHeader.setPreferredSize(new Dimension(10, 35));
		jpNorth.add(jpHeader);

		lblImg = new JLabel("");
		ImageIcon imgICon = new ImageIcon(this.getClass().getResource("/iconUser.png"));
		lblImg.setIcon(imgICon);
		jpHeader.add(lblImg);

		lblTenUser = new JLabel(LoginGUI.hoTen);
		lblTenUser.setLabelFor(lblImg);
		lblTenUser.setForeground(Color.WHITE);
		lblTenUser.setFont(new Font("Arial", Font.PLAIN, 12));
		jpHeader.add(lblTenUser);

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
		btnDangXuat.setBackground(Color.ORANGE);
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("Arial", Font.BOLD, 12));
		jpHeader.add(btnDangXuat);

		jpChucNang = new JPanel();
		jpChucNang.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255)));
		jpChucNang.setPreferredSize(new Dimension(10, 45));
		jpChucNang.setBackground(new Color(0, 153, 153));
		jpNorth.add(jpChucNang);
		jpChucNang.setLayout(new BoxLayout(jpChucNang, BoxLayout.X_AXIS));

		jpTinhLuongCongNhan = new JPanel();
		jpTinhLuongCongNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(jpTinhLuongCongNhan)) {
					jpTinhLuongCongNhan.setBackground(new Color(238, 147, 34));
					jpTinhLuongNhanVien.setBackground(new Color(33, 156, 144));
					jpThongKeLuong.setBackground(new Color(33, 156, 144));
					
					pCenter.removeAll();
					pCenter.add(new NVKT_TinhLuongCN());
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_jpTinhLuongCongNhan = (FlowLayout) jpTinhLuongCongNhan.getLayout();
		fl_jpTinhLuongCongNhan.setVgap(10);
		jpTinhLuongCongNhan.setBorder(new LineBorder(new Color(196, 98, 0), 2));
		jpTinhLuongCongNhan.setBackground(new Color(238, 147, 34));
		jpChucNang.add(jpTinhLuongCongNhan);

		lblChamCong = new JLabel("TÍNH LƯƠNG CÔNG NHÂN");
		lblChamCong.setForeground(Color.WHITE);
		lblChamCong.setFont(new Font("Arial", Font.BOLD, 16));
		jpTinhLuongCongNhan.add(lblChamCong);

		jpTinhLuongNhanVien = new JPanel();
		jpTinhLuongNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(jpTinhLuongNhanVien)) {
					jpTinhLuongCongNhan.setBackground(new Color(33, 156, 144));
					jpTinhLuongNhanVien.setBackground(new Color(238, 147, 34));
					jpThongKeLuong.setBackground(new Color(33, 156, 144));
					
					pCenter.removeAll();
					pCenter.add(new NVKT_TinhLuongNV());
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_jpTinhLuongNhanVien = (FlowLayout) jpTinhLuongNhanVien.getLayout();
		fl_jpTinhLuongNhanVien.setVgap(10);
		jpTinhLuongNhanVien.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpTinhLuongNhanVien.setBackground(new Color(0, 153, 153));
		jpChucNang.add(jpTinhLuongNhanVien);

		lblPhanCong = new JLabel("TÍNH LƯƠNG NHÂN VIÊN");
		lblPhanCong.setForeground(Color.WHITE);
		lblPhanCong.setFont(new Font("Arial", Font.BOLD, 16));
		jpTinhLuongNhanVien.add(lblPhanCong);

		jpThongKeLuong = new JPanel();
		jpThongKeLuong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(jpThongKeLuong)) {
					jpTinhLuongCongNhan.setBackground(new Color(33, 156, 144));
					jpTinhLuongNhanVien.setBackground(new Color(33, 156, 144));
					jpThongKeLuong.setBackground(new Color(238, 147, 34));
					
					pCenter.removeAll();
					pCenter.add(new ThongKe());
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_jpThongKeLuong = (FlowLayout) jpThongKeLuong.getLayout();
		fl_jpThongKeLuong.setVgap(10);
		jpThongKeLuong.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpThongKeLuong.setBackground(new Color(0, 153, 153));
		jpChucNang.add(jpThongKeLuong);

		lblQuanLySP = new JLabel("THỐNG KÊ LƯƠNG");
		lblQuanLySP.setForeground(Color.WHITE);
		lblQuanLySP.setFont(new Font("Arial", Font.BOLD, 16));
		jpThongKeLuong.add(lblQuanLySP);

		pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
	

		tlcn = new NVKT_TinhLuongCN();
		tlnv = new NVKT_TinhLuongNV();
	
		pCenter.add(tlcn);
		pCenter.setVisible(true);
	}

}
