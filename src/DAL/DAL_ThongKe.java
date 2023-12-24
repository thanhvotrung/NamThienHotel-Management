/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Trung Thanh
 */
public class DAL_ThongKe {
    
  
    public static ResultSet SLKhachVaoTheoGio(Date dateTu, Date dateDen)
    {
        String sql = "select FORMAT(ngaytao,'HH') as 'hour', count(FORMAT(ngaytao,'HH')) as 'count' from thuephong where FORMAT(ngaytao,'yyyy-MM-dd') between ? and ? group by FORMAT(ngaytao,'HH') order by FORMAT(ngaytao,'HH') DESC";
        return HELPER.HELPER_ConnectSQL.executeQuery(sql, HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateTu), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateDen));
    }

    public static ResultSet TienLoaiPhongKiemTrongNgay(Date date, Date date2)
    {
        String sql = "exec SP_TienLoaiPhongTrongNgay @date = ?, @date2 = ?";
        return HELPER.HELPER_ConnectSQL.executeQuery(sql, HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date2));
    }
   
    public static ResultSet NguonTienTrongNgay(Date date, Date date2)
    {
        String sql = "exec SP_ThongKeNguonTienTrongNgay @date = ?, @date2 = ?";
        return HELPER.HELPER_ConnectSQL.executeQuery(sql, HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date), HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date2));
    }
    
    public static ResultSet getMaLoaiPhong()
    {
        String sql = "SELECT MaLoaiPhong FROM loaiphong";
        return HELPER_ConnectSQL.executeQuery(sql);
    }
    
    public static ResultSet ThongKeTinhTrangPhong(Date date)
    {
        String sql = "select sum(PhongTrong) as 'PhongTrong', sum(DangO) as 'DangO', sum(DaTra) as 'DaTra', sum(DatTruoc) as 'DatTruoc' from ThongKeTinhTrangPhong where format(ThoiGianTao,'yyyy-MM-dd') like ?";
        return HELPER_ConnectSQL.executeQuery(sql,  HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date));
    }
    
    // update thong ke phong
    public static void UpdateThongKe(Date date)
    {
        String sql = "EXEC dbo.SP_ThongKeCacTrangThaiPhong @date = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sql, HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date));
    }
    
    public static ResultSet ThongKeTinhTrangLoaiPhong(Date date, String maLoaiPhong)
    {
        String sql = "select LoaiPhong.TenLoaiPhong, PhongTrong, DangO, DaTra, DatTruoc from ThongKeTinhTrangLoaiPhong join LoaiPhong on LoaiPhong.MaLoaiPhong = ThongKeTinhTrangLoaiPhong.MaLoaiPhong where format(ThoiGianTao,'yyyy-MM-dd') like ? and ThongKeTinhTrangLoaiPhong.MaLoaiPhong = ?";
        return HELPER_ConnectSQL.executeQuery(sql,  HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date), maLoaiPhong);
    }
     
    public static void UpdateThongKeLoaiPhong(Date date, String maLoaiPhong)
    {
        String sql = "EXEC dbo.SP_ThongKeCacTrangThaiLoaiPhong @date = ?,  @kindOfRoom = ?";
        HELPER_ConnectSQL.executeUpdateNoMessage(sql, HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", date), maLoaiPhong);
    }
    

}
