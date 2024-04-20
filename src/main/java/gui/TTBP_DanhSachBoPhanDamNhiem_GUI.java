package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TTBP_DanhSachBoPhanDamNhiem_GUI extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;

	public TTBP_DanhSachBoPhanDamNhiem_GUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("DANH SÁCH PHÂN CÔNG CÔNG NHÂN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panel.add(lblNewLabel);

		JPanel container = new JPanel();
		add(container, BorderLayout.CENTER);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		container.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Bộ phận");
		lblNewLabel_1.setPreferredSize(new Dimension(80, 14));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_2.add(lblNewLabel_1);

		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(14);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		container.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);

		JLabel lblNewLabel_2 = new JLabel("Mã sản phẩm");
		lblNewLabel_2.setPreferredSize(new Dimension(80, 14));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_4.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setColumns(14);
		panel_4.add(textField_1);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel lblNewLabel_3 = new JLabel("Mã công đoạn");
		lblNewLabel_3.setPreferredSize(new Dimension(100, 14));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_5.add(lblNewLabel_3);

		textField_2 = new JTextField();
		panel_5.add(textField_2);
		textField_2.setColumns(14);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);

		JLabel lblNewLabel_4 = new JLabel("Số lượng cần làm");
		lblNewLabel_4.setPreferredSize(new Dimension(110, 14));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_6.add(lblNewLabel_4);

		textField_3 = new JTextField();
		panel_6.add(textField_3);
		textField_3.setColumns(14);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		container.add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);

		JLabel lblNewLabel_5 = new JLabel("Tên sản phẩm");
		lblNewLabel_5.setPreferredSize(new Dimension(80, 14));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_8.add(lblNewLabel_5);

		textField_4 = new JTextField();
		panel_8.add(textField_4);
		textField_4.setColumns(14);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);

		JLabel lblNewLabel_6 = new JLabel("Tên công đoạn");
		lblNewLabel_6.setPreferredSize(new Dimension(100, 14));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_9.add(lblNewLabel_6);

		textField_5 = new JTextField();
		panel_9.add(textField_5);
		textField_5.setColumns(14);

		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10);

		JLabel lblNewLabel_7 = new JLabel("Ngày phân công");
		lblNewLabel_7.setPreferredSize(new Dimension(110, 14));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_10.add(lblNewLabel_7);

		textField_6 = new JTextField();
		panel_10.add(textField_6);
		textField_6.setColumns(14);

		JPanel panel_11 = new JPanel();
		add(panel_11, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_11.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 ph\u00E2n c\u00F4ng", "M\u00E3 s\u1EA3n ph\u1EA9m",
						"T\u00EAn s\u1EA3n ph\u1EA9m", "M\u00E3 c\u00F4ng \u0111o\u1EA1n",
						"M\u00E3 c\u00F4ng nh\u00E2n", "H\u1ECD t\u00EAn", " Ng\u00E0y ph\u00E2n c\u00F4ng",
						"S\u1ED1 l\u01B0\u1EE3ng c\u1EA7n l\u00E0m" }));
		table.setPreferredScrollableViewportSize(new Dimension(750, 160));
		scrollPane.setViewportView(table);
	}
}
