package com.example.MovieApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MovieApp.entities.Movie;

public interface MovieDao extends JpaRepository<Movie, Integer> {

}
