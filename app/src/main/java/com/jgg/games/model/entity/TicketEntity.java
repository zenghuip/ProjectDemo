package com.jgg.games.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class TicketEntity extends GraphQlModel {
    private List<TicketEntity> myTickets;

    private String condition; // 使用金额下限
    private String description; // 文字说明
    private String name; // 券名称
    private String icon;
    private String deduction; // 抵扣金
    private String uid;
    private long startTime; // 生效时间
    private long endTime; // 失效时间
    private int state; //  1 未使用 2 已使用

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketEntity> getMyTickets() {
        return myTickets;
    }

    public void setMyTickets(List<TicketEntity> myTickets) {
        this.myTickets = myTickets;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }
}
