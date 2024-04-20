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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import dao.BangLuongCongNhanDAO;
import dao.BangLuongNhanVienDAO;
import dao.BoPhanDAO;
import dao.CongDoanDAO;
import entities.BangLuongCongNhan;
import entities.BangPhanCongCongNhan;
import entities.BoPhan;
import entities.ChiTietPhuCapKhenThuongKhauTruCN;
import entities.CongNhan;

public class ThongKe extends JPanel {
	private Map<String, String> boPhanMap;

	private JTable table;
	private DefaultComboBoxModel modelBoPhan;
	private BoPhanDAO qlbp;
	public static DefaultTableModel model;
	private CongDoanDAO qlcd;
	private DefaultTableModel dsmodel;
	private JMonthChooser chonThang;
	private JYearChooser chonNam;
	private JComboBox chonDoiTuong;
	private DefaultPieDataset pieDataSet;
	private JFreeChart pieChart;
	private PiePlot piePlot;
	private ChartPanel chartPanel;
	private JPanel pBieuDo;
	private JPanel pieChartPanel;
	/**
	 * Create the panel.
	 */
	public ThongKe() {
		setLayout(new BorderLayout(0, 0));
	
		JPanel pTop = new JPanel();

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

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
	

		pTop.add(panel_2);

		JLabel date = new JLabel("");
		date.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		JLabel lblNewLabel_1 = new JLabel("Chọn đối tượng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_2.add(lblNewLabel_1);
		
		chonDoiTuong = new JComboBox();
		chonDoiTuong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		chonDoiTuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(chonDoiTuong.getSelectedIndex() == -1) {
//					chonNam.setEnabled(false);
//					chonThang.setEnabled(false);
//				}
//				else {
//					chonNam.setEnabled(true);
//					chonThang.setEnabled(true);
//				}
			}
		});
		chonDoiTuong.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Công nhân"}));
		chonDoiTuong.setSelectedIndex(0);
		panel_2.add(chonDoiTuong);
		
