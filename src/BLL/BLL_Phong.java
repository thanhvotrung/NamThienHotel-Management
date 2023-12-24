/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_Phong;
import DTO.DTO_Phong;
import DTO.DTO_ThuePhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_Phong {
    
    public static boolean check(DTO_Phong phong) {
        if (phong.getMaPhong().isEmpty() || phong.getTenPhong().isEmpty() || phong.getMaTang().isEmpty() || phong.getMaLoaiPhong().isEmpty() || phong.getMaTrangThaiPhong().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static void add(DTO_Phong phong) {
        if (!check(phong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_Phong.add(phong);
        }
    }
    
    public static void delete(String maPhong) {
        DAL_Phong.delete(maPhong);
    }
    
    public static void edit(DTO_Phong phong) {
        if (!check(phong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_Phong.edit(phong);
        }
    }
    
    public static ArrayList<DTO_Phong> select() {
        ResultSet rs = DAL_Phong.select();
        ArrayList<DTO_Phong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_Phong phong = new DTO_Phong();
                phong.setMaPhong(rs.getString("MaPhong"));
                phong.setTenPhong(rs.getString("TenPhong"));
                phong.setMaTang(rs.getString("MaTang"));
                phong.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                phong.setMaTrangThaiPhong(rs.getString("MaTrangThaiPhong"));
                array.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    
    public static ArrayList<DTO_Phong> searchChonPhong(String maTang, int index) {
        ResultSet rs = DAL_Phong.searchChonPhong(maTang, index);
        ArrayList<DTO_Phong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_Phong phong = new DTO_Phong();
                phong.setMaPhong(rs.getString("MaPhong"));
                phong.setMaTrangThaiPhong(rs.getString("MaTrangThaiPhong"));
                array.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    
    public static ArrayList<DTO_Phong> searchChuyenPhong(String maTang, int index) {
        ResultSet rs = DAL_Phong.searchChuyenPhong(maTang, index);
        ArrayList<DTO_Phong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_Phong phong = new DTO_Phong();
                phong.setMaPhong(rs.getString("MaPhong"));
                phong.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                array.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    
    public static ArrayList<DTO_ThuePhong> searchSoPhong(String maTang, int index) {
        ResultSet rs = DAL_Phong.searchSoPhong(maTang, index);
        ArrayList<DTO_ThuePhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ThuePhong thuePhong = new DTO_ThuePhong();
                thuePhong.setMaPhong(rs.getString("MaPhong"));
                thuePhong.setNgayDen(rs.getTimestamp("NgayDen"));
                thuePhong.setNgayDi(rs.getTimestamp("NgayDi"));
                array.add(thuePhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    
    public static void load(ArrayList<DTO_Phong> array, JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (DTO_Phong phong : array) {
            Object obj[] = new Object[6];
            obj[0] = array;
            obj[1] = phong.getMaPhong();
            obj[2] = phong.getTenPhong();
            obj[3] = BLL_MaTenLoai.findTenTang(phong.getMaTang());
            obj[4] = BLL_MaTenLoai.findTenLoaiPhong(phong.getMaLoaiPhong());
            obj[5] = BLL_MaTenLoai.findTenTrangThaiPhong(phong.getMaTrangThaiPhong());
            tbl.getColumnModel().getColumn(6).setCellRenderer(new HELPER_SetIcon.iconEdit());
            tbl.getColumnModel().getColumn(7).setCellRenderer(new HELPER_SetIcon.iconDelete());
            tblModel.addRow(obj);
        }
    }
    
    public static void loadChonPhong(ArrayList<DTO_Phong> array, JLabel lblSoPhong, JLabel lblTrangThai) {
        for (DTO_Phong phong : array) {
            lblSoPhong.setText(BLL_MaTenLoai.findTenPhong(phong.getMaPhong()));
            lblTrangThai.setText(BLL_MaTenLoai.findTenTrangThaiPhong(phong.getMaTrangThaiPhong()));
        }
    }
    
    public static void loadChuyenPhong(ArrayList<DTO_Phong> array, JLabel lblSoPhong, JLabel lblLoaiPhong) {
        for (DTO_Phong phong : array) {
            lblSoPhong.setText(BLL_MaTenLoai.findTenPhong(phong.getMaPhong()));
            lblLoaiPhong.setText(BLL_MaTenLoai.findTenLoaiPhong(phong.getMaLoaiPhong()));
        }
    }
    
    public static void loadSoPhong(ArrayList<DTO_ThuePhong> array, JLabel lblSoPhong, JLabel lblNgayDen, JLabel lblNgayDi) {
        for (DTO_ThuePhong thuePhong : array) {
            lblSoPhong.setText(BLL_MaTenLoai.findTenPhong(thuePhong.getMaPhong()));
            lblNgayDen.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy", thuePhong.getNgayDen()));
            if (thuePhong.getNgayDi() == null) {
                lblNgayDi.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yy"));
            } else {
                lblNgayDi.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy", thuePhong.getNgayDi()));
            }
        }
    }
    
    public static int countSearchChonPhong(String maTang) {
        ResultSet rs = DAL_Phong.countSearchChonPhong(maTang);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static int countSearchChuyenPhong(String maTang) {
        ResultSet rs = DAL_Phong.countSearchChuyenPhong(maTang);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static String selectChuyenPhong(String maTang, int index) {
        ResultSet rs = DAL_Phong.searchChuyenPhong(maTang, index);
        try {
            while (rs.next()) {
                return rs.getString("TenPhong") + " - " + BLL_MaTenLoai.findTenLoaiPhong(rs.getString("MaLoaiPhong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
