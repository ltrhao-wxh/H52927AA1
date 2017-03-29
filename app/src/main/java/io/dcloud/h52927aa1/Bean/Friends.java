package io.dcloud.h52927aa1.Bean;

import java.util.List;

/**
 * Created by wuxiaohui on 17/3/8.
 */

public class Friends {


    /**
     * content_id : 429
     * content_user_id : 162
     * content_content : 3月7日，电视连续剧【大盛魁】在内蒙电视台新闻综合频道首播，首日收视率达到4.118%，市场份额达到11.376%，创历史新高
     * content_time : 1小时前
     * content_voice : []
     * content_img : [{"a":"Public/Upload/2017-03-10/958957e5374c11bd7211287050b6c0ae1489126390869.jpg","b":"Public/Upload/2017-03-10/33837625966a363df9d188712aa4e9b41489126390869.jpg"}]
     * content_video : null
     * content_good : 0
     * content_report : 0
     * content_report_type : 0
     * content_hot : 0
     * content_follow : null
     * content_address : null
     * is_hot : 1
     * hot_sort : 0
     * con_state : 1
     * user_id : 162
     * user_cooked_name : 黄伟
     * user_number : 38
     * user_img : http://wx.qlogo.cn/mmopen/ajNVdqHZLLD6bLlWyHLQHVib1nZic7wuibG8icBg04icY8Qtc0p5ibbnZuxAtKFnSkrD6jvajdL6VGjZtFeekgDEyG2w/0
     * user_type : 0000
     * user_level : 1
     * title : 娱乐
     * pp : []
     * dianzan : []
     */

    private String content_id;
    private String content_user_id;
    private String content_content;
    private String content_time;
    private String content_video;
    private String content_good;
    private String content_report;
    private String content_report_type;
    private String content_hot;
    private String content_follow;
    private String content_address;
    private String is_hot;
    private String hot_sort;
    private String con_state;
    private String user_id;
    private String user_cooked_name;
    private String user_number;
    private String user_img;
    private String user_type;
    private String user_level;
    private String title;
    private List<String> content_voice;
    private List<String> content_img;
    private List<String> pp;
    private List<String> dianzan;
    private boolean Video;

    public boolean isVideo() {
        return Video;
    }

    public void setVideo(boolean video) {
        Video = video;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "content_id='" + content_id + '\'' +
                ", content_user_id='" + content_user_id + '\'' +
                ", content_content='" + content_content + '\'' +
                ", content_time='" + content_time + '\'' +
                ", content_video='" + content_video + '\'' +
                ", content_good='" + content_good + '\'' +
                ", content_report='" + content_report + '\'' +
                ", content_report_type='" + content_report_type + '\'' +
                ", content_hot='" + content_hot + '\'' +
                ", content_follow='" + content_follow + '\'' +
                ", content_address='" + content_address + '\'' +
                ", is_hot='" + is_hot + '\'' +
                ", hot_sort='" + hot_sort + '\'' +
                ", con_state='" + con_state + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_cooked_name='" + user_cooked_name + '\'' +
                ", user_number='" + user_number + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_type='" + user_type + '\'' +
                ", user_level='" + user_level + '\'' +
                ", title='" + title + '\'' +
                ", content_voice=" + content_voice +
                ", content_img=" + content_img +
                ", pp=" + pp +
                ", dianzan=" + dianzan +
                '}';
    }
    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_video() {
        return content_video;
    }

    public void setContent_video(String content_video) {
        this.content_video = content_video;
    }

    public void setContent_user_id(String content_user_id) {
        this.content_user_id = content_user_id;
    }

    public void setContent_content(String content_content) {
        this.content_content = content_content;
    }

    public void setContent_time(String content_time) {
        this.content_time = content_time;
    }


    public void setContent_good(String content_good) {
        this.content_good = content_good;
    }

    public void setContent_report(String content_report) {
        this.content_report = content_report;
    }

    public void setContent_report_type(String content_report_type) {
        this.content_report_type = content_report_type;
    }

    public void setContent_hot(String content_hot) {
        this.content_hot = content_hot;
    }

    public void setContent_follow(String content_follow) {
        this.content_follow = content_follow;
    }

    public void setContent_address(String content_address) {
        this.content_address = content_address;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public void setHot_sort(String hot_sort) {
        this.hot_sort = hot_sort;
    }

    public void setCon_state(String con_state) {
        this.con_state = con_state;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_cooked_name(String user_cooked_name) {
        this.user_cooked_name = user_cooked_name;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent_voice(List<String> content_voice) {
        this.content_voice =content_voice;
    }

    public void setContent_img(List<String> content_img) {
        this.content_img = content_img;
    }

    public void setPp(List<String> pp) {
        this.pp = pp;
    }

    public void setDianzan(List<String> dianzan) {
        this.dianzan = dianzan;
    }

    public String getContent_id() {
        return content_id;
    }

    public String getContent_user_id() {
        return content_user_id;
    }

    public String getContent_content() {
        return content_content;
    }

    public String getContent_time() {
        return content_time;
    }


    public String getContent_good() {
        return content_good;
    }

    public String getContent_report() {
        return content_report;
    }

    public String getContent_report_type() {
        return content_report_type;
    }

    public String getContent_hot() {
        return content_hot;
    }

    public String getContent_follow() {
        return content_follow;
    }

    public String getContent_address() {
        return content_address;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public String getHot_sort() {
        return hot_sort;
    }

    public String getCon_state() {
        return con_state;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_cooked_name() {
        return user_cooked_name;
    }

    public String getUser_number() {
        return user_number;
    }

    public String getUser_img() {
        return user_img;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getUser_level() {
        return user_level;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContent_voice() {
        return content_voice;
    }

    public List<String> getContent_img() {
        return content_img;
    }

    public List<String> getPp() {
        return pp;
    }

    public List<String> getDianzan() {
        return dianzan;
    }

    public static class ContentImgBean {
        /**
         * a : Public/Upload/2017-03-10/958957e5374c11bd7211287050b6c0ae1489126390869.jpg
         * b : Public/Upload/2017-03-10/33837625966a363df9d188712aa4e9b41489126390869.jpg
         */

        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
