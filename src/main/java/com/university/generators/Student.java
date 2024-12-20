package com.university.generators;

import com.university.CLI.Entity;

import java.util.HashSet;
import java.util.Set;

public class Student implements Entity {
    private String Name;
    private Integer Course_Count;
    private Set<String> Courses = new HashSet<>();

    public Student(String Name, Integer Course_Count) {
        this.Name = Name;
        this.Course_Count = Course_Count;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    public String getName() { return Name;}

    public Integer Get_Course_Count() { return Course_Count; }

    public void increment_Course_Count() {
        Course_Count++;
    }

    public boolean has_Already_Course(String Course_Name) {
        return Courses.contains(Course_Name);
    }

    public void Add_Course(String Course_Name) {
        Courses.add(Course_Name);
    }

    public Set<String> getCourses() {
        return Courses;
    }
}
