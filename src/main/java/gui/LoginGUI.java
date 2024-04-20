
package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.ChucVuDAO;
import dao.NguoiDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import db.DBConnection;
import entities.ChucVu;
import entities.NhanVien;
import entities.TaiKhoan;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


// 232PBP0010003 232PBP0010003 Tổ trưởng bộ phận
// 232PSX0010001 123 Trưởng phòng sản xuất
// 232PKT0010003 123 Kế toán tiền lương
// 232PNS0010001 123 Trưởng phòng nhân sự
public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lbDangNhap;
	private JTextField txtDangNhap;
	private JPasswordField txtMatKhau;
	public static String chucVu, hoTen;
	
	public NhanVienDAO nhanVienDAO = new NhanVienDAO();
	public TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	public ChucVuDAO chucVuDAO = new ChucVuDAO();
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public NhanVien nhanVien;
	public TaiKhoan taiKhoan;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginGUI().setVisible(true);
			
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Đăng nhập");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 447);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(33, 156, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel ImageLogin = new JLabel("");
		ImageIcon img_1 = new ImageIcon(this.getClass().getResource("/hinh_nen_login.png"));
		ImageLogin.setIcon(img_1);
		ImageLogin.setBounds(10, 72, 360, 303);
		contentPane.add(ImageLogin);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img_2 = new ImageIcon(this.getClass().getResource("/hinh_goc_login.png"));
		lblNewLabel.setIcon(img_2);
		lblNewLabel.setBounds(0, 10, 55, 32);
		contentPane.add(lblNewLabel);
		
		lbDangNhap = new JLabel("ĐĂNG NHẬP");
		lbDangNhap.setForeground(new Color(238, 147, 34));
		lbDangNhap.setFont(new Font("Arial", Font.BOLD, 34));
		lbDangNhap.setBounds(507, 78, 231, 57);
		contentPane.add(lbDangNhap);
		
		JLabel lbTenDangNhap = new JLabel("Tên đăng nhập:");
		lbTenDangNhap.setForeground(new Color(255, 255, 255));
		lbTenDangNhap.setFont(new Font("Arial", Font.BOLD, 13));
		lbTenDangNhap.setBounds(461, 142, 103, 13);
		contentPane.add(lbTenDangNhap);
		
		txtDangNhap = new JTextField("232PKT0010003");
		txtDangNhap.setBounds(461, 172, 270, 25);
		contentPane.add(txtDangNhap);
		txtDangNhap.setColumns(10);
		
		JLabel lbMatKhau = new JLabel("Mật khẩu:");
		lbMatKhau.setForeground(new Color(255, 255, 255));
		lbMatKhau.setFont(new Font("Arial", Font.BOLD, 13));
		lbMatKhau.setBounds(461, 233, 89, 25);
		contentPane.add(lbMatKhau);
		
		txtMatKhau = new JPasswordField("123");
		txtMatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { // con trỏ đang ở txtMatKhau
					if(txtDangNhap.getText().equals("") || txtMatKhau.getText().equals("")) {
						showMessage("Thông báo", "Không được để trống các trường này");
					}
					else {
						NhanVien nhanVien = nhanVienDAO.getNhanVienByID(txtDangNhap.getText());
						if(nhanVien == null)
							showMessage("Thông báo", "Tài khoản không tồn tại");					
						else {
							TaiKhoan taiKhoan = taiKhoanDAO.checkLogin(nhanVien.getNguoiID(), txtMatKhau.getText());
							if(taiKhoan != null) {
								ResultSet rs = nguoiDAO.getTenNguoiByID(nhanVien.getNguoiID());
								try {
									while(rs.next())
										try {
											hoTen = rs.getString(1);
										} catch (SQLException e1) {
											e1.printStackTrace();
										}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								chucVu = nhanVien.getChucVu().getTenChucVu();
								MainGUI main = new MainGUI();
								main.setLocationRelativeTo(null);
								main.setVisible(true);
								//dispose();
							}
							else
								showMessage("Thông báo", "Mật khẩu không đúng");			
						}
					}
				}
			}
		});
		txtMatKhau.setBounds(461, 263, 269, 25);
		contentPane.add(txtMatKhau);
		txtMatKhau.setColumns(10);
		final JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if(o.equals(btnDangNhap)) { // nhấn nút btnDangNhap
					if(txtDangNhap.getText().equals("") || txtMatKhau.getText().equals("")) { // cả hai trường đều trống
						showMessage("Thông báo", "Không được để trống các trường này");
					}
					else {
						nhanVien = nhanVienDAO.getNhanVienByID(txtDangNhap.getText()); // kiểm tra nhân viên có tồn tại trong csdl hay không
						if(nhanVien == null)
							showMessage("Thông báo", "Tài khoản không tồn tại");					
						else {
							taiKhoan = taiKhoanDAO.checkLogin(nhanVien.getNguoiID(), txtMatKhau.getText()); // kiểm tra tài khoản có tồn tại trong csdl hay không
							if(taiKhoan != null) {
								ResultSet rs = nguoiDAO.getTenNguoiByID(nhanVien.getNguoiID());// trả về 1 Result Set chứa giá trị là một chuỗi
								try {
									while(rs.next())
										try {
											hoTen = rs.getString(1); // lấy họ tên cho MainGUI
										} catch (SQLException e1) {
											e1.printStackTrace();
										}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								chucVu = nhanVien.getChucVu().getTenChucVu(); // lấy chức vụ cho MainGUI
								
								MainGUI main = new MainGUI();
								main.setLocationRelativeTo(null);
								main.setVisible(true);
								dispose();
							}
							else
								showMessage("Thông báo", "Mật khẩu không đúng");			
						}
					}								
				}
			}
		});
		
		btnDangNhap.setOpaque(true);
		btnDangNhap.setBorderPainted(false);
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setBackground(new Color(238, 147, 34));
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 10));
		btnDangNhap.setBounds(536, 315, 120, 31);
		contentPane.add(btnDangNhap);
		
		JLabel lbQMK = new JLabel("Quên mật khẩu?");
		lbQMK.setForeground(new Color(255, 255, 255));
		lbQMK.setFont(new Font("Arial", Font.PLAIN, 12));
		lbQMK.setBounds(550, 371, 110, 13);
		contentPane.add(lbQMK);
	}
	
	private void showMessage(String title, String noiDung) { //JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);// 
		dialog.setVisible(true);
	}
	
}
