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
public class DTO_DichVu {

    public String maPhieuDichVu;
    public String maPhieuThue;
    public String maNhanVien;
    public Date ngayTao;
    public String ghiChu;
    public int trangThaiThanhToan;

    public DTO_DichVu() {
    }

    public DTO_DichVu(String maPhieuDichVu, String maPhieuThue, String maNhanVien, Date ngayTao, String ghiChu, int trangThaiThanhToan) {
        this.maPhieuDichVu = maPhieuDichVu;
        this.maPhieuThue = maPhieuThue;
        this.maNhanVien = maNhanVien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getMaPhieuDichVu() {
        return maPhieuDichVu;
    }

    public void setMaPhieuDichVu(String maPhieuDichVu) {
        this.maPhieuDichVu = maPhieuDichVu;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }
}
