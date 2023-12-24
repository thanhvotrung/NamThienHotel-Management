/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_ChucVu;
import DTO.DTO_ChucVu;
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
public class BLL_ChucVu {

    public static ArrayList<DTO_ChucVu> select() {
        ResultSet rs = DAL_ChucVu.select();
        ArrayList<DTO_ChucVu> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ChucVu chucVu = new DTO_ChucVu();
                chucVu.setMaChucVu(rs.getString("MaChucVu"));
                chucVu.setTenChucVu(rs.getString("TenChucVu"));
                array.add(chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_ChucVu> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_ChucVu chucVu : array) {
            Object obj = chucVu.getTenChucVu();
            cboModel.addElement(obj);
        }
    }
}
