/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author CherryCe
 */
public class DTO_ChiTietDichVu {

    public String maChiTiet;
    public String maPhieuDichVu;
    public String maSanPham;
    public int soLuong;
    public int giaTien;

    public DTO_ChiTietDichVu() {
    }

    public DTO_ChiTietDichVu(String maChiTiet, String maPhieuDichVu, String maSanPham, int soLuong, int giaTien) {
        this.maChiTiet = maChiTiet;
        this.maPhieuDichVu = maPhieuDichVu;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaPhieuDichVu() {
        return maPhieuDichVu;
    }

    public void setMaPhieuDichVu(String maPhieuDichVu) {
        this.maPhieuDichVu = maPhieuDichVu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
