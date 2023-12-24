/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DichVu;
import BLL.BLL_LoaiPhong;
import BLL.BLL_MaTenLoai;
import BLL.BLL_Phong;
import BLL.BLL_SoDoPhong;
import BLL.BLL_SoTang;
import BLL.BLL_ThuePhong;
import DAL.DAL_SoDoPhong;
import DAL.DAL_ThuePhong;
import DTO.DTO_Phong;
import DTO.DTO_SoTang;
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
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
public class GUI_pnl_ChiTietPhong extends javax.swing.JPanel {

    public boolean isShowHiddenTime = false;
    public boolean isDonPhong = false;
    public boolean isKhachRaNgoai = false;
    public static boolean isDatThue = false;
    public static int indexPosition;
    long diffInDay;
    long diffInHours;
    long diffInMinutes;

    /**
     * Creates new form GUI_pnlChiTietPhong
     */
    public GUI_pnl_ChiTietPhong() {
        initComponents();
        load();
        loadPhong();
        validatePhong();
    }

    public void load() {
        lblDonPhong.setVisible(false);
        lblRaNgoai.setVisible(false);
        mniThuePhong.setVisible(false);
        mniThongTinPhong.setVisible(false);
        mniKhachRaNgoai.setVisible(false);
        mniChuyenPhong.setVisible(false);
        mniHuyPhong.setVisible(false);
        mniDatPhong.setVisible(false);
        mniDonPhong.setVisible(false);
    }

