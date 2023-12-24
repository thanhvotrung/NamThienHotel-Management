/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL_ChiTietDichVu;
import BLL.BLL_DatPhong;
import BLL.BLL_LoaiPhong;
import BLL.BLL_MaTenLoai;
import BLL.BLL_PhuongThucThanhToan;
import BLL.BLL_SanPham;
import BLL.BLL_SoDoPhong;
import BLL.BLL_TaiKhoan;
import BLL.BLL_ThuePhong;
import DAL.DAL_ChiTietDichVu;
import DAL.DAL_DatPhong;
import DAL.DAL_ThuePhong;
import DTO.DTO_ChiTietDichVu;
import DTO.DTO_DichVu;
import DTO.DTO_Phong;
import DTO.DTO_PhuongThucThanhToan;
import DTO.DTO_SanPham;
import DTO.DTO_ThuePhong;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.doubleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.quadraRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.singleRoom_Hour_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Day_Rate;
import static GUI.GUI_pnl_GiaPhong.tripleRoom_Hour_Rate;
import static GUI.GUI_pnl_SoDoPhong.index;
import static GUI.GUI_pnl_SoDoPhong.pnlFormChinh;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import HELPER.HELPER_SetMa;
import HELPER.HELPER_Validate;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author CherryCe
 */
public class GUI_dal_ThongTinPhong extends javax.swing.JDialog {

    public int row;
    public int column;
    public long diffInDay;
    public long diffInHours;
    public long diffInMinutes;
    public static byte[] hinhAnh;
    public static JDialog dal = null;

