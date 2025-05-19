package com.loginwebsite.websiteproject.controller;

import com.loginwebsite.websiteproject.manager.MovieTableManager;
import com.loginwebsite.websiteproject.model.MovieBio;
import com.loginwebsite.websiteproject.model.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Controller
public class MovieBioController {
    private Properties props;
    private MysqlDataSource dataSource;
    public MovieBioController() {
        DataSourceController dataSourceController = new DataSourceController();
        this.props = dataSourceController.getProperties();
        this.dataSource = dataSourceController.getDataSource(this.props);
    }

    @RequestMapping(value = "/imageSelection", method = RequestMethod.GET)
    public String imageSelection(@ModelAttribute MovieBio movie, Model model){

        User user = new User();

        model.addAttribute("user", user);

        MovieTableManager movieQueryMgr = new MovieTableManager();
        MovieBio movieInfo = movieQueryMgr.getMovieByQuery(("SELECT movie_name, rated, description, studio, director, run_time, " +
                "poster, trailer, trending, newest FROM movies.movie WHERE id ='%s'").formatted(movie.getId()));
        model.addAttribute("movie", movieInfo);
        return "movie-bio";
    }
}
