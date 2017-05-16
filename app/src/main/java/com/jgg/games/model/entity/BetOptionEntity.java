package com.jgg.games.model.entity;

/**
 * Created by sugl on 2017/3/23 0023.
 */

public class BetOptionEntity extends GraphQlModel {
    private String title;
    private BetEntity bet;
    private String betId;
    private String odds;
    private String totalProfit;
    private String totalBet;
    private String people;
    private TeamEntity team;
    private int BONumber;
    private int minBet; // 最小金额
    private boolean isCorrect;
    private String richest;
    private boolean isCheck;
    private String money;
    private String winMoney;
    private int cost = 1; // 1 竞币 2 积分
    private String matchTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BetEntity getBet() {
        return bet;
    }

    public void setBet(BetEntity bet) {
        this.bet = bet;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(String totalBet) {
        this.totalBet = totalBet;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public int getBONumber() {
        return BONumber;
    }

    public void setBONumber(int BONumber) {
        this.BONumber = BONumber;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getRichest() {
        return richest;
    }

    public void setRichest(String richest) {
        this.richest = richest;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(String winMoney) {
        this.winMoney = winMoney;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
}
