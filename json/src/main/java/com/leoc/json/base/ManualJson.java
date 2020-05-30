package com.leoc.json.base;

import com.leoc.json.football.JsonUtil;
import com.leoc.json.football.Team;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManualJson {


    public static void main(String[] args) throws MalformedURLException {
        System.out.println(new Random().nextInt(17));
        Team borussiaDormund = new Team();
        borussiaDormund.setCity("Dormund");
        borussiaDormund.setName("Borussia Dormund");
        borussiaDormund.setStatium("Signal Iduna Park");
        List<String> tournaments = new ArrayList<>();
        tournaments.add("Bundesliga");
        tournaments.add("UCL");
        borussiaDormund.setTournaments(tournaments);

        System.out.println(new JsonUtil().asJson(borussiaDormund));

        Catalog catalog = new Catalog();
        catalog.setName("Clavos");
        catalog.setProperties(new ArrayList<>());
        catalog.getProperties().add("Tamaño");
        catalog.getProperties().add("Uso");
        catalog.getProperties().add("Cantidad");
        File file = new File("c:\\source-code\\temp.json");
        new JsonUtil().toFile(file, catalog);
        Catalog catalog1 = new JsonUtil().asObject(file.toURI().toURL(), Catalog.class);

        System.out.println((new JsonUtil().asJson(catalog1)));


    }
}

class Catalog {
    private String name;
    private List<String> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}
