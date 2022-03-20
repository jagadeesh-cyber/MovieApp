package com.example.MovieApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieApp.dto.MovieDTO;
import com.example.MovieApp.entities.Movie;
import com.example.MovieApp.service.MovieService;

@RestController
@RequestMapping(value = "/movie_app/v1")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {

		Movie newMovie = modelMapper.map(movieDTO, Movie.class);
		Movie savedMovie = movieService.acceptMovieDetails(newMovie);
		MovieDTO savedMovieDTO = modelMapper.map(savedMovie, MovieDTO.class);
		return new ResponseEntity<MovieDTO>(savedMovieDTO, HttpStatus.CREATED);

	}

	@GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MovieDTO>> getAllMovies() {
		List<Movie> movieList = movieService.getAllMovies();
		List<MovieDTO> movieDTOList = new ArrayList<>();
		movieList.stream().forEach(movie -> {
			movieDTOList.add(modelMapper.map(movie, MovieDTO.class));
		});
		return new ResponseEntity<List<MovieDTO>>(movieDTOList, HttpStatus.OK);

	}

	@GetMapping(value = "/movies/{id}")
	public ResponseEntity<MovieDTO> getMovieBasedOnId(@PathVariable(name = "id") int id) {
		Movie movieResponse = movieService.getMovieDetails(id);
		MovieDTO responseMovieDTO = modelMapper.map(movieResponse, MovieDTO.class);
		return new ResponseEntity<MovieDTO>(responseMovieDTO, HttpStatus.OK);

	}

	@PutMapping(value = "/movies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovieDTO> updateMovieDetails(@PathVariable(name = "id") int id,
			@RequestBody MovieDTO movieDTO) {

		Movie newMovie = modelMapper.map(movieDTO, Movie.class);
		Movie updatedMovie = movieService.updateMovieDetails(id, newMovie);
		MovieDTO updatedMovieDTO = modelMapper.map(updatedMovie, MovieDTO.class);
		return new ResponseEntity<MovieDTO>(updatedMovieDTO, HttpStatus.OK);

	}
}
