package dev.innova.wizard.db;

import dev.innova.wizard.beans.Student;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface StudentsDAO {
    @SqlUpdate("insert into students (first_name,last_name,email) values (:first_name, :last_name,:email)")
    void insert(@Bind("first_name") String firstName, @Bind("last_name") String lastName, @Bind("email") String email);

    @SqlQuery("select name from students where id = :id")
    Student findNameById(@Bind("id") int id);

    @SqlQuery("select name from students")
    List<Student> getAllStudents();

    @SqlUpdate("delete from students where first_name = :first_name")
    void deleteByName(@Bind("first_name") String name);

    @SqlQuery("select count(*) from students")
    int getAllStudentsCount();
}
