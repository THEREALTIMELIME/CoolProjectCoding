package com.loginwebsite.websiteproject.util;

import com.loginwebsite.websiteproject.manager.MovieTableManager;
import com.loginwebsite.websiteproject.model.MovieBio;
import org.springframework.ui.Model;

public class MoviesUtil {

    public static void setMainMoviesInfo(Model theModel){

        MovieTableManager movieQueryMgr = new MovieTableManager();

        MovieBio[] allTrendingMovies = movieQueryMgr.getMovieListByQuery("SELECT * FROM movies.movie WHERE trending=1");
        theModel.addAttribute("trendingMovies", allTrendingMovies);

        MovieBio[] allNewMovies = movieQueryMgr.getMovieListByQuery("SELECT * FROM movies.movie WHERE newest=1");
        theModel.addAttribute("newestMovies", allNewMovies);

    }
}
