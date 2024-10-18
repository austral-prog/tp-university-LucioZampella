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

class Evaluation { // Manage the evaluation data
    private String Subject_Name;
    private String Evaluation_Name;
    private Map<String, Double> studentGrades;

    public Evaluation(String subjectName, String evaluationName) {
        this.Subject_Name = subjectName;
        this.Evaluation_Name = evaluationName;
        this.studentGrades = new HashMap<>();
    }

    public String Get_Subject_Name() {
        return Subject_Name;
    }

    public String Get_Evaluation_Name() {
        return Evaluation_Name;
    }

    public void Add_Grade(String studentName, double grade) {
        studentGrades.put(studentName, grade);
    }

    public Map<String, Double> Get_Grades() {
        return studentGrades;
    }
}

class StudentManager { // Add the students to the TreeMap
    private TreeMap<String, Student> studentMap = new TreeMap<>();
    private TreeMap<String, Evaluation> evaluations = new TreeMap<>();

    public void addStudent(String studentName, String courseName) {
        Student student;
        if (studentMap.containsKey(studentName)) {
            student = studentMap.get(studentName);
            if (!student.has_Already_Course(courseName)) {
                student.Add_Course(courseName);
                student.increment_Course_Count();
            }
        } else {
            student = new Student(studentName, 1);
            studentMap.put(studentName, student);
            student.Add_Course(courseName);
        }
    }

    public void addEvaluation(String subjectName, String evaluationName, String studentName, double grade) {
        String key = subjectName + "-" + evaluationName + "-" + studentName;
        evaluations.putIfAbsent(key, new Evaluation(subjectName, evaluationName));
        evaluations.get(key).Add_Grade(studentName, grade);
    }

    public TreeMap<String, Student> getStudentMap() {
        return studentMap;
    }

    public Map<String, Evaluation> getEvaluations() {
        return evaluations;
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
            bw.write("Student_Name,Course_Count");
            bw.newLine();
            for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                String studentName = entry.getKey();
                int courseCount = entry.getValue().Get_Course_Count();
                bw.write(studentName + "," + courseCount);
                bw.newLine();
            }
        }
    }

    public void writeEvaluationCSV(String filePath, Map<String, Evaluation> evaluationMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Subject_Name,Evaluation_Name,Student_Name,Grade");
            bw.newLine();
            for (Evaluation evaluation : evaluationMap.values()) {
                Map<String, Double> studentGrades = evaluation.Get_Grades();
                for (Map.Entry<String, Double> entry : studentGrades.entrySet()) {
                    String studentName = entry.getKey();
                    Double grade = entry.getValue();
                    bw.write(evaluation.Get_Subject_Name() + "," + evaluation.Get_Evaluation_Name() + "," + studentName + "," + grade);
                    bw.newLine();
                }
            }
        }
    }
}

public class App { // Execute the app
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Input_2CSV = "src/main/resources/input_2.csv";
        String Output_CSV = "src/main/resources/student.csv";
        String Output_Evaluation_CSV = "src/main/resources/evaluation.csv";

        FileHandler fileHandler = new FileHandler();
        StudentManager studentManager = new StudentManager();

        try {
            List<String[]> data = fileHandler.readCSV(Input_CSV);
            List<String[]> data2 = fileHandler.readCSV(Input_2CSV);

            for (String[] entry : data) {
                String studentName = entry[2];
                String courseCount = entry[1];
                studentManager.addStudent(studentName, courseCount);
            }
            for (String[] entry : data2) {
                String studentName = entry[0];
                String subjectName = entry[1];
                String evaluationName = entry[3];
                double grade = Double.parseDouble(entry[5]);
                studentManager.addEvaluation(subjectName, evaluationName, studentName, grade);
            }
            fileHandler.writeCSV(Output_CSV, studentManager.getStudentMap());
            fileHandler.writeEvaluationCSV(Output_Evaluation_CSV, studentManager.getEvaluations());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
