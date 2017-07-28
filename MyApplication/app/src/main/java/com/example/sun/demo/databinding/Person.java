package com.example.sun.demo.databinding;

/**
 * Created by sun on 17/7/21.
 */

public class Person {
    private String name;
    private String gender;
    private int age;
    private int light;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public int getLight() {
        return light;
    }

    public Person setLight(int light) {
        this.light = light;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", light=" + light +
                '}';
    }
}
