/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_ChiTietDichVu;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_ChiTietDichVu {

    public static void add(DTO_ChiTietDichVu chiTietDichVu) {
        String sqlInsert = "INSERT INTO ChiTietDichVu VALUES (?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlInsert, chiTietDichVu.getMaChiTiet(), chiTietDichVu.getMaPhieuDichVu(), chiTietDichVu.getMaSanPham(), chiTietDichVu.getSoLuong(), chiTietDichVu.getGiaTien());
    }
    
    public static void delete(String maPhieuDichVu) {
        String sqlDelete = "DELETE FROM ChiTietDichVu WHERE MaPhieuDichVu = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlDelete, maPhieuDichVu);
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM ChiTietDichVu ORDER BY MaChiTiet";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet selectMaDichVu(String maDichVu) {
        String sqlSelect = "SELECT * FROM ChiTietDichVu WHERE MaPhieuDichVu = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maDichVu);
    }

    public static ResultSet selectMaSanPham(String maSanPham) {
        String sqlSelect = "SELECT * FROM ChiTietDichVu WHERE MaSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maSanPham);
    }

    public static ResultSet count(String dateTime) {
        String sqlSelect = "SELECT COUNT(*) FROM ChiTietDichVu WHERE MaChiTiet LIKE ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, "%" + dateTime + "%");
    }

    public static ResultSet countDichVu(String maPhieuThue) {
        String sqlSelect = "SELECT MaSanPham, SUM(SoLuongBan) AS SoLuongBan, GiaTien FROM PhieuDichVu JOIN ChiTietDichVu ON ChiTietDichVu.MaPhieuDichVu = PhieuDichVu.MaPhieuDichVu WHERE MaPhieuThue = ? GROUP BY MaSanPham, GiaTien ORDER BY MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuThue);
    }

    public static ResultSet tongTienDichVu(String maPhieuThue) {
        String sqlSelect = "SELECT SUM(SoLuongBan * GiaTien) FROM PhieuDichVu JOIN ChiTietDichVu ON ChiTietDichVu.MaPhieuDichVu = PhieuDichVu.MaPhieuDichVu WHERE MaPhieuThue = ? GROUP BY MaPhieuThue";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuThue);
    }

    public static ResultSet countTienDichVu(String maPhieuDichVu) {
        String sqlSelect = "SELECT SUM(SoLuongBan*GiaTien) FROM PhieuDichVu JOIN ChiTietDichVu ON ChiTietDichVu.MaPhieuDichVu = PhieuDichVu.MaPhieuDichVu WHERE PhieuDichVu.MaPhieuDichVu = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuDichVu);
    }

    public static ResultSet countThanhToan(String maPhieuThue) {
        String sqlSelect = "SELECT SUM(SoLuongBan * GiaTien) FROM PhieuDichVu JOIN ChiTietDichVu ON ChiTietDichVu.MaPhieuDichVu = PhieuDichVu.MaPhieuDichVu WHERE MaPhieuThue = ? AND TrangThaiThanhToan = 1 GROUP BY MaPhieuThue";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuThue);
    }

    public static ResultSet countGiamGiaByPhong(String maPhong) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet countTienCocByMaPhong(String maPhong) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet countTienCocByMaPhieu(String maPhieuThue) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhieuThue = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuThue);
    }

    public static ResultSet countGiamGiaByPhieu(String maPhieuThue) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhieuThue = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhieuThue);
    }
}
