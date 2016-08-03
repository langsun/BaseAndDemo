package com.example.sun.demo.event;

/**
 * Created by sun on 16/7/1.
 */
public class TestEvent extends BaseEvent {
    public String mMessage;
    public TestEvent(Class<?> from, String s) {
        super(from);
        this.mMessage = s;
    }
}
