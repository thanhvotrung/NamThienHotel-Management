/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DAL_SoDoPhong;
import DAL.DAL_ThuNgan;
import DAL.DAL_ThuePhong;
import DTO.DTO_DichVu;
import DTO.DTO_Phong;
import DTO.DTO_ThuePhong;
import HELPER.HELPER_ChuyenDoi;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class BLL_ThuNgan {

    public static ArrayList<DTO_ThuePhong> selectHoaDon(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuNgan.rowNumberHoadon(tuNgay, denNgay, index);
        ArrayList<DTO_ThuePhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_ThuePhong thuePhong = new DTO_ThuePhong();
                thuePhong.setMaPhong(rs.getString("MaPhong"));
                thuePhong.setMaPhieuThue(rs.getString("MaHoaDon"));
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

    public static ArrayList<DTO_ThuePhong> selectTienCoc(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuNgan.rowNumberTienCoc(tuNgay, denNgay, index);
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

    public static ArrayList<DTO_Phong> selectByHoaDon(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuNgan.rowNumberHoadon(tuNgay, denNgay, index);
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

    public static ArrayList<DTO_Phong> selectByTienCoc(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuNgan.rowNumberTienCoc(tuNgay, denNgay, index);
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

    public static ArrayList<DTO_DichVu> selectDichVu(String tuNgay, String denNgay, int index) {
        ResultSet rs = DAL_ThuNgan.rowNumberDichVu(tuNgay, denNgay, index);
        ArrayList<DTO_DichVu> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_DichVu dichVu = new DTO_DichVu();
                dichVu.setMaNhanVien(rs.getString("MaNhanVien"));
                dichVu.setMaPhieuDichVu(rs.getString("MaPhieuDichVu"));
                dichVu.setNgayTao(rs.getTimestamp("NgayTao"));
                dichVu.setMaPhieuThue(rs.getString("MaPhieuThue"));
                dichVu.setTrangThaiThanhToan(rs.getInt("TrangThaiThanhToan"));
                array.add(dichVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadPhong(ArrayList<DTO_Phong> array, JLabel lblSetPhong, JLabel lblSetLoaiPhong) {
        for (DTO_Phong phong : array) {
            lblSetPhong.setText(phong.getTenPhong());
            lblSetLoaiPhong.setText(BLL_MaTenLoai.findTenLoaiPhong(phong.getMaLoaiPhong()));
        }
    }

    public static void loadThuePhong(ArrayList<DTO_ThuePhong> array, JLabel lblNgayDen, JLabel lblThangDen, JLabel lblGioPhutDen, JLabel lblNgayDi, JLabel lblThangDi, JLabel lblGioPhutDi, JLabel lblMaPhieu, JLabel lblNgayTao, JLabel lblGioPhutTao, JLabel lblLoaiThanhToan, JLabel lblNhanVien, JLabel lblPhuongThuc) {
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
            lblMaPhieu.setText(thuePhong.getMaPhieuThue());
            lblLoaiThanhToan.setText("Hóa Đơn");
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy", thuePhong.getNgayTao()));
            lblGioPhutTao.setText(HELPER_ChuyenDoi.getNgayString("HH:mm", thuePhong.getNgayTao()));
            lblNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(thuePhong.getMaNhanVien()));
            lblPhuongThuc.setText(BLL_MaTenLoai.findTenPhuongThuc(thuePhong.getMaPhuongThuc()));
        }
    }

    public static void loadTienCoc(ArrayList<DTO_ThuePhong> array, JLabel lblNgayDen, JLabel lblThangDen, JLabel lblGioPhutDen, JLabel lblNgayDi, JLabel lblThangDi, JLabel lblGioPhutDi, JLabel lblMaPhieu, JLabel lblNgayTao, JLabel lblGioPhutTao, JLabel lblLoaiThanhToan, JLabel lblNhanVien, JLabel lblPhuongThuc) {
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
            lblMaPhieu.setText(thuePhong.getMaPhieuThue());
            lblLoaiThanhToan.setText("Tiền Cọc");
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy", thuePhong.getNgayTao()));
            lblGioPhutTao.setText(HELPER_ChuyenDoi.getNgayString("HH:mm", thuePhong.getNgayTao()));
            lblNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(thuePhong.getMaNhanVien()));
            lblPhuongThuc.setText(BLL_MaTenLoai.findTenPhuongThuc(thuePhong.getMaPhuongThuc()));
        }
    }

    public static void loadDichVu(ArrayList<DTO_DichVu> array, JLabel lblLoaiKhach, JLabel lblMaNhanVien, JLabel lblMaPhieuDichVu, JLabel lblNgayTao, JLabel lblGioPhutTao, JLabel lblLoaiThanhToan, JLabel lblPhuongThuc) {
        for (DTO_DichVu dichVu : array) {
            lblLoaiKhach.setText("Khách Lẻ");
            lblMaNhanVien.setText(BLL_MaTenLoai.findTenNhanVien(dichVu.getMaNhanVien()));
            lblMaPhieuDichVu.setText(dichVu.getMaPhieuDichVu());
            lblNgayTao.setText(HELPER_ChuyenDoi.getNgayString("dd-MM-yy", dichVu.getNgayTao()));
            lblGioPhutTao.setText(HELPER_ChuyenDoi.getNgayString("HH:mm", dichVu.getNgayTao()));
            lblLoaiThanhToan.setText("Dịch Vụ");
            lblPhuongThuc.setText("Tiền Mặt");
        }
    }

    public static int countHoaDon(String tuNgay, String denNgay) {
        ResultSet rs = DAL_ThuNgan.countHoaDon(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countTienCoc(String tuNgay, String denNgay) {
        ResultSet rs = DAL_ThuNgan.countTienCoc(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countDichVu(String tuNgay, String denNgay) {
        ResultSet rs = DAL_ThuNgan.countDichVu(tuNgay, denNgay);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
