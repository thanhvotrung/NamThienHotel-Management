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
public class DTO_PhieuChi {

    public String maPhieuChi;
    public String maLoaiTienChi;
    public int tienChi;
    public String maPhuongThuc;
    public String ghiChu;
    public Date ngayTao;
    public String maNhanVien;

    public DTO_PhieuChi() {
    }

    public DTO_PhieuChi(String maPhieuChi, String maLoaiTienChi, int tienChi, String maPhuongThuc, String ghiChu, Date ngayTao, String maNhanVien) {
        this.maPhieuChi = maPhieuChi;
        this.maLoaiTienChi = maLoaiTienChi;
        this.tienChi = tienChi;
        this.maPhuongThuc = maPhuongThuc;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.maNhanVien = maNhanVien;
    }

    public String getMaPhieuChi() {
        return maPhieuChi;
    }

    public void setMaPhieuChi(String maPhieuChi) {
        this.maPhieuChi = maPhieuChi;
    }

    public String getMaLoaiTienChi() {
        return maLoaiTienChi;
    }

    public void setMaLoaiTienChi(String maLoaiTienChi) {
        this.maLoaiTienChi = maLoaiTienChi;
    }

    public int getTienChi() {
        return tienChi;
    }

    public void setTienChi(int tienChi) {
        this.tienChi = tienChi;
    }

    public String getMaPhuongThuc() {
        return maPhuongThuc;
    }

    public void setMaPhuongThuc(String maPhuongThuc) {
        this.maPhuongThuc = maPhuongThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
