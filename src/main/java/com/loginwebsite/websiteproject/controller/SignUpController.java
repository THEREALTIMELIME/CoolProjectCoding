package com.loginwebsite.websiteproject.controller;

import com.loginwebsite.websiteproject.manager.AccountTableManager;
import com.loginwebsite.websiteproject.manager.MovieTableManager;
import com.loginwebsite.websiteproject.model.MovieBio;
import com.loginwebsite.websiteproject.model.User;
import com.loginwebsite.websiteproject.util.MoviesUtil;
import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Controller
@ControllerAdvice
public class SignUpController {
    private Properties props;
    private MysqlDataSource dataSource;
    private CallableStatement statement;

    public SignUpController() {
        DataSourceController dataSourceController = new DataSourceController();
        this.props = dataSourceController.getProperties();
        this.dataSource = dataSourceController.getDataSource(this.props);
    }

    @GetMapping("/showSignUpPage")
    public String showPage(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-sign-up";
    }

    @PostMapping("/processSignUpPage")
    public String processPage(@Valid @ModelAttribute User theUser, BindingResult result, Model model, HttpSession session) throws SQLException {
        if (result.hasErrors()) {
            User newUser = new User();
            model.addAttribute("user", newUser);
            return "user-sign-up";
        } else {
            AccountTableManager accountQueryMgr = new AccountTableManager();

            User emailCheck = accountQueryMgr.getAccountProfileByQuery("SELECT id, firstName, lastName FROM movies.account where email = '%s'".formatted(theUser.getEmail()));
            User phoneNumberCheck = accountQueryMgr.getAccountProfileByQuery("SELECT id, firstName, lastName FROM movies.account where phoneNumber = '%s'".formatted(theUser.getPhoneNumber()));
            User usernameCheck = accountQueryMgr.getAccountProfileByQuery("SELECT id, firstName, lastName FROM movies.account where username = '%s'".formatted(theUser.getUsername()));
            if (emailCheck != null) {
                System.out.println("User exists with email");
                model.addAttribute("emailErrorMessage", "Email already exists.");
                model.addAttribute("user", theUser);
                return "user-sign-up";
            } else if (phoneNumberCheck != null) {
                System.out.println("User exists with phone number");
                model.addAttribute("phoneNumberErrorMessage", "Phone Number already exists.");
                model.addAttribute("user", theUser);
                return "user-sign-up";
            } else if (usernameCheck != null) {
                System.out.println("User exists with phone number");
                model.addAttribute("usernameErrorMessage", "Username already exists.");
                model.addAttribute("user", theUser);
                return "user-sign-up";
            } else {

                accountQueryMgr.insertAccountInfo(theUser);

                session.setAttribute("user", theUser);


                MoviesUtil.setMainMoviesInfo(model);

                return "home-page";
            }
        }
    }
}