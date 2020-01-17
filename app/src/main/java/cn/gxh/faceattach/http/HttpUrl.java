package cn.gxh.faceattach.http;

public class HttpUrl {
    public static final String BASE_URL = "http://doormobile.hyzschina.com";

    public static final String MOBILE_LOGIN = BASE_URL+"/api/mobile/MobileUserLogin";
    public static final String MOBILE_REGISTER = BASE_URL+"/api/mobile/RegisterMobileUser";
    public static final String MOBILE_ADD = BASE_URL+"/api/mobile/RegisterUser";
    public static final String MOBILE_DEVICES= BASE_URL+"/api/mobile/GetMobileUserMobileList";
    public static final String MOBILE_SET_DOOR_STATUS= BASE_URL+"/api/mobile/SetDoorStatus";

    public static final String MOBILE_RECORD=BASE_URL+"/api/mobile/GetMobileUserAccessList";
}
