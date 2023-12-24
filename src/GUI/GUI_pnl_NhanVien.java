/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChucVu;
import BLL.BLL_MaTenLoai;
import BLL.BLL_NhanVien;
import DAL.DAL_NhanVien;
import DTO.DTO_ChucVu;
import DTO.DTO_NhanVien;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import HELPER.HELPER_ShowHinhAnh;
import HELPER.HELPER_Validate;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_NhanVien extends javax.swing.JPanel {

    public static String maNhanVien;
    public byte[] hinhAnh = null;

    /**
     * Creates new form GUI_pnl_NhanVien
     */
    public GUI_pnl_NhanVien() {
        initComponents();
        validate();
        load();
        loadChucVu();
    }

    public void validate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        dateNgaySinh.setMaxSelectableDate(calendar.getTime());
        dateNgaySinh.getDateEditor().setEnabled(false);
    }

    public void add() {
        DTO_NhanVien nhanVien = new DTO_NhanVien(txtMaNhanVien.getText(), txtTenNhanVien.getText(), String.valueOf(cboGioiTinh.getSelectedItem()).equals("Nam") ? 1 : 0, dateNgaySinh.getDate(), txtSoDienThoai.getText(), txtCMND.getText(), String.valueOf(cboChucVu.getSelectedItem()), HELPER_ChuyenDoi.getSoInt(txtLuong.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", HELPER_ChuyenDoi.getTimeNow("dd-MM-yy HH:mm")), 0, hinhAnh);
        BLL_NhanVien.add(nhanVien);
    }

    public void delete(int index) {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maNhanVien = tblNhanVien.getValueAt(index, 0).toString();
            BLL_NhanVien.delete(maNhanVien);
        }
        return;
    }

    public void edit() {
        DTO_NhanVien nhanVien = new DTO_NhanVien(txtMaNhanVien.getText(), txtTenNhanVien.getText(), String.valueOf(cboGioiTinh.getSelectedItem()).equals("Nam") ? 1 : 0, dateNgaySinh.getDate(), txtSoDienThoai.getText(), txtCMND.getText(), String.valueOf(cboChucVu.getSelectedItem()), HELPER_ChuyenDoi.getSoInt(txtLuong.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getSoInt(lblSetTrangThai.getText()), hinhAnh);
        BLL_NhanVien.edit(nhanVien);
    }

    public void reset() {
        txtMaNhanVien.setText(null);
        txtTenNhanVien.setText(null);
        cboGioiTinh.setSelectedItem(null);
        dateNgaySinh.setDate(null);
        txtSoDienThoai.setText(null);
        txtCMND.setText(null);
        cboChucVu.setSelectedItem(null);
        txtLuong.setText(null);
        lblSetNgayTao.setText(null);
        lblSetTrangThai.setText(null);
        hinhAnh = null;
        lblImage.setIcon(null);
    }

    public void fill(int index) {
        txtMaNhanVien.setText(tblNhanVien.getValueAt(index, 0).toString());
        txtTenNhanVien.setText(tblNhanVien.getValueAt(index, 1).toString());
        cboGioiTinh.setSelectedItem(tblNhanVien.getValueAt(index, 2).toString());
        dateNgaySinh.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", tblNhanVien.getValueAt(index, 3).toString()));
        txtSoDienThoai.setText(tblNhanVien.getValueAt(index, 4).toString());
        txtCMND.setText(tblNhanVien.getValueAt(index, 5).toString());
        cboChucVu.setSelectedItem(tblNhanVien.getValueAt(index, 6).toString());
        txtLuong.setText(tblNhanVien.getValueAt(index, 7).toString());
        lblSetNgayTao.setText(tblNhanVien.getValueAt(index, 8).toString());
        lblSetTrangThai.setText(tblNhanVien.getValueAt(index, 9).toString());
        hinhAnh = BLL_NhanVien.hinhAnh.get(index);
        lblImage.setIcon(HELPER_SetIcon.resizeImage(hinhAnh, lblImage));
    }

    public void load() {
        ArrayList<DTO_NhanVien> array = BLL_NhanVien.select();
        BLL_NhanVien.load(array, tblNhanVien);
    }

    public void loadChucVu() {
        ArrayList<DTO_ChucVu> array = BLL_ChucVu.select();
        BLL_ChucVu.load(array, cboChucVu);
    }

    public void loadTaiKhoan() {
        new GUI_dal_TaiKhoan(null, true).setVisible(true);
    }

    public void open() {
        try {
            JFileChooser chooser = new JFileChooser("src/WEBCAM");
            chooser.setDialogTitle("Open File");
            chooser.showOpenDialog(this);
            File nameIMG = chooser.getSelectedFile();
            if (nameIMG != null) {
                FileInputStream fis = new FileInputStream(nameIMG.getAbsolutePath());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readnum; (readnum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readnum);
                }
                hinhAnh = bos.toByteArray();
                lblImage.setIcon(HELPER_SetIcon.resizeImage(hinhAnh, lblImage));
            }
            return;
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

        sdoFormChinh = new HELPER.PanelShadow();
        lblThem = new javax.swing.JLabel();
        spt_1 = new javax.swing.JSeparator();
        lblLamMoi = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        cboGioiTinh = new javax.swing.JComboBox<>();
        txtLuong = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        lblQuanLiNhanVien = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblLuong = new javax.swing.JLabel();
        lblCMND = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblSetNgayTao = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        lblImage = new javax.swing.JLabel();
        lblSetTrangThai = new javax.swing.JLabel();
        sdoNhanVien = new HELPER.PanelShadow();
        scrNhanVien = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1150, 730));
        setPreferredSize(new java.awt.Dimension(1150, 730));

        sdoFormChinh.setBackground(new java.awt.Color(255, 255, 255));
        sdoFormChinh.setMinimumSize(new java.awt.Dimension(1150, 290));
        sdoFormChinh.setPreferredSize(new java.awt.Dimension(1150, 290));
        sdoFormChinh.setShadowOpacity(0.4F);
        sdoFormChinh.setShadowSize(9);
        sdoFormChinh.setShadowType(HELPER.ShadowType.BOT);
        sdoFormChinh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        sdoFormChinh.add(lblThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 180, 70, 30));

        spt_1.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(spt_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1110, 20));

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
        sdoFormChinh.add(lblLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 90, 30));

        lblTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(153, 153, 153));
        lblTrangThai.setText("Trạng Thái");
        sdoFormChinh.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 70, 20));

        cboChucVu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboChucVu.setForeground(new java.awt.Color(62, 73, 95));
        cboChucVu.setBorder(null);
        sdoFormChinh.add(cboChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 90, 20));

        cboGioiTinh.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboGioiTinh.setForeground(new java.awt.Color(62, 73, 95));
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setBorder(null);
        sdoFormChinh.add(cboGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 80, 20));

        txtLuong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtLuong.setForeground(new java.awt.Color(62, 73, 95));
        txtLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtLuongMousePressed(evt);
            }
        });
        txtLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongActionPerformed(evt);
            }
        });
        txtLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 80, 20));

        txtCMND.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(62, 73, 95));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCMND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCMNDActionPerformed(evt);
            }
        });
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 100, 20));

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
        sdoFormChinh.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 90, 20));

        txtTenNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTenNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });
        txtTenNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenNhanVienKeyReleased(evt);
            }
        });
        sdoFormChinh.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 100, 20));

        txtMaNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtMaNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        txtMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });
        sdoFormChinh.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 90, 20));

        lblQuanLiNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblQuanLiNhanVien.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblQuanLiNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        lblQuanLiNhanVien.setText("QUẢN LÍ NHÂN VIÊN");
        sdoFormChinh.add(lblQuanLiNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, 30));

        lblNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayTao.setText("Ngày Tạo");
        sdoFormChinh.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 60, 20));

        lblLuong.setBackground(new java.awt.Color(255, 255, 255));
        lblLuong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblLuong.setForeground(new java.awt.Color(153, 153, 153));
        lblLuong.setText("Lương");
        sdoFormChinh.add(lblLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 50, 20));

        lblCMND.setBackground(new java.awt.Color(255, 255, 255));
        lblCMND.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblCMND.setForeground(new java.awt.Color(153, 153, 153));
        lblCMND.setText("CMND");
        sdoFormChinh.add(lblCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 40, 20));

        lblMaNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblMaNhanVien.setText("Mã Nhân Viên");
        sdoFormChinh.add(lblMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 90, 20));

        lblGioiTinh.setBackground(new java.awt.Color(255, 255, 255));
        lblGioiTinh.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(153, 153, 153));
        lblGioiTinh.setText("Giới Tính");
        sdoFormChinh.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 60, 20));

        lblSoDienThoai.setBackground(new java.awt.Color(255, 255, 255));
        lblSoDienThoai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoDienThoai.setForeground(new java.awt.Color(153, 153, 153));
        lblSoDienThoai.setText("Số Điện Thoại");
        sdoFormChinh.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 90, 20));

        lblTenNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblTenNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTenNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblTenNhanVien.setText("Tên Nhân Viên");
        sdoFormChinh.add(lblTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 90, 20));

        lblNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        lblNgaySinh.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(153, 153, 153));
        lblNgaySinh.setText("Ngày Sinh");
        sdoFormChinh.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 60, 20));

        lblChucVu.setBackground(new java.awt.Color(255, 255, 255));
        lblChucVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(153, 153, 153));
        lblChucVu.setText("Chức Vụ");
        sdoFormChinh.add(lblChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 60, 20));

        lblSetNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayTao.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayTao.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 100, 20));

        dateNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        dateNgaySinh.setForeground(new java.awt.Color(62, 73, 95));
        dateNgaySinh.setToolTipText("");
        dateNgaySinh.setDateFormatString("dd-MM-yyyy");
        dateNgaySinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgaySinhPropertyChange(evt);
            }
        });
        sdoFormChinh.add(dateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 100, 20));

        lblImage.setOpaque(true);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });
        sdoFormChinh.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 140, 140));

        lblSetTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTrangThai.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTrangThai.setForeground(new java.awt.Color(62, 73, 95));
        sdoFormChinh.add(lblSetTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 70, 20));

        sdoNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        sdoNhanVien.setMinimumSize(new java.awt.Dimension(1150, 440));
        sdoNhanVien.setPreferredSize(new java.awt.Dimension(1150, 440));
        sdoNhanVien.setShadowOpacity(0.4F);
        sdoNhanVien.setShadowSize(9);
        sdoNhanVien.setShadowType(HELPER.ShadowType.BOT);
        sdoNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        scrNhanVien.setBorder(null);

        tblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Ngày Sinh", "Số Điện Thoại", "CMND", "Chức Vụ", "Lương", "Ngày Tạo", "Trạng Thái", "Hình Ảnh", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setRowHeight(100);
        tblNhanVien.setShowHorizontalLines(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        scrNhanVien.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(11).setMaxWidth(40);
            tblNhanVien.getColumnModel().getColumn(12).setMaxWidth(40);
            tblNhanVien.getColumnModel().getColumn(13).setMaxWidth(40);
        }

        sdoNhanVien.add(scrNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1110, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sdoFormChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtCMNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCMNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMNDActionPerformed

    private void txtLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongActionPerformed

    private void lblLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLamMoiMouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_lblLamMoiMouseClicked

    private void lblThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemMouseClicked
        // TODO add your handling code here:
        add();
        load();
    }//GEN-LAST:event_lblThemMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        int column = tblNhanVien.getSelectedColumn();
        maNhanVien = tblNhanVien.getValueAt(row, 0).toString();
        if (column <= 10) {
            fill(row);
        } else if (column == 13) {
            if (!GUI_frm_Menu.auThenTiCaTion()) {
                return;
            } else {
                delete(row);
                load();
            }
        } else if (column == 12) {
            if (!GUI_frm_Menu.auThenTiCaTion()) {
                return;
            } else {
                edit();
                load();
            }
        } else if (column == 11) {
            if (!GUI_frm_Menu.auThenTiCaTion()) {
                return;
            } else {
                loadTaiKhoan();
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void dateNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgaySinhPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dateNgaySinhPropertyChange

    private void txtTenNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNhanVienKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateString(txtTenNhanVien);
    }//GEN-LAST:event_txtTenNhanVienKeyReleased

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtSoDienThoai);
        HELPER_Validate.setTextLimited(txtSoDienThoai, 10);
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtCMND);
        HELPER_Validate.setTextLimited(txtCMND, 12);
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void txtLuongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLuongMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongMousePressed

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        open();
    }//GEN-LAST:event_lblImageMouseClicked

    private void txtLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtLuong);
    }//GEN-LAST:event_txtLuongKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblGioiTinh;
    public static javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLamMoi;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblQuanLiNhanVien;
    private javax.swing.JLabel lblSetNgayTao;
    private javax.swing.JLabel lblSetTrangThai;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblThem;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JScrollPane scrNhanVien;
    private HELPER.PanelShadow sdoFormChinh;
    private HELPER.PanelShadow sdoNhanVien;
    private javax.swing.JSeparator spt_1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
