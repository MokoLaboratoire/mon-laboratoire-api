package com.moko.monlaboratoireapi.models;

public class User {
    private Long id;
    private String name;
    private String email;
    private String date_of_birth;
    private Integer age;

    public User(Long id, String name, String email, String date_of_birth, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.age = age;
    }

    public User(String name, String email, String date_of_birth, Integer age) {
        this.name = name;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", age=" + age +
                '}';
    }
}


