package com.example.demo_sqllite;

public class DonVi {
    private int ID;
    private String TenDV, MotaDV, NgayTao, NgayCapNhat;

    public DonVi(int iD, String tenDV, String motaDV, String ngayTao, String ngayCapNhat) {
        this.ID = iD;
        this.TenDV = tenDV;
        this.MotaDV = motaDV;
        this.NgayTao = ngayTao;
        this.NgayCapNhat = ngayCapNhat;
    }


    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String tenDV) {
        TenDV = tenDV;
    }

    public String getNgayCapNhat() {
        return NgayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        NgayCapNhat = ngayCapNhat;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getMotaDV() {
        return MotaDV;
    }

    public void setMotaDV(String motaDV) {
        MotaDV = motaDV;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
