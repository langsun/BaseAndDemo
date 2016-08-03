package com.example.sun.demo.event;

/**
 * Created by luoruiyi on 15-11-22.
 */

/**
 * 自定义事件基类.
 *
 *
 * Created by sun on 16/7/1.
 *
 *
 */
public abstract class BaseEvent {

    /**
     * 发送事件源(方便追踪事件发送者).
     */
    public final Class<?> mFrom;

    /**
     * 事件数据.
     */
    public Object mData;

    /**
     * 事件类型.
     */
    public int mEventType;

    /**
     * 事件消息内容
     */
    public String mMsg;

    public BaseEvent(Class<?> from) {
        mFrom = from;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ", from " + mFrom + ", eventType:" + mEventType + ", msg:" + mMsg
                + ", data:" + mData;
    }
}

