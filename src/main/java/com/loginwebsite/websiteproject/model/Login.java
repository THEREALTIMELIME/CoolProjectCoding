package com.loginwebsite.websiteproject.model;

public class Login {

    private String loginInput;
    private String password;

    public Login(){
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginInput() {
        return loginInput;
    }

    public void setLoginInput(String loginInput) {
        this.loginInput = loginInput;
    }
}
