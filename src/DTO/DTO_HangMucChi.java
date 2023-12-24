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
public class DTO_HangMucChi {

    public String maChi;
    public String mucChi;

    public DTO_HangMucChi() {
    }

    public DTO_HangMucChi(String maChi, String mucChi) {
        this.maChi = maChi;
        this.mucChi = mucChi;
    }

    public String getMaChi() {
        return maChi;
    }

    public void setMaChi(String maChi) {
        this.maChi = maChi;
    }

    public String getMucChi() {
        return mucChi;
    }

    public void setMucChi(String mucChi) {
        this.mucChi = mucChi;
    }
}
