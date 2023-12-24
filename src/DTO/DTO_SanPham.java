/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Trung Thanh
 */
public class DTO_SanPham {

    public String maSanPham;
    public String tenSanPham;
    public String maLoaiSanPham;
    public String donViTinh;
    public int giaBan;
    public Date ngayTao;

    public DTO_SanPham() {
    }

    public DTO_SanPham(String maSanPham, String tenSanPham, String maLoaiSanPham, String donViTinh, int giaBan, Date ngayTao) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoaiSanPham = maLoaiSanPham;
        this.donViTinh = donViTinh;
        this.giaBan = giaBan;
        this.ngayTao = ngayTao;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
