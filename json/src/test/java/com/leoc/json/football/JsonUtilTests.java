package com.leoc.json.football;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtilTests {

    @Test
    public void testJsonRead() {
        String json = "{\"name\":\"Borussia Dormund\"," +
                "\"city\":\"Dormund\"," +
                "\"statium\":\"Signal Iduna Park\"," +
                "\"tournaments\":[\"Bundesliga\",\"UCL\"]," +
                "\"players\":null}";
        Team borussiaDormund = new Team();
        borussiaDormund.setCity("Dormund");
        borussiaDormund.setName("Borussia Dormund");
        borussiaDormund.setStatium("Signal Iduna Park");
        List<String> tournaments = new ArrayList<>();
        tournaments.add("Bundesliga");
        tournaments.add("UCL");
        borussiaDormund.setTournaments(tournaments);
        JsonUtil util = new JsonUtil();
        Team parsedTeam = util.asObject(json, Team.class);
        Assert.assertEquals(borussiaDormund, parsedTeam);
    }

    @Test
    public void testJsonWrite() {
        Team borussiaDormund = new Team();
        borussiaDormund.setCity("Dormund");
        borussiaDormund.setName("Borussia Dormund");
        borussiaDormund.setStatium("Signal Iduna Park");
        List<String> tournaments = new ArrayList<>();
        tournaments.add("Bundesliga");
        tournaments.add("UCL");
        borussiaDormund.setTournaments(tournaments);
        JsonUtil util = new JsonUtil();
        String json = "{\"name\":\"Borussia Dormund\"," +
                "\"city\":\"Dormund\"," +
                "\"statium\":\"Signal Iduna Park\"," +
                "\"tournaments\":[\"Bundesliga\",\"UCL\"]," +
                "\"players\":null}";
        String jsonParsed = util.asJson(borussiaDormund);
        System.out.println(/*util.pretty*/(jsonParsed));
        Assert.assertEquals(jsonParsed, json);
    }


    @Test
    public void testObjectToFile() throws IOException {
        Team borussiaDormund = new Team();
        borussiaDormund.setCity("Dormund");
        borussiaDormund.setName("Borussia Dormund");
        borussiaDormund.setStatium("Signal Iduna Park");
        List<String> tournaments = new ArrayList<>();
        tournaments.add("Bundesliga");
        tournaments.add("UCL");
        borussiaDormund.setTournaments(tournaments);
        JsonUtil util = new JsonUtil();
        File teamFile = new File("C:\\Users\\leoca\\AppData\\Local\\Temp\\test11538312587372549655.json");
        List<Team> teams = new ArrayList<>();
        teams.add(borussiaDormund);
        teams.add(borussiaDormund);
        util.toFile(teamFile, teams);
        System.out.println("File created:" + teamFile.getAbsolutePath());
        List<Team> bdParsed = util.asObject(teamFile.toURI().toURL(), List.class);
        Assert.assertEquals(bdParsed, teams);
    }
}