    /**
     * Creates new form GUI_dalThongTinPhong
     */
    public GUI_dal_ThongTinPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        loadThongTinPhong();
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
        loadChiTietDichVu();
        loadTongTienDichVu();
        loadThanhToan();
        setTongTien();
        setConLai();
        phuongThucThanhToan();
        validation();
        dal = this;
    }

    public void validation() {
        dateTuNgay.getDateEditor().setEnabled(false);
        dateDenNgay.getDateEditor().setEnabled(false);
    }

    public void loadThongTinPhong() {
        if (!GUI_pnl_SoDoPhong.isThongTinPhong) {
            if (!GUI_pnl_SoDoPhong.isSelectPhong) {
                ArrayList<DTO_Phong> array = BLL_SoDoPhong.selectPhong(GUI_pnl_ChiTietPhong.indexPosition + 1);
                BLL_SoDoPhong.loadThongTinPhong(array, lblSetSoPhong, lblSetTrangThai);
            } else {
                ArrayList<DTO_Phong> array = BLL_SoDoPhong.selectPhong(GUI_pnl_SoDoPhong.maTang, GUI_pnl_ChiTietPhong.indexPosition + 1);
                BLL_SoDoPhong.loadThongTinPhong(array, lblSetSoPhong, lblSetTrangThai);
            }
            trangThaiPhong();
        } else {
            JLabel dateTimeNgayDen = new JLabel();
            JLabel dateTimeNgayDi = new JLabel();
            BLL_ThuePhong.findThuePhong(GUI_pnl_ThuePhong.tuNgay, GUI_pnl_ThuePhong.denNgay, GUI_pnl_ChiTietThuePhong.indexPosition + 1);
            if (BLL_ThuePhong.arrayThuePhong.get(1).equals("Trả Phòng")) {
                ArrayList<DTO_Phong> array = BLL_SoDoPhong.selectPhong(BLL_ThuePhong.arrayThuePhong.get(0));
                BLL_SoDoPhong.loadThongTinPhong(array, lblSetSoPhong, lblSetTrangThai);
                ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuePhong.selectThuePhong(BLL_ThuePhong.arrayThuePhong.get(2), BLL_ThuePhong.arrayThuePhong.get(3));
                BLL_ThuePhong.load(arrayThuePhong, lblSetMaPhieuThue, lblSetNhanVien, lblSetNgayTao, dateTimeNgayDen, dateTimeNgayDi, txtCMND, txtTenKhach, txtSoLuong, txtGhiChu, txtTienCoc, txtGiamGia, cboThanhToan);
                lblSetTrangThai.setText("Trả Phòng");
            } else {
                ArrayList<DTO_Phong> array = BLL_SoDoPhong.selectPhong(BLL_ThuePhong.arrayThuePhong.get(0));
                BLL_SoDoPhong.loadThongTinPhong(array, lblSetSoPhong, lblSetTrangThai);
                ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuePhong.select(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)));
                BLL_ThuePhong.load(arrayThuePhong, lblSetMaPhieuThue, lblSetNhanVien, lblSetNgayTao, dateTimeNgayDen, dateTimeNgayDi, txtCMND, txtTenKhach, txtSoLuong, txtGhiChu, txtTienCoc, txtGiamGia, cboThanhToan);
            }
            if (lblSetTrangThai.getText().equals("Có Khách")) {
                lblSetTrangThai.setBackground(new Color(255, 142, 113));

            } else if (lblSetTrangThai.getText().equals("Đặt Trước")) {
                lblSetTrangThai.setBackground(new Color(102, 153, 255));
            } else if (lblSetTrangThai.getText().equals("Trả Phòng")) {
                lblSetTrangThai.setBackground(new Color(240, 165, 0));
            }
            if (hinhAnh != null) {
                lblImage.setIcon(HELPER_SetIcon.resizeImage(hinhAnh, lblImage));
            }
            lblCapNhat.setVisible(false);
            lblThanhToanPhong.setVisible(false);

            try {
                dateTuNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDen.getText()));
                txtGioDen.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "HH", dateTimeNgayDen.getText()));
                txtPhutDen.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "mm", dateTimeNgayDen.getText()));
                dateDenNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDi.getText()));
                txtGioDi.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "HH", dateTimeNgayDi.getText()));
                txtPhutDi.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "mm", dateTimeNgayDi.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void trangThaiPhong() {
        JLabel dateTimeNgayDen = new JLabel();
        JLabel dateTimeNgayDi = new JLabel();
        lblThanhToanPhong.setVisible(false);
        if (GUI_pnl_ChiTietPhong.isDatThue) {
            dateDenNgay.setEnabled(true);
            txtGioDen.setEnabled(true);
            txtPhutDen.setEnabled(true);
            txtGioDi.setEnabled(true);
            txtPhutDi.setEnabled(true);
        } else if (!GUI_pnl_ChiTietPhong.isDatThue) {
            dateDenNgay.setEnabled(false);
            txtGioDen.setEnabled(false);
            txtPhutDen.setEnabled(false);
            txtGioDi.setEnabled(false);
            txtPhutDi.setEnabled(false);
        }
        if (lblSetTrangThai.getText().equals("Phòng Trống")) {
            dateTimeNgayDen.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
            dateTimeNgayDi.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
            lblSetTrangThai.setBackground(new Color(97, 177, 90));
            lblSetMaPhieuThue.setText(HELPER_SetMa.setMaDateTime("PT"));
            lblSetNgayTao.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yy HH:mm"));
            lblSetNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(BLL_TaiKhoan.selectMaNhanVien(GUI_pnl_DangNhap.taiKhoan)));
        } else {
            ArrayList<DTO_ThuePhong> arrayThuePhong = BLL_ThuePhong.select(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)));
            BLL_ThuePhong.load(arrayThuePhong, lblSetMaPhieuThue, lblSetNhanVien, lblSetNgayTao, dateTimeNgayDen, dateTimeNgayDi, txtCMND, txtTenKhach, txtSoLuong, txtGhiChu, txtTienCoc, txtGiamGia, cboThanhToan);
            if (lblSetTrangThai.getText().equals("Có Khách")) {
                lblSetTrangThai.setBackground(new Color(255, 142, 113));
                lblThanhToanPhong.setVisible(true);
            } else if (lblSetTrangThai.getText().equals("Đặt Trước")) {
                lblSetTrangThai.setBackground(new Color(102, 153, 255));
                if (!GUI_pnl_ChiTietPhong.isDatThue) {
                    dateTimeNgayDen.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
                    dateTimeNgayDi.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
                }
            } else if (lblSetTrangThai.getText().equals("Trả Phòng")) {
                lblSetTrangThai.setBackground(new Color(240, 165, 0));
                lblCapNhat.setVisible(false);
            }
            if (hinhAnh != null) {
                lblImage.setIcon(HELPER_SetIcon.resizeImage(hinhAnh, lblImage));
            }
        }
        try {
            dateTuNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDen.getText()));
            txtGioDen.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "HH", dateTimeNgayDen.getText()));
            txtPhutDen.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "mm", dateTimeNgayDen.getText()));
            dateDenNgay.setDate(HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy", dateTimeNgayDi.getText()));
            txtGioDi.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "HH", dateTimeNgayDi.getText()));
            txtPhutDi.setText(HELPER_ChuyenDoi.convertDate("yyyy-MM-dd HH:mm", "mm", dateTimeNgayDi.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setThoiGian_GiaTien(int row, int column) {
        int price = 0;
        String filePath = null;
        String setPrice = null;
        String dateNgayDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate());
        String dateNgayDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate());
        if (dateNgayDen.equals(dateNgayDi)) {
            setPrice = "Giá Giờ";
        } else {
            setPrice = "Giá Ngày";
        }
        if (lblSetSoPhong.getText().contains("Phòng Đơn") && setPrice.equals("Giá Giờ")) {
            filePath = singleRoom_Hour_Rate;
            price = 0;
        } else if (lblSetSoPhong.getText().contains("Phòng Đơn") && setPrice.equals("Giá Ngày")) {
            filePath = singleRoom_Day_Rate;
            price = 250;
        } else if (lblSetSoPhong.getText().contains("Phòng Đôi Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = doubleRoom_Hour_Rate;
            price = 0;
        } else if (lblSetSoPhong.getText().contains("Phòng Đôi Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = doubleRoom_Day_Rate;
            price = 300;
        } else if (lblSetSoPhong.getText().contains("Phòng Lớn Nhỏ") && setPrice.equals("Giá Giờ")) {
            filePath = tripleRoom_Hour_Rate;
            price = 0;
        } else if (lblSetSoPhong.getText().contains("Phòng Lớn Nhỏ") && setPrice.equals("Giá Ngày")) {
            filePath = tripleRoom_Day_Rate;
            price = 350;
        } else if (lblSetSoPhong.getText().contains("Phòng Đôi Lớn") && setPrice.equals("Giá Giờ")) {
            filePath = quadraRoom_Hour_Rate;
            price = 0;
        } else if (lblSetSoPhong.getText().contains("Phòng Đôi Lớn") && setPrice.equals("Giá Ngày")) {
            filePath = quadraRoom_Day_Rate;
            price = 400;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTimeDen = LocalDateTime.parse(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText(), formatter);
            LocalDateTime dateTimeDi = LocalDateTime.parse(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText(), formatter);
            diffInDay = Duration.between(dateTimeDen, dateTimeDi).toDays();
            diffInHours = Duration.between(dateTimeDen, dateTimeDi).toHours() - diffInDay * 24;
            diffInMinutes = (Duration.between(dateTimeDen, dateTimeDi).toMinutes() - diffInDay * 60 * 24) % 60;
            lblTongThoiGian.setText(String.valueOf(diffInDay + "d " + diffInHours + "h " + diffInMinutes + "m"));
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            if (diffInDay == 0) {
                lblSetGiaPhong.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
            } else {
                if (HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()) <= HELPER_ChuyenDoi.getSoInt(txtGioDi.getText())) {
                    lblSetGiaPhong.setText(HELPER_ChuyenDoi.getSoString((diffInDay - 1) * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
                } else {
                    lblSetGiaPhong.setText(HELPER_ChuyenDoi.getSoString(diffInDay * price + HELPER_ChuyenDoi.getSoInt(String.valueOf(sheet.getRow(row).getCell(column))) / 10) + "K");
                }
            }
        } catch (Exception e) {
            return;
        }
    }

    public void loadChiTietDichVu() {
        ArrayList<DTO_ChiTietDichVu> arrayChiTiet = BLL_ChiTietDichVu.countDichVu(lblSetMaPhieuThue.getText());
        BLL_ChiTietDichVu.loadChiTietDichVu(arrayChiTiet, tblDichVu);
    }

    public void loadThanhToan() {
        lblSetThanhToan.setText(HELPER_ChuyenDoi.getSoString(BLL_ChiTietDichVu.countThanhToan(lblSetMaPhieuThue.getText())) + "K");
    }

    public void loadTongTienDichVu() {
        int total = 0;
        for (int i = 0; i < tblDichVu.getRowCount(); i++) {
            total += HELPER_ChuyenDoi.getSoInt(tblDichVu.getValueAt(i, 3).toString());
        }
        lblSetDichVu.setText(HELPER_ChuyenDoi.getSoString(total) + "K");
    }

    public void setTongTien() {
        lblSetTongTien.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(lblSetGiaPhong.getText()) + HELPER_ChuyenDoi.getSoInt(lblSetDichVu.getText()) - HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText())) + "K");
    }

    public void setConLai() {
        if (lblSetTrangThai.getText().equals("Trả Phòng")) {
            lblSetConLai.setText("0K");
        } else {
            lblSetConLai.setText(HELPER_ChuyenDoi.getSoString(HELPER_ChuyenDoi.getSoInt(lblSetGiaPhong.getText()) + HELPER_ChuyenDoi.getSoInt(lblSetDichVu.getText()) - HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()) - HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()) - HELPER_ChuyenDoi.getSoInt(lblSetThanhToan.getText())) + "K");
        }
    }

    public void phuongThucThanhToan() {
        ArrayList<DTO_PhuongThucThanhToan> arrayPhuongThuc = BLL_PhuongThucThanhToan.select();
        BLL_PhuongThucThanhToan.load(arrayPhuongThuc, cboThanhToan);
    }

    public void addThuePhong() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        DTO_ThuePhong thuePhong = new DTO_ThuePhong(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)), lblSetMaPhieuThue.getText(), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), null, txtCMND.getText(), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()), hinhAnh, BLL_MaTenLoai.findMaPhuongThuc(String.valueOf(cboThanhToan.getSelectedItem())), 0);
        BLL_ThuePhong.addThuePhong(thuePhong);
    }

    public void addDatPhong() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        String ngayGioDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText();
        DTO_ThuePhong thuePhong = new DTO_ThuePhong(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)), lblSetMaPhieuThue.getText(), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDi), txtCMND.getText(), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()), null, BLL_MaTenLoai.findMaPhuongThuc(String.valueOf(cboThanhToan.getSelectedItem())), 0);
        BLL_ThuePhong.addDatPhong(thuePhong);
        DAL_DatPhong.setTrangThai(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "yyyy-MM-dd HH:mm", ngayGioDen), HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "yyyy-MM-dd HH:mm", ngayGioDen), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()));
    }

    public void editThuePhong() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        DTO_ThuePhong thuePhong = new DTO_ThuePhong(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)), lblSetMaPhieuThue.getText(), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), null, txtCMND.getText(), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()), hinhAnh, BLL_MaTenLoai.findMaPhuongThuc(String.valueOf(cboThanhToan.getSelectedItem())), 0);
        BLL_ThuePhong.editThuePhong(thuePhong);
    }

    public void editDatPhong() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        String ngayGioDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText();
        DTO_ThuePhong thuePhong = new DTO_ThuePhong(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)), lblSetMaPhieuThue.getText(), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDi), txtCMND.getText(), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()), null, BLL_MaTenLoai.findMaPhuongThuc(String.valueOf(cboThanhToan.getSelectedItem())), 0);
        BLL_ThuePhong.editDatPhong(thuePhong);
        DAL_DatPhong.setTrangThai(HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "yyyy-MM-dd HH:mm", ngayGioDen), HELPER_ChuyenDoi.convertDate("dd-MM-yyyy HH:mm", "yyyy-MM-dd HH:mm", ngayGioDen), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()));
    }

    public static void addThanhToan() {
        String ngayGioDen = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateTuNgay.getDate()) + " " + txtGioDen.getText() + ":" + txtPhutDen.getText();
        String ngayGioDi = HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy", dateDenNgay.getDate()) + " " + txtGioDi.getText() + ":" + txtPhutDi.getText();
        DTO_ThuePhong thuePhong = new DTO_ThuePhong(BLL_MaTenLoai.findMaPhong(lblSetSoPhong.getText().substring(0, 3)), lblSetMaPhieuThue.getText(), BLL_MaTenLoai.findMaNhanVien(lblSetNhanVien.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yy HH:mm", lblSetNgayTao.getText()), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDen), HELPER_ChuyenDoi.getNgayDate("dd-MM-yyyy HH:mm", ngayGioDi), txtCMND.getText(), txtTenKhach.getText(), HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()), txtGhiChu.getText(), HELPER_ChuyenDoi.getSoInt(txtTienCoc.getText()), HELPER_ChuyenDoi.getSoInt(txtGiamGia.getText()), null, BLL_MaTenLoai.findMaPhuongThuc(String.valueOf(cboThanhToan.getSelectedItem())), 0);
        BLL_ThuePhong.addThanhToan(thuePhong);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlChucNang = new javax.swing.JPanel();
        lblThoat = new javax.swing.JLabel();
        lblCapNhat = new javax.swing.JLabel();
        lblThanhToanPhong = new javax.swing.JLabel();
        sdoThongTinPhong = new HELPER.PanelShadow();
        txtSoLuong = new javax.swing.JTextField();
        txtTenKhach = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtGhiChu = new javax.swing.JTextField();
        lblSetTrangThai = new javax.swing.JLabel();
        lblSetMaPhieuThue = new javax.swing.JLabel();
        lblNhanPhong = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblTenKhach = new javax.swing.JLabel();
        lblCMND = new javax.swing.JLabel();
        lblSoPhong = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        spt_2 = new javax.swing.JSeparator();
        spt_1 = new javax.swing.JSeparator();
        lblSetNhanVien = new javax.swing.JLabel();
        lblSetSoPhong = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        lblMaPhieu = new javax.swing.JLabel();
        lblTraPhong = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblThongTinPhong = new javax.swing.JLabel();
        lblSetNgayTao = new javax.swing.JLabel();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        lblTongThoiGian = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        txtGioDen = new javax.swing.JTextField();
        txtPhutDen = new javax.swing.JTextField();
        lblDoubleDotDen = new javax.swing.JLabel();
        txtGioDi = new javax.swing.JTextField();
        txtPhutDi = new javax.swing.JTextField();
        lblDoubleDotDi = new javax.swing.JLabel();
        sdoDichVu = new HELPER.PanelShadow();
        scrDichVu = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        lblExit = new javax.swing.JLabel();
        sdoGiaPhong = new HELPER.PanelShadow();
        lblSetGiaPhong = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        lblSetConLai = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        lblSetDichVu = new javax.swing.JLabel();
        cboThanhToan = new javax.swing.JComboBox<>();
        lblSetThanhToan = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        lblConLai = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblGiaPhong = new javax.swing.JLabel();
        lblDichVu = new javax.swing.JLabel();
        lblSetTongTien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1020, 580));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlChucNang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(33, 150, 243)));
        pnlChucNang.setMinimumSize(new java.awt.Dimension(380, 60));
        pnlChucNang.setPreferredSize(new java.awt.Dimension(380, 60));
        pnlChucNang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        pnlChucNang.add(lblThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 90, 40));

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
        });
        pnlChucNang.add(lblCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 110, 40));

        lblThanhToanPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblThanhToanPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblThanhToanPhong.setForeground(new java.awt.Color(33, 150, 243));
        lblThanhToanPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThanhToanPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cart (3).png"))); // NOI18N
        lblThanhToanPhong.setText("Thanh Toán");
        lblThanhToanPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThanhToanPhongMouseClicked(evt);
            }
        });
        pnlChucNang.add(lblThanhToanPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 40));

        getContentPane().add(pnlChucNang, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, -1, 60));

        sdoThongTinPhong.setBackground(new java.awt.Color(255, 255, 255));
        sdoThongTinPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(33, 150, 243)));
        sdoThongTinPhong.setMinimumSize(new java.awt.Dimension(640, 440));
        sdoThongTinPhong.setPreferredSize(new java.awt.Dimension(640, 440));
        sdoThongTinPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSoLuong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtSoLuong.setForeground(new java.awt.Color(62, 73, 95));
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });
        sdoThongTinPhong.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 70, 20));

        txtTenKhach.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTenKhach.setForeground(new java.awt.Color(62, 73, 95));
        txtTenKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhachKeyReleased(evt);
            }
        });
        sdoThongTinPhong.add(txtTenKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 140, 20));

        txtCMND.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(62, 73, 95));
        txtCMND.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMNDKeyReleased(evt);
            }
        });
        sdoThongTinPhong.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 120, -1));

        txtGhiChu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGhiChu.setForeground(new java.awt.Color(62, 73, 95));
        txtGhiChu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoThongTinPhong.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 140, 20));

        lblSetTrangThai.setBackground(new java.awt.Color(255, 102, 102));
        lblSetTrangThai.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetTrangThai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSetTrangThai.setOpaque(true);
        sdoThongTinPhong.add(lblSetTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 100, 20));

        lblSetMaPhieuThue.setBackground(new java.awt.Color(255, 255, 255));
        lblSetMaPhieuThue.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetMaPhieuThue.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetMaPhieuThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, 20));

        lblNhanPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblNhanPhong.setText("Nhận Phòng");
        sdoThongTinPhong.add(lblNhanPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, 20));

        lblSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(153, 153, 153));
        lblSoLuong.setText("Số Lượng");
        sdoThongTinPhong.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        lblTenKhach.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKhach.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTenKhach.setForeground(new java.awt.Color(153, 153, 153));
        lblTenKhach.setText("Tên Khách");
        sdoThongTinPhong.add(lblTenKhach, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, -1, -1));

        lblCMND.setBackground(new java.awt.Color(255, 255, 255));
        lblCMND.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblCMND.setForeground(new java.awt.Color(153, 153, 153));
        lblCMND.setText("CMND/ Hộ Chiếu");
        sdoThongTinPhong.add(lblCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        lblSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblSoPhong.setText("Số Phòng");
        sdoThongTinPhong.add(lblSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        lblGhiChu.setBackground(new java.awt.Color(255, 255, 255));
        lblGhiChu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGhiChu.setForeground(new java.awt.Color(153, 153, 153));
        lblGhiChu.setText("Ghi Chú");
        sdoThongTinPhong.add(lblGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));
        sdoThongTinPhong.add(spt_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 620, 10));
        sdoThongTinPhong.add(spt_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 620, 10));

        lblSetNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNhanVien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNhanVien.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 100, 20));

        lblSetSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSetSoPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetSoPhong.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetSoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 160, 20));

        lblNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        lblNhanVien.setText("Nhân Viên");
        sdoThongTinPhong.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, 20));

        lblTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(153, 153, 153));
        lblTrangThai.setText("Trạng Thái");
        sdoThongTinPhong.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 70, 20));

        lblMaPhieu.setBackground(new java.awt.Color(255, 255, 255));
        lblMaPhieu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblMaPhieu.setForeground(new java.awt.Color(153, 153, 153));
        lblMaPhieu.setText("Mã Phiếu");
        sdoThongTinPhong.add(lblMaPhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, 20));

        lblTraPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblTraPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTraPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblTraPhong.setText("Trả Phòng");
        sdoThongTinPhong.add(lblTraPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, 20));

        lblNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(153, 153, 153));
        lblNgayTao.setText("Ngày Tạo");
        sdoThongTinPhong.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 60, 20));

        lblThongTinPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblThongTinPhong.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblThongTinPhong.setForeground(new java.awt.Color(62, 73, 95));
        lblThongTinPhong.setText("THÔNG TIN PHÒNG");
        sdoThongTinPhong.add(lblThongTinPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        lblSetNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        lblSetNgayTao.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetNgayTao.setForeground(new java.awt.Color(62, 73, 95));
        sdoThongTinPhong.add(lblSetNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 100, 20));

        dateDenNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateDenNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateDenNgay.setToolTipText("");
        dateDenNgay.setDateFormatString("dd-MM-yyyy");
        dateDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenNgayPropertyChange(evt);
            }
        });
        sdoThongTinPhong.add(dateDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 100, 20));

        dateTuNgay.setBackground(new java.awt.Color(255, 255, 255));
        dateTuNgay.setForeground(new java.awt.Color(62, 73, 95));
        dateTuNgay.setToolTipText("");
        dateTuNgay.setDateFormatString("dd-MM-yyyy");
        dateTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuNgayPropertyChange(evt);
            }
        });
        sdoThongTinPhong.add(dateTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 100, 20));

        lblTongThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lblTongThoiGian.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        lblTongThoiGian.setForeground(new java.awt.Color(62, 73, 95));
        lblTongThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongThoiGian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/circle (1).png"))); // NOI18N
        lblTongThoiGian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTongThoiGian.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseMoved(evt);
            }
        });
        lblTongThoiGian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTongThoiGianMouseExited(evt);
            }
        });
        sdoThongTinPhong.add(lblTongThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 80, 70));

        lblImage.setOpaque(true);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });
        sdoThongTinPhong.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 260, 130));

        txtGioDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioDen.setForeground(new java.awt.Color(62, 73, 95));
        txtGioDen.setText("00");
        txtGioDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioDen.setEnabled(false);
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
        sdoThongTinPhong.add(txtGioDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 20, 20));

        txtPhutDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtPhutDen.setForeground(new java.awt.Color(62, 73, 95));
        txtPhutDen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhutDen.setText("00");
        txtPhutDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPhutDen.setEnabled(false);
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
        sdoThongTinPhong.add(txtPhutDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 20, 20));

        lblDoubleDotDen.setBackground(new java.awt.Color(255, 255, 255));
        lblDoubleDotDen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblDoubleDotDen.setForeground(new java.awt.Color(62, 73, 95));
        lblDoubleDotDen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoubleDotDen.setText(":");
        lblDoubleDotDen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoThongTinPhong.add(lblDoubleDotDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 10, 20));

        txtGioDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGioDi.setForeground(new java.awt.Color(62, 73, 95));
        txtGioDi.setText("00");
        txtGioDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGioDi.setEnabled(false);
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGioDiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioDiKeyReleased(evt);
            }
        });
        sdoThongTinPhong.add(txtGioDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 20, 20));

        txtPhutDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtPhutDi.setForeground(new java.awt.Color(62, 73, 95));
        txtPhutDi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPhutDi.setText("00");
        txtPhutDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPhutDi.setEnabled(false);
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
        sdoThongTinPhong.add(txtPhutDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 20, 20));

        lblDoubleDotDi.setBackground(new java.awt.Color(255, 255, 255));
        lblDoubleDotDi.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblDoubleDotDi.setForeground(new java.awt.Color(62, 73, 95));
        lblDoubleDotDi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoubleDotDi.setText(":");
        lblDoubleDotDi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdoThongTinPhong.add(lblDoubleDotDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 10, 20));

        getContentPane().add(sdoThongTinPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 440));

        sdoDichVu.setBackground(new java.awt.Color(255, 255, 255));
        sdoDichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 1, new java.awt.Color(33, 150, 243)));
        sdoDichVu.setMinimumSize(new java.awt.Dimension(380, 520));
        sdoDichVu.setPreferredSize(new java.awt.Dimension(380, 520));
        sdoDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrDichVu.setBackground(new java.awt.Color(255, 255, 255));
        scrDichVu.setBorder(null);

        tblDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tblDichVu.setForeground(new java.awt.Color(62, 73, 95));
        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDichVu.setRowHeight(30);
        tblDichVu.setShowHorizontalLines(false);
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDichVuMousePressed(evt);
            }
        });
        scrDichVu.setViewportView(tblDichVu);

        sdoDichVu.add(scrDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 360, 460));

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/close (1).png"))); // NOI18N
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        sdoDichVu.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, 30));

        getContentPane().add(sdoDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, -1, 520));

        sdoGiaPhong.setBackground(new java.awt.Color(255, 255, 255));
        sdoGiaPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(33, 150, 243)));
        sdoGiaPhong.setMinimumSize(new java.awt.Dimension(640, 140));
        sdoGiaPhong.setPreferredSize(new java.awt.Dimension(640, 140));
        sdoGiaPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSetGiaPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSetGiaPhong.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetGiaPhong.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(lblSetGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 90, 20));

        txtGiamGia.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtGiamGia.setForeground(new java.awt.Color(62, 73, 95));
        txtGiamGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });
        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });
        sdoGiaPhong.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 90, 20));

        lblSetConLai.setBackground(new java.awt.Color(255, 255, 255));
        lblSetConLai.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetConLai.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(lblSetConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 70, 20));

        txtTienCoc.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtTienCoc.setForeground(new java.awt.Color(62, 73, 95));
        txtTienCoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTienCoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienCocKeyReleased(evt);
            }
        });
        sdoGiaPhong.add(txtTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 90, 20));

        lblSetDichVu.setBackground(new java.awt.Color(255, 255, 255));
        lblSetDichVu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetDichVu.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(lblSetDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 90, 20));

        cboThanhToan.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        cboThanhToan.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(cboThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 90, 20));

        lblSetThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblSetThanhToan.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetThanhToan.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(lblSetThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 70, 20));

        lblThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        lblThanhToan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(153, 153, 153));
        lblThanhToan.setText("Thanh Toán");
        sdoGiaPhong.add(lblThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, 20));

        lblTienCoc.setBackground(new java.awt.Color(255, 255, 255));
        lblTienCoc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTienCoc.setForeground(new java.awt.Color(153, 153, 153));
        lblTienCoc.setText("Tiền Cọc");
        sdoGiaPhong.add(lblTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, 20));

        lblConLai.setBackground(new java.awt.Color(255, 255, 255));
        lblConLai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblConLai.setForeground(new java.awt.Color(153, 153, 153));
        lblConLai.setText("Còn Lại");
        sdoGiaPhong.add(lblConLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, 20));

        lblTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(153, 153, 153));
        lblTongTien.setText("Tổng Tiền");
        sdoGiaPhong.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 20));

        lblGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(153, 153, 153));
        lblGiamGia.setText("Giảm Giá");
        sdoGiaPhong.add(lblGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 20));

        lblGiaPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblGiaPhong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblGiaPhong.setForeground(new java.awt.Color(153, 153, 153));
        lblGiaPhong.setText("Giá Phòng");
        sdoGiaPhong.add(lblGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 20));

        lblDichVu.setBackground(new java.awt.Color(255, 255, 255));
        lblDichVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDichVu.setForeground(new java.awt.Color(153, 153, 153));
        lblDichVu.setText("Dịch Vụ");
        sdoGiaPhong.add(lblDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 20));

        lblSetTongTien.setBackground(new java.awt.Color(255, 255, 255));
        lblSetTongTien.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblSetTongTien.setForeground(new java.awt.Color(62, 73, 95));
        sdoGiaPhong.add(lblSetTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 70, 20));

        getContentPane().add(sdoGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, -1, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaActionPerformed

    private void lblThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThoatMouseClicked
        // TODO add your handling code here:  
        dispose();
    }//GEN-LAST:event_lblThoatMouseClicked

    private void lblTongThoiGianMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTongThoiGianMouseMoved

    private void lblTongThoiGianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTongThoiGianMouseClicked

    private void lblTongThoiGianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTongThoiGianMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTongThoiGianMouseExited

    private void dateDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenNgayPropertyChange
        // TODO add your handling code here:
        if (dateTuNgay.getDate() != null && dateDenNgay.getDate() != null) {
            setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
            dateTuNgay.setMaxSelectableDate(dateDenNgay.getDate());
        }
    }//GEN-LAST:event_dateDenNgayPropertyChange

    private void dateTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuNgayPropertyChange
        // TODO add your handling code here:
        if (dateTuNgay.getDate() != null && dateDenNgay.getDate() != null) {
            setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
            dateDenNgay.setMinSelectableDate(dateTuNgay.getDate());;
        }
    }//GEN-LAST:event_dateTuNgayPropertyChange

    private void lblCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCapNhatMouseClicked
        // TODO add your handling code here:
        if (GUI_pnl_ChiTietPhong.isDatThue) {
            if (lblSetTrangThai.getText().equals("Phòng Trống")) {
                addDatPhong();
            } else {
                editDatPhong();
            }
        } else {
            if (lblSetTrangThai.getText().equals("Phòng Trống")) {
                addThuePhong();
            } else {
                editThuePhong();
            }
        }
        if (!GUI_pnl_SoDoPhong.isSelectPhong) {
            GUI_pnl_SoDoPhong.load();
            dispose();
        } else {
            GUI_pnl_SoDoPhong.search();
            dispose();
        }
    }//GEN-LAST:event_lblCapNhatMouseClicked

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDichVuMouseClicked

    private void lblThanhToanPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThanhToanPhongMouseClicked
        // TODO add your handling code here:     
        new GUI_dal_ThanhToan(null, true).setVisible(true);
    }//GEN-LAST:event_lblThanhToanPhongMouseClicked

    private void tblDichVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDichVuMousePressed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        // TODO add your handling code here:
        if (!GUI_pnl_SoDoPhong.isSelectPhong) {
            if (hinhAnh == null) {
                GUI_dal_WebCam.isOpen = false;
            } else {
                GUI_dal_WebCam.isOpen = true;
            }
            new GUI_dal_WebCam(null, true).setVisible(true);
        }
        return;
    }//GEN-LAST:event_lblImageMouseClicked

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
        HELPER_Validate.validateNumber(txtGioDen);
        HELPER_Validate.setTextLimited(txtGioDen, 2);
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
        if (HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()) < 0 || HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()) > 23 || txtGioDen.getText().isEmpty() || lblTongThoiGian.getText().contains("-")) {
            txtGioDen.setText("00");
        }
    }//GEN-LAST:event_txtGioDenKeyReleased

    private void txtGioDiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioDiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDiMouseReleased

    private void txtGioDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDiActionPerformed

    private void txtGioDiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioDiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtGioDi);
        HELPER_Validate.setTextLimited(txtGioDi, 2);
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
        if (HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) < 0 || HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) > 23 || txtGioDi.getText().isEmpty() || lblTongThoiGian.getText().contains("-")) {
            txtGioDi.setText("00");
        }
    }//GEN-LAST:event_txtGioDiKeyReleased

    private void txtPhutDiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPhutDiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDiMouseReleased

    private void txtPhutDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhutDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhutDiActionPerformed

    private void txtPhutDiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutDiKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtPhutDi);
        HELPER_Validate.setTextLimited(txtPhutDi, 2);
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtPhutDi.getText()), HELPER_ChuyenDoi.getSoInt(txtPhutDi.getText()) + 1);
        if (HELPER_ChuyenDoi.getSoInt(txtPhutDi.getText()) < 0 || HELPER_ChuyenDoi.getSoInt(txtPhutDi.getText()) > 23 || txtPhutDi.getText().isEmpty() || lblTongThoiGian.getText().contains("-")) {
            txtPhutDi.setText("00");
        }
    }//GEN-LAST:event_txtPhutDiKeyReleased

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtGiamGia);
        setTongTien();
        setConLai();
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void txtTienCocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCocKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtTienCoc);
        setTongTien();
        setConLai();
    }//GEN-LAST:event_txtTienCocKeyReleased

    private void txtCMNDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtCMND);
        HELPER_Validate.setTextLimited(txtCMND, 12);
    }//GEN-LAST:event_txtCMNDKeyReleased

    private void txtTenKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateString(txtTenKhach);
    }//GEN-LAST:event_txtTenKhachKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtSoLuong);
        if (HELPER_ChuyenDoi.getSoInt(txtSoLuong.getText()) > BLL_LoaiPhong.selectSoNguoi(BLL_MaTenLoai.findMaLoaiPhong(lblSetSoPhong.getText().substring(6)))) {
            txtSoLuong.setText(null);
        }
        return;
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtPhutDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutDenKeyReleased
        // TODO add your handling code here:
        HELPER_Validate.validateNumber(txtPhutDen);
        HELPER_Validate.setTextLimited(txtPhutDen, 2);
        setThoiGian_GiaTien(24 - HELPER_ChuyenDoi.getSoInt(txtGioDen.getText()), HELPER_ChuyenDoi.getSoInt(txtGioDi.getText()) + 1);
        if (HELPER_ChuyenDoi.getSoInt(txtPhutDen.getText()) < 0 || HELPER_ChuyenDoi.getSoInt(txtPhutDen.getText()) > 23 || txtPhutDen.getText().isEmpty() || lblTongThoiGian.getText().contains("-")) {
            txtPhutDen.setText("00");
        }
    }//GEN-LAST:event_txtPhutDenKeyReleased

    private void txtGioDiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioDiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioDiKeyPressed

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
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinPhong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinPhong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinPhong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_dal_ThongTinPhong.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_dal_ThongTinPhong dialog = new GUI_dal_ThongTinPhong(new javax.swing.JFrame(), true);
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
    public static javax.swing.JComboBox<String> cboThanhToan;
    public static com.toedter.calendar.JDateChooser dateDenNgay;
    public static com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JLabel lblCMND;
    private javax.swing.JLabel lblCapNhat;
    private javax.swing.JLabel lblConLai;
    private javax.swing.JLabel lblDichVu;
    private javax.swing.JLabel lblDoubleDotDen;
    private javax.swing.JLabel lblDoubleDotDi;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblGiamGia;
    public static javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanPhong;
    private javax.swing.JLabel lblNhanVien;
    public static javax.swing.JLabel lblSetConLai;
    public static javax.swing.JLabel lblSetDichVu;
    public static javax.swing.JLabel lblSetGiaPhong;
    public static javax.swing.JLabel lblSetMaPhieuThue;
    public static javax.swing.JLabel lblSetNgayTao;
    public static javax.swing.JLabel lblSetNhanVien;
    public static javax.swing.JLabel lblSetSoPhong;
    public static javax.swing.JLabel lblSetThanhToan;
    private javax.swing.JLabel lblSetTongTien;
    private javax.swing.JLabel lblSetTrangThai;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblTenKhach;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblThanhToanPhong;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JLabel lblThongTinPhong;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTongThoiGian;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraPhong;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JScrollPane scrDichVu;
    private HELPER.PanelShadow sdoDichVu;
    private HELPER.PanelShadow sdoGiaPhong;
    private HELPER.PanelShadow sdoThongTinPhong;
    private javax.swing.JSeparator spt_1;
    private javax.swing.JSeparator spt_2;
    private javax.swing.JTable tblDichVu;
    public static javax.swing.JTextField txtCMND;
    public static javax.swing.JTextField txtGhiChu;
    public static javax.swing.JTextField txtGiamGia;
    public static javax.swing.JTextField txtGioDen;
    public static javax.swing.JTextField txtGioDi;
    public static javax.swing.JTextField txtPhutDen;
    public static javax.swing.JTextField txtPhutDi;
    public static javax.swing.JTextField txtSoLuong;
    public static javax.swing.JTextField txtTenKhach;
    public static javax.swing.JTextField txtTienCoc;
    // End of variables declaration//GEN-END:variables
}
