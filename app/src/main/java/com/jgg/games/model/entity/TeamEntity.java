package com.jgg.games.model.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class TeamEntity extends GraphQlModel {
    private String name;
    private String logo;
    private String createAt;
    private String teamid;
    int teamType;// 1常规，2临时
    private ArrayList<MatchEntity> history;
    private ArrayList<MatchEntity> glory;
    private String avgKill;
    private String winRate;
    private String uid;
    private String attendCount;
    private String winCount;
    private String slogen;
    private String description;
    private boolean isPerfessional;
    private String section;
    private String KDA;
    private String avgMoney;
    private String address;
    private String latitude;
    private String longitude;
    private String duelTime;  //约战次数
    private String duelWinTime; // 约战胜场数   胜率 胜场数/约战次数
    private int attendingType;

    public ArrayList<MatchEntity> getGlory() {
        return glory;
    }

    public void setGlory(ArrayList<MatchEntity> glory) {
        this.glory = glory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public ArrayList<MatchEntity> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<MatchEntity> history) {
        this.history = history;
    }

    public String getAvgKill() {
        return avgKill;
    }

    public void setAvgKill(String avgKill) {
        this.avgKill = avgKill;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(String attendCount) {
        this.attendCount = attendCount;
    }

    public String getWinCount() {
        return winCount;
    }

    public void setWinCount(String winCount) {
        this.winCount = winCount;
    }

    public String getSlogen() {
        return slogen;
    }

    public void setSlogen(String slogen) {
        this.slogen = slogen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPerfessional() {
        return isPerfessional;
    }

    public void setPerfessional(boolean perfessional) {
        isPerfessional = perfessional;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getKDA() {
        return KDA;
    }

    public void setKDA(String KDA) {
        this.KDA = KDA;
    }

    public String getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(String avgMoney) {
        this.avgMoney = avgMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDuelTime() {
        return duelTime;
    }

    public void setDuelTime(String duelTime) {
        this.duelTime = duelTime;
    }

    public String getDuelWinTime() {
        return duelWinTime;
    }

    public void setDuelWinTime(String duelWinTime) {
        this.duelWinTime = duelWinTime;
    }

    public int getAttendingType() {
        return attendingType;
    }

    public void setAttendingType(int attendingType) {
        this.attendingType = attendingType;
    }
}
