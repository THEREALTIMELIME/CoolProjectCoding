package com.loginwebsite.websiteproject.manager;

import com.loginwebsite.websiteproject.controller.DataSourceController;
import com.loginwebsite.websiteproject.model.MovieBio;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MovieTableManager {

    private Properties props;
    private MysqlDataSource dataSource;
    public MovieTableManager() {
        DataSourceController dataSourceController = new DataSourceController();
        this.props = dataSourceController.getProperties();
        this.dataSource = dataSourceController.getDataSource(this.props);
    }

    public MovieBio getMovieByQuery(String query) {
        MovieBio movie = new MovieBio();
        try (var connection = dataSource.getConnection(
                props.getProperty("user"),
                props.getProperty("password"));
             Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            movie.setName(resultSet.getString(1));
            movie.setRating(resultSet.getString(2));
            movie.setDescription(resultSet.getString(3));
            movie.setStudio(resultSet.getString(4));
            movie.setDirector(resultSet.getString(5));
            movie.setRunTime(resultSet.getString(6));
            movie.setPoster(resultSet.getString(7));
            movie.setTrailer(resultSet.getString(8));
            movie.setTrending(Integer.parseInt(resultSet.getString(9)));
            movie.setNewest(Integer.parseInt(resultSet.getString(10)));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return movie;
    }

    public MovieBio[] getMovieListByQuery(String query) {

        List<MovieBio> movieList = new ArrayList<>();

        try (var connection = dataSource.getConnection(props.getProperty("user"), this.props.getProperty("password"));
             Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            {
                while (resultSet.next()) {
                    MovieBio movie = new MovieBio();

                    movie.setName(resultSet.getString(1));
                    movie.setDirector(resultSet.getString(2));
                    movie.setDescription(resultSet.getString(3));
                    movie.setStudio(resultSet.getString(4));
                    movie.setRunTime(String.valueOf(Integer.parseInt(resultSet.getString(5))));
                    movie.setRating(resultSet.getString(6));
                    movie.setId(String.valueOf(Integer.parseInt(resultSet.getString(7))));
                    movie.setPoster(resultSet.getString(8));
                    movie.setTrailer(resultSet.getString(9));
                    movie.setTrending(Integer.parseInt(resultSet.getString(10)));
                    movie.setNewest(Integer.parseInt(resultSet.getString(11)));
                    movieList.add(movie);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movieList.toArray(new MovieBio[movieList.size()]);
    }
}
