package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.PhanCong;

public class PhanCongDAO {
	private Connection con;

	public PhanCongDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public List<PhanCong> getPhanCongByConditions(String congDoanID, String boPhanID) {
		List<PhanCong> phanCongs = new ArrayList<>();
		try {
			String sql = "SELECT pccn.PhanCongID, sp.SanPhamID, sp.TenSanPham, cd.CongDoanID, cd.TenCongDoan, cn.CongNhanID, n.HoTen, pccn.Ngay, pccn.SoLuongPhanCong "
					+ "FROM CongNhan AS cn " + "INNER JOIN Nguoi AS n ON n.NguoiID = cn.CongNhanID "
					+ "INNER JOIN BangPhanCongCongNhan AS pccn ON pccn.CongNhanID = cn.CongNhanID "
					+ "INNER JOIN BoPhan AS bp ON bp.BoPhanID = pccn.BoPhanID "
					+ "INNER JOIN BangPhanCongBoPhan AS pcbp ON pcbp.BoPhanID = bp.BoPhanID "
					+ "INNER JOIN CongDoan AS cd ON cd.CongDoanID = pcbp.CongDoanID "
					+ "INNER JOIN SanPham AS sp ON sp.SanPhamID = cd.SanPhamID "
					+ "where  bp.BoPhanID = ? and pccn.PhanCongBPID = pcbp.PhanCongID and pcbp.CongDoanID = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, boPhanID);
				preparedStatement.setString(2, congDoanID);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						// Xử lý dữ liệu từ ResultSet và tạo đối tượng PhanCong
						PhanCong phanCong = new PhanCong(resultSet.getString("PhanCongID"),
								resultSet.getString("SanPhamID"), resultSet.getString("TenSanPham"),
								resultSet.getString("CongDoanID"), resultSet.getString("TenCongDoan"),
								resultSet.getString("CongNhanID"), resultSet.getString("HoTen"),
								resultSet.getDate("Ngay"), resultSet.getInt("SoLuongPhanCong"));
						phanCongs.add(phanCong);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý ngoại lệ SQL
		}
		return phanCongs;
	}

	public int getSoLuongDaPhanCong(String congDoanID, String boPhanID) {
		int tong = 0;
		try {
			String sql = "select sum(pccn.SoLuongPhanCong) as SoLuongDaPhanCong\n" + "from CongDoan as cd\n"
					+ "inner join BangPhanCongBoPhan as pcbp on pcbp.CongDoanID = cd.CongDoanID\n"
					+ "inner join BoPhan as bp on bp.BoPhanID = pcbp.BoPhanID\n"
					+ "inner join BangPhanCongCongNhan as pccn on pccn.BoPhanID = bp.BoPhanID\n"
					+ "where cd.CongDoanID like ? and pccn.PhanCongBPID = pcbp.PhanCongID and bp.BoPhanID = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, congDoanID);
				preparedStatement.setString(2, boPhanID);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						// Lấy giá trị tổng số lượng đã phân công từ cột SoLuongDaPhanCong
						tong = resultSet.getInt("SoLuongDaPhanCong");
					}
				}
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

}
