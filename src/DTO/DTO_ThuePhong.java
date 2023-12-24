/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author CherryCe
 */
public class DTO_ThuePhong {

    public String maPhong;
    public String maPhieuThue;
    public String maNhanVien;
    public Date ngayTao;
    public Date ngayDen;
    public Date ngayDi;
    public String CMND;
    public String tenKhachHang;
    public int soLuong;
    public String ghiChu;
    public int tienCoc;
    public int giamGia;
    public byte[] hinhAnh;
    public String maPhuongThuc;
    public int trangThaiThanhToan;

    public DTO_ThuePhong() {
    }

    public DTO_ThuePhong(String maPhong, String maPhieuThue, String maNhanVien, Date ngayTao, Date ngayDen, Date ngayDi, String CMND, String tenKhachHang, int soLuong, String ghiChu, int tienCoc, int giamGia, byte[] hinhAnh, String maPhuongThuc, int trangThaiThanhToan) {
        this.maPhong = maPhong;
        this.maPhieuThue = maPhieuThue;
        this.maNhanVien = maNhanVien;
        this.ngayTao = ngayTao;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
        this.CMND = CMND;
        this.tenKhachHang = tenKhachHang;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
        this.tienCoc = tienCoc;
        this.giamGia = giamGia;
        this.hinhAnh = hinhAnh;
        this.maPhuongThuc = maPhuongThuc;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaPhieuThue() {
        return maPhieuThue;
    }

    public void setMaPhieuThue(String maPhieuThue) {
        this.maPhieuThue = maPhieuThue;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(int tienCoc) {
        this.tienCoc = tienCoc;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaPhuongThuc() {
        return maPhuongThuc;
    }

    public void setMaPhuongThuc(String maPhuongThuc) {
        this.maPhuongThuc = maPhuongThuc;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }
}
