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
public class DTO_DatPhong {

    public String maPhieu;
    public String maLoaiPhong;
    public String loaiKhach;
    public String maNhanVien;
    public Date ngayTao;
    public Date ngayDen;
    public Date ngayDi;
    public String tenKhachHang;
    public int soLuongKhach;
    public int soDienThoai;
    public String ghiChu;
    public int tienCoc;
    public String trangThai;

    public DTO_DatPhong() {
    }

    public DTO_DatPhong(String maPhieu, String maLoaiPhong, String loaiKhach, String maNhanVien, Date ngayTao, Date ngayDen, Date ngayDi, String tenKhachHang, int soLuongKhach, int soDienThoai, String ghiChu, int tienCoc, String trangThai) {
        this.maPhieu = maPhieu;
        this.maLoaiPhong = maLoaiPhong;
        this.loaiKhach = loaiKhach;
        this.maNhanVien = maNhanVien;
        this.ngayTao = ngayTao;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
        this.tenKhachHang = tenKhachHang;
        this.soLuongKhach = soLuongKhach;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
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

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
