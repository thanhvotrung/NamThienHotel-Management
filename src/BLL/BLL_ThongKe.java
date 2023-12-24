/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_ThongKe;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Trung Thanh
 */
public class BLL_ThongKe {
  
    public static ArrayList<Object[]> TienLoaiPhongKiemTrongNgay(Date date, Date date2)
    {
       ArrayList<Object[]> arrList = new ArrayList<>();
       ResultSet rs = DAL_ThongKe.TienLoaiPhongKiemTrongNgay(date, date2);
        try {
            while(rs.next())
            {
                Object[] obj = new Object[2];
                obj[0] = rs.getString("tenloaiphong");
                obj[1] = rs.getDouble("tongtien");
                arrList.add(obj);
            }
            return arrList;
        } catch (Exception e) {
        }
        return null;
    }
  
    public static ArrayList NguonTienTrongNgay(Date date, Date date2)
    {
        ArrayList<Double> arrList = new ArrayList<>();
        try {
            ResultSet rs = DAL_ThongKe.NguonTienTrongNgay(date, date2);
            if(rs.next())
            {
                arrList.add(rs.getDouble("TienPhong"));
                arrList.add(rs.getDouble("TienDichVu"));
                arrList.add(rs.getDouble("TienCoc"));
                arrList.add(rs.getDouble("DoanhThu"));
                arrList.add(rs.getDouble("PhiChi"));
                arrList.add(rs.getDouble("PhiNhapKho"));
                arrList.add(rs.getDouble("ConLai"));
                return arrList;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
        
    public static ArrayList SLKhachVaoTheoGio(Date dateTu, Date dateDen)
    {
        ArrayList<Object[]> arrList = new ArrayList<>();
        ResultSet rs = DAL_ThongKe.SLKhachVaoTheoGio(dateTu, dateDen);
        try {
            while(rs.next())
            {
                String hour = rs.getString("Hour");
                double count = rs.getDouble("count");
                
                arrList.add(new Object[]{hour, count});
            } 
        } catch (SQLException ex) {
            Logger.getLogger(BLL_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrList;
    }
        
    public static ArrayList ThongKeTinhTrangPhong(Date date)
    {
        ResultSet rs = DAL_ThongKe.ThongKeTinhTrangPhong(date);
        ArrayList<Object> arrList = new ArrayList<>();
        try {
            if(rs.next())
            {
                
                arrList.add(rs.getInt("PhongTrong"));
                arrList.add(rs.getInt("DangO"));
                arrList.add(rs.getInt("DaTra"));
                arrList.add(rs.getInt("DatTruoc"));
            }
        } catch (Exception e) {
        }
        return arrList;
    }
    
    public static void UpdateThongKe(Date date)
    {
        Timer timer = new Timer(5000, (arg0) -> {

            DAL_ThongKe.UpdateThongKe(date);
        });
        timer.start();
    }
    
    
    public static ArrayList ThongKeTinhTrangLoaiPhong(Date date)
    {
        ResultSet rsMaLoaiPhong = DAL_ThongKe.getMaLoaiPhong();
        
        ArrayList<Object[]> arrList = new ArrayList<>();
        try {
            
            while(rsMaLoaiPhong.next())
            {
                ResultSet rs = DAL_ThongKe.ThongKeTinhTrangLoaiPhong(date, rsMaLoaiPhong.getString("MaLoaiPhong"));
                if(rs.next())
            {
                Object[] object = new Object[5];
                object[0] = rs.getString("TenLoaiPhong");
                object[1] = rs.getInt("PhongTrong");
                object[2] =rs.getInt("DangO");
                object[3] =rs.getInt("DaTra");
                object[4] =rs.getInt("DatTruoc");
                arrList.add(object);

            }  
            }
        } catch (Exception e) {
        }
        return arrList;
    }
    
    public static void UpdateThongKeLoaiPhong(Date date)
    {
        Timer timer = new Timer(5000, (arg0) -> {

            ResultSet rsMaLoaiPhong = DAL_ThongKe.getMaLoaiPhong();
            try {
            while(rsMaLoaiPhong.next())
            {
                DAL_ThongKe.UpdateThongKeLoaiPhong(date, rsMaLoaiPhong.getString("MaLoaiPhong"));
            }
            } catch (Exception e) {
            }
        });
        timer.start();
    }
    
    
    //Chuyển đổi số Double <-> String 10.000.000
     public static String SoStringGiaTien(double so){
        return NumberFormat.getNumberInstance().format(so);
    }
     
    public static Color randomColor()
    {
        Random random = new Random();
    final float hue = random.nextFloat();
    // Saturation between 0.1 and 0.3
    final float saturation = (random.nextInt(2000) + 1000) / 10000f;
    final float luminance = 0.9f;
    final Color color = Color.getHSBColor(hue, saturation, luminance);
    return color;
    }
}
