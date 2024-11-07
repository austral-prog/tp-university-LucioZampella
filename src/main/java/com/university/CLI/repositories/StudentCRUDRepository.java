package com.university.CLI.repositories;

import com.university.CLI.CRUDRepository;
import com.university.generators.Student;
import com.university.CLI.exception.InvalidIDException;
import java.util.ArrayList;

public class StudentCRUDRepository implements CRUDRepository<Student> {
    ArrayList<Student> studentRepository = new ArrayList<>();

    @Override
    public void create(Student entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Student entity cannot be null");
        }
        studentRepository.add(entity);
    }

    @Override
    public Student read(int id) {
        Student studentR = null;
        for (Student student : studentRepository) {
            if (student.getId() == id) {
                studentR = student;
                break;
            }
        }
        if (studentR == null) {
            throw new InvalidIDException("Student with ID " + id + " not found");
        }
        return studentR;
    }

    @Override
    public void update(int id, Student entity) {
        Student studentU = this.read(id);
        if (studentU != null) {
            int indexStudentU = studentRepository.indexOf(studentU);
            entity.setId(studentU.getId());
            studentRepository.set(indexStudentU, entity);
        } else {
            throw new InvalidIDException("Cannot update: Student with ID " + id + " not found");
        }
    }

    @Override
    public void delete(int id) {
        Student studentD = this.read(id);
        if (studentD != null) {
            studentRepository.remove(studentD);
            System.out.println("You deleted:");
            System.out.println("Student: " + studentD.getName());
        } else {
            throw new InvalidIDException("Cannot delete: Student with ID " + id + " not found");
        }
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
