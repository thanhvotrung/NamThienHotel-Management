/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_TaiKhoan;
import DAL.DAL_NhanVien;
import DAL.DAL_TaiKhoan;
import java.awt.Color;
import java.awt.Desktop;
import java.net.URI;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_DangNhap extends javax.swing.JPanel {
    
    public boolean isPassword = false;
    public static String taiKhoan = null;

    /**
     * Creates new form GUI_pnlDangNhap
     */
    public GUI_pnl_DangNhap() {
        initComponents();
        load();
        loadDangNhap();
    }
    
    public void load() {
        txtTenDangNhap.setText("Nhập Mail Của Bạn");
        txtTenDangNhap.setForeground(new Color(153, 153, 153));
        psdMatKhau.setText("Nhập Mật Khẩu");
        psdMatKhau.setEchoChar((char) 0);
        psdMatKhau.setForeground(new Color(153, 153, 153));
    }
    
    public void loadDangNhap() {
        ResultSet rs = DAL_TaiKhoan.checkDangNhap();
        try {
            while (rs.next()) {
                if (rs.getInt("CheckDangNhap") == 1) {
                    txtTenDangNhap.setText(rs.getString("TenDangNhap"));
                    psdMatKhau.setText(rs.getString("MatKhau"));
                    chkLuuDangNhap.setSelected(true);
                    txtTenDangNhap.setForeground(new Color(62, 73, 95));
                    psdMatKhau.setEchoChar('*');
                    psdMatKhau.setForeground(new Color(62, 73, 95));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void focusTaiKhoan(JTextField txt, String getText, String setText, int r, int g, int b) {
        if (txt.getText().equals(getText)) {
            txt.setForeground(new Color(r, g, b));
            txt.setText(setText);
        }
    }
    
    public void focusMatKhau(JPasswordField psd, String getText, String setText, int r, int g, int b, char c) {
        if (String.valueOf(psd.getPassword()).equals(getText)) {
            psd.setForeground(new Color(r, g, b));
            psd.setText(setText);
            psd.setEchoChar(c);
        }
    }
    
    public void login() {
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = String.valueOf(psdMatKhau.getPassword());
        
        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống !!!");
        } else {
            try {
                new GUI_dal_Loading(null, true).setVisible(true);
                if (BLL_TaiKhoan.select(tenDangNhap, matKhau).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Đăng Nhập Không Thành Công ???");
                } else {
                    taiKhoan = txtTenDangNhap.getText();
                    DAL_NhanVien.setOffline();
                    DAL_NhanVien.setOnline(BLL_TaiKhoan.selectMaNhanVien(taiKhoan));
                    if (chkLuuDangNhap.isSelected()) {
                        DAL_TaiKhoan.editCheckDangNhap_0();
                        DAL_TaiKhoan.editCheckDangNhap_1(taiKhoan);
                    } else {
                        DAL_TaiKhoan.editCheckDangNhap_0();
                    }
                    GUI_frm_Login.frm.dispose();
                    new GUI_frm_Menu().setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void isPassword() {
        if (!isPassword) {
            psdMatKhau.setEchoChar((char) 0);
            isPassword = true;
            lblShowHiddenMatKhau.setIcon(new ImageIcon("src/IMG/hidden.png"));
            
        } else {
            psdMatKhau.setEchoChar('*');
            isPassword = false;
            lblShowHiddenMatKhau.setIcon(new ImageIcon("src/IMG/hidden (1).png"));
        }
    }
    
    public void setBackground_78_87_103(JLabel lbl) {
        lbl.setBackground(new Color(78, 87, 103));
    }
    
    public void setBackground_62_73_95(JLabel lbl) {
        lbl.setBackground(new Color(62, 73, 95));
    }
    
    public void setForeground_33_150_243(JLabel lbl) {
        lbl.setForeground(new Color(33, 150, 243));
    }
    
    public void setForeground_62_73_95(JLabel lbl) {
        lbl.setForeground(new Color(62, 73, 95));
    }
    
    public void showFanPage() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://www.facebook.com/MrPii.2k3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showDisCord() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://discord.com/channels/902461485291274281/902461485798793217"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exit() {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Chương Trình ???", "Thoát", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        return;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDangNhap = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        psdMatKhau = new javax.swing.JPasswordField();
        lblTenDangNhap = new javax.swing.JLabel();
        lblDangNhap = new javax.swing.JLabel();
        chkLuuDangNhap = new javax.swing.JCheckBox();
        txtTenDangNhap = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        lblShowHiddenMatKhau = new javax.swing.JLabel();
        lblHoTro = new javax.swing.JLabel();
        lblSetHoTro = new javax.swing.JLabel();
        lblWebsite = new javax.swing.JLabel();
        lblSetWebsite = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 450));
        setPreferredSize(new java.awt.Dimension(400, 450));

        pnlDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        pnlDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(33, 150, 243)));
        pnlDangNhap.setMinimumSize(new java.awt.Dimension(400, 450));
        pnlDangNhap.setPreferredSize(new java.awt.Dimension(400, 450));
        pnlDangNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (4).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        pnlDangNhap.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 20, 20));

        psdMatKhau.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        psdMatKhau.setForeground(new java.awt.Color(62, 73, 95));
        psdMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        psdMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                psdMatKhauFocusLost(evt);
            }
        });
        psdMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                psdMatKhauMouseClicked(evt);
            }
        });
        pnlDangNhap.add(psdMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 270, 30));

        lblTenDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        lblTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/profile-user.png"))); // NOI18N
        pnlDangNhap.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 30, 30));

        lblDangNhap.setBackground(new java.awt.Color(62, 73, 95));
        lblDangNhap.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangNhap.setText("ĐĂNG NHẬP");
        lblDangNhap.setOpaque(true);
        lblDangNhap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseMoved(evt);
            }
        });
        lblDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseExited(evt);
            }
        });
        pnlDangNhap.add(lblDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 300, 40));

        chkLuuDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        chkLuuDangNhap.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        chkLuuDangNhap.setForeground(new java.awt.Color(62, 73, 95));
        chkLuuDangNhap.setText("Lưu Đăng Nhập");
        chkLuuDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLuuDangNhapActionPerformed(evt);
            }
        });
        pnlDangNhap.add(chkLuuDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 360, -1, 30));

        txtTenDangNhap.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTenDangNhap.setForeground(new java.awt.Color(62, 73, 95));
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenDangNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenDangNhapFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenDangNhapFocusLost(evt);
            }
        });
        txtTenDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenDangNhapMouseClicked(evt);
            }
        });
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        pnlDangNhap.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 300, 30));

        lblMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        lblMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/padlock.png"))); // NOI18N
        pnlDangNhap.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 30, 30));

        lblShowHiddenMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        lblShowHiddenMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/hidden (1).png"))); // NOI18N
        lblShowHiddenMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblShowHiddenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowHiddenMatKhauMouseClicked(evt);
            }
        });
        pnlDangNhap.add(lblShowHiddenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 30, 30));

        lblHoTro.setBackground(new java.awt.Color(255, 255, 255));
        lblHoTro.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblHoTro.setForeground(new java.awt.Color(153, 153, 153));
        lblHoTro.setText("Hỗ Trợ:");
        pnlDangNhap.add(lblHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

        lblSetHoTro.setBackground(new java.awt.Color(255, 255, 255));
        lblSetHoTro.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetHoTro.setForeground(new java.awt.Color(62, 73, 95));
        lblSetHoTro.setText("Game2K");
        lblSetHoTro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSetHoTroMouseMoved(evt);
            }
        });
        lblSetHoTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetHoTroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSetHoTroMouseExited(evt);
            }
        });
        pnlDangNhap.add(lblSetHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, 20));

        lblWebsite.setBackground(new java.awt.Color(255, 255, 255));
        lblWebsite.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblWebsite.setForeground(new java.awt.Color(153, 153, 153));
        lblWebsite.setText("Website:");
        pnlDangNhap.add(lblWebsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, 20));

        lblSetWebsite.setBackground(new java.awt.Color(255, 255, 255));
        lblSetWebsite.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetWebsite.setForeground(new java.awt.Color(62, 73, 95));
        lblSetWebsite.setText("MrPii.2k3");
        lblSetWebsite.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblSetWebsiteMouseMoved(evt);
            }
        });
        lblSetWebsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetWebsiteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSetWebsiteMouseExited(evt);
            }
        });
        pnlDangNhap.add(lblSetWebsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 420, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtTenDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenDangNhapMouseClicked
        // TODO add your handling code here:
        focusTaiKhoan(txtTenDangNhap, "Nhập Mail Của Bạn", "", 62, 73, 95);
    }//GEN-LAST:event_txtTenDangNhapMouseClicked

    private void txtTenDangNhapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenDangNhapFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapFocusGained

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void txtTenDangNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenDangNhapFocusLost
        // TODO add your handling code here:
        focusTaiKhoan(txtTenDangNhap, "", "Nhập Mail Của Bạn", 153, 153, 153);
    }//GEN-LAST:event_txtTenDangNhapFocusLost

    private void lblShowHiddenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowHiddenMatKhauMouseClicked
        // TODO add your handling code here:
        isPassword();
    }//GEN-LAST:event_lblShowHiddenMatKhauMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_lblExitMouseClicked

    private void chkLuuDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLuuDangNhapActionPerformed
        // TODO add your handling code here:     
    }//GEN-LAST:event_chkLuuDangNhapActionPerformed

    private void lblDangNhapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblDangNhap);
    }//GEN-LAST:event_lblDangNhapMouseMoved

    private void lblDangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblDangNhap);
    }//GEN-LAST:event_lblDangNhapMouseExited

    private void lblSetHoTroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetHoTroMouseMoved
        // TODO add your handling code here:
        setForeground_33_150_243(lblSetHoTro);
    }//GEN-LAST:event_lblSetHoTroMouseMoved

    private void lblSetHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetHoTroMouseClicked
        // TODO add your handling code here:
        showDisCord();
    }//GEN-LAST:event_lblSetHoTroMouseClicked

    private void lblSetHoTroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetHoTroMouseExited
        // TODO add your handling code here:
        setForeground_62_73_95(lblSetHoTro);
    }//GEN-LAST:event_lblSetHoTroMouseExited

    private void lblSetWebsiteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetWebsiteMouseMoved
        // TODO add your handling code here:
        setForeground_33_150_243(lblSetWebsite);
    }//GEN-LAST:event_lblSetWebsiteMouseMoved

    private void lblSetWebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetWebsiteMouseClicked
        // TODO add your handling code here:
        showFanPage();
    }//GEN-LAST:event_lblSetWebsiteMouseClicked

    private void lblSetWebsiteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetWebsiteMouseExited
        // TODO add your handling code here:
        setForeground_62_73_95(lblSetWebsite);
    }//GEN-LAST:event_lblSetWebsiteMouseExited

    private void psdMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_psdMatKhauMouseClicked
        // TODO add your handling code here:
        focusMatKhau(psdMatKhau, "Nhập Mật Khẩu", "", 62, 73, 95, '*');
    }//GEN-LAST:event_psdMatKhauMouseClicked

    private void psdMatKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_psdMatKhauFocusLost
        // TODO add your handling code here:
        focusMatKhau(psdMatKhau, "", "Nhập Mật Khẩu", 153, 153, 153, (char) 0);
    }//GEN-LAST:event_psdMatKhauFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkLuuDangNhap;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblHoTro;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblSetHoTro;
    private javax.swing.JLabel lblSetWebsite;
    private javax.swing.JLabel lblShowHiddenMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JLabel lblWebsite;
    public javax.swing.JPanel pnlDangNhap;
    private javax.swing.JPasswordField psdMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
