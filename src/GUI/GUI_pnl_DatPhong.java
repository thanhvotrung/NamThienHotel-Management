/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_MaTenLoai;
import BLL.BLL_DatPhong;
import BLL.BLL_LoaiPhong;
import BLL.BLL_TaiKhoan;
import DAL.DAL_DatPhong;
import DTO.DTO_DatPhong;
import DTO.DTO_LoaiPhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetMa;
import HELPER.HELPER_ShowHinhAnh;
import HELPER.HELPER_Validate;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_DatPhong extends javax.swing.JPanel {

    public String tuNgay;
    public String denNgay;

    /**
     * Creates new form GUI_pnl_DatPhong
     */
    public GUI_pnl_DatPhong() {
        initComponents();
        load();
        loadDatPhong();
        loadTenLoaiPhong();
    }

    public void add() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateNgayDen.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        String ngayGioDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateNgayDi.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText();
        DTO_DatPhong datPhong = new DTO_DatPhong(HELPER_SetMa.setMaDateTime("DP"), BLL_MaTenLoai.findMaLoaiPhong(String.valueOf(cboLoaiPhong.getSelectedItem())), String.valueOf(cboLoaiKhach.getSelectedItem()), BLL_TaiKhoan.selectMaNhanVien(GUI_pnl_DangNhap.taiKhoan), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", HELPER_ChuyenDoi.getTimeNow("dd-MM-yy HH:mm")), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDi), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), HELPER_ChuyenDoi.getSoInt(txtSoDienThoai.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), "Không Phòng");
        BLL_DatPhong.add(datPhong);
    }

    public void delete(int index) {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maPhieuDat = tblDatPhong.getValueAt(index, 0).toString();
            BLL_DatPhong.delete(maPhieuDat);
        }
        return;
    }

    public void edit() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateNgayDen.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        String ngayGioDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateNgayDi.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText();
        DTO_DatPhong datPhong = new DTO_DatPhong(lblSetMaPhieu.getText(), BLL_MaTenLoai.findMaLoaiPhong(String.valueOf(cboLoaiPhong.getSelectedItem())), String.valueOf(cboLoaiKhach.getSelectedItem()), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", ngayGioDi), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), HELPER_ChuyenDoi.getSoInt(txtSoDienThoai.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), lblSetTrangThai.getText());
        BLL_DatPhong.edit(datPhong);
    }

    public void reset() {
        lblSetMaPhieu.setText(null);
        cboLoaiPhong.setSelectedItem(null);
        cboLoaiKhach.setSelectedItem(null);
        lblSetNhanVien.setText(null);
        lblSetNgayTao.setText(null);
        dateNgayDen.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy")));
        dateNgayDi.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy")));
        txtTenKhach.setText(null);
        txtSoLuong.setText(null);
        txtSoDienThoai.setText(null);
        txtGhiChu.setText(null);
        txtTienCoc.setText(null);
        lblSetTrangThai.setText(null);
    }

    public void fill(int index) {
        lblSetMaPhieu.setText(tblDatPhong.getValueAt(index, 0).toString());
        txtTenKhach.setText(tblDatPhong.getValueAt(index, 1).toString());
        txtSoDienThoai.setText(tblDatPhong.getValueAt(index, 2).toString());
        txtSoLuong.setText(tblDatPhong.getValueAt(index, 3).toString());
        dateNgayDen.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", tblDatPhong.getValueAt(index, 4).toString()));
        txtGioDen.setText(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "HH", tblDatPhong.getValueAt(index, 4).toString()));
        txtPhutDen.setText(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "mm", tblDatPhong.getValueAt(index, 4).toString()));
        dateNgayDi.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", tblDatPhong.getValueAt(index, 5).toString()));
        txtGioDi.setText(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "HH", tblDatPhong.getValueAt(index, 5).toString()));
        txtPhutDi.setText(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "mm", tblDatPhong.getValueAt(index, 5).toString()));
        txtTienCoc.setText(tblDatPhong.getValueAt(index, 6).toString());
        cboLoaiPhong.setSelectedItem(String.valueOf(tblDatPhong.getValueAt(index, 7)));
        cboLoaiKhach.setSelectedItem(String.valueOf(tblDatPhong.getValueAt(index, 8)));
        txtGhiChu.setText(tblDatPhong.getValueAt(index, 9).toString());
        lblSetNhanVien.setText(tblDatPhong.getValueAt(index, 10).toString());
        lblSetNgayTao.setText(tblDatPhong.getValueAt(index, 11).toString());
        lblSetTrangThai.setText(tblDatPhong.getValueAt(index, 12).toString());
    }

    public void load() {
        dateTuNgay.getDateEditor().setEnabled(false);
        dateDenNgay.getDateEditor().setEnabled(false);
        dateNgayDen.getDateEditor().setEnabled(false);
        dateNgayDi.getDateEditor().setEnabled(false);
        String dateTimeTuNgay = HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm");
        String dateTimeDenNgay = HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm");
        dateTuNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeTuNgay));
        dateDenNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeDenNgay));
        dateNgayDen.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeTuNgay));
        dateNgayDi.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeDenNgay));
        tuNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateTuNgay.getDate());
        denNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateDenNgay.getDate());
    }

    public void loadDatPhong() {
        ArrayList<DTO_DatPhong> array = BLL_DatPhong.select(tuNgay, denNgay);
        BLL_DatPhong.load(array, tblDatPhong);
    }

    public void loadTenLoaiPhong() {
        ArrayList<DTO_LoaiPhong> array = BLL_MaTenLoai.selectTenLoaiPhong();
        BLL_MaTenLoai.loadTenLoaiPhong(array, cboLoaiPhong);
    }

    public void search() {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblDatPhong.getModel());
        tblDatPhong.setRowSorter(rowSorter);
        if (txtTimKiem.getText().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiem.getText()));
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

        sdoFormChinh = new HELPER.PanelShadow();
        lblTimKiem = new javax.swing.JLabel();
        lblLamMoi = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        lblDanhSachDatPhong = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblNgayDi = new javax.swing.JLabel();
        lblNgayDen = new javax.swing.JLabel();
        lblTenKhach = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        lblLoaiKhach = new javax.swing.JLabel();
        cboLoaiPhong = new javax.swing.JComboBox<>();
        lblSetNgayTao = new javax.swing.JLabel();
        dateNgayDi = new com.toedter.calendar.JDateChooser();
        lblMaPhieu = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        dateNgayDen = new com.toedter.calendar.JDateChooser();
        cboLoaiKhach = new javax.swing.JComboBox<>();
        txtTenKhach = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        lblThem = new javax.swing.JLabel();
        lblSetMaPhieu = new javax.swing.JLabel();
        lblSetNhanVien = new javax.swing.JLabel();
        txtPhutDen = new javax.swing.JTextField();
        txtGioDen = new javax.swing.JTextField();
        lblDoubleDotDen = new javax.swing.JLabel();
        txtGioDi = new javax.swing.JTextField();
        lblDoubleDotDi = new javax.swing.JLabel();
        txtPhutDi = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        lblSetTrangThai = new javax.swing.JLabel();
        lblTuNgay = new javax.swing.JLabel();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        lblDenNgay = new javax.swing.JLabel();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        sdoDatPhong = new HELPER.PanelShadow();
        scrDatPhong = new javax.swing.JScrollPane();
        tblDatPhong = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1150, 730));
        setPreferredSize(new java.awt.Dimension(1150, 730));

        sdoFormChinh.setBackground(new java.awt.Color(255, 255, 255));
        sdoFormChinh.setMinimumSize(new java.awt.Dimension(1150, 280));
        sdoFormChinh.setPreferredSize(new java.awt.Dimension(1150, 280));
        sdoFormChinh.setShadowOpacity(0.4F);
        sdoFormChinh.setShadowSize(9);
        sdoFormChinh.setShadowType(HELPER.ShadowType.BOT);
        sdoFormChinh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        lblTimKiem.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblTimKiem.setForeground(new java.awt.Color(33, 150, 243));
        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/magnifier (2).png"))); // NOI18N
        lblTimKiem.setText("Tìm Kiếm");
        lblTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemMouseClicked(evt);
            }
        });
        sdoFormChinh.add(lblTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 220, 90, 30));

        lblLamMoi.setBackground(new java.awt.Color(255, 255, 255));
        lblLamMoi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblLamMoi.setForeground(new java.awt.Color(33, 150, 243));
        lblLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/circle-of-two-clockwise-arrows-rotation.png"))); // NOI18N
        lblLamMoi.setText("Làm Mới");
        lblLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLamMoiMouseClicked(evt);
            }
        });
        sdoFormChinh.add(lblLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 80, 30));

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblLoaiPhong.setText("Loại Phòng");
        sdoFormChinh.add(lblLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 70, 20));

        txtTienCoc.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTienCoc.setForeground(new java.awt.Color(62, 73, 95));
        txtTienCoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCocActionPerformed(evt);
            }
        });
        txtTienCoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienCocKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 80, 20));

        txtGhiChu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGhiChu.setForeground(new java.awt.Color(62, 73, 95));
        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGhiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuActionPerformed(evt);
            }
        });
        txtGhiChu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGhiChuKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 540, 20));

        lblDanhSachDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblDanhSachDatPhong.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblDanhSachDatPhong.setForeground(new java.awt.Color(62, 73, 95));
        lblDanhSachDatPhong.setText("DANH SÁCH ĐẶT PHÒNG");
        sdoFormChinh.add(lblDanhSachDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 30));

        lblNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayTao.setText("Ngày Tạo ");
        sdoFormChinh.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 60, 20));

        lblNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDi.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayDi.setText("Ngày Đi ");
        sdoFormChinh.add(lblNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 50, 20));

        lblNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayDen.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayDen.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayDen.setText("Ngày Đến ");
        sdoFormChinh.add(lblNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 60, 20));

        lblTenKhach.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKhach.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTenKhach.setForeground(new java.awt.Color(153, 153, 153));
        lblTenKhach.setText("Tên Khách");
        sdoFormChinh.add(lblTenKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 70, 20));

        lblSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(153, 153, 153));
        lblSoLuong.setText("Số Lượng");
        sdoFormChinh.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 60, 20));

        lblTienCoc.setBackground(new java.awt.Color(255, 255, 255));
        lblTienCoc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTienCoc.setForeground(new java.awt.Color(153, 153, 153));
        lblTienCoc.setText("Tiền Cọc");
        sdoFormChinh.add(lblTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 60, 20));

        lblSoDienThoai.setBackground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(153, 153, 153));
        lblSoDienThoai.setText("Số Điện Thoại");
        sdoFormChinh.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 90, 20));

        lblGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        lblGhiChu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        lblGhiChu.setText("Ghi Chú");
        sdoFormChinh.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 60, 20));

        lblLoaiKhach.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiKhach.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLoaiKhach.setForeground(new java.awt.Color(153, 153, 153));
        lblLoaiKhach.setText("Loại Khách");
        sdoFormChinh.add(lblLoaiKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 70, 20));

        cboLoaiPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboLoaiPhong.setForeground(new java.awt.Color(62, 73, 95));
        cboLoaiPhong.setBorder(null);
        sdoFormChinh.add(cboLoaiPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 110, 20));

        lblSetNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayTao.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayTao.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 100, 20));

        dateNgayDi.setBackground(new java.awt.Color(255, 255, 255));
        dateNgayDi.setForeground(new java.awt.Color(62, 73, 95));
        dateNgayDi.setToolTipText("");
        dateNgayDi.setDateFormatString("dd-MM-yyyy");
        dateNgayDi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayDiPropertyChange(evt);
            }
        });
        sdoFormChinh.add(dateNgayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 110, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoFormChinh.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 60, 20));

        lblNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblNhanVien.setText("Nhân Viên");
        sdoFormChinh.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 70, 20));

        dateNgayDen.setBackground(new java.awt.Color(255, 255, 255));
        dateNgayDen.setForeground(new java.awt.Color(62, 73, 95));
        dateNgayDen.setToolTipText("");
        dateNgayDen.setDateFormatString("dd-MM-yyyy");
        dateNgayDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayDenPropertyChange(evt);
            }
        });
        sdoFormChinh.add(dateNgayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, 20));

        cboLoaiKhach.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboLoaiKhach.setForeground(new java.awt.Color(62, 73, 95));
        cboLoaiKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách Đơn", "Khách Đoàn" }));
        cboLoaiKhach.setBorder(null);
        cboLoaiKhach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiKhachItemStateChanged(evt);
            }
        });
        sdoFormChinh.add(cboLoaiKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 110, 20));

        txtTenKhach.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTenKhach.setForeground(new java.awt.Color(62, 73, 95));
        txtTenKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachActionPerformed(evt);
            }
        });
        txtTenKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhachKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtTenKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 100, 20));

        txtSoDienThoai.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtSoDienThoai.setForeground(new java.awt.Color(62, 73, 95));
        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 90, 20));

        txtSoLuong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtSoLuong.setForeground(new java.awt.Color(62, 73, 95));
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 70, 20));

        txtTimKiem.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(62, 73, 95));
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 220, 100, 30));

        lblThem.setBackground(new java.awt.Color(255, 255, 255));
        lblThem.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblThem.setForeground(new java.awt.Color(33, 150, 243));
        lblThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add (1).png"))); // NOI18N
        lblThem.setText("Thêm");
        lblThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemMouseClicked(evt);
            }
        });
        sdoFormChinh.add(lblThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 60, 30));

        lblSetMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetMaPhieu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetMaPhieu.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 100, 20));

        lblSetNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 110, 20));

        txtPhutDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtPhutDen.setForeground(new java.awt.Color(62, 73, 95));
        txtPhutDen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhutDen.setText("00");
        txtPhutDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPhutDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtPhutDenMouseReleased(evt);
            }
        });
        txtPhutDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhutDenActionPerformed(evt);
            }
        });
        txtPhutDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhutDenKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtPhutDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 20, 20));

        txtGioDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioDen.setForeground(new java.awt.Color(62, 73, 95));
        txtGioDen.setText("00");
        txtGioDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtGioDenMouseReleased(evt);
            }
        });
        txtGioDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioDenActionPerformed(evt);
            }
        });
        txtGioDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioDenKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtGioDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 20, 20));

        lblDoubleDotDen.setBackground(new java.awt.Color(255, 255, 255));
        lblDoubleDotDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblDoubleDotDen.setForeground(new java.awt.Color(62, 73, 95));
        lblDoubleDotDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoubleDotDen.setText(":");
        lblDoubleDotDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoFormChinh.add(lblDoubleDotDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 10, 20));

        txtGioDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioDi.setForeground(new java.awt.Color(62, 73, 95));
        txtGioDi.setText("00");
        txtGioDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtGioDiMouseReleased(evt);
            }
        });
        txtGioDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioDiActionPerformed(evt);
            }
        });
        txtGioDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioDiKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtGioDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 20, 20));

        lblDoubleDotDi.setBackground(new java.awt.Color(255, 255, 255));
        lblDoubleDotDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblDoubleDotDi.setForeground(new java.awt.Color(62, 73, 95));
        lblDoubleDotDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoubleDotDi.setText(":");
        lblDoubleDotDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoFormChinh.add(lblDoubleDotDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 10, 20));

        txtPhutDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtPhutDi.setForeground(new java.awt.Color(62, 73, 95));
        txtPhutDi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhutDi.setText("00");
        txtPhutDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPhutDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtPhutDiMouseReleased(evt);
            }
        });
        txtPhutDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhutDiActionPerformed(evt);
            }
        });
        txtPhutDi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhutDiKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtPhutDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 20, 20));

        lblTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(153, 153, 153));
        lblTrangThai.setText("Trạng Thái");
        sdoFormChinh.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 140, 70, 20));

        lblSetTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTrangThai.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTrangThai.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 100, 20));

        lblTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblTuNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTuNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblTuNgay.setText("Từ");
        sdoFormChinh.add(lblTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 20, 20));

        dateDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateDenNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateDenNgay.setDateFormatString("dd-MM-yyyy");
        dateDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenNgayPropertyChange(evt);
            }
        });
        sdoFormChinh.add(dateDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, 100, -1));

        lblDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblDenNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDenNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblDenNgay.setText("Đến");
        sdoFormChinh.add(lblDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, -1, 20));

        dateTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateTuNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateTuNgay.setDateFormatString("dd-MM-yyyy");
        dateTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuNgayPropertyChange(evt);
            }
        });
        sdoFormChinh.add(dateTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 100, 20));

        sdoDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        sdoDatPhong.setMinimumSize(new java.awt.Dimension(1150, 450));
        sdoDatPhong.setPreferredSize(new java.awt.Dimension(1150, 450));
        sdoDatPhong.setShadowOpacity(0.4F);
        sdoDatPhong.setShadowSize(9);
        sdoDatPhong.setShadowType(HELPER.ShadowType.BOT);
        sdoDatPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        scrDatPhong.setBorder(null);

        tblDatPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblDatPhong.setForeground(new java.awt.Color(62, 73, 95));
        tblDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu", "Tên Khách", "SĐT", "Số Lượng", "Ngày Đến", "Ngày Đi", "Tiền Cọc", "Loại Phòng", "Loại Khách", "Ghi Chú", "Nhân Viên", "Ngày Tạo", "Trạng Thái", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatPhong.setRowHeight(30);
        tblDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatPhongMouseClicked(evt);
            }
        });
        scrDatPhong.setViewportView(tblDatPhong);
        if (tblDatPhong.getColumnModel().getColumnCount() > 0) {
            tblDatPhong.getColumnModel().getColumn(13).setMaxWidth(40);
            tblDatPhong.getColumnModel().getColumn(14).setMaxWidth(40);
        }

        sdoDatPhong.add(scrDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1110, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sdoDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sdoDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGhiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuActionPerformed

    private void txtTienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienCocActionPerformed

    private void lblLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLamMoiMouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_lblLamMoiMouseClicked

    private void lblTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemMouseClicked
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_lblTimKiemMouseClicked

    private void tblDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatPhongMouseClicked
        // TODO add your handling code here:
        int row = tblDatPhong.getSelectedRow();
        int column = tblDatPhong.getSelectedColumn();
        if (column < 13) {
            fill(row);
        } else if (column == 13) {
            edit();
            loadDatPhong();
        } else if (column == 14) {
            delete(row);
            loadDatPhong();
        }
    }//GEN-LAST:event_tblDatPhongMouseClicked

    private void dateNgayDiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayDiPropertyChange
        // TODO add your handling code here:
        if (dateNgayDi.getDate() != null && dateNgayDen.getDate() != null) {
            dateNgayDen.setMaxSelectableDate(dateNgayDi.getDate());
        }
    }//GEN-LAST:event_dateNgayDiPropertyChange

    private void txtGhiChuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGhiChuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuKeyReleased

    private void txtTienCocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCocKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtTienCoc);
    }//GEN-LAST:event_txtTienCocKeyReleased

    private void dateNgayDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayDenPropertyChange
        // TODO add your handling code here:
        if (dateNgayDen.getDate() != null && dateNgayDi.getDate() != null) {
            dateNgayDi.setMinSelectableDate(dateNgayDen.getDate());
        }
    }//GEN-LAST:event_dateNgayDenPropertyChange

    private void txtTenKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachActionPerformed

    private void txtTenKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateString(txtTenKhach);
    }//GEN-LAST:event_txtTenKhachKeyReleased

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtSoDienThoai);
        HELPER_Validate.setTextLimited(txtSoDienThoai, 10);
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtSoLuong);
        if (String.valueOf(cboLoaiKhach.getSelectedItem()).equals("Khách Đoàn")) {
            HELPER_Validate.setTextLimited(txtSoLuong, 2);
        } else {
            if (HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()) > BLL_LoaiPhong.selectSoNguoi(BLL_MaTenLoai.findMaLoaiPhong(String.valueOf(cboLoaiPhong.getSelectedItem())))) {
                txtSoLuong.setText(null);
            }
            return;
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemMouseClicked
        // TODO add your handling code here:
        add();
        loadDatPhong();
    }//GEN-LAST:event_lblThemMouseClicked

    private void txtPhutDenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPhutDenMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDenMouseReleased

    private void txtPhutDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhutDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDenActionPerformed

    private void txtGioDenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioDenMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDenMouseReleased

    private void txtGioDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDenActionPerformed

    private void txtGioDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioDenKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.setTextLimited(txtGioDen, 2);
    }//GEN-LAST:event_txtGioDenKeyReleased

    private void txtGioDiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioDiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDiMouseReleased

    private void txtGioDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDiActionPerformed

    private void txtGioDiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioDiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.setTextLimited(txtGioDi, 2);
    }//GEN-LAST:event_txtGioDiKeyReleased

    private void txtPhutDiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPhutDiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDiMouseReleased

    private void txtPhutDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhutDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDiActionPerformed

    private void dateTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuNgayPropertyChange
        // TODO add your handling code here:
        if (dateTuNgay.getDate() != null && dateDenNgay.getDate() != null) {
            dateDenNgay.setMinSelectableDate(dateTuNgay.getDate());
            tuNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateTuNgay.getDate());
            denNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateDenNgay.getDate());
            loadDatPhong();
        }
    }//GEN-LAST:event_dateTuNgayPropertyChange

    private void dateDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenNgayPropertyChange
        // TODO add your handling code here:
        if (dateDenNgay.getDate() != null && dateTuNgay.getDate() != null) {
            dateTuNgay.setMaxSelectableDate(dateDenNgay.getDate());
            tuNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateTuNgay.getDate());
            denNgay = HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd", dateDenNgay.getDate());
            loadDatPhong();
        }
    }//GEN-LAST:event_dateDenNgayPropertyChange

    private void cboLoaiKhachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiKhachItemStateChanged
        // TODO add your handling code here:
        if (String.valueOf(cboLoaiKhach.getSelectedItem()).equals("Khách Đoàn")) {
            cboLoaiPhong.setSelectedItem(null);
            cboLoaiPhong.setEnabled(false);
        } else {
            cboLoaiPhong.setEnabled(true);
        }
    }//GEN-LAST:event_cboLoaiKhachItemStateChanged

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void txtPhutDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutDenKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.setTextLimited(txtPhutDen, 2);
    }//GEN-LAST:event_txtPhutDenKeyReleased

    private void txtPhutDiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutDiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.setTextLimited(txtPhutDi, 2);
    }//GEN-LAST:event_txtPhutDiKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboLoaiKhach;
    private javax.swing.JComboBox<String> cboLoaiPhong;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateNgayDen;
    private com.toedter.calendar.JDateChooser dateNgayDi;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JLabel lblDanhSachDatPhong;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblDoubleDotDen;
    private javax.swing.JLabel lblDoubleDotDi;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblLamMoi;
    private javax.swing.JLabel lblLoaiKhach;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayDen;
    private javax.swing.JLabel lblNgayDi;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSetMaPhieu;
    private javax.swing.JLabel lblSetNgayTao;
    private javax.swing.JLabel lblSetNhanVien;
    private javax.swing.JLabel lblSetTrangThai;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenKhach;
    private javax.swing.JLabel lblThem;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JScrollPane scrDatPhong;
    private HELPER.PanelShadow sdoDatPhong;
    private HELPER.PanelShadow sdoFormChinh;
    private javax.swing.JTable tblDatPhong;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGioDen;
    private javax.swing.JTextField txtGioDi;
    private javax.swing.JTextField txtPhutDen;
    private javax.swing.JTextField txtPhutDi;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
