package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JDesktopPane;

public class NQLSX_QuanLySanPham_GUI extends JInternalFrame implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel jpPhanCong;
	private JPanel jpQuanLy;
	private JPanel jpMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NQLSX_QuanLySanPham_GUI frame = new NQLSX_QuanLySanPham_GUI();
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
	public NQLSX_QuanLySanPham_GUI() {
		setSize(1200, 700);
		getContentPane().setBackground(Color.WHITE);
		setBorder(null);
		setFrameIcon(null);
		InternalFrameUI ui=this.getUI();
		((BasicInternalFrameUI)ui).setNorthPane(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(10, 30));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		jpPhanCong = new JPanel();
		jpPhanCong.setBorder(new LineBorder(new Color(196, 98, 0), 2));
		jpPhanCong.setBackground(Color.ORANGE);
		FlowLayout fl_jpPhanCong = (FlowLayout) jpPhanCong.getLayout();
		fl_jpPhanCong.setVgap(7);
		panel.add(jpPhanCong);
		
		JLabel lblNewLabel = new JLabel("PHÂN CÔNG ĐOẠN SẢN PHẨM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		jpPhanCong.add(lblNewLabel);
		
		jpQuanLy = new JPanel();
		FlowLayout fl_jpQuanLy = (FlowLayout) jpQuanLy.getLayout();
		fl_jpQuanLy.setVgap(7);
		jpQuanLy.setBackground(new Color(0, 128, 128));
		jpQuanLy.setBorder(new LineBorder(new Color(0, 98, 98), 2));
		jpQuanLy.setPreferredSize(new Dimension(200, 30));
		panel.add(jpQuanLy);
		
		JLabel lblQuanLy = new JLabel("QUẢN LÝ THÔNG TIN SẢN PHẨM");
		lblQuanLy.setForeground(Color.WHITE);
		lblQuanLy.setFont(new Font("Arial", Font.BOLD, 12));
		jpQuanLy.add(lblQuanLy);
		
		jpMain = new JPanel();
		getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.setLayout(new BorderLayout(0, 0));
		
		NQLSX_PhanCongDoanSanPham_GUI phanCongDoanSanPham = new NQLSX_PhanCongDoanSanPham_GUI();
		phanCongDoanSanPham.setPreferredSize(new Dimension(1200, 387));
		jpMain.add(phanCongDoanSanPham);
		jpPhanCong.addMouseListener(this);
		jpQuanLy.addMouseListener(this);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o=e.getSource();
		if(o.equals(jpPhanCong)) {
			setMau(jpPhanCong);
			jpMain.removeAll();
            NQLSX_PhanCongDoanSanPham_GUI phanCongDoanSanPham=new NQLSX_PhanCongDoanSanPham_GUI(); //setPreferredSize(new Dimension(1200, 387));
//            phanCongDoanSanPham.setPreferredSize(new Dimension(1200, 387));
            jpMain.add(phanCongDoanSanPham,BorderLayout.CENTER);

            jpMain.repaint();
            jpMain.revalidate();
		}
		else if(o.equals(jpQuanLy)) {
			setMau(jpQuanLy);
			jpMain.removeAll();
            jpMain.add(new NQLSX_QuanLyThongTinSanPham_GUI(), BorderLayout.CENTER);
            jpMain.repaint();
            jpMain.revalidate();;
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
			setMauMacDinh(jpPhanCong);
			setMauMacDinh(jpQuanLy);
		}
		public void setMau(JPanel jp) {
			setMauMacDinh();
			jp.setBorder(new LineBorder(new Color(196, 98, 0), 2));
			jp.setBackground(Color.ORANGE);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
