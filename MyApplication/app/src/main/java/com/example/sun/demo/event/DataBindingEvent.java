package com.example.sun.demo.event;

import com.example.sun.demo.databinding.Person;

/**
 * Created by sun on 17/8/9.
 */

public class DataBindingEvent extends BaseEvent {
    public Person person;
    public DataBindingEvent(Class<?> from,Person p) {
        super(from);
        this.person = p;
    }
}
