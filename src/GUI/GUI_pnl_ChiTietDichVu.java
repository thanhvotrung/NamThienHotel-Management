/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DichVu;
import DTO.DTO_ChiTietDichVu;
import DTO.DTO_DichVu;
import HELPER.HELPER_ChuyenDoi;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 *
 * @author CherryCe
 */
public class GUI_pnl_ChiTietDichVu extends javax.swing.JPanel {

    public static int indexPosition;
    public static boolean isDichVu = false;

    /**
     * Creates new form GUI_pnlChiTietPhong
     */
    public GUI_pnl_ChiTietDichVu() {
        initComponents();
        load();
    }

    public void load() {
        ArrayList<DTO_DichVu> arrayDichVu = BLL_DichVu.search(GUI_pnl_DichVu.tuNgay, GUI_pnl_DichVu.denNgay, GUI_pnl_DichVu.index);
        BLL_DichVu.loadDichVu(arrayDichVu, lblSetMaPhieu, lblSetPhongSo, lblSetNgay);
        ArrayList<DTO_ChiTietDichVu> arrayChiTietDichVu = BLL_ChiTietDichVu.select(lblSetMaPhieu.getText());
        BLL_ChiTietDichVu.loadChiTietDichVu(arrayChiTietDichVu, tblDichVu);
        int total = 0;
        for (int i = 0; i < tblDichVu.getRowCount(); i++) {
            total += HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(i, 3).toString());
        }
        lblTongTien.setText(HELPER_ChuyenDoi.getSoString(total) + "K");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sdoChiTietDichVu = new HELPER.PanelShadow();
        lblSetPhongSo = new javax.swing.JLabel();
        lblSetNgay = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        scrDichVu = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        lblSetMaPhieu = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        lblPhongSo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(350, 210));
        setPreferredSize(new java.awt.Dimension(350, 210));

        sdoChiTietDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoChiTietDichVu.setMinimumSize(new java.awt.Dimension(350, 210));
        sdoChiTietDichVu.setPreferredSize(new java.awt.Dimension(350, 210));
        sdoChiTietDichVu.setShadowOpacity(0.3F);
        sdoChiTietDichVu.setShadowSize(4);
        sdoChiTietDichVu.setShadowType(HELPER.ShadowType.BOT_RIGHT);
        sdoChiTietDichVu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sdoChiTietDichVuMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                sdoChiTietDichVuMouseMoved(evt);
            }
        });
        sdoChiTietDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sdoChiTietDichVuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sdoChiTietDichVuMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sdoChiTietDichVuMouseReleased(evt);
            }
        });
        sdoChiTietDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSetPhongSo.setBackground(new java.awt.Color(255, 255, 255));
        lblSetPhongSo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetPhongSo.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietDichVu.add(lblSetPhongSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 30, 20));

        lblSetNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetNgay.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietDichVu.add(lblSetNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 90, 20));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 102, 102));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sdoChiTietDichVu.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 90, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoChiTietDichVu.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 20));

        scrDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrDichVu.setBorder(null);

        tblDichVu.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Hàng", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVu.setRowHeight(24);
        tblDichVu.setShowHorizontalLines(false);
        scrDichVu.setViewportView(tblDichVu);

        sdoChiTietDichVu.add(scrDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 320, 110));

        lblSetMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSetMaPhieu.setForeground(new java.awt.Color(62, 73, 95));
        sdoChiTietDichVu.add(lblSetMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 120, 20));

        lblNgay.setBackground(new java.awt.Color(255, 255, 255));
        lblNgay.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(153, 153, 153));
        lblNgay.setText("Ngày");
        sdoChiTietDichVu.add(lblNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 30, 20));

        lblPhongSo.setBackground(new java.awt.Color(255, 255, 255));
        lblPhongSo.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblPhongSo.setForeground(new java.awt.Color(153, 153, 153));
        lblPhongSo.setText("Phòng Số");
        sdoChiTietDichVu.add(lblPhongSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sdoChiTietDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sdoChiTietDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietDichVuMouseClicked
        // TODO add your handling code here:
        indexPosition = GUI_pnl_DichVu.pnlFormChinh.getComponentZOrder(sdoChiTietDichVu);
        if (evt.getClickCount() == 2) {
            isDichVu = true;
            new GUI_dal_ThongTinDichVu(null, true).setVisible(true);
        }
    }//GEN-LAST:event_sdoChiTietDichVuMouseClicked

    private void sdoChiTietDichVuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietDichVuMouseReleased
        // TODO add your handling code here:      
    }//GEN-LAST:event_sdoChiTietDichVuMouseReleased

    private void sdoChiTietDichVuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietDichVuMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_sdoChiTietDichVuMouseDragged

    private void sdoChiTietDichVuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietDichVuMouseMoved
        // TODO add your handling code here:
        sdoChiTietDichVu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(33, 150, 243)));
    }//GEN-LAST:event_sdoChiTietDichVuMouseMoved

    private void sdoChiTietDichVuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdoChiTietDichVuMouseExited
        // TODO add your handling code here:
        sdoChiTietDichVu.setBorder(null);
    }//GEN-LAST:event_sdoChiTietDichVuMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lblMaPhieu;
    public javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblPhongSo;
    public javax.swing.JLabel lblSetMaPhieu;
    public javax.swing.JLabel lblSetNgay;
    public javax.swing.JLabel lblSetPhongSo;
    public javax.swing.JLabel lblTongTien;
    private javax.swing.JScrollPane scrDichVu;
    public HELPER.PanelShadow sdoChiTietDichVu;
    private javax.swing.JTable tblDichVu;
    // End of variables declaration//GEN-END:variables
}
