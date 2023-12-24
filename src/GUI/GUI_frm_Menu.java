/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_MaTenLoai;
import BLL.BLL_ThongKe;
import DAL.DAL_TaiKhoan;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author CherryCe
 */
public class GUI_frm_Menu extends javax.swing.JFrame {

    static boolean isShowHidden = false;
    public boolean isWindow = false;
    public JLabel lbl;
    public String lblIcon;
    public String lblSoDoPhongIconClicked = "src/IMG/app.png";
    public String lblThuNganIconClicked = "src/IMG/money (1).png";
    public String lblLichSuThuePhongIconClicked = "src/IMG/hotel-sign (3).png";
    public String lblDanhSachDatPhongIconClicked = "src/IMG/booking (1).png";
    public String lblDichVuIconClicked = "src/IMG/minibar (3).png";
    public String lblThongKeTrongNgayIconClicked = "src/IMG/line-chart (1).png";
    public String lblChiTietGiaoCaIconClicked = "src/IMG/exchange (7).png";
    public String lblQuanLiSanPhamIconClicked = "src/IMG/box (1).png";
    public String lblQuanLiKhoIconClicked = "src/IMG/project-management (1).png";
    public String lblQuanLiChiPhiIconClicked = "src/IMG/assets (2).png";
    public String lblThietDatPhongIconClicked = "src/IMG/bed (2).png";
    public String lblThietDatGiaPhongIconClicked = "src/IMG/card (1).png";
    public String lblQuanLiNguoiDungIconClicked = "src/IMG/profile-user (3).png";
    public String lblThayDoiMatKhauIconClicked = "src/IMG/reset-password (3).png";
    public String lblDangXuatIconClicked = "src/IMG/logout (7).png";
    public String lblSoDoPhongIconExited = "src/IMG/show-apps-button.png";
    public String lblThuNganIconExited = "src/IMG/money.png";
    public String lblLichSuThuePhongIconExited = "src/IMG/hotel.png";
    public String lblDanhSachDatPhongIconExited = "src/IMG/booking.png";
    public String lblDichVuIconExited = "src/IMG/minibar (2).png";
    public String lblThongKeTrongNgayIconExited = "src/IMG/line-chart.png";
    public String lblChiTietGiaoCaIconExited = "src/IMG/exchange (6).png";
    public String lblQuanLiSanPhamIconExited = "src/IMG/box.png";
    public String lblQuanLiKhoIconExited = "src/IMG/project-management.png";
    public String lblQuanLiChiPhiIconExited = "src/IMG/assets (1).png";
    public String lblThietDatPhongIconExited = "src/IMG/bed (1).png";
    public String lblThietDatGiaPhongIconExited = "src/IMG/card.png";
    public String lblQuanLiNguoiDungIconExited = "src/IMG/profile-user (2).png";
    public String lblThayDoiMatKhauIconExited = "src/IMG/reset-password (1).png";
    public String lblDangXuatIconExited = "src/IMG/logout.png";

    /**
     * Creates new form GUI_frmMenu
     */
    public GUI_frm_Menu() {
        initComponents();
        setLocationRelativeTo(null);
        load();
        BLL_ThongKe.UpdateThongKe(new Date());
        BLL_ThongKe.UpdateThongKeLoaiPhong(new Date());
    }

