package com.jgg.games.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 * 游戏种类
 */

public class GameTypeEntity extends GraphQlModel {
    private List<GameTypeEntity> gameCategorys;
    private String name;
    private String description; // 描述
    private IconEntity logo;


    public List<GameTypeEntity> getGameCategorys() {
        return gameCategorys;
    }

    public void setGameCategorys(List<GameTypeEntity> gameCategorys) {
        this.gameCategorys = gameCategorys;
    }


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

    public IconEntity getLogo() {
        return logo;
    }

    public void setLogo(IconEntity logo) {
        this.logo = logo;
    }
}
