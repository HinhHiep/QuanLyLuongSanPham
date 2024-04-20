package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class TTBP_Main_GUI extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpNorth;
	private JPanel jpHeader;
	private JPanel jpChucNang;
	private JLabel lblImg;
	private JLabel lblTenUser;
	private JButton btnDangXuat;
	private JPanel jpPhanCongCN;
	private JLabel lblChamCong;
	private JPanel pCenter;
	private TTBP_PhanCongCongNhan_GUI pccn;
//	private TTBP_QuanLySanPhamCongDoan_GUI qlspcd;
//	private TTBP_DanhSachBoPhanDamNhiem_GUI dsbpdn;
	private JButton mylabel;
	private JPanel jpThongKe;
	private JLabel lblThngK;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTBP_Main_GUI frame = new TTBP_Main_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public TTBP_Main_GUI() throws IOException {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Tổ trưởng bộ phận");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(923, 647);
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
			public void actionPerformed1(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnDangXuat)) {
					LoginGUI gui = new LoginGUI();
					gui.setLocationRelativeTo(null);
					gui.setVisible(true);
					dispose();
				}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btnDangXuat.setBackground(Color.ORANGE);
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("Arial", Font.BOLD, 12));
		jpHeader.add(btnDangXuat);

		btnNewButton = new JButton("Help");
		btnNewButton.setPreferredSize(new Dimension(100, 30));
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/help.png")));
		jpHeader.add(btnNewButton);

		// Khi nhấp vào nút Help
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {// Lấy tham chiếu đến JFrame chứa JButton
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton);

					// Thu nhỏ cửa sổ JFrame
					frame.setState(Frame.ICONIFIED);
					String url = getClass().getResource("/files/index.html").toString();
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		jpChucNang = new JPanel();
		jpChucNang.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255)));
		jpChucNang.setPreferredSize(new Dimension(10, 45));
		jpChucNang.setBackground(new Color(0, 153, 153));
		jpNorth.add(jpChucNang);
		jpChucNang.setLayout(new BoxLayout(jpChucNang, BoxLayout.X_AXIS));

		jpPhanCongCN = new JPanel();

		FlowLayout fl_jpPhanCongCN = (FlowLayout) jpPhanCongCN.getLayout();
		fl_jpPhanCongCN.setVgap(10);
		jpPhanCongCN.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jpPhanCongCN.setBackground(new Color(0, 128, 128));
		jpChucNang.add(jpPhanCongCN);

		lblChamCong = new JLabel("PHÂN CÔNG CÔNG NHÂN");
		lblChamCong.setForeground(Color.WHITE);
		lblChamCong.setFont(new Font("Arial", Font.BOLD, 16));
		jpPhanCongCN.add(lblChamCong);

		pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));

		pCenter.add(new TTBP_PhanCongCongNhan_GUI());
		jpPhanCongCN.setBackground(new Color(255, 128, 64));

		jpThongKe = new JPanel();

		jpThongKe.setBorder(new EmptyBorder(6, 0, 0, 0));
		jpThongKe.setBackground(new Color(0, 128, 128));
		jpChucNang.add(jpThongKe);
		jpThongKe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblThngK = new JLabel("THỐNG KÊ");
		lblThngK.setForeground(Color.WHITE);
		lblThngK.setFont(new Font("Arial", Font.BOLD, 16));
		jpThongKe.add(lblThngK);
		pCenter.setVisible(true);

		jpPhanCongCN.addMouseListener(this);
		jpThongKe.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(jpPhanCongCN)) {
			pCenter.removeAll();
			pCenter.add(new TTBP_PhanCongCongNhan_GUI());
			pCenter.repaint();
			pCenter.revalidate();
			jpPhanCongCN.setBackground(new Color(255, 128, 64));
			jpThongKe.setBackground(new Color(0, 128, 128)); // Thêm dòng này để trở về màu mặc định của jpThongKe
		} else if (e.getSource().equals(jpThongKe)) {
			pCenter.removeAll();
			pCenter.add(new TTBP_ThongKe());
			pCenter.repaint();
			pCenter.revalidate();
			jpThongKe.setBackground(new Color(255, 128, 64));
			jpPhanCongCN.setBackground(new Color(0, 128, 128)); // Thêm dòng này để trở về màu mặc định của jpPhanCongCN
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

	// Set màu mặc định cho 1 jpanel
	public void setMauMacDinh(JPanel jp) {
		jp.setBorder(new LineBorder(new Color(0, 91, 91), 2));
		jp.setBackground(new Color(0, 153, 153));
	}

	// Set màu mặc định cho tất cả jp chức năng
	public void setMauMacDinh() {
		setMauMacDinh(jpPhanCongCN);
	}

	public void setMau(JPanel jp) {
		setMauMacDinh();
		jp.setBorder(new LineBorder(new Color(196, 98, 0), 2));
		jp.setBackground(Color.ORANGE);
	}
}
