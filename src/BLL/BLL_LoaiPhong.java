/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_LoaiPhong;
import DTO.DTO_LoaiPhong;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_LoaiPhong {

    public static boolean check(DTO_LoaiPhong loaiPhong) {
        if (loaiPhong.getMaPhong().isEmpty() || loaiPhong.getTenPhong().isEmpty() || loaiPhong.getSoGiuong() == 0 || loaiPhong.getSoNguoi() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_LoaiPhong loaiPhong) {
        if (!check(loaiPhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_LoaiPhong.add(loaiPhong);
        }
    }

    public static void delete(String maPhong) {
        DAL_LoaiPhong.delete(maPhong);
    }

    public static void edit(DTO_LoaiPhong loaiPhong) {
        if (!check(loaiPhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_LoaiPhong.edit(loaiPhong);
        }
    }

    public static ArrayList<DTO_LoaiPhong> select() {
        ResultSet rs = DAL_LoaiPhong.select();
        ArrayList<DTO_LoaiPhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_LoaiPhong loaiPhong = new DTO_LoaiPhong();
                loaiPhong.setMaPhong(rs.getString("MaLoaiPhong"));
                loaiPhong.setTenPhong(rs.getString("TenLoaiPhong"));
                loaiPhong.setSoGiuong(rs.getInt("SoGiuong"));
                loaiPhong.setSoNguoi(rs.getInt("SoNguoi"));
                array.add(loaiPhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_LoaiPhong> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_LoaiPhong loaiPhong : array) {
            Object obj[] = new Object[4];
            obj[0] = loaiPhong.getMaPhong();
            obj[1] = loaiPhong.getTenPhong();
            obj[2] = loaiPhong.getSoGiuong();
            obj[3] = loaiPhong.getSoNguoi();
            tbl.getColumnModel().getColumn(4).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(5).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }

    public static int selectSoNguoi(String maLoaiPhong) {
        ResultSet rs = DAL_LoaiPhong.selectSoNguoi(maLoaiPhong);
        try {
            while (rs.next()) {
                return rs.getInt("SoNguoi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
