package com.loginwebsite.websiteproject.controller;

import com.loginwebsite.websiteproject.manager.MovieTableManager;
import com.loginwebsite.websiteproject.model.MovieBio;
import com.loginwebsite.websiteproject.util.MoviesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    public NavigationController(){

    }

    @GetMapping("/homePage")
    public String homePage(Model theModel) {

        MoviesUtil.setMainMoviesInfo(theModel);
        return "home-page";
    }
}
