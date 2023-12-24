/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author CherryCe
 */
public class DTO_SoTang {
    
    public String maTang;
    public String tenTang;

    public DTO_SoTang() {
    }

    public DTO_SoTang(String maTang, String tenTang) {
        this.maTang = maTang;
        this.tenTang = tenTang;
    }

    public String getMaTang() {
        return maTang;
    }

    public void setMaTang(String maTang) {
        this.maTang = maTang;
    }

    public String getTenTang() {
        return tenTang;
    }

    public void setTenTang(String tenTang) {
        this.tenTang = tenTang;
    }
}
