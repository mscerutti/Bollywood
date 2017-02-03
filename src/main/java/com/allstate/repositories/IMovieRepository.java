package com.allstate.repositories;

import com.allstate.entities.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by localadmin on 2/3/17.
 */
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
}
