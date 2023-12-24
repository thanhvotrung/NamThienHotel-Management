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
public class DTO_Phong {

    public String maPhong;
    public String tenPhong;
    public String maTang;
    public String maLoaiPhong;
    public String maTrangThaiPhong;

    public DTO_Phong() {
    }

    public DTO_Phong(String maPhong, String tenPhong, String maTang, String maLoaiPhong, String maTrangThaiPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.maTang = maTang;
        this.maLoaiPhong = maLoaiPhong;
        this.maTrangThaiPhong = maTrangThaiPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getMaTang() {
        return maTang;
    }

    public void setMaTang(String maTang) {
        this.maTang = maTang;
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getMaTrangThaiPhong() {
        return maTrangThaiPhong;
    }

    public void setMaTrangThaiPhong(String maTrangThaiPhong) {
        this.maTrangThaiPhong = maTrangThaiPhong;
    }
}
