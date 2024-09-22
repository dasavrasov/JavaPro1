package ru.stepup;

public class User {
    private String name;
    private int age;
    private String casta;

    public User(String name, int age, String casta) {
        this.name = name;
        this.age = age;
        this.casta = casta;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCasta() {
        return casta;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", casta='" + casta + '\'' +
                '}';
    }
}