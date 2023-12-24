/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_HangMucChi;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Trung Thanh
 */
public class DAL_HangMucChi {

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM LoaiTienChi ORDER BY MaLoaiTienChi";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static void add(DTO_HangMucChi hangMucChi) {
        String sqlInsert = "INSERT INTO LoaiTienChi VALUES(?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, hangMucChi.getMaChi(), hangMucChi.getMucChi());
    }

    public static void edit(DTO_HangMucChi hangMucChi) {
        String sqlUpdate = "UPDATE LoaiTienChi SET TenLoaiTienChi = ? WHERE MaLoaiTienChi = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, hangMucChi.getMucChi(), hangMucChi.getMaChi());
    }

    public static void delete(String maLoaiTienChi) {
        String sqlDelete = "DELETE FROM LoaiTienChi WHERE MaLoaiTienChi = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maLoaiTienChi);
    }
}
