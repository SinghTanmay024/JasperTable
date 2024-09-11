package com.example.JasperTable.Model;


import lombok.Data;

@Data
public class Student
{
    private Long roll;
    private String sname;
    private String address;
    private String gender;
    private Integer age;

    public Student(Long roll, String sname, String address, String gender, Integer age) {
        this.roll = roll;
        this.sname = sname;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }

    public Long getRoll() {
        return roll;
    }

    public void setRoll(Long roll) {
        this.roll = roll;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
