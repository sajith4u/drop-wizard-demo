package dev.innova.wizard.service;

import com.codahale.metrics.annotation.Timed;
import dev.innova.wizard.beans.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentManagementService {

    StudentDatabaseService studentDatabaseService;

    public StudentManagementService(StudentDatabaseService studentDatabaseService) {
        this.studentDatabaseService = studentDatabaseService;
    }

    @GET
    @Timed
    @Path("/get/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getPerson(@PathParam("id") int id) {
        Student student = studentDatabaseService.findNameById(id);
        return Response.ok(student).build();
    }

    @DELETE
    @Timed
    @Path("/remove/{first_name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response removePerson(@PathParam("first_name") String first_name) {
        String response = studentDatabaseService.deleteByFirstName(first_name);
        return Response.ok(response).build();
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getPersons() {
        return studentDatabaseService.getPersons();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(Student student) {
        studentDatabaseService.save(student);
        return "Students Count : " + studentDatabaseService.getCounts();
    }
}
