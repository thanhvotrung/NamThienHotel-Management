/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DichVu;
import BLL.BLL_HoaDon;
import BLL.BLL_MaTenLoai;
import BLL.BLL_ThuNgan;
import BLL.BLL_ThuePhong;
import DTO.DTO_DichVu;
import DTO.DTO_Phong;
import DTO.DTO_ThuePhong;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Hour_Rate;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
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
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_ChiTietThuNgan extends javax.swing.JPanel {

    long diffInDay;
    long diffInHours;
    long diffInMinutes;

    /**
     * Creates new form GUI_pnlChiTietPhong
     */
    public GUI_pnl_ChiTietThuNgan() {
        initComponents();
        load();
        validate();
    }

    public void load() {
        if (GUI_pnl_ThuNgan.isSelect.equals("HoaDon")) {
            ArrayList<DTO_Phong> arrayPhong = BLL_ThuNgan.selectByHoaDon(GUI_pnl_ThuNgan.tuNgay, GUI_pnl_ThuNgan.denNgay, GUI_pnl_ThuNgan.index);
            ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuNgan.selectHoaDon(GUI_pnl_ThuNgan.tuNgay, GUI_pnl_ThuNgan.denNgay, GUI_pnl_ThuNgan.index);
            BLL_ThuNgan.loadPhong(arrayPhong, lblSoPhong, lblLoaiPhong);
            BLL_ThuNgan.loadThuePhong(arrayThuePhong, lblNgayDen, lblThangDen, lblGioPhutDen, lblNgayDi, lblThangDi, lblGioPhutDi, lblSetMaPhieu, lblSetNgay, lblSetGio, lblLoaiThanhToan, lblNhanVien, lblLoaiTien);
            setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)), HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2)) + 1);
        } else if (GUI_pnl_ThuNgan.isSelect.equals("TienCoc")) {
            ArrayList<DTO_Phong> arrayPhong = BLL_ThuNgan.selectByTienCoc(GUI_pnl_ThuNgan.tuNgay, GUI_pnl_ThuNgan.denNgay, GUI_pnl_ThuNgan.index);
            ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuNgan.selectTienCoc(GUI_pnl_ThuNgan.tuNgay, GUI_pnl_ThuNgan.denNgay, GUI_pnl_ThuNgan.index);
            BLL_ThuNgan.loadPhong(arrayPhong, lblSoPhong, lblLoaiPhong);
            BLL_ThuNgan.loadTienCoc(arrayThuePhong, lblNgayDen, lblThangDen, lblGioPhutDen, lblNgayDi, lblThangDi, lblGioPhutDi, lblSetMaPhieu, lblSetNgay, lblSetGio, lblLoaiThanhToan, lblNhanVien, lblLoaiTien);
            setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)), HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2)) + 1);
        } else if (GUI_pnl_ThuNgan.isSelect.equals("DichVu")) {
            ArrayList<DTO_DichVu> arrayDichVu = BLL_ThuNgan.selectDichVu(GUI_pnl_ThuNgan.tuNgay, GUI_pnl_ThuNgan.denNgay, GUI_pnl_ThuNgan.index);
            BLL_ThuNgan.loadDichVu(arrayDichVu, lblSoPhong, lblNhanVien, lblSetMaPhieu, lblSetNgay, lblSetGio, lblLoaiThanhToan, lblLoaiTien);
            setTienDichVu();
        }
        GUI_pnl_ThuNgan.tongTien += HELPER_ChuyenDoi.getSoInt(lblTongTien.getText());
    }

    public void validate() {
        if (GUI_pnl_ThuNgan.isSelect.equals("DichVu")) {
            lblLoaiPhong.setVisible(false);
            lblThangDen.setVisible(false);
            lblNgayDen.setVisible(false);
            lblGioPhutDen.setVisible(false);
            spt_1.setVisible(false);
            lblThangDi.setVisible(false);
            lblNgayDi.setVisible(false);
            lblGioPhutDi.setVisible(false);
            spt_2.setVisible(false);
            lblTongThoiGian.setText("0d 0h 0m");
        }
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
            if (lblLoaiThanhToan.getText().equals("Tiền Cọc")) {
                setTienCoc();
            } else {
                if (diffInDay == 0) {
                    lblTongTien.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - BLL_ChiTietDichVu.countTienCocByMaPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText())) - BLL_ChiTietDichVu.countGiamGiaByPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText()))) + "K");
                } else {
                    if (HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)) <= HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2))) {
                        lblTongTien.setText(HELPER_ChuyenDoi.getSoString((diffInDay - 1) * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - BLL_ChiTietDichVu.countTienCocByMaPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText())) - BLL_ChiTietDichVu.countGiamGiaByPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText()))) + "K");
                    } else {
                        lblTongTien.setText(HELPER_ChuyenDoi.getSoString(diffInDay * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 - BLL_ChiTietDichVu.countTienCocByMaPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText())) - BLL_ChiTietDichVu.countGiamGiaByPhieu(BLL_HoaDon.findMaPhieuThue(lblSetMaPhieu.getText()))) + "K");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Định Dạng ???");
        }
    }

    public void setTienCoc() {
        lblTongTien.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.countTienCocByMaPhieu(lblSetMaPhieu.getText())) + "K");
    }

    public void setTienDichVu() {
        lblTongTien.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.countTienDichVu(lblSetMaPhieu.getText())) + "K");
    }

    public void export(String maLoaiThuNgan, String value, String filePath, String filePdf) {
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport(filePath);
            map.put(maLoaiThuNgan, value);
            JasperPrint p = JasperFillManager.fillReport(report, map, HELPER_ConnectSQL.conn);
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, filePdf);
        } catch (Exception e) {
            e.printStackTrace();
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

        sdoChiTietThuNgan = new HELPER.PanelShadow();
        lblGioPhutDi = new javax.swing.JLabel();
        lblLoaiTien = new javax.swing.JLabel();
        lblLoaiThanhToan = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        spt_3 = new javax.swing.JSeparator();
        lblNgay = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblThangDen = new javax.swing.JLabel();
        spt_1 = new javax.swing.JSeparator();
        lblNgayDen = new javax.swing.JLabel();
        lblSetGio = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblThangDi = new javax.swing.JLabel();
        spt_2 = new javax.swing.JSeparator();
        lblTongTien = new javax.swing.JLabel();
        lblNgayDi = new javax.swing.JLabel();
        lblIconDongHo = new javax.swing.JLabel();
        lblTongThoiGian = new javax.swing.JLabel();
        lblGioPhutDen = new javax.swing.JLabel();
        lblSetMaPhieu = new javax.swing.JLabel();
        lblSetNgay = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(250, 220));
        setPreferredSize(new java.awt.Dimension(250, 220));

        sdoChiTietThuNgan.setBackground(new java.awt.Color(255, 255, 255));
        sdoChiTietThuNgan.setMinimumSize(new java.awt.Dimension(250, 220));
        sdoChiTietThuNgan.setPreferredSize(new java.awt.Dimension(250, 220));
        sdoChiTietThuNgan.setShadowOpacity(0.3F);
        sdoChiTietThuNgan.setShadowSize(4);
        sdoChiTietThuNgan.setShadowType(HELPER.ShadowType.BOT_RIGHT);
        sdoChiTietThuNgan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                sdoChiTietThuNganMouseMoved(evt);
            }
        });
        sdoChiTietThuNgan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sdoChiTietThuNganMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sdoChiTietThuNganMouseExited(evt);
            }
        });
        sdoChiTietThuNgan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGioPhutDi.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDi.setForeground(new java.awt.Color(62, 73, 95));
        lblGioPhutDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblGioPhutDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 40, 20));

        lblLoaiTien.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiTien.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblLoaiTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 60, 30));

        lblLoaiThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiThanhToan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiThanhToan.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblLoaiThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 60, 30));

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblLoaiPhong.setForeground(new java.awt.Color(153, 153, 153));
        sdoChiTietThuNgan.add(lblLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 20));

        spt_3.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(spt_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 220, 10));

        lblNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblNgay.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblNgay.setText("Ngày");
        sdoChiTietThuNgan.add(lblNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 40, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoChiTietThuNgan.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 50, 20));

        lblNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 60, 30));

        lblThangDen.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDen.setForeground(new java.awt.Color(62, 73, 95));
        lblThangDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblThangDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 20, 30));

        spt_1.setBackground(new java.awt.Color(0, 0, 0));
        spt_1.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(spt_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 20, 10));

        lblNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDen.setForeground(new java.awt.Color(62, 73, 95));
        lblNgayDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, 20, 20));

        lblSetGio.setBackground(new java.awt.Color(255, 255, 255));
        lblSetGio.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetGio.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblSetGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 40, 20));

        lblGio.setBackground(new java.awt.Color(255, 255, 255));
        lblGio.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblGio.setForeground(new java.awt.Color(153, 153, 153));
        lblGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGio.setText("Giờ");
        sdoChiTietThuNgan.add(lblGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 40, 20));

        lblSoPhong.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        lblThangDi.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDi.setForeground(new java.awt.Color(62, 73, 95));
        lblThangDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblThangDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 20, 30));

        spt_2.setBackground(new java.awt.Color(0, 0, 0));
        spt_2.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(spt_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 20, 10));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 102, 102));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 70, 30));

        lblNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDi.setForeground(new java.awt.Color(62, 73, 95));
        lblNgayDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 57, 20, 20));

        lblIconDongHo.setBackground(new java.awt.Color(255, 255, 255));
        lblIconDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/clock.png"))); // NOI18N
        sdoChiTietThuNgan.add(lblIconDongHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 30, 30));

        lblTongThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblTongThoiGian.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongThoiGian.setForeground(new java.awt.Color(255, 102, 102));
        lblTongThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblTongThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 100, 30));

        lblGioPhutDen.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDen.setForeground(new java.awt.Color(62, 73, 95));
        lblGioPhutDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietThuNgan.add(lblGioPhutDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 20));

        lblSetMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetMaPhieu.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblSetMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 20));

        lblSetNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetNgay.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietThuNgan.add(lblSetNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 70, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietThuNgan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietThuNgan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sdoChiTietThuNganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuNganMouseClicked
        // TODO add your handling code here:
//        if (evt.getClickCount() == 2) {
//            if (lblLoaiThanhToan.getText().equals("Hóa Đơn")) {
//                export("MaHoaDon", lblSetMaPhieu.getText(), "src/GUI/GUI_rpt_HoaDon.jrxml", "HoaDon.pdf");
//            } else if (lblLoaiThanhToan.getText().equals("Tiền Cọc")) {
//                export("MaPhieuThue", lblSetMaPhieu.getText(), "src/GUI/GUI_rpt_PhieuThuePhong.jrxml", "PhieuThuePhong.pdf");
//            } else if (lblLoaiThanhToan.getText().equals("Dịch Vụ")) {
//                export("MaPhieuDichVu", lblSetMaPhieu.getText(), "src/GUI/GUI_rpt_PhieuDichVu.jrxml", "PhieuDichVu.pdf");
//            }
//        }
    }//GEN-LAST:event_sdoChiTietThuNganMouseClicked

    private void sdoChiTietThuNganMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuNganMouseMoved
        // TODO add your handling code here:
        sdoChiTietThuNgan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(33, 150, 243)));
    }//GEN-LAST:event_sdoChiTietThuNganMouseMoved

    private void sdoChiTietThuNganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietThuNganMouseExited
        // TODO add your handling code here:
        sdoChiTietThuNgan.setBorder(null);
    }//GEN-LAST:event_sdoChiTietThuNganMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblGioPhutDen;
    private javax.swing.JLabel lblGioPhutDi;
    private javax.swing.JLabel lblIconDongHo;
    public javax.swing.JLabel lblLoaiPhong;
    public javax.swing.JLabel lblLoaiThanhToan;
    public javax.swing.JLabel lblLoaiTien;
    public javax.swing.JLabel lblMaPhieu;
    public javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgayDen;
    private javax.swing.JLabel lblNgayDi;
    public javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSetGio;
    private javax.swing.JLabel lblSetMaPhieu;
    private javax.swing.JLabel lblSetNgay;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblThangDen;
    private javax.swing.JLabel lblThangDi;
    private javax.swing.JLabel lblTongThoiGian;
    private javax.swing.JLabel lblTongTien;
    public HELPER.PanelShadow sdoChiTietThuNgan;
    private javax.swing.JSeparator spt_1;
    private javax.swing.JSeparator spt_2;
    private javax.swing.JSeparator spt_3;
    // End of variables declaration//GEN-END:variables
}
