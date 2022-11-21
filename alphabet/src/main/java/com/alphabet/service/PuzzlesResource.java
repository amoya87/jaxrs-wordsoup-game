package com.alphabet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alphabet.IWordSearch;
import com.alphabet.WordSearch;
import com.alphabet.model.Solve;
import com.alphabet.model.Soup;

//Sets the path to base URL + /alphabetSoup
@Path("/alphabetSoup")
public class PuzzlesResource implements IPuzzlesResource {

	private static Map<String, IWordSearch> puzzles = new HashMap<>();

	//
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newAlphabetSoup(Soup s) {
		IWordSearch ws = new WordSearch(s.getW(), s.getH(), s.isLtr(), s.isRtl(), s.isTtb(), s.isBtt(), s.isD(), false,
				10);
		String id = UUID.randomUUID().toString();
		puzzles.put(id, ws);

		return Response.status(Response.Status.OK).entity(id).build();
	}

	// Allows to type http://localhost:8080/alphabetSoup/list/1
	// 1 will be treaded as parameter
	@Override
	@GET
	@Path("list/{puzzle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWordsPuzzle(@PathParam("puzzle") String id) {
		List<String> words = puzzles.get(id).getWords();
		return Response.status(Response.Status.OK).entity(words).build();
	}

	// Allows to type http://localhost:8080/alphabetSoup/view/1
	// 1 will be treaded as parameter
	@Override
	@GET
	@Path("view/{puzzle}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response viewPuzzle(@PathParam("puzzle") String id) {
		String soup = puzzles.get(id).toString();
		return Response.status(Response.Status.OK).entity(soup).build();
	}

	@Override
	@PUT
	@Path("{puzzle}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response solvePuzzle(Solve s, @PathParam("puzzle") String id) {
		Boolean ok = puzzles.get(id).setWords(s.getSr(), s.getSc(), s.getEr(), s.getEc());
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
