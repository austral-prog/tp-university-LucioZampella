package com.university.CLI.runners;

import com.university.CLI.CLI;
import com.university.CLI.CRUDRepository;
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
        System.out.println("Welcome to CLI Creater\n");
        System.out.println("What do you want to create:\n");
        System.out.println("1. Create Student");
        System.out.println("2. Create Evaluation");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();

        switch (choice) {

            case 1: {
                System.out.println("Enter student name:");
                String name = scanner.next();
                String lastname = scanner.nextLine();
                studentRepository.create(new Student(name + " " + lastname, 1));
                break;
            }

            case 2: {
                System.out.println("Enter subject name:");
                String subjectName = scanner.nextLine();
                System.out.println("Enter evaluation name:");
                String evaluationName = scanner.next();
                String evaluationName2 = scanner.nextLine();
                System.out.println("Enter exercise name:");
                String exerciseName = scanner.nextLine();
                System.out.println("\nChoose evaluation type:\n");
                System.out.println("1. Final Evaluation\n");
                System.out.println("2. Oral Exam\n");
                System.out.println("3. Written Exam\n");
                System.out.println("4. Practical Work\n");
                choice = scanner.nextInt();
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
                System.out.println("Enter criteria subject:");
                String criteriaSubject = scanner.nextLine();
                System.out.println("Enter criteria evaluation:");
                String criteriaEvaluation = scanner.nextLine();
                System.out.println("Enter criteria type:");
                String criteriaType = scanner.nextLine();
                System.out.println("Enter criteria value:");
                String criteriaValue = scanner.nextLine();

                criteriaRepository.create(new Criteria(criteriaSubject, criteriaEvaluation,
                        criteriaType, criteriaValue ));
            }

            default:
                System.out.println("Invalid input");
        }
    }

    private void readCLI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CLI reader\n");
        System.out.println("What do you want to read:\n");
        System.out.println("1. Student");
        System.out.println("2. Evaluation");
        System.out.println("3. Criteria");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("Enter id:\n");
                int id = scanner.nextInt();
                Student studentRead = studentRepository.read(id);
                if (studentRead != null){
                    System.out.println("Student: " + studentRead.getName());
                } else {
                    System.out.println("Invalid ID");
                }
                break;
            }

            case 2: {
                System.out.println("Enter id:\n");
                int id = scanner.nextInt();
                Evaluation evaluationRead = evaluationRepository.read(id);
                if (evaluationRead != null) {
                    System.out.println("Subject Name: " + evaluationRead.getSubjectName());
                    System.out.println("Evaluation Name: " + evaluationRead.getEvaluationName());
                    System.out.println("Exercise Name: " + evaluationRead.Get_ExerciseName());
                    System.out.println("Evaluation Type: " + evaluationRead.Get_Evaluation_Type());
                } else {
                    System.out.println("Invalid ID");
                }
            }

            case 3: {
                System.out.println("Enter id:\n");
                int id = scanner.nextInt();
                Criteria criteriaRead = criteriaRepository.read(id);
                if (criteriaRead != null) {
                    System.out.println("Criteria Subject: " + criteriaRead.Get_Criteria_Subject());
                    System.out.println("Criteria Evaluation: " + criteriaRead.Get_Criteria_Evaluation());
                    System.out.println("Criteria Type: " + criteriaRead.Get_Criteria_Type());
                    System.out.println("Criteria Value: " + criteriaRead.Get_Criteria_Value());
                } else {
                    System.out.println("Invalid ID");
                }
            }

            default:
                System.out.println("Invalid input, try again");
            }
        }

        public void updateCLI() {

        Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to CLI updater\n");
        System.out.println("What do you want to update:\n");
        System.out.println("1. Update Student");
        System.out.println("2. Update Evaluation");
        System.out.println("3. Update Criteria");
        int choice = scanner.nextInt();

        switch (choice) {

            case 1: {
                System.out.println("Enter student ID:\n");
                int id = scanner.nextInt();
                System.out.println("Enter new student name:\n");
                String studentName = scanner.nextLine();
                String studentLastname = scanner.nextLine();

                studentRepository.update(id, new Student(studentName + " " + studentLastname, 1));
                break;
            }
            case 2: {
                System.out.println("Enter evaluation ID:\n");
                int id = scanner.nextInt();

                System.out.println("Enter new subject name:\n");
                String subjectName = scanner.nextLine();
                System.out.println("Enter new evaluation name\n");
                String evaluationName = scanner.nextLine();
                String evaluationName2 = scanner.nextLine();
                System.out.println("Enter new exercise name:\n");
                String exerciseName = scanner.nextLine();
                System.out.println("Choose new evaluation type:\n");
                System.out.println("1. Final Evaluation");
                System.out.println("2. Oral exam");
                System.out.println("3. Written Exam");
                System.out.println("4. Practical Work");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        evaluationRepository.update(id, new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Final Evaluation"));
                        break;
                    case 2:
                        evaluationRepository.update(id, new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Oral Exam"));
                        break;
                    case 3:
                        evaluationRepository.update(id, new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Written Exam"));
                        break;
                    case 4:
                        evaluationRepository.update(id, new Evaluation(subjectName, evaluationName + " "
                                + evaluationName2, exerciseName, "Practical Work"));
                        break;
                    default:
                        System.out.println("Invalid input");
                    }
                }
            case 3: {
                System.out.println("Enter criteria ID:\n");
                int id = scanner.nextInt();
                Scanner scnaner = new Scanner(System.in);
                System.out.println("Enter new criteria subject:");
                String criteriaSubject = scanner.nextLine();
                System.out.println("Enter new criteria evaluation:");
                String criteriaEvaluation = scanner.nextLine();
                System.out.println("Enter new criteria type:");
                String criteriaType = scanner.nextLine();
                System.out.println("Enter new criteria value:");
                String criteriaValue = scanner.nextLine();

                criteriaRepository.update(id, new Criteria(criteriaSubject, criteriaEvaluation, criteriaType, criteriaValue));
            }
                default:
                    System.out.println("Invalid input");
            }
        }

        private void deleteCLI() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to CLI deleter\n");
            System.out.println("What do you want to delete:\n");
            System.out.println("1. Student");
            System.out.println("2. Evaluation");
            System.out.println("3. Criteria");
            int choice = scanner.nextInt();
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            switch (choice) {
                case 1: {
                    studentRepository.delete(id);
                }
                case 2: {
                    evaluationRepository.delete(id);
                }
                case 3: {
                    criteriaRepository.delete(id);
                }
                default:
                    System.out.println("Invalid input");
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