package com.jgg.games.model.manager;

import android.content.Context;

import com.jgg.games.http.HttpRequest;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.BetsEntity;
import com.jgg.games.model.entity.GameTypeEntity;
import com.jgg.games.model.entity.IndexBannerEntity;
import com.jgg.games.model.entity.MatchListEntity;

/**
 *  圈子
 */
public class BetManager {

    private static BetManager instance = new BetManager();

    private BetManager(){

    }

    public static BetManager getInstance() {
        return instance;
    }
    /**
     * 获取游戏类型
     */
    public void getGameType(CommonCallback callback) {
        new HttpRequest().postUrl(getGameType(0,20),callback,GameTypeEntity.class);
    }

    /**
     * 获取首页公告
     *
     * @param responseCallback
     */
    public void getAnnounce(CommonCallback responseCallback) {
        new HttpRequest().postUrl(getAnnounce(),responseCallback,AnnouncementEntity.class);
    }


    /**
     * 获取广告位
     *
     * @param responseCallback position  0 首页 1 商城轮播图
     */
    public void getBanner(int position, CommonCallback responseCallback) {
        new HttpRequest().postUrl(getBanner(position),responseCallback,IndexBannerEntity.class);
    }

    /**
     * 首页赛事列表
     * gameId 游戏类型id
     * @param offset
     * @param limit
     * @param responseCallback
     */
    public void getMatchList(String gameId,int offset, int limit, CommonCallback responseCallback) {
        new HttpRequest().postUrl(getMatchList(gameId,offset,limit),responseCallback,MatchListEntity.class);
    }

    /**
     * 竞猜列表
     *
     * @param matchId
     * @param responseCallback
     */
    public void getBetList(String matchId, CommonCallback responseCallback) {
        new HttpRequest().postUrl(getBetList(matchId), responseCallback, BetsEntity.class);
    }

    private String getBetList(String matchId) {

        return "{\n" +
                "  bets(match: \"" + matchId + "\") {\n" +
                "    id,name,shortName,maxBet,BONumber,minBet,frequency,desc,status,startTime,endTime,type,totalIn\n" +
                "    betOptions {\n" +
                "      id,people,odds,isCorrect,title\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    private String getGameType(int offset,int limit){
        return "{\n" +
                "  gameCategorys(offset: "+offset+", limit: "+limit+") {\n" +
                "    id,name,description,logo {url}\n" +
                "  }\n" +
                "}";
    }

    private String getAnnounce() {
        return "{\n" +
                "  announcements {content,order,time}\n" +
                "}";
    }

    private String getBanner(int position) {

        return "{appMenuItems(position:" + position + "){\n" +
                "  name,image {url},url}}";
    }

    private String getMatchList(String gameId,int offset, int limit) {
        return "{\n" +
                "   matches(gameCategory: \""+gameId+"\", offset: "+offset+", limit: "+limit+")" +
                "{" + "id " +
                "    description ,leftTeamScore,rightTeamScore,BORound,startTime ,state,videoURL,liveURL,title,leftTeamSupporterNum,rightTeamSupporterNum\n" +
                "    leftTeam {id,name,logo}\n" +
                "    rightTeam {id,name ,logo},winnerTeam {id}\n" +
                "    game {id,name,description}\n" +
                "    schedule {name}\n" +
                "  }\n" +
                "}\n";
    }
}
