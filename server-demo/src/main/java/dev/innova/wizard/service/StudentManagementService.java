package dev.innova.wizard.service;

import com.codahale.metrics.annotation.Timed;
import dev.innova.wizard.beans.Student;
import dev.innova.wizard.db.StudentsDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student")
public class StudentManagementService {
    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getPerson(@PathParam("id") int id) {
        return StudentsDB.getById(id);
    }

    @GET
    @Timed
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    public String removePerson() {
        StudentsDB.remove();
        return "Last person remove. Total count: " + StudentsDB.getCount();
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getPersons() {
        return StudentsDB.getAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(Student student) {
        return StudentsDB.save(student);
    }
}
