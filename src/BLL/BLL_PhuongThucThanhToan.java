/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_PhuongThucThanhToan;
import DTO.DTO_PhuongThucThanhToan;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
public class BLL_PhuongThucThanhToan {

    public static ArrayList<DTO_PhuongThucThanhToan> select() {
        ResultSet rs = DAL_PhuongThucThanhToan.select();
        ArrayList<DTO_PhuongThucThanhToan> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_PhuongThucThanhToan phuongThucThanhToan = new DTO_PhuongThucThanhToan();
                phuongThucThanhToan.setMaPhuongThuc(rs.getString("MaPhuongThuc"));
                phuongThucThanhToan.setTenPhuongThuc(rs.getString("TenPhuongThuc"));
                array.add(phuongThucThanhToan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_PhuongThucThanhToan> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_PhuongThucThanhToan phuongThuc : array) {
            Object obj = phuongThuc.getTenPhuongThuc();
            cboModel.addElement(obj);
        }
    }
}
