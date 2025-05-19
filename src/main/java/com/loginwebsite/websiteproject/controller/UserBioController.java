package com.loginwebsite.websiteproject.controller;


import com.loginwebsite.websiteproject.manager.AccountTableManager;
import com.loginwebsite.websiteproject.manager.MovieTableManager;
import com.loginwebsite.websiteproject.model.MovieBio;
import com.loginwebsite.websiteproject.model.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Controller
public class UserBioController {

    @GetMapping("/processUserBio")
    public String processUserBio(@ModelAttribute User theUser, Model model) throws SQLException {
        AccountTableManager accountQueryMgr = new AccountTableManager();

        theUser = accountQueryMgr.getAccountProfileByQuery(("SELECT id, firstName, lastName, email, username, password FROM movies.account WHERE id ='%s'").formatted(theUser.getId()));
        model.addAttribute("user", theUser);

        return "user-update";
    }

    @PostMapping("/processUser")
    public String processUser(@ModelAttribute User user,
                              Model model,
                              @RequestParam(required = false) String updateButton,
                              @RequestParam(required = false) String deleteButton) throws SQLException {

        String resultPage = "home-page";

        if (updateButton != null) {
            AccountTableManager accountQueryMgr = new AccountTableManager();
            accountQueryMgr.updateAccount(user);

            MovieTableManager movieQueryMgr = new MovieTableManager();

            MovieBio[] allTrendingMovies = movieQueryMgr.getMovieListByQuery("SELECT * FROM movies.movie WHERE trending=1");
            model.addAttribute("trendingMovies", allTrendingMovies);

            MovieBio[] allNewMovies = movieQueryMgr.getMovieListByQuery("SELECT * FROM movies.movie WHERE newest=1");
            model.addAttribute("newestMovies", allNewMovies);

        } else if (deleteButton != null) {
            AccountTableManager accountQueryMgr = new AccountTableManager();
            accountQueryMgr.deleteUser(user);
            model.addAttribute("deletedUser", model);

            resultPage = "user-login";
        }
        return resultPage;
    }
}