/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_ChiTietNhapKho;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_ChiTietNhapKho {

    public static void add(DTO_ChiTietNhapKho chiTietNhapKho) {
        String sqlInsert = "INSERT INTO ChiTietNhapKho VALUES (?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlInsert, chiTietNhapKho.getMaChiTietNhapKho(), chiTietNhapKho.getMaNhapKho(), chiTietNhapKho.getMaSanPham(), chiTietNhapKho.getSoLuong(), chiTietNhapKho.getGiaNhap());
    }
    
    public static void delete(String maNhapKho) {
        String sqlDelete = "DELETE FROM ChiTietNhapKho WHERE MaNhapKho = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlDelete, maNhapKho);
    }

    public static ResultSet select(String maNhapKho) {
        String sqlSelect = "SELECT * FROM ChiTietNhapKho WHERE MaNhapKho = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maNhapKho);
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM ChiTietNhapKho ORDER BY MaChiTietNhapKho";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet count(String dateTime) {
        String sqlSelect = "SELECT COUNT(*) FROM ChiTietNhapKho WHERE MaChiTietNhapKho LIKE ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, "%" + dateTime + "%");
    }
}
