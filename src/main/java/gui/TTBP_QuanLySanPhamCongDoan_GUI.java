package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CongDoanDAO;
import dao.NguyenLieuDAO;
import dao.SanPhamDAO;
import entities.CongDoan;
import entities.SanPham;

public class TTBP_QuanLySanPhamCongDoan_GUI extends JPanel {

	private JPanel contentPane;
	private JTable table_sp;
	private JTextField txtMaSanPham;
	private JTextField txtLoaiSanPham;
	private JTextField txtSoCongDoan;
	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTextField txtSoLuongHoanThanh;
	private JTextField txtCount;
	private JTable cdtable;
	private SanPhamDAO qlsp;
	private DefaultTableModel spmodel;
	private NguyenLieuDAO qlnl;
	private CongDoanDAO qlcd;
	private DefaultTableModel cdmodel;
	private JLabel imageLabel;
	private JLabel productName;
	private JTextField txtSoLuongYeuCau;

	public TTBP_QuanLySanPhamCongDoan_GUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		qlnl = new NguyenLieuDAO();
		JPanel pList = new JPanel();
		pList.setAlignmentY(1.0f);
		add(pList);
		pList.setLayout(new BoxLayout(pList, BoxLayout.X_AXIS));

		JPanel products = new JPanel();
		products.setBorder(new TitledBorder(null, "DANH S\u00C1CH S\u1EA2N PH\u1EA8M", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 128, 128)));
		pList.add(products);
		products.setLayout(new BoxLayout(products, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		products.add(panel);

		JLabel lblNewLabel = new JLabel("Tổng số: 12");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		products.add(panel_1);

		JButton btnNewButton1 = new JButton("Tìm kiếm sản phẩm");
		btnNewButton1.setBackground(new Color(0, 128, 0));
		btnNewButton1
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\iconTimkiem.png"));
		panel_1.add(btnNewButton1);

		JScrollPane scrollPane = new JScrollPane();
		products.add(scrollPane);

		table_sp = new JTable();
		table_sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selected = table_sp.getSelectedRow();
				DefaultTableModel temp = (DefaultTableModel) table_sp.getModel();

				// Get data of the selected row
				Object[] rowData = new Object[temp.getColumnCount()];
				for (int i = 0; i < temp.getColumnCount(); i++) {
					rowData[i] = temp.getValueAt(selected, i);
				}
				productName.setText(rowData[3].toString());
				loadCongDoanWithSanPham(rowData[2].toString());
				String url = qlsp.getUrlImage(rowData[2].toString());
				displayProductImageFromURL(url); // Giả sử cột thứ 5 chứa đường dẫn URL
				loadInfoProduct();
			}
		});
		table_sp.setModel(spmodel = new DefaultTableModel(new Object[][] {
				{ "1", "\u00C1o kho\u00E1c", "122hfjdf", "\u00C1o kho\u00E1c Kaki ch\u1ED1ng m\u1ED3 h\u00F4i",
						"Kaki s\u1ECDc", "V\u1EA3i kaki", "100" },
				{ "2", "V\u00E1y li\u1EC1n th\u00E2n", "VLT102020", "V\u00E1y li\u1EC1n thnn c\u00F3 n\u00FAt g\u00E0i",
						"\u0110\u01A1n \u0111i\u1EC7u", "L\u1EE5a", "120" }, },
				new String[] { "STT", "Lo\u1EA1i s\u1EA3n ph\u1EA9m", " M\u00E3 s\u1EA3n ph\u1EA9m",
						"T\u00EAn s\u1EA3n ph\u1EA9m", "Ki\u1EC3u d\u00E1ng", "Nguy\u00EAn Li\u1EC7u",
						"S\u1ED1 l\u01B0\u1EE3ng" }));
		scrollPane.setViewportView(table_sp);

		JPanel detail = new JPanel();
		detail.setBorder(new TitledBorder(null, "S\u1EA2N PH\u1EA8M", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 128, 128)));
		pList.add(detail);
		detail.setLayout(new BoxLayout(detail, BoxLayout.Y_AXIS));

		JPanel sptop = new JPanel();
		detail.add(sptop);
		sptop.setLayout(new BoxLayout(sptop, BoxLayout.X_AXIS));

		// URL của hình ảnh bạn muốn tải và thay đổi kích thước
		String imageUrl = "https://raw.githubusercontent.com/hongson1003/ImageProductDesktop/main/aosomi.png";
		imageUrl = null;
		ImageIcon icon = null;
		try {
			// Tải hình ảnh từ URL
			URL url = new URL(imageUrl);
			BufferedImage originalImage = ImageIO.read(url);

			// Đặt kích thước mới
			int newWidth = 140; // Chiều rộng mới
			int newHeight = 140; // Chiều cao mới

			// Thay đổi kích thước hình ảnh
			Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			// Tạo ImageIcon từ hình ảnh đã thay đổi kích thước
			icon = new ImageIcon(resizedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_4.setBorder(new EmptyBorder(10, 0, 0, 0));
		panel_4.setAlignmentX(0.0f);
		FlowLayout flowLayout_13 = (FlowLayout) panel_4.getLayout();
		flowLayout_13.setHgap(0);
		flowLayout_13.setVgap(0);
		sptop.add(panel_4);

		imageLabel = new JLabel();
		panel_4.add(imageLabel);
		imageLabel.setAlignmentY(0.0f);

		// Đặt hình ảnh vào JLabel
		imageLabel.setIcon(icon);

		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		sptop.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel title = new JPanel();
		panel_2.add(title);

		productName = new JLabel("TÊN SẢN PHẨM");
		productName.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.add(productName);

		Panel panel_3 = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel("Mã sản phẩm:");
		lblNewLabel_2.setPreferredSize(new Dimension(165, 14));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(lblNewLabel_2);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEditable(false);
		panel_3.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);

		Panel panel_5 = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setHgap(0);
		flowLayout_3.setVgap(0);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_5);

		JLabel lblNewLabel_3 = new JLabel("Loại sản phẩm");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setPreferredSize(new Dimension(165, 14));
		panel_5.add(lblNewLabel_3);

		txtLoaiSanPham = new JTextField();
		txtLoaiSanPham.setEditable(false);
		panel_5.add(txtLoaiSanPham);
		txtLoaiSanPham.setColumns(10);

		Panel panel_6 = new Panel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_6.getLayout();
		flowLayout_9.setHgap(0);
		flowLayout_9.setVgap(0);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_6);

		JLabel lblNewLabel_4 = new JLabel("Số công đoạn đã phân công");
		lblNewLabel_4.setPreferredSize(new Dimension(165, 14));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_6.add(lblNewLabel_4);

		txtSoCongDoan = new JTextField();
		txtSoCongDoan.setEditable(false);
		panel_6.add(txtSoCongDoan);
		txtSoCongDoan.setColumns(10);

		Panel spcenter = new Panel();
		detail.add(spcenter);
		spcenter.setLayout(new BoxLayout(spcenter, BoxLayout.Y_AXIS));

		Panel panel_7 = new Panel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		spcenter.add(panel_7);

		JLabel lblNewLabel_5 = new JLabel("Mã công đoạn:");
		lblNewLabel_5.setPreferredSize(new Dimension(150, 14));
		panel_7.add(lblNewLabel_5);

		txtMaCD = new JTextField();
		txtMaCD.setEditable(false);
		txtMaCD.setSize(new Dimension(6, 0));
		txtMaCD.setPreferredSize(new Dimension(20, 20));
		panel_7.add(txtMaCD);
		txtMaCD.setColumns(25);

		Panel panel_8 = new Panel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_8.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		spcenter.add(panel_8);

		JLabel lblNewLabel_6 = new JLabel("Tên công đoạn:");
		lblNewLabel_6.setPreferredSize(new Dimension(150, 14));
		panel_8.add(lblNewLabel_6);

		txtTenCD = new JTextField();
		txtTenCD.setEditable(false);
		panel_8.add(txtTenCD);
		txtTenCD.setColumns(25);

		Panel panel_10 = new Panel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_10.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		spcenter.add(panel_10);

		JLabel lbYeuCau = new JLabel("Số lượng phân công");
		lbYeuCau.setPreferredSize(new Dimension(150, 14));
		panel_10.add(lbYeuCau);

		txtSoLuongYeuCau = new JTextField();
		txtSoLuongYeuCau.setEditable(false);
		txtSoLuongYeuCau.setColumns(25);
		panel_10.add(txtSoLuongYeuCau);

		Panel panel_11 = new Panel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_11.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		spcenter.add(panel_11);

		JLabel lblNewLabel_9 = new JLabel("Số lượng hoàn thành");
		lblNewLabel_9.setPreferredSize(new Dimension(150, 14));
		panel_11.add(lblNewLabel_9);

		txtSoLuongHoanThanh = new JTextField();
		txtSoLuongHoanThanh.setEditable(false);
		panel_11.add(txtSoLuongHoanThanh);
		txtSoLuongHoanThanh.setColumns(25);

		JPanel pBottom = new JPanel();
		pBottom.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DANH S\u00C1CH C\u00D4NG \u0110O\u1EA0N S\u1EA2N PH\u1EA8M", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(64, 128, 128)));
		add(pBottom);
		pBottom.setLayout(new BoxLayout(pBottom, BoxLayout.Y_AXIS));

		JPanel t = new JPanel();
		pBottom.add(t);
		t.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_12.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		t.add(panel_12);

		JButton btnNewButton_11 = new JButton("Hiển thị danh sách công đoạn");
		btnNewButton_11.setPreferredSize(new Dimension(173, 27));
		btnNewButton_11.setBackground(new Color(64, 128, 128));
		panel_12.add(btnNewButton_11);

		JButton btnNewButton_2 = new JButton("Tìm kiếm công đoạn sản phẩm");
		btnNewButton_2.setPreferredSize(new Dimension(250, 30));
		btnNewButton_2
				.setIcon(new ImageIcon("D:\\PTUD\\Data\\PTUD_2023_Nhom15_DHKTPM17C\\target\\classes\\iconTimkiem.png"));
		btnNewButton_2.setBackground(new Color(255, 128, 0));
		panel_12.add(btnNewButton_2);

		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_13.getLayout();
		flowLayout_12.setAlignment(FlowLayout.RIGHT);
		t.add(panel_13);

		JButton btnNewButton_3 = new JButton("Danh sách bộ phận đảm nhiệm");
		btnNewButton_3.setPreferredSize(new Dimension(280, 30));
		btnNewButton_3.setBackground(new Color(0, 128, 64));
		panel_13.add(btnNewButton_3);

		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_14.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		pBottom.add(panel_14);

		JLabel lblNewLabel_10 = new JLabel("Tổng: ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_14.add(lblNewLabel_10);

		txtCount = new JTextField();
		txtCount.setEditable(false);
		panel_14.add(txtCount);
		txtCount.setColumns(4);

		JLabel lblNewLabel_11 = new JLabel("công đoạn");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_14.add(lblNewLabel_11);

		JPanel main = new JPanel();
		pBottom.add(main);

		JScrollPane scrollPane_1 = new JScrollPane();
		main.add(scrollPane_1);

		cdtable = new JTable();
		cdtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadInfoCongDoan();
			}
		});
		cdtable.setPreferredScrollableViewportSize(new Dimension(1300, 400));
		cdtable.setModel(cdmodel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Lo\u1EA1i s\u1EA3n ph\u1EA9m", "M\u00E3 s\u1EA3n ph\u1EA9m",
						"T\u00EAn s\u1EA3n ph\u1EA9m", "Ki\u1EC3u d\u00E1ng", "M\u00E3 c\u00F4ng \u0111o\u1EA1n",
						"T\u00EAn c\u00F4ng \u0111o\u1EA1n", "M\u1EE9c \u01B0u ti\u00EAn",
						"M\u1EE9c y\u00EAu c\u1EA7u k\u1EF9 thu\u1EADt", "\u0110\u01A1n gi\u00E1",
						"S\u1ED1 l\u01B0\u1EE3ng \u0111\u00E3 ph\u00E2n c\u00F4ng" }));
		cdtable.getColumnModel().getColumn(9).setPreferredWidth(44);
		scrollPane_1.setViewportView(cdtable);
		loadSanPhams();
	}

	private void loadSanPhams() {
		qlsp = new SanPhamDAO();
		List<SanPham> list = qlsp.getSanPhamWithAtribute();

		spmodel.setRowCount(0); // Xóa dữ liệu hiện tại của spmodel
		int index = 1; // Biến đếm số thứ tự

		for (SanPham sp : list) {
			// Lấy tên nguyên liệu từ hàm getTenNguyenLieu
			String tenNguyenLieu = qlnl.getTenNguyenLieu(sp.getSanPhamID());

			Object[] row = { index++, // Số thứ tự
					sp.getLoaiSanPham(), sp.getSanPhamID(), sp.getTenSanPham(), sp.getKieuDang(), tenNguyenLieu,
					sp.getSoLuong() };

			spmodel.addRow(row);
		}

		// Thông báo cho spmodel rằng dữ liệu đã thay đổi
		spmodel.fireTableDataChanged();
	}

	private void loadCongDoanWithSanPham(String maSP) {
		qlcd = new CongDoanDAO();
		List<CongDoan> list = qlcd.layDSCongDoanTheoMaSP(maSP);
		int res = 1;
		cdmodel.setRowCount(0);
		int count = 0;
		for (CongDoan congDoan : list) {
			SanPham sp = qlsp.laySanPhamTheoID(maSP);
			Object[] row = { res++, sp.getLoaiSanPham(), sp.getSanPhamID(), sp.getTenSanPham(), sp.getKieuDang(),
					congDoan.getCongDoanID(), congDoan.getTenCongDoan(), congDoan.getMucUuTien(),
					congDoan.getMucYeuCauKyThuat(), congDoan.getDonGia(),
					qlcd.getSoLuongDaPhanCongBP(congDoan.getCongDoanID()) };

			cdmodel.addRow(row);
			count++;
		}
		txtCount.setText(count + "");

	}

	private void displayProductImageFromURL(String imageUrl) {
		ImageIcon icon = null;
		try {
			// Tải hình ảnh từ URL
			URL url = new URL(imageUrl);
			BufferedImage originalImage = ImageIO.read(url);
			if (originalImage == null) {
				originalImage = ImageIO.read(
						new URL("https://raw.githubusercontent.com/hongson1003/ImageProductDesktop/main/aosomi.png"));
			}
			// Đặt kích thước mới
			int newWidth = 140; // Chiều rộng mới
			int newHeight = 140; // Chiều cao mới

			// Thay đổi kích thước hình ảnh
			Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			// Tạo ImageIcon từ hình ảnh đã thay đổi kích thước
			icon = new ImageIcon(resizedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Dùng JLabel làm ví dụ
		imageLabel.setIcon(icon);
	}

	private ImageIcon loadImageFromUrl(String imageUrl) {
		ImageIcon icon = null;
		try {
			URL url = new URL(imageUrl);
			Image image = ImageIO.read(url);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	private void loadInfoProduct() {
		int selected = table_sp.getSelectedRow();
		DefaultTableModel temp = (DefaultTableModel) table_sp.getModel();

		// Get data of the selected row
		Object[] rowData = new Object[temp.getColumnCount()];
		for (int i = 0; i < temp.getColumnCount(); i++) {
			rowData[i] = temp.getValueAt(selected, i);
		}
		txtMaSanPham.setText(rowData[2].toString());
		txtLoaiSanPham.setText(rowData[1].toString());
		txtSoCongDoan.setText(cdtable.getRowCount() + "");
	}

	private void loadInfoCongDoan() {
		int selected = cdtable.getSelectedRow();
		DefaultTableModel temp = (DefaultTableModel) cdtable.getModel();

		// Get data of the selected row
		Object[] rowData = new Object[temp.getColumnCount()];
		for (int i = 0; i < temp.getColumnCount(); i++) {
			rowData[i] = temp.getValueAt(selected, i);
		}
		txtMaCD.setText(rowData[5].toString());
		txtTenCD.setText(rowData[6].toString());
		txtSoLuongYeuCau.setText(rowData[10].toString());
	}

}