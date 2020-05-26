package com.leoc.json.football;

import java.util.List;
import java.util.Objects;

public class Team {
    private String name;
    private String city;
    private String statium;
    private List<String> tournaments;
    private List<Player> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatium() {
        return statium;
    }

    public void setStatium(String statium) {
        this.statium = statium;
    }

    public List<String> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<String> tournaments) {
        this.tournaments = tournaments;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) &&
                Objects.equals(city, team.city) &&
                Objects.equals(statium, team.statium) &&
                Objects.equals(tournaments, team.tournaments) &&
                Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, statium, tournaments, players);
    }
}
