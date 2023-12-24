/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_MaTenLoai;
import DAL.DAL_DatPhong;
import DTO.DTO_DatPhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_DatPhong {

    public static boolean check(DTO_DatPhong datPhong) {
        if (datPhong.getMaPhieu().isEmpty() || datPhong.getLoaiKhach().isEmpty() || datPhong.getMaNhanVien().isEmpty() || datPhong.getNgayTao() == null || datPhong.getNgayDen() == null || datPhong.getNgayDi() == null || datPhong.getTenKhachHang().isEmpty() || datPhong.getSoLuongKhach() == 0 || datPhong.getSoDienThoai() == 0) {
            return false;
        }
        return true;
    }

    public static void add(DTO_DatPhong datPhong) {
        if (!check(datPhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_DatPhong.add(datPhong);
        }
    }

    public static void delete(String maPhieuDat) {
        DAL_DatPhong.delete(maPhieuDat);
    }

    public static void edit(DTO_DatPhong datPhong) {
        if (!check(datPhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_DatPhong.edit(datPhong);
        }
    }

    public static ArrayList<DTO_DatPhong> select(String dateTuNgay, String dateDenNgay) {
        ResultSet rs = DAL_DatPhong.select(dateTuNgay, dateDenNgay);
        ArrayList<DTO_DatPhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_DatPhong datPhong = new DTO_DatPhong();
                datPhong.setMaPhieu(rs.getString("MaPhieuDat"));
                datPhong.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                datPhong.setLoaiKhach(rs.getString("LoaiKhach"));
                datPhong.setMaNhanVien(rs.getString("MaNhanVien"));
                datPhong.setNgayTao(rs.getTimestamp("NgayTao"));
                datPhong.setNgayDen(rs.getTimestamp("NgayDen"));
                datPhong.setNgayDi(rs.getTimestamp("NgayDi"));
                datPhong.setTenKhachHang(rs.getString("TenKhachHang"));
                datPhong.setSoLuongKhach(rs.getInt("SoLuongKhach"));
                datPhong.setSoDienThoai(rs.getInt("SoDienThoai"));
                datPhong.setGhiChu(rs.getString("GhiChu"));
                datPhong.setTienCoc(rs.getInt("TienCoc"));
                datPhong.setTrangThai(rs.getString("TrangThai"));
                array.add(datPhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_DatPhong> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_DatPhong datPhong : array) {
            Object obj[] = new Object[13];
            obj[0] = datPhong.getMaPhieu();
            obj[1] = datPhong.getTenKhachHang();
            obj[2] = datPhong.getSoDienThoai();
            obj[3] = datPhong.getSoLuongKhach();
            obj[4] = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy HH:mm", datPhong.getNgayDen());
            obj[5] = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy HH:mm", datPhong.getNgayDi());
            obj[6] = datPhong.getTienCoc() + "K";
            obj[7] = BLL_MaTenLoai.findTenLoaiPhong(datPhong.getMaLoaiPhong());
            obj[8] = datPhong.getLoaiKhach();
            obj[9] = datPhong.getGhiChu();
            obj[10] = BLL_MaTenLoai.findTenNhanVien(datPhong.getMaNhanVien());
            obj[11] = HELPER_ChuyenDoi.getNgayString("dd-MM-yy HH:mm", datPhong.getNgayTao());
            obj[12] = datPhong.getTrangThai();
            tbl.getColumnModel().getColumn(13).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(14).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }
}
