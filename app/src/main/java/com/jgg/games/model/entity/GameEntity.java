package com.jgg.games.model.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class GameEntity extends GraphQlModel {
    public static final int STATE_BEFORE = 0;
    public static final int STATE_START = 1;
    public static final int STATE_END = 2;

    public static final int TYPE_NOMARL = 0;
    public static final int TYPE_PRO = 1;
    private String name;
    private String description;
    private String logo;
    private String banner;
    private String regularStartTime;
    private String regularEndTime;
    private String playoffsStartTime;
    private String playoffsEndTime;
    private String state;
    private String newsChannel;
    private String totalComment;
    private String videoChannel;
    private String ruleURL;
    private String CPLGameID;
    private String type; // 0:普通赛事；1:职业赛事
    private String professionalLeague; // 0:CPL；1:WCA；2:TGA
    private String startTime;
    private String endTime;
    private String AgainstPlanURL;
    private ArrayList<TeamEntity> attendedTeams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getRegularStartTime() {
        return regularStartTime;
    }

    public void setRegularStartTime(String regularStartTime) {
        this.regularStartTime = regularStartTime;
    }

    public String getRegularEndTime() {
        return regularEndTime;
    }

    public void setRegularEndTime(String regularEndTime) {
        this.regularEndTime = regularEndTime;
    }

    public String getPlayoffsStartTime() {
        return playoffsStartTime;
    }

    public void setPlayoffsStartTime(String playoffsStartTime) {
        this.playoffsStartTime = playoffsStartTime;
    }

    public String getPlayoffsEndTime() {
        return playoffsEndTime;
    }

    public void setPlayoffsEndTime(String playoffsEndTime) {
        this.playoffsEndTime = playoffsEndTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNewsChannel() {
        return newsChannel;
    }

    public void setNewsChannel(String newsChannel) {
        this.newsChannel = newsChannel;
    }

    public String getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(String totalComment) {
        this.totalComment = totalComment;
    }

    public String getVideoChannel() {
        return videoChannel;
    }

    public void setVideoChannel(String videoChannel) {
        this.videoChannel = videoChannel;
    }

    public String getRuleURL() {
        return ruleURL;
    }

    public void setRuleURL(String ruleURL) {
        this.ruleURL = ruleURL;
    }

    public String getCPLGameID() {
        return CPLGameID;
    }

    public void setCPLGameID(String CPLGameID) {
        this.CPLGameID = CPLGameID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfessionalLeague() {
        return professionalLeague;
    }

    public void setProfessionalLeague(String professionalLeague) {
        this.professionalLeague = professionalLeague;
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

    public String getAgainstPlanURL() {
        return AgainstPlanURL;
    }

    public void setAgainstPlanURL(String againstPlanURL) {
        AgainstPlanURL = againstPlanURL;
    }

    public ArrayList<TeamEntity> getAttendedTeams() {
        return attendedTeams;
    }

    public void setAttendedTeams(ArrayList<TeamEntity> attendedTeams) {
        this.attendedTeams = attendedTeams;
    }
}
