package com.jgg.games.model.entity;

import com.jgg.games.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class BetEntity extends GraphQlModel {
    public static int STATE_BEFORE = 0;      //竞猜未开始
    public static int STATE_START = -1;      //竞猜中
    public static int STATE_COUNTING = 2;      //停止下注
    public static int STATE_COUNTED = 3;      //结算完成
    public static int STATE_CANCEL = 4;      //竞猜取消

    public static int LOG_UNCLEAR = 0;      //不清楚
    public static int LOG_YES = 1;      //有竞猜
    public static int LOG_NO = 2;      //没有竞猜

    private String name;
    private String startTime;
    private String endTime;
    private String payTime;
    private String totalIn;
    private String totalOut;
    private String earning;
    private String frequency;
    private String benefit;
    private String maxBet;
    private String type;
    private String desc;
    private int minBet;
    private MatchEntity match;
    private String shortName;
    private ArrayList<BetOptionEntity> betOptions;
    private String hasBetLog;
    private String totalComment;
    private boolean isOpen;
    private boolean isHot;
    private boolean isCheck;
    private BetEntity multiBetOrder;
    private BetEntity createBetAttempt; // 创建守护
    private String attemptId; // 守护id
    private TicketEntity ticketUsed;
    private int BONumber;

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    public ArrayList<BetOptionEntity> getBetOptions() {
        return betOptions;
    }

    public void setBetOptions(ArrayList<BetOptionEntity> betOptions) {
        this.betOptions = betOptions;
    }

    public BetEntity getMultiBetOrder() {
        return multiBetOrder;
    }

    public void setMultiBetOrder(BetEntity multiBetOrder) {
        this.multiBetOrder = multiBetOrder;
    }

    public BetEntity getCreateBetAttempt() {
        return createBetAttempt;
    }

    public void setCreateBetAttempt(BetEntity createBetAttempt) {
        this.createBetAttempt = createBetAttempt;
    }

    public TicketEntity getTicketUsed() {
        return ticketUsed;
    }

    public void setTicketUsed(TicketEntity ticketUsed) {
        this.ticketUsed = ticketUsed;
    }

    public String getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(String totalOut) {
        this.totalOut = totalOut;
    }

    public static int getStateBefore() {
        return STATE_BEFORE;
    }

    public static void setStateBefore(int stateBefore) {
        STATE_BEFORE = stateBefore;
    }

    public static int getStateStart() {
        return STATE_START;
    }

    public static void setStateStart(int stateStart) {
        STATE_START = stateStart;
    }

    public static int getStateCounting() {
        return STATE_COUNTING;
    }

    public static void setStateCounting(int stateCounting) {
        STATE_COUNTING = stateCounting;
    }

    public static int getStateCounted() {
        return STATE_COUNTED;
    }

    public static void setStateCounted(int stateCounted) {
        STATE_COUNTED = stateCounted;
    }

    public static int getStateCancel() {
        return STATE_CANCEL;
    }

    public static void setStateCancel(int stateCancel) {
        STATE_CANCEL = stateCancel;
    }

    public static int getLogUnclear() {
        return LOG_UNCLEAR;
    }

    public static void setLogUnclear(int logUnclear) {
        LOG_UNCLEAR = logUnclear;
    }

    public static int getLogYes() {
        return LOG_YES;
    }

    public static void setLogYes(int logYes) {
        LOG_YES = logYes;
    }

    public static int getLogNo() {
        return LOG_NO;
    }

    public static void setLogNo(int logNo) {
        LOG_NO = logNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(String totalIn) {
        this.totalIn = totalIn;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(String maxBet) {
        this.maxBet = maxBet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getHasBetLog() {
        return hasBetLog;
    }

    public void setHasBetLog(String hasBetLog) {
        this.hasBetLog = hasBetLog;
    }

    public String getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(String totalComment) {
        this.totalComment = totalComment;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getBONumber() {
        return BONumber;
    }

    public void setBONumber(int BONumber) {
        this.BONumber = BONumber;
    }

    public int getBetState(){
        int state = R.string.fragment_my_bet_wks;
        int curState = getStatus();
        if (curState == STATE_BEFORE){
            state = R.string.fragment_my_bet_wks;
        }else if (curState == STATE_START){
            state = R.string.fragment_my_bet_ing;
        }else if (curState == STATE_COUNTING){
            state = R.string.fragment_my_bet_wait;
        }else if (curState == STATE_COUNTED){
            state = R.string.fragment_my_bet_end;
        }else if (curState == STATE_CANCEL){
            state = R.string.fragment_my_bet_cancel;
        }
        return state;
    }


    public static List<BetEntity> getBoList(List<BetEntity> datas, int BoNum) {
        List<BetEntity> list = new ArrayList<>();
        for (BetEntity bet : datas) {
            if (bet != null && bet.getBONumber() == BoNum) {
                list.add(bet);
            }
        }
        return list;
    }

}