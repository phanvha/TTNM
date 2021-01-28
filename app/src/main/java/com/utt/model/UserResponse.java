package com.utt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataUser dataUser;

    public UserResponse(String status, int code, String message, DataUser dataUser) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.dataUser = dataUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }
}
