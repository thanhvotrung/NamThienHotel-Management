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
public class DTO_HoaDon {

    public String maHoaDon;
    public String maPhieuThue;
    public Date ngayTao;
    public String maNhanVien;
    public int tienPhong;
    public int tienDichVu;
    public int daTra;
    public int conLai;
    public String maPhuongThuc;

    public DTO_HoaDon() {
    }

    public DTO_HoaDon(String maHoaDon, String maPhieuThue, Date ngayTao, String maNhanVien, int tienPhong, int tienDichVu, int daTra, int conLai, String maPhuongThuc) {
        this.maHoaDon = maHoaDon;
        this.maPhieuThue = maPhieuThue;
        this.ngayTao = ngayTao;
        this.maNhanVien = maNhanVien;
        this.tienPhong = tienPhong;
        this.tienDichVu = tienDichVu;
        this.daTra = daTra;
        this.conLai = conLai;
        this.maPhuongThuc = maPhuongThuc;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaPhieuThue() {
        return maPhieuThue;
    }

    public void setMaPhieuThue(String maPhieuThue) {
        this.maPhieuThue = maPhieuThue;
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

    public int getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(int tienPhong) {
        this.tienPhong = tienPhong;
    }

    public int getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(int tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public int getDaTra() {
        return daTra;
    }

    public void setDaTra(int daTra) {
        this.daTra = daTra;
    }

    public int getConLai() {
        return conLai;
    }

    public void setConLai(int conLai) {
        this.conLai = conLai;
    }

    public String getMaPhuongThuc() {
        return maPhuongThuc;
    }

    public void setMaPhuongThuc(String maPhuongThuc) {
        this.maPhuongThuc = maPhuongThuc;
    }  
}
