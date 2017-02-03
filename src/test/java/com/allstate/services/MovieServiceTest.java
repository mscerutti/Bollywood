package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private MovieService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception{
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertEquals(2,after.getId());
        assertEquals(0, after.getVersion());
        assertEquals("The Matrix", after.getTitle());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
    }

    @Test
    public void shouldFindById() throws Exception{
        Movie after = this.service.findById(1);
        assertEquals(1, after.getId());
        assertEquals("The Avengers", after.getTitle());
    }

    @Test
    public void shouldNotFindMovieByBadID() throws Exception {
        Movie movie = this.service.findById(6);
        assertNull(movie);
    }

    @Test
    public void shouldFindAllMovies() throws Exception{
        ArrayList<Movie> movies = (ArrayList<Movie>) this.service.findAll();
        assertEquals(1,movies.size());
    }

    @Test
    public void shouldReturnMovieGivenTitle() throws Exception {
        Movie movie = this.service.findByTitle("The Avengers");
        assertNotNull(movie);
        assertEquals(1,movie.getId());
        assertEquals("PG",movie.getRating());
    }

    @Test
    public void shouldNotFindMovieByBadTitle() throws Exception {
        Movie movie = this.service.findByTitle("The Matrix");
        assertNull(movie);
    }

    @Test
    public void shouldDeleteMovieById() throws Exception {
        this.service.deleteById(1);
        Movie movie = this.service.findById(1);
        assertNull(movie);
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void shouldNotDeleteMovieByBadId() throws Exception {
        this.service.deleteById(6);
    }

    @Test
    public void shouldUpdateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Avengers II");
        Movie after = this.service.update(1, before);
        assertEquals(1, after.getVersion());
        assertEquals("The Avengers II", after.getTitle());
        assertNull(after.getRating());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotUpdateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        before.setTitle(null);
        Movie after = this.service.update(1, before);
    }

}