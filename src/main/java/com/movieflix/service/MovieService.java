package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public Movie save(Movie movie){
        movie.setCategories(this.findCategory(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long id, Movie updateMovie){
        Optional<Movie> optMovie = movieRepository.findById(id);
        if (optMovie.isPresent()){
            List<Category> categories = this.findCategory(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());

            movie.getCategories().clear();
            movie.setCategories(categories);

            movie.getStreamings().clear();
            movie.setStreamings(streamings);

            movieRepository.save(movie);

            return Optional.of(movie);
        }

        return Optional.empty();
    }

    public List<Category> findCategory(List<Category> categories){
        List<Category> categoryFound = new ArrayList<>();
        for (Category category : categories){
            categoryService.findById(category.getId()).ifPresent(categoryFound::add);
        }

        return categoryFound;
    }

    public List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        for (Streaming streaming : streamings){
            streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);
        }
        return streamingsFound;
    }

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieBycategory(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }
}
