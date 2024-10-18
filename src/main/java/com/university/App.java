package com.university;

import java.io.*;
import java.util.*;

class Student { // Manage the data of students
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

    public Set<String> getCourses() {
        return Courses;
    }
}

class Evaluation {
    private String courseName;
    private Map<String, Integer> studentGrades;

    public Evaluation(String courseName, Map<String, Integer> studentGrades) {
        this.courseName = courseName;
        this.studentGrades = new HashMap<>();
    }

    public String Get_Course_Name() {
        return courseName;
    }

    public Map<String, Integer> Get_Student_Grades() {
        return studentGrades;
    }

    public void Add_Grade(String studentName, Integer grade) {
        studentGrades.put(studentName, grade);
    }
}
class StudentManager { // Add the students to the TreeMap
    private TreeMap<String, Student> studentMap = new TreeMap<>();

    public void addStudent(String studentName, String courseName) {
        Student student;
        if (studentMap.containsKey(studentName)) {
            student = studentMap.get(studentName);
            if (!student.has_Already_Course(courseName)) {
                student.Add_Course(courseName);
                student.increment_Course_Count();
            }
        } else {
            student = new Student(studentName, 0);
            student.increment_Course_Count();
            studentMap.put(studentName, student);
            student.Add_Course(courseName);
        }
    }

    public TreeMap<String, Student> getStudentMap() {
        return studentMap;
    }
}

class FileHandler { // Manage the CSV
    public List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                data.add(line.split(","));
            }
        }
        return data;
    }

    public void writeCSV(String filePath, TreeMap<String, Student> studentMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Student_Name, Course_Count");
            bw.newLine();
            for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                String studentName = entry.getKey();
                Integer courseCount = entry.getValue().Get_Course_Count();
                bw.write(studentName + "," + courseCount);
                bw.newLine();
            }
        }
    }
}

public class App { // Execute the app
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Output_CSV = "src/main/resources/student.csv";

        FileHandler fileHandler = new FileHandler();
        StudentManager studentManager = new StudentManager();

        try {
            // Leer el archivo CSV
            List<String[]> data = fileHandler.readCSV(Input_CSV);

            // Procesar los datos
            for (String[] entry : data) {
                String studentName = entry[2];
                String courseName = entry[1];
                studentManager.addStudent(studentName, courseName);
            }

            // Escribir el resultado en el archivo CSV
            fileHandler.writeCSV(Output_CSV, studentManager.getStudentMap());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
