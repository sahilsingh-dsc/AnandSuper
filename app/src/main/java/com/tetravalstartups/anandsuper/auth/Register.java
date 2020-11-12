package com.tetravalstartups.anandsuper.auth;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Register() {
    }

    /**
     *
     * @param message
     * @param userId
     * @param status
     */
    public Register(String status, String message, Integer userId) {
        super();
        this.status = status;
        this.message = message;
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
