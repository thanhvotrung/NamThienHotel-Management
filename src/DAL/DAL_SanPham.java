/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Trung Thanh
 */
public class DAL_SanPham {

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM SanPham ORDER BY MaSanPham";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }

    public static ResultSet select(String maLoaiSanPham) {
        String sqlSelect = "SELECT * FROM SanPham WHERE MaLoaiSanPham = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, maLoaiSanPham);
    }

    public static void add(DTO_SanPham sanPham) {
        String sqlInsert = "INSERT INTO SanPham VALUES(?, ?, ?, ?, ?, CONVERT(VARCHAR, ?))";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, sanPham.getMaSanPham(), sanPham.getTenSanPham(), BLL_MaTenLoai.findMaLoaiSanPham(sanPham.getMaLoaiSanPham()), sanPham.getDonViTinh(), sanPham.getGiaBan(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", sanPham.getNgayTao()));
    }

    public static void edit(DTO_SanPham sanPham) {
        String sqlUpdate = "UPDATE SanPham SET TenSanPham = ?, MaLoaiSanPham = ?, DonViTinh = ?, GiaBan = ?, NgayTao = CONVERT(VARCHAR, ?) WHERE MaSanPham = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, sanPham.getTenSanPham(), BLL_MaTenLoai.findMaLoaiSanPham(sanPham.getMaLoaiSanPham()), sanPham.getDonViTinh(), sanPham.getGiaBan(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", sanPham.getNgayTao()), sanPham.getMaSanPham());
    }

    public static void delete(String maSanPham) {
        String sqlDelete = "DELETE FROM SanPham WHERE MaSanPham = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maSanPham);
    }
}
