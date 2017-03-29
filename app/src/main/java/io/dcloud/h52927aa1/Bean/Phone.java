package io.dcloud.h52927aa1.Bean;

/**
 * Created by wuxiaohui on 17/3/25.
 */

public class Phone {

    public static int userid;
    public static String status;
    public static String message;

    public static String getVerify() {
        return verify;
    }

    public static void setVerify(String verify) {
        Phone.verify = verify;
    }

    public static String verify;

    public static int getUserid() {
        return userid;
    }

    public static void setUserid(int userid) {
        Phone.userid = userid;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Phone.status = status;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        Phone.message = message;
    }
}
