/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DAL_MaTenLoai;
import DAL.DAL_SoTang;
import DTO.DTO_HangMucChi;
import DTO.DTO_LoaiPhong;
import DTO.DTO_LoaiSanPham;
import DTO.DTO_MaTenLoai;
import DTO.DTO_PhuongThucThanhToan;
import DTO.DTO_SoTang;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CherryCe
 */
public class BLL_MaTenLoai {

    public static ArrayList<DTO_SoTang> selectTenTang() {
        ResultSet rs = DAL_MaTenLoai.selectTenTang();
        ArrayList<DTO_SoTang> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_SoTang soTang = new DTO_SoTang();
                soTang.setTenTang(rs.getString("TenTang"));
                array.add(soTang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadTenTang(ArrayList<DTO_SoTang> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_SoTang soTang : array) {
            Object obj = soTang.getTenTang();
            cboModel.addElement(obj);
        }
    }

    public static String findTenTang(String maTang) {
        ResultSet rs = DAL_MaTenLoai.findTenTang(maTang);
        try {
            while (rs.next()) {
                return rs.getString("TenTang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaTang(String tenTang) {
        ResultSet rs = DAL_MaTenLoai.findMaTang(tenTang);
        try {
            while (rs.next()) {
                return rs.getString("MaTang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<DTO_LoaiPhong> selectTenLoaiPhong() {
        ResultSet rs = DAL_MaTenLoai.selectTenLoaiPhong();
        ArrayList<DTO_LoaiPhong> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_LoaiPhong loaiPhong = new DTO_LoaiPhong();
                loaiPhong.setTenPhong(rs.getString("TenLoaiPhong"));
                array.add(loaiPhong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadTenLoaiPhong(ArrayList<DTO_LoaiPhong> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_LoaiPhong loaiPhong : array) {
            Object obj = loaiPhong.getTenPhong();
            cboModel.addElement(obj);
        }
    }

    public static String findTenLoaiPhong(String maLoaiPhong) {
        ResultSet rs = DAL_MaTenLoai.findTenLoaiPhong(maLoaiPhong);
        try {
            while (rs.next()) {
                return rs.getString("TenLoaiPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaLoaiPhong(String tenLoaiPhong) {
        ResultSet rs = DAL_MaTenLoai.findMaLoaiPhong(tenLoaiPhong);
        try {
            while (rs.next()) {
                return rs.getString("MaLoaiPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenTrangThaiPhong(String maTrangThaiPhong) {
        ResultSet rs = DAL_MaTenLoai.findTenTrangThaiPhong(maTrangThaiPhong);
        try {
            while (rs.next()) {
                return rs.getString("TenTrangThaiPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaTrangThaiPhong(String tenTrangThaiPhong) {
        ResultSet rs = DAL_MaTenLoai.findMaTrangThaiPhong(tenTrangThaiPhong);
        try {
            while (rs.next()) {
                return rs.getString("MaTrangThaiPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenPhong(String maPhong) {
        ResultSet rs = DAL_MaTenLoai.findTenPhong(maPhong);
        try {
            while (rs.next()) {
                return rs.getString("TenPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaPhong(String tenPhong) {
        ResultSet rs = DAL_MaTenLoai.findMaPhong(tenPhong);
        try {
            while (rs.next()) {
                return rs.getString("MaPhong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenNhanVien(String maNhanVien) {
        ResultSet rs = DAL_MaTenLoai.findTenNhanVien(maNhanVien);
        try {
            while (rs.next()) {
                return rs.getString("TenNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaNhanVien(String tenNhanVien) {
        ResultSet rs = DAL_MaTenLoai.findMaNhanVien(tenNhanVien);
        try {
            while (rs.next()) {
                return rs.getString("MaNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenChucVu(String maChucVu) {
        ResultSet rs = DAL_MaTenLoai.findTenChucVu(maChucVu);
        try {
            while (rs.next()) {
                return rs.getString("TenChucVu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaChucVu(String tenChucVu) {
        ResultSet rs = DAL_MaTenLoai.findMaChucVu(tenChucVu);
        try {
            while (rs.next()) {
                return rs.getString("MaChucVu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<DTO_LoaiSanPham> selectTenLoaiSanPham() {
        ResultSet rs = DAL_MaTenLoai.selectTenLoaiSanPham();
        ArrayList<DTO_LoaiSanPham> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_LoaiSanPham sanPham = new DTO_LoaiSanPham();
                sanPham.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                array.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadTenLoaiSanPham(ArrayList<DTO_LoaiSanPham> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_LoaiSanPham loaiSanPham : array) {
            Object obj = loaiSanPham.getTenLoaiSanPham();
            cboModel.addElement(obj);
        }
    }

    public static String findTenLoaiSanPham(String maLoaiSanPham) {
        ResultSet rs = DAL_MaTenLoai.findTenLoaiSanPham(maLoaiSanPham);
        try {
            while (rs.next()) {
                return rs.getString("TenLoaiSanPham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaLoaiSanPham(String tenLoaiSanPham) {
        ResultSet rs = DAL_MaTenLoai.findMaLoaiSanPham(tenLoaiSanPham);
        try {
            while (rs.next()) {
                return rs.getString("MaLoaiSanPham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenSanPham(String maSanPham) {
        ResultSet rs = DAL_MaTenLoai.findTenSanPham(maSanPham);
        try {
            while (rs.next()) {
                return rs.getString("TenSanPham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaSanPham(String tenSanPham) {
        ResultSet rs = DAL_MaTenLoai.findMaSanPham(tenSanPham);
        try {
            while (rs.next()) {
                return rs.getString("MaSanPham");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenLoaiTienChi(String maLoaiTienChi) {
        ResultSet rs = DAL_MaTenLoai.findTenLoaiTienChi(maLoaiTienChi);
        try {
            while (rs.next()) {
                return rs.getString("TenLoaiTienChi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaLoaiTienChi(String tenLoaiTienChi) {
        ResultSet rs = DAL_MaTenLoai.findMaLoaiTienChi(tenLoaiTienChi);
        try {
            while (rs.next()) {
                return rs.getString("MaLoaiTienChi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findTenPhuongThuc(String maPhuongThuc) {
        ResultSet rs = DAL_MaTenLoai.findTenPhuongThuc(maPhuongThuc);
        try {
            while (rs.next()) {
                return rs.getString("TenPhuongThuc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findMaPhuongThuc(String tenPhuongThuc) {
        ResultSet rs = DAL_MaTenLoai.findMaPhuongThuc(tenPhuongThuc);
        try {
            while (rs.next()) {
                return rs.getString("MaPhuongThuc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<DTO_HangMucChi> selectTenMucChi() {
        ResultSet rs = DAL_MaTenLoai.selectTenMucChi();
        ArrayList<DTO_HangMucChi> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_HangMucChi hangMucChi = new DTO_HangMucChi();
                hangMucChi.setMucChi(rs.getString("TenLoaiTienChi"));
                array.add(hangMucChi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadTenMucChi(ArrayList<DTO_HangMucChi> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_HangMucChi hangMucChi : array) {
            Object obj = hangMucChi.getMucChi();
            cboModel.addElement(obj);
        }
    }

    public static ArrayList<DTO_PhuongThucThanhToan> selectTenPhuongThuc() {
        ResultSet rs = DAL_MaTenLoai.selectTenPhuongThuc();
        ArrayList<DTO_PhuongThucThanhToan> array = new ArrayList<>();
        try {
            while (rs.next()) {
                DTO_PhuongThucThanhToan phuongThuc = new DTO_PhuongThucThanhToan();
                phuongThuc.setTenPhuongThuc(rs.getString("TenPhuongThuc"));
                array.add(phuongThuc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void loadTenPhuongThuc(ArrayList<DTO_PhuongThucThanhToan> array, JComboBox cbo) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cbo.getModel();
        cboModel.removeAllElements();
        for (DTO_PhuongThucThanhToan phuongThuc : array) {
            Object obj = phuongThuc.getTenPhuongThuc();
            cboModel.addElement(obj);
        }
    }
}
