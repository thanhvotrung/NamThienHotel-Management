/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_DatPhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_DatPhong {

    public static void add(DTO_DatPhong datPhong) {
        String sqlInsert = "INSERT INTO DatPhong VALUES (?, ?, ?, ?, CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), ?, ?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, datPhong.getMaPhieu(), datPhong.getMaLoaiPhong(), datPhong.getLoaiKhach(), datPhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayDen()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayDi()), datPhong.getTenKhachHang(), datPhong.getSoLuongKhach(), datPhong.getSoDienThoai(), datPhong.getGhiChu(), datPhong.getTienCoc(), datPhong.getTrangThai());
    }

    public static void delete(String maDatPhong) {
        String sqlDelete = "DELETE FROM DatPhong WHERE MaPhieuDat = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlDelete, maDatPhong);
    }

    public static void edit(DTO_DatPhong datPhong) {
        String sqlUpdate = "UPDATE DatPhong SET MaLoaiPhong = ?, LoaiKhach = ?, MaNhanVien = ?, NgayTao = CONVERT(VARCHAR, ?), NgayDen = CONVERT(VARCHAR, ?), NgayDi = CONVERT(VARCHAR, ?), TenKhachHang = ?, SoLuongKhach = ?, SoDienThoai = ?, GhiChu = ?, TienCoc = ?, TrangThai = ? WHERE MaPhieuDat = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, datPhong.getMaLoaiPhong(), datPhong.getLoaiKhach(), datPhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayDen()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", datPhong.getNgayDi()), datPhong.getTenKhachHang(), datPhong.getSoLuongKhach(), datPhong.getSoDienThoai(), datPhong.getGhiChu(), datPhong.getTienCoc(), datPhong.getTrangThai(), datPhong.getMaPhieu());
    }

    public static ResultSet select(String dateTuNgay, String dateDenNgay) {
        String sqlSelect = "SELECT * FROM DatPhong WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ? ORDER BY MaPhieuDat";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, dateTuNgay, dateDenNgay);
    }

    public static void setTrangThai(String ngayDen, String ngayDi, String tenKhachHang, int soLuongKhach, int tienCoc) {
        String sqlUpdate = "UPDATE DatPhong SET TrangThai = 'Có Phòng' WHERE CONVERT(DATETIME, NgayDen) = ? AND CONVERT(DATETIME, NgayDi) = ? AND TenKhachHang = ? AND SoLuongKhach = ? AND TienCoc = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, ngayDen, ngayDi, tenKhachHang, soLuongKhach, tienCoc);
    }
}
