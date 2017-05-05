package com.jgg.games.model.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

@Table("user")
public class UserEntity extends GraphQlModel{
    public static final String UID = "uid";

    // 每个对象需要有一个主键
    @PrimaryKey(AssignType.BY_MYSELF)
    @Column("uid")
    private long uid;
    private String name;
    private String sid;
    private String openid;
    private String token;
    private String avatar;
    private String birthday;
    private String realName;
    private String latestLoginTime;
    private String signature;
    private String telephone;
    private int sex; // 1 男 2 女

    private String earningOfCredit; // 积分收益
    private String earningOfGold; // 竞币收益
    private String betTimes; // 竞猜场数
    private double rateOfBingo; // 竞猜胜率
    private int credit;                   //魂币积分
    private int gold; //魂币余额

    @Mapping(Relation.OneToOne)
    private GuildEntity consortia; // 公会

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }
    public long getLongBirthday() {
        return getLong(birthday);
    }

    public long getBirth(){
        return getLong(birthday);
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(String latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEarningOfCredit() {
        return earningOfCredit;
    }

    public void setEarningOfCredit(String earningOfCredit) {
        this.earningOfCredit = earningOfCredit;
    }

    public String getEarningOfGold() {
        return earningOfGold;
    }

    public void setEarningOfGold(String earningOfGold) {
        this.earningOfGold = earningOfGold;
    }

    public String getBetTimes() {
        return betTimes;
    }

    public void setBetTimes(String betTimes) {
        this.betTimes = betTimes;
    }

    public double getRateOfBingo() {
        return rateOfBingo;
    }

    public void setRateOfBingo(double rateOfBingo) {
        this.rateOfBingo = rateOfBingo;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public GuildEntity getConsortia() {
        return consortia;
    }

    public void setConsortia(GuildEntity consortia) {
        this.consortia = consortia;
    }

}
