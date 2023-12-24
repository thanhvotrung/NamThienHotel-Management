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
public class DTO_NhapKho {

    public String maNhapKho;
    public String maNhanVien;
    public Date ngayTao;
    public String ghiChu;

    public DTO_NhapKho() {
    }

    public DTO_NhapKho(String maNhapKho, String maNhanVien, Date ngayTao, String ghiChu) {
        this.maNhapKho = maNhapKho;
        this.maNhanVien = maNhanVien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }

    public String getMaNhapKho() {
        return maNhapKho;
    }

    public void setMaNhapKho(String maNhapKho) {
        this.maNhapKho = maNhapKho;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
