/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_LoaiSanPham;
import DTO.DTO_LoaiSanPham;
import GUI.GUI_dal_PhieuNhap;
import GUI.GUI_dal_ThongTinDichVu;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Trung Thanh
 */
public class BLL_LoaiSanPham {

    public static boolean check(DTO_LoaiSanPham loaiSanPham) {
        if (loaiSanPham.getMaLoaiSanPham().isEmpty() || loaiSanPham.getTenLoaiSanPham().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_LoaiSanPham loaiSanPham) {
        if (!check(loaiSanPham)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_LoaiSanPham.add(loaiSanPham);
        }
    }

    public static void delete(String maLoaiSanPham) {
        DAL_LoaiSanPham.delete(maLoaiSanPham);
    }

    public static void edit(DTO_LoaiSanPham loaiSanPham) {
        if (!check(loaiSanPham)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_LoaiSanPham.edit(loaiSanPham);
        }
    }

    public static ArrayList<DTO_LoaiSanPham> select() {
        ResultSet rs = DAL_LoaiSanPham.select();
        ArrayList<DTO_LoaiSanPham> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_LoaiSanPham loaiSanPham = new DTO_LoaiSanPham();
                loaiSanPham.setMaLoaiSanPham(rs.getString("MaLoaiSanPham"));
                loaiSanPham.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                array.add(loaiSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_LoaiSanPham> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_LoaiSanPham loaiSanPham : array) {
            Object obj[] = new Object[2];
            obj[0] = loaiSanPham.getMaLoaiSanPham();
            obj[1] = loaiSanPham.getTenLoaiSanPham();
            tbl.getColumnModel().getColumn(2).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(3).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }

    public static void loadTenLoaiPhieuNhap() {
        ResultSet rs = DAL_LoaiSanPham.select();
        ArrayList<String> array = new ArrayList<>();
        try {
            while (rs.next()) {
                array.add(rs.getString("TenLoaiSanPham"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (array.size() == 0) {
            GUI_dal_PhieuNhap.str_1 = null;
            GUI_dal_PhieuNhap.str_2 = null;
            GUI_dal_PhieuNhap.str_3 = null;
        } else if (array.size() == 1) {
            GUI_dal_PhieuNhap.str_1 = array.get(0);
            GUI_dal_PhieuNhap.str_2 = null;
            GUI_dal_PhieuNhap.str_3 = null;
        } else if (array.size() == 2) {
            GUI_dal_PhieuNhap.str_1 = array.get(0);
            GUI_dal_PhieuNhap.str_2 = array.get(1);
            GUI_dal_PhieuNhap.str_3 = null;
        } else if (array.size() >= 3) {
            GUI_dal_PhieuNhap.str_1 = array.get(0);
            GUI_dal_PhieuNhap.str_2 = array.get(1);
            GUI_dal_PhieuNhap.str_3 = array.get(2);
        }
    }
    
    public static void loadTenLoaiDichVu() {
        ResultSet rs = DAL_LoaiSanPham.select();
        ArrayList<String> array = new ArrayList<>();
        try {
            while (rs.next()) {
                array.add(rs.getString("TenLoaiSanPham"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (array.size() == 0) {
            GUI_dal_ThongTinDichVu.str_1 = null;
            GUI_dal_ThongTinDichVu.str_2 = null;
            GUI_dal_ThongTinDichVu.str_3 = null;
        } else if (array.size() == 1) {
            GUI_dal_ThongTinDichVu.str_1 = array.get(0);
            GUI_dal_ThongTinDichVu.str_2 = null;
            GUI_dal_ThongTinDichVu.str_3 = null;
        } else if (array.size() == 2) {
            GUI_dal_ThongTinDichVu.str_1 = array.get(0);
            GUI_dal_ThongTinDichVu.str_2 = array.get(1);
            GUI_dal_ThongTinDichVu.str_3 = null;
        } else if (array.size() >= 3) {
            GUI_dal_ThongTinDichVu.str_1 = array.get(0);
            GUI_dal_ThongTinDichVu.str_2 = array.get(1);
            GUI_dal_ThongTinDichVu.str_3 = array.get(2);
        }
    }
}
