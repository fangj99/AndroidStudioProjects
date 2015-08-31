package com.example.lance_3770.xml;

/**
 * Created by lance-3770 on 7/1/2015.
 */
public class Person {
    private Integer id;
    private Integer age;
    private String  name;

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Person(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
