package com.tetravalstartups.anandsuper.modules.course;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("image-url")
    @Expose
    private String imageUrl;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public CourseResponse() {
    }

    /**
     * @param data
     * @param imageUrl
     * @param message
     * @param status
     */
    public CourseResponse(String status, String message, String imageUrl, List<Datum> data) {
        super();
        this.status = status;
        this.message = message;
        this.imageUrl = imageUrl;
        this.data = data;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("p_image")
        @Expose
        private String pImage;

        /**
         * No args constructor for use in serialization
         */
        public Datum() {
        }

        /**
         * @param id
         * @param pImage
         * @param productName
         */
        public Datum(String id, String productName, String pImage) {
            super();
            this.id = id;
            this.productName = productName;
            this.pImage = pImage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPImage() {
            return pImage;
        }

        public void setPImage(String pImage) {
            this.pImage = pImage;
        }
    }
}
