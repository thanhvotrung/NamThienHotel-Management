/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_HoaDon;
import DTO.DTO_HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CherryCe
 */
public class BLL_HoaDon {

    public static boolean check(DTO_HoaDon hoaDon) {
        if (hoaDon.getMaHoaDon().isEmpty() || hoaDon.getMaPhieuThue().isEmpty() || hoaDon.getNgayTao() == null || hoaDon.getMaNhanVien().isEmpty() || hoaDon.getTienPhong() == 0 || hoaDon.getMaPhuongThuc().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void addHoaDon(DTO_HoaDon hoaDon) {
        if (!check(hoaDon)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_HoaDon.addHoaDon(hoaDon);
        }
    }

    public static String findMaPhieuThue(String maHoaDon) {
        ResultSet rs = DAL_HoaDon.findMaPhieuThue(maHoaDon);
        try {
            while (rs.next()) {
                return rs.getString("MaPhieuThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
