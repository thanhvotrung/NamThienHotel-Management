/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_TaiKhoan;
import DAL.DAL_TaiKhoan;
import DTO.DTO_TaiKhoan;
import static GUI.GUI_frm_Login.frm;
import HELPER.HELPER_SendMail;
import HELPER.HELPER_Validate;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jdk.nashorn.internal.ir.BreakNode;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_CheckMail extends javax.swing.JPanel {

    public int otp;
    public String conFirmOTP;
    public static String mail;

    /**
     * Creates new form GUI_pnlDangKi
     */
    public GUI_pnl_CheckMail() {
        initComponents();
        load();
    }

    public void load() {
        lblIconOTP.setVisible(false);
        txtOTP_1.setVisible(false);
        txtOTP_2.setVisible(false);
        txtOTP_3.setVisible(false);
        txtOTP_4.setVisible(false);
        txtOTP_5.setVisible(false);
        lblDoiMa.setVisible(false);
        txtTaiKhoan.setText("Nhập Mail Để Xác Thực Tài Khoản");
        txtTaiKhoan.setForeground(new Color(153, 153, 153));
        pnlCheckMail.add(lblTiepTuc, new AbsoluteConstraints(70, 250, 300, 40));
    }

    public void loadForm() {
        if (lblTiepTuc.getText().equals("TIẾP TỤC")) {
            if (HELPER_Validate.alreayExits(DAL_TaiKhoan.select(), "TenDangNhap", txtTaiKhoan.getText())) {
                JOptionPane.showMessageDialog(this, "Mail Chưa Được Đăng Kí ???");
            } else {
                otp = new Random().nextInt(99999 - 10000) + 10000;
                HELPER_SendMail.sendMail(txtTaiKhoan.getText(), String.valueOf(otp));
                if (!HELPER_SendMail.check) {
                    return;
                } else {
                    lblIconOTP.setVisible(true);
                    txtOTP_1.setVisible(true);
                    txtOTP_2.setVisible(true);
                    txtOTP_3.setVisible(true);
                    txtOTP_4.setVisible(true);
                    txtOTP_5.setVisible(true);
                    lblDoiMa.setVisible(true);
                    txtTaiKhoan.setEnabled(false);
                    pnlCheckMail.add(lblTiepTuc, new AbsoluteConstraints(70, 300, 300, 40));
                    lblTiepTuc.setText("XÁC THỰC");
                }
            }
        } else {
            conFirmOTP = txtOTP_1.getText() + txtOTP_2.getText() + txtOTP_3.getText() + txtOTP_4.getText() + txtOTP_5.getText();
            if (conFirmOTP.equals(String.valueOf(otp))) {
                JOptionPane.showMessageDialog(this, "Xác Thực Hoàn Tất !!!");
                mail = txtTaiKhoan.getText();
                GUI_frm_Login.pnlLogin.removeAll();
                GUI_frm_Login.pnlLogin.add(new GUI_pnl_ChangePassWord().pnlChangePassWord);
                GUI_frm_Login.pnlLogin.validate();
                GUI_frm_Login.pnlLogin.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Xác Thực Thất Bại ???");
            }
        }
    }

    public void resetOTP() {
        otp = new Random().nextInt(99999 - 10000) + 10000;
        HELPER_SendMail.sendMail(txtTaiKhoan.getText(), String.valueOf(otp));
    }

    public void focusOTP(JTextField txtBack, JTextField txtNomal, JTextField txtNext) {
        if (txtNomal.getText().length() == 1) {
            txtNomal.setText(String.valueOf(txtNomal.getText().charAt(0)));
            txtNext.requestFocus();
        } else if (txtNomal.getText().length() > 1) {
            txtNomal.setText(String.valueOf(txtNomal.getText().charAt(1)));
            txtNext.requestFocus();
        } else if (txtNomal.getText().isEmpty()) {
            txtBack.requestFocus();
        }
    }

    public void focusTxt(JTextField txt, String getText, String setText, int r, int g, int b) {
        if (txt.getText().equals(getText)) {
            txtTaiKhoan.setForeground(new Color(r, g, b));
            txtTaiKhoan.setText(setText);
        }
    }

    public void setBackground_238_238_238(JLabel lbl) {
        lbl.setBackground(new Color(238, 238, 238));
    }

    public void setBackground_153_153_153(JLabel lbl) {
        lbl.setBackground(new Color(153, 153, 153));
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

        pnlCheckMail = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        lblTiepTuc = new javax.swing.JLabel();
        lblDoiMa = new javax.swing.JLabel();
        txtOTP_2 = new javax.swing.JTextField();
        txtOTP_3 = new javax.swing.JTextField();
        txtOTP_4 = new javax.swing.JTextField();
        txtOTP_5 = new javax.swing.JTextField();
        txtOTP_1 = new javax.swing.JTextField();
        lblIconMail = new javax.swing.JLabel();
        lblIconOTP = new javax.swing.JLabel();
        lblHoTro = new javax.swing.JLabel();
        lblSetHoTro = new javax.swing.JLabel();
        lblWebsite = new javax.swing.JLabel();
        lblSetWebsite = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 450));
        setPreferredSize(new java.awt.Dimension(400, 450));

        pnlCheckMail.setBackground(new java.awt.Color(255, 255, 255));
        pnlCheckMail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(33, 150, 243)));
        pnlCheckMail.setMinimumSize(new java.awt.Dimension(400, 450));
        pnlCheckMail.setPreferredSize(new java.awt.Dimension(400, 450));
        pnlCheckMail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (4).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        pnlCheckMail.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 20, 20));

        txtTaiKhoan.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTaiKhoan.setForeground(new java.awt.Color(62, 73, 95));
        txtTaiKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTaiKhoan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusLost(evt);
            }
        });
        txtTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTaiKhoanMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtTaiKhoanMouseExited(evt);
            }
        });
        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });
        txtTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTaiKhoanKeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 300, 30));

        lblTiepTuc.setBackground(new java.awt.Color(62, 73, 95));
        lblTiepTuc.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTiepTuc.setForeground(new java.awt.Color(255, 255, 255));
        lblTiepTuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiepTuc.setText("TIẾP TỤC");
        lblTiepTuc.setOpaque(true);
        lblTiepTuc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTiepTucMouseMoved(evt);
            }
        });
        lblTiepTuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTiepTucMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTiepTucMouseExited(evt);
            }
        });
        pnlCheckMail.add(lblTiepTuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 300, 40));

        lblDoiMa.setBackground(new java.awt.Color(238, 238, 238));
        lblDoiMa.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblDoiMa.setForeground(new java.awt.Color(62, 73, 95));
        lblDoiMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reset-password (6).png"))); // NOI18N
        lblDoiMa.setText("Đổi Mã");
        lblDoiMa.setOpaque(true);
        lblDoiMa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDoiMaMouseMoved(evt);
            }
        });
        lblDoiMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDoiMaMouseExited(evt);
            }
        });
        pnlCheckMail.add(lblDoiMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 80, 30));

        txtOTP_2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtOTP_2.setForeground(new java.awt.Color(62, 73, 95));
        txtOTP_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTP_2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOTP_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOTP_2FocusGained(evt);
            }
        });
        txtOTP_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOTP_2ActionPerformed(evt);
            }
        });
        txtOTP_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOTP_2KeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtOTP_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 30, 30));

        txtOTP_3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtOTP_3.setForeground(new java.awt.Color(62, 73, 95));
        txtOTP_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTP_3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOTP_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOTP_3ActionPerformed(evt);
            }
        });
        txtOTP_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOTP_3KeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtOTP_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 30, 30));

        txtOTP_4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtOTP_4.setForeground(new java.awt.Color(62, 73, 95));
        txtOTP_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTP_4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOTP_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOTP_4ActionPerformed(evt);
            }
        });
        txtOTP_4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOTP_4KeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtOTP_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 30, 30));

        txtOTP_5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtOTP_5.setForeground(new java.awt.Color(62, 73, 95));
        txtOTP_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTP_5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOTP_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOTP_5ActionPerformed(evt);
            }
        });
        txtOTP_5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOTP_5KeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtOTP_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 30, 30));

        txtOTP_1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtOTP_1.setForeground(new java.awt.Color(62, 73, 95));
        txtOTP_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTP_1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOTP_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOTP_1FocusGained(evt);
            }
        });
        txtOTP_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOTP_1ActionPerformed(evt);
            }
        });
        txtOTP_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOTP_1KeyReleased(evt);
            }
        });
        pnlCheckMail.add(txtOTP_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 30, 30));

        lblIconMail.setBackground(new java.awt.Color(255, 255, 255));
        lblIconMail.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        lblIconMail.setForeground(new java.awt.Color(153, 153, 153));
        lblIconMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/google.png"))); // NOI18N
        pnlCheckMail.add(lblIconMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 30, 30));

        lblIconOTP.setBackground(new java.awt.Color(255, 255, 255));
        lblIconOTP.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        lblIconOTP.setForeground(new java.awt.Color(153, 153, 153));
        lblIconOTP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/password (1).png"))); // NOI18N
        pnlCheckMail.add(lblIconOTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 30, 30));

        lblHoTro.setBackground(new java.awt.Color(255, 255, 255));
        lblHoTro.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblHoTro.setForeground(new java.awt.Color(153, 153, 153));
        lblHoTro.setText("Hỗ Trợ:");
        pnlCheckMail.add(lblHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

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
        pnlCheckMail.add(lblSetHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, 20));

        lblWebsite.setBackground(new java.awt.Color(255, 255, 255));
        lblWebsite.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblWebsite.setForeground(new java.awt.Color(153, 153, 153));
        lblWebsite.setText("Website:");
        pnlCheckMail.add(lblWebsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, 20));

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
        pnlCheckMail.add(lblSetWebsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 420, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCheckMail, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCheckMail, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblTiepTucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTiepTucMouseClicked
        // TODO add your handling code here:
        loadForm();
    }//GEN-LAST:event_lblTiepTucMouseClicked

    private void txtOTP_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOTP_5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_5ActionPerformed

    private void txtOTP_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOTP_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_4ActionPerformed

    private void txtOTP_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOTP_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_3ActionPerformed

    private void txtOTP_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOTP_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_2ActionPerformed

    private void txtOTP_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOTP_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_1ActionPerformed

    private void txtTaiKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaiKhoanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanKeyReleased

    private void txtTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTaiKhoanMouseClicked
        // TODO add your handling code here:
        focusTxt(txtTaiKhoan, "Nhập Mail Để Xác Thực Tài Khoản", "", 62, 73, 95);
    }//GEN-LAST:event_txtTaiKhoanMouseClicked

    private void txtTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTaiKhoanMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanMouseExited

    private void txtOTP_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTP_1KeyReleased
        // TODO add your handling code here:
        focusOTP(txtOTP_1, txtOTP_1, txtOTP_2);
    }//GEN-LAST:event_txtOTP_1KeyReleased

    private void txtTaiKhoanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusLost
        // TODO add your handling code here:
        focusTxt(txtTaiKhoan, "", "Nhập Mail Để Xác Thực Tài Khoản", 153, 153, 153);
    }//GEN-LAST:event_txtTaiKhoanFocusLost

    private void txtOTP_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTP_2KeyReleased
        // TODO add your handling code here:
        focusOTP(txtOTP_1, txtOTP_2, txtOTP_3);
    }//GEN-LAST:event_txtOTP_2KeyReleased

    private void txtOTP_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTP_3KeyReleased
        // TODO add your handling code here:
        focusOTP(txtOTP_2, txtOTP_3, txtOTP_4);
    }//GEN-LAST:event_txtOTP_3KeyReleased

    private void txtOTP_4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTP_4KeyReleased
        // TODO add your handling code here:
        focusOTP(txtOTP_3, txtOTP_4, txtOTP_5);
    }//GEN-LAST:event_txtOTP_4KeyReleased

    private void txtOTP_5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTP_5KeyReleased
        // TODO add your handling code here:
        focusOTP(txtOTP_4, txtOTP_5, txtOTP_5);
    }//GEN-LAST:event_txtOTP_5KeyReleased

    private void txtOTP_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOTP_1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_1FocusGained

    private void txtOTP_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOTP_2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOTP_2FocusGained

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

    private void lblTiepTucMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTiepTucMouseExited
        // TODO add your handling code here:
        setBackground_62_73_95(lblTiepTuc);
    }//GEN-LAST:event_lblTiepTucMouseExited

    private void lblTiepTucMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTiepTucMouseMoved
        // TODO add your handling code here:
        setBackground_78_87_103(lblTiepTuc);
    }//GEN-LAST:event_lblTiepTucMouseMoved

    private void lblDoiMaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMaMouseExited
        // TODO add your handling code here:
        setBackground_238_238_238(lblDoiMa);
    }//GEN-LAST:event_lblDoiMaMouseExited

    private void lblDoiMaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMaMouseMoved
        // TODO add your handling code here:
        setBackground_153_153_153(lblDoiMa);
    }//GEN-LAST:event_lblDoiMaMouseMoved

    private void lblDoiMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMaMouseClicked
        // TODO add your handling code here:
        resetOTP();
    }//GEN-LAST:event_lblDoiMaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDoiMa;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblHoTro;
    private javax.swing.JLabel lblIconMail;
    private javax.swing.JLabel lblIconOTP;
    private javax.swing.JLabel lblSetHoTro;
    private javax.swing.JLabel lblSetWebsite;
    private javax.swing.JLabel lblTiepTuc;
    private javax.swing.JLabel lblWebsite;
    public javax.swing.JPanel pnlCheckMail;
    private javax.swing.JTextField txtOTP_1;
    private javax.swing.JTextField txtOTP_2;
    private javax.swing.JTextField txtOTP_3;
    private javax.swing.JTextField txtOTP_4;
    private javax.swing.JTextField txtOTP_5;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
