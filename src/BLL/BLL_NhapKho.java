/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_NhapKho;
import DTO.DTO_NhapKho;
import DTO.DTO_NhapKho;
import HELPER.HELPER_ChuyenDoi;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_NhapKho {

    public static boolean check(DTO_NhapKho nhapKho) {
        if (nhapKho.getMaNhapKho().isEmpty() || nhapKho.getMaNhanVien().isEmpty() || nhapKho.getNgayTao() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void add(DTO_NhapKho nhapKho) {
        if (!check(nhapKho)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_NhapKho.add(nhapKho);
        }
    }

    public static void delete(String maNhapKho) {
        DAL_NhapKho.delete(maNhapKho);
    }

    public static ArrayList<DTO_NhapKho> search(String dateTuNgay, String dateDenNgay, int index) {
        ResultSet rs = DAL_NhapKho.search(dateTuNgay, dateDenNgay, index);
        ArrayList<DTO_NhapKho> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_NhapKho nhapKho = new DTO_NhapKho();
                nhapKho.setMaNhapKho(rs.getString("MaNhapKho"));
                nhapKho.setMaNhanVien(rs.getString("MaNhanVien"));
                nhapKho.setNgayTao(rs.getTimestamp("NgayTao"));
                nhapKho.setGhiChu(rs.getString("GhiChu"));
                array.add(nhapKho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadNhapKho(ArrayList<DTO_NhapKho> array, JLabel lblMaPhieu, JLabel lblMaNhanVien, JLabel lblNgayTao) {
        for (DTO_NhapKho nhapKho : array) {
            lblMaPhieu.setText(nhapKho.getMaNhapKho());
            lblMaNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(nhapKho.getMaNhanVien()));
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy HH:mm", nhapKho.getNgayTao()));
        }
    }

    public static void loadChiTietNhapKho(ArrayList<DTO_NhapKho> array, JLabel lblMaPhieu, JLabel lblMaNhanVien, JLabel lblNgayTao, JTextField txtGhiChu) {
        for (DTO_NhapKho nhapKho : array) {
            lblMaPhieu.setText(nhapKho.getMaNhapKho());
            lblMaNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(nhapKho.getMaNhanVien()));
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy HH:mm", nhapKho.getNgayTao()));
            txtGhiChu.setText(nhapKho.getGhiChu());
        }
    }

    public static int countSearch(String tuNgay, String denNgay) {
        ResultSet rs = DAL_NhapKho.countSearch(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int money(String tuNgay, String denNgay) {
        ResultSet rs = DAL_NhapKho.money(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
