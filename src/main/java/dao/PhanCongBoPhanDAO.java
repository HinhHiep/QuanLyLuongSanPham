package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.PhanCong_BoPhan;

public class PhanCongBoPhanDAO {

	private Connection con;

	public PhanCongBoPhanDAO() {
		DBConnection.getConnection();
		con = DBConnection.getConnection();
	}

	public List<PhanCong_BoPhan> getPhanCongBoPhanByCongDoanID(String congDoanID) {
		List<PhanCong_BoPhan> listPhanCongBoPhan = new ArrayList<>();
		try {
			String sql = "SELECT * FROM BangPhanCongBoPhan WHERE CongDoanID = ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setString(1, congDoanID);
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						PhanCong_BoPhan phanCongBoPhan = new PhanCong_BoPhan(rs.getString("PhanCongID"),
								rs.getInt("SoLuong"), rs.getTimestamp("Ngay").toLocalDateTime(),
								rs.getString("CongDoanID"), rs.getString("BoPhanID"));
						listPhanCongBoPhan.add(phanCongBoPhan);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPhanCongBoPhan;
	}

	// Thêm các phương thức khác cần thiết cho PhanCong_BoPhanDAO
}
