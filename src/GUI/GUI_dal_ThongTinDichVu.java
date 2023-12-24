/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DichVu;
import BLL.BLL_LoaiSanPham;
import BLL.BLL_MaTenLoai;
import BLL.BLL_SanPham;
import BLL.BLL_TaiKhoan;
import DAL.DAL_ChiTietDichVu;
import DAL.DAL_DichVu;
import DTO.DTO_ChiTietDichVu;
import DTO.DTO_DichVu;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import HELPER.HELPER_SetIcon;
import HELPER.HELPER_SetMa;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author CherryCe
 */
public class GUI_dal_ThongTinDichVu extends javax.swing.JDialog {

    public int row;
    public int column;
    public static String str_1;
    public static String str_2;
    public static String str_3;
    public boolean isUpgrade = false;
    public boolean isAddEdit = false;

    /**
     * Creates new form GUI_dalThongTinPhong
     */
    public GUI_dal_ThongTinDichVu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        check();
        loadSanPham();
        loadTenLoaiSanPham();
        loadChiTietDichVu();
        setTongTien();
        setThanhToan();
    }

    public void check() {
        if (GUI_pnl_ChiTietDichVu.isDichVu) {
            loadPhieuDichVu();
            lblInPhieu.setVisible(false);
            if (!GUI_frm_Menu.auThenTiCaTion()) {
                lblCapNhat.setVisible(false);
            } else {
                lblCapNhat.setText("Xóa");
                lblCapNhat.setIcon(new ImageIcon("src/IMG/delete.png"));
                isUpgrade = true;
                GUI_pnl_ChiTietDichVu.isDichVu = false;
            }
        } else {
            load();
            lblInPhieu.setVisible(true);
            lblCapNhat.setText("Cập Nhật");
            lblCapNhat.setIcon(new ImageIcon("src/IMG/upgrade (3).png"));
            isUpgrade = false;
            GUI_pnl_ChiTietDichVu.isDichVu = false;
        }
    }

    public void addChiTietDichVu(int row) {
        DTO_ChiTietDichVu chiTietDichVu = new DTO_ChiTietDichVu(HELPER_SetMa.setMaDateTime("CT", DAL_ChiTietDichVu.count(HELPER_ChuyenDoi.getTimeNow("yyMMdd"))), lblSetMaPhieu.getText(), tblDichVu.getValueAt(row, 0).toString(), HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()), HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()));
        BLL_ChiTietDichVu.add(chiTietDichVu);
    }

    public void loadSanPham() {
        ArrayList<DTO_SanPham> array = BLL_SanPham.select();
        BLL_SanPham.loadKhoDichVu(array, tblKhoDichVu);
    }

    public void loadTenLoaiSanPham() {
        BLL_LoaiSanPham.loadTenLoaiDichVu();
        lbl_1.setText(str_1);
        lbl_2.setText(str_2);
        lbl_3.setText(str_3);
    }

    public void loadSanPham(String maLoaiSanPham) {
        ArrayList<DTO_SanPham> array = BLL_SanPham.select(maLoaiSanPham);
        BLL_SanPham.loadKhoDichVu(array, tblKhoDichVu);
    }

    public void loadChiTietDichVu() {
        ArrayList<DTO_ChiTietDichVu> arrayChiTiet = BLL_ChiTietDichVu.select(lblSetMaPhieu.getText());
        BLL_ChiTietDichVu.load(arrayChiTiet, tblDichVu);
    }

    public void loadPhieuDichVu() {
        ArrayList<DTO_DichVu> arrayDichVu = BLL_DichVu.search(GUI_pnl_DichVu.tuNgay, GUI_pnl_DichVu.denNgay, GUI_pnl_ChiTietDichVu.indexPosition + 1);
        BLL_DichVu.loadChiTietDichVu(arrayDichVu, lblSetMaPhieu, lblSetSoPhong, lblSetNhanVien, lblSetNgayTao, lblSetNgayDen, lblSetNgayDi, txtGhiChu);
    }

    public void addDichVu() {
        DTO_DichVu dichVu = new DTO_DichVu(lblSetMaPhieu.getText(), BLL_DichVu.findMaPhieuThue(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText())), lblSetNhanVien.getText(), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", lblSetNgayTao.getText()), txtGhiChu.getText(), 0);
        BLL_DichVu.add(dichVu);
    }

    public void deleteDichVu() {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maPhieuDichVu = lblSetMaPhieu.getText();
            BLL_ChiTietDichVu.delete(maPhieuDichVu);
            BLL_DichVu.delete(maPhieuDichVu);
        }
        return;
    }

    public void load() {
        lblSetSoPhong.setText(null);
        lblSetMaPhieu.setText(HELPER_SetMa.setMaDateTime("DV"));
        lblSetNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(BLL_TaiKhoan.selectMaNhanVien(GUI_pnl_DangNhap.taiKhoan)));
        lblSetNgayDen.setText(null);
        lblSetNgayDi.setText(null);
        lblSetNgayTao.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
    }

    public void addRow(int row) {
        DefaultTableModel tblModel = (DefaultTableModel) tblDichVu.getModel();
        Object obj[] = new Object[5];
        obj[0] = tblKhoDichVu.getValueAt(row, 0).toString();
        obj[1] = tblKhoDichVu.getValueAt(row, 1).toString();
        obj[2] = 1;
        obj[3] = tblKhoDichVu.getValueAt(row, 2).toString();
        obj[4] = HELPER_ChuyenDoi.getSoInt(tblKhoDichVu.getValueAt(row, 2).toString()) * HELPER_ChuyenDoi.getSoInt(obj[2].toString()) + "K";
        tblDichVu.getColumnModel().getColumn(5).setCellRenderer(new HELPER_SetIcon.iconDelete());
        tblModel.addRow(obj);
    }

    public void editRow(int row) {
        DefaultTableModel tblModel = (DefaultTableModel) tblDichVu.getModel();
        tblModel.setValueAt(HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) + 1, row, 2);
        tblModel.setValueAt(HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) * HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()) + "K", row, 4);
    }

    public void deleteRow(int row) {
        DefaultTableModel tblModel = (DefaultTableModel) tblDichVu.getModel();
        if (HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) != 1) {
            tblModel.setValueAt(HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) - 1, row, 2);
            tblModel.setValueAt(HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) * HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()) + "K", row, 4);
        } else {
            tblModel.removeRow(row);
        }
    }

    public void setTongTien() {
        int total = 0;
        for (int i = 0; i < tblDichVu.getRowCount(); i++) {
            total += HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(i, 4).toString());
        }
        lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(total) + "K");
    }

    public void setThanhToan() {
        if (BLL_DichVu.findThanhToan(lblSetMaPhieu.getText()) == 0) {
            lblSetDaThanhToan.setText("0K");
        } else {
            lblSetDaThanhToan.setText(lblSetTongTien.getText());
        }
    }

    public void xuatPhieuDichVu(String maPhieuDichVu) {
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/GUI/GUI_rpt_PhieuDichVu.jrxml");
            map.put("MaPhieuDichVu", maPhieuDichVu);
            JasperPrint p = JasperFillManager.fillReport(report, map, HELPER_ConnectSQL.conn);
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, "PhieuDichVu.pdf");
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

        sdoThongTinPhong = new HELPER.PanelShadow();
        lblSetMaPhieu = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblSetNhanVien = new javax.swing.JLabel();
        lblSetSoPhong = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblNgayDi = new javax.swing.JLabel();
        lblNgayDen = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblPhieuBanHang = new javax.swing.JLabel();
        lblSetNgayDen = new javax.swing.JLabel();
        lblSetNgayTao = new javax.swing.JLabel();
        lblSetNgayDi = new javax.swing.JLabel();
        lbl_1 = new javax.swing.JLabel();
        lbl_3 = new javax.swing.JLabel();
        lblAll = new javax.swing.JLabel();
        lbl_2 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        lblExit = new javax.swing.JLabel();
        sdoKhoDichVu = new HELPER.PanelShadow();
        scrKhoDichVu = new javax.swing.JScrollPane();
        tblKhoDichVu = new javax.swing.JTable();
        sdoDichVu = new HELPER.PanelShadow();
        scrDichVu = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        sdoChucNang = new HELPER.PanelShadow();
        lblSetDaThanhToan = new javax.swing.JLabel();
        lblSetTongTien = new javax.swing.JLabel();
        lblDaThanhToan = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblCapNhat = new javax.swing.JLabel();
        lblInPhieu = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(860, 610));
        setUndecorated(true);

        sdoThongTinPhong.setBackground(new java.awt.Color(255, 255, 255));
        sdoThongTinPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(33, 150, 243)));
        sdoThongTinPhong.setMinimumSize(new java.awt.Dimension(860, 130));
        sdoThongTinPhong.setPreferredSize(new java.awt.Dimension(860, 130));
        sdoThongTinPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSetMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetMaPhieu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetMaPhieu.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 100, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoThongTinPhong.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        lblSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblSoPhong.setText("Số Phòng");
        sdoThongTinPhong.add(lblSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        lblSetNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 110, 20));

        lblSetSoPhong.setBackground(new java.awt.Color(97, 177, 90));
        lblSetSoPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetSoPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblSetSoPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetSoPhong.setOpaque(true);
        lblSetSoPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetSoPhongMouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lblSetSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 40, 20));

        lblNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblNhanVien.setText("Nhân Viên");
        sdoThongTinPhong.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, 20));

        lblNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDi.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayDi.setText("Ngày Đi");
        sdoThongTinPhong.add(lblNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, 20));

        lblNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDen.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayDen.setText("Ngày Đến");
        sdoThongTinPhong.add(lblNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, 20));

        lblGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        lblGhiChu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        lblGhiChu.setText("Ghi Chú");
        sdoThongTinPhong.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, 20));

        lblNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayTao.setText("Ngày Tạo");
        sdoThongTinPhong.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, 20));

        lblPhieuBanHang.setBackground(new java.awt.Color(255, 255, 255));
        lblPhieuBanHang.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblPhieuBanHang.setForeground(new java.awt.Color(62, 73, 95));
        lblPhieuBanHang.setText("PHIẾU BÁN HÀNG");
        sdoThongTinPhong.add(lblPhieuBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        lblSetNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayDen.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 70, 20));

        lblSetNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayTao.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayTao.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 120, 20));

        lblSetNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayDi.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 70, 20));

        lbl_1.setBackground(new java.awt.Color(255, 102, 102));
        lbl_1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_1.setOpaque(true);
        lbl_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_1MouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lbl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 70, 30));

        lbl_3.setBackground(new java.awt.Color(255, 102, 102));
        lbl_3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_3.setOpaque(true);
        lbl_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_3MouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lbl_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 70, 30));

        lblAll.setBackground(new java.awt.Color(255, 102, 102));
        lblAll.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblAll.setForeground(new java.awt.Color(255, 255, 255));
        lblAll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAll.setText("All");
        lblAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAll.setOpaque(true);
        lblAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAllMouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lblAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 40, 30));

        lbl_2.setBackground(new java.awt.Color(255, 102, 102));
        lbl_2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_2.setOpaque(true);
        lbl_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_2MouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lbl_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 70, 30));

        txtGhiChu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGhiChu.setForeground(new java.awt.Color(62, 73, 95));
        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGhiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuActionPerformed(evt);
            }
        });
        sdoThongTinPhong.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 190, 20));

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (1).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 30, 30));

        sdoKhoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoKhoDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(33, 150, 243)));
        sdoKhoDichVu.setMinimumSize(new java.awt.Dimension(390, 430));
        sdoKhoDichVu.setPreferredSize(new java.awt.Dimension(390, 430));
        sdoKhoDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrKhoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrKhoDichVu.setBorder(null);

        tblKhoDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblKhoDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Giá Bán", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoDichVu.setRowHeight(30);
        tblKhoDichVu.setShowHorizontalLines(false);
        tblKhoDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoDichVuMouseClicked(evt);
            }
        });
        scrKhoDichVu.setViewportView(tblKhoDichVu);
        if (tblKhoDichVu.getColumnModel().getColumnCount() > 0) {
            tblKhoDichVu.getColumnModel().getColumn(3).setMaxWidth(40);
        }

        sdoKhoDichVu.add(scrKhoDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 390));

        sdoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(33, 150, 243)));
        sdoDichVu.setMinimumSize(new java.awt.Dimension(470, 430));
        sdoDichVu.setPreferredSize(new java.awt.Dimension(470, 430));
        sdoDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrDichVu.setBorder(null);

        tblDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Đơn Giá", "Thành Tiền", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVu.setRowHeight(30);
        tblDichVu.setShowHorizontalLines(false);
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        scrDichVu.setViewportView(tblDichVu);
        if (tblDichVu.getColumnModel().getColumnCount() > 0) {
            tblDichVu.getColumnModel().getColumn(5).setMaxWidth(40);
        }

        sdoDichVu.add(scrDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 430, 390));

        sdoChucNang.setBackground(new java.awt.Color(255, 255, 255));
        sdoChucNang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(33, 150, 243)));
        sdoChucNang.setMinimumSize(new java.awt.Dimension(860, 50));
        sdoChucNang.setPreferredSize(new java.awt.Dimension(860, 50));
        sdoChucNang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSetDaThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblSetDaThanhToan.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetDaThanhToan.setForeground(new java.awt.Color(97, 177, 90));
        sdoChucNang.add(lblSetDaThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 90, 30));

        lblSetTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTongTien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTongTien.setForeground(new java.awt.Color(97, 177, 90));
        sdoChucNang.add(lblSetTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 90, 30));

        lblDaThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblDaThanhToan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDaThanhToan.setForeground(new java.awt.Color(153, 153, 153));
        lblDaThanhToan.setText("Đã Thanh Toán");
        sdoChucNang.add(lblDaThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, 30));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(153, 153, 153));
        lblTongTien.setText("Tổng Tiền");
        sdoChucNang.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        lblThoat.setBackground(new java.awt.Color(255, 255, 255));
        lblThoat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblThoat.setForeground(new java.awt.Color(33, 150, 243));
        lblThoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logout (6).png"))); // NOI18N
        lblThoat.setText("Thoát");
        lblThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThoatMouseClicked(evt);
            }
        });
        sdoChucNang.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 80, 30));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCapNhatMouseEntered(evt);
            }
        });
        sdoChucNang.add(lblCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 100, 30));

        lblInPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblInPhieu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblInPhieu.setForeground(new java.awt.Color(33, 150, 243));
        lblInPhieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/bill (3).png"))); // NOI18N
        lblInPhieu.setText("In Phiếu");
        lblInPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInPhieuMouseClicked(evt);
            }
        });
        sdoChucNang.add(lblInPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoKhoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sdoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdoKhoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGhiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuActionPerformed

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblThoatMouseClicked

    private void lblSetSoPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetSoPhongMouseClicked
        // TODO add your handling code here:
        if (!isUpgrade) {
            new GUI_dal_ChonPhong(null, true).setVisible(true);
        }
        return;
    }//GEN-LAST:event_lblSetSoPhongMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void tblKhoDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoDichVuMouseClicked
        // TODO add your handling code here:
        row = tblKhoDichVu.getSelectedRow();
        column = tblKhoDichVu.getSelectedColumn();
        if (!isUpgrade) {
            int rowIndex = 0;
            if (column == 3) {
                if (tblDichVu.getRowCount() == 0) {
                    addRow(row);
                } else {
                    for (int i = 0; i < tblDichVu.getRowCount(); i++) {
                        if (!tblKhoDichVu.getValueAt(row, 0).toString().equals(tblDichVu.getValueAt(i, 0).toString())) {
                            isAddEdit = false;
                        } else {
                            isAddEdit = true;
                            rowIndex = i;
                            break;
                        }
                    }
                    if (isAddEdit) {
                        editRow(rowIndex);
                    } else {
                        addRow(row);
                    }
                }
            }
            setTongTien();
        } else {
            return;
        }
    }//GEN-LAST:event_tblKhoDichVuMouseClicked

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
        row = tblDichVu.getSelectedRow();
        column = tblDichVu.getSelectedColumn();
        if (!isUpgrade) {
            if (column == 5) {
                deleteRow(row);
            }
            setTongTien();
        }
        return;
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void lblCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCapNhatMouseClicked
        // TODO add your handling code here:        
        if (lblSetSoPhong.getText() != null) {
            if (!isUpgrade) {
                if (tblDichVu.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Dữ Liệu Không Được Để Trống !!!");
                } else {
                    addDichVu();
                    for (int i = 0; i < tblDichVu.getRowCount(); i++) {
                        addChiTietDichVu(i);
                    }
                }
            } else {
                deleteDichVu();
            }
            GUI_pnl_DichVu.search();
            dispose();
        }
        return;
    }//GEN-LAST:event_lblCapNhatMouseClicked

    private void lblAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllMouseClicked
        // TODO add your handling code here:
        loadSanPham();
    }//GEN-LAST:event_lblAllMouseClicked

    private void lbl_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_1MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_1));
    }//GEN-LAST:event_lbl_1MouseClicked

    private void lbl_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_2MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_2));
    }//GEN-LAST:event_lbl_2MouseClicked

    private void lbl_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_3MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_3));
    }//GEN-LAST:event_lbl_3MouseClicked

    private void lblCapNhatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCapNhatMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCapNhatMouseEntered

    private void lblInPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInPhieuMouseClicked
        // TODO add your handling code here:
        if (tblDichVu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            addDichVu();
            for (int i = 0; i < tblDichVu.getRowCount(); i++) {
                addChiTietDichVu(i);
            }
            DAL_DichVu.setThanhToanByDichVu(lblSetMaPhieu.getText());
            GUI_pnl_DichVu.search();
            xuatPhieuDichVu(lblSetMaPhieu.getText());
            dispose();
        }
    }//GEN-LAST:event_lblInPhieuMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_dal_ThongTinDichVu dialog = new GUI_dal_ThongTinDichVu(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAll;
    private javax.swing.JLabel lblCapNhat;
    private javax.swing.JLabel lblDaThanhToan;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblInPhieu;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayDen;
    private javax.swing.JLabel lblNgayDi;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhieuBanHang;
    private javax.swing.JLabel lblSetDaThanhToan;
    private javax.swing.JLabel lblSetMaPhieu;
    public static javax.swing.JLabel lblSetNgayDen;
    public static javax.swing.JLabel lblSetNgayDi;
    private javax.swing.JLabel lblSetNgayTao;
    private javax.swing.JLabel lblSetNhanVien;
    public static javax.swing.JLabel lblSetSoPhong;
    private javax.swing.JLabel lblSetTongTien;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lbl_1;
    private javax.swing.JLabel lbl_2;
    private javax.swing.JLabel lbl_3;
    private javax.swing.JScrollPane scrDichVu;
    private javax.swing.JScrollPane scrKhoDichVu;
    private HELPER.PanelShadow sdoChucNang;
    private HELPER.PanelShadow sdoDichVu;
    private HELPER.PanelShadow sdoKhoDichVu;
    private HELPER.PanelShadow sdoThongTinPhong;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblKhoDichVu;
    private javax.swing.JTextField txtGhiChu;
    // End of variables declaration//GEN-END:variables
}