    public void loadPhong() {
        if (!GUI_pnl_SoDoPhong.isSelectPhong) {
            ArrayList<DTO_Phong> arrayPhong = BLL_SoDoPhong.selectPhong(GUI_pnl_SoDoPhong.index);
            BLL_SoDoPhong.loadPhong(arrayPhong, lblSoPhong, lblLoaiPhong, lblSetTrangThai);
        } else {
            ArrayList<DTO_Phong> arrayPhong = BLL_SoDoPhong.selectPhong(GUI_pnl_SoDoPhong.maTang, GUI_pnl_SoDoPhong.index);
            BLL_SoDoPhong.loadPhong(arrayPhong, lblSoPhong, lblLoaiPhong, lblSetTrangThai);
        }
        ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_SoDoPhong.selectThuePhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()));
        BLL_SoDoPhong.loadThuePhong(arrayThuePhong, lblNgayDen, lblThangDen, lblGioPhutDen, lblNgayDi, lblThangDi, lblGioPhutDi, lblSetDatCoc);
    }

    public void validatePhong() {
        if (lblSetTrangThai.getText().equals("Phòng Trống")) {
            sdoChiTietPhong.setBackground(new Color(97, 177, 90));
            lblIconTrangThai.setIcon(new ImageIcon("src/IMG/beds (1).png"));
            mniDonPhong.setVisible(true);
            mniDatPhong.setVisible(true);
            mniThuePhong.setVisible(true);
            isShowHiddenMoney(false);
            isShowHiddenTime(false);
        } else {
            if (lblSetTrangThai.getText().equals("Có Khách")) {
                mniDonPhong.setVisible(true);
                mniKhachRaNgoai.setVisible(true);
                mniChuyenPhong.setVisible(true);
                mniHuyPhong.setVisible(true);
                mniThongTinPhong.setVisible(true);
                sdoChiTietPhong.setBackground(new Color(255, 142, 113));
                lblIconTrangThai.setIcon(new ImageIcon("src/IMG/hotel-sign (2).png"));
            } else if (lblSetTrangThai.getText().equals("Đặt Trước")) {
                mniDonPhong.setVisible(true);
                mniThongTinPhong.setVisible(true);
                mniThuePhong.setVisible(true);
                mniHuyPhong.setVisible(true);
                sdoChiTietPhong.setBackground(new Color(102, 153, 255));
                lblIconTrangThai.setIcon(new ImageIcon("src/IMG/add-group (1).png"));
            } else if (lblSetTrangThai.getText().equals("Trả Phòng")) {
                mniDonPhong.setVisible(true);
                mniThongTinPhong.setVisible(true);
                sdoChiTietPhong.setBackground(new Color(240, 165, 0));
                lblIconTrangThai.setIcon(new ImageIcon("src/IMG/hotel-sign (2).png"));
            }
            isShowHiddenMoney(true);
            isShowHiddenTime(false);
            setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)), HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2)) + 1);
            tongTienConLai();
        }
    }

    public void isShowHiddenTime(boolean isShowHidden) {
        lblNgayDen.setVisible(isShowHidden);
        lblNgayDi.setVisible(isShowHidden);
        lblGioPhutDen.setVisible(isShowHidden);
        lblGioPhutDi.setVisible(isShowHidden);
        lblThangDen.setVisible(isShowHidden);
        lblThangDi.setVisible(isShowHidden);
        lblNgayDen.setVisible(isShowHidden);
        spt_1.setVisible(isShowHidden);
        spt_2.setVisible(isShowHidden);
    }

    public void isShowHiddenMoney(boolean isShowHidden) {
        lblTongThoiGian.setVisible(isShowHidden);
        lblNgayDefault.setVisible(!isShowHidden);
        lblNgayDefault.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy"));
        lblTongTien.setVisible(isShowHidden);
        lblDatCoc.setVisible(isShowHidden);
        lblConLai.setVisible(isShowHidden);
        lblSetTongTien.setVisible(isShowHidden);
        lblSetDatCoc.setVisible(isShowHidden);
        lblSetConLai.setVisible(isShowHidden);
    }

    public void showPopUp(MouseEvent evt) {
        popMenu.show(this, evt.getX(), evt.getY());
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
            if (diffInDay == 0) {
                lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 + tongTienDichVu() - BLL_ChiTietDichVu.countGiamGiaByPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()))) + "K");
            } else {
                if (HELPER_ChuyenDoi.getSoInt(lblGioPhutDen.getText().substring(0, 2)) <= HELPER_ChuyenDoi.getSoInt(lblGioPhutDi.getText().substring(0, 2))) {
                    lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString((diffInDay - 1) * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 + tongTienDichVu() - BLL_ChiTietDichVu.countGiamGiaByPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()))) + "K");
                } else {
                    lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(diffInDay * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10 + tongTienDichVu() - BLL_ChiTietDichVu.countGiamGiaByPhong(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText()))) + "K");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Định Dạng ???");
        }
    }

    public int tongTienDichVu() {
        return BLL_ChiTietDichVu.tongTienDichVu(BLL_DichVu.findMaPhieuThue(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText())));
    }

    public void tongTienConLai() {
        if (lblSetTrangThai.getText().equals("Trả Phòng")) {
            lblSetConLai.setText("0K");
        } else {
            lblSetConLai.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(lblSetTongTien.getText()) - HELPER_ChuyenDoi.getSoInt(lblSetDatCoc.getText()) - BLL_ChiTietDichVu.countThanhToan(BLL_DichVu.findMaPhieuThue(BLL_MaTenLoai.findMaPhong(lblSoPhong.getText())))) + "K");
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

        popMenu = new javax.swing.JPopupMenu();
        mniThongTinPhong = new javax.swing.JMenuItem();
        mniThuePhong = new javax.swing.JMenuItem();
        mniDonPhong = new javax.swing.JMenuItem();
        mniKhachRaNgoai = new javax.swing.JMenuItem();
        mniDatPhong = new javax.swing.JMenuItem();
        mniChuyenPhong = new javax.swing.JMenuItem();
        mniHuyPhong = new javax.swing.JMenuItem();
        sdoChiTietPhong = new HELPER.PanelShadow();
        lblGioPhutDi = new javax.swing.JLabel();
        spt_1 = new javax.swing.JSeparator();
        lblNgayDi = new javax.swing.JLabel();
        lblThangDi = new javax.swing.JLabel();
        lblSetDatCoc = new javax.swing.JLabel();
        lblDonPhong = new javax.swing.JLabel();
        lblDatCoc = new javax.swing.JLabel();
        spt_2 = new javax.swing.JSeparator();
        lblSetTongTien = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblSetConLai = new javax.swing.JLabel();
        lblGioPhutDen = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblConLai = new javax.swing.JLabel();
        lblThangDen = new javax.swing.JLabel();
        lblSetTrangThai = new javax.swing.JLabel();
        lblNgayDen = new javax.swing.JLabel();
        lblIconTrangThai = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblRaNgoai = new javax.swing.JLabel();
        lblTongThoiGian = new javax.swing.JLabel();
        lblNgayDefault = new javax.swing.JLabel();

        popMenu.setBackground(new java.awt.Color(255, 255, 255));
        popMenu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popMenu.setForeground(new java.awt.Color(62, 73, 95));
        popMenu.setComponentPopupMenu(popMenu);

        mniThongTinPhong.setBackground(new java.awt.Color(255, 255, 255));
        mniThongTinPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniThongTinPhong.setForeground(new java.awt.Color(62, 73, 95));
        mniThongTinPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/information-button.png"))); // NOI18N
        mniThongTinPhong.setText("Thông Tin Phòng");
        mniThongTinPhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniThongTinPhong.setOpaque(true);
        mniThongTinPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniThongTinPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniThongTinPhongMouseClicked(evt);
            }
        });
        mniThongTinPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongTinPhongActionPerformed(evt);
            }
        });
        popMenu.add(mniThongTinPhong);

        mniThuePhong.setBackground(new java.awt.Color(255, 255, 255));
        mniThuePhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniThuePhong.setForeground(new java.awt.Color(62, 73, 95));
        mniThuePhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/personal-profile.png"))); // NOI18N
        mniThuePhong.setText("Thuê Phòng");
        mniThuePhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniThuePhong.setOpaque(true);
        mniThuePhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniThuePhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniThuePhongMouseClicked(evt);
            }
        });
        mniThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThuePhongActionPerformed(evt);
            }
        });
        popMenu.add(mniThuePhong);

        mniDonPhong.setBackground(new java.awt.Color(255, 255, 255));
        mniDonPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniDonPhong.setForeground(new java.awt.Color(62, 73, 95));
        mniDonPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cleaning.png"))); // NOI18N
        mniDonPhong.setText("Dọn Phòng");
        mniDonPhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniDonPhong.setOpaque(true);
        mniDonPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniDonPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDonPhongActionPerformed(evt);
            }
        });
        popMenu.add(mniDonPhong);

        mniKhachRaNgoai.setBackground(new java.awt.Color(255, 255, 255));
        mniKhachRaNgoai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniKhachRaNgoai.setForeground(new java.awt.Color(62, 73, 95));
        mniKhachRaNgoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logout (8).png"))); // NOI18N
        mniKhachRaNgoai.setText("Khách Ra Ngoài");
        mniKhachRaNgoai.setMinimumSize(new java.awt.Dimension(150, 30));
        mniKhachRaNgoai.setOpaque(true);
        mniKhachRaNgoai.setPreferredSize(new java.awt.Dimension(150, 30));
        mniKhachRaNgoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhachRaNgoaiActionPerformed(evt);
            }
        });
        popMenu.add(mniKhachRaNgoai);

        mniDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        mniDatPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniDatPhong.setForeground(new java.awt.Color(62, 73, 95));
        mniDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/calendar.png"))); // NOI18N
        mniDatPhong.setText("Đặt Phòng");
        mniDatPhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniDatPhong.setOpaque(true);
        mniDatPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDatPhongActionPerformed(evt);
            }
        });
        popMenu.add(mniDatPhong);

        mniChuyenPhong.setBackground(new java.awt.Color(255, 255, 255));
        mniChuyenPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniChuyenPhong.setForeground(new java.awt.Color(62, 73, 95));
        mniChuyenPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/exchange (5).png"))); // NOI18N
        mniChuyenPhong.setText("Chuyển Phòng");
        mniChuyenPhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniChuyenPhong.setOpaque(true);
        mniChuyenPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniChuyenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniChuyenPhongActionPerformed(evt);
            }
        });
        popMenu.add(mniChuyenPhong);

        mniHuyPhong.setBackground(new java.awt.Color(255, 255, 255));
        mniHuyPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        mniHuyPhong.setForeground(new java.awt.Color(62, 73, 95));
        mniHuyPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/delete (1).png"))); // NOI18N
        mniHuyPhong.setText("Hủy Phòng");
        mniHuyPhong.setMinimumSize(new java.awt.Dimension(150, 30));
        mniHuyPhong.setOpaque(true);
        mniHuyPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        mniHuyPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuyPhongActionPerformed(evt);
            }
        });
        popMenu.add(mniHuyPhong);

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(220, 180));
        setPreferredSize(new java.awt.Dimension(220, 180));

        sdoChiTietPhong.setBackground(new java.awt.Color(97, 177, 90));
        sdoChiTietPhong.setComponentPopupMenu(popMenu);
        sdoChiTietPhong.setMinimumSize(new java.awt.Dimension(220, 180));
        sdoChiTietPhong.setPreferredSize(new java.awt.Dimension(220, 180));
        sdoChiTietPhong.setShadowOpacity(0.3F);
        sdoChiTietPhong.setShadowSize(3);
        sdoChiTietPhong.setShadowType(HELPER.ShadowType.BOT_RIGHT);
        sdoChiTietPhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                sdoChiTietPhongMouseMoved(evt);
            }
        });
        sdoChiTietPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sdoChiTietPhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sdoChiTietPhongMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sdoChiTietPhongMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sdoChiTietPhongMouseReleased(evt);
            }
        });
        sdoChiTietPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGioPhutDi.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDi.setForeground(new java.awt.Color(255, 255, 255));
        lblGioPhutDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblGioPhutDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 40, 20));
        sdoChiTietPhong.add(spt_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 20, 10));

        lblNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDi.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 20, 20));

        lblThangDi.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDi.setForeground(new java.awt.Color(255, 255, 255));
        lblThangDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblThangDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 20, 30));

        lblSetDatCoc.setBackground(new java.awt.Color(255, 255, 255));
        lblSetDatCoc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetDatCoc.setForeground(new java.awt.Color(255, 255, 255));
        sdoChiTietPhong.add(lblSetDatCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 50, 20));

        lblDonPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblDonPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/mop (1).png"))); // NOI18N
        lblDonPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblDonPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 30, 20));

        lblDatCoc.setBackground(new java.awt.Color(255, 255, 255));
        lblDatCoc.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblDatCoc.setForeground(new java.awt.Color(230, 230, 230));
        lblDatCoc.setText("Đặt Cọc");
        sdoChiTietPhong.add(lblDatCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, 20));
        sdoChiTietPhong.add(spt_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 20, 10));

        lblSetTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTongTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetTongTien.setForeground(new java.awt.Color(255, 255, 255));
        sdoChiTietPhong.add(lblSetTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, 20));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(230, 230, 230));
        lblTongTien.setText("Tổng Tiền");
        sdoChiTietPhong.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        lblSetConLai.setBackground(new java.awt.Color(255, 255, 255));
        lblSetConLai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetConLai.setForeground(new java.awt.Color(255, 255, 255));
        sdoChiTietPhong.add(lblSetConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 50, 20));

        lblGioPhutDen.setBackground(new java.awt.Color(255, 255, 255));
        lblGioPhutDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioPhutDen.setForeground(new java.awt.Color(255, 255, 255));
        lblGioPhutDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblGioPhutDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 40, 20));

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblLoaiPhong.setForeground(new java.awt.Color(230, 230, 230));
        sdoChiTietPhong.add(lblLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 20));

        lblConLai.setBackground(new java.awt.Color(255, 255, 255));
        lblConLai.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblConLai.setForeground(new java.awt.Color(230, 230, 230));
        lblConLai.setText("Còn Lại");
        sdoChiTietPhong.add(lblConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, 20));

        lblThangDen.setBackground(new java.awt.Color(255, 255, 255));
        lblThangDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThangDen.setForeground(new java.awt.Color(255, 255, 255));
        lblThangDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThangDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblThangDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 20, 30));

        lblSetTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTrangThai.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblSetTrangThai.setForeground(new java.awt.Color(230, 230, 230));
        lblSetTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblSetTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 70, 20));

        lblNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDen.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 20, 20));

        lblIconTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblIconTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/hotel-sign (2).png"))); // NOI18N
        sdoChiTietPhong.add(lblIconTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 30, 20));

        lblSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(255, 255, 255));
        sdoChiTietPhong.add(lblSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 20));

        lblRaNgoai.setBackground(new java.awt.Color(255, 255, 255));
        lblRaNgoai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRaNgoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logout (4).png"))); // NOI18N
        lblRaNgoai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblRaNgoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 30, 20));

        lblTongThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblTongThoiGian.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        lblTongThoiGian.setForeground(new java.awt.Color(62, 73, 95));
        lblTongThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongThoiGian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/circle (1).png"))); // NOI18N
        lblTongThoiGian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTongThoiGian.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseMoved(evt);
            }
        });
        lblTongThoiGian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseExited(evt);
            }
        });
        sdoChiTietPhong.add(lblTongThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 80, 80));

        lblNgayDefault.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDefault.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblNgayDefault.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayDefault.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sdoChiTietPhong.add(lblNgayDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sdoChiTietPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietPhongMouseClicked
        // TODO add your handling code here:
        indexPosition = GUI_pnl_SoDoPhong.pnlFormChinh.getComponentZOrder(sdoChiTietPhong);
        GUI_pnl_SoDoPhong.isThongTinPhong = false;
        if (lblSetTrangThai.getText().equals("Đặt Trước")) {
            isDatThue = true;
        } else {
            isDatThue = false;
        }
        if (evt.getClickCount() == 2) {
            new GUI_dal_ThongTinPhong(null, true).setVisible(true);
        }
    }//GEN-LAST:event_sdoChiTietPhongMouseClicked

    private void lblTongThoiGianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseExited
        // TODO add your handling code here:
        lblTongThoiGian.setForeground(new Color(62, 73, 95));
        sdoChiTietPhong.setBorder(null);
    }//GEN-LAST:event_lblTongThoiGianMouseExited

    private void lblTongThoiGianMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseMoved
        // TODO add your handling code here:
        lblTongThoiGian.setForeground(new Color(255, 102, 102));
        sdoChiTietPhong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(33, 150, 243)));
    }//GEN-LAST:event_lblTongThoiGianMouseMoved

    private void lblTongThoiGianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseClicked
        // TODO add your handling code here:
        if (!isShowHiddenTime) {
            isShowHiddenTime(true);
            isShowHiddenTime = true;
        } else {
            isShowHiddenTime(false);
            isShowHiddenTime = false;
        }
    }//GEN-LAST:event_lblTongThoiGianMouseClicked

    private void sdoChiTietPhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietPhongMouseMoved
        // TODO add your handling code here:
        sdoChiTietPhong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(33, 150, 243)));
    }//GEN-LAST:event_sdoChiTietPhongMouseMoved

    private void sdoChiTietPhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietPhongMouseExited
        // TODO add your handling code here:
        sdoChiTietPhong.setBorder(null);
    }//GEN-LAST:event_sdoChiTietPhongMouseExited

    private void sdoChiTietPhongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietPhongMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_sdoChiTietPhongMouseReleased

    private void mniThuePhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniThuePhongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mniThuePhongMouseClicked

    private void mniThuePhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThuePhongActionPerformed
        // TODO add your handling code here:  
        isDatThue = false;
        new GUI_dal_ThongTinPhong(null, true).setVisible(true);
    }//GEN-LAST:event_mniThuePhongActionPerformed

    private void mniDonPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDonPhongActionPerformed
        // TODO add your handling code here:
        if (lblSetTrangThai.getText().equals("Trả Phòng")) {
            if (!GUI_pnl_SoDoPhong.isSelectPhong) {
                DAL_ThuePhong.setTrangThaiPhong("PhongTrong", BLL_ThuePhong.findMaPhong(indexPosition + 1));
                DAL_ThuePhong.setThanhToan(BLL_ThuePhong.findMaPhong(indexPosition + 1));
                GUI_pnl_SoDoPhong.load();
            } else {
                DAL_ThuePhong.setTrangThaiPhong("PhongTrong", BLL_ThuePhong.findMaPhong(GUI_pnl_SoDoPhong.maTang, indexPosition + 1));
                DAL_ThuePhong.setThanhToan(BLL_ThuePhong.findMaPhong(GUI_pnl_SoDoPhong.maTang, indexPosition + 1));
                GUI_pnl_SoDoPhong.search();
            }
        } else {
            if (!isDonPhong) {
                lblDonPhong.setVisible(true);
                isDonPhong = true;
            } else {
                lblDonPhong.setVisible(false);
                isDonPhong = false;
            }
        }
    }//GEN-LAST:event_mniDonPhongActionPerformed

    private void mniKhachRaNgoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhachRaNgoaiActionPerformed
        // TODO add your handling code here:
        if (!isKhachRaNgoai) {
            lblRaNgoai.setVisible(true);
            isKhachRaNgoai = true;
        } else {
            lblRaNgoai.setVisible(false);
            isKhachRaNgoai = false;
        }
    }//GEN-LAST:event_mniKhachRaNgoaiActionPerformed

    private void mniThongTinPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniThongTinPhongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mniThongTinPhongMouseClicked

    private void mniThongTinPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongTinPhongActionPerformed
        // TODO add your handling code here:
        if (lblSetTrangThai.getText().equals("Đặt Trước")) {
            isDatThue = true;
        } else {
            isDatThue = false;
        }
        new GUI_dal_ThongTinPhong(null, true).setVisible(true);
    }//GEN-LAST:event_mniThongTinPhongActionPerformed

    private void mniDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDatPhongActionPerformed
        // TODO add your handling code here:
        isDatThue = true;
        new GUI_dal_ThongTinPhong(null, true).setVisible(true);
    }//GEN-LAST:event_mniDatPhongActionPerformed

    private void sdoChiTietPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietPhongMousePressed
        // TODO add your handling code here:
        indexPosition = GUI_pnl_SoDoPhong.pnlFormChinh.getComponentZOrder(sdoChiTietPhong);
    }//GEN-LAST:event_sdoChiTietPhongMousePressed

    private void mniHuyPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuyPhongActionPerformed
        // TODO add your handling code here:
        String maPhong = null;
        if (!lblSetTrangThai.getText().equals("Đặt Trước")) {
            if (diffInDay != 0 || diffInHours != 0 || diffInMinutes > 10) {
                JOptionPane.showMessageDialog(this, "Phòng Đã Sử Dụng Quá 10 Phút !!!");
                return;
            }
        }
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Hủy Phòng Không ?", "Hủy", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (!GUI_pnl_SoDoPhong.isSelectPhong) {
                maPhong = BLL_SoDoPhong.findMaPhong(indexPosition + 1);
                BLL_ThuePhong.delete(maPhong);
                GUI_pnl_SoDoPhong.load();
            } else {
                maPhong = BLL_SoDoPhong.findMaPhong(GUI_pnl_SoDoPhong.maTang, indexPosition + 1);
                BLL_ThuePhong.delete(maPhong);
                GUI_pnl_SoDoPhong.search();
            }
        }
        return;
    }//GEN-LAST:event_mniHuyPhongActionPerformed

    private void mniChuyenPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniChuyenPhongActionPerformed
        // TODO add your handling code here:
        GUI_dal_ChuyenPhong.conLai = HELPER_ChuyenDoi.getSoInt(lblSetConLai.getText());
        new GUI_dal_ChuyenPhong(null, true).setVisible(true);
    }//GEN-LAST:event_mniChuyenPhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lblConLai;
    public javax.swing.JLabel lblDatCoc;
    public javax.swing.JLabel lblDonPhong;
    public javax.swing.JLabel lblGioPhutDen;
    public javax.swing.JLabel lblGioPhutDi;
    public javax.swing.JLabel lblIconTrangThai;
    public static javax.swing.JLabel lblLoaiPhong;
    public javax.swing.JLabel lblNgayDefault;
    public javax.swing.JLabel lblNgayDen;
    public javax.swing.JLabel lblNgayDi;
    public javax.swing.JLabel lblRaNgoai;
    public javax.swing.JLabel lblSetConLai;
    public javax.swing.JLabel lblSetDatCoc;
    public javax.swing.JLabel lblSetTongTien;
    public javax.swing.JLabel lblSetTrangThai;
    public static javax.swing.JLabel lblSoPhong;
    public javax.swing.JLabel lblThangDen;
    public javax.swing.JLabel lblThangDi;
    public javax.swing.JLabel lblTongThoiGian;
    public javax.swing.JLabel lblTongTien;
    public javax.swing.JMenuItem mniChuyenPhong;
    public javax.swing.JMenuItem mniDatPhong;
    public javax.swing.JMenuItem mniDonPhong;
    public javax.swing.JMenuItem mniHuyPhong;
    public javax.swing.JMenuItem mniKhachRaNgoai;
    public javax.swing.JMenuItem mniThongTinPhong;
    public javax.swing.JMenuItem mniThuePhong;
    public javax.swing.JPopupMenu popMenu;
    public HELPER.PanelShadow sdoChiTietPhong;
    public javax.swing.JSeparator spt_1;
    public javax.swing.JSeparator spt_2;
    // End of variables declaration//GEN-END:variables
}
