package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import dao.BangPhanCongCongNhanDAO;
import dao.BoPhanDAO;
import dao.CongDoanDAO;
import entities.BangPhanCongCongNhan;
import entities.BoPhan;
import file.FileExport;

public class TTBP_ThongKe extends JPanel {
	private Map<String, String> boPhanMap;
	private DefaultCategoryDataset dataset;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JTable table;
	private JComboBox danhSachBoPhan;
	private DefaultComboBoxModel modelBoPhan;
	private BoPhanDAO qlbp;
	private BangPhanCongCongNhanDAO qlpccn;
	private CongDoanDAO qlcd;
	private DefaultTableModel dsmodel;
	private JLabel txtSoCD;
	private ChartPanel chartPanel_1;
	private JFreeChart chart;

	/**
	 * Create the panel.
	 */
	public TTBP_ThongKe() {
		setLayout(new BorderLayout(0, 0));
		boPhanMap = new HashMap<>();
		qlbp = new BoPhanDAO();
		qlcd = new CongDoanDAO();
		JPanel pTop = new JPanel();
		qlpccn = new BangPhanCongCongNhanDAO();
		pTop.setBorder(new EmptyBorder(0, 20, 0, 0));
		add(pTop, BorderLayout.NORTH);
		pTop.setLayout(new BoxLayout(pTop, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new EmptyBorder(5, 0, 0, 0));
		pTop.add(panel);

		JLabel title = new JLabel("Báo cáo thống kê");
		title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.add(title);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pTop.add(panel_1);

		JButton btnNewButton = new JButton();
		btnNewButton.setPreferredSize(new Dimension(30, 30));
		ImageIcon icon = new ImageIcon(getClass().getResource("/download.png"));
		btnNewButton.setIcon(icon);

		panel_1.add(btnNewButton);

		JLabel xuatBaoCao = new JLabel("Xuất báo cáo");
		xuatBaoCao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(xuatBaoCao);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		// login

		pTop.add(panel_2);

		JLabel date = new JLabel("");
		date.setFont(new Font("Tahoma", Font.BOLD, 12));

		// Tạo JMonthChooser và đặt Locale là tiếng Việt
		monthChooser = new JMonthChooser();
		monthChooser.setLocale(new Locale("vi", "VN")); // Đặt Locale tiếng Việt

		// Tạo JYearChooser và đặt Locale là tiếng Việt
		yearChooser = new JYearChooser();
		yearChooser.setLocale(new Locale("vi", "VN")); // Đặt Locale tiếng Việt

		// Thêm JMonthChooser và JYearChooser vào panel_2
		panel_2.add(monthChooser);
		panel_2.add(yearChooser);

		danhSachBoPhan = new JComboBox();
		danhSachBoPhan.setModel(modelBoPhan = new DefaultComboBoxModel(new String[] {}));
		danhSachBoPhan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Lấy giá trị được chọn từ combobox
					String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
					// Lấy giá trị boPhanID từ Map
					String boPhanID = boPhanMap.get(selectedTenBoPhan);
					// Xử lý và hiển thị thông tin boPhan
					hienThiSoCongDoanDaLam();
				}
			}
		});
		hienThiDanhSachBoPhan();
		panel_2.add(danhSachBoPhan);

		JButton btnNewButton_1 = new JButton("Xem");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiChiTiet();
			}
		});
		panel_2.add(btnNewButton_1);

		// Đặt sự kiện khi thay đổi tháng và năm
		monthChooser.addPropertyChangeListener("month", evt -> {
			int month = monthChooser.getMonth();
			int year = yearChooser.getYear();
			date.setText("Tháng được chọn: " + (month + 1) + "/" + year);
			hienThiChiTiet();
			updateChart();
		});
		// Lấy tháng từ JMonthChooser
		int selectedMonth = monthChooser.getMonth() + 1; // JMonthChooser trả về tháng từ 0-11, nên cộng thêm 1 để có
															// giá trị từ 1-12

		// Lấy năm từ JYearChooser
		int selectedYear = yearChooser.getYear();
		String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
		// Lấy giá trị boPhanID từ Map
		String boPhanID = boPhanMap.get(selectedTenBoPhan);
		// Lấy tháng và năm từ JMonthChooser và JYearChooser
		// vẽ biểu đồ nè
		dataset = createDataset(boPhanID, selectedMonth, selectedYear);

		JPanel pBieuDo = new JPanel();
		pBieuDo.setBackground(Color.LIGHT_GRAY); // Chọn màu xám xám
		add(pBieuDo, BorderLayout.CENTER);
		// Tạo biểu đồ và thêm vào panel
		chart = createChart();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		pBieuDo.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pChiTiet = new JPanel();
		pBieuDo.add(pChiTiet);
		pChiTiet.setLayout(new BoxLayout(pChiTiet, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pChiTiet.add(panel_4);

		JLabel lblNewLabel = new JLabel("Số công đoạn đã làm: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_4.add(lblNewLabel);

		txtSoCD = new JLabel("");
		txtSoCD.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(txtSoCD);
		hienThiSoCongDoanDaLam();

		JPanel panel_5 = new JPanel();
		pChiTiet.add(panel_5);

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(650, 400));
		table.setModel(dsmodel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "T\u00EAn C\u00F4ng \u0110o\u1EA1n", "T\u00EAn c\u00F4ng nh\u00E2n",
						"S\u1ED1 l\u01B0\u1EE3ng ho\u00E0n th\u00E0nh" }));
		scrollPane.setViewportView(table);
		chartPanel_1 = new ChartPanel(chart);
		pBieuDo.add(chartPanel_1);

		// Thêm sự kiện thay đổi tháng và năm để cập nhật biểu đồ
		monthChooser.addPropertyChangeListener("month", evt -> updateChart());
		yearChooser.addPropertyChangeListener("year", evt -> updateChart());
		// set hanh dong cho button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileExport.exportTableAndChartToPDF(table, table, chart, monthChooser.getMonth() + 1);
			}
		});
	}

	private DefaultCategoryDataset createDataset(String maBP, int thang, int nam) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Thêm dữ liệu cho các tháng từ 1 đến 12 (hoặc các tháng bạn muốn)
		for (int month = thang - 3; month <= thang; month++) {
			ArrayList<Integer> ls = qlpccn.getSoLuongThongKe(maBP, month, nam);
			int soLuongPhanCong = ls.get(0);
			int soLuongHoanThanh = ls.get(1);
			dataset.addValue(soLuongPhanCong, "Số lượng phân công", "Tháng " + month);
			dataset.addValue(soLuongHoanThanh, "Số lượng hoàn thành", "Tháng " + month);
		}

		return dataset;
	}

	private JFreeChart createChart() {
		JFreeChart chart = ChartFactory.createBarChart("Thống kê tình hình phân công trong các tháng vừa qua",
				// // đồ
				"Tháng", // Nhãn trục x
				"Số lượng", // Nhãn trục y
				dataset // Dữ liệu
		);
		return chart;
	}

	private void updateChart() {
		dataset.clear(); // Xóa dữ liệu cũ
		String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
		String boPhanID = boPhanMap.get(selectedTenBoPhan);
		int selectedMonth = monthChooser.getMonth() + 1;
		int selectedYear = yearChooser.getYear();
		dataset = createDataset(boPhanID, selectedMonth, selectedYear); // Tạo dataset mới
		JFreeChart newChart = createChart(); // Tạo biểu đồ mới
		chart = newChart;
		chartPanel_1.setChart(newChart); // Cập nhật biểu đồ trên ChartPanel
		chartPanel_1.repaint(); // Vẽ lại biểu đồ
	}

	private void hienThiDanhSachBoPhan() {
		// Gọi hàm lấy danh sách bộ phận từ DAO
		ArrayList<BoPhan> dsBoPhan = qlbp.getAllBoPhan1();

		// Tạo một Map để lưu trữ ánh xạ giữa tên và boPhanID
		// Tạo danh sách tên bộ phận để đưa vào JComboBox
		ArrayList<String> tenBoPhanList = new ArrayList<>();

		for (BoPhan boPhan : dsBoPhan) {
			String boPhanID = boPhan.getBoPhanID();
			String tenBoPhan = boPhan.getTenBoPhan();

			boPhanMap.put(tenBoPhan, boPhanID);
			tenBoPhanList.add(tenBoPhan);
		}

		// Thiết lập model cho JComboBox sử dụng danh sách tên bộ phận
		danhSachBoPhan.setModel(new DefaultComboBoxModel<>(tenBoPhanList.toArray(new String[0])));

		// Thiết lập sự kiện cho JComboBox để lấy giá trị khi thay đổi
		danhSachBoPhan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					dsmodel.setRowCount(0);
					updateChart();
				}
			}
		});
	}

	private void hienThiChiTiet() {
		String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
		// Lấy giá trị boPhanID từ Map
		String boPhanID = boPhanMap.get(selectedTenBoPhan);
		// Lấy tháng và năm từ JMonthChooser và JYearChooser
		int month = monthChooser.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
		int year = yearChooser.getYear();
		ArrayList<BangPhanCongCongNhan> list = qlpccn.getToanBoPhanCongTrongThang(boPhanID, month, year);
		int i = 1;
		dsmodel.setRowCount(0);
		for (BangPhanCongCongNhan pccn : list) {
			String stt = Integer.toString(i);
			String tenCongDoan = pccn.getBangPhanCongBoPhan().getCongDoan().getTenCongDoan();
			String tenCongNhan = pccn.getCongNhan().getHoTen();
			String soLuongHoanThanh = Integer.toString(pccn.getSoLuongHoanThanh());
			// Thêm hàng dữ liệu vào DefaultTableModel
			dsmodel.addRow(new Object[] { stt, tenCongDoan, tenCongNhan, soLuongHoanThanh });
			i++;
		}
	}

	private void hienThiSoCongDoanDaLam() {
		String selectedTenBoPhan = (String) danhSachBoPhan.getSelectedItem();
		String boPhanID = boPhanMap.get(selectedTenBoPhan);
		int month = monthChooser.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần +1 để lấy tháng thực tế
		int year = yearChooser.getYear();
		int soCD = qlpccn.soCongDoanDaLam(boPhanID, month, year);
		txtSoCD.setText(soCD + "");
	}

}
