package io.dcloud.h52927aa1.network;

/**
 * 请求API
 */
public class RequestAPI {

    //基础URL
    public static final String BASE_URL="http://test.shourenapp.com/index.php/App/";//测试地址

    public static final String REMEN="Wwfriend/friend_index/is_refreh/true/type/2";  //热门项目
    public static final String QUANBU="Wwfriend/friend_index/is_refreh/true/type/1";    //全部项目


    public static final String POSTLOGIN="Login/msgs_wj";      //登陆验证码

    public static final String USERID="Setuser/get_changenumber";//用户id
    //http://app.shourenapp.com/index.php/App/Login/logins
    //http://app.shourenapp.com/index.php/App/Wwfriend/friend_index/is_refreh/true/type/2
//http://test.shourenapp.com/index.php/App/Setuser/get_changenumber
    /**
     * 拼接完整URL
     * @param relativeUrl
     * @return
     */
    public static String getAbsoluteUrl(String relativeUrl) {
        return RequestAPI.BASE_URL + relativeUrl;
    }

    public static String getReadmeURL(int projectId) {
        return "projects/"+projectId + "/readme";
    }

}
