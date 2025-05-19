package com.loginwebsite.websiteproject.model;

public class MovieBio {
private String id;
private String name;
private String description;
private String rating;
private String studio;
private String director;
private String runTime;
private String poster;
private String trailer;
private int trending;
private int newest;

public MovieBio(){

}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getRunTime() {
        return runTime;
    }
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getTrailer() {
        return trailer;
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    public int getNewest() {
        return newest;
    }
    public void setNewest(int newest) {
        this.newest = newest;
    }
    public int getTrending() {
        return trending;
    }
    public void setTrending(int trending) {
        this.trending = trending;
    }
}