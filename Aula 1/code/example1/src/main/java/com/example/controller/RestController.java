package com.example.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.model.Person;

@Path("/person")
public class RestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> hello() {
        return List.of(new Person("Lucas", "34"), new Person("Laura", "<1"));
    }
}
