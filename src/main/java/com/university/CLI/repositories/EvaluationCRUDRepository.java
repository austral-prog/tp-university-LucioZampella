package com.university.CLI.repositories;

import com.university.CLI.CRUDRepository;
import com.university.generators.Evaluation;
import com.university.CLI.exception.InvalidIDException;
import java.util.ArrayList;

public class EvaluationCRUDRepository implements CRUDRepository<Evaluation> {
    ArrayList<Evaluation> evaluationRepository = new ArrayList<>();

    @Override
    public void create(Evaluation entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Evaluation entity cannot be null");
        }
        evaluationRepository.add(entity);
    }

    @Override
    public Evaluation read(int id) {
        Evaluation evaluationR = null;
        for (Evaluation evaluation : evaluationRepository) {
            if (evaluation.getId() == id) {
                evaluationR = evaluation;
                break;
            }
        }
        if (evaluationR == null) {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Evaluation with ID " + id + " not found");
        }
        return evaluationR;
    }

    @Override
    public void update(int id, Evaluation entity) {
        Evaluation evaluationU = this.read(id);
        if (evaluationU != null) {
            int indexEvaluationU = evaluationRepository.indexOf(evaluationU);
            entity.setId(evaluationU.getId());
            evaluationRepository.set(indexEvaluationU, entity);
        } else {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Cannot update: Evaluation with ID " + id + " not found");
        }
    }

    @Override
    public void delete(int id) {
        Evaluation evaluationD = this.read(id);
        if (evaluationD != null) {
            evaluationRepository.remove(evaluationD);
            System.out.println("You deleted:");
            System.out.println("Evaluation: " + evaluationD.getEvaluationName());
        } else {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Cannot delete: Evaluation with ID " + id + " not found");
        }
    }

    @Override
    public String getIdentifier() {
        return "Evaluation";
    }

    @Override
    public Class<Evaluation> getEntityClass() {
        return Evaluation.class;
    }
}
