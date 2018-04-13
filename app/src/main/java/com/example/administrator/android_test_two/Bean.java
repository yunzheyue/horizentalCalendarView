package com.example.administrator.android_test_two;

import java.util.List;

/**
 * autour : lbing
 * date : 2018/4/4 0004 09:34
 * className :
 * version : 1.0
 * description :
 */


public class Bean {
    private int result;
    private String ceshi;

    public String  getCeshi() {
        return ceshi;
    }

    public void setCeshi(String ceshi) {
        this.ceshi = ceshi;
    }

    private UrlBean url;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "Bean{" +
                "result=" + result +
                ", url=" + url +
                ", data=" + data +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UrlBean getUrl() {
        return url;
    }

    public void setUrl(UrlBean url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class UrlBean {
        @Override
        public String toString() {
            return "UrlBean{" +
                    "gcjz='" + gcjz + '\'' +
                    ", xfpg='" + xfpg + '\'' +
                    ", tsjz='" + tsjz + '\'' +
                    '}';
        }

        /**
         * gcjz : http://gcjz.bjxfj.gov.cn/login_toLogin
         * xfpg : http://www.kxxf.net.cn/sample/explain/xfpg_demo.html
         * tsjz : http://www.kxxf.net.cn/sample/explain/tsjz_demo.html
         */

        private String gcjz;
        private String xfpg;
        private String tsjz;

        public String getGcjz() {
            return gcjz;
        }

        public void setGcjz(String gcjz) {
            this.gcjz = gcjz;
        }

        public String getXfpg() {
            return xfpg;
        }

        public void setXfpg(String xfpg) {
            this.xfpg = xfpg;
        }

        public String getTsjz() {
            return tsjz;
        }

        public void setTsjz(String tsjz) {
            this.tsjz = tsjz;
        }
    }

    public static class DataBean {

        /**
         * newid : 12844
         * title : “掌上119”一般单位版功能介绍
         * createtime : 1506048543
         * pic : http://pic.kxxf.net.cn/20170922_134446937294.jpg
         * introduction :
         * listorder : 7
         * is_video : 1
         * new_content :
         */

        private String newid;
        private String title;
        private String createtime;
        private String pic;
        private String introduction;
        private String listorder;
        private String is_video;
        private String new_content;

        public String getNewid() {
            return newid;
        }

        public void setNewid(String newid) {
            this.newid = newid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getIs_video() {
            return is_video;
        }

        public void setIs_video(String is_video) {
            this.is_video = is_video;
        }

        public String getNew_content() {
            return new_content;
        }

        public void setNew_content(String new_content) {
            this.new_content = new_content;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "newid='" + newid + '\'' +
                    ", title='" + title + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", pic='" + pic + '\'' +
                    ", introduction='" + introduction + '\'' +
                    ", listorder='" + listorder + '\'' +
                    ", is_video='" + is_video + '\'' +
                    ", new_content='" + new_content + '\'' +
                    '}';
        }
    }
}
