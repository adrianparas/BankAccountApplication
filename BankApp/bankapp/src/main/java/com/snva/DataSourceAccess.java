package com.snva;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceAccess {
    public static HikariDataSource getDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bank_account_db");
        config.setUsername("root");
        config.setPassword("ace#2000");
        return new HikariDataSource(config);
    }  
}
