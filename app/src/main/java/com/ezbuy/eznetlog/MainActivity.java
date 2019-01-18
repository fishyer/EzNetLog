package com.ezbuy.eznetlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("test");
                testChunk();
            }
        });
    }

    private void log(String msg) {
        Log.d("EzNetLog",msg);
    }

    private void testChunk() {
        HttpModal httpModal = new HttpModal();
        httpModal.header = getHeader();
        httpModal.methodName = "getUserInfo";
        httpModal.url = "http:baidu.com";
        httpModal.args = getArgs();
        httpModal.response = "这是返回值";
        ChuckHelper.saveModal(this, httpModal);
    }

    private Map<String, Object> getArgs() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "fisher");
        map.put("password", "111111");
        return map;
    }

    private Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("device", "123456");
        map.put("os", "android");
        return map;
    }
}
