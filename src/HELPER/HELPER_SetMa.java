/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HELPER;

import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class HELPER_SetMa {

    public static String setMaDateTime(String setMa) {
        return setMa + HELPER_ChuyenDoi.getTimeNow("yyMMddHHmmss");
    }
    
    public static String setMaCount(String setMa, ResultSet selectCount) {
        String primaryKey = setMa;
        try {
            ResultSet rs = selectCount;
            int rowCount = 0;
            while (rs.next()) {
                rowCount = rs.getInt(1);
                if (rowCount < 9) {
                    primaryKey = primaryKey + "00" + (rowCount + 1);
                } else if (rowCount >= 9) {
                    primaryKey = primaryKey + "0" + (rowCount + 1);
                } else if (rowCount >= 99) {
                    primaryKey = primaryKey + (rowCount + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKey;
    }
    
    public static String setMaDateTime(String setMa, ResultSet selectCount) {
        String primaryKey = setMa;
        try {
            primaryKey = primaryKey + HELPER_ChuyenDoi.getTimeNow("yyMMdd");
            ResultSet rs = selectCount;
            int rowCount = 0;
            while (rs.next()) {
                rowCount = rs.getInt(1);
                if (rowCount < 9) {
                    primaryKey = primaryKey + "00" + (rowCount + 1);
                } else if (rowCount >= 9) {
                    primaryKey = primaryKey + "0" + (rowCount + 1);
                } else if (rowCount >= 99) {
                    primaryKey = primaryKey + (rowCount + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKey;
    }

    public static int setSoLuong(ResultSet selectCount, String data) {
        int total = 0;
        try {
            ResultSet rs = selectCount;
            while (rs.next()) {
                total = rs.getInt(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
