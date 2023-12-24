/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_SoDoPhong;
import DAL.DAL_ThuePhong;
import DTO.DTO_Phong;
import DTO.DTO_ThuePhong;
import GUI.GUI_dal_ChuyenPhong;
import GUI.GUI_dal_ThongTinPhong;
import GUI.GUI_pnl_ChiTietThuePhong;
import HELPER.HELPER_ChuyenDoi;
import HELPER.HELPER_SetIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_ThuePhong {

    public static ArrayList<String> arrayThuePhong = new ArrayList<String>();

    public static boolean check(DTO_ThuePhong thuePhong) {
        if (thuePhong.getMaPhong().isEmpty() || thuePhong.getMaPhieuThue().isEmpty() || thuePhong.getMaNhanVien().isEmpty() || thuePhong.getNgayTao() == null || thuePhong.getNgayDen() == null || thuePhong.getCMND().isEmpty() || thuePhong.getTenKhachHang().isEmpty() || thuePhong.getSoLuong() == 0 || thuePhong.getMaPhuongThuc().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void addThuePhong(DTO_ThuePhong thuePhong) {
        if (!check(thuePhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ThuePhong.addThuePhong(thuePhong);
            DAL_ThuePhong.setTrangThaiPhong("CoKhach", thuePhong.getMaPhong());
        }
    }

    public static void addDatPhong(DTO_ThuePhong thuePhong) {
        if (!check(thuePhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ThuePhong.addDatPhong(thuePhong);
            DAL_ThuePhong.setTrangThaiPhong("DatTruoc", thuePhong.getMaPhong());
        }
    }

    public static void delete(String maPhong) {
        DAL_ThuePhong.delete(maPhong);
        DAL_ThuePhong.setTrangThaiPhong("PhongTrong", maPhong);
    }

    public static void editThuePhong(DTO_ThuePhong thuePhong) {
        if (!check(thuePhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ThuePhong.editThuePhong(thuePhong);
            DAL_ThuePhong.setTrangThaiPhong("CoKhach", thuePhong.getMaPhong());
        }
    }

    public static void editDatPhong(DTO_ThuePhong thuePhong) {
        if (!check(thuePhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ThuePhong.editDatPhong(thuePhong);
            DAL_ThuePhong.setTrangThaiPhong("DatTruoc", thuePhong.getMaPhong());
        }
    }

    public static void addThanhToan(DTO_ThuePhong thuePhong) {
        if (!check(thuePhong)) {
            JOptionPane.showMessageDialog(null, "Dữ Liệu Không Được Để Trống !!!");
        } else {
            DAL_ThuePhong.addThanhToan(thuePhong);
            DAL_ThuePhong.setTrangThaiPhong("TraPhong", thuePhong.getMaPhong());
        }
    }

    public static ArrayList<DTO_ThuePhong> select(String maPhong) {
        ResultSet rs = DAL_ThuePhong.select(maPhong);
        ArrayList<DTO_ThuePhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ThuePhong thuePhong = new DTO_ThuePhong();
                thuePhong.setMaPhong(rs.getString("MaPhong"));
                thuePhong.setMaPhieuThue(rs.getString("MaPhieuThue"));
                thuePhong.setMaNhanVien(rs.getString("MaNhanVien"));
                thuePhong.setNgayTao(rs.getTimestamp("NgayTao"));
                thuePhong.setNgayDen(rs.getTimestamp("NgayDen"));
                thuePhong.setNgayDi(rs.getTimestamp("NgayDi"));
                thuePhong.setCMND(rs.getString("CMND"));
                thuePhong.setTenKhachHang(rs.getString("TenKhachHang"));
                thuePhong.setSoLuong(rs.getInt("SoLuongKhach"));
                thuePhong.setGhiChu(rs.getString("GhiChu"));
                thuePhong.setTienCoc(rs.getInt("TienCoc"));
                thuePhong.setGiamGia(rs.getInt("GiamGia"));
                thuePhong.setHinhAnh(rs.getBytes("HinhAnh"));
                thuePhong.setMaPhuongThuc(rs.getString("MaPhuongThuc"));
                thuePhong.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                array.add(thuePhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<DTO_ThuePhong> selectThuePhong(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuePhong.rowNumber(tuNgay, denNgay, index);
        ArrayList<DTO_ThuePhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ThuePhong thuePhong = new DTO_ThuePhong();
                thuePhong.setMaPhong(rs.getString("MaPhong"));
                thuePhong.setMaPhieuThue(rs.getString("MaPhieuThue"));
                thuePhong.setMaNhanVien(rs.getString("MaNhanVien"));
                thuePhong.setNgayTao(rs.getTimestamp("NgayTao"));
                thuePhong.setNgayDen(rs.getTimestamp("NgayDen"));
                thuePhong.setNgayDi(rs.getTimestamp("NgayDi"));
                thuePhong.setCMND(rs.getString("CMND"));
                thuePhong.setTenKhachHang(rs.getString("TenKhachHang"));
                thuePhong.setSoLuong(rs.getInt("SoLuongKhach"));
                thuePhong.setGhiChu(rs.getString("GhiChu"));
                thuePhong.setTienCoc(rs.getInt("TienCoc"));
                thuePhong.setGiamGia(rs.getInt("GiamGia"));
                thuePhong.setHinhAnh(rs.getBytes("HinhAnh"));
                thuePhong.setMaPhuongThuc(rs.getString("MaPhuongThuc"));
                thuePhong.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                array.add(thuePhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<DTO_ThuePhong> selectThuePhong(String ngayDen, String ngayDi) {
        ResultSet rs = DAL_ThuePhong.findMaPhieuThue(ngayDen, ngayDi);
        ArrayList<DTO_ThuePhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ThuePhong thuePhong = new DTO_ThuePhong();
                thuePhong.setMaPhong(rs.getString("MaPhong"));
                thuePhong.setMaPhieuThue(rs.getString("MaPhieuThue"));
                thuePhong.setMaNhanVien(rs.getString("MaNhanVien"));
                thuePhong.setNgayTao(rs.getTimestamp("NgayTao"));
                thuePhong.setNgayDen(rs.getTimestamp("NgayDen"));
                thuePhong.setNgayDi(rs.getTimestamp("NgayDi"));
                thuePhong.setCMND(rs.getString("CMND"));
                thuePhong.setTenKhachHang(rs.getString("TenKhachHang"));
                thuePhong.setSoLuong(rs.getInt("SoLuongKhach"));
                thuePhong.setGhiChu(rs.getString("GhiChu"));
                thuePhong.setTienCoc(rs.getInt("TienCoc"));
                thuePhong.setGiamGia(rs.getInt("GiamGia"));
                thuePhong.setHinhAnh(rs.getBytes("HinhAnh"));
                thuePhong.setMaPhuongThuc(rs.getString("MaPhuongThuc"));
                thuePhong.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                array.add(thuePhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<DTO_Phong> selectPhong(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuePhong.rowNumber(tuNgay, denNgay, index);
        ArrayList<DTO_Phong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_Phong phong = new DTO_Phong();
                phong.setMaPhong(rs.getString("MaPhong"));
                phong.setTenPhong(rs.getString("TenPhong"));
                phong.setMaTang(rs.getString("MaTang"));
                phong.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
                phong.setMaTrangThaiPhong(rs.getString("MaTrangThaiPhong"));
                array.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void load(ArrayList<DTO_ThuePhong> array, JLabel lblMaPhieu, JLabel lblNhanVien, JLabel lblNgayTao, JLabel lblNgayDen, JLabel lblNgayDi, JTextField txtCMND, JTextField txtTenKhach, JTextField txtSoLuong, JTextField txtGhiChu, JTextField txtTienCoc, JTextField txtGiamGia, JComboBox cboPhuongThuc) {
        for (DTO_ThuePhong thuePhong : array) {
            lblMaPhieu.setText(thuePhong.getMaPhieuThue());
            lblNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(thuePhong.getMaNhanVien()));
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy HH:mm", thuePhong.getNgayTao()));
            lblNgayDen.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy HH:mm", thuePhong.getNgayDen()));
            if (thuePhong.getNgayDi() == null) {
                lblNgayDi.setText(HELPER_ChuyenDoi.getTimeNow("dd-MM-yyyy HH:mm"));
            } else {
                lblNgayDi.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yyyy HH:mm", thuePhong.getNgayDi()));
            }
            txtCMND.setText(thuePhong.getCMND());
            txtTenKhach.setText(thuePhong.getTenKhachHang());
            txtSoLuong.setText(HELPER_ChuyenDoi.getSoString(thuePhong.getSoLuong()));
            txtGhiChu.setText(thuePhong.getGhiChu());
            txtTienCoc.setText(HELPER_ChuyenDoi.getSoString(thuePhong.getTienCoc()) + "K");
            txtGiamGia.setText(HELPER_ChuyenDoi.getSoString(thuePhong.getGiamGia()) + "K");
            GUI_dal_ThongTinPhong.hinhAnh = thuePhong.getHinhAnh();
            cboPhuongThuc.setSelectedItem(BLL_MaTenLoai.findTenPhuongThuc(thuePhong.getMaPhuongThuc()));
        }
    }

    public static void load(ArrayList<DTO_ThuePhong> array) {
        for (DTO_ThuePhong thuePhong : array) {
            GUI_dal_ChuyenPhong.maPhong = thuePhong.getMaPhong();
            GUI_dal_ChuyenPhong.maPhieuThue = thuePhong.getMaPhieuThue();
            GUI_dal_ChuyenPhong.maNhanVien = thuePhong.getMaNhanVien();
            GUI_dal_ChuyenPhong.ngayTao = thuePhong.getNgayTao();
            GUI_dal_ChuyenPhong.ngayDen = thuePhong.getNgayDen();
            GUI_dal_ChuyenPhong.ngayDi = thuePhong.getNgayDi();
            GUI_dal_ChuyenPhong.CMND = thuePhong.getCMND();
            GUI_dal_ChuyenPhong.tenKhachHang = thuePhong.getTenKhachHang();
            GUI_dal_ChuyenPhong.soLuong = thuePhong.getSoLuong();
            GUI_dal_ChuyenPhong.ghiChu = thuePhong.getGhiChu();
            GUI_dal_ChuyenPhong.tienCoc = thuePhong.getTienCoc();
            GUI_dal_ChuyenPhong.giamGia = thuePhong.getGiamGia();
            GUI_dal_ChuyenPhong.hinhAnh = thuePhong.getHinhAnh();
            GUI_dal_ChuyenPhong.maPhuongThuc = thuePhong.getMaPhuongThuc();
            GUI_dal_ChuyenPhong.trangThaiThanhToan = thuePhong.getTrangThaiThanhToan();
        }
    }

    public static void loadPhong(ArrayList<DTO_Phong> array, JLabel lblSetPhong, JLabel lblSetLoaiPhong, JLabel lblSetTrangThaiPhong) {
        for (DTO_Phong phong : array) {
            lblSetPhong.setText(phong.getTenPhong());
            lblSetLoaiPhong.setText(BLL_MaTenLoai.findTenLoaiPhong(phong.getMaLoaiPhong()));
            lblSetTrangThaiPhong.setText(BLL_MaTenLoai.findTenTrangThaiPhong(phong.getMaTrangThaiPhong()));
        }
    }

    public static void loadThuePhong(ArrayList<DTO_ThuePhong> array, JLabel lblNgayDen, JLabel lblThangDen, JLabel lblGioPhutDen, JLabel lblNgayDi, JLabel lblThangDi, JLabel lblGioPhutDi, JLabel lblTenKhachHang, JLabel lblTrangThaiPhong) {
        for (DTO_ThuePhong thuePhong : array) {
            lblNgayDen.setText(HELPER_ChuyenDoi.getNgayString("dd", thuePhong.getNgayDen()));
            lblThangDen.setText(HELPER_ChuyenDoi.getNgayString("MM", thuePhong.getNgayDen()));
            lblGioPhutDen.setText(HELPER_ChuyenDoi.getNgayString("HH:mm", thuePhong.getNgayDen()));
            if (thuePhong.getNgayDi() == null) {
                lblNgayDi.setText(HELPER_ChuyenDoi.getTimeNow("dd"));
                lblThangDi.setText(HELPER_ChuyenDoi.getTimeNow("MM"));
                lblGioPhutDi.setText(HELPER_ChuyenDoi.getTimeNow("HH:mm"));
            } else {
                lblNgayDi.setText(HELPER_ChuyenDoi.getNgayString("dd", thuePhong.getNgayDi()));
                lblThangDi.setText(HELPER_ChuyenDoi.getNgayString("MM", thuePhong.getNgayDi()));
                lblGioPhutDi.setText(HELPER_ChuyenDoi.getNgayString("HH:mm", thuePhong.getNgayDi()));
            }
            lblTenKhachHang.setText(thuePhong.getTenKhachHang());
            if (thuePhong.getTrangThaiThanhToan() == 1) {
                lblTrangThaiPhong.setText("Trả Phòng");
            }
            GUI_pnl_ChiTietThuePhong.ghiChu = thuePhong.getGhiChu();
        }
    }

    public static int countSearch(String tuNgay, String denNgay) {
        ResultSet rs = DAL_ThuePhong.countSearch(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String findMaPhieuThue(String ngayDen, String ngayDi) {
        ResultSet rs = DAL_ThuePhong.findMaPhieuThue(ngayDen, ngayDi);
        try {
            while (rs.next()) {
                return rs.getString("MaPhieuThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaPhong(int index) {
        ResultSet rs = DAL_ThuePhong.findMaPhong(index);
        try {
            while (rs.next()) {
                return rs.getString("MaPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaPhong(String maTang, int index) {
        ResultSet rs = DAL_ThuePhong.findMaPhong(maTang, index);
        try {
            while (rs.next()) {
                return rs.getString("MaPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void findThuePhong(String tuNgay, String denNgay, int index) {
        arrayThuePhong.clear();
        ResultSet rs = DAL_ThuePhong.rowNumber(tuNgay, denNgay, index);
        try {
            while (rs.next()) {
                arrayThuePhong.add(rs.getString("MaPhong"));
                if (rs.getInt("TrangThaiThanhToan") == 0) {
                    arrayThuePhong.add(BLL_MaTenLoai.findTenTrangThaiPhong(rs.getString("MaTrangThaiPhong")));
                } else {
                    arrayThuePhong.add("Trả Phòng");
                }
                arrayThuePhong.add(HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", rs.getTimestamp("NgayDen")));
                if (rs.getTimestamp("NgayDi") == null) {
                    arrayThuePhong.add(HELPER_ChuyenDoi.getTimeNow("yyyy-MM-dd HH:mm"));
                } else {
                    arrayThuePhong.add(HELPER_ChuyenDoi.getNgayString("yyyy-MM-dd HH:mm", rs.getTimestamp("NgayDi")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static String selectChuyenPhong(String maPhong, String data) {
        ResultSet rs = DAL_ThuePhong.select(maPhong);
        try {
            while (rs.next()) {
                if (rs.getTimestamp(data) == null) {
                    return HELPER_ChuyenDoi.getTimeNow("dd-MM-yy HH:mm");
                } else {
                    return HELPER_ChuyenDoi.getNgayString("dd-MM-yy HH:mm", rs.getTimestamp(data));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
