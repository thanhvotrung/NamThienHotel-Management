/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_LoaiPhong;
import DTO.DTO_SoTang;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_LoaiPhong {

    public static void add(DTO_LoaiPhong loaiPhong) {
        String sqlSelect = "INSERT INTO LoaiPhong VALUES (?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlSelect, loaiPhong.getMaPhong(), loaiPhong.getTenPhong(), loaiPhong.getSoGiuong(), loaiPhong.getSoNguoi());
    }

    public static void delete(String maPhong) {
        String sqlDelete = "DELETE FROM LoaiPhong WHERE MaLoaiPhong = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maPhong);
    }

    public static void edit(DTO_LoaiPhong loaiPhong) {
        String sqlUpdate = "UPDATE LoaiPhong SET TenLoaiPhong = ?, SoGiuong = ?, SoNguoi = ? WHERE MaLoaiPhong = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, loaiPhong.getTenPhong(), loaiPhong.getSoGiuong(), loaiPhong.getSoNguoi(), loaiPhong.getMaPhong());
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM LoaiPhong ORDER BY MaLoaiPhong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
    
    public static ResultSet selectSoNguoi(String maLoaiPhong) {
        String sqlSelect = "SELECT * FROM LoaiPhong WHERE MaLoaiPhong = ? ORDER BY MaLoaiPhong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maLoaiPhong);
    }
}
