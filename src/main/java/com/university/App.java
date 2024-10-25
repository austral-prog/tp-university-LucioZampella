package com.university;

import java.io.*;
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

    public Set<String> getCourses() {
        return Courses;
    }
}

class Evaluation {
    private String Subject_Name;
    private String Evaluation_Name;
    private Map<String, Double> studentGrades;
    private String exerciseName;
    private String evaluationType;

    public Evaluation(String subjectName, String evaluationName, String exerciseName, String evaluationType) {
        this.Subject_Name = subjectName;
        this.Evaluation_Name = evaluationName;
        this.exerciseName = exerciseName;
        this.studentGrades = new HashMap<>();
        this.evaluationType = evaluationType;
    }

    public String Get_Subject_Name() {
        return Subject_Name;
    }

    public String Get_Evaluation_Name() {
        return Evaluation_Name;
    }

    public void Add_Grade(String exerciseName, double grade) {
        studentGrades.put(exerciseName, grade);
    }

    public Map<String, Double> Get_Grades() {
        return studentGrades;
    }

    public String Get_ExerciseName() {
        return exerciseName;
    }

    public String Get_Evaluation_Type() {
        return evaluationType;
    }

    public double calculateFinalGrade() {
        double sum = 0.0;
        for (Double grade : studentGrades.values()) {
            sum += grade;
        }
        return sum / studentGrades.size();
    }
}

class StudentManager {
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

    public void addEvaluation(String subjectName, String evaluationName, String studentName, String exerciseName, double grade, String evaluationType) {
        String key = subjectName + "-" + evaluationName + "-" + studentName + "-" + evaluationType;
        Evaluation evaluationInstance = evaluations.getOrDefault(key, new Evaluation(subjectName, evaluationName, exerciseName, evaluationType));
        evaluations.putIfAbsent(key, evaluationInstance);
        evaluationInstance.Add_Grade(exerciseName, grade);
    }

    public TreeMap<String, Student> getStudentMap() {
        return studentMap;
    }

    public Map<String, Evaluation> getEvaluations() {
        return evaluations;
    }
}

class FileHandler {
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
            bw.write("Subject_Name,Evaluation_Name,Student_Name,Final_Grade");
            bw.newLine();
            for (Map.Entry<String, Evaluation> entry : evaluationMap.entrySet()) {
                Evaluation evaluation = entry.getValue();
                double finalGrade = evaluation.calculateFinalGrade();
                bw.write(evaluation.Get_Subject_Name() + "," + evaluation.Get_Evaluation_Name() + "," + entry.getKey().split("-")[2] + "," + String.format("%.1f", finalGrade));
                bw.newLine();
            }
        }
    }
}

public class App {
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
                String evaluationType = entry[2];
                String evaluationName = entry[3];
                String exerciseName = entry[4];
                double grade = Double.parseDouble(entry[5]);
                studentManager.addEvaluation(subjectName, evaluationName, studentName, exerciseName, grade, evaluationType);
            }
            fileHandler.writeCSV(Output_CSV, studentManager.getStudentMap());
            fileHandler.writeEvaluationCSV(Output_Evaluation_CSV, studentManager.getEvaluations());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
