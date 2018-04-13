package com.example.administrator.android_test_two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    private void getData() {
        Log.e("TAG", "测试===" + "http://kxxf.net.cn/index.php/firenews/GetIspostionNews?" + getInterfaceDesKey() + "?addr=北京");
        Map<String, String> params = new HashMap<>();
        params.put("addr", "北京");
        OkHttpUtils.post()
                .url("http://kxxf.net.cn/index.php/firenews/GetIspostionNews?" + getInterfaceDesKey())
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);

                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Bean result = null;
                        Type cvbType = new TypeToken<Bean>() {
                        }.getType();
                        result = gson.fromJson(response, cvbType);
                        Log.e("TAG", "result.getCeshi()---" + result.getCeshi());
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }
                });

    }


    private static String getInterfaceDesKey() {
        try {
            long time = new Date().getTime() / 1000;
            String keyTime = Md5Util.getMD5Str(time + "7Fq3Uqmi");
            return "time=" + time + "&encry_time="
                    + URLEncoder.encode(keyTime, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
