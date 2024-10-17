package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// CLASE COURSE
class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}

// CLASE ESTUDIANTE
class Student {
    private String name;
    private String email;
    private ArrayList<Course> courseList;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
        this.courseList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course) {
        this.courseList.add(course);
    }
}

// MI CLASE PRINCIPAL APP
public class App {
    public static void main(String[] args) {
        String inputPath = "src/main/resources/input.csv";
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        HashMap<String, Student> studentMap = new HashMap<>();

        // LEE EL ARCHIVO CSV DE ENTRADA
        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputPath))) {
            String inputLine;

            while ((inputLine = inputReader.readLine()) != null) {
                ArrayList<String> lineList = new ArrayList<>();
                String currentWord = "";

                for (char i : inputLine.toCharArray()) {
                    if (i != ',') {
                        currentWord += i;
                    } else {
                        lineList.add(currentWord.strip());
                        currentWord = "";
                    }
                }
                lineList.add(currentWord.strip()); // AGREGA EL VALOR DE LA ULTIMA COMA
                dataList.add(lineList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SACAR ENCABEZADO
        dataList.remove(0);

        // CREAR MAPAS DE ESTUDIANTES
        for (ArrayList<String> studentData : dataList) {
            String studentName = studentData.get(0);
            String courseName = studentData.get(1);
            String studentEmail = studentData.get(2);

            // SI EL ESTUDIANTE NO ESTÁ, AGREGARLO
            studentMap.putIfAbsent(studentEmail, new Student(studentName, studentEmail));

            // AGREGAR CURSO DEL ESTUDIANTE
            Course course = new Course(courseName);
            studentMap.get(studentEmail).addCourse(course);
        }

        // CSV DE SALIDA
        try {
            File solutionFile = new File("src/main/resources/solution.csv");
            FileWriter solutionWriter = new FileWriter(solutionFile);

            solutionWriter.write("Student_Name,Course_Count\n");
            for (Student student : studentMap.values()) {
                int courseCount = student.getCourseList().size();
                solutionWriter.write(student.getName() + "," + courseCount + "\n");
            }
            solutionWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
