package cn.gxh.faceattach.bean;

public class MobileRegisterSend {


    /**
     * UserName : string
     * UserPass : string
     * NickName : string
     */

    private String UserName;
    private String UserPass;
    private String NickName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String UserPass) {
        this.UserPass = UserPass;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
}
