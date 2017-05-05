package com.jgg.games.event;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public final class EventBusManager {
    private static EventBusManager instance = null;

    private EventBusManager() {
    }

    public synchronized static EventBusManager getInstance() {
        if (instance == null) {
            instance = new EventBusManager();
        }
        return instance;
    }


    // 所有更新用户信息的事件
    public static final class NotifyUser {
    }

    // 文字输入 修改昵称等
    public static final class NotifyInputText {
        public String content;
    }
}