		JPanel panel_2_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2_1.getLayout();
		flowLayout_3.setHgap(15);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pTop.add(panel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Thống kê theo");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_2_1.add(lblNewLabel_1_1);
		
		chonThang = new JMonthChooser();
	
		chonThang.setLocale(new Locale("vi", "VN"));
		panel_2_1.add(chonThang);
		
		chonNam = new JYearChooser();
		chonNam.setLocale(new Locale("vi", "VN"));
		panel_2_1.add(chonNam);
		
		JButton btnNewButton_1_1 = new JButton("Xem");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnNewButton_1_1)) {
					if(chonDoiTuong.getSelectedItem().toString().equals("Nhân viên")) {
						int thang = chonThang.getMonth() + 1;
						int nam = chonNam.getYear();
						try {
							addDataTableNhanVien(thang, nam);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						int thang = chonThang.getMonth() + 1;
						int nam = chonNam.getYear();
						try {
							addDataTableCongNhan(thang, nam);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		panel_2_1.add(btnNewButton_1_1);

	

		pBieuDo = new JPanel();
		pBieuDo.setBackground(Color.LIGHT_GRAY); // Chọn màu xám xám
		add(pBieuDo, BorderLayout.CENTER);
		

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

		JPanel panel_5 = new JPanel();
		pChiTiet.add(panel_5);

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(650, 400));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "Th\u00E1ng l\u01B0\u01A1ng", "S\u1ED1 l\u01B0\u1EE3n", "Kho\u1EA3ng l\u01B0\u01A1ng", "Ph\u1EA7n tr\u0103m"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Integer.class, Object.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		scrollPane.setViewportView(table);
		
		pieChartPanel = new JPanel();
		pBieuDo.add(pieChartPanel);
//		ChartPanel chartPanel_1 = new ChartPanel(chart);
//		pBieuDo.add(chartPanel_1);
//		// Thiết lập giá trị trục y
//		CategoryPlot plot = (CategoryPlot) chart.getPlot();
//		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//		yAxis.setAutoRange(false); // Tắt chế độ tự động điều chỉnh giá trị trục y
//		yAxis.setTickUnit(new NumberTickUnit(15)); // Đặt các giá trị trục y
//		yAxis.setRange(0, 105);
//		// set hanh dong cho button
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				FileExport.exportTableAndChartToPDF(table, table, chart, monthChooser.getMonth() + 1);
//			}
//		});
	}
	
	
	public Map<String, Integer> thongKeNhanVienTheoThangNam( int thang, int nam) throws SQLException {
		double thue;
		String khoang1 = "Đến 5 triệu";
		String khoang2 = "5 - 10 triệu"; 
		String khoang3 = "10 - 18 triệu";
		String khoang4 = "18 - 32 triệu";
		String khoang5 = "32 - 52 triệu";
		String khoang6 = "52 - 80 triệu";		
		String khoang7 = "Trên 80 triệu";
		int soLuong1 = 0;
		int soLuong2 = 0;
		int soLuong3 = 0;
		int soLuong4 = 0;
		int soLuong5 = 0;
		int soLuong6 = 0;
		int soLuong7 = 0;
		
		BangLuongNhanVienDAO bangLuongNhanVienDAO = new BangLuongNhanVienDAO();
		ResultSet rs = bangLuongNhanVienDAO.thongKeLuongTheoThangNam(thang, nam);
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put(khoang1, soLuong1);
		map.put(khoang2, soLuong2);
		map.put(khoang3, soLuong3);
		map.put(khoang4, soLuong4);
		map.put(khoang5, soLuong5);
		map.put(khoang6, soLuong6);
		map.put(khoang7, soLuong7);
		while(rs.next()) {
			if(rs.getDouble(2) < 5000000) {
				if(map.containsKey(khoang1)) {
					map.put(khoang1, map.get(khoang1) + 1);
				}
			}			
			else if(rs.getDouble(2) >= 5000000 && rs.getDouble(2) <= 10000000) {
				if(map.containsKey(khoang2)) {
					map.put(khoang2, map.get(khoang2) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 10000000 && rs.getDouble(2) <= 18000000) {
				if(map.containsKey(khoang3)) {
					map.put(khoang3, map.get(khoang3) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 18000000 && rs.getDouble(2) <= 32000000) {
				if(map.containsKey(khoang4)) {
					map.put(khoang4, map.get(khoang4) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 32000000 && rs.getDouble(2) <= 52000000) {
				if(map.containsKey(khoang5)) {
					map.put(khoang5, map.get(khoang5) + 1);
				}
				
			}		
			else if(rs.getDouble(2) >= 52000000 && rs.getDouble(2) <= 80000000) {
				if(map.containsKey(khoang6)) {
					map.put(khoang6, map.get(khoang6) + 1);
				}
			}			
			else {
				if(map.containsKey(khoang7)) {
					map.put(khoang7, map.get(khoang7) + 1);
				}
				
			}
		}
			
		return map;
	}
	
	private void addDataTableNhanVien(int thang, int nam) throws SQLException {
		Map<String, Integer> map = thongKeNhanVienTheoThangNam(thang, nam);
		pieDataSet = new DefaultPieDataset();
		int sum = map.values().stream().reduce(0, Integer::sum);
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		int stt = 1;
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			pieDataSet.setValue(entry.getKey(), (double) Math.round((entry.getValue() * 100 / sum) * 100) / 100);
			model.addRow(new Object[] {
				stt,
				thang + " - " + nam,
				entry.getValue(),
				entry.getKey(),
				(double) Math.round((entry.getValue() * 100 / sum) * 100) / 100
			});
			stt++;
		}
		
		pieChart = ChartFactory.createPieChart("Thống kê lương nhân viên tháng "+ thang + " - " + nam, pieDataSet, true, true, true);
		piePlot = (PiePlot) pieChart.getPlot();
		chartPanel = new ChartPanel(pieChart);
		pieChartPanel.removeAll();
		pieChartPanel.add(chartPanel);
		chartPanel.revalidate();
	
	}
	
	
	public Map<String, Integer> thongKeCongNhanTheoThangNam( int thang, int nam) throws SQLException {
		double thue;
		String khoang1 = "Đến 5 triệu";
		String khoang2 = "5 - 10 triệu"; 
		String khoang3 = "10 - 18 triệu";
		String khoang4 = "18 - 32 triệu";
		String khoang5 = "32 - 52 triệu";
		String khoang6 = "52 - 80 triệu";		
		String khoang7 = "Trên 80 triệu";
		int soLuong1 = 0;
		int soLuong2 = 0;
		int soLuong3 = 0;
		int soLuong4 = 0;
		int soLuong5 = 0;
		int soLuong6 = 0;
		int soLuong7 = 0;
		
		BangLuongCongNhanDAO bangLuongCongNhanDAO = new BangLuongCongNhanDAO();
		ResultSet rs = bangLuongCongNhanDAO.thongKeLuongTheoThangNam(thang, nam);
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put(khoang1, soLuong1);
		map.put(khoang2, soLuong2);
		map.put(khoang3, soLuong3);
		map.put(khoang4, soLuong4);
		map.put(khoang5, soLuong5);
		map.put(khoang6, soLuong6);
		map.put(khoang7, soLuong7);
		while(rs.next()) {
			if(rs.getDouble(2) < 5000000) {
				if(map.containsKey(khoang1)) {
					map.put(khoang1, map.get(khoang1) + 1);
				}
			}			
			else if(rs.getDouble(2) >= 5000000 && rs.getDouble(2) <= 10000000) {
				if(map.containsKey(khoang2)) {
					map.put(khoang2, map.get(khoang2) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 10000000 && rs.getDouble(2) <= 18000000) {
				if(map.containsKey(khoang3)) {
					map.put(khoang3, map.get(khoang3) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 18000000 && rs.getDouble(2) <= 32000000) {
				if(map.containsKey(khoang4)) {
					map.put(khoang4, map.get(khoang4) + 1);
				}
				
			}			
			else if(rs.getDouble(2) >= 32000000 && rs.getDouble(2) <= 52000000) {
				if(map.containsKey(khoang5)) {
					map.put(khoang5, map.get(khoang5) + 1);
				}
				
			}		
			else if(rs.getDouble(2) >= 52000000 && rs.getDouble(2) <= 80000000) {
				if(map.containsKey(khoang6)) {
					map.put(khoang6, map.get(khoang6) + 1);
				}
			}			
			else {
				if(map.containsKey(khoang7)) {
					map.put(khoang7, map.get(khoang7) + 1);
				}
				
			}
		}
			
		return map;
	}
	
	private void addDataTableCongNhan(int thang, int nam) throws SQLException {
		Map<String, Integer> map = thongKeCongNhanTheoThangNam(thang, nam);
		pieDataSet = new DefaultPieDataset();
		int sum = map.values().stream().reduce(0, Integer::sum);
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		int stt = 1;
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			pieDataSet.setValue(entry.getKey(), (double) Math.round((entry.getValue() * 100 / sum) * 100) / 100);
			model.addRow(new Object[] {
				stt,
				thang + " - " + nam,
				entry.getValue(),
				entry.getKey(),
				(double) Math.round((entry.getValue() * 100 / sum) * 100) / 100
			});
			stt++;
		}
		
		pieChart = ChartFactory.createPieChart("Thống kê lương công nhân tháng "+ thang + " - " + nam, pieDataSet, true, true, true);
		piePlot = (PiePlot) pieChart.getPlot();
		chartPanel = new ChartPanel(pieChart);
		pieChartPanel.removeAll();
		pieChartPanel.add(chartPanel);
		chartPanel.revalidate();
	
	}
		
}



