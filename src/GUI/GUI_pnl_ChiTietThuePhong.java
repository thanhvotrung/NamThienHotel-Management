/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DichVu;
import BLL.BLL_MaTenLoai;
import BLL.BLL_SoDoPhong;
import BLL.BLL_ThuePhong;
import DTO.DTO_Phong;
import DTO.DTO_ThuePhong;
import static GUI.GUI_pnl_ChiTietPhong.lblLoaiPhong;
import static GUI.GUI_pnl_ChiTietPhong.lblSoPhong;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Hour_Rate;
import HELPER.HELPER_ChuyenDoi;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_ChiTietThuePhong extends javax.swing.JPanel {

    public static int indexPosition;
    public static String ghiChu;
    long diffInDay;
    long diffInHours;
    long diffInMinutes;

    /**
     * Creates new form GUI_pnlChiTietPhong
     */
    public GUI_pnl_ChiTietThuePhong() {
        initComponents();
        load();
        tongTienDichVu();
        setDaTra();
        setGiamGia();
    }

    public void load() {
        ArrayList<DTO_Phong> arrayPhong = BLL_ThuePhong.selectPhong(GUI_pnl_ThuePhong.tuNgay, GUI_pnl_ThuePhong.denNgay, GUI_pnl_ThuePhong.index);
        BLL_ThuePhong.loadPhong(arrayPhong, lblSoPhong, lblLoaiPhong, lblTrangThaiPhong);
        ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuePhong.selectThuePhong(GUI_pnl_ThuePhong.tuNgay, GUI_pnl_ThuePhong.denNgay, GUI_pnl_ThuePhong.index);
        BLL_ThuePhong.loadThuePhong(arrayThuePhong, lblNgayDen, lblThangDen, lblGioPhutDen, lblNgayDi, lblThangDi, lblGioPhutDi, lblTenKhach, lblTrangThaiPhong);
        if (lblTrangThaiPhong.getText().equals("Có Khách")) {
            lblTrangThaiPhong.setForeground(new Color(255, 142, 113));
            lblIconPhong.setIcon(new ImageIcon("src/IMG/hotel-sign (5).png"));
        } else if (lblTrangThaiPhong.getText().equals("Đặt Trước")) {
            lblTrangThaiPhong.setForeground(new Color(102, 153, 255));
            lblIconPhong.setIcon(new ImageIcon("src/IMG/hotel-sign (6).png"));
        } else if (lblTrangThaiPhong.getText().equals("Trả Phòng")) {
            lblTrangThaiPhong.setForeground(new Color(255, 153, 0));
            lblIconPhong.setIcon(new ImageIcon("src/IMG/hotel-sign (7).png"));
        }
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)), HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2)) + 1);
    }

    public void setThoiGian_GiaTien(int row, int column) {
        int price = 0;
        String filePath = null;
        String setPrice = null;
        String dateNgayDen = lblNgayDen.getText() + "-" + lblThangDen.getText() + "-" + HELPER_ChuyenDoi.getTimeNow("yyyy");
        String dateNgayDi = lblNgayDi.getText() + "-" + lblThangDi.getText() + "-" + HELPER_ChuyenDoi.getTimeNow("yyyy");
        if (dateNgayDen.equals(dateNgayDi)) {
            setPrice = "Giá Giờ";
        } else {
            setPrice = "Giá Ngày";
        }
        if (lblLoaiPhong.getText().equals("Phòng Đơn") && setPrice.equals("Giá Giờ")) {
            filePath = singleRoom_Hour_Rate;
            price = 0;
        } else if (lblLoaiPhong.getText().equals("Phòng Đơn") && setPrice.equals("Giá Ngày")) {
            filePath = singleRoom_Day_Rate;
            price = 250;
        } else if (lblLoaiPhong.getText().equals("Phòng Đôi Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = doubleRoom_Hour_Rate;
            price = 0;
        } else if (lblLoaiPhong.getText().equals("Phòng Đôi Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = doubleRoom_Day_Rate;
            price = 300;
        } else if (lblLoaiPhong.getText().equals("Phòng Lớn Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = tripleRoom_Hour_Rate;
            price = 0;
        } else if (lblLoaiPhong.getText().equals("Phòng Lớn Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = tripleRoom_Day_Rate;
            price = 350;
        } else if (lblLoaiPhong.getText().equals("Phòng Đôi Lớn") && setPrice.equals("Giá Giờ")) {
            filePath = quadraRoom_Hour_Rate;
            price = 0;
        } else if (lblLoaiPhong.getText().equals("Phòng Đôi Lớn") && setPrice.equals("Giá Ngày")) {
            filePath = quadraRoom_Day_Rate;
            price = 400;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTimeDen = LocalDateTime.parse(lblNgayDen.getText() + "-" + lblThangDen.getText() + "-" + HELPER_ChuyenDoi.getTimeNow("yyyy") + " " + lblGioPhutDen.getText(), formatter);
            LocalDateTime dateTimeDi = LocalDateTime.parse(lblNgayDi.getText() + "-" + lblThangDi.getText() + "-" + HELPER_ChuyenDoi.getTimeNow("yyyy") + " " + lblGioPhutDi.getText(), formatter);
            diffInDay = Duration.between(dateTimeDen, dateTimeDi).toDays();
            diffInHours = Duration.between(dateTimeDen, dateTimeDi).toHours() - diffInDay * 24;
            diffInMinutes = (Duration.between(dateTimeDen, dateTimeDi).toMinutes() - diffInDay * 60 * 24) % 60;
            lblTongThoiGian.setText(String.valueOf(diffInDay + "d " + diffInHours + "h " + diffInMinutes + "m"));
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int giamGia = 0;
            if (lblTrangThaiPhong.getText().equals("Trả Phòng")) {
                giamGia = BLL_ChiTietDichVu.countGiamGiaByPhieu(BLL_ThuePhong.findMaPhieuThue(HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDen.getText() + "-" + lblNgayDen.getText() + " " + lblGioPhutDen.getText(), HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDi.getText() + "-" + lblNgayDi.getText() + " " + lblGioPhutDi.getText()));
            } else {
                giamGia = BLL_ChiTietDichVu.countGiamGiaByPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()));
            }
            if (diffInDay == 0) {
                lblSetTienPhong.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - giamGia) + "K");
            } else {
                if (HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)) <= HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2))) {
                    lblSetTienPhong.setText(HELPER_ChuyenDoi.getSoString((diffInDay - 1) * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - giamGia) + "K");
                } else {
                    lblSetTienPhong.setText(HELPER_ChuyenDoi.getSoString(diffInDay * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - giamGia) + "K");
                }
            }
            GUI_pnl_ThuePhong.tienPhong += HELPER_ChuyenDoi.getSoInt(lblSetTienPhong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Định Dạng ???");
        }
    }

    public void tongTienDichVu() {
        if (lblTrangThaiPhong.getText().equals("Trả Phòng")) {
            lblSetDichVu.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.tongTienDichVu(BLL_ThuePhong.findMaPhieuThue(HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDen.getText() + "-" + lblNgayDen.getText() + " " + lblGioPhutDen.getText(), HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDi.getText() + "-" + lblNgayDi.getText() + " " + lblGioPhutDi.getText()))) + "K");
        } else {
            lblSetDichVu.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.tongTienDichVu(BLL_DichVu.findMaPhieuThue(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText())))) + "K");
        }
        GUI_pnl_ThuePhong.dichVu += HELPER_ChuyenDoi.getSoInt(lblSetDichVu.getText());
    }

    public void setDaTra() {
        if (lblTrangThaiPhong.getText().equals("Trả Phòng")) {
            if (ghiChu.contains("Chuyển Đến Phòng")) {
                lblSetDaTra.setText("0K");
            } else {
                lblSetDaTra.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(lblSetTienPhong.getText()) + HELPER_ChuyenDoi.getSoInt(lblSetDichVu.getText())) + "K");
            }
        } else {
            lblSetDaTra.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.countTienCocByMaPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText())) + BLL_ChiTietDichVu.countThanhToan(BLL_DichVu.findMaPhieuThue(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText())))) + "K");
        }
        GUI_pnl_ThuePhong.daTra += HELPER_ChuyenDoi.getSoInt(lblSetDaTra.getText());
    }

    public void setGiamGia() {
        if (lblTrangThaiPhong.getText().equals("Trả Phòng")) {
            GUI_pnl_ThuePhong.giamGia += BLL_ChiTietDichVu.countGiamGiaByPhieu(BLL_ThuePhong.findMaPhieuThue(HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDen.getText() + "-" + lblNgayDen.getText() + " " + lblGioPhutDen.getText(), HELPER_ChuyenDoi.getTimeNow("yyyy") + "-" + lblThangDi.getText() + "-" + lblNgayDi.getText() + " " + lblGioPhutDi.getText()));
        } else {
            GUI_pnl_ThuePhong.giamGia += BLL_ChiTietDichVu.countGiamGiaByPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()));
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

        sdoChiTietThuePhong = new HELPER.PanelShadow();
        lblGioPhutDi = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblDichVu = new javax.swing.JLabel();
        lblTienPhong = new javax.swing.JLabel();
        lblThangDen = new javax.swing.JLabel();
        spt_1 = new javax.swing.JSeparator();
        lblNgayDen = new javax.swing.JLabel();
        lblSetDaTra = new javax.swing.JLabel();
        lblDaTra = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblThangDi = new javax.swing.JLabel();
        spt_2 = new javax.swing.JSeparator();
        lblTrangThaiPhong = new javax.swing.JLabel();
        lblNgayDi = new javax.swing.JLabel();
        lblIconDongHo = new javax.swing.JLabel();
        lblTongThoiGian = new javax.swing.JLabel();
        lblGioPhutDen = new javax.swing.JLabel();
        lblSetTienPhong = new javax.swing.JLabel();
        lblSetDichVu = new javax.swing.JLabel();
        lblIconPhong = new javax.swing.JLabel();
        lblTenKhach = new javax.swing.JLabel();
        spt_3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(250, 220));
        setPreferredSize(new java.awt.Dimension(250, 220));

        sdoChiTietThuePhong.setBackground(new java.awt.Color(255, 255, 255));
        sdoChiTietThuePhong.setMinimumSize(new java.awt.Dimension(250, 220));
        sdoChiTietThuePhong.setPreferredSize(new java.awt.Dimension(250, 220));
        sdoChiTietThuePhong.setShadowOpacity(0.3F);
        sdoChiTietThuePhong.setShadowSize(4);
        sdoChiTietThuePhong.setShadowType(HELPER.ShadowType.BOT_RIGHT);
        sdoChiTietThuePhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                sdoChiTietThuePhongMouseMoved(evt);
            }
        });
        sdoChiTietThuePhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sdoChiTietThuePhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sdoChiTietThuePhongMouseExited(evt);
            }
        });
        sdoChiTietThuePhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGioPhutDi.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDi.setForeground(new java.awt.Color(62, 73, 95));
        lblGioPhutDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblGioPhutDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 40, 20));

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblLoaiPhong.setForeground(new java.awt.Color(153, 153, 153));
        sdoChiTietThuePhong.add(lblLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 20));

        lblDichVu.setBackground(new java.awt.Color(255, 255, 255));
        lblDichVu.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblDichVu.setForeground(new java.awt.Color(153, 153, 153));
        lblDichVu.setText("Dịch Vụ");
        sdoChiTietThuePhong.add(lblDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 50, 20));

        lblTienPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblTienPhong.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblTienPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblTienPhong.setText("Tiền Phòng");
        sdoChiTietThuePhong.add(lblTienPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        lblThangDen.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDen.setForeground(new java.awt.Color(62, 73, 95));
        lblThangDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblThangDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 20, 30));

        spt_1.setBackground(new java.awt.Color(0, 0, 0));
        spt_1.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(spt_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 20, 10));

        lblNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDen.setForeground(new java.awt.Color(62, 73, 95));
        lblNgayDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, 20, 20));

        lblSetDaTra.setBackground(new java.awt.Color(255, 255, 255));
        lblSetDaTra.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetDaTra.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(lblSetDaTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 60, 20));

        lblDaTra.setBackground(new java.awt.Color(255, 255, 255));
        lblDaTra.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblDaTra.setForeground(new java.awt.Color(153, 153, 153));
        lblDaTra.setText("Đã Trả");
        sdoChiTietThuePhong.add(lblDaTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 40, 20));

        lblSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(lblSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        lblThangDi.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDi.setForeground(new java.awt.Color(62, 73, 95));
        lblThangDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblThangDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 20, 30));

        spt_2.setBackground(new java.awt.Color(0, 0, 0));
        spt_2.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(spt_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 20, 10));

        lblTrangThaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangThaiPhong.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblTrangThaiPhong.setForeground(new java.awt.Color(97, 177, 90));
        lblTrangThaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangThaiPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblTrangThaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 70, 30));

        lblNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDi.setForeground(new java.awt.Color(62, 73, 95));
        lblNgayDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 57, 20, 20));

        lblIconDongHo.setBackground(new java.awt.Color(255, 255, 255));
        lblIconDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/clock.png"))); // NOI18N
        sdoChiTietThuePhong.add(lblIconDongHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 30, 30));

        lblTongThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblTongThoiGian.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongThoiGian.setForeground(new java.awt.Color(255, 102, 102));
        lblTongThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblTongThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 100, 30));

        lblGioPhutDen.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDen.setForeground(new java.awt.Color(62, 73, 95));
        lblGioPhutDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblGioPhutDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 20));

        lblSetTienPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTienPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetTienPhong.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(lblSetTienPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 70, 20));

        lblSetDichVu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetDichVu.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(lblSetDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 60, 20));

        lblIconPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblIconPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/beds (3).png"))); // NOI18N
        lblIconPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuePhong.add(lblIconPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 30, -1));

        lblTenKhach.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKhach.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTenKhach.setForeground(new java.awt.Color(255, 102, 102));
        lblTenKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/personal-profile (1).png"))); // NOI18N
        sdoChiTietThuePhong.add(lblTenKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 130, 30));

        spt_3.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuePhong.add(spt_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 220, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sdoChiTietThuePhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuePhongMouseMoved
        // TODO add your handling code here:
        sdoChiTietThuePhong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(33, 150, 243)));
    }//GEN-LAST:event_sdoChiTietThuePhongMouseMoved

    private void sdoChiTietThuePhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuePhongMouseExited
        // TODO add your handling code here:
        sdoChiTietThuePhong.setBorder(null);
    }//GEN-LAST:event_sdoChiTietThuePhongMouseExited

    private void sdoChiTietThuePhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuePhongMouseClicked
        // TODO add your handling code here:
        GUI_pnl_SoDoPhong.isThongTinPhong = true;
        indexPosition = GUI_pnl_ThuePhong.pnlFormChinh.getComponentZOrder(sdoChiTietThuePhong);
        if (evt.getClickCount() == 2) {
            new GUI_dal_ThongTinPhong(null, true).setVisible(true);
        }
    }//GEN-LAST:event_sdoChiTietThuePhongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lblDaTra;
    public javax.swing.JLabel lblDichVu;
    private javax.swing.JLabel lblGioPhutDen;
    private javax.swing.JLabel lblGioPhutDi;
    private javax.swing.JLabel lblIconDongHo;
    private javax.swing.JLabel lblIconPhong;
    public javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblNgayDen;
    private javax.swing.JLabel lblNgayDi;
    private javax.swing.JLabel lblSetDaTra;
    private javax.swing.JLabel lblSetDichVu;
    private javax.swing.JLabel lblSetTienPhong;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblTenKhach;
    private javax.swing.JLabel lblThangDen;
    private javax.swing.JLabel lblThangDi;
    public javax.swing.JLabel lblTienPhong;
    private javax.swing.JLabel lblTongThoiGian;
    private javax.swing.JLabel lblTrangThaiPhong;
    public HELPER.PanelShadow sdoChiTietThuePhong;
    private javax.swing.JSeparator spt_1;
    private javax.swing.JSeparator spt_2;
    private javax.swing.JSeparator spt_3;
    // End of variables declaration//GEN-END:variables
}
