package com.leoc.javafxjson.domain;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<String> names = new ArrayList<>();

    public List<String> getNames() {
        return names;
    }

    public void addName(String name) {
        names.add(name);

    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
