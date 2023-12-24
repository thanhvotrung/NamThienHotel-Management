/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_TonKho {

    public static ResultSet selectSanPham() {
        String sqlSelect = "SELECT * FROM SanPham ORDER BY MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet selectNhapKho(String dateTuNgay, String dateDenNgay, String maSanPham) {
        String sqlSelect = "SELECT ChiTietNhapKho.MaSanPham, SUM(SoLuong) AS SoLuongNhap, SUM(GiaNhap*SoLuong) AS TongTienNhap FROM ChiTietNhapkho JOIN NhapKho ON NhapKho.MaNhapKho = ChiTietNhapkho.MaNhapKho WHERE CONVERT(DATE, NhapKho.NgayTao) BETWEEN ? AND ? AND ChiTietNhapKho.MaSanPham = ? GROUP BY ChiTietNhapKho.MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, dateTuNgay, dateDenNgay, maSanPham);
    }

    public static ResultSet selectDichVu(String dateTuNgay, String dateDenNgay, String maSanPham) {
        String sqlSelect = "SELECT ChiTietDichVu.MaSanPham, SUM(SoLuongBan) AS SoLuongBan, SUM(GiaTien*SoLuongBan) AS TongTienBan FROM ChiTietDichVu JOIN PhieuDichVu ON PhieuDichVu.MaPhieuDichVu = ChiTietDichVu.MaPhieuDichVu WHERE CONVERT(DATE, PhieuDichVu.NgayTao) BETWEEN ? AND ? AND ChiTietDichVu.MaSanPham = ? GROUP BY ChiTietDichVu.MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, dateTuNgay, dateDenNgay, maSanPham);
    }

    public static ResultSet selectSoLuongNhap(String dateTuNgay, String maSanPham) {
        String sqlSelect = "SELECT ChiTietNhapKho.MaSanPham, SUM(SoLuong) AS SoLuongNhap FROM ChiTietNhapkho JOIN NhapKho ON NhapKho.MaNhapKho = ChiTietNhapkho.MaNhapKho WHERE CONVERT(DATE, NhapKho.NgayTao) NOT BETWEEN ? AND GETDATE() AND ChiTietNhapKho.MaSanPham = ? GROUP BY ChiTietNhapKho.MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, dateTuNgay, maSanPham);
    }

    public static ResultSet selectSoLuongBan(String dateTuNgay, String maSanPham) {
        String sqlSelect = "SELECT ChiTietDichVu.MaSanPham, SUM(SoLuongBan) AS SoLuongBan FROM ChiTietDichVu JOIN PhieuDichVu ON PhieuDichVu.MaPhieuDichVu = ChiTietDichVu.MaPhieuDichVu WHERE CONVERT(DATE, PhieuDichVu.NgayTao) NOT BETWEEN ? AND GETDATE() AND ChiTietDichVu.MaSanPham = ? GROUP BY ChiTietDichVu.MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, dateTuNgay, maSanPham);
    }
}
