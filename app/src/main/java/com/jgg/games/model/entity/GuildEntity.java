package com.jgg.games.model.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dingdongdong on 2017/3/27.
 * 公会
 */
@Table("consortia")
public class GuildEntity extends GraphQlModel implements Serializable {
    public static final int REFUSE = 2;
    public static final int ACCEPT = 1;

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    private long cid;

    @Ignore
    private String name;
    @Ignore
    private String logo;
    @Ignore
    private String description;
    @Ignore
    private int size;
    @Ignore
    private UserEntity leader;
    @Ignore
    private int createTime;
    /**
     * id : 5799d1c4387308cf641bfd75
     * name : 'WANG   JUN   H
     */
    @Ignore
    private List<UserEntity> members;
    @Ignore
    private List<UserEntity> viceLeader;
    @Ignore
    private List<AnnouncementEntity> announcement;
    @Ignore
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public UserEntity getLeader() {
        return leader;
    }

    public void setLeader(UserEntity leader) {
        this.leader = leader;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public List<UserEntity> getViceLeader() {
        return viceLeader;
    }

    public void setViceLeader(List<UserEntity> viceLeader) {
        this.viceLeader = viceLeader;
    }

    public List<AnnouncementEntity> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(List<AnnouncementEntity> announcement) {
        this.announcement = announcement;
    }
}
