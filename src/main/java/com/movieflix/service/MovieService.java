package com.movieflix.service;

import com.movieflix.entity.Movie;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }
}
