package com.utt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_token")
    @Expose
    private String user_token;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phonenumber")
    @Expose
    private int phonenumber;
    @SerializedName("permission")
    @Expose
    private int permission;
    @SerializedName("api_token")
    @Expose
    private String api_token;
    @SerializedName("Url")
    @Expose
    private String Url;
    @SerializedName("diachi")
    @Expose
    private String diachi;
    @SerializedName("customer_vip")
    @Expose
    private String customer_vip;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public DataUser(int id, String user_token, String name, String email, int phonenumber, int permission, String api_token, String url, String diachi, String customer_vip, String created_at, String updated_at) {
        this.id = id;
        this.user_token = user_token;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.permission = permission;
        this.api_token = api_token;
        Url = url;
        this.diachi = diachi;
        this.customer_vip = customer_vip;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCustomer_vip() {
        return customer_vip;
    }

    public void setCustomer_vip(String customer_vip) {
        this.customer_vip = customer_vip;
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
