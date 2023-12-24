/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_SoDoPhong {

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM Phong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
    
    public static ResultSet selectMaPhong(String maPhong) {
        String sqlSelect = "SELECT * FROM Phong WHERE MaPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet count() {
        String sqlSelect = "SELECT COUNT(*) FROM Phong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet count(String maTang) {
        String sqlSelect = "SELECT COUNT(*) FROM Phong WHERE MaTang = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang);
    }

    public static ResultSet rowNumber(int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, index);
    }

    public static ResultSet rowNumber(String maTang, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong WHERE MaTang = ?) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, index);
    }

    public static ResultSet select(String maPhong) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet countLoaiPhong(String trangThaiPhong) {
        String sqlSelect = "SELECT COUNT(*) FROM Phong WHERE MaTrangThaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, trangThaiPhong);
    }

    public static ResultSet countLoaiPhong(String maTang, String trangThaiPhong) {
        String sqlSelect = "SELECT COUNT(*) FROM Phong WHERE MaTang = ? AND MaTrangThaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, trangThaiPhong);
    }
}
