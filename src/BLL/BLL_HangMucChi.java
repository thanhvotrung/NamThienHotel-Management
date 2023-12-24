/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_HangMucChi;
import DAL.DAL_Phong;
import DTO.DTO_HangMucChi;
import DTO.DTO_Phong;
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
public class BLL_HangMucChi {

    public static boolean check(DTO_HangMucChi hangMucChi) {
        if (hangMucChi.getMaChi().isEmpty() || hangMucChi.getMucChi().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_HangMucChi hangMucChi) {
        if (!check(hangMucChi)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_HangMucChi.add(hangMucChi);
        }
    }

    public static void delete(String maLoaiTienChi) {
        DAL_HangMucChi.delete(maLoaiTienChi);
    }

    public static void edit(DTO_HangMucChi hangMucChi) {
        if (!check(hangMucChi)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_HangMucChi.edit(hangMucChi);
        }
    }

    public static ArrayList<DTO_HangMucChi> select() {
        ResultSet rs = DAL_HangMucChi.select();
        ArrayList<DTO_HangMucChi> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_HangMucChi hangMucChi = new DTO_HangMucChi();
                hangMucChi.setMaChi(rs.getString("MaLoaiTienChi"));
                hangMucChi.setMucChi(rs.getString("TenLoaiTienChi"));
                array.add(hangMucChi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_HangMucChi> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_HangMucChi hangMucChi : array) {
            Object obj[] = new Object[2];
            obj[0] = hangMucChi.getMaChi();
            obj[1] = hangMucChi.getMucChi();
            tbl.getColumnModel().getColumn(2).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(3).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }
}
