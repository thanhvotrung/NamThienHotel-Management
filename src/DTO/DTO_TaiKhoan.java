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
public class DTO_TaiKhoan {

    public String maTaiKhoan;
    public String maNhanVien;
    public String tenDangNhap;
    public String matKhau;
    public int checkDangNhap;

    public DTO_TaiKhoan() {
    }

    public DTO_TaiKhoan(String maTaiKhoan, String maNhanVien, String tenDangNhap, String matKhau, int checkDangNhap) {
        this.maTaiKhoan = maTaiKhoan;
        this.maNhanVien = maNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.checkDangNhap = checkDangNhap;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getCheckDangNhap() {
        return checkDangNhap;
    }

    public void setCheckDangNhap(int checkDangNhap) {
        this.checkDangNhap = checkDangNhap;
    }
}
