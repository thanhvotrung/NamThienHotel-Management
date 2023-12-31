/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_HangMucChi;
import DTO.DTO_HangMucChi;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CherryCe
 */
public class GUI_dal_HangMucChi extends javax.swing.JDialog {

    /**
     * Creates new form GUI_dalThongTinPhong
     */
    public GUI_dal_HangMucChi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        load();
        validation();
    }

    public void validation() {
        if (!GUI_frm_Menu.auThenTiCaTion()) {
            lblCapNhat.setVisible(false);
        }
        return;
    }

    public void add() {
        DTO_HangMucChi hangMucChi = new DTO_HangMucChi(txtMaChi.getText(), txtMucChi.getText());
        BLL_HangMucChi.add(hangMucChi);
    }

    public void delete(int index) {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String maLoaiTienChi = tblHangMucChi.getValueAt(index, 0).toString();
            BLL_HangMucChi.delete(maLoaiTienChi);
        }
        return;
    }

    public void edit() {
        DTO_HangMucChi hangMucChi = new DTO_HangMucChi(txtMaChi.getText(), txtMucChi.getText());
        BLL_HangMucChi.edit(hangMucChi);
    }

    public void fill(int index) {
        txtMaChi.setText(tblHangMucChi.getValueAt(index, 0).toString());
        txtMucChi.setText(tblHangMucChi.getValueAt(index, 1).toString());
    }

    public void load() {
        ArrayList<DTO_HangMucChi> array = BLL_HangMucChi.select();
        BLL_HangMucChi.load(array, tblHangMucChi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sdoHangMucChi = new HELPER.PanelShadow();
        lblMucChi = new javax.swing.JLabel();
        lblMaChi = new javax.swing.JLabel();
        lblHangMucChi = new javax.swing.JLabel();
        txtMaChi = new javax.swing.JTextField();
        txtMucChi = new javax.swing.JTextField();
        lblThoat = new javax.swing.JLabel();
        lblCapNhat = new javax.swing.JLabel();
        scrHangMucChi = new javax.swing.JScrollPane();
        tblHangMucChi = new javax.swing.JTable();
        lblExit = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(360, 360));
        setUndecorated(true);

        sdoHangMucChi.setBackground(new java.awt.Color(255, 255, 255));
        sdoHangMucChi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(33, 150, 243)));
        sdoHangMucChi.setMinimumSize(new java.awt.Dimension(360, 360));
        sdoHangMucChi.setPreferredSize(new java.awt.Dimension(360, 360));
        sdoHangMucChi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMucChi.setBackground(new java.awt.Color(255, 255, 255));
        lblMucChi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMucChi.setForeground(new java.awt.Color(153, 153, 153));
        lblMucChi.setText("Mục Chi");
        sdoHangMucChi.add(lblMucChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 60, 20));

        lblMaChi.setBackground(new java.awt.Color(255, 255, 255));
        lblMaChi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaChi.setForeground(new java.awt.Color(153, 153, 153));
        lblMaChi.setText("Mã Chi");
        sdoHangMucChi.add(lblMaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 50, 20));

        lblHangMucChi.setBackground(new java.awt.Color(255, 255, 255));
        lblHangMucChi.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblHangMucChi.setForeground(new java.awt.Color(62, 73, 95));
        lblHangMucChi.setText("HẠNG MỤC CHI");
        sdoHangMucChi.add(lblHangMucChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        txtMaChi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtMaChi.setForeground(new java.awt.Color(62, 73, 95));
        txtMaChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChiActionPerformed(evt);
            }
        });
        sdoHangMucChi.add(txtMaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 240, 20));

        txtMucChi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtMucChi.setForeground(new java.awt.Color(62, 73, 95));
        txtMucChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMucChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucChiActionPerformed(evt);
            }
        });
        sdoHangMucChi.add(txtMucChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 240, 20));

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
        sdoHangMucChi.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 80, 30));

        lblCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        lblCapNhat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblCapNhat.setForeground(new java.awt.Color(33, 150, 243));
        lblCapNhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/upgrade (3).png"))); // NOI18N
        lblCapNhat.setText("Cập Nhập");
        lblCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCapNhatMouseClicked(evt);
            }
        });
        sdoHangMucChi.add(lblCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 100, 30));

        scrHangMucChi.setBackground(new java.awt.Color(255, 255, 255));
        scrHangMucChi.setBorder(null);
        scrHangMucChi.setForeground(new java.awt.Color(62, 73, 95));

        tblHangMucChi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblHangMucChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Chi", "Mục Chi", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHangMucChi.setRowHeight(24);
        tblHangMucChi.setShowHorizontalLines(false);
        tblHangMucChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangMucChiMouseClicked(evt);
            }
        });
        scrHangMucChi.setViewportView(tblHangMucChi);
        if (tblHangMucChi.getColumnModel().getColumnCount() > 0) {
            tblHangMucChi.getColumnModel().getColumn(2).setMaxWidth(40);
            tblHangMucChi.getColumnModel().getColumn(3).setMaxWidth(40);
        }

        sdoHangMucChi.add(scrHangMucChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 320, 140));

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (1).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        sdoHangMucChi.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoHangMucChi, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoHangMucChi, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChiActionPerformed

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblThoatMouseClicked

    private void txtMucChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucChiActionPerformed

    private void tblHangMucChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangMucChiMouseClicked
        // TODO add your handling code here:
        int row = tblHangMucChi.getSelectedRow();
        int column = tblHangMucChi.getSelectedColumn();
        if (column < 2) {
            fill(row);
        } else if (column == 2) {
            edit();
            load();
        } else if (column == 3) {
            delete(row);
            load();
        }
    }//GEN-LAST:event_tblHangMucChiMouseClicked

    private void lblCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCapNhatMouseClicked
        // TODO add your handling code here:
        add();
        load();
    }//GEN-LAST:event_lblCapNhatMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_dal_HangMucChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_HangMucChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_HangMucChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_HangMucChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_dal_HangMucChi dialog = new GUI_dal_HangMucChi(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblCapNhat;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblHangMucChi;
    private javax.swing.JLabel lblMaChi;
    private javax.swing.JLabel lblMucChi;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JScrollPane scrHangMucChi;
    private HELPER.PanelShadow sdoHangMucChi;
    private javax.swing.JTable tblHangMucChi;
    private javax.swing.JTextField txtMaChi;
    private javax.swing.JTextField txtMucChi;
    // End of variables declaration//GEN-END:variables
}
