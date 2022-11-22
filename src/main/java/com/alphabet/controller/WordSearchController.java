package com.alphabet.controller;

import com.alphabet.model.Solve;
import com.alphabet.model.Soup;
import com.alphabet.service.IWordSearchService;
import com.alphabet.service.WordSearchService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Sets the path to base URL + /alphabetSoup
@Path("/alphabetSoup")
public class WordSearchController {

    private IWordSearchService service = new WordSearchService();

    //
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newAlphabetSoup(Soup s) {
        String message = service.addPuzzle(s);
        return Response.status(Response.Status.OK).entity(message).build();
    }

    // Allows to type http://localhost:8080/alphabetSoup/list/1
    // 1 will be treaded as parameter
    @GET
    @Path("list/{puzzle}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listWordsPuzzle(@PathParam("puzzle") String id) {
        List<String> words = service.getPuzzleWords(id);
        return Response.status(Response.Status.OK).entity(words).build();
    }

    // Allows to type http://localhost:8080/alphabetSoup/view/1
    // 1 will be treaded as parameter
    @GET
    @Path("view/{puzzle}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response viewPuzzle(@PathParam("puzzle") String id) {
        String soup = service.getPuzzle(id);
        return Response.status(Response.Status.OK).entity(soup).build();
    }

    @PUT
    @Path("{puzzle}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response solvePuzzle(Solve s, @PathParam("puzzle") String id) {
        Boolean ok = service.solvePuzzle(s, id);
        String answer = (ok) ? "Correcta" : "Incorrecta";
        return Response.status(Response.Status.OK).entity(answer).build();
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }
}
