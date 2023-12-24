/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HELPER;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author CherryCe
 */
public class HELPER_SetIcon {

    public static class iconAccount extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(new ImageIcon("src/IMG/profile-user (5).png"));
            setHorizontalAlignment(CENTER);
            return this;
        }
    }

    public static class iconAdd extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(new ImageIcon("src/IMG/queue.png"));
            setHorizontalAlignment(CENTER);
            return this;
        }
    }

    public static class iconEdit extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(new ImageIcon("src/IMG/edit.png"));
            setHorizontalAlignment(CENTER);
            return this;
        }
    }

    public static class iconDelete extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(new ImageIcon("src/IMG/trash.png"));
            setHorizontalAlignment(CENTER);
            return this;
        }
    }

    public static ImageIcon resizeImage(byte[] imagePath, JLabel lbl) {
        ImageIcon myIMG = new ImageIcon(imagePath);
        Image img = myIMG.getImage();
        Image newIMG = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newIMG);
        return image;
    }
}
