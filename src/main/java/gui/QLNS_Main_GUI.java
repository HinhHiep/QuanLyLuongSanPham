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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QLNS_Main_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel jpNorth;
	private JPanel jpHeader;
	private JLabel lblImg;
	private JLabel lblTenUser;
	private JButton btnDangXuat;
	private JPanel jpChucNang;
	private JPanel btnQLNV;
	private JLabel lbQLNV;
	private JPanel btnQLCN;
	private JLabel lbQLCN;
	private JPanel btnQLHD;
	private JLabel lbQLHD;
	private JPanel pCenter;
	private NVKT_TinhLuongCN tlcn;
	private NVKT_TinhLuongNV tlnv;
	private NVKT_ThongTinLuongNV_GUI ttlnv;
	private QLNS_QuanLyNhanVien_GUI qlsv;
	private JPanel btnCCNV;
	private JLabel lbCCNV;
	private QLNS_QuanLyCongNhan_GUI qlcn;
	private QLNS_QuanLyHopDongLaoDong_GUI qlhdld;
	private QLNS_ChamCongNhanVien_GUI ccnv;
	public static QLNS_Main_GUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new QLNS_Main_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public QLNS_Main_GUI() throws ParseException {
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

		btnQLNV = new JPanel();
		btnQLNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(btnQLNV)) {
					btnQLNV.setBackground(new Color(238, 147, 34));
					btnQLCN.setBackground(new Color(33, 156, 144));
					btnQLHD.setBackground(new Color(33, 156, 144));
					btnCCNV.setBackground(new Color(33, 156, 144));

					pCenter.removeAll();
					pCenter.add(new QLNS_QuanLyNhanVien_GUI());
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_btnQLNV = (FlowLayout) btnQLNV.getLayout();
		fl_btnQLNV.setVgap(10);
		btnQLNV.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		btnQLNV.setBackground(new Color(238, 147, 34));
		jpChucNang.add(btnQLNV);

		lbQLNV = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lbQLNV.setForeground(Color.WHITE);
		lbQLNV.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLNV.add(lbQLNV);

		btnQLCN = new JPanel();
		btnQLCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(btnQLCN)) {
					btnQLCN.setBackground(new Color(238, 147, 34));
					btnQLNV.setBackground(new Color(33, 156, 144));
					btnQLHD.setBackground(new Color(33, 156, 144));
					btnCCNV.setBackground(new Color(33, 156, 144));

					pCenter.removeAll();
					pCenter.add(new QLNS_QuanLyCongNhan_GUI());
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_btnQLCN = (FlowLayout) btnQLCN.getLayout();
		fl_btnQLCN.setVgap(10);
		btnQLCN.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		btnQLCN.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnQLCN);

		lbQLCN = new JLabel("QUẢN LÝ CÔNG NHÂN");
		lbQLCN.setForeground(Color.WHITE);
		lbQLCN.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLCN.add(lbQLCN);

		btnQLHD = new JPanel();
		btnQLHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(btnQLHD)) {
					btnQLHD.setBackground(new Color(238, 147, 34));
					btnQLCN.setBackground(new Color(33, 156, 144));
					btnQLNV.setBackground(new Color(33, 156, 144));
					btnCCNV.setBackground(new Color(33, 156, 144));

					pCenter.removeAll();
					pCenter.add(new QLNS_QuanLyHopDongLaoDong_GUI());
					pCenter.repaint();
					pCenter.revalidate();

				}
			}
		});
		FlowLayout fl_btnQLHD = (FlowLayout) btnQLHD.getLayout();
		fl_btnQLHD.setVgap(10);
		btnQLHD.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		btnQLHD.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnQLHD);

		lbQLHD = new JLabel("QUẢN LÝ HỢP ĐỒNG");
		lbQLHD.setForeground(Color.WHITE);
		lbQLHD.setFont(new Font("Arial", Font.BOLD, 16));
		btnQLHD.add(lbQLHD);

		btnCCNV = new JPanel();
		btnCCNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(btnCCNV)) {
					btnCCNV.setBackground(new Color(238, 147, 34));
					btnQLNV.setBackground(new Color(33, 156, 144));
					btnQLCN.setBackground(new Color(33, 156, 144));
					btnQLHD.setBackground(new Color(33, 156, 144));

					pCenter.removeAll();
					try {
						pCenter.add(new QLNS_ChamCongNhanVien_GUI());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pCenter.repaint();
					pCenter.revalidate();
				}
			}
		});
		FlowLayout fl_btnCCNV = (FlowLayout) btnCCNV.getLayout();
		fl_btnCCNV.setVgap(10);
		btnCCNV.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		btnCCNV.setBackground(new Color(0, 153, 153));
		jpChucNang.add(btnCCNV);

		lbCCNV = new JLabel("CHẤM CÔNG NHÂN VIÊN");
		btnCCNV.add(lbCCNV);
		lbCCNV.setForeground(Color.WHITE);
		lbCCNV.setFont(new Font("Arial", Font.BOLD, 16));

		pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		//
		qlsv = new QLNS_QuanLyNhanVien_GUI();
		qlcn = new QLNS_QuanLyCongNhan_GUI();
		qlhdld = new QLNS_QuanLyHopDongLaoDong_GUI();
		ccnv = new QLNS_ChamCongNhanVien_GUI();

		pCenter.add(qlsv, BorderLayout.CENTER);
		pCenter.setVisible(true);
	}

}
