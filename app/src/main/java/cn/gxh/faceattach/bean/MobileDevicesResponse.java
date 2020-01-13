package cn.gxh.faceattach.bean;

import java.util.List;

public class MobileDevicesResponse extends BaseBean{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * MobileName : 192.168.1.16
         * MobileId : ea153044-6418-479b-925d-b1b986de4e3b
         * DoorStatus : 0
         */

        private String MobileName;
        private String MobileId;
        private int DoorStatus;

        public String getMobileName() {
            return MobileName;
        }

        public void setMobileName(String MobileName) {
            this.MobileName = MobileName;
        }

        public String getMobileId() {
            return MobileId;
        }

        public void setMobileId(String MobileId) {
            this.MobileId = MobileId;
        }

        public int getDoorStatus() {
            return DoorStatus;
        }

        public void setDoorStatus(int DoorStatus) {
            this.DoorStatus = DoorStatus;
        }
    }
}
