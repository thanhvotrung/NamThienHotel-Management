/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_ChiTietNhapKho;
import DAL.DAL_NhapKho;
import DAL.DAL_PhieuChi;
import DTO.DTO_ChiTietNhapKho;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_ChiTietNhapKho {

    public static boolean check(DTO_ChiTietNhapKho chiTietNhapKho) {
        if (chiTietNhapKho.getMaChiTietNhapKho().isEmpty() || chiTietNhapKho.getMaNhapKho().isEmpty() || chiTietNhapKho.getMaSanPham().isEmpty() || chiTietNhapKho.getSoLuong() == 0 || chiTietNhapKho.getGiaNhap() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_ChiTietNhapKho chiTietNhapKho) {
        if (!check(chiTietNhapKho)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ChiTietNhapKho.add(chiTietNhapKho);
        }
    }

    public static void delete(String maNhapKho) {
        DAL_ChiTietNhapKho.delete(maNhapKho);
    }

    public static ArrayList<DTO_ChiTietNhapKho> select(String maNhapKho) {
        ResultSet rs = DAL_ChiTietNhapKho.select(maNhapKho);
        ArrayList<DTO_ChiTietNhapKho> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ChiTietNhapKho chiTietNhapKho = new DTO_ChiTietNhapKho();
                chiTietNhapKho.setMaChiTietNhapKho(rs.getString("MaChiTietNhapKho"));
                chiTietNhapKho.setMaNhapKho(rs.getString("MaNhapKho"));
                chiTietNhapKho.setMaSanPham(rs.getString("MaSanPham"));
                chiTietNhapKho.setSoLuong(rs.getInt("SoLuong"));
                chiTietNhapKho.setGiaNhap(rs.getInt("GiaNhap"));
                array.add(chiTietNhapKho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_ChiTietNhapKho> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_ChiTietNhapKho chiTietNhapKho : array) {
            Object obj[] = new Object[5];
            obj[0] = chiTietNhapKho.getMaSanPham();
            obj[1] = BLL_MaTenLoai.findTenSanPham(chiTietNhapKho.getMaSanPham());
            obj[2] = chiTietNhapKho.getSoLuong();
            obj[3] = chiTietNhapKho.getGiaNhap() + "K";
            obj[4] = chiTietNhapKho.getSoLuong() * chiTietNhapKho.getGiaNhap() + "K";
            tbl.getColumnModel().getColumn(5).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }

    public static void loadChiTietNhapKho(ArrayList<DTO_ChiTietNhapKho> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_ChiTietNhapKho chiTietNhapKho : array) {
            Object obj[] = new Object[4];
            obj[0] = BLL_MaTenLoai.findTenSanPham(chiTietNhapKho.getMaSanPham());
            obj[1] = chiTietNhapKho.getSoLuong();
            obj[2] = chiTietNhapKho.getGiaNhap() + "K";
            obj[3] = chiTietNhapKho.getSoLuong() * chiTietNhapKho.getGiaNhap() + "K";
            tblModel.addRow(obj);
        }
    }
}
