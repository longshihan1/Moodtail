package com.longshihan.moodtail.model.bean;

/**
 * @author longshihan
 * @time 2017/3/24 17:28
 * @des
 */

public class TestModel {
    String name;
    String value;

    public TestModel(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
