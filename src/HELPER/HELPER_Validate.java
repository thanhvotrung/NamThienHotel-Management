/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HELPER;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author CherryCe
 */
public class HELPER_Validate {

    public static void validateString(javax.swing.JTextField txt) {
        txt.setText(txt.getText().replaceAll("[1234567890[`~!@#$%^&*()-_=+]]", ""));
    }

    public static void validateNumber(javax.swing.JTextField txt) {
        txt.setText(txt.getText().replaceAll("[abcdefghijklmnopqrstuvwxyz[`~!@#$%^&*()_=]]", ""));
    }

    public static void setTextLimited(javax.swing.JTextField txt, int limited) {
        if (txt.getText().length() > limited) {
            txt.setText(txt.getText().substring(0, limited));
        }
    }

    public static boolean alreayExits(ResultSet resultSet, String data, String value) {
        ResultSet rs = resultSet;
        ArrayList<String> array = new ArrayList<>();
        try {
            while (rs.next()) {
                array.add(rs.getString(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < array.size(); i++) {
            if (value.equals(array.get(i))) {
                return false;
            }
        }
        return true;
    }
}
