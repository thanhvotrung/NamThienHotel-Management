/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_HoaDon;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_HoaDon {
    
    public static void addHoaDon(DTO_HoaDon hoaDon) {
        String sqlSelect = "INSERT INTO HoaDon VALUES (?, ?, CONVERT(VARCHAR, ?), ?, ?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlSelect, hoaDon.getMaHoaDon(), hoaDon.getMaPhieuThue(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", hoaDon.getNgayTao()), hoaDon.getMaNhanVien(), hoaDon.getTienPhong(), hoaDon.getTienDichVu(), hoaDon.getDaTra(), hoaDon.getConLai(), hoaDon.getMaPhuongThuc());
    } 
    
    public static ResultSet findMaPhieuThue(String maHoaDon) {
        String sqlSelect = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maHoaDon);
    }
}
