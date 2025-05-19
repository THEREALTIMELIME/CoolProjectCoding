package com.loginwebsite.websiteproject.controller;
import com.loginwebsite.websiteproject.manager.AccountTableManager;

import com.loginwebsite.websiteproject.model.Login;
import com.loginwebsite.websiteproject.model.User;
import com.loginwebsite.websiteproject.util.MoviesUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    public LoginController() {

    }

    @GetMapping("/showUserLogin")
    public String showForm(Model theModel) {
        Login userLogin = new Login();
        theModel.addAttribute("userLogin", userLogin);

        return "user-login";
    }

    @GetMapping("/sessionEndLogout")
    public String invalidateSession(HttpSession session, Model theModel) {
        if (session != null) {
            session.invalidate();
        }
        Login userLogin = new Login();
        theModel.addAttribute("userLogin", userLogin);
        return "user-login";
    }

    @PostMapping("/accessPassword")
    public String loginPassword(@ModelAttribute Login userLogin, Model theModel) {
        System.out.println("loginPassword() - theUser: " + userLogin.getLoginInput());
        theModel.addAttribute("userLogin", userLogin);

        AccountTableManager accountQueryMgr = new AccountTableManager();
        User theUser = accountQueryMgr.getAccountProfileByQuery(("SELECT id, firstName, lastName FROM movies.account " +
                "WHERE username ='%s'").formatted(userLogin.getLoginInput()) + " OR email = '%s'".formatted(userLogin.getLoginInput()) +
                " OR phoneNumber = '%s'".formatted(userLogin.getLoginInput()));

        String returnVal = null;
        if (theUser != null) {
            returnVal = "user-password";
        } else {
            theModel.addAttribute("errorMessage", "Account not found.");
            returnVal = "user-login";
        }
        return returnVal;
    }

    @PostMapping("/loginUserAccount")
    public String loginUserAccount(@ModelAttribute Login userLogin, Model theModel, HttpSession session) {

        System.out.println("theUser: " + userLogin.getLoginInput() + " " + userLogin.getPassword());

        AccountTableManager accountQueryMgr = new AccountTableManager();

        String password = userLogin.getPassword();
        User theUser = accountQueryMgr.getAccountProfileByQuery(("SELECT id, firstName, lastName FROM movies.account" +
                " WHERE (username ='%s'").formatted(userLogin.getLoginInput()) + " OR email = '%s'".formatted(userLogin.getLoginInput()) +
                " OR phoneNumber = '%s'".formatted(userLogin.getLoginInput()) + ") AND password ='%s'".formatted(password));

        String returnVal = null;

        if (theUser != null) {
            session.setAttribute("user", theUser);

            MoviesUtil.setMainMoviesInfo(theModel);

            returnVal = "home-page";
        } else {
            theModel.addAttribute("errorMessage", "Password is incorrect. Please try again.");
            theModel.addAttribute("userLogin", userLogin);
            returnVal = "user-password";
        }
        return returnVal;
    }
}