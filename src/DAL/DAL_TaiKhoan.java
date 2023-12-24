/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_TaiKhoan;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_TaiKhoan {

    public static void add(DTO_TaiKhoan taiKhoan) {
        String sqlInsert = "INSERT INTO taiKhoan VALUES (?, ?, ?, ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, taiKhoan.getMaTaiKhoan(), taiKhoan.getMaNhanVien(), taiKhoan.getTenDangNhap(), taiKhoan.getMatKhau(), taiKhoan.getCheckDangNhap());
    }

    public static void delete(String maNhanVien) {
        String sqlDelete = "DELETE FROM TaiKhoan WHERE MaNhanVien = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maNhanVien);
    }

    public static void edit(String tenDangNhap, String matKhau, String maTaiKhoan) {
        String sqlUpdate = "UPDATE TaiKhoan SET TenDangNhap = ?, MatKhau = ? WHERE MaTaiKhoan = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, tenDangNhap, matKhau, maTaiKhoan);
    }

    public static void editMatKhau(String matKhau, String tenDangNhap) {
        String sqlUpdate = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenDangNhap = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, matKhau, tenDangNhap);
    }

    public static void editCheckDangNhap_0() {
        String sqlUpdate = "UPDATE TaiKhoan SET CheckDangNhap = 0";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate);
    }

    public static void editCheckDangNhap_1(String tenDangNhap) {
        String sqlUpdate = "UPDATE TaiKhoan SET CheckDangNhap = 1 WHERE TenDangNhap = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, tenDangNhap);
    }

    public static ResultSet selectTaiKhoan(String tenDangNhap, String matKhau) {
        String sqlSelect = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenDangNhap, matKhau);
    }

    public static ResultSet select(String maNhanVien) {
        String sqlSelect = "SELECT * FROM NhanVien LEFT JOIN TaiKhoan ON TaiKhoan.MaNhanVien = NhanVien.MaNhanVien WHERE NhanVien.MaNhanVien = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maNhanVien);
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM TaiKhoan ORDER BY MaTaiKhoan";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet selectMaNhanVien(String tenDangNhap) {
        String sqlSelect = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tenDangNhap);
    }

    public static ResultSet checkDangNhap() {
        String sqlSelect = "SELECT * FROM TaiKhoan WHERE CheckDangNhap = 1";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet auThenTiCaTion(String auThen) {
        String sqlSelect = "SELECT * FROM TaiKhoan JOIN NhanVien ON NhanVien.MaNhanVien = TaiKhoan.MaNhanVien WHERE TenDangNhap = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, auThen);
    }
}
