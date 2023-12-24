/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_Phong;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_Phong {

    public static void add(DTO_Phong phong) {
        String sqlSelect = "INSERT INTO Phong VALUES (?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlSelect, phong.getMaPhong(), phong.getTenPhong(), BLL_MaTenLoai.findMaTang(phong.getMaTang()), BLL_MaTenLoai.findMaLoaiPhong(phong.getMaLoaiPhong()), BLL_MaTenLoai.findMaTrangThaiPhong(phong.getMaTrangThaiPhong()));
    }

    public static void delete(String maPhong) {
        String sqlDelete = "DELETE FROM Phong WHERE MaPhong = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maPhong);
    }

    public static void edit(DTO_Phong phong) {
        String sqlUpdate = "UPDATE Phong SET TenPhong = ?, MaTang = ?, MaLoaiPhong = ?, MaTrangThaiPhong = ? WHERE MaPhong = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, phong.getTenPhong(), BLL_MaTenLoai.findMaTang(phong.getMaTang()), BLL_MaTenLoai.findMaLoaiPhong(phong.getMaLoaiPhong()), BLL_MaTenLoai.findMaTrangThaiPhong(phong.getMaTrangThaiPhong()), phong.getMaPhong());
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM Phong ORDER BY MaPhong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet searchChonPhong(String maTang, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong WHERE MaTrangThaiPhong NOT LIKE 'PhongTrong' AND MaTrangThaiPhong NOT LIKE 'TraPhong' AND Phong.MaTang = ?) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, index);
    }
    
    public static ResultSet searchChuyenPhong(String maTang, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong WHERE MaTrangThaiPhong LIKE 'PhongTrong' AND Phong.MaTang = ?) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, index);
    }

    public static ResultSet searchSoPhong(String maTang, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong WHERE MaTrangThaiPhong NOT LIKE 'PhongTrong' AND MaTrangThaiPhong NOT LIKE 'TraPhong' AND Phong.MaTang = ?) AS Phong JOIN ThuePhong ON ThuePhong.MaPhong = Phong.MaPhong WHERE TrangThaiThanhToan = 0 AND Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, index);
    }

    public static ResultSet countSearchChonPhong(String maTang) {
        String sqlSelect = "SELECT COUNT(*) FROM Phong WHERE MaTrangThaiPhong NOT LIKE 'PhongTrong' AND MaTrangThaiPhong NOT LIKE 'TraPhong' AND MaTang = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang);
    }
    
    public static ResultSet countSearchChuyenPhong(String maTang) {
        String sqlSelect = "SELECT COUNT(*) FROM Phong WHERE MaTrangThaiPhong LIKE 'PhongTrong' AND MaTang = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang);
    }
}
