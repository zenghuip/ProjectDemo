package com.jgg.games.model.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

public class UnprofessionalGamesBean implements Serializable{

    private int state;
    private String id;
    private String title;
    private boolean isTop;
    private boolean isJoin;
    private Object rootGame;
    private int attendType;
    private String advertisingImage;
    private String time;
    private String lname;
    private String rname;
    private String content;
    private String score;

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public Object getRootGame() {
        return rootGame;
    }

    public void setRootGame(Object rootGame) {
        this.rootGame = rootGame;
    }

    public int getAttendType() {
        return attendType;
    }

    public void setAttendType(int attendType) {
        this.attendType = attendType;
    }

    public String getAdvertisingImage() {
        return advertisingImage;
    }

    public void setAdvertisingImage(String advertisingImage) {
        this.advertisingImage = advertisingImage;
    }
}
