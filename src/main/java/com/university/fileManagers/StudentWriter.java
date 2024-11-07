package com.university.fileManagers;

import com.university.generators.Student;

import java.io.IOException;
import java.util.Map;

public class StudentWriter extends Writer<Student> {

    @Override
    protected String getHeader() {
        return "Student_Name,Course_Count";
    }

    @Override
    protected String formatData(Student student) {
        return student.getName() + "," + student.Get_Course_Count();
    }
}
