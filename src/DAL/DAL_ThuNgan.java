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
public class DAL_ThuNgan {

    public static ResultSet countHoaDon(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT COUNT(*) FROM HoaDon WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }
    
    public static ResultSet countTienCoc(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT COUNT(*) FROM ThuePhong WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ? AND TienCoc != 0";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }

    public static ResultSet countDichVu(String tuNgay, String denNgay) {
        String sqlSelect = "SELECT COUNT(*) FROM PhieuDichVu WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ? AND TrangThaiThanhToan = 1";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay);
    }

    public static ResultSet rowNumberHoadon(String tuNgay, String denNgay, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaHoaDon) AS RowNumber FROM HoaDon WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ?) AS HoaDon JOIN ThuePhong ON ThuePhong.MaPhieuThue = HoaDon.MaPhieuThue JOIN Phong ON Phong.MaPhong = ThuePhong.MaPhong WHERE HoaDon.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay, index);
    }
    
    public static ResultSet rowNumberTienCoc(String tuNgay, String denNgay, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhieuThue) AS RowNumber FROM ThuePhong WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ? AND TienCoc != 0) AS ThuePhong JOIN Phong ON Phong.MaPhong = ThuePhong.MaPhong WHERE ThuePhong.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay, index);
    }
    
    public static ResultSet rowNumberDichVu(String tuNgay, String denNgay, int index) {
        String sqlSelect = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY MaPhieuDichVu) AS RowNumber FROM PhieuDichVu WHERE CONVERT(DATE, NgayTao) BETWEEN ? AND ? AND TrangThaiThanhToan = 1) AS PhieuDichVu WHERE PhieuDichVu.RowNumber = ?";
        return HELPER_ConnectSQL.executeQuery(sqlSelect, tuNgay, denNgay, index);
    }
}
