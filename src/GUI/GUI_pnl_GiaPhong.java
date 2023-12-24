/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_MaTenLoai;
import DTO.DTO_LoaiPhong;
import GUI.GUI_pnl_ChiTietPhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_Validate;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_GiaPhong extends javax.swing.JPanel {

    public int rowIndex;
    public int columnIndex;
    public long diffInDay;
    public long diffInHours;
    public long diffInMinutes;
    public String path;
    public String setPrice;
    public static String singleRoom_Day_Rate = "src/DOCUMENT/SingleRoom_Day_Rate.xlsx";
    public static String singleRoom_Hour_Rate = "src/DOCUMENT/SingleRoom_Hour_Rate.xlsx";
    public static String doubleRoom_Day_Rate = "src/DOCUMENT/DoubleRoom_Day_Rate.xlsx";
    public static String doubleRoom_Hour_Rate = "src/DOCUMENT/DoubleRoom_Hour_Rate.xlsx";
    public static String tripleRoom_Day_Rate = "src/DOCUMENT/TripleRoom_Day_Rate.xlsx";
    public static String tripleRoom_Hour_Rate = "src/DOCUMENT/TripleRoom_Hour_Rate.xlsx";
    public static String quadraRoom_Day_Rate = "src/DOCUMENT/QuadraRoom_Day_Rate.xlsx";
    public static String quadraRoom_Hour_Rate = "src/DOCUMENT/QuadraRoom_Hour_Rate.xlsx";

    /**
     * Creates new form GUI_pnlSoDoPhong
     */
    public GUI_pnl_GiaPhong() {
        initComponents();
        loadTenLoaiPhong();
        load();
        validation();
    }

    public void validation() {
        if (!GUI_frm_Menu.auThenTiCaTion()) {
            lblCapNhat.setVisible(false);
        }
        return;
    }

    public void load() {
        dateTuNgay.getDateEditor().setEnabled(false);
        dateDenNgay.getDateEditor().setEnabled(false);
        String dateTimeNgayDen = HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm");
        String dateTimeNgayDi = HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm");
        dateTuNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDen));
        dateDenNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDi));
    }

    public void loadTenLoaiPhong() {
        ArrayList<DTO_LoaiPhong> array = BLL_MaTenLoai.selectTenLoaiPhong();
        BLL_MaTenLoai.loadTenLoaiPhong(array, cboLoaiPhong);
        setPrice = String.valueOf(cboLoaiGia.getSelectedItem());
    }

    public void readExcel(String url) {
        rowIndex = -1;
        columnIndex = -1;
        try {
            FileInputStream file = new FileInputStream(url);
            Workbook workbook = new XSSFWorkbook(file);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Sheet> sheets = workbook.sheetIterator();
            while (sheets.hasNext()) {
                Sheet sh = sheets.next();
                Iterator<Row> iterator = sh.iterator();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    Iterator<Cell> cellIterator = row.iterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = dataFormatter.formatCellValue(cell);
                        columnIndex++;
                        if (columnIndex > 24) {
                            columnIndex = 0;
                            rowIndex++;
                        }
                        if (rowIndex == -1) {
                            tblGiaPhong.getColumnModel().getColumn(columnIndex).setHeaderValue(cellValue);
                        }
                        if (rowIndex != -1) {
                            tblGiaPhong.setValueAt(cellValue, rowIndex, columnIndex);
                        }
                    }
                }
            }
            workbook.close();
            path = url;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeExcel(String url) {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Sheet");
            Row rowCol = sheet.createRow(0);

            for (int i = 0; i < tblGiaPhong.getColumnCount(); i++) {
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(tblGiaPhong.getColumnName(i));
            }

            for (int j = 0; j < tblGiaPhong.getRowCount(); j++) {
                Row row = sheet.createRow(j + 1);
                for (int k = 0; k < tblGiaPhong.getColumnCount(); k++) {
                    Cell cell = row.createCell(k);
                    if (tblGiaPhong.getValueAt(j, k) != null && !tblGiaPhong.getValueAt(j, k).toString().isEmpty()) {
                        cell.setCellValue(Integer.parseInt(tblGiaPhong.getValueAt(j, k).toString()));
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(url);
            wb.write(out);
            wb.close();
            out.close();
            JOptionPane.showMessageDialog(this, "Cập Nhật Hoàn Tất !!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Định Dạng ???");
        }
    }

    public void setRoomRate() {
        if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đơn") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Giờ")) {
            readExcel(singleRoom_Hour_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đơn") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Ngày")) {
            readExcel(singleRoom_Day_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Nhỏ") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Giờ")) {
            readExcel(doubleRoom_Hour_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Nhỏ") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Ngày")) {
            readExcel(doubleRoom_Day_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Lớn Nhỏ") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Giờ")) {
            readExcel(tripleRoom_Hour_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Lớn Nhỏ") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Ngày")) {
            readExcel(tripleRoom_Day_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Lớn") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Giờ")) {
            readExcel(quadraRoom_Hour_Rate);
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Lớn") && String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Ngày")) {
            readExcel(quadraRoom_Day_Rate);
        }
    }

    public void setChuThichGia(String green, String blue, String yellow, String pink, String orange, String gray) {
        lblColorGreen.setText(green);
        lblColorBlue.setText(blue);
        lblColorYellow.setText(yellow);
        lblColorPink.setText(pink);
        lblColorOrange.setText(orange);
        lblColorGray.setText(gray);
    }

    public void setThoiGian_GiaTien(int row, int column) {
        int price = 0;
        String filePath = null;
        String setPrice = null;
        String dateNgayDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate());
        String dateNgayDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate());
        if (dateNgayDen.equals(dateNgayDi)) {
            setPrice = "Giá Giờ";
        } else {
            setPrice = "Giá Ngày";
        }
        if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đơn") && setPrice.equals("Giá Giờ")) {
            filePath = singleRoom_Hour_Rate;
            price = 0;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đơn") && setPrice.equals("Giá Ngày")) {
            filePath = singleRoom_Day_Rate;
            price = 250;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = doubleRoom_Hour_Rate;
            price = 0;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = doubleRoom_Day_Rate;
            price = 300;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Lớn Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = tripleRoom_Hour_Rate;
            price = 0;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Lớn Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = tripleRoom_Day_Rate;
            price = 350;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Lớn") && setPrice.equals("Giá Giờ")) {
            filePath = quadraRoom_Hour_Rate;
            price = 0;
        } else if (String.valueOf(cboLoaiPhong.getSelectedItem()).equals("Phòng Đôi Lớn") && setPrice.equals("Giá Ngày")) {
            filePath = quadraRoom_Day_Rate;
            price = 400;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTimeDen = LocalDateTime.parse(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioTuNgay.getText() + lblPhutTuNgay.getText().replaceAll(" ", ""), formatter);
            LocalDateTime dateTimeDi = LocalDateTime.parse(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate()) + " " + txtGioDenNgay.getText() + lblPhutDenNgay.getText().replaceAll(" ", ""), formatter);
            diffInDay = Duration.between(dateTimeDen, dateTimeDi).toDays();
            diffInHours = Duration.between(dateTimeDen, dateTimeDi).toHours() - diffInDay * 24;
            diffInMinutes = (Duration.between(dateTimeDen, dateTimeDi).toMinutes() - diffInDay * 60 * 24) % 60;
            lblSetThoiGian.setText(String.valueOf(diffInDay + "d " + diffInHours + "h " + diffInMinutes + "m"));
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            if (diffInDay == 0) {
                lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
            } else {
                if (HELPER_ChuyenDoi.getSoInt(txtGioTuNgay.getText()) <= HELPER_ChuyenDoi.getSoInt(txtGioDenNgay.getText())) {
                    lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString((diffInDay - 1) * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
                } else {
                    lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(diffInDay * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Định Dạng ???");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sdoChucNang = new HELPER.PanelShadow();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        lblDenNgay = new javax.swing.JLabel();
        lblTuNgay = new javax.swing.JLabel();
        lblTinhGia = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblSetThoiGian = new javax.swing.JLabel();
        lblSetTongTien = new javax.swing.JLabel();
        cboLoaiPhong = new javax.swing.JComboBox<>();
        lblThoiGian = new javax.swing.JLabel();
        lblKiemTraGia = new javax.swing.JLabel();
        lblLoaiGia = new javax.swing.JLabel();
        cboLoaiGia = new javax.swing.JComboBox<>();
        lblLoaiPhong = new javax.swing.JLabel();
        txtGioTuNgay = new javax.swing.JTextField();
        txtGioDenNgay = new javax.swing.JTextField();
        lblPhutDenNgay = new javax.swing.JLabel();
        lblPhutTuNgay = new javax.swing.JLabel();
        sdoChuThich = new HELPER.PanelShadow();
        lblColorBlue = new javax.swing.JLabel();
        lblColorYellow = new javax.swing.JLabel();
        lblColorPink = new javax.swing.JLabel();
        lblColorGray = new javax.swing.JLabel();
        lblColorGreen = new javax.swing.JLabel();
        lblColorOrange = new javax.swing.JLabel();
        lblCapNhat = new javax.swing.JLabel();
        pnlGiaPhong = new javax.swing.JPanel();
        scrGiaPhong = new javax.swing.JScrollPane();
        tblGiaPhong = new javax.swing.JTable()
        {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex
            ) {
                Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
                Object value = getModel().getValueAt(rowIndex, columnIndex);
                tblGiaPhong.getTableHeader().setBackground(new Color(61, 132, 184));
                tblGiaPhong.getTableHeader().setForeground(new Color(255, 255, 255));
                tblGiaPhong.getTableHeader().setOpaque(false);
                if (setPrice == "Giá Giờ"){
                    if (columnIndex == 0) {
                        component.setBackground(new Color(77, 169, 108));
                        component.setForeground(new Color(255, 255, 255));
                    }else if (rowIndex >= 6 && rowIndex <=13 && columnIndex >= 30 - rowIndex && columnIndex <= 24){
                        component.setBackground(new Color(255, 199, 206));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 14 && rowIndex <= 16 && columnIndex >= 30 - rowIndex && columnIndex <= 30 - rowIndex + 1){
                        component.setBackground(new Color(255, 199, 206));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 17 && rowIndex <= 23 && columnIndex >= 30 - rowIndex && columnIndex <= 13){
                        component.setBackground(new Color(255, 199, 206));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 14 && rowIndex <= 17 && columnIndex >= 32 - rowIndex && columnIndex <= 24){
                        component.setBackground(new Color(255, 153, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex == 17 && columnIndex == 14){
                        component.setBackground(new Color(255, 153, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 18 && rowIndex <= 23 && columnIndex >= 14 && columnIndex <= 18){
                        component.setBackground(new Color(255, 153, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 18 && rowIndex <= 23 && columnIndex >= 19 && columnIndex <= 24){
                        component.setBackground(new Color(240, 240, 240));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 0 && rowIndex <= 22 && columnIndex >= 1 && columnIndex <= 23 - rowIndex){
                        component.setBackground(new Color(255, 255, 255));
                        component.setForeground(new Color(0, 0, 0));
                    }else{
                        component.setBackground(new Color(255, 255, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }
                }else if (setPrice == "Giá Ngày"){
                    if (columnIndex == 0) {
                        component.setBackground(new Color(77, 169, 108));
                        component.setForeground(new Color(255, 255, 255));
                    }else if (rowIndex >= 0 && rowIndex <=4 && columnIndex >= 0 && columnIndex <= 5 - rowIndex){
                        component.setBackground(new Color(255, 255, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 14 && rowIndex <= 17 && columnIndex >= 1 && columnIndex <= 13){
                        component.setBackground(new Color(255, 199, 206));
                        component.setForeground(new Color(0, 0, 0));
                    }else if (rowIndex >= 0 && rowIndex <= 23 && columnIndex >= 14 && columnIndex <= 18){
                        component.setBackground(new Color(255, 153, 102));
                        component.setForeground(new Color(0, 0, 0));
                    }else{
                        component.setBackground(new Color(240, 240, 240));
                        component.setForeground(new Color(0, 0, 0));
                    }
                }
                ((DefaultTableCellRenderer) tblGiaPhong.getCellRenderer(rowIndex, columnIndex)).setHorizontalAlignment((int) CENTER_ALIGNMENT);
                return component;
            }
        }
        ;

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1150, 730));
        setPreferredSize(new java.awt.Dimension(1150, 730));

        sdoChucNang.setBackground(new java.awt.Color(255, 255, 255));
        sdoChucNang.setForeground(new java.awt.Color(255, 255, 255));
        sdoChucNang.setMinimumSize(new java.awt.Dimension(1150, 110));
        sdoChucNang.setPreferredSize(new java.awt.Dimension(1150, 110));
        sdoChucNang.setShadowOpacity(0.4F);
        sdoChucNang.setShadowSize(9);
        sdoChucNang.setShadowType(HELPER.ShadowType.BOT);
        sdoChucNang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateTuNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateTuNgay.setDateFormatString("dd-MM-yyyy");
        dateTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuNgayPropertyChange(evt);
            }
        });
        sdoChucNang.add(dateTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 100, 20));

        dateDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateDenNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateDenNgay.setDateFormatString("dd-MM-yyyy");
        dateDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenNgayPropertyChange(evt);
            }
        });
        sdoChucNang.add(dateDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 100, 20));

        lblDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblDenNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDenNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblDenNgay.setText("Đến Ngày");
        sdoChucNang.add(lblDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, 20));

        lblTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblTuNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTuNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblTuNgay.setText("Từ Ngày");
        sdoChucNang.add(lblTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, 20));

        lblTinhGia.setBackground(new java.awt.Color(255, 255, 255));
        lblTinhGia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTinhGia.setForeground(new java.awt.Color(33, 150, 243));
        lblTinhGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/money-back.png"))); // NOI18N
        lblTinhGia.setText("Tính Giá");
        lblTinhGia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTinhGia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblTinhGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTinhGiaMouseClicked(evt);
            }
        });
        sdoChucNang.add(lblTinhGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 50, 50));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(153, 153, 153));
        lblTongTien.setText("Tổng Tiền");
        sdoChucNang.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, 20));

        lblSetThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblSetThoiGian.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetThoiGian.setForeground(new java.awt.Color(97, 177, 90));
        sdoChucNang.add(lblSetThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, 80, 20));

        lblSetTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTongTien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTongTien.setForeground(new java.awt.Color(97, 177, 90));
        sdoChucNang.add(lblSetTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, 80, 20));

        cboLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboLoaiPhong.setForeground(new java.awt.Color(62, 73, 95));
        cboLoaiPhong.setToolTipText("");
        cboLoaiPhong.setBorder(null);
        cboLoaiPhong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiPhongItemStateChanged(evt);
            }
        });
        sdoChucNang.add(cboLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, 20));

        lblThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblThoiGian.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThoiGian.setForeground(new java.awt.Color(153, 153, 153));
        lblThoiGian.setText("Thời Gian");
        sdoChucNang.add(lblThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, -1, 20));

        lblKiemTraGia.setBackground(new java.awt.Color(255, 255, 255));
        lblKiemTraGia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblKiemTraGia.setForeground(new java.awt.Color(62, 73, 95));
        lblKiemTraGia.setText("Kiểm Tra Giá");
        sdoChucNang.add(lblKiemTraGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 80, 20));

        lblLoaiGia.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiGia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiGia.setForeground(new java.awt.Color(153, 153, 153));
        lblLoaiGia.setText("Loại Giá");
        sdoChucNang.add(lblLoaiGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 70, 20));

        cboLoaiGia.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboLoaiGia.setForeground(new java.awt.Color(62, 73, 95));
        cboLoaiGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá Ngày", "Giá Giờ" }));
        cboLoaiGia.setToolTipText("");
        cboLoaiGia.setBorder(null);
        cboLoaiGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiGiaItemStateChanged(evt);
            }
        });
        sdoChucNang.add(cboLoaiGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 90, 20));

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblLoaiPhong.setText("Loại Phòng");
        sdoChucNang.add(lblLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        txtGioTuNgay.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioTuNgay.setForeground(new java.awt.Color(62, 73, 95));
        txtGioTuNgay.setText("00");
        txtGioTuNgay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioTuNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtGioTuNgayMouseReleased(evt);
            }
        });
        txtGioTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioTuNgayActionPerformed(evt);
            }
        });
        txtGioTuNgay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioTuNgayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGioTuNgayKeyTyped(evt);
            }
        });
        sdoChucNang.add(txtGioTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 20, 20));

        txtGioDenNgay.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioDenNgay.setForeground(new java.awt.Color(62, 73, 95));
        txtGioDenNgay.setText("00");
        txtGioDenNgay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioDenNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtGioDenNgayMouseReleased(evt);
            }
        });
        txtGioDenNgay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioDenNgayKeyReleased(evt);
            }
        });
        sdoChucNang.add(txtGioDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 20, 20));

        lblPhutDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblPhutDenNgay.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblPhutDenNgay.setForeground(new java.awt.Color(62, 73, 95));
        lblPhutDenNgay.setText(": 00");
        lblPhutDenNgay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoChucNang.add(lblPhutDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, 20));

        lblPhutTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblPhutTuNgay.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblPhutTuNgay.setForeground(new java.awt.Color(62, 73, 95));
        lblPhutTuNgay.setText(": 00");
        lblPhutTuNgay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoChucNang.add(lblPhutTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, 20));

        sdoChuThich.setBackground(new java.awt.Color(255, 255, 255));
        sdoChuThich.setForeground(new java.awt.Color(255, 255, 255));
        sdoChuThich.setMinimumSize(new java.awt.Dimension(1150, 70));
        sdoChuThich.setPreferredSize(new java.awt.Dimension(1150, 70));
        sdoChuThich.setShadowOpacity(0.6F);
        sdoChuThich.setShadowSize(9);
        sdoChuThich.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblColorBlue.setBackground(new java.awt.Color(61, 132, 184));
        lblColorBlue.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorBlue.setForeground(new java.awt.Color(255, 255, 255));
        lblColorBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorBlue.setText("Giờ Ra");
        lblColorBlue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorBlue.setOpaque(true);
        sdoChuThich.add(lblColorBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 100, 30));

        lblColorYellow.setBackground(new java.awt.Color(255, 255, 102));
        lblColorYellow.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorYellow.setForeground(new java.awt.Color(51, 51, 51));
        lblColorYellow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorYellow.setText("Giá Giờ");
        lblColorYellow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorYellow.setOpaque(true);
        sdoChuThich.add(lblColorYellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 100, 30));

        lblColorPink.setBackground(new java.awt.Color(255, 199, 206));
        lblColorPink.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorPink.setForeground(new java.awt.Color(51, 51, 51));
        lblColorPink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorPink.setText("Giá Đến Sớm");
        lblColorPink.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorPink.setOpaque(true);
        sdoChuThich.add(lblColorPink, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 100, 30));

        lblColorGray.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorGray.setForeground(new java.awt.Color(51, 51, 51));
        lblColorGray.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorGray.setText("Giá Ngày Đêm");
        lblColorGray.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorGray.setOpaque(true);
        sdoChuThich.add(lblColorGray, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 100, 30));

        lblColorGreen.setBackground(new java.awt.Color(74, 169, 108));
        lblColorGreen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorGreen.setForeground(new java.awt.Color(255, 255, 255));
        lblColorGreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorGreen.setText("Giờ Vào");
        lblColorGreen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorGreen.setOpaque(true);
        sdoChuThich.add(lblColorGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 30));

        lblColorOrange.setBackground(new java.awt.Color(255, 153, 102));
        lblColorOrange.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblColorOrange.setForeground(new java.awt.Color(51, 51, 51));
        lblColorOrange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColorOrange.setText("Giá Ra Muộn");
        lblColorOrange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblColorOrange.setOpaque(true);
        sdoChuThich.add(lblColorOrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 100, 30));

        lblCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        lblCapNhat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblCapNhat.setForeground(new java.awt.Color(33, 150, 243));
        lblCapNhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/upgrade (3).png"))); // NOI18N
        lblCapNhat.setText("Cập Nhật");
        lblCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCapNhatMouseClicked(evt);
            }
        });
        sdoChuThich.add(lblCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 100, 30));

        pnlGiaPhong.setBackground(new java.awt.Color(255, 255, 255));
        pnlGiaPhong.setMinimumSize(new java.awt.Dimension(1150, 550));
        pnlGiaPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrGiaPhong.setBackground(new java.awt.Color(255, 255, 255));
        scrGiaPhong.setBorder(null);

        tblGiaPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblGiaPhong.setForeground(new java.awt.Color(62, 73, 95));
        tblGiaPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGiaPhong.setRowHeight(30);
        tblGiaPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiaPhongMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGiaPhongMousePressed(evt);
            }
        });
        scrGiaPhong.setViewportView(tblGiaPhong);

        pnlGiaPhong.add(scrGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1130, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sdoChuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sdoChuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiGiaItemStateChanged
        // TODO add your handling code here:
        setPrice = String.valueOf(cboLoaiGia.getSelectedItem());
        setRoomRate();
        if (String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Ngày")) {
            setChuThichGia("Giờ Vào", "Giờ Ra", "Giá Giờ", "Giá Đến Sớm", "Giá Ra Muộn", "Giá Ngày Đêm");
        } else if (String.valueOf(cboLoaiGia.getSelectedItem()).equals("Giá Giờ")) {
            setChuThichGia("Giờ Vào", "Giờ Ra", "Giá Giờ", "Giá > 5h", "Giá > 12h", "Giá > 18h");
        }
    }//GEN-LAST:event_cboLoaiGiaItemStateChanged

    private void lblCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCapNhatMouseClicked
        // TODO add your handling code here:
        writeExcel(path);
    }//GEN-LAST:event_lblCapNhatMouseClicked

    private void cboLoaiPhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiPhongItemStateChanged
        // TODO add your handling code here:
        setRoomRate();
    }//GEN-LAST:event_cboLoaiPhongItemStateChanged

    private void tblGiaPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiaPhongMouseClicked
        // TODO add your handling code here:  
    }//GEN-LAST:event_tblGiaPhongMouseClicked

    private void tblGiaPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiaPhongMousePressed
        // TODO add your handling code here:
        rowIndex = tblGiaPhong.getSelectedRow();
        columnIndex = tblGiaPhong.getSelectedColumn();
        if (setPrice.equals("Giá Giờ") && rowIndex >= 0 && rowIndex <= 22 && columnIndex >= 0 && columnIndex <= 23 - rowIndex) {
            JOptionPane.showMessageDialog(this, "Không Được Chỉnh Sửa ???");
        }
        return;
    }//GEN-LAST:event_tblGiaPhongMousePressed

    private void lblTinhGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTinhGiaMouseClicked
        // TODO add your handling code here:
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioTuNgay.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDenNgay.getText()) + 1);
    }//GEN-LAST:event_lblTinhGiaMouseClicked

    private void txtGioTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioTuNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioTuNgayActionPerformed

    private void dateDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenNgayPropertyChange
        // TODO add your handling code here:
        if (dateDenNgay.getDate() != null && dateTuNgay.getDate() != null) {
            dateTuNgay.setMaxSelectableDate(dateDenNgay.getDate());;
        }
    }//GEN-LAST:event_dateDenNgayPropertyChange

    private void dateTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuNgayPropertyChange
        // TODO add your handling code here:
        if (dateTuNgay.getDate() != null && dateDenNgay.getDate() != null) {
            dateDenNgay.setMinSelectableDate(dateTuNgay.getDate());;
        }
    }//GEN-LAST:event_dateTuNgayPropertyChange

    private void txtGioTuNgayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioTuNgayMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioTuNgayMouseReleased

    private void txtGioDenNgayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioDenNgayMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDenNgayMouseReleased

    private void txtGioTuNgayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioTuNgayKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtGioTuNgay);
        HELPER_Validate.setTextLimited(txtGioTuNgay, 2);
    }//GEN-LAST:event_txtGioTuNgayKeyReleased

    private void txtGioDenNgayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioDenNgayKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtGioDenNgay);
        HELPER_Validate.setTextLimited(txtGioDenNgay, 2);
    }//GEN-LAST:event_txtGioDenNgayKeyReleased

    private void txtGioTuNgayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioTuNgayKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioTuNgayKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboLoaiGia;
    private javax.swing.JComboBox<String> cboLoaiPhong;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JLabel lblCapNhat;
    private javax.swing.JLabel lblColorBlue;
    private javax.swing.JLabel lblColorGray;
    private javax.swing.JLabel lblColorGreen;
    private javax.swing.JLabel lblColorOrange;
    private javax.swing.JLabel lblColorPink;
    private javax.swing.JLabel lblColorYellow;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblKiemTraGia;
    private javax.swing.JLabel lblLoaiGia;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblPhutDenNgay;
    private javax.swing.JLabel lblPhutTuNgay;
    private javax.swing.JLabel lblSetThoiGian;
    private javax.swing.JLabel lblSetTongTien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTinhGia;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JPanel pnlGiaPhong;
    private javax.swing.JScrollPane scrGiaPhong;
    private HELPER.PanelShadow sdoChuThich;
    private HELPER.PanelShadow sdoChucNang;
    private javax.swing.JTable tblGiaPhong;
    private javax.swing.JTextField txtGioDenNgay;
    private javax.swing.JTextField txtGioTuNgay;
    // End of variables declaration//GEN-END:variables
}
