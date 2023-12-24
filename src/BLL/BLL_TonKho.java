/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_ChiTietNhapKho;
import DAL.DAL_SanPham;
import DAL.DAL_TonKho;
import DTO.DTO_ChiTietDichVu;
import DTO.DTO_ChiTietNhapKho;
import DTO.DTO_SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_TonKho {

    public static ArrayList<String> maSanPham = new ArrayList<String>();
    public static ArrayList<String> tenSanPham = new ArrayList<String>();
    public static ArrayList<String> donViTinh = new ArrayList<String>();
    public static ArrayList<Integer> tonDauKi = new ArrayList<Integer>();
    public static ArrayList<Integer> soLuongNhap = new ArrayList<Integer>();
    public static ArrayList<Integer> tongTienNhap = new ArrayList<Integer>();
    public static ArrayList<Integer> soLuongBan = new ArrayList<Integer>();
    public static ArrayList<Integer> tongTienBan = new ArrayList<Integer>();
    public static ArrayList<Integer> tonKho = new ArrayList<Integer>();

    public static void selectSanPham() {
        ResultSet rs = DAL_TonKho.selectSanPham();
        maSanPham.clear();
        try {
            while (rs.next()) {
                maSanPham.add(rs.getString("MaSanPham"));
                tenSanPham.add(rs.getString("TenSanPham"));
                donViTinh.add(rs.getString("DonViTinh"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectNhapKho(String dateTugay, String dateDenNgay) {
        soLuongNhap.clear();
        tongTienNhap.clear();
        for (int i = 0; i < maSanPham.size(); i++) {
            ResultSet rs = DAL_TonKho.selectNhapKho(dateTugay, dateDenNgay, maSanPham.get(i));
            try {
                if (rs.next()) {
                    soLuongNhap.add(rs.getInt("SoLuongNhap"));
                    tongTienNhap.add(rs.getInt("TongTienNhap"));
                } else {
                    soLuongNhap.add(0);
                    tongTienNhap.add(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectDichVu(String dateTuNgay, String dateDenNgay) {
        soLuongBan.clear();
        tongTienBan.clear();
        for (int i = 0; i < maSanPham.size(); i++) {
            ResultSet rs = DAL_TonKho.selectDichVu(dateTuNgay, dateDenNgay, maSanPham.get(i));
            try {
                if (rs.next()) {
                    soLuongBan.add(rs.getInt("SoLuongBan"));
                    tongTienBan.add(rs.getInt("TongTienBan"));
                } else {
                    soLuongBan.add(0);
                    tongTienBan.add(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectTonDauKi(String dateTuNgay) {
        tonDauKi.clear();
        for (int i = 0; i < maSanPham.size(); i++) {
            ResultSet rsSoLuongNhap = DAL_TonKho.selectSoLuongNhap(dateTuNgay, maSanPham.get(i));
            ResultSet rsSoLuongBan = DAL_TonKho.selectSoLuongBan(dateTuNgay, maSanPham.get(i));
            try {
                if (rsSoLuongNhap.next() && rsSoLuongBan.next()) {
                    tonDauKi.add(rsSoLuongNhap.getInt("SoLuongNhap") - rsSoLuongBan.getInt("SoLuongBan"));
                } else if (rsSoLuongNhap.next() && !rsSoLuongBan.next()) {
                    tonDauKi.add(rsSoLuongNhap.getInt("SoLuongNhap") - 0);
                } else if (!rsSoLuongNhap.next() && rsSoLuongBan.next()) {
                    tonDauKi.add(0 - rsSoLuongBan.getInt("SoLuongBan"));
                } else {
                    tonDauKi.add(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void selectTonKho() {
        tonKho.clear();
        for (int i = 0; i < maSanPham.size(); i++) {
            tonKho.add(tonDauKi.get(i) + soLuongNhap.get(i) - soLuongBan.get(i));
        }
    }

    public static void load(JTable tbl) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (int i = 0; i < maSanPham.size(); i++) {
            Object obj[] = new Object[9];
            obj[0] = maSanPham.get(i);
            obj[1] = tenSanPham.get(i);
            obj[2] = donViTinh.get(i);
            obj[3] = tonDauKi.get(i);
            obj[4] = soLuongNhap.get(i);
            obj[5] = tongTienNhap.get(i) + "K";
            obj[6] = soLuongBan.get(i);
            obj[7] = tongTienBan.get(i) + "K";
            obj[8] = tonKho.get(i);
            tblModel.addRow(obj);
        }
    }
}
