/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_MaTenLoai {

    public static ResultSet selectTenTang() {
        String sqlSelect = "SELECT * FROM Tang ORDER BY MaTang";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet findTenTang(String maTang) {
        String sqlSelect = "SELECT * FROM Tang WHERE MaTang = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTang);
    }

    public static ResultSet findMaTang(String tenTang) {
        String sqlSelect = "SELECT * FROM Tang WHERE TenTang = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenTang);
    }

    public static ResultSet selectTenLoaiPhong() {
        String sqlSelect = "SELECT * FROM LoaiPhong ORDER BY MaLoaiPhong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet findTenLoaiPhong(String maLoaiPhong) {
        String sqlSelect = "SELECT * FROM LoaiPhong WHERE MaLoaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maLoaiPhong);
    }

    public static ResultSet findMaLoaiPhong(String tenLoaiPhong) {
        String sqlSelect = "SELECT * FROM LoaiPhong WHERE TenLoaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenLoaiPhong);
    }

    public static ResultSet selectTenTrangThaiPhong() {
        String sqlSelect = "SELECT * FROM TrangThaiPhong ORDER BY MaTrangThaiPhong";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet findTenTrangThaiPhong(String maTrangThaiPhong) {
        String sqlSelect = "SELECT * FROM TrangThaiPhong WHERE MaTrangThaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maTrangThaiPhong);
    }

    public static ResultSet findMaTrangThaiPhong(String tenTrangThaiPhong) {
        String sqlSelect = "SELECT * FROM TrangThaiPhong WHERE TenTrangThaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenTrangThaiPhong);
    }

    public static ResultSet findTenPhong(String maPhong) {
        String sqlSelect = "SELECT * FROM Phong WHERE MaPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhong);
    }

    public static ResultSet findMaPhong(String tenPhong) {
        String sqlSelect = "SELECT * FROM Phong WHERE TenPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenPhong);
    }

    public static ResultSet findTenNhanVien(String maNhanVien) {
        String sqlSelect = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maNhanVien);
    }

    public static ResultSet findMaNhanVien(String tenNhanVien) {
        String sqlSelect = "SELECT * FROM NhanVien WHERE TenNhanVien = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenNhanVien);
    }

    public static ResultSet findTenChucVu(String maChucVu) {
        String sqlSelect = "SELECT * FROM ChucVu WHERE MaChucVu = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maChucVu);
    }

    public static ResultSet findMaChucVu(String tenChucVu) {
        String sqlSelect = "SELECT * FROM ChucVu WHERE TenChucVu = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenChucVu);
    }

    public static ResultSet selectTenLoaiSanPham() {
        String sqlSelect = "SELECT * FROM LoaiSanPham ORDER BY MaLoaiSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet findTenLoaiSanPham(String maLoaiSanPham) {
        String sqlSelect = "SELECT * FROM LoaiSanPham WHERE MaLoaiSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maLoaiSanPham);
    }

    public static ResultSet findMaLoaiSanPham(String tenLoaiSanPham) {
        String sqlSelect = "SELECT * FROM LoaiSanPham WHERE TenLoaiSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenLoaiSanPham);
    }

    public static ResultSet findTenSanPham(String maSanPham) {
        String sqlSelect = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maSanPham);
    }

    public static ResultSet findMaSanPham(String tenSanPham) {
        String sqlSelect = "SELECT * FROM SanPham WHERE TenSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenSanPham);
    }
    
    public static ResultSet findTenLoaiTienChi(String maLoaiTienChi) {
        String sqlSelect = "SELECT * FROM LoaiTienChi WHERE MaLoaiTienChi = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maLoaiTienChi);
    }

    public static ResultSet findMaLoaiTienChi(String tenLoaiTienChi) {
        String sqlSelect = "SELECT * FROM LoaiTienChi WHERE TenLoaiTienChi = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenLoaiTienChi);
    }
    
    public static ResultSet findTenPhuongThuc(String maPhuongThuc) {
        String sqlSelect = "SELECT * FROM PhuongThucThanhToan WHERE MaPhuongThuc = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maPhuongThuc);
    }

    public static ResultSet findMaPhuongThuc(String tenPhuongThuc) {
        String sqlSelect = "SELECT * FROM PhuongThucThanhToan WHERE TenPhuongThuc = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenPhuongThuc);
    }
    
    public static ResultSet selectTenMucChi() {
        String sqlSelect = "SELECT * FROM LoaiTienChi ORDER BY MaLoaiTienChi";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
    
    public static ResultSet selectTenPhuongThuc() {
        String sqlSelect = "SELECT * FROM PhuongThucThanhToan ORDER BY MaPhuongThuc";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
}
