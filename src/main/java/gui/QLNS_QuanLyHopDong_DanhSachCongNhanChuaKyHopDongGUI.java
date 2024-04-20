package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.BoPhanDAO;
import dao.CongNhanDAO;
import dao.NguoiDAO;
import entities.CongNhan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCongNhan;
	static DefaultTableModel model;
	public CongNhanDAO congNhanDAO = new CongNhanDAO();
	public BoPhanDAO boPhanDAO = new BoPhanDAO();
	public NguoiDAO nguoiDAO = new NguoiDAO();
	public static String maCongNhan;
	public static QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI hopDongFrame = new QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hopDongFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLNS_QuanLyHopDong_DanhSachCongNhanChuaKyHopDongGUI() {
		setTitle("Danh sách nhân viên chưa ký hợp đồng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 1155, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Danh sách công nhân chưa ký hợp đồng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(331, 23, 445, 34);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 146, 1141, 310);
		contentPane.add(scrollPane);

		tableCongNhan = new JTable();
		tableCongNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableCongNhan.getSelectedRow();
			
//				System.out.println(maCongNhan);
			}
		});
		tableCongNhan.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "STT", "Mã công nhân", "Họ tên", "Ngày sinh", "Giới tính", "Số CMND", "Địa chỉ", "Email",
				"Số điện thoại", "Bộ phận", "Hợp đồng" }));
		TableColumnModel tableColumnModel = tableCongNhan.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(10);
		tableColumnModel.getColumn(3).setPreferredWidth(20);
		tableColumnModel.getColumn(4).setPreferredWidth(20);
		tableColumnModel.getColumn(5).setPreferredWidth(50);
		tableColumnModel.getColumn(7).setPreferredWidth(120);
		tableColumnModel.getColumn(10).setPreferredWidth(30);
		tableColumnModel.getColumn(11).setPreferredWidth(30);
		scrollPane.setViewportView(tableCongNhan);

		JButton btnThem = new JButton("Chọn nhân viên");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnThem)) {
					int row = tableCongNhan.getSelectedRow();
					if (row == -1)
						showMessage("Thông báo", "Mời chọn 1 công nhân bằng cách click vào bất kỳ công nhân nào trong bảng");
					else {
						int option = JOptionPane.showConfirmDialog(hopDongFrame, "Chọn nhân viên này để ký hợp đồng?", "Chọn nhân viên", JOptionPane.YES_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							maCongNhan = tableCongNhan.getValueAt(row, 1).toString();
							QLNS_QuanLyHopDongLaoDong_GUI.textMaCongNhan.setText(maCongNhan);
							showMessage("Thông báo", "Chọn thành công");
							hopDongFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
						} else
							showMessage("Thông báo", "Mời chọn nhân viên khác");
					}
				}

			}
		});
		btnThem.setBounds(1016, 108, 125, 28);
		contentPane.add(btnThem);
		loadDataTable();
	}

	private void loadDataTable() {
		int stt = 1;
		model = (DefaultTableModel) tableCongNhan.getModel();
		model.setRowCount(0);
		List<CongNhan> list = congNhanDAO.getAllCongNhan();
		for (CongNhan cn : list) {
			model.addRow(new Object[] { stt, cn.getNguoiID(), cn.getHoTen(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy").format(cn.getNgaySinh()), // format cho giống người Ziệt
																						// // -.-
					cn.isGioiTinh() == true ? "Nam" : "Nữ", cn.getSoCMND(), cn.getDiaChi(), cn.getEmail(),
					cn.getSoDienThoai(), cn.getBoPhan().getTenBoPhan(), "Chưa ký hợp đồng nào" });
			stt++;
		}
	}

	private void showMessage(String title, String noiDung) { // JOptionPane nằm dưới Jframe thì dùng cách này để fix
		JOptionPane optionPane = new JOptionPane(noiDung, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Thông báo");
		dialog.setAlwaysOnTop(true);//
		dialog.setVisible(true);
	}
}
