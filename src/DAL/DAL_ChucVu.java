/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DTO_LoaiSanPham;
import HELPER.HELPER_ConnectSQL;
import java.sql.*;

/**
 *
 * @author Trung Thanh
 */
public class DAL_ChucVu {

    public static ResultSet select() {
        String sqlSelect = "SELECT * FROM ChucVu";
        return HELPER_ConnectSQL.executeQuery(sqlSelect);
    }
}
