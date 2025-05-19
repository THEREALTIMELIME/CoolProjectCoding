package com.loginwebsite.websiteproject.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private String username;

    /**
     * ^: Matches the beginning of the string.
     * (?=.*[0-9]): Ensures at least one digit.
     * (?=.*[a-z]): Ensures at least one lowercase letter.
     * (?=.*[A-Z]): Ensures at least one uppercase letter.
     * (?=.*[!@#$%^&*()]): Ensures at least one special character.
     * (?=\\S+$): Ensures no whitespace.
     * .{8,20}: Ensures the length is between 8 and 20 characters.
     * $: Matches the end of the string.
     */
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\[\\]{}\\\\|;:\\'\",.\\<\\>\\/?~`])(?=\\S+$).{8,}$",
            message = "Password is invalid. It must follow these guidelines:\n" +
                    "\nAt least 8 characters or more.\nAt least 1 Uppercase Letter\n1 Lowercase Letter\n1 Number\n1 Special Character (Examples: &,@,!,#,*)")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "Phone Number length is incorrect.")
    private String phoneNumber;
    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void getAccountProfileByQuery(String formatted) {
    }

}