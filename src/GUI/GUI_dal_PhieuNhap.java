/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietNhapKho;
import BLL.BLL_LoaiSanPham;
import BLL.BLL_MaTenLoai;
import BLL.BLL_NhapKho;
import BLL.BLL_NhanVien;
import BLL.BLL_SanPham;
import BLL.BLL_TaiKhoan;
import DAL.DAL_ChiTietNhapKho;
import DAL.DAL_LoaiSanPham;
import DAL.DAL_NhapKho;
import DAL.DAL_ThuePhong;
import DTO.DTO_ChiTietNhapKho;
import DTO.DTO_LoaiPhong;
import DTO.DTO_LoaiSanPham;
import DTO.DTO_NhapKho;
import DTO.DTO_SanPham;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_ConnectSQL;
import HELPER.HELPER_SetIcon;
import HELPER.HELPER_SetMa;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class GUI_dal_PhieuNhap extends javax.swing.JDialog {

    public int row;
    public int column;
    public static String str_1;
    public static String str_2;
    public static String str_3;
    public boolean isDelete = false;
    public boolean isAdd = false;

    /**
     * Creates new form GUI_dalThongTinPhong
     */
    public GUI_dal_PhieuNhap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        check();
        loadSanPham();
        loadTenLoaiSanPham();
        loadChiTietNhapKho();
        setTongTien();
        validation();
    }
    
    public void validation() {
        if (!GUI_frm_Menu.auThenTiCaTion()) {
            lblXoa.setVisible(false);
        }
        return;
    }

    public void check() {
        if (GUI_pnl_ChiTietNhapKho.isNhapKho) {
            loadPhieuNhap();
            lblInPhieu.setVisible(false);
            isDelete = true;
            GUI_pnl_ChiTietNhapKho.isNhapKho = false;
        } else {
            load();
            lblInPhieu.setVisible(true);
            isDelete = false;
            GUI_pnl_ChiTietNhapKho.isNhapKho = false;
        }
    }

    public void addChiTietNhapKho(int row) {
        DTO_ChiTietNhapKho chiTietNhapKho = new DTO_ChiTietNhapKho(HELPER_SetMa.setMaDateTime("NK", DAL_ChiTietNhapKho.count(HELPER_ChuyenDoi.getTimeNow("yyMMdd"))), lblSetMaPhieu.getText(), tblKhoDichVu.getValueAt(row, 0).toString(), HELPER_ChuyenDoi.getSoInt(tblKhoDichVu.getValueAt(row, 2).toString()), HELPER_ChuyenDoi.getSoInt(tblKhoDichVu.getValueAt(row, 3).toString()));
        BLL_ChiTietNhapKho.add(chiTietNhapKho);
    }

    public void loadSanPham() {
        ArrayList<DTO_SanPham> array = BLL_SanPham.select();
        BLL_SanPham.loadSanPham(array, tblDichVu);
    }

    public void loadTenLoaiSanPham() {
        BLL_LoaiSanPham.loadTenLoaiPhieuNhap();
        lbl_1.setText(str_1);
        lbl_2.setText(str_2);
        lbl_3.setText(str_3);
    }

    public void loadSanPham(String maLoaiSanPham) {
        ArrayList<DTO_SanPham> array = BLL_SanPham.select(maLoaiSanPham);
        BLL_SanPham.loadSanPham(array, tblDichVu);
    }

    public void loadChiTietNhapKho() {
        ArrayList<DTO_ChiTietNhapKho> arrayChiTiet = BLL_ChiTietNhapKho.select(lblSetMaPhieu.getText());
        BLL_ChiTietNhapKho.load(arrayChiTiet, tblKhoDichVu);
    }

    public void loadPhieuNhap() {
        ArrayList<DTO_NhapKho> arrayNhapKho = BLL_NhapKho.search(GUI_pnl_QuanLiKho.tuNgay, GUI_pnl_QuanLiKho.denNgay, GUI_pnl_ChiTietNhapKho.indexPosition + 1);
        BLL_NhapKho.loadChiTietNhapKho(arrayNhapKho, lblSetMaPhieu, lblSetNhanVien, lblSetNgayTao, txtGhiChu);
    }

    public void addNhapKho() {
        DTO_NhapKho nhapKho = new DTO_NhapKho(lblSetMaPhieu.getText(), lblSetNhanVien.getText(), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", lblSetNgayTao.getText()), txtGhiChu.getText());
        BLL_NhapKho.add(nhapKho);
    }

    public void deleteNhapKho() {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maPhieuNhap = lblSetMaPhieu.getText();
            BLL_ChiTietNhapKho.delete(maPhieuNhap);
            BLL_NhapKho.delete(maPhieuNhap);
        }
        return;
    }

    public void load() {
        lblSetMaPhieu.setText(HELPER_SetMa.setMaDateTime("PN"));
        lblSetNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(BLL_TaiKhoan.selectMaNhanVien(GUI_pnl_DangNhap.taiKhoan)));
        lblSetNgayTao.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
    }

    public void addRow(int row) {
        DefaultTableModel tblModel = (DefaultTableModel) tblKhoDichVu.getModel();
        Object obj[] = new Object[5];
        obj[0] = tblDichVu.getValueAt(row, 0).toString();
        obj[1] = tblDichVu.getValueAt(row, 1).toString();
        obj[2] = HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString());
        obj[3] = HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()) + "K";
        obj[4] = HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) * HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()) + "K";
        tblKhoDichVu.getColumnModel().getColumn(5).setCellRenderer(new HELPER_SetIcon.iconDelete());
        tblModel.addRow(obj);
    }

    public void deleteRow(int row) {
        DefaultTableModel tblModel = (DefaultTableModel) tblKhoDichVu.getModel();
        tblModel.removeRow(row);
    }

    public void setTongTien() {
        int total = 0;
        for (int i = 0; i < tblKhoDichVu.getRowCount(); i++) {
            total += HELPER_ChuyenDoi.getSoInt(tblKhoDichVu.getValueAt(i, 4).toString());
        }
        lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(total) + "K");
    }

    public void xuatPhieuNhapKho(String maNhapKho) {
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/GUI/GUI_rpt_PhieuNhapKho.jrxml");
            map.put("MaNhapKho", maNhapKho);
            JasperPrint p = JasperFillManager.fillReport(report, map, HELPER_ConnectSQL.conn);
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, "PhieuNhapKho.pdf");
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
        lblSetNhanVien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblPhieuNhap = new javax.swing.JLabel();
        lblSetNgayTao = new javax.swing.JLabel();
        lbl_1 = new javax.swing.JLabel();
        lbl_3 = new javax.swing.JLabel();
        lblAll = new javax.swing.JLabel();
        lbl_2 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        lblExit = new javax.swing.JLabel();
        sdoDichVu = new HELPER.PanelShadow();
        scrDichVu = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        sdoKhoDichVu = new HELPER.PanelShadow();
        scrKhoDichVu = new javax.swing.JScrollPane();
        tblKhoDichVu = new javax.swing.JTable();
        sdoChucNang = new HELPER.PanelShadow();
        lblSetTongTien = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblXoa = new javax.swing.JLabel();
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
        sdoThongTinPhong.add(lblSetMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 110, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoThongTinPhong.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 20));

        lblSetNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 110, 20));

        lblNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblNhanVien.setText("Nhân Viên");
        sdoThongTinPhong.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, 20));

        lblGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        lblGhiChu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        lblGhiChu.setText("Ghi Chú");
        sdoThongTinPhong.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        lblNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayTao.setText("Ngày Tạo");
        sdoThongTinPhong.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, 20));

        lblPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        lblPhieuNhap.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblPhieuNhap.setForeground(new java.awt.Color(62, 73, 95));
        lblPhieuNhap.setText("PHIẾU NHẬP");
        sdoThongTinPhong.add(lblPhieuNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        lblSetNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayTao.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayTao.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 120, 20));

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
        sdoThongTinPhong.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 380, 20));

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (1).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 30, 30));

        sdoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(33, 150, 243)));
        sdoDichVu.setMinimumSize(new java.awt.Dimension(390, 430));
        sdoDichVu.setPreferredSize(new java.awt.Dimension(390, 430));
        sdoDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrDichVu.setBorder(null);

        tblDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Giá Nhập", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
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
            tblDichVu.getColumnModel().getColumn(4).setMaxWidth(40);
        }

        sdoDichVu.add(scrDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 390));

        sdoKhoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoKhoDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(33, 150, 243)));
        sdoKhoDichVu.setMinimumSize(new java.awt.Dimension(470, 430));
        sdoKhoDichVu.setPreferredSize(new java.awt.Dimension(470, 430));
        sdoKhoDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrKhoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrKhoDichVu.setBorder(null);

        tblKhoDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblKhoDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhoDichVu.setRowHeight(30);
        tblKhoDichVu.setShowHorizontalLines(false);
        tblKhoDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoDichVuMouseClicked(evt);
            }
        });
        scrKhoDichVu.setViewportView(tblKhoDichVu);
        if (tblKhoDichVu.getColumnModel().getColumnCount() > 0) {
            tblKhoDichVu.getColumnModel().getColumn(5).setMaxWidth(40);
        }

        sdoKhoDichVu.add(scrKhoDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 430, 390));

        sdoChucNang.setBackground(new java.awt.Color(255, 255, 255));
        sdoChucNang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(33, 150, 243)));
        sdoChucNang.setMinimumSize(new java.awt.Dimension(860, 50));
        sdoChucNang.setPreferredSize(new java.awt.Dimension(860, 50));
        sdoChucNang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSetTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTongTien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTongTien.setForeground(new java.awt.Color(97, 177, 90));
        sdoChucNang.add(lblSetTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 110, 30));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(153, 153, 153));
        lblTongTien.setText("Tổng Tiền");
        sdoChucNang.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 30));

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

        lblXoa.setBackground(new java.awt.Color(255, 255, 255));
        lblXoa.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblXoa.setForeground(new java.awt.Color(33, 150, 243));
        lblXoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/delete.png"))); // NOI18N
        lblXoa.setText("Xóa");
        lblXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXoaMouseClicked(evt);
            }
        });
        sdoChucNang.add(lblXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 60, 30));

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
        sdoChucNang.add(lblInPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sdoKhoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdoKhoDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(sdoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
        row = tblDichVu.getSelectedRow();
        column = tblDichVu.getSelectedColumn();
        if (!isDelete) {
            if (column == 4) {
                if (HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 2).toString()) != 0 && HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(row, 3).toString()) != 0) {
                    if (tblKhoDichVu.getRowCount() == 0) {
                        addRow(row);
                    } else {
                        for (int i = 0; i < tblKhoDichVu.getRowCount(); i++) {
                            if (!tblDichVu.getValueAt(row, 0).toString().equals(tblKhoDichVu.getValueAt(i, 0).toString())) {
                                isAdd = false;
                            } else {
                                isAdd = true;
                                break;
                            }
                        }
                        if (isAdd) {
                            JOptionPane.showMessageDialog(this, "Giá Trị Đã Tồn Tại ???");
                        } else {
                            addRow(row);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Dữ Liệu Không Được Để Trống !!!");
                }
            }
            setTongTien();
        }
        return;
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void tblKhoDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoDichVuMouseClicked
        // TODO add your handling code here:
        row = tblKhoDichVu.getSelectedRow();
        column = tblKhoDichVu.getSelectedColumn();
        if (!isDelete) {
            if (column == 5) {
                deleteRow(row);
            }
            setTongTien();
        }
        return;
    }//GEN-LAST:event_tblKhoDichVuMouseClicked

    private void lblXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXoaMouseClicked
        // TODO add your handling code here:
        if (isDelete) {
            deleteNhapKho();
            GUI_pnl_QuanLiKho.search();
            dispose();
        }
        return;
    }//GEN-LAST:event_lblXoaMouseClicked

    private void lbl_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_1MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_1));
    }//GEN-LAST:event_lbl_1MouseClicked

    private void lblAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAllMouseClicked
        // TODO add your handling code here:
        loadSanPham();
    }//GEN-LAST:event_lblAllMouseClicked

    private void lbl_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_2MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_2));
    }//GEN-LAST:event_lbl_2MouseClicked

    private void lbl_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_3MouseClicked
        // TODO add your handling code here:
        loadSanPham(BLL_MaTenLoai.findMaLoaiSanPham(str_3));
    }//GEN-LAST:event_lbl_3MouseClicked

    private void lblInPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInPhieuMouseClicked
        // TODO add your handling code here:
        if (tblKhoDichVu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            addNhapKho();
            for (int i = 0; i < tblKhoDichVu.getRowCount(); i++) {
                addChiTietNhapKho(i);
            }
            GUI_pnl_QuanLiKho.search();
            xuatPhieuNhapKho(lblSetMaPhieu.getText());
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
            java.util.logging.Logger.getLogger(GUI_dal_PhieuNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_PhieuNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_PhieuNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_PhieuNhap.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_dal_PhieuNhap dialog = new GUI_dal_PhieuNhap(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblInPhieu;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhieuNhap;
    private javax.swing.JLabel lblSetMaPhieu;
    private javax.swing.JLabel lblSetNgayTao;
    private javax.swing.JLabel lblSetNhanVien;
    private javax.swing.JLabel lblSetTongTien;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblXoa;
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
