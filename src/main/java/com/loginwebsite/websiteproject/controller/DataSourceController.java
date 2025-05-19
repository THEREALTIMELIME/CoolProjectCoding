package com.loginwebsite.websiteproject.controller;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceController {
    public DataSourceController() {
    }

    public Properties getProperties() {
        Properties props = new Properties();
        try {
            Resource resource = new ClassPathResource("db/movies.db.properties");
            InputStream input = resource.getInputStream();
            props.load(input);
            return props;
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public MysqlDataSource getDataSource(Properties props) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));
        return dataSource;
    }
}