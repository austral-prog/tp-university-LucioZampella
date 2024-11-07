package com.university.CLI.repositories;

import com.university.CLI.CRUDRepository;
import com.university.generators.Criteria;
import com.university.CLI.exception.InvalidIDException;
import java.util.ArrayList;

public class CriteriaCRUDRepository implements CRUDRepository<Criteria> {
    ArrayList<Criteria> criteriaRepository = new ArrayList<>();

    @Override
    public void create(Criteria entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Criteria entity cannot be null");
        }
        criteriaRepository.add(entity);
    }

    @Override
    public Criteria read(int id) {
        Criteria criteriaR = null;
        for (Criteria criteria : criteriaRepository) {
            if (criteria.getId() == id) {
                criteriaR = criteria;
                break;
            }
        }
        if (criteriaR == null) {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Criteria with ID " + id + " not found");
        }
        return criteriaR;
    }

    @Override
    public void update(int id, Criteria entity) {
        Criteria criteriaU = this.read(id);
        if (criteriaU != null) {
            int indexCriteriaU = criteriaRepository.indexOf(criteriaU);
            entity.setId(criteriaU.getId());
            criteriaRepository.set(indexCriteriaU, entity);
        } else {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Cannot update: Criteria with ID " + id + " not found");
        }
    }

    @Override
    public void delete(int id) {
        Criteria criteriaD = this.read(id);
        if (criteriaD != null) {
            criteriaRepository.remove(criteriaD);
            System.out.println("You deleted:");
            System.out.println("Criteria: " + criteriaD.Get_Criteria_Type());
        } else {
            System.out.println("Invalid ID");
            throw new InvalidIDException("Cannot delete: Criteria with ID " + id + " not found");
        }
    }

    @Override
    public String getIdentifier() {
        return "Criteria";
    }

    @Override
    public Class<Criteria> getEntityClass() {
        return Criteria.class;
    }
}
