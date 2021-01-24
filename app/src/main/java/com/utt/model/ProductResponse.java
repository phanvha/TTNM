package com.utt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {
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
    private ArrayList<DataProducts> dataProductsArrayList = new ArrayList<>();

    public ProductResponse(String status, int code, String message, ArrayList<DataProducts> dataProductsArrayList) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.dataProductsArrayList = dataProductsArrayList;
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

    public ArrayList<DataProducts> getDataProductsArrayList() {
        return dataProductsArrayList;
    }

    public void setDataProductsArrayList(ArrayList<DataProducts> dataProductsArrayList) {
        this.dataProductsArrayList = dataProductsArrayList;
    }
}
