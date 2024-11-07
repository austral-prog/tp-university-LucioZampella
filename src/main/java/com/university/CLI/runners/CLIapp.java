package com.university.CLI.runners;

import com.university.CLI.CLI;
import com.university.CLI.repositories.CriteriaCRUDRepository;
import com.university.CLI.repositories.EvaluationCRUDRepository;
import com.university.CLI.repositories.StudentCRUDRepository;
import com.university.CLI.CRUDRepository;
import com.university.CLI.Entity;
import com.university.generators.Student;
import com.university.generators.Evaluation;
import com.university.generators.Criteria;

import java.util.List;

public class CLIapp {
    public static void main(String[] args) {
        CLI cli = new CLIImpl();
        CRUDRepository<Student> studentRepository = new StudentCRUDRepository();
        CRUDRepository<Criteria> subjectRepository = new CriteriaCRUDRepository();
        CRUDRepository<Evaluation> evaluationRepository = new EvaluationCRUDRepository();
        List<CRUDRepository<?>> repositoryList = List.of(studentRepository, subjectRepository, evaluationRepository);
        CRUDRepository<Entity>[] repositories = repositoryList.toArray(new CRUDRepository[0]);
        cli.runCLI(repositories);
    }
}
