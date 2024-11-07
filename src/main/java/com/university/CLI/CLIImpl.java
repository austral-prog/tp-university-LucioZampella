package com.university.CLI;

import com.university.CRUDRepository;
import com.university.generators.Criteria;
import com.university.generators.Evaluation;
import com.university.generators.Student;

import java.util.Scanner;

public class CLIImpl implements CLI {
    CRUDRepository<Student> studentRepository = null;
    CRUDRepository<Evaluation> evaluationRepository = null;
    CRUDRepository<Criteria> criteriaRepository = null;

    private void createRepositories(CRUDRepository<?>[] crudInterfaces) {

        for (CRUDRepository<?> crudRepository : crudInterfaces) {
            if (crudRepository.getIdentifier().equalsIgnoreCase("Student")) {
                studentRepository = (CRUDRepository<Student>) crudRepository;
            }
        }
        if (studentRepository == null){
            throw new IllegalArgumentException("Repository array is invalid");
        }

        for (CRUDRepository<?> crudRepository : crudInterfaces){
            if (crudRepository.getIdentifier().equalsIgnoreCase("Evaluation")){
                evaluationRepository = (CRUDRepository<Evaluation>) crudRepository;
            }
        }

        for (CRUDRepository<?> crudRepository : crudInterfaces){
            if (crudRepository.getIdentifier().equalsIgnoreCase("Criteria")){
                criteriaRepository = (CRUDRepository<Criteria>) crudRepository;
            }
        }

        if (criteriaRepository == null){
            throw new IllegalArgumentException("Repository array is invalid");
        }
    }

    private void createCLI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to CLI Creater\n");
        System.out.println("\nWhat do you want to create:\n");
        System.out.println("1. Create Student");
        System.out.println("2. Create Evaluation");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();

        switch (choice) {

            case 1: {
                System.out.print("Enter student name:");
                String name = scanner.next();
                String lastname = scanner.nextLine();
                studentRepository.create(new Student(name + " " + lastname, 1));
                break;
            }
            case 2: {
                System.out.print("Enter subject name:");
                String subjectName = scanner.nextLine();
                System.out.print("Enter evaluation name:");
                String evaluationName = scanner.next();
                String evaluationName2 = scanner.nextLine();
                System.out.print("Enter exercise name:");
                String exerciseName = scanner.nextLine();
                System.out.println("\nChoose evaluation type:\n");
                System.out.println("1. Final Evaluation\n");
                System.out.println("2. Oral Exam\n");
                System.out.println("3. Written Exam\n");
                System.out.println("4. Practical Work\n");

                switch (choice) {
                    case 1:
                        evaluationRepository.create(new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Final Evaluation"));
                        break;
                    case 2:
                        evaluationRepository.create(new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Oral Exam"));
                        break;
                    case 3:
                        evaluationRepository.create(new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Written Exam"));
                        break;
                    case 4:
                        evaluationRepository.create(new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Practical Work"));
                        break;
                    default:
                        System.out.println("Invalid input");
                }
                break;
            }
            case 3: {
                System.out.print("Enter criteria subject:");
                String criteriaSubject = scanner.nextLine();
                System.out.print("Enter criteria evaluation:");
                String criteriaEvaluation = scanner.nextLine();
                System.out.print("Enter criteria type:");
                String criteriaType = scanner.nextLine();
                System.out.print("Enter criteria value:");
                String criteriaValue = scanner.nextLine();

                criteriaRepository.create(new Criteria(criteriaSubject, criteriaEvaluation,
                        criteriaType, criteriaValue ));
            }
            default:
                System.out.println("Invalid input");
        }
    }
    private void readCLI() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("\nWelcome to CLI reader\n");
        System.out.println("\nWhat do you want to read:\n")
        System.out.println("1. Student");
        System.out.println("2. Evaluation");
        System.out.println("3. Criteria");
        int choice = myScanner.nextInt();

        switch (choice) {
            case 1: {
                System.out.print("Enter id:\n");
                int id = myScanner.nextInt();
                Student studentRead = studentRepository.read(id);
                if (studentRead == null){

                }
            }
        }
        @Override
        public void runCLI (CRUDRepository < ? >[]crudInterfaces){

            createRepositories(crudInterfaces);

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n Hello, welcome to CLI \n");
            System.out.println("\n What would you like to do?\n");
            System.out.println(" 1. Create");
            System.out.println(" 2. Read");
            System.out.println(" 3. Update");
            System.out.println(" 4. Delete");
            System.out.println(" 5. Exit + \n");

        }
    }
}