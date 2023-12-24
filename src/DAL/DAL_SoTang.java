/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_SoTang;
import HELPER.HELPER_ConnectSQL;
import java.sql.ResultSet;

/**
 *
 * @author CherryCe
 */
public class DAL_SoTang {
    public static void add(DTO_SoTang soTang){
        String sqlSelect = "INSERT INTO Tang VALUES (?, ?)";
        HELPER_ConnectSQL.executeUpdate(sqlSelect, soTang.getMaTang(), soTang.getTenTang());
    }
    
    public static void delete(String maTang){
        String sqlDelete = "DELETE FROM Tang WHERE MaTang = ?";
        HELPER_ConnectSQL.executeUpdate(sqlDelete, maTang);
    }
    
    public static void edit(DTO_SoTang soTang){
        String sqlUpdate = "UPDATE Tang SET TenTang = ? WHERE MaTang = ?";
        HELPER_ConnectSQL.executeUpdate(sqlUpdate, soTang.getTenTang(), soTang.getMaTang());
    }
    
    public static ResultSet select(){
        String sqlSelect = "SELECT * FROM Tang ORDER BY MaTang";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
}
