package file;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.jfree.chart.JFreeChart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FileExport {
	private static Font font;
	private static Font fontBold;

	public static void exportTableAndChartToPDF(Component parentComponent, JTable table, JFreeChart chart, int month) {
		Document document = new Document();

		try {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showSaveDialog(parentComponent);
			File file;
			if (result == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				if (!file.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
					file = new File(file.getAbsolutePath() + ".pdf");
				}
			} else {
				return;
			}

			if (table.getRowCount() == 0 && chart == null) {
				JOptionPane.showMessageDialog(parentComponent, "Không có dữ liệu để xuất ra PDF.");
				return;
			}

			InputStream is = FileExport.class.getResourceAsStream("/fonts/ArialUnicodeMS.ttf");
			byte[] fontData = new byte[is.available()];
			is.read(fontData);
			BaseFont bf = BaseFont.createFont("ArialUnicodeMS.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true,
					fontData, null);
			font = new Font(bf, 12);
			fontBold = new Font(bf, 12, Font.BOLD);
			is.close();

			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			// Add title row
			// Add title as a separate paragraph
			com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph(
					"Danh sách công nhân ứng với số lượng sản phẩm làm được trong tháng " + month, fontBold);
			title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			title.setSpacingAfter(10); // Cách dòng sau tiêu đề

			document.add(title);
			// Add table headers
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i), font)));
			}

			// Add table data
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					PdfPCell cell = new PdfPCell(new Phrase(table.getValueAt(i, j).toString(), font));
					pdfTable.addCell(cell);
				}
			}

			document.add(pdfTable);

			// Add chart as an image to the PDF
			if (chart != null) {
				com.itextpdf.text.Image chartImage = com.itextpdf.text.Image
						.getInstance(chart.createBufferedImage(500, 300), null);
				document.add(chartImage);
			}

			document.close();

			JOptionPane.showMessageDialog(parentComponent, "Xuất bảng và biểu đồ ra PDF thành công!");
		} catch (IOException | DocumentException ex) {
			JOptionPane.showMessageDialog(parentComponent, "Lỗi khi xuất bảng và biểu đồ ra PDF: " + ex.getMessage());
		}
	}
}
