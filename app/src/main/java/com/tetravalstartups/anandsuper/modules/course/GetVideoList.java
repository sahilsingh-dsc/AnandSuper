package com.tetravalstartups.anandsuper.modules.course;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVideoList {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public GetVideoList() {
    }

    /**
     * @param data
     * @param message
     * @param url
     * @param status
     */
    public GetVideoList(String status, String message, String url, List<Datum> data) {
        super();
        this.status = status;
        this.message = message;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("iframe_id")
        @Expose
        private String iframeId;
        @SerializedName("v_title")
        @Expose
        private String vTitle;
        @SerializedName("v_description")
        @Expose
        private String vDescription;
        @SerializedName("v_path")
        @Expose
        private String vPath;
        @SerializedName("v_image")
        @Expose
        private String vImage;
        @SerializedName("attachment")
        @Expose
        private String attachment;
        @SerializedName("video_type")
        @Expose
        private String videoType;
        @SerializedName("topic_id")
        @Expose
        private String topicId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("created")
        @Expose
        private String created;

        /**
         * No args constructor for use in serialization
         */
        public Datum() {
        }

        /**
         * @param topicId
         * @param attachment
         * @param productId
         * @param releaseDate
         * @param iframeId
         * @param vImage
         * @param videoType
         * @param created
         * @param vDescription
         * @param id
         * @param vTitle
         * @param vPath
         */
        public Datum(String id, String releaseDate, String iframeId, String vTitle, String vDescription, String vPath, String vImage, String attachment, String videoType, String topicId, String productId, String created) {
            super();
            this.id = id;
            this.releaseDate = releaseDate;
            this.iframeId = iframeId;
            this.vTitle = vTitle;
            this.vDescription = vDescription;
            this.vPath = vPath;
            this.vImage = vImage;
            this.attachment = attachment;
            this.videoType = videoType;
            this.topicId = topicId;
            this.productId = productId;
            this.created = created;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getIframeId() {
            return iframeId;
        }

        public void setIframeId(String iframeId) {
            this.iframeId = iframeId;
        }

        public String getVTitle() {
            return vTitle;
        }

        public void setVTitle(String vTitle) {
            this.vTitle = vTitle;
        }

        public String getVDescription() {
            return vDescription;
        }

        public void setVDescription(String vDescription) {
            this.vDescription = vDescription;
        }

        public String getVPath() {
            return vPath;
        }

        public void setVPath(String vPath) {
            this.vPath = vPath;
        }

        public String getVImage() {
            return vImage;
        }

        public void setVImage(String vImage) {
            this.vImage = vImage;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}
