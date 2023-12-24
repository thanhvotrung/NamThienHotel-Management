/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_TaiKhoan;
import DTO.DTO_LoaiPhong;
import DTO.DTO_TaiKhoan;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;
//import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_TaiKhoan {

    public static boolean check(DTO_TaiKhoan taiKhoan) {
        if (taiKhoan.getMaTaiKhoan().isEmpty() || taiKhoan.getMaNhanVien().isEmpty() || taiKhoan.getTenDangNhap().isEmpty() || taiKhoan.getMatKhau().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateMail(String mail) {
        String mailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(mailRegex);
        if (pattern.matcher(mail).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void add(DTO_TaiKhoan taiKhoan) {
        if (!check(taiKhoan)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else if (!validateMail(taiKhoan.getTenDangNhap())) {
            JOptionPane.showMessageDialog(null, "Lỗi Định Dạng ???");
        } else {
            DAL_TaiKhoan.add(taiKhoan);
        }
    }

    public static void delete(String maNhanVien) {
        DAL_TaiKhoan.delete(maNhanVien);
    }

    public static void edit(String tenDangNhap, String matKhau, String maTaiKhoan) {
        if (tenDangNhap.isEmpty() || matKhau.isEmpty() || maTaiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else if (!validateMail(tenDangNhap)) {
            JOptionPane.showMessageDialog(null, "Lỗi Định Dạng ???");
        } else {
            DAL_TaiKhoan.edit(tenDangNhap, matKhau, maTaiKhoan);
        }
    }

    public static void editMatKhau(String tenDangNhap, String matKhau) {
        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_TaiKhoan.editMatKhau(matKhau, tenDangNhap);
        }
    }

    public static ArrayList<DTO_TaiKhoan> select(String tenDangNhap, String matKhau) {
        ResultSet rs = DAL_TaiKhoan.selectTaiKhoan(tenDangNhap, matKhau);
        ArrayList<DTO_TaiKhoan> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_TaiKhoan taiKhoan = new DTO_TaiKhoan();
                taiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                taiKhoan.setMaNhanVien(rs.getString("MaNhanVien"));
                taiKhoan.setTenDangNhap(rs.getString("TenDangNhap"));
                taiKhoan.setMatKhau(rs.getString("MatKhau"));
                taiKhoan.setCheckDangNhap(rs.getInt("CheckDangNhap"));
                array.add(taiKhoan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<DTO_TaiKhoan> select(String maNhanvien) {
        ResultSet rs = DAL_TaiKhoan.select(maNhanvien);
        ArrayList<DTO_TaiKhoan> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_TaiKhoan taiKhoan = new DTO_TaiKhoan();
                taiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                taiKhoan.setMaNhanVien(rs.getString("MaNhanVien"));
                taiKhoan.setTenDangNhap(rs.getString("TenDangNhap"));
                taiKhoan.setMatKhau(rs.getString("MatKhau"));
                taiKhoan.setCheckDangNhap(rs.getInt("CheckDangNhap"));
                array.add(taiKhoan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static String selectMaNhanVien(String tenDangNhap) {
        ResultSet rs = DAL_TaiKhoan.selectMaNhanVien(tenDangNhap);
        try {
            while (rs.next()) {
                return rs.getString("MaNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void load(ArrayList<DTO_TaiKhoan> array, JLabel lblMaTaiKhoan, JTextField txtTenDangNhap, JPasswordField psdMatKhau) {
        for (DTO_TaiKhoan taiKhoan : array) {
            lblMaTaiKhoan.setText(taiKhoan.getMaTaiKhoan());
            txtTenDangNhap.setText(taiKhoan.getTenDangNhap());
            psdMatKhau.setText(taiKhoan.getMatKhau());
        }
    }
}
