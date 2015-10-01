/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import db.PersonFacade;
import entity.Person;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.JSONConverter;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("person")
public class PersonResource {
    
    private PersonFacade pf;
    private final Gson gson;

    @Context
    private UriInfo context;

    public PersonResource() {
        this.gson = new Gson();
        pf = new PersonFacade();
    }

    @GET
    @Produces("application/json")
    public Response get() {
        List<Person> persons = pf.getPersons();
        
        String json = JSONConverter.getJSONFromPerson(persons);
        
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}")
    public Response get(@PathParam("id") int id){
        Person p = pf.getPerson(id);
        
        String json = JSONConverter.getJSONFromPerson(p);
        
        return Response.ok(JSONConverter.getJSONFromPerson(p), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes("application/json")    
    @Produces("application/json")
    public Response putJson(String jsonIn) {
        Person p = JSONConverter.getPersonFromJson(jsonIn);
        
        p = pf.editPerson(p);
        
        return Response.ok(JSONConverter.getJSONFromPerson(p), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(String jsonIn){
        Person p = JSONConverter.getPersonFromJson(jsonIn);
        
        pf.addPerson(p);
        
        return Response.ok(JSONConverter.getJSONFromPerson(p), MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        Person p = pf.deletePerson(id);
        
        return Response.ok(JSONConverter.getJSONFromPerson(p), MediaType.APPLICATION_JSON).build();
    }

}
