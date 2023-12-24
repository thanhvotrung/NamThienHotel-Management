/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLL_MaTenLoai;
import DTO.DTO_NhapKho;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_NhapKho {

    public static void add(DTO_NhapKho nhapKho) {
        String sqlInsert = "INSERT INTO NhapKho VALUES (?, ?, CONVERT(VARCHAR, ?), ?)";
        HELPER_ConnectSQL.executeUpdate(sqlInsert, nhapKho.getMaNhapKho(), BLL_MaTenLoai.findMaNhanVien(nhapKho.getMaNhanVien()), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", nhapKho.getNgayTao()), nhapKho.getGhiChu());
    }

    public static void delete(String maNhapKho) {
        String sqlDelete = "DELETE FROM NhapKho WHERE MaNhapKho = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sqlDelete, maNhapKho);
    }

    public static ResultSet countSearch(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT COUNT(*) FROM NhapKho WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }

    public static ResultSet search(String tuNgay, String denNgay, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaNhapKho) AS RowNumber FROM NhapKho WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?) AS NhapKho WHERE NhapKho.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay, index);
    }

    public static ResultSet money(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT SUM(SoLuong * GiaNhap) FROM ChiTietNhapkho JOIN NhapKho ON NhapKho.MaNhapKho = ChiTietNhapkho.MaNhapKho WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }
}
