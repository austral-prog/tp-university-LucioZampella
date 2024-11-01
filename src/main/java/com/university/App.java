package com.university;


import com.university.loggers.CriteriaLogger;
import com.university.loggers.EvaluationLogger;
import com.university.loggers.StudentLogger;

public class App {
    public static void main(String[] args) {
        StudentLogger.main(args);
        EvaluationLogger.main(args);
        CriteriaLogger.main(args);
    }
}