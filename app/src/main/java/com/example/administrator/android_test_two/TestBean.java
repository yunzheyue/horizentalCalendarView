package com.example.administrator.android_test_two;

import java.util.ArrayList;

/**
 * autour : lbing
 * date : 2018/4/9 0009 09:52
 * className :
 * version : 1.0
 * description :
 */


public class TestBean {

    private String name;
    private int age;
    private boolean agen;
    private ArrayList list;

    public String getName() {
        return name == null ? "" : name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAgen() {
        return agen;
    }

    public ArrayList getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", agen=" + agen +
                ", list=" + list +
                '}';
    }
}
