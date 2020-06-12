package com.leoc.javafxjson.domain;

import java.util.Map;

public class InventoryItem {
    private Integer id;
    private Map<String,String> item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, String> getItem() {
        return item;
    }

    public void setItem(Map<String, String> item) {
        this.item = item;
    }
}
