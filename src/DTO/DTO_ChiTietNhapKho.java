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
public class DTO_ChiTietNhapKho {

    public String maChiTietNhapKho;
    public String maNhapKho;
    public String maSanPham;
    public int soLuong;
    public int giaNhap;

    public DTO_ChiTietNhapKho() {
    }

    public DTO_ChiTietNhapKho(String maChiTietNhapKho, String maNhapKho, String maSanPham, int soLuong, int giaNhap) {
        this.maChiTietNhapKho = maChiTietNhapKho;
        this.maNhapKho = maNhapKho;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    public String getMaChiTietNhapKho() {
        return maChiTietNhapKho;
    }

    public void setMaChiTietNhapKho(String maChiTietNhapKho) {
        this.maChiTietNhapKho = maChiTietNhapKho;
    }

    public String getMaNhapKho() {
        return maNhapKho;
    }

    public void setMaNhapKho(String maNhapKho) {
        this.maNhapKho = maNhapKho;
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

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }
}
