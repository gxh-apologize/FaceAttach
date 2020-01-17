package cn.gxh.faceattach.bean;

import java.util.List;

public class MobileRecordResponse extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * NickName : string
         * PicPath : string
         * RecordTime : 2020-01-14T00:28:07.085Z
         * RecordTimeStr : string
         */

        private String NickName;
        private String PicPath;
        private String RecordTime;
        private String RecordTimeStr;

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getPicPath() {
            return PicPath;
        }

        public void setPicPath(String PicPath) {
            this.PicPath = PicPath;
        }

        public String getRecordTime() {
            return RecordTime;
        }

        public void setRecordTime(String RecordTime) {
            this.RecordTime = RecordTime;
        }

        public String getRecordTimeStr() {
            return RecordTimeStr;
        }

        public void setRecordTimeStr(String RecordTimeStr) {
            this.RecordTimeStr = RecordTimeStr;
        }
    }
}
