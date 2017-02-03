package com.allstate.controllers;

import com.allstate.entities.Movie;
import com.allstate.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by localadmin on 2/3/17.
 */
@RestController
@RequestMapping(value="/movies")
public class MovieController {
    private MovieService service;

    @Autowired
    public void setService(MovieService service){
        this.service = service;
    }

    @RequestMapping(value= {"","/"}, method = RequestMethod.POST)
    public Movie create(@RequestBody Movie movie){
        return this.service.create(movie);
    }
}