    public static boolean auThenTiCaTion() {
        ResultSet rs = DAL_TaiKhoan.auThenTiCaTion(GUI_pnl_DangNhap.taiKhoan);
        try {
            while (rs.next()) {
                if (rs.getString("MaChucVu").equals("user")) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void load() {
        lblSoDoPhong.setIcon(new ImageIcon(lblSoDoPhongIconClicked));
        setForeground_33_150_243(lblSoDoPhong);
        lbl = lblSoDoPhong;
        lblIcon = lblSoDoPhongIconExited;
//        pnlFormChinh.removeAll();
//        pnlFormChinh.add(new GUI_pnl_SoDoPhong());
//        pnlFormChinh.validate();
//        pnlFormChinh.repaint();
    }

    public void menuItemClicked(JLabel lblClicked, String lblIconClicked, String lblIconExited, Component component) {
        lbl.setIcon(new ImageIcon(lblIcon));
        setForeground_255_255_255(lbl);
        lblClicked.setIcon(new ImageIcon(lblIconClicked));
        setForeground_33_150_243(lblClicked);
        lbl = lblClicked;
        lblIcon = lblIconExited;
        lblSetTenMenu.setText("Game2K - " + lbl.getText());
        pnlFormChinh.removeAll();
        pnlFormChinh.add(component);
        pnlFormChinh.validate();
        pnlFormChinh.repaint();
    }

    public void logOut() {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Đăng Xuất ???", "LogOut", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            new GUI_dal_Loading(null, true).setVisible(true);
            new GUI_frm_Login().setVisible(true);
        }
        return;
    }

    public void exit() {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Chương Trình ???", "Thoát", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        return;
    }

    public void setBackground_78_87_103(javax.swing.JLabel lbl) {
        lbl.setBackground(new Color(78, 87, 103));
    }

    public void setBackground_62_73_95(javax.swing.JLabel lbl) {
        lbl.setBackground(new Color(62, 73, 95));
    }

    public void setForeground_255_255_255(javax.swing.JLabel lbl) {
        lbl.setForeground(new Color(255, 255, 255));
    }

    public void setForeground_33_150_243(javax.swing.JLabel lbl) {
        lbl.setForeground(new Color(33, 150, 243));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlToolBar = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        lblWifi = new javax.swing.JLabel();
        lblSetTenMenu = new javax.swing.JLabel();
        lblWindow = new javax.swing.JLabel();
        pnlFormChinh = new javax.swing.JPanel();
        scrMenuBar = new javax.swing.JScrollPane();
        pnlMenuBar = new javax.swing.JPanel();
        lblShowHiddenMenu = new javax.swing.JLabel();
        lblThuNgan = new javax.swing.JLabel();
        lblQuanLiKho = new javax.swing.JLabel();
        lblSoDoPhong = new javax.swing.JLabel();
        lblLichSuThuePhong = new javax.swing.JLabel();
        lblDichVu = new javax.swing.JLabel();
        lblThietDatPhong = new javax.swing.JLabel();
        lblQuanLiChiPhi = new javax.swing.JLabel();
        lblThietDatGiaPhong = new javax.swing.JLabel();
        lblThayDoiMatKhau = new javax.swing.JLabel();
        lblThongKeTrongNgay = new javax.swing.JLabel();
        lblDangXuat = new javax.swing.JLabel();
        lblQuanLiNguoiDung = new javax.swing.JLabel();
        lblQuanLiSanPham = new javax.swing.JLabel();
        lblChiTietGiaoCa = new javax.swing.JLabel();
        lblDanhSachDatPhong = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1360, 760));
        setUndecorated(true);

        pnlToolBar.setBackground(new java.awt.Color(62, 73, 95));
        pnlToolBar.setMinimumSize(new java.awt.Dimension(1360, 30));
        pnlToolBar.setPreferredSize(new java.awt.Dimension(1360, 30));
        pnlToolBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExit.setBackground(new java.awt.Color(62, 73, 95));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cancel.png"))); // NOI18N
        lblExit.setOpaque(true);
        lblExit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExitMouseMoved(evt);
            }
        });
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
        });
        pnlToolBar.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 30, 30));

        lblMinimize.setBackground(new java.awt.Color(62, 73, 95));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/minus-sign.png"))); // NOI18N
        lblMinimize.setOpaque(true);
        lblMinimize.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseMoved(evt);
            }
        });
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
        });
        pnlToolBar.add(lblMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 30, 30));

        lblWifi.setBackground(new java.awt.Color(255, 255, 255));
        lblWifi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/wifi.png"))); // NOI18N
        pnlToolBar.add(lblWifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 30, 30));

        lblSetTenMenu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTenMenu.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblSetTenMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTenMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetTenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/hotel (1).png"))); // NOI18N
        lblSetTenMenu.setText("Game2K");
        pnlToolBar.add(lblSetTenMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        lblWindow.setBackground(new java.awt.Color(62, 73, 95));
        lblWindow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/full-screen.png"))); // NOI18N
        lblWindow.setOpaque(true);
        lblWindow.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblWindowMouseMoved(evt);
            }
        });
        lblWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblWindowMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblWindowMouseExited(evt);
            }
        });
        pnlToolBar.add(lblWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 0, 30, 30));

        pnlFormChinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormChinh.setMinimumSize(new java.awt.Dimension(1160, 730));
        pnlFormChinh.setPreferredSize(new java.awt.Dimension(1160, 730));

        scrMenuBar.setBackground(new java.awt.Color(255, 255, 255));
        scrMenuBar.setBorder(null);
        scrMenuBar.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlMenuBar.setBackground(new java.awt.Color(62, 73, 95));
        pnlMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblShowHiddenMenu.setBackground(new java.awt.Color(62, 73, 95));
        lblShowHiddenMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowHiddenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/more.png"))); // NOI18N
        lblShowHiddenMenu.setOpaque(true);
        lblShowHiddenMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblShowHiddenMenuMouseMoved(evt);
            }
        });
        lblShowHiddenMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowHiddenMenuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblShowHiddenMenuMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblShowHiddenMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 30, 30));

        lblThuNgan.setBackground(new java.awt.Color(62, 73, 95));
        lblThuNgan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThuNgan.setForeground(new java.awt.Color(255, 255, 255));
        lblThuNgan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/money.png"))); // NOI18N
        lblThuNgan.setText("Thu Ngân");
        lblThuNgan.setOpaque(true);
        lblThuNgan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThuNganMouseMoved(evt);
            }
        });
        lblThuNgan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThuNganMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThuNganMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblThuNgan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 190, 40));

        lblQuanLiKho.setBackground(new java.awt.Color(62, 73, 95));
        lblQuanLiKho.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblQuanLiKho.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLiKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/project-management.png"))); // NOI18N
        lblQuanLiKho.setText("Quản Lí Kho");
        lblQuanLiKho.setOpaque(true);
        lblQuanLiKho.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblQuanLiKhoMouseMoved(evt);
            }
        });
        lblQuanLiKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLiKhoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuanLiKhoMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblQuanLiKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 190, 40));

        lblSoDoPhong.setBackground(new java.awt.Color(62, 73, 95));
        lblSoDoPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoDoPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoDoPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/show-apps-button.png"))); // NOI18N
        lblSoDoPhong.setLabelFor(lblSoDoPhong);
        lblSoDoPhong.setText("Sơ Đồ Phòng");
        lblSoDoPhong.setOpaque(true);
        lblSoDoPhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSoDoPhongMouseMoved(evt);
            }
        });
        lblSoDoPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSoDoPhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSoDoPhongMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblSoDoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 40));

        lblLichSuThuePhong.setBackground(new java.awt.Color(62, 73, 95));
        lblLichSuThuePhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLichSuThuePhong.setForeground(new java.awt.Color(255, 255, 255));
        lblLichSuThuePhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/hotel.png"))); // NOI18N
        lblLichSuThuePhong.setText("Lịch Sử Thuê Phòng");
        lblLichSuThuePhong.setOpaque(true);
        lblLichSuThuePhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblLichSuThuePhongMouseMoved(evt);
            }
        });
        lblLichSuThuePhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLichSuThuePhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLichSuThuePhongMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblLichSuThuePhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 190, 40));

        lblDichVu.setBackground(new java.awt.Color(62, 73, 95));
        lblDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDichVu.setForeground(new java.awt.Color(255, 255, 255));
        lblDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/minibar (2).png"))); // NOI18N
        lblDichVu.setText("Minibar - Dịch Vụ");
        lblDichVu.setOpaque(true);
        lblDichVu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDichVuMouseMoved(evt);
            }
        });
        lblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDichVuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDichVuMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 190, 40));

        lblThietDatPhong.setBackground(new java.awt.Color(62, 73, 95));
        lblThietDatPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThietDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblThietDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/bed (1).png"))); // NOI18N
        lblThietDatPhong.setText("Thiết Đặt Phòng");
        lblThietDatPhong.setOpaque(true);
        lblThietDatPhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThietDatPhongMouseMoved(evt);
            }
        });
        lblThietDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThietDatPhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThietDatPhongMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblThietDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 190, 40));

        lblQuanLiChiPhi.setBackground(new java.awt.Color(62, 73, 95));
        lblQuanLiChiPhi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblQuanLiChiPhi.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLiChiPhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/assets (1).png"))); // NOI18N
        lblQuanLiChiPhi.setText("Quản Lí Chi Phí");
        lblQuanLiChiPhi.setOpaque(true);
        lblQuanLiChiPhi.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblQuanLiChiPhiMouseMoved(evt);
            }
        });
        lblQuanLiChiPhi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLiChiPhiMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuanLiChiPhiMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblQuanLiChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 190, 40));

        lblThietDatGiaPhong.setBackground(new java.awt.Color(62, 73, 95));
        lblThietDatGiaPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThietDatGiaPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblThietDatGiaPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/card.png"))); // NOI18N
        lblThietDatGiaPhong.setText("Thiết Đặt Giá Phòng");
        lblThietDatGiaPhong.setOpaque(true);
        lblThietDatGiaPhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThietDatGiaPhongMouseMoved(evt);
            }
        });
        lblThietDatGiaPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThietDatGiaPhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThietDatGiaPhongMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblThietDatGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 190, 40));

        lblThayDoiMatKhau.setBackground(new java.awt.Color(62, 73, 95));
        lblThayDoiMatKhau.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThayDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblThayDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reset-password (1).png"))); // NOI18N
        lblThayDoiMatKhau.setText("Thay Đổi Mật Khẩu");
        lblThayDoiMatKhau.setOpaque(true);
        lblThayDoiMatKhau.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThayDoiMatKhauMouseMoved(evt);
            }
        });
        lblThayDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThayDoiMatKhauMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThayDoiMatKhauMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblThayDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 190, 40));

        lblThongKeTrongNgay.setBackground(new java.awt.Color(62, 73, 95));
        lblThongKeTrongNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThongKeTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKeTrongNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/line-chart.png"))); // NOI18N
        lblThongKeTrongNgay.setText("Thống Kê Trong Ngày");
        lblThongKeTrongNgay.setOpaque(true);
        lblThongKeTrongNgay.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThongKeTrongNgayMouseMoved(evt);
            }
        });
        lblThongKeTrongNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeTrongNgayMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThongKeTrongNgayMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblThongKeTrongNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 190, 40));

        lblDangXuat.setBackground(new java.awt.Color(62, 73, 95));
        lblDangXuat.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lblDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logout.png"))); // NOI18N
        lblDangXuat.setText("Đăng Xuất");
        lblDangXuat.setOpaque(true);
        lblDangXuat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseMoved(evt);
            }
        });
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 760, 190, 40));

        lblQuanLiNguoiDung.setBackground(new java.awt.Color(62, 73, 95));
        lblQuanLiNguoiDung.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblQuanLiNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLiNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/profile-user (2).png"))); // NOI18N
        lblQuanLiNguoiDung.setText("Quản Lí Người Dùng");
        lblQuanLiNguoiDung.setOpaque(true);
        lblQuanLiNguoiDung.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblQuanLiNguoiDungMouseMoved(evt);
            }
        });
        lblQuanLiNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLiNguoiDungMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuanLiNguoiDungMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblQuanLiNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 190, 40));

        lblQuanLiSanPham.setBackground(new java.awt.Color(62, 73, 95));
        lblQuanLiSanPham.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblQuanLiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/box.png"))); // NOI18N
        lblQuanLiSanPham.setText("Quản Lí  Sản Phẩm");
        lblQuanLiSanPham.setOpaque(true);
        lblQuanLiSanPham.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblQuanLiSanPhamMouseMoved(evt);
            }
        });
        lblQuanLiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLiSanPhamMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblQuanLiSanPhamMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblQuanLiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 190, 40));

        lblChiTietGiaoCa.setBackground(new java.awt.Color(62, 73, 95));
        lblChiTietGiaoCa.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblChiTietGiaoCa.setForeground(new java.awt.Color(255, 255, 255));
        lblChiTietGiaoCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/exchange (6).png"))); // NOI18N
        lblChiTietGiaoCa.setText("Chi Tiết Giao Ca");
        lblChiTietGiaoCa.setOpaque(true);
        lblChiTietGiaoCa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblChiTietGiaoCaMouseMoved(evt);
            }
        });
        lblChiTietGiaoCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChiTietGiaoCaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblChiTietGiaoCaMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblChiTietGiaoCa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 190, 40));

        lblDanhSachDatPhong.setBackground(new java.awt.Color(62, 73, 95));
        lblDanhSachDatPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDanhSachDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblDanhSachDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/booking.png"))); // NOI18N
        lblDanhSachDatPhong.setText("Danh Sách Đặt Phòng");
        lblDanhSachDatPhong.setOpaque(true);
        lblDanhSachDatPhong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDanhSachDatPhongMouseMoved(evt);
            }
        });
        lblDanhSachDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhSachDatPhongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDanhSachDatPhongMouseExited(evt);
            }
        });
        pnlMenuBar.add(lblDanhSachDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 190, 40));

        scrMenuBar.setViewportView(pnlMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblSoDoPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoDoPhongMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblSoDoPhong, lblSoDoPhongIconClicked, lblSoDoPhongIconExited, new GUI_pnl_SoDoPhong());
    }//GEN-LAST:event_lblSoDoPhongMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblThuNganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuNganMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblThuNgan, lblThuNganIconClicked, lblThuNganIconExited, new GUI_pnl_ThuNgan());
    }//GEN-LAST:event_lblThuNganMouseClicked

    private void lblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDichVuMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblDichVu, lblDichVuIconClicked, lblDichVuIconExited, new GUI_pnl_DichVu());
    }//GEN-LAST:event_lblDichVuMouseClicked

    private void lblThietDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatPhongMouseClicked
        // TODO add your handling code here:       
        menuItemClicked(lblThietDatPhong, lblThietDatPhongIconClicked, lblThietDatPhongIconExited, new GUI_pnl_ThietDatPhong());
    }//GEN-LAST:event_lblThietDatPhongMouseClicked

    private void lblThietDatGiaPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatGiaPhongMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblThietDatGiaPhong, lblThietDatGiaPhongIconClicked, lblThietDatGiaPhongIconExited, new GUI_pnl_GiaPhong());
    }//GEN-LAST:event_lblThietDatGiaPhongMouseClicked

    private void lblQuanLiNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiNguoiDungMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblQuanLiNguoiDung, lblQuanLiNguoiDungIconClicked, lblQuanLiNguoiDungIconExited, new GUI_pnl_NhanVien());
    }//GEN-LAST:event_lblQuanLiNguoiDungMouseClicked

    private void lblSoDoPhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoDoPhongMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblSoDoPhong);
    }//GEN-LAST:event_lblSoDoPhongMouseMoved

    private void lblSoDoPhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoDoPhongMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblSoDoPhong);
    }//GEN-LAST:event_lblSoDoPhongMouseExited

    private void lblThayDoiMatKhauMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThayDoiMatKhauMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblThayDoiMatKhau);
    }//GEN-LAST:event_lblThayDoiMatKhauMouseMoved

    private void lblThayDoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThayDoiMatKhauMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblThayDoiMatKhau);
    }//GEN-LAST:event_lblThayDoiMatKhauMouseExited

    private void lblLichSuThuePhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuThuePhongMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblLichSuThuePhong, lblLichSuThuePhongIconClicked, lblLichSuThuePhongIconExited, new GUI_pnl_ThuePhong());
    }//GEN-LAST:event_lblLichSuThuePhongMouseClicked

    private void lblThuNganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuNganMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblThuNgan);
    }//GEN-LAST:event_lblThuNganMouseExited

    private void lblThuNganMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThuNganMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblThuNgan);
    }//GEN-LAST:event_lblThuNganMouseMoved

    private void lblLichSuThuePhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuThuePhongMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblLichSuThuePhong);
    }//GEN-LAST:event_lblLichSuThuePhongMouseExited

    private void lblLichSuThuePhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuThuePhongMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblLichSuThuePhong);
    }//GEN-LAST:event_lblLichSuThuePhongMouseMoved

    private void lblDichVuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDichVuMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblDichVu);
    }//GEN-LAST:event_lblDichVuMouseExited

    private void lblDichVuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDichVuMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblDichVu);
    }//GEN-LAST:event_lblDichVuMouseMoved

    private void lblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblDangXuat);
    }//GEN-LAST:event_lblDangXuatMouseExited

    private void lblDangXuatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblDangXuat);
    }//GEN-LAST:event_lblDangXuatMouseMoved

    private void lblQuanLiNguoiDungMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiNguoiDungMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblQuanLiNguoiDung);
    }//GEN-LAST:event_lblQuanLiNguoiDungMouseMoved

    private void lblQuanLiNguoiDungMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiNguoiDungMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblQuanLiNguoiDung);
    }//GEN-LAST:event_lblQuanLiNguoiDungMouseExited

    private void lblQuanLiKhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiKhoMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblQuanLiKho);
    }//GEN-LAST:event_lblQuanLiKhoMouseExited

    private void lblQuanLiKhoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiKhoMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblQuanLiKho);
    }//GEN-LAST:event_lblQuanLiKhoMouseMoved

    private void lblThongKeTrongNgayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeTrongNgayMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblThongKeTrongNgay);
    }//GEN-LAST:event_lblThongKeTrongNgayMouseExited

    private void lblThongKeTrongNgayMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeTrongNgayMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblThongKeTrongNgay);
    }//GEN-LAST:event_lblThongKeTrongNgayMouseMoved

    private void lblQuanLiChiPhiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiChiPhiMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblQuanLiChiPhi);
    }//GEN-LAST:event_lblQuanLiChiPhiMouseExited

    private void lblQuanLiChiPhiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiChiPhiMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblQuanLiChiPhi);
    }//GEN-LAST:event_lblQuanLiChiPhiMouseMoved

    private void lblThietDatPhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatPhongMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblThietDatPhong);
    }//GEN-LAST:event_lblThietDatPhongMouseExited

    private void lblThietDatPhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatPhongMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblThietDatPhong);
    }//GEN-LAST:event_lblThietDatPhongMouseMoved

    private void lblThietDatGiaPhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatGiaPhongMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblThietDatGiaPhong);
    }//GEN-LAST:event_lblThietDatGiaPhongMouseExited

    private void lblThietDatGiaPhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietDatGiaPhongMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblThietDatGiaPhong);
    }//GEN-LAST:event_lblThietDatGiaPhongMouseMoved

    private void lblShowHiddenMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowHiddenMenuMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblShowHiddenMenu);
    }//GEN-LAST:event_lblShowHiddenMenuMouseExited

    private void lblShowHiddenMenuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowHiddenMenuMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblShowHiddenMenu);
    }//GEN-LAST:event_lblShowHiddenMenuMouseMoved

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblMinimize);
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblMinimizeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblMinimize);
    }//GEN-LAST:event_lblMinimizeMouseMoved

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblExit);
    }//GEN-LAST:event_lblExitMouseExited

    private void lblExitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblExit);
    }//GEN-LAST:event_lblExitMouseMoved

    private void lblThongKeTrongNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeTrongNgayMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblThongKeTrongNgay, lblThongKeTrongNgayIconClicked, lblThongKeTrongNgayIconExited, new GUI_pnl_ThongKe());
    }//GEN-LAST:event_lblThongKeTrongNgayMouseClicked

    private void lblQuanLiKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiKhoMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblQuanLiKho, lblQuanLiKhoIconClicked, lblQuanLiKhoIconExited, new GUI_pnl_QuanLiKho());
    }//GEN-LAST:event_lblQuanLiKhoMouseClicked

    private void lblQuanLiChiPhiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiChiPhiMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblQuanLiChiPhi, lblQuanLiChiPhiIconClicked, lblQuanLiChiPhiIconExited, new GUI_pnl_QuanLiChiPhi());
    }//GEN-LAST:event_lblQuanLiChiPhiMouseClicked

    private void lblThayDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThayDoiMatKhauMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblThayDoiMatKhau, lblThayDoiMatKhauIconClicked, lblThayDoiMatKhauIconExited, new GUI_pnl_Image().pnlImage);
        new GUI_dal_TaiKhoan(this, true).setVisible(true);
    }//GEN-LAST:event_lblThayDoiMatKhauMouseClicked

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblDangXuat, lblDangXuatIconClicked, lblDangXuatIconExited, new GUI_pnl_Image().pnlImage);
        logOut();
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void lblShowHiddenMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowHiddenMenuMouseClicked
        // TODO add your handling code here:
        if (!isShowHidden) {
            lblSoDoPhong.setText(null);
            lblThuNgan.setText(null);
            lblLichSuThuePhong.setText(null);
            lblDanhSachDatPhong.setText(null);
            lblDichVu.setText(null);
            lblQuanLiSanPham.setText(null);
            lblThongKeTrongNgay.setText(null);
            lblChiTietGiaoCa.setText(null);
            lblQuanLiKho.setText(null);
            lblQuanLiChiPhi.setText(null);
            lblThietDatPhong.setText(null);
            lblThietDatGiaPhong.setText(null);
            lblQuanLiNguoiDung.setText(null);
            lblThayDoiMatKhau.setText(null);
            lblDangXuat.setText(null);
            isShowHidden = true;
        } else {
            lblSoDoPhong.setText("Sơ Đồ Phòng");
            lblThuNgan.setText("Thu Ngân");
            lblLichSuThuePhong.setText("Lịch Sử Thuê Phòng");
            lblDanhSachDatPhong.setText("Danh Sách Đặt Phòng");
            lblDichVu.setText("Minibar - Dịch Vụ");
            lblQuanLiSanPham.setText("Giao Ca");
            lblThongKeTrongNgay.setText("Thống Kê Trong Ngày");
            lblChiTietGiaoCa.setText("Chi Tiết Giao Ca");
            lblQuanLiKho.setText("Quản Lí Kho");
            lblQuanLiChiPhi.setText("Quản Lí Chi Phí");
            lblThietDatPhong.setText("Thiết Đặt Phòng");
            lblThietDatGiaPhong.setText("Thiết Đặt Giá Phòng");
            lblQuanLiNguoiDung.setText("Quản Lí Người Dùng");
            lblThayDoiMatKhau.setText("Thay Đổi Mật Khẩu");
            lblDangXuat.setText("Đăng Xuất");
            isShowHidden = false;
        }
    }//GEN-LAST:event_lblShowHiddenMenuMouseClicked

    private void lblQuanLiSanPhamMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiSanPhamMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblQuanLiSanPham);
    }//GEN-LAST:event_lblQuanLiSanPhamMouseMoved

    private void lblQuanLiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiSanPhamMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblQuanLiSanPham, lblQuanLiSanPhamIconClicked, lblQuanLiSanPhamIconExited, new GUI_pnl_SanPham());
    }//GEN-LAST:event_lblQuanLiSanPhamMouseClicked

    private void lblQuanLiSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLiSanPhamMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblQuanLiSanPham);
    }//GEN-LAST:event_lblQuanLiSanPhamMouseExited

    private void lblChiTietGiaoCaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChiTietGiaoCaMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblChiTietGiaoCa);
    }//GEN-LAST:event_lblChiTietGiaoCaMouseMoved

    private void lblChiTietGiaoCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChiTietGiaoCaMouseClicked
        // TODO add your handling code here:
//        menuItemClicked(lblChiTietGiaoCa, lblChiTietGiaoCaIconClicked, lblChiTietGiaoCaIconExited);
    }//GEN-LAST:event_lblChiTietGiaoCaMouseClicked

    private void lblChiTietGiaoCaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChiTietGiaoCaMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblChiTietGiaoCa);
    }//GEN-LAST:event_lblChiTietGiaoCaMouseExited

    private void lblDanhSachDatPhongMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhSachDatPhongMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblDanhSachDatPhong);
    }//GEN-LAST:event_lblDanhSachDatPhongMouseMoved

    private void lblDanhSachDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhSachDatPhongMouseClicked
        // TODO add your handling code here:
        menuItemClicked(lblDanhSachDatPhong, lblDanhSachDatPhongIconClicked, lblDanhSachDatPhongIconExited, new GUI_pnl_DatPhong());
    }//GEN-LAST:event_lblDanhSachDatPhongMouseClicked

    private void lblDanhSachDatPhongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhSachDatPhongMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblDanhSachDatPhong);
    }//GEN-LAST:event_lblDanhSachDatPhongMouseExited

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblWindowMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWindowMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblWindow);
    }//GEN-LAST:event_lblWindowMouseMoved

    private void lblWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWindowMouseClicked
        // TODO add your handling code here:
        if (!isWindow) {
            this.setExtendedState(MAXIMIZED_BOTH);
            isWindow = true;
            lblWindow.setIcon(new ImageIcon("src/IMG/restore-down.png"));
        } else {
            this.setExtendedState(NORMAL);
            isWindow = false;
            lblWindow.setIcon(new ImageIcon("src/IMG/full-screen.png"));
        }
    }//GEN-LAST:event_lblWindowMouseClicked

    private void lblWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWindowMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblWindow);
    }//GEN-LAST:event_lblWindowMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_frm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_frm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_frm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_frm_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_frm_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblChiTietGiaoCa;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDanhSachDatPhong;
    private javax.swing.JLabel lblDichVu;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblLichSuThuePhong;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblQuanLiChiPhi;
    private javax.swing.JLabel lblQuanLiKho;
    private javax.swing.JLabel lblQuanLiNguoiDung;
    private javax.swing.JLabel lblQuanLiSanPham;
    private javax.swing.JLabel lblSetTenMenu;
    private javax.swing.JLabel lblShowHiddenMenu;
    private javax.swing.JLabel lblSoDoPhong;
    private javax.swing.JLabel lblThayDoiMatKhau;
    private javax.swing.JLabel lblThietDatGiaPhong;
    private javax.swing.JLabel lblThietDatPhong;
    private javax.swing.JLabel lblThongKeTrongNgay;
    private javax.swing.JLabel lblThuNgan;
    private javax.swing.JLabel lblWifi;
    private javax.swing.JLabel lblWindow;
    public javax.swing.JPanel pnlFormChinh;
    private javax.swing.JPanel pnlMenuBar;
    private javax.swing.JPanel pnlToolBar;
    private javax.swing.JScrollPane scrMenuBar;
    // End of variables declaration//GEN-END:variables

}
