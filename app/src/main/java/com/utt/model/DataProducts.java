package com.utt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProducts {
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Mahang")
    @Expose
    private String Mahang;
    @SerializedName("Tensanpham")
    @Expose
    private String Tensanpham;
    @SerializedName("Tenkhongdau")
    @Expose
    private String Tenkhongdau;
    @SerializedName("Url")
    @Expose
    private String Url;
    @SerializedName("Motasanpham")
    @Expose
    private String Motasanpham;
    @SerializedName("Giagoc")
    @Expose
    private int Giagoc;
    @SerializedName("Phantramkm")
    @Expose
    private int Phantramkm;
    @SerializedName("Giakm")
    @Expose
    private int Giakm;
    @SerializedName("size_nho")
    @Expose
    private int size_nho;
    @SerializedName("size_vua")
    @Expose
    private String size_vua;
    @SerializedName("size_lon")
    @Expose
    private int size_lon;
    @SerializedName("New")
    @Expose
    private int New;
    @SerializedName("ID_TL")
    @Expose
    private int ID_TL;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public DataProducts(int id, String mahang, String tensanpham, String tenkhongdau, String url, String motasanpham, int giagoc, int phantramkm, int giakm, int size_nho, String size_vua, int size_lon, int aNew, int ID_TL, String created_at, String updated_at) {
        this.id = id;
        Mahang = mahang;
        Tensanpham = tensanpham;
        Tenkhongdau = tenkhongdau;
        Url = url;
        Motasanpham = motasanpham;
        Giagoc = giagoc;
        Phantramkm = phantramkm;
        Giakm = giakm;
        this.size_nho = size_nho;
        this.size_vua = size_vua;
        this.size_lon = size_lon;
        New = aNew;
        this.ID_TL = ID_TL;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMahang() {
        return Mahang;
    }

    public void setMahang(String mahang) {
        Mahang = mahang;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public String getTenkhongdau() {
        return Tenkhongdau;
    }

    public void setTenkhongdau(String tenkhongdau) {
        Tenkhongdau = tenkhongdau;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getGiagoc() {
        return Giagoc;
    }

    public void setGiagoc(int giagoc) {
        Giagoc = giagoc;
    }

    public int getPhantramkm() {
        return Phantramkm;
    }

    public void setPhantramkm(int phantramkm) {
        Phantramkm = phantramkm;
    }

    public int getGiakm() {
        return Giakm;
    }

    public void setGiakm(int giakm) {
        Giakm = giakm;
    }

    public int getSize_nho() {
        return size_nho;
    }

    public void setSize_nho(int size_nho) {
        this.size_nho = size_nho;
    }

    public String getSize_vua() {
        return size_vua;
    }

    public void setSize_vua(String size_vua) {
        this.size_vua = size_vua;
    }

    public int getSize_lon() {
        return size_lon;
    }

    public void setSize_lon(int size_lon) {
        this.size_lon = size_lon;
    }

    public int getNew() {
        return New;
    }

    public void setNew(int aNew) {
        New = aNew;
    }

    public int getID_TL() {
        return ID_TL;
    }

    public void setID_TL(int ID_TL) {
        this.ID_TL = ID_TL;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
