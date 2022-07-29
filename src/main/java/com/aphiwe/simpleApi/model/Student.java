package com.aphiwe.simpleApi.model;

import java.util.Objects;

public class Student {

    private int id;
    private String name;
    private double gpa;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }


    public Student( int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId() &&
                Double.compare(student.getGpa(), getGpa()) == 0 &&
                getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getGpa());
    }
}
