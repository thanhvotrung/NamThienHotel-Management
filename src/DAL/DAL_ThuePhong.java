/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_ThuePhong;
import DTO.DTO_SoTang;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_ThuePhong {

    public static void addThuePhong(DTO_ThuePhong thuePhong) {
        String sqlSelect = "INSERT INTO ThuePhong VALUES (?, ?, ?, CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlSelect, thuePhong.getMaPhong(), thuePhong.getMaPhieuThue(), thuePhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDen()), null, thuePhong.getCMND(), thuePhong.getTenKhachHang(), thuePhong.getSoLuong(), thuePhong.getGhiChu(), thuePhong.getTienCoc(), thuePhong.getGiamGia(), thuePhong.getHinhAnh(), thuePhong.getMaPhuongThuc(), thuePhong.getTrangThaiThanhToan());
    }

    public static void addDatPhong(DTO_ThuePhong thuePhong) {
        String sqlSelect = "INSERT INTO ThuePhong VALUES (?, ?, ?, CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), CONVERT(VARCHAR, ?), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlSelect, thuePhong.getMaPhong(), thuePhong.getMaPhieuThue(), thuePhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDen()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDi()), thuePhong.getCMND(), thuePhong.getTenKhachHang(), thuePhong.getSoLuong(), thuePhong.getGhiChu(), thuePhong.getTienCoc(), thuePhong.getGiamGia(), thuePhong.getHinhAnh(), thuePhong.getMaPhuongThuc(), thuePhong.getTrangThaiThanhToan());
    }

    public static void delete(String maPhong) {
        String sqlDelete = "DELETE FROM ThuePhong WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlDelete, maPhong);
    }

    public static void editThuePhong(DTO_ThuePhong thuePhong) {
        String sqlUpdate = "UPDATE ThuePhong SET MaPhong = ?, MaNhanVien = ?, NgayTao = CONVERT(VARCHAR, ?), NgayDen = CONVERT(VARCHAR, ?), NgayDi = CONVERT(VARCHAR, ?), CMND = ?, TenKhachHang = ?, SoLuongKhach = ?, GhiChu = ?, TienCoc = ?, GiamGia = ?, HinhAnh = ?, MaPhuongThuc = ?, TrangThaiThanhToan = ? WHERE MaPhieuThue = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, thuePhong.getMaPhong(), thuePhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDen()), null, thuePhong.getCMND(), thuePhong.getTenKhachHang(), thuePhong.getSoLuong(), thuePhong.getGhiChu(), thuePhong.getTienCoc(), thuePhong.getGiamGia(), thuePhong.getHinhAnh(), thuePhong.getMaPhuongThuc(), thuePhong.getTrangThaiThanhToan(), thuePhong.getMaPhieuThue());
    }

    public static void editDatPhong(DTO_ThuePhong thuePhong) {
        String sqlUpdate = "UPDATE ThuePhong SET MaPhong = ?, MaNhanVien = ?, NgayTao = CONVERT(VARCHAR, ?), NgayDen = CONVERT(VARCHAR, ?), NgayDi = CONVERT(VARCHAR, ?), CMND = ?, TenKhachHang = ?, SoLuongKhach = ?, GhiChu = ?, TienCoc = ?, GiamGia = ?, HinhAnh = ?, MaPhuongThuc = ?, TrangThaiThanhToan = ? WHERE MaPhieuThue = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, thuePhong.getMaPhong(), thuePhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDen()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDi()), thuePhong.getCMND(), thuePhong.getTenKhachHang(), thuePhong.getSoLuong(), thuePhong.getGhiChu(), thuePhong.getTienCoc(), thuePhong.getGiamGia(), thuePhong.getHinhAnh(), thuePhong.getMaPhuongThuc(), thuePhong.getTrangThaiThanhToan(), thuePhong.getMaPhieuThue());
    }

    public static void addThanhToan(DTO_ThuePhong thuePhong) {
        String sqlUpdate = "UPDATE ThuePhong SET MaPhong = ?, MaNhanVien = ?, NgayTao = CONVERT(VARCHAR, ?), NgayDen = CONVERT(VARCHAR, ?), NgayDi = CONVERT(VARCHAR, ?), CMND = ?, TenKhachHang = ?, SoLuongKhach = ?, GhiChu = ?, TienCoc = ?, GiamGia = ?, HinhAnh = ?, MaPhuongThuc = ?, TrangThaiThanhToan = ? WHERE MaPhieuThue = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, thuePhong.getMaPhong(), thuePhong.getMaNhanVien(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayTao()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDen()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", thuePhong.getNgayDi()), thuePhong.getCMND(), thuePhong.getTenKhachHang(), thuePhong.getSoLuong(), thuePhong.getGhiChu(), thuePhong.getTienCoc(), thuePhong.getGiamGia(), thuePhong.getHinhAnh(), thuePhong.getMaPhuongThuc(), thuePhong.getTrangThaiThanhToan(), thuePhong.getMaPhieuThue());
    }

    public static ResultSet select(String maPhong) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet countSearch(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT COUNT(*) FROM ThuePhong WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }

    public static ResultSet rowNumber(String tuNgay, String denNgay, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhieuThue) AS RowNumber FROM ThuePhong WHERE CONVERT(DATE, ThuePhong.NgayTao) BETWEEN ? AND ?) AS ThuePhong JOIN Phong ON Phong.MaPhong = ThuePhong.MaPhong WHERE ThuePhong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay, index);
    }

    public static ResultSet findMaPhieuThue(String ngayDen, String ngayDi) {
        String sqlSelect = "SELECT * FROM ThuePhong WHERE CONVERT(DATETIME, NgayDen) = ? AND CONVERT(DATETIME, NgayDi) = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, ngayDen, ngayDi);
    }

    public static ResultSet findMaPhong(int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, index);
    }

    public static ResultSet findMaPhong(String maTang, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhong) AS RowNumber FROM Phong WHERE MaTang = ?) AS Phong WHERE Phong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang, index);
    }

    public static void setTrangThaiPhong(String maTrangThaiPhong, String maPhong) {
        String sqlUpdate = "UPDATE Phong SET MaTrangThaiPhong = ? WHERE MaPhong = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, maTrangThaiPhong, maPhong);
    }

    public static void setThanhToan(String maPhong) {
        String sqlUpdate = "UPDATE ThuePhong SET TrangThaiThanhToan = 1 WHERE MaPhong = ? AND TrangThaiThanhToan = 0";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, maPhong);
    }
}
