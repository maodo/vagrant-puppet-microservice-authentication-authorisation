package com.example.resources;

import com.example.core.Person;
import com.example.dao.PersonDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GET
    public List<Person> getAll(){
        return personDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") Integer id){
        return personDAO.findById(id);
    }

    @POST
    public Person add(@Valid Person person) {
        int newId = personDAO.insert(person);

        return person.setId(newId);
    }

    @PUT
    @Path("/{id}")
    public Person update(@PathParam("id") Integer id, @Valid Person person) {
        person = person.setId(id);
        personDAO.update(person);

        return person;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        personDAO.deleteById(id);
    }
}
