/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_LoaiSanPham;
import DAL.DAL_SanPham;
import DTO.DTO_LoaiSanPham;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Trung Thanh
 */
public class BLL_SanPham {

    public static boolean check(DTO_SanPham sanPham) {
        if (sanPham.getMaSanPham().isEmpty() || sanPham.getTenSanPham().isEmpty() || sanPham.getMaLoaiSanPham().isEmpty() || sanPham.getDonViTinh().isEmpty() || sanPham.getGiaBan() == 0 || sanPham.getNgayTao() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_SanPham sanPham) {
        if (!check(sanPham)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_SanPham.add(sanPham);
        }
    }

    public static void delete(String maSanPham) {
        DAL_SanPham.delete(maSanPham);
    }

    public static void edit(DTO_SanPham sanPham) {
        if (!check(sanPham)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_SanPham.edit(sanPham);
        }
    }

    public static ArrayList<DTO_SanPham> select() {
        ResultSet rs = DAL_SanPham.select();
        ArrayList<DTO_SanPham> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_SanPham sanPham = new DTO_SanPham();
                sanPham.setMaSanPham(rs.getString("MaSanPham"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setMaLoaiSanPham(rs.getString("MaLoaiSanPham"));
                sanPham.setDonViTinh(rs.getString("DonViTinh"));
                sanPham.setGiaBan(rs.getInt("GiaBan"));
                sanPham.setNgayTao(rs.getTimestamp("NgayTao"));
                array.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<DTO_SanPham> select(String maLoaiSanPham) {
        ResultSet rs = DAL_SanPham.select(maLoaiSanPham);
        ArrayList<DTO_SanPham> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_SanPham sanPham = new DTO_SanPham();
                sanPham.setMaSanPham(rs.getString("MaSanPham"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setMaLoaiSanPham(rs.getString("MaLoaiSanPham"));
                sanPham.setDonViTinh(rs.getString("DonViTinh"));
                sanPham.setGiaBan(rs.getInt("GiaBan"));
                sanPham.setNgayTao(rs.getTimestamp("NgayTao"));
                array.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_SanPham> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_SanPham sanPham : array) {
            Object obj[] = new Object[6];
            obj[0] = sanPham.getMaSanPham();
            obj[1] = sanPham.getTenSanPham();
            obj[2] = BLL_MaTenLoai.findTenLoaiSanPham(sanPham.getMaLoaiSanPham());
            obj[3] = sanPham.getDonViTinh();
            obj[4] = sanPham.getGiaBan() + "K";
            obj[5] = HELPER_ChuyenDoi.getNgayString("dd-MM-yy HH:mm", sanPham.getNgayTao());
            tbl.getColumnModel().getColumn(6).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(7).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }

    public static void loadSanPham(ArrayList<DTO_SanPham> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_SanPham sanPham : array) {
            Object obj[] = new Object[4];
            obj[0] = sanPham.getMaSanPham();
            obj[1] = sanPham.getTenSanPham();
            obj[2] = 0;
            obj[3] = 0 + "K";
            tbl.getColumnModel().getColumn(4).setCellRenderer(new HELPER_SetIcon.iconAdd());
            tblModel.addRow(obj);
        }
    }

    public static void loadKhoDichVu(ArrayList<DTO_SanPham> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_SanPham sanPham : array) {
            Object obj[] = new Object[3];
            obj[0] = sanPham.getMaSanPham();
            obj[1] = sanPham.getTenSanPham();
            obj[2] = sanPham.getGiaBan() + "K";
            tbl.getColumnModel().getColumn(3).setCellRenderer(new HELPER_SetIcon.iconAdd());
            tblModel.addRow(obj);
        }
    }
}
