package cn.gxh.faceattach.bean;

public class MobileSetDoorStatusSend {

    /**
     * MobileId : 00000000-0000-0000-0000-000000000000
     * DoorStatus : 0
     */

    private String MobileId;
    private int DoorStatus;

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
