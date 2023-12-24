/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_NhanVien;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_NhanVien {

    public static void add(DTO_NhanVien nhanVien) {
        String sqlInsert = "INSERT INTO NhanVien VALUES (?, ?, ?, CONVERT(VARCHAR, ?), ?, ?, ?, ?, CONVERT(VARCHAR, ?), ?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getGioiTinh(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", nhanVien.getNgaySinh()), nhanVien.getSoDienThoai(), nhanVien.getCMND(), BLL_MaTenLoai.findMaChucVu(nhanVien.getMaChucVu()), nhanVien.getLuong(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", nhanVien.getNgayTao()), nhanVien.getTrangThaiNhanVien(), nhanVien.getHinhAnh());
    }

    public static void delete(String maNhanVien) {
        String sqlDelete = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maNhanVien);
    }

    public static void edit(DTO_NhanVien nhanVien) {
        String sqlUpdate = "UPDATE NhanVien SET TenNhanVien = ?, GioiTinh = ?, NgaySinh = CONVERT(VARCHAR, ?), SoDienThoai = ?, CMND = ?, MaChucVu = ?, Luong = ?, NgayTao = CONVERT(VARCHAR, ?), TrangThaiNhanVien = ?, HinhAnh = ? WHERE MaNhanVien = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, nhanVien.getTenNhanVien(), nhanVien.getGioiTinh(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", nhanVien.getNgaySinh()), nhanVien.getSoDienThoai(), nhanVien.getCMND(), BLL_MaTenLoai.findMaChucVu(nhanVien.getMaChucVu()), nhanVien.getLuong(), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", nhanVien.getNgayTao()), nhanVien.getTrangThaiNhanVien(), nhanVien.getHinhAnh(), nhanVien.getMaNhanVien());
    }

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM NhanVien ORDER BY MaNhanVien";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
    
    public static void setOffline() {
        String sqlUpdate = "UPDATE NhanVien SET TrangThaiNhanVien = 0";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate);
    }

    public static void setOnline(String maNhanVien) {
        String sqlUpdate = "UPDATE NhanVien SET TrangThaiNhanVien = 1 WHERE MaNhanVien = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlUpdate, maNhanVien);
    }
}
