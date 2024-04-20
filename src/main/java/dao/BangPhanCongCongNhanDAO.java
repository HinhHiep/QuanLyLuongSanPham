package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import db.DBConnection;
import entities.BangPhanCongBoPhan;
import entities.BangPhanCongCongNhan;
import entities.CongDoan;
import entities.CongNhan;

public class BangPhanCongCongNhanDAO {
	private Connection con;
	private CongNhanDAO congNhanDAO=new CongNhanDAO();
	private BangPhanCongBoPhanDAO bangPhanCongBoPhanDAO=new BangPhanCongBoPhanDAO();
//------------------------------------------------------------------
	public BangPhanCongCongNhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}
//--------------------------------------------------------------------------------------------------
	public ArrayList<BangPhanCongCongNhan> getPhanCongByConditions(String congDoanID, String boPhanID) {
		ArrayList<BangPhanCongCongNhan> phanCongs = new ArrayList<>();
		try {
			String sql="select* from BangPhanCongCongNhan as pccn join BangPhanCongBoPhan as pcbp on pccn.PhanCongBoPhanID=pcbp.PhanCongID where BoPhanID=? and CongDoanID=?";
//			String sql = "SELECT pccn.PhanCongID, sp.SanPhamID, sp.TenSanPham, cd.CongDoanID, cd.TenCongDoan, cn.CongNhanID, n.HoTen, pccn.Ngay, pccn.SoLuongPhanCong "
//					+ "FROM CongNhan AS cn " + "INNER JOIN Nguoi AS n ON n.NguoiID = cn.CongNhanID "
//					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.CongNhanID = cn.CongNhanID "
//					+ "INNER JOIN BoPhan AS bp ON bp.BoPhanID = pccn.BoPhanID "
//					+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.BoPhanID = bp.BoPhanID "
//					+ "INNER JOIN CongDoan AS cd ON cd.CongDoanID = pcbp.CongDoanID "
//					+ "INNER JOIN SanPham AS sp ON sp.SanPhamID = cd.SanPhamID "
//					+ "where  bp.BoPhanID = ? and pccn.PhanCongBPID = pcbp.PhanCongID and pcbp.CongDoanID = ?";
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setString(1, boPhanID);
			statement.setString(2, congDoanID);
//			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
//				preparedStatement.setString(1, boPhanID);
//				preparedStatement.setString(2, congDoanID);
//				try (ResultSet resultSet = preparedStatement.executeQuery()) {
			ResultSet resultSet=statement.executeQuery();
					while (resultSet.next()) {
						// Xử lý dữ liệu từ ResultSet và tạo đối tượng PhanCong
						String phanCongID=resultSet.getString("PhanCongID");
						String congNhanID=resultSet.getString("CongNhanID");
						CongNhan congNhan=congNhanDAO.getCongNhanTheoID(congNhanID);
						String phanCongBoPhanID=resultSet.getString("PhanCongBoPhanID");
						BangPhanCongBoPhan bangPhanCongBoPhan=bangPhanCongBoPhanDAO.layBangPhanCongTheoID(phanCongBoPhanID);
						int soLuongPhanCong=resultSet.getInt("SoLuongPhanCong");
						int soLuongHoanThanh=resultSet.getInt("soLuongHoanThanh");
						BangPhanCongCongNhan phanCong=new BangPhanCongCongNhan(phanCongID, congNhan, bangPhanCongBoPhan, soLuongPhanCong, soLuongHoanThanh);
//						BangPhanCongCongNhan phanCong = new PhanCong(resultSet.getString("PhanCongID"),
//								resultSet.getString("SanPhamID"), resultSet.getString("TenSanPham"),
//								resultSet.getString("CongDoanID"), resultSet.getString("TenCongDoan"),
//								resultSet.getString("CongNhanID"), resultSet.getString("HoTen"),
//								resultSet.getDate("Ngay"), resultSet.getInt("SoLuongPhanCong"));
						phanCongs.add(phanCong);
					}
				
			
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý ngoại lệ SQL
		}
		return phanCongs;
	}

	public int getSoLuongDaPhanCong(String congDoanID, String boPhanID) {
		int tong = 0;
		try {
			String sql="  select* from BangPhanCongCongNhan as pccn join BangPhanCongBoPhan as pcbp on pccn.PhanCongBoPhanID=pcbp.PhanCongID where BoPhanID=? and CongDoanID=? ";
//			String sql = "select sum(pccn.SoLuongPhanCong) as SoLuongDaPhanCong\n" + "from CongDoan as cd\n"
//					+ "inner join BangPhanCongBoPhan as pcbp on pcbp.CongDoanID = cd.CongDoanID\n"
//					+ "inner join BoPhan as bp on bp.BoPhanID = pcbp.BoPhanID\n"
//					+ "inner join BangPhanCongCongNhan as pccn on pccn.BoPhanID = bp.BoPhanID\n"
//					+ "where cd.CongDoanID like ? and pccn.PhanCongBPID = pcbp.PhanCongID and bp.BoPhanID = ?";

			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(2, congDoanID);
			preparedStatement.setString(1, boPhanID);
//			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
//				preparedStatement.setString(1, congDoanID);
//				preparedStatement.setString(2, boPhanID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
						// Lấy giá trị tổng số lượng đã phân công từ cột SoLuongDaPhanCong
					tong = resultSet.getInt("SoLuongDaPhanCong");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý ngoại lệ SQL
		}
		return tong;
	}

	public void xoaPhanCong(String phanCongID) {
		try {
			String sql = "DELETE FROM BangPhanCongCongNhan WHERE PhanCongID = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, phanCongID);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý ngoại lệ SQL
		}
	}
	public void themPhanCongCongNhan(BangPhanCongCongNhan pccn) {
		try {
			String sql="insert into BangPhanCongCongNhan values (?,?,?,?,?)";
			PreparedStatement statement =con.prepareStatement(sql);
			statement.setString(1, pccn.getPhanCongID());
			statement.setString(2, pccn.getCongNhan().getNguoiID());
			statement.setString(3,pccn.getBangPhanCongBoPhan().getPhanCongID());
			statement.setInt(4, pccn.getSoLuongPhanCong());
			statement.setInt(5, pccn.getSoLuongHoanThanh());
//			String sql = "INSERT INTO BangPhanCongCongNhan (PhanCongID, BoPhanID, CongNhanID, PhanCongBPID, SoLuongPhanCong, SoLuongHoanThanh, Ngay, CaLamViec) "
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

//			try (PreparedStatement statement = con.prepareStatement(sql)) {
//				statement.setString(1, pccn.getPhanCongID());
//				statement.setString(2, pccn.getBoPhanID());
//				statement.setString(3, pccn.getCongNhanID());
//				statement.setString(4, pccn.getPhanCongBPID());
//				statement.setInt(5, pccn.getSoLuongPhanCong());
//				statement.setString(6, pccn.getSoLuongHoanThanh());
//				statement.setTimestamp(7, Timestamp.valueOf(pccn.getNgay()));
//				statement.setString(8, pccn.getCaLamViec());

				// Execute the INSERT statement
				int affectedRows = statement.executeUpdate();
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSoLuongPhanCong(String maPhanCong, int soLuong) {
		try {
			String sql ="update BangPhanCongCongNhan set SoLuongPhanCong=? where PhanCongID=?";
			//String sql = "update BangPhanCongCongNhan\r\n" + "set SoLuongPhanCong = ?\r\n" + "where PhanCongID = ?";

			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setInt(1, soLuong);
				statement.setString(2, maPhanCong);

				// Execute the INSERT statement
				int affectedRows = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//--------------------------------------------
	public int getSLHoanThanh(String phanCongBoPhanID) {
		int sl=0;
		String sql="select sum(SoLuongHoanThanh) from BangPhanCongCongNhan where PhanCongBoPhanID=?";
		try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setString(1, phanCongBoPhanID);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				sl=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}
	
	public ArrayList<BangPhanCongCongNhan> getDanhSachCongNhanDaDuocPhanCong(String maCD, String caLamViec,
			String maBoPhan, LocalDate ngay) {
		ArrayList<BangPhanCongCongNhan> dsPhanCong = new ArrayList<>();
		try {
			String sql = "SELECT pccn.PhanCongID, bp.BoPhanID, cn.CongNhanID, pccn.PhanCongBoPhanID, pccn.SoLuongPhanCong, pccn.soLuongHoanThanh "
					+ "FROM CongDoan AS cd "
					+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.CongDoanID = cd.CongDoanID "
					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.[PhanCongBoPhanID] = pcbp.PhanCongID "
					+ "INNER JOIN CongNhan AS cn ON cn.CongNhanID = pccn.CongNhanID "
					+ "INNER JOIN Nguoi AS n ON n.NguoiID = cn.CongNhanID "
					+ "INNER JOIN BoPhan AS bp ON pcbp.BoPhanID = bp.BoPhanID " + "WHERE "
					+ "cd.congDoanID like ? AND cn.BoPhanID LIKE ? " + "AND pcbp.CaLamViec LIKE ? "
					+ "AND pcbp.Ngay = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maCD);
			statement.setString(2, maBoPhan);
			statement.setString(3, "%" + caLamViec + "%");
			statement.setDate(4, java.sql.Date.valueOf(ngay));

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String phanCongID = rs.getString("PhanCongID");
				String boPhanID = rs.getString("BoPhanID");
				String congNhanID = rs.getString("CongNhanID");
				String phanCongBoPhanID = rs.getString("PhanCongBoPhanID");
				int soLuongPhanCong = rs.getInt("SoLuongPhanCong");
				int soLuongHoanThanh = rs.getInt("SoLuongHoanThanh");
				// Tạo đối tượng BangPhanCongCongNhan từ kết quả truy vấn và thêm vào danh sách
				BoPhanDAO bpdao = new BoPhanDAO();
				CongNhanDAO cndao = new CongNhanDAO();
				CongNhan cn = cndao.getCongNhanTheoID(congNhanID);
				BangPhanCongCongNhan phanCong = new BangPhanCongCongNhan(phanCongID, cn, null, soLuongPhanCong,
						soLuongHoanThanh);
				dsPhanCong.add(phanCong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsPhanCong;
	}
	
	public void themPhanCongCongNhan(BangPhanCongCongNhan pccn, LocalDate date) {
		try {
			// Câu lệnh SQL để thêm vào bảng BangPhanCongCongNhan
			String sql = "INSERT INTO BangPhanCongCongNhan (PhanCongID, CongNhanID, PhanCongBoPhanID, SoLuongPhanCong, SoLuongHoanThanh, Ngay) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(sql);

			// Chuyển đổi LocalDate sang Timestamp để lưu vào cột datetime
			java.sql.Timestamp ngaySql = java.sql.Timestamp.valueOf(date.atStartOfDay());

			// Truyền giá trị vào các tham số của câu lệnh SQL
			statement.setString(1, pccn.getPhanCongID());
			statement.setString(2, pccn.getCongNhan().getNguoiID());
			statement.setString(3, pccn.getBangPhanCongBoPhan().getPhanCongID());
			statement.setInt(4, pccn.getSoLuongPhanCong());
			statement.setInt(5, pccn.getSoLuongHoanThanh());
			statement.setTimestamp(6, ngaySql); // Set giá trị ngày vào câu lệnh SQL

			// Thực hiện câu lệnh SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void chiaDeuPhanCongChoCongNhan(int n, String listMaPhanCongID[]) {
		String sql = "UPDATE [dbo].[BangPhanCongCongNhan]\n" + "SET SoLuongPhanCong = SoLuongPhanCong + 1\n"
				+ "WHERE PhanCongID = ?";
		try {
			// Sử dụng batch để thực hiện nhiều câu lệnh SQL một lúc
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(sql);

			for (int i = 0; i < n; i++) {
				// Đặt giá trị cho tham số của câu lệnh SQL
				statement.setString(1, listMaPhanCongID[i]);

				// Thêm câu lệnh vào batch
				statement.addBatch();
			}

			// Thực hiện batch
			int[] updateCounts = statement.executeBatch();

			// Kết thúc batch và commit thay đổi vào cơ sở dữ liệu
			con.setAutoCommit(true);

			// Kiểm tra kết quả thực hiện của từng câu lệnh
			for (int i = 0; i < updateCounts.length; i++) {
				if (updateCounts[i] == Statement.EXECUTE_FAILED) {
					// Xử lý trường hợp thất bại nếu cần thiết
					System.err.println("Lỗi khi thực hiện câu lệnh thứ " + (i + 1));
				}
			}
		} catch (SQLException e) {
			// Xử lý ngoại lệ nếu cần thiết
			e.printStackTrace();
		}
	}
	
	public String getNewMaPCCN(String typePhanCongID) throws SQLException {
		String mapc = "";
		String sql = "SELECT TOP 1 * FROM BangPhanCongCongNhan WHERE PhanCongID LIKE ? ORDER BY PhanCongID DESC";

		PreparedStatement statement = con.prepareStatement(sql);
		try {
			statement.setString(1, typePhanCongID + "%");
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					mapc = rs.getString("PhanCongID");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapc;
	}
	
	public void capNhatSoLuongHoanThanh(int n, String[] listMaPhanCongID, int[] listSoLuongHoanThanh) {
		String sql = "UPDATE [dbo].[BangPhanCongCongNhan]\n" + "SET SoLuongHoanThanh = ?\n" + "WHERE PhanCongID = ?";

		try {
			// Sử dụng batch để thực hiện nhiều câu lệnh SQL một lúc
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(sql);

			for (int i = 0; i < n; i++) {
				// Đặt giá trị cho tham số của câu lệnh SQL
				statement.setInt(1, listSoLuongHoanThanh[i]);
				statement.setString(2, listMaPhanCongID[i]);

				// Thêm câu lệnh vào batch
				statement.addBatch();
			}

			// Thực hiện batch
			int[] updateCounts = statement.executeBatch();

			// Kết thúc batch và commit thay đổi vào cơ sở dữ liệu
			con.setAutoCommit(true);

			// Kiểm tra kết quả thực hiện của từng câu lệnh
			for (int i = 0; i < updateCounts.length; i++) {
				if (updateCounts[i] == Statement.EXECUTE_FAILED) {
					// Xử lý trường hợp thất bại nếu cần thiết
					System.err.println("Lỗi khi thực hiện câu lệnh thứ " + (i + 1));
				}
			}
		} catch (SQLException e) {
			// Xử lý ngoại lệ nếu cần thiết
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getSoLuongThongKe(String boPhanID, int month, int year) {
		ArrayList<Integer> list = new ArrayList<>();

		String sql = "SELECT SUM(SoLuongPhanCong) AS TongPhanCong, SUM(SoLuongHoanThanh) AS TongHoanThanh\n"
				+ "FROM BangPhanCongCongNhan AS pccn\n"
				+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.PhanCongID = pccn.PhanCongBoPhanID\n"
				+ "WHERE pcbp.BoPhanID = ?\n" + "AND MONTH(pcbp.Ngay) = ?\n" + "AND YEAR(pcbp.Ngay) = ?";

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, boPhanID);
			statement.setInt(2, month);
			statement.setInt(3, year);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int tongPhanCong = resultSet.getInt("TongPhanCong");
				int tongHoanThanh = resultSet.getInt("TongHoanThanh");

				list.add(tongPhanCong);
				list.add(tongHoanThanh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<BangPhanCongCongNhan> getToanBoPhanCongTrongThang(String boPhanID, int month, int year) {
		ArrayList<BangPhanCongCongNhan> list = new ArrayList<>();

		String sql = "SELECT pcbp.CongDoanID, SUM(pccn.SoLuongHoanThanh) AS Tong, pccn.CongNhanID\n"
				+ "FROM BangPhanCongCongNhan AS pccn\n"
				+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.PhanCongID = pccn.PhanCongBoPhanID\n"
				+ "WHERE pcbp.BoPhanID = ?\n" + "AND MONTH(pcbp.Ngay) = ?\n" + "AND YEAR(pcbp.Ngay) = ?\n"
				+ "GROUP BY pcbp.CongDoanID, pccn.CongNhanID";

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, boPhanID);
			statement.setInt(2, month);
			statement.setInt(3, year);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				BangPhanCongCongNhan phanCong = new BangPhanCongCongNhan();
				phanCong.setPhanCongID(resultSet.getString("CongDoanID"));
				int soLuongHoanThanh = resultSet.getInt("Tong");
				String maCN = resultSet.getString("CongNhanID");

				CongNhanDAO cndao = new CongNhanDAO();
				CongNhan cn = cndao.getCongNhanTheoID(maCN);

				CongDoanDAO cddao = new CongDoanDAO();
				// Bạn cần đảm bảo rằng CongDoanDAO có phương thức layCongDoanTheoID
				CongDoan cd = cddao.layCongDoanTheoID(resultSet.getString("CongDoanID"));

				BangPhanCongBoPhan pcbp = new BangPhanCongBoPhan();
				pcbp.setCongDoan(cd);

				phanCong.setBangPhanCongBoPhan(pcbp);
				phanCong.setCongNhan(cn);
				phanCong.setSoLuongHoanThanh(soLuongHoanThanh);

				list.add(phanCong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public int soCongDoanDaLam(String boPhanID, int month, int year) {
		int soCongDoan = 0;

		String sql = "SELECT COUNT(DISTINCT pcbp.CongDoanID) AS CountCongDoanID " + "FROM BangPhanCongBoPhan AS pcbp "
				+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pcbp.PhanCongID = pccn.PhanCongBoPhanID "
				+ "WHERE pcbp.BoPhanID LIKE ? " + "AND MONTH(pcbp.Ngay) = ? " + "AND YEAR(pcbp.Ngay) = ?";

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, boPhanID);
			statement.setInt(2, month);
			statement.setInt(3, year);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				soCongDoan = resultSet.getInt("CountCongDoanID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return soCongDoan;
	}
}
