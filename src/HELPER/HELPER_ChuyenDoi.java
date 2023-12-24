/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HELPER;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CherryCe
 */
public class HELPER_ChuyenDoi {

    public static String convertDate(String input, String output, String value) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(input);
            SimpleDateFormat outputFormat = new SimpleDateFormat(output);
            Date date = null;
            String requiredDate = null;
            date = inputFormat.parse(value);
            requiredDate = outputFormat.format(date);
            return requiredDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeNow(String time) {
        return new SimpleDateFormat(time).format(new Date());
    }

    public static String getNgayString(String data, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(data);
        return dateFormat.format(date);
    }

    public static Date getNgayDate(String data, String ngay) {
        try {
            return new SimpleDateFormat(data).parse(ngay);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String getSoString(double so) {
        return NumberFormat.getNumberInstance().format(so);
    }

    public static String getSoString(int so) {
        return NumberFormat.getNumberInstance().format(so);
    }

    public static int getSoInt(String so) {
        try {
            return NumberFormat.getNumberInstance().parse(so).intValue();
        } catch (ParseException ex) {
            return 0;
        }
    }

    public static double getSoDouble(String so) {
        try {
            return NumberFormat.getNumberInstance().parse(so).doubleValue();
        } catch (ParseException ex) {
            return 0;
        }
    }
}
