package com.example.app2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MyCalendarView my_calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_calendarView = findViewById(R.id.my_calendarView);


        ArrayList<DataEntity> datas = new ArrayList();
        for (int i = 0; i < 12; i++) {
            DataEntity dataEntity = new DataEntity();
            dataEntity.setContent(i + 1 + "");
            int i1 = new Random().nextInt(10);
            int i2 = new Random().nextInt(10);
            dataEntity.setPreCount(i1 + "");
            dataEntity.setSufCount(i2 + "");
            datas.add(dataEntity);
        }

        my_calendarView.setCalendarData(datas,10);
    }
}
