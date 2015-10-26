package dev.innova.wizard.service;

import dev.innova.wizard.beans.Student;
import dev.innova.wizard.db.StudentsDAO;

import javax.inject.Inject;
import java.util.List;

public class StudentDatabaseService {
    private final StudentsDAO studentsDAO;

    @Inject
    public StudentDatabaseService(StudentsDAO studentsDAO) {
        this.studentsDAO = studentsDAO;
    }

    public void save(Student student) {
        studentsDAO.insert(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    public Student findNameById(int id) {
        return studentsDAO.findNameById(id);
    }

    public List<Student> getPersons() {
        return studentsDAO.getAllStudents();
    }

    public String deleteByFirstName(String firstName) {
        studentsDAO.deleteByName(firstName);
        return String.valueOf(studentsDAO.getAllStudentsCount());
    }
}
