package com.jgg.games.model.entity;

import android.text.TextUtils;

import com.jgg.games.R;
import com.jgg.games.utils.TimeUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class MatchEntity extends GraphQlModel implements Serializable {
    public static int STATE_BEFORE = 0;
    public static int STATE_START = 1;
    public static int STATE_END = 2;
    private TeamEntity leftTeam;
    private TeamEntity rightTeam;
    public GameEntity game;
    public ScheduleEntity schedule;
    private String group;
    private String groupRound;
    private String BORound;
    private boolean syncCosData;
    private long startTime;
    private long endTime;
    private String description;
    private String leftTeamScore;
    private String rightTeamScore;
    private TeamEntity winnerTeam;
    private boolean winnerPromot;
    private int state;
    private String videoURL;
    private String liveURL;
    private String toLatestBetUrl;
    private ArrayList<BetEntity> bet;
    private int rightTeamSupporterNum;
    private int leftTeamSupporterNum;
    private ArrayList<String> leftTeamInformation;
    private ArrayList<String> rightTeamInformation;
    private String dateTime;
    private String title;
    private String gameName;
    private String rank;


    public TeamEntity getLeftTeam() {
        return leftTeam;
    }

    public void setLeftTeam(TeamEntity leftTeam) {
        this.leftTeam = leftTeam;
    }

    public TeamEntity getRightTeam() {
        return rightTeam;
    }

    public void setRightTeam(TeamEntity rightTeam) {
        this.rightTeam = rightTeam;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupRound() {
        return groupRound;
    }

    public void setGroupRound(String groupRound) {
        this.groupRound = groupRound;
    }

    public int getBORound() {
        return getInt(BORound);
    }

    public void setBORound(String BORound) {
        this.BORound = BORound;
    }

    public boolean isSyncCosData() {
        return syncCosData;
    }

    public void setSyncCosData(boolean syncCosData) {
        this.syncCosData = syncCosData;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeftTeamScore() {
        return leftTeamScore;
    }

    public void setLeftTeamScore(String leftTeamScore) {
        this.leftTeamScore = leftTeamScore;
    }

    public String getRightTeamScore() {
        return rightTeamScore;
    }

    public void setRightTeamScore(String rightTeamScore) {
        this.rightTeamScore = rightTeamScore;
    }

    public TeamEntity getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(TeamEntity winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public boolean isWinnerPromot() {
        return winnerPromot;
    }

    public void setWinnerPromot(boolean winnerPromot) {
        this.winnerPromot = winnerPromot;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getLiveURL() {
        return liveURL;
    }

    public void setLiveURL(String liveURL) {
        this.liveURL = liveURL;
    }

    public String getToLatestBetUrl() {
        return toLatestBetUrl;
    }

    public void setToLatestBetUrl(String toLatestBetUrl) {
        this.toLatestBetUrl = toLatestBetUrl;
    }

    public ArrayList<BetEntity> getBet() {
        return bet;
    }

    public void setBet(ArrayList<BetEntity> bet) {
        this.bet = bet;
    }

    public int getRightTeamSupporterNum() {
        return rightTeamSupporterNum;
    }

    public void setRightTeamSupporterNum(int rightTeamSupporterNum) {
        this.rightTeamSupporterNum = rightTeamSupporterNum;
    }

    public int getLeftTeamSupporterNum() {
        return leftTeamSupporterNum;
    }

    public void setLeftTeamSupporterNum(int leftTeamSupporterNum) {
        this.leftTeamSupporterNum = leftTeamSupporterNum;
    }

    public ArrayList<String> getLeftTeamInformation() {
        return leftTeamInformation;
    }

    public void setLeftTeamInformation(ArrayList<String> leftTeamInformation) {
        this.leftTeamInformation = leftTeamInformation;
    }

    public ArrayList<String> getRightTeamInformation() {
        return rightTeamInformation;
    }

    public void setRightTeamInformation(ArrayList<String> rightTeamInformation) {
        this.rightTeamInformation = rightTeamInformation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    public String getRightTeamName() {
        if (getRightTeam() != null) {
            return getRightTeam().getName();
        }
        return "";
    }

    public String getLefttTeamName() {
        if (getLeftTeam() != null) {
            return getLeftTeam().getName();
        }
        return "";
    }

    public String getLefttTeamLogo() {
        if (getLeftTeam() != null) {
            return getLeftTeam().getLogo();
        }
        return "";
    }
    public String getRightTeamLogo() {
        if (getRightTeam() != null) {
            return getRightTeam().getLogo();
        }
        return "";
    }


    public String getMatchScore() {
        return getIntLeftTeamScore() + "   -   " + getIntRightTeamScore();
    }

    public int getIntLeftTeamScore() {
        if (TextUtils.isEmpty(getLeftTeamScore())) {
            return 0;
        }
        return Integer.parseInt(getLeftTeamScore());
    }

    public int getIntRightTeamScore() {
        if (TextUtils.isEmpty(getRightTeamScore())) {
            return 0;
        }
        return Integer.parseInt(getRightTeamScore());
    }

    public String getMatchName() {
        if (getGame() == null) {
            return "";
        }
        return getGame().getName();
    }

    public String getScheduleName() {
        if (getSchedule() == null) {
            return "";
        }
        return getSchedule().getName();
    }

    public int getBetState(){
        int state = R.string.fragment_my_bet_wks;
        int curState = getState();
        if (curState == STATE_BEFORE){
            state = R.string.fragment_my_bet_wks;
        }else if (curState == STATE_START){
            state = R.string.fragment_my_bet_ing;
        }else if (curState == STATE_END){
            state = R.string.fragment_my_bet_end;
        }
        return state;
    }

    public long getCountTime(){

        return (getStartTime() - System.currentTimeMillis()/1000)*1000;
    }

    public String getStringStartTime(String pattern){
        return  TimeUtil.millis2String(getStartTime()*1000, pattern);
    }
}
