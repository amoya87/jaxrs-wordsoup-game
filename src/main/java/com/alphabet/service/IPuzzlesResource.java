package com.alphabet.service;

import javax.ws.rs.core.Response;

import com.alphabet.model.Solve;
import com.alphabet.model.Soup;

public interface IPuzzlesResource {

	/*
	 * Returna un json que indica el id autogenerado para la sopa que se acaba de
	 * crear.
	 * 
	 * @param w - Ancho de la sopa de letras, valor opcional por defecto debe ser 15
	 * 
	 * @param h - Largo de la sopa de letras, valor opcional pode defecto debe ser
	 * 15
	 * 
	 * @param ltr - Habilitar o deshabilitar palabras que van de izquierda a
	 * derecha, valor opcional por defecto debe ser true
	 * 
	 * @param rtl - Habilitar o deshabilitar palabras que van de derecha a
	 * izquierda, valor opcional por defecto debe ser false
	 * 
	 * @param ttb - Habilitar o deshabilitar palabras que van desde arriba hacia
	 * abajo, valor opcional por defecto debe ser true
	 * 
	 * @param btt - Habilitar o deshabilitar palabras que van desde abajo hacia
	 * arriba, valor opcional por defecto debe ser false
	 * 
	 * @param d - Habilitar o deshabilitar palabras diagonales, valor por opcional
	 * por defecto debe ser false
	 * 
	 * @return id autogenerado
	 * 
	 * @throws InputException Si los parametros no cumplen con las premisas
	 */
	public Response newAlphabetSoup(Soup s);

	
	public Response listWordsPuzzle(String id);

	public Response viewPuzzle(String id);

	public Response solvePuzzle(Solve s, String id);

}
