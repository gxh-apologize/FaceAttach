package cn.gxh.faceattach.bean;

public class MobileLoginResponse {

    /**
     * data : {"Id":"bb808064-fad1-4abe-ba8b-4eb6a05c533b","NickName":"string"}
     * state : 1
     * message : null
     */

    private DataBean data;
    private int state;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * Id : bb808064-fad1-4abe-ba8b-4eb6a05c533b
         * NickName : string
         */

        private String Id;
        private String NickName;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }
    }
}
