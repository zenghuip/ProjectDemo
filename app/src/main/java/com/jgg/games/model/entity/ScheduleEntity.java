package com.jgg.games.model.entity;

import java.util.ArrayList;

/**
 * Created by sugl on 2017/3/23 0023.
 */

public class ScheduleEntity extends GraphQlModel {
    public static final int TYPE_FINAL = 3;
    public static final int TYPE_HALF = 2;
    public static final int TYPE_FOUR = 1;
    public static final int TYPE_TEAM = 0;
    private GameEntity game;
    private String name;
    private int type;
    private String startTime;
    private String endTime;
    private ArrayList<MatchEntity> match;

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<MatchEntity> getMatch() {
        return match;
    }

    public void setMatch(ArrayList<MatchEntity> match) {
        this.match = match;
    }
}
