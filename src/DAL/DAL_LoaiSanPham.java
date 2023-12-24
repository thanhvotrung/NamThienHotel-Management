/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_LoaiSanPham;
import HELPER.HELPER_ConnectSQL;
import java.sql.*;

/**
 *
 * @author Trung Thanh
 */
public class DAL_LoaiSanPham {

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM LoaiSanPham ORDER BY MaLoaiSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static void add(DTO_LoaiSanPham loaiSanPham) {
        String sqlInsert = "INSERT INTO LoaiSanPham VALUES(?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, loaiSanPham.getMaLoaiSanPham(), loaiSanPham.getTenLoaiSanPham());
    }

    public static void edit(DTO_LoaiSanPham loaiSanPham) {
        String sqlUpdate = "UPDATE LoaiSanPham SET TenLoaiSanPham = ? WHERE MaLoaiSanPham = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, loaiSanPham.getTenLoaiSanPham(), loaiSanPham.getMaLoaiSanPham());
    }

    public static void delete(String maLoaiSanPham) {
        String sqlDelete = "DELETE FROM LoaiSanPham WHERE MaLoaiSanPham = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maLoaiSanPham);
    }
}
