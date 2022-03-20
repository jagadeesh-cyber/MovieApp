package com.example.MovieApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.MovieApp.entities.Movie;

public interface MovieService {
	public Movie acceptMovieDetails(Movie movie);

	public List<Movie> acceptMultipleMovieDetails(List<Movie> movies);

	public Movie getMovieDetails(int id);

	public Movie updateMovieDetails(int id, Movie movie);

	public boolean deleteMovie(int id);

	public List<Movie> getAllMovies();

	public Page<Movie> getPaginatedMovieDetails(Pageable pageable);
}
