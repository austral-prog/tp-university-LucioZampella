package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Student {
    private String Name;
    private Integer Course_Count;
    private Set<String> Courses = new HashSet<>();

    public Student(String Name, Integer Course_Count) {
        this.Name = Name;
        this.Course_Count = Course_Count;
        this.Courses = new HashSet<>();
    }

    public String Get_Name() {
        return Name;
    }

    public Integer Get_Course_Count() {
        return Course_Count;
    }

    public void increment_Course_Count() {
        Course_Count++;
    }

    public boolean has_Already_Course(String Course_Name) {
        return Courses.contains(Course_Name);
    }

    public void Add_Course(String Course_Name) {
        Courses.add(Course_Name);
    }
}

public class App {
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Output_CSV = "src/main/resources/student.csv";
        TreeMap<String, Student> studentMap = new TreeMap<>();
        Set<String> courseSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Input_CSV))) {
            String line;

            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                String studentName = data[2];
                String courseName = data[1];

                Student student;
                    if (studentMap.containsKey(studentName)) {
                        student = studentMap.get(studentName);
                        if (!student.has_Already_Course(courseName)) {
                            student.Add_Course(courseName);
                            student.increment_Course_Count();
                        }
                    }
                    else {
                        student = new Student(studentName, 0);
                        student.increment_Course_Count();
                        studentMap.put(studentName, student);
                        student.Add_Course(courseName);
                    }
                }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Output_CSV))) {
                bw.write("Student_Name, Course_Count");
                bw.newLine();

                for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                    String studentName = entry.getKey();
                    Integer courseCount = entry.getValue().Get_Course_Count();
                    bw.write(studentName + "," + courseCount);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
